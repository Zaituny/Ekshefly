package com.example.eksheflyproject;


import javafx.scene.control.Alert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.*;


public class Admin extends User{
    public Admin(){

    }

    public Admin(String firstName, String lastName, String userName,String passWord, int age, String gender, String ID) {
        super(firstName, lastName, userName,passWord, age, gender, ID);
    }

    private String generateID(String userType,String prefix) {
        int myID = (int) ((Math.random() * 9999) + 1000);
        String ID = "";
        String myQuery = "";
        boolean validID = false;
        while(!validID) {
            ID = prefix + myID;
            myQuery = "SELECT ID FROM " + userType + " WHERE ID = " + "\"" + ID + "\"";
            List<String> IDList = new ArrayList<>();
            try {
                ResultSet resultSet = Connector.execute(myQuery, Path.getPath());
                while (resultSet.next()) {
                    IDList.add(resultSet.getString("ID"));
                }
                while (IDList.contains(ID)) {
                    myID = (int) ((Math.random() * 9999) + 1000);
                    ID = prefix + myID;
                }
                if (!IDList.contains(ID)) {
                    validID = true;
                }
                Connector.executeWithoutResults(myQuery, Path.getPath());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ID;
    }

    public List<String> getUserInfo(String firstName, String lastName, String username, String password, int age, String gender){
        //ArrayList is in order[firstName(0), lastName(1), userName(2), password(3)
        // , age(4), gender(5)]
        List<String> strings = new ArrayList<>();
        boolean validUsername = false;
        String myQuery = "";

        strings.add(firstName.toUpperCase(Locale.ROOT));

        strings.add(lastName.toUpperCase(Locale.ROOT));

        while(!validUsername){
            myQuery = "SELECT Username FROM Doctor WHERE Username = " + "\"" + username + "\"";
            try {
                List<String> myDoctorUsername = new ArrayList<>();
                List<String> myPatientUsername = new ArrayList<>();
                List<String> myPharmacistUsername = new ArrayList<>();
                ResultSet resultSet = Connector.execute(myQuery, Path.getPath());
                while(resultSet.next()){
                    myDoctorUsername.add(resultSet.getString("Username"));
                }
                myQuery = "SELECT Username FROM Patient WHERE Username = " + "\"" + username + "\"";
                resultSet = Connector.execute(myQuery, Path.getPath());
                while(resultSet.next()){
                    myPatientUsername.add(resultSet.getString("Username"));
                }
                myQuery = "SELECT Username FROM Pharmacist WHERE Username = " + "\"" + username + "\"";
                resultSet = Connector.execute(myQuery, Path.getPath());
                while(resultSet.next()){
                    myPharmacistUsername.add(resultSet.getString("Username"));
                }
                while(myDoctorUsername.contains(username) || myPatientUsername.contains(username) || myPharmacistUsername.contains(username)){
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Username already used");
                }
                if(!myDoctorUsername.contains(username) && !myPatientUsername.contains(username) && !myPharmacistUsername.contains(username)){
                    validUsername = true;
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }

        }
        strings.add(username);

        strings.add(password);

        strings.add(age + "");

        strings.add(gender.toUpperCase(Locale.ROOT));

        return strings;
    }
    public void createDoctorAccount(String firstName,
                                    String lastName,
                                    String username,
                                    String password,
                                    int age,
                                    String gender,
                                    String speciality,
                                    String PhoneNumber,
                                    String [] ScheduleList){
        String myQuery = "";
        String schedule = String.join(",", ScheduleList);
        List<String> strings = getUserInfo(firstName, lastName, username, password, age, gender);

        String ID = generateID("Doctor", "DR");

        myQuery = "INSERT INTO Doctor VALUES(" + "\"" + ID + "\""+ ','+
                "\"" + /*username*/strings.get(2) + "\"" + ',' +
                "\"" + /*password*/strings.get(3) + "\"" + ',' +
                "\"" + /*firstName*/strings.get(0) + "\"" + ',' +
                "\"" + /*lastName*/strings.get(1) + "\"" + ',' +
                /*age*/Integer.parseInt(strings.get(4)) + ',' +
                "\"" + /*gender*/strings.get(5) + "\"" + ',' +
                "\"" + speciality + "\"" + ',' +
                "\"" + PhoneNumber + "\""+ ',' +
                "\"" + schedule + "\"" + ")";
        try {
            Connector.executeWithoutResults(myQuery, Path.getPath());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        myQuery = "CREATE TABLE IF NOT EXISTS" + "\"" + strings.get(2) + "'s Appointments\"" +
                "(\"" + "PatientFirstName" + "\""	+ " TEXT," +
                "\"" + "PatientLastName" + "\""	+ " TEXT," +
                "\"" + "Date"+ "\""	+ " TEXT,"+
                "\"" + "RoomNumber" + "\""	+ " INTEGER UNIQUE)";
        try {
            Connector.executeWithoutResults(myQuery, Path.getPath());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        myQuery = "CREATE TABLE IF NOT EXISTS" + "\"" + strings.get(2) + "'s Schedule\"" +
                "(\"" + "firstPeriod" + "\""	+ " TEXT," +
                "\"" + "secondPeriod" + "\""	+ " TEXT," +
                "\"" + "thirdPeriod"+ "\""	+ " TEXT,"+
                "\"" + "fourthPeriod" + "\""	+ " TEXT,"+
                "\"" + "fifthPeriod"+ "\""	+ " TEXT,"+
                "\"" + "sixthPeriod"+ "\""	+ " TEXT)";
        try {
            Connector.executeWithoutResults(myQuery, Path.getPath());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 7; i++) {
            myQuery = "INSERT INTO " + "\"" + strings.get(2) + "'s Schedule"+ "\"" + "VALUES(\"\",\"\",\"\",\"\",\"\",\"\")";
            try {
                Connector.executeWithoutResults(myQuery, Path.getPath());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void createPatientAccount(String firstName,
                                     String lastName,
                                     String username,
                                     String password,
                                     int age,
                                     String gender,
                                     String bloodType){

        List<String> strings = getUserInfo(firstName, lastName, username, password, age, gender);
        String myQuery = "";
        String ID = generateID("Patient", "PT");
        myQuery = "INSERT INTO Patient VALUES(" + "\"" + ID + "\""+ ','+
                "\"" + /*username*/strings.get(2) + "\"" + ',' +
                "\"" + /*password*/strings.get(3) + "\"" + ',' +
                "\"" + /*firstName*/strings.get(0) + "\"" + ',' +
                "\"" + /*lastName*/strings.get(1) + "\"" + ',' +
                /*age*/Integer.parseInt(strings.get(4)) + ',' +
                "\"" + /*gender*/strings.get(5) + "\"" + ',' +
                "\"" + /*bloodType*/bloodType + "\"" + ")";
        try {
            Connector.executeWithoutResults(myQuery, Path.getPath());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        myQuery = "CREATE TABLE IF NOT EXISTS" + "\"" + strings.get(2) + "'s Appointments\"" +
                "(\"" + "DoctorFirstName" + "\""	+ " TEXT," +
                "\"" + "DoctorLastName" + "\""	+ " TEXT," +
                "\"" + "Speciality" + "\""	+ " TEXT," +
                "\"" + "Date"+ "\""	+ " TEXT,"+
                "\"" + "RoomNumber" + "\""	+ " INTEGER UNIQUE)";
        try {
            Connector.executeWithoutResults(myQuery, Path.getPath());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void createPharmacistAccount(String firstName,
                                        String lastName,
                                        String username,
                                        String password,
                                        int age,
                                        String gender){

        List<String> strings = getUserInfo(firstName, lastName, username, password, age, gender);
        String myQuery = "";
        String ID = generateID("Pharmacist", "PHAR");
        myQuery = "INSERT INTO Pharmacist VALUES(" + "\"" + ID + "\""+ ','+
                "\"" + /*username*/strings.get(2) + "\"" + ',' +
                "\"" + /*password*/strings.get(3) + "\"" + ',' +
                "\"" + /*firstName*/strings.get(0) + "\"" + ',' +
                "\"" + /*lastName*/strings.get(1) + "\"" + ',' +
                /*age*/Integer.parseInt(strings.get(4)) + ',' +
                "\"" + /*gender*/strings.get(5) + "\"" + ")";
        try {
            Connector.executeWithoutResults(myQuery, Path.getPath());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteDoctorAccount(String username){
        String myQuery = "DELETE FROM Doctor WHERE Username = " + "\"" + username + "\"";
        try {
            Connector.executeWithoutResults(myQuery, Path.getPath());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        myQuery = "DROP TABLE IF EXISTS" + "\"" + username +"\"";
        try {
            Connector.executeWithoutResults(myQuery, Path.getPath());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        myQuery = "DROP TABLE IF EXISTS" + "\"" + username + "'s Appointments\"";
        try {
            Connector.executeWithoutResults(myQuery, Path.getPath());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        myQuery = "DROP TABLE IF EXISTS" + "\"" + username + "'s Schedule\"";
        try {
            Connector.executeWithoutResults(myQuery, Path.getPath());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePatientAccount(String username){
        String myQuery = "DELETE FROM Patient WHERE Username = " + "\"" + username + "\"";
        try {
            Connector.executeWithoutResults(myQuery, Path.getPath());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        myQuery = "DROP TABLE IF EXISTS" + "\"" + username +"\"";
        try {
            Connector.executeWithoutResults(myQuery, Path.getPath());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        myQuery = "DROP TABLE IF EXISTS" + "\"" + username + "'s Appointments\"";
        try {
            Connector.executeWithoutResults(myQuery, Path.getPath());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deletePharmacistAccount(String username){
        String myQuery = "DELETE FROM Pharmacist WHERE Username = " + "\"" + username + "\"";
        try {
            Connector.executeWithoutResults(myQuery, Path.getPath());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<LocalDateTime> findAvailableAppointments(String DoctorID){
        String myQuery = "SELECT Username,Shift FROM Doctor WHERE ID = " + "\"" + DoctorID + "\"";
        LocalDate currentDate = LocalDate.now();
        LocalDate date;
        String time = "";
        String username = "";
        String shifts = "";
        List<String> shiftsList = new ArrayList<>();
        List<String> rowData = new ArrayList<>();
        List<LocalDateTime> availablePeriods = new ArrayList<>();
        List<Integer> index = new ArrayList<>();
        int whichDay = 0;
        int howManyAppointments = 0;
        try {
            ResultSet resultSet = Connector.execute(myQuery, Path.getPath());
            while(resultSet.next()){
                username = resultSet.getString("Username");
                shifts = resultSet.getString("Shift");
                String temp = resultSet.getString("Shift");
                StringTokenizer tokenizer = new StringTokenizer(temp, ",");
                while (tokenizer.hasMoreTokens()) {
                    shiftsList.add(tokenizer.nextToken());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        myQuery = "SELECT * FROM " + "\"" + username + "'s Schedule" + "\"";
        try {
            ResultSet resultSet = Connector.execute(myQuery, Path.getPath());
            while(resultSet.next()){
                whichDay++;
                rowData.clear();
                rowData.add(resultSet.getString("firstPeriod"));
                rowData.add(resultSet.getString("secondPeriod"));
                rowData.add(resultSet.getString("thirdPeriod"));
                rowData.add(resultSet.getString("fourthPeriod"));
                rowData.add(resultSet.getString("fifthPeriod"));
                rowData.add(resultSet.getString("sixthPeriod"));
                index.clear();
                for (int i = 0; i < rowData.size(); i++) {
                    if(rowData.get(i).equals("")){
                        index.add(i + 1);
                    }
                }
                for (int i = 0; i < index.size(); i++) {
                    switch (whichDay) {
                        case 1 :{
                            date = currentDate.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
                            availablePeriods = setAppointment(shiftsList, whichDay, index, i, howManyAppointments, availablePeriods, date, time);
                        }
                        case 2 : {
                            date = currentDate.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
                            availablePeriods = setAppointment(shiftsList, whichDay, index, i, howManyAppointments, availablePeriods, date, time);
                        }
                        case 3 : {
                            date = currentDate.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
                            availablePeriods = setAppointment(shiftsList, whichDay, index, i, howManyAppointments, availablePeriods, date, time);
                        }
                        case 4 : {
                            date = currentDate.with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
                            availablePeriods = setAppointment(shiftsList, whichDay, index, i, howManyAppointments, availablePeriods, date, time);
                        }
                        case 5 : {
                            date = currentDate.with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY));
                            availablePeriods = setAppointment(shiftsList, whichDay, index, i, howManyAppointments, availablePeriods, date, time);
                        }
                        case 6 : {
                            date = currentDate.with(TemporalAdjusters.next(DayOfWeek.THURSDAY));
                            availablePeriods = setAppointment(shiftsList, whichDay, index, i, howManyAppointments, availablePeriods, date, time);
                        }
                        case 7 : {
                            date = currentDate.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
                            availablePeriods = setAppointment(shiftsList, whichDay, index, i, howManyAppointments, availablePeriods, date, time);
                        }
                    }
                }


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return availablePeriods;

    }

    private List<LocalDateTime> setAppointment(List<String> shiftsList,
                                               int whichDay,
                                               List<Integer> index,
                                               int i,
                                               int howManyAppointments,
                                               List<LocalDateTime> availablePeriods,
                                               LocalDate date,
                                               String time) {

        if ("Afternoon".equals(shiftsList.get(whichDay - 1))) {
            switch (index.get(i)) {
                case 1:
                    if (howManyAppointments <= 4) {
                        time = "12:00";
                        availablePeriods.add(LocalDateTime.parse(date + "" + 'T' + time));

                    }
                    break;
                case 2:
                    if (howManyAppointments <= 4) {
                        time = "01:00";
                        availablePeriods.add(LocalDateTime.parse(date + "" + 'T' + time));
                    }
                    break;
                case 3:
                    if (howManyAppointments <= 4) {
                        time = "02:00";
                        availablePeriods.add(LocalDateTime.parse(date + "" + 'T' + time));
                    }
                    break;
                case 4:
                    if (howManyAppointments <= 4) {
                        time = "03:00";
                        availablePeriods.add(LocalDateTime.parse(date + "" + 'T' + time));
                    }
                    break;
                case 5:
                    if (howManyAppointments <= 4) {
                        time = "04:00";
                        availablePeriods.add(LocalDateTime.parse(date + "" + 'T' + time));
                    }
                    break;
                case 6:
                    if (howManyAppointments <= 4) {
                        time = "05:00";
                        availablePeriods.add(LocalDateTime.parse(date + "" + 'T' + time));
                    }
                    break;
            }
        }
        return  availablePeriods;
    }

//    public Appointment createAppointment(String patientID, String doctorID){
//        List<LocalDateTime> availableAppointments;
//        int roomNumber;
//
//
//
//
//        Patient patient = new Patient(patientID);
//
//        Doctor doc = new Doctor(doctorID);
//
//        availableAppointments = findAvailableAppointments(doctorID);
//
//        System.out.println("Choose a date from the following list");
//        for (int i = 0; i < availableAppointments.size(); i++) {
//            System.out.println(availableAppointments.get(i));
//        }
//
//        LocalDateTime pickedDate = LocalDateTime.parse(myScanner.nextLine());
//
//
//        Appointment appointment = new Appointment(pickedDate, patient.getFirstName(), patient.getLastName(),patientID, doctorID,
//                roomNumber,doc.getSpeciality(), doc.getFirstName(), doc.getLastName());
//
//
//        return appointment;
//    }




    public void setRequiredOperations(String patientID, String surgery){
        String myQuery = "SELECT Surgeries FROM History WHERE ID = " + "\"" + patientID + "\"";
        String surgeries = "";
        List<String> surgeriesList = new ArrayList<String>();
        try {
            ResultSet resultSet = Connector.execute(myQuery, Path.getPath());
            while(resultSet.next()){
                surgeries = resultSet.getString("Surgeries");
            }
            StringTokenizer tokenizer = new StringTokenizer(surgeries, ",");
            while (tokenizer.hasMoreTokens()) {
                surgeriesList.add(tokenizer.nextToken());
            }
            surgeriesList.add(surgery);
            surgeries = String.join(",", surgeriesList);
            myQuery = "UPDATE History SET Surgeries = " + "\"" + surgeries + "\" WHERE PatientID = " + "\"" + patientID + "\"";
            Connector.executeWithoutResults(myQuery, Path.getPath());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public void viewSchedule(String doctorID){
//        Schedule sched = new Schedule(doctorID);
//        System.out.format("%16s%16s%16s%16s%16s%16s%16s%16s\n", "","Shift", "First period",
//                "Second period",
//                "Third period",
//                "Fourth period",
//                "Fifth period",
//                "Sixth period");
//        for (int i = 0; i < 7; i++) {
//            System.out.format("%16s%16s%16s%16s%16s%16s%16s%16s\n", sched.getDays().get(i).get(0),
//                    sched.getShifts().get(i),
//                    sched.getDays().get(i).get(1),
//                    sched.getDays().get(i).get(2),
//                    sched.getDays().get(i).get(3),
//                    sched.getDays().get(i).get(4),
//                    sched.getDays().get(i).get(5),
//                    sched.getDays().get(i).get(6));
//        }
//
//    }


}
