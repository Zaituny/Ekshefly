package com.example.eksheflyproject;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Appointment {
    private LocalDateTime date;
    private String day;
    private String time;
    private String patientID;
    private String doctorID;
    private String patientUsername;
    private String doctorUsername;
    private String doctorFirstName;
    private String doctorLastName;
    private String patientFirstName;
    private String patientLastName;
    private String speciality;
    private int roomNumber;

    public Appointment(LocalDateTime date, String patientFirstName, String patientLastName, String patientID,String doctorID,
                       int roomNumber, String speciality, String doctorFirstName, String doctorLastName) {
        this.date = date;
        this.doctorID = doctorID;
        this.patientID = patientID;
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
        this.doctorFirstName = doctorFirstName;
        this.doctorLastName = doctorLastName;
        this.roomNumber = roomNumber;
        this.speciality = speciality;
        setDataBase();
    }
    public void setDataBase(){
        String myQuery = "SELECT Username FROM Doctor WHERE ID = " + "\"" + this.doctorID + "\"";
        try {
            ResultSet resultSet = Connector.execute(myQuery, Path.getPath());
            while(resultSet.next()){
                doctorUsername = resultSet.getString("Username");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        myQuery = "INSERT INTO " + "\"" + doctorUsername + "'s Appointments\"" + " VALUES(" +
                "\"" + this.patientFirstName + "\"" + ',' +
                "\"" + this.patientLastName + "\"" + ',' +
                "\"" + this.date + "\"" + ',' +
                "\"" + this.roomNumber + "\")";
        try {
            Connector.executeWithoutResults(myQuery, Path.getPath());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        myQuery = "SELECT Username FROM Patient WHERE ID = " + "\"" + this.patientID + "\"";
        try {
            ResultSet resultSet = Connector.execute(myQuery, Path.getPath());
            while(resultSet.next()){
                patientUsername = resultSet.getString("Username");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        myQuery = "INSERT INTO " + "\"" + patientUsername + "'s Appointments\"" + " VALUES(" +
                "\"" + doctorFirstName + "\"" + ',' +
                "\"" + doctorLastName + "\"" + ',' +
                "\"" + speciality + "\"" + ',' +
                "\"" + date + "\"" + ',' +
                "\"" + roomNumber + "\")";
        try {
            Connector.executeWithoutResults(myQuery, Path.getPath());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String[] parts = (date + "").split("T");
        day = date.getDayOfWeek() + "";
        time = parts[1];
        switch(day){
            case "SATURDAY" :{
                setTimeOfDay(doctorUsername, 1);
                break;
            }
            case "SUNDAY" :{
                setTimeOfDay(doctorUsername, 2);
            }
            case "MONDAY" :{
                setTimeOfDay(doctorUsername, 3);
                break;
            }
            case "TUESDAY" :{
                setTimeOfDay(doctorUsername, 4);
                break;
            }
            case "WEDNESDAY" :{
                setTimeOfDay(doctorUsername, 5);
                break;
            }
            case "THURSDAY" :{
                setTimeOfDay(doctorUsername, 6);
                break;
            }
            case "FRIDAY" :{
                setTimeOfDay(doctorUsername, 7);
                break;
            }
        }
        updateDatabase();
    }
    private void setTimeOfDayHelper(String doctorUsername, int rowNumber, String columnName){
        String myQuery = "";
        myQuery = "UPDATE " + "\"" + doctorUsername + "'s Schedule\"" + " SET " + "\"" + columnName + "\"" + " = \"RESERVED\" WHERE ROWID = " + rowNumber;
        try {
            Connector.executeWithoutResults(myQuery, Path.getPath());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void setTimeOfDay(String doctorUsername, int rowNumber){

        switch (time){
            case "12:00" :{
                setTimeOfDayHelper(doctorUsername, rowNumber, "firstPeriod");
                break;
            }
            case "01:00" : {
                setTimeOfDayHelper(doctorUsername, rowNumber, "secondPeriod");
                break;
            }
            case "02:00" :{
                setTimeOfDayHelper(doctorUsername, rowNumber, "thirdPeriod");
                break;
            }
            case "03:00" :{
                setTimeOfDayHelper(doctorUsername, rowNumber, "fourthPeriod");
                break;
            }
            case "04:00" :{
                setTimeOfDayHelper(doctorUsername, rowNumber, "fifthPeriod");
                break;
            }
            case "05:00" :{
                setTimeOfDayHelper(doctorUsername, rowNumber, "sixthPeriod");
                break;
            }
        }
    }

    private void updateDatabase(){
        String myQuery = "SELECT Appointments FROM History WHERE ID = " + "\"" + patientID + "\"";
        String appointments ="";
        List<String> appointmentsList = new ArrayList<>();
        try{
            ResultSet resultSet = Connector.execute(myQuery, Path.getPath());
            while(resultSet.next()){
                appointments = resultSet.getString("Appointments");
            }
            StringTokenizer tokenizer = new StringTokenizer(appointments, ",");
            while (tokenizer.hasMoreTokens()) {
                appointmentsList.add(tokenizer.nextToken());
            }
            appointmentsList.add(date + "");
            appointments = String.join(",", appointmentsList);
            myQuery = "UPDATE History SET Appointments WHERE ID = " + "\"" + patientID + "\"";
            Connector.executeWithoutResults(myQuery, Path.getPath());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public LocalDateTime getDate() {
        return date;
    }


    public String getFirstName() {
        return patientFirstName;
    }


    public String getPatientName() {
        return patientLastName;
    }

}
