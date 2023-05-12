package com.example.eksheflyproject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class Doctor extends User {

    private Schedule schedule;
    private String speciality;
    private String shift;
    private String address;
    private String phoneNumber;
    List<Appointment> appointments;
    public Doctor(){}
    public Doctor(String ID){
        super("Doctor", ID);
        String myQuery = "SELECT * FROM Doctor WHERE ID = " + "\"" + ID + "\"";
        try {
            ResultSet resultSet = Connector.execute(myQuery, Path.getPath());
            while(resultSet.next()){
                this.speciality = resultSet.getString("Speciality");
                this.shift = resultSet.getString("Shift");
                this.address = resultSet.getString("Address");
                this.phoneNumber = resultSet.getString("PhoneNumber");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Doctor(String firstName,String lastName, String userName,String passWord, int age, String gender, String ID,
                  String speciality, String address, String phoneNumber) {
        super(firstName,lastName, userName, passWord, age, gender, ID);
        this.speciality = speciality;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Doctor(String firstName,String lastName, String userName, String passWord, int age, String gender,
                  String ID, String speciality,String phoneNumber) {
        this(firstName, lastName, userName, passWord, age, gender, ID, speciality, "none", phoneNumber);
    }

    public Doctor(String firstName,String lastName, String userName, String passWord, int age, String gender,String ID, String speciality,String phoneNumber,Schedule schedule) {
        this(firstName, lastName,userName,passWord, age, gender, ID,speciality, "none", phoneNumber);
        this.schedule = schedule;
    }

    public Schedule getSchedule(){
        return schedule;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public void setSchedule(Schedule schedule){
        this.schedule = schedule;
    }
    public List<Appointment> getAppointments(){
        return appointments;
    }
    public void setAppointments(List<Appointment> appointments){
        this.appointments = appointments;
    }

    public LabTest requestTest(String patientID, String testName){
        LabTest labtest = new LabTest(testName, LocalDate.now(), patientID);
        labtest.setDataBase();
        setLabHistory(labtest);
        return labtest;
    }

    private void setLabHistory(LabTest test){
        String myQuery = "SELECT LapReports FROM History WHERE ID = " + "\"" + test.getPatientID() + "\"";
        String lapReports = "";
        List<String> reportsList = new ArrayList<String>();
        try {
            ResultSet resultSet = Connector.execute(myQuery, Path.getPath());
            while(resultSet.next()){
                lapReports = resultSet.getString("LapReports");
            }
            StringTokenizer tokenizer = new StringTokenizer(lapReports, ",");
            while (tokenizer.hasMoreTokens()) {
                reportsList.add(tokenizer.nextToken());
            }
            reportsList.add(test.getTestID());
            lapReports = String.join(",", reportsList);
            myQuery = "UPDATE History SET LapReports = " + "\"" + lapReports + "\" WHERE PatientID = " + "\"" + test.getPatientID() + "\"";
            Connector.executeWithoutResults(myQuery, Path.getPath());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void diagnosis(String diagnosis, String patientID){
        String myQuery = "SELECT Diagnosis FROM History WHERE PatientID = " + "\"" + patientID + "\"";
        String diagnos = "";
        List<String> diagnosisList = new ArrayList<>();
        try{
            ResultSet resultSet = Connector.execute(myQuery, Path.getPath());
            while(resultSet.next()){
                diagnos = resultSet.getString("Diagnosis");
            }
            StringTokenizer tokenizer = new StringTokenizer(diagnos, ",");
            while (tokenizer.hasMoreTokens()) {
                diagnosisList.add(tokenizer.nextToken());
            }
            diagnosisList.add(diagnosis);
            diagnos = String.join(",", diagnosisList);
            myQuery = "UPDATE History SET Diagnosis = " + "\"" + diagnos + "\"" + " WHERE PatientID = " + "\"" + patientID + "\"";
            Connector.executeWithoutResults(myQuery, Path.getPath());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void prescribeDrugs(String drugName, String patientID){
        String myQuery = "SELECT PrescribedDrugs FROM History WHERE PatientID = " + "\"" + patientID + "\"";
        String drugs ="";
        List<String> drugsList = new ArrayList<>();
        try{
            ResultSet resultSet = Connector.execute(myQuery, Path.getPath());
            while(resultSet.next()){
                drugs = resultSet.getString("PrescribedDrugs");
            }
            StringTokenizer tokenizer = new StringTokenizer(drugs, ",");
            while (tokenizer.hasMoreTokens()) {
                drugsList.add(tokenizer.nextToken());
            }
            drugsList.add(drugName);
            drugs = String.join(",", drugsList);
            myQuery = "UPDATE History SET PrescribedDrugs = " + "\"" + drugs + "\""+ " WHERE PatientID = " + "\"" + patientID + "\"";
            Connector.executeWithoutResults(myQuery, Path.getPath());

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void viewHistory(String patientID){
        History history = new History(patientID);
        System.out.format("%64s\n", patientID);
        System.out.format("%20s%20s%20s%20s%20s\n", "Diagnosis", "LabTests", "Appointments", "PrescribedDrugs", "Surgeries");

        List<Integer> size= new ArrayList<>();

        size.add(history.getDiagnosis().size());
        size.add(history.getPrescribedDrugs().size());
        size.add(history.getLabTests().size());
        size.add(history.getAppointments().size());
        size.add(history.getSurgeries().size());

        List<List<String>> lists = new ArrayList<>();

        lists.add(history.getDiagnosis());
        lists.add(history.getPrescribedDrugs());
        lists.add(history.getLabTests());
        lists.add(history.getAppointments());
        lists.add(history.getSurgeries());

        int max = Collections.max(size);

        for (int i = 0; i < size.size(); i++) {
            if(size.get(i) < max){
                for (int j = 0; j < max - size.get(i); j++) {
                    lists.get(i).add("");
                }
            }
        }
        for (int i = 0; i < max; i++) {
            System.out.format("%20s%20s%20s%20s%20s\n", history.getDiagnosis().get(i),
                    history.getLabTests().get(i),
                    history.getAppointments().get(i),
                    history.getPrescribedDrugs().get(i),
                    history.getSurgeries().get(i));
        }

    }
    public void viewSchedule(String doctorID){
        Schedule sched = new Schedule(doctorID);
        System.out.format("%16s%16s%16s%16s%16s%16s%16s%16s\n", "","Shift", "First period",
                "Second period",
                "Third period",
                "Fourth period",
                "Fifth period",
                "Sixth period");
        for (int i = 0; i < 7; i++) {
            System.out.format("%16s%16s%16s%16s%16s%16s%16s%16s\n", sched.getDays().get(i).get(0),
                    sched.getShifts().get(i),
                    sched.getDays().get(i).get(1),
                    sched.getDays().get(i).get(2),
                    sched.getDays().get(i).get(3),
                    sched.getDays().get(i).get(4),
                    sched.getDays().get(i).get(5),
                    sched.getDays().get(i).get(6));
        }

    }

}



