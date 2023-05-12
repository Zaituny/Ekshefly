package com.example.eksheflyproject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Patient extends User {
    private String bloodType;
    private History sickness;

    public Patient(String firstName, String lastName,String userName ,String password, int age,String gender,  String ID, String bloodType) {
        super(firstName, lastName, userName,password, age, gender, ID);
    }

    public Patient(String ID){
        super("Patient", ID);
        String myQuery = "SELECT BloodType FROM Patient WHERE ID = " + "\"" + ID + "\"";
        try {
            ResultSet resultSet = Connector.execute(myQuery, Path.getPath());
            while(resultSet.next()){
                this.bloodType = resultSet.getString("BloodType");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public History viewHistory(){
        //this method for showing the history we just made a new object to
        // be a placeholder only
        return new History();
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public History getSickness() {
        return sickness;
    }

    public void setSickness(History sickness) {
        this.sickness = sickness;
    }


    public Schedule getSchedule(String DoctorName, String Speciality){
        return null;
    }

    public void viewSchedule(){}


}