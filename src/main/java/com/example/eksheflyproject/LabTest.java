package com.example.eksheflyproject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LabTest {
    private String patientID;
    private String testID;
    private String testType;
    private LocalDate testDate;
    private boolean status;

    public LabTest(String testType, LocalDate testDate, String patientID) {
        this.testType = testType;
        this.patientID = patientID;
        this.testDate = testDate;
    }
    public void setDataBase(){
        this.testID = generateID();
        String myQuery = "INSERT INTO LabTest VALUES(" + "\"" + this.testID + "\"" + ',' +
                "\"" + this.testType + "\"" + ',' +
                "\"" + this.testDate + "\"" + ',' +
                "\"" + this.patientID + "\"" + ',' +
                0 +")";
        try {
            Connector.executeWithoutResults(myQuery, Path.getPath());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    private String generateID(){
        int myID = (int) ((Math.random() * 9999) + 1000);
        String ID = "LT" + myID;
        String myQuery = "";
        boolean validID = false;
        while(!validID) {
            myQuery = "SELECT ID FROM LabTest WHERE ID = " + "\"" + ID + "\"";
            List<String> IDList = new ArrayList<>();
            try {
                ResultSet resultSet = Connector.execute(myQuery, Path.getPath());
                while (resultSet.next()) {
                    IDList.add(resultSet.getString("ID"));
                }
                while (IDList.contains(ID)) {
                    myID = (int) ((Math.random() * 9999) + 1000);
                    ID = "LT" + myID;
                }
                if (!IDList.contains(ID)) {
                    validID = true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ID;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public void setTestID(String testID) {
        this.testID = testID;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public void setTestDate(LocalDate testDate) {
        this.testDate = testDate;
    }

    public String getTestID() {return testID;}

    public String getTestType() {
        return testType;
    }


    public LocalDate getTestDate() {
        return testDate;
    }


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
