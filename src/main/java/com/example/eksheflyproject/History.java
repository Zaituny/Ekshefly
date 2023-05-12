package com.example.eksheflyproject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class History {
    List<String> prescribedDrugs;
    List<String> diagnosis;
    List<String> surgeries;
    List<String> labTests;
    List<String> appointments;

    public History() {

        this.diagnosis = new ArrayList<String>();
        this.prescribedDrugs = new ArrayList<String>();
        this.labTests = new ArrayList<String>();
        this.appointments = new ArrayList<String>();
        this.surgeries = new ArrayList<String>();
    }
    public History(String patientID){

        this.diagnosis = new ArrayList<String>();
        this.prescribedDrugs = new ArrayList<String>();
        this.labTests = new ArrayList<String>();
        this.appointments = new ArrayList<String>();
        this.surgeries = new ArrayList<String>();

        String myQuery = "SELECT * FROM History WHERE PatientID = " + "\"" + patientID + "\"";
        try {
            ResultSet resultSet = Connector.execute(myQuery, Path.getPath());
            while(resultSet.next()){
                setPrescribedDrugs(resultSet.getString("PrescribedDrugs"));
                setDiagnosis(resultSet.getString("Diagnosis"));
                setLabTests(resultSet.getString("LabReports"));
                setAppointments(resultSet.getString("Appointments"));
                setSurgeries(resultSet.getString("Surgeries"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getPrescribedDrugs() {
        return prescribedDrugs;
    }

    public void setPrescribedDrugs(String prescribedDrugsString) {
        StringTokenizer tokenizer = new StringTokenizer(prescribedDrugsString, ",");
        while (tokenizer.hasMoreTokens()) {
            prescribedDrugs.add(tokenizer.nextToken());
        }
    }

    public List<String> getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        StringTokenizer tokenizer = new StringTokenizer(diagnosis, ",");
        while (tokenizer.hasMoreTokens()) {
            this.diagnosis.add(tokenizer.nextToken());
        }
    }

    public List<String> getSurgeries() {
        return surgeries;
    }

    public void setSurgeries(String surgeries) {
        StringTokenizer tokenizer = new StringTokenizer(surgeries, ",");
        while (tokenizer.hasMoreTokens()) {
            this.surgeries.add(tokenizer.nextToken());
        }
    }

    public List<String> getLabTests() {
        return labTests;
    }

    public void setLabTests(List<String> labTests) {
        this.labTests = labTests;
    }

    public List<String> getAppointments() {
        return appointments;
    }

    public void setAppointments(String appointments) {
        StringTokenizer tokenizer = new StringTokenizer(appointments, ",");
        while (tokenizer.hasMoreTokens()) {
            this.appointments.add(tokenizer.nextToken());
        }
    }

    public void setLabTests(String labReports)
    {
        StringTokenizer tokenizer = new StringTokenizer(labReports, ",");
        while (tokenizer.hasMoreTokens()) {
            labTests.add(tokenizer.nextToken());
        }
    }
}