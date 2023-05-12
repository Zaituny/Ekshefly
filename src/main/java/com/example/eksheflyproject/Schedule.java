package com.example.eksheflyproject;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class Schedule {
    private List<List<String>> days;
    private List<String> saturday;
    private List<String> sunday;
    private List<String> monday;
    private List<String> tuesday;
    private List<String> wednesday;
    private List<String> thursday;
    private List<String> friday;
    private List<String> shifts;


    public Schedule(String doctorID) {

        days = new ArrayList<>();

        saturday = new ArrayList<>();
        sunday = new ArrayList<>();
        monday = new ArrayList<>();
        tuesday = new ArrayList<>();
        wednesday = new ArrayList<>();
        thursday = new ArrayList<>();
        friday = new ArrayList<>();

        shifts = new ArrayList<>();

        saturday.add("Saturday:");
        sunday.add("Sunday:");
        monday.add("Monday:");
        tuesday.add("Tuesday:");
        wednesday.add("Wednesday:");
        thursday.add("Thursday:");
        friday.add("Friday:");

        days.add(saturday);
        days.add(sunday);
        days.add(monday);
        days.add(tuesday);
        days.add(wednesday);
        days.add(thursday);
        days.add(friday);

        String myQuery = "SELECT Username, Shift FROM Doctor WHERE ID = " + "\"" + doctorID + "\"";
        String username = "";
        String shift = "";

        List<String> shiftsInNumbers = new ArrayList<>();
        try {
            ResultSet resultSet = Connector.execute(myQuery, Path.getPath());
            while(resultSet.next()){
                username = resultSet.getString("Username");
                shift = resultSet.getString("Shift");
            }
            StringTokenizer tokenizer = new StringTokenizer(shift, ",");
            while (tokenizer.hasMoreTokens()) {
                shiftsInNumbers.add(tokenizer.nextToken());
            }
            for (int i = 0; i < shiftsInNumbers.size(); i++) {
                switch (shiftsInNumbers.get(i)){
                    case "0" :{
                        this.shifts.add("Off");
                    }
                    case "1" :{
                        this.shifts.add("Morning");
                    }
                    case "2" :{
                        this.shifts.add("Afternoon");
                    }
                    case "3" :{
                        this.shifts.add("Evening");
                    }
                    case "4" : {
                        this.shifts.add("Midnight");
                    }
                }
            }
            for (int i = 0; i < 7; i++) {
                myQuery = "SELECT * FROM " + "\"" + username + "'s Schedule\" WHERE ROWID = " + (i + 1);
                resultSet = Connector.execute(myQuery, Path.getPath());
                while(resultSet.next()){
                    days.get(i).add(resultSet.getString("firstPeriod"));
                    days.get(i).add(resultSet.getString("secondPeriod"));
                    days.get(i).add(resultSet.getString("thirdPeriod"));
                    days.get(i).add(resultSet.getString("fourthPeriod"));
                    days.get(i).add(resultSet.getString("fifthPeriod"));
                    days.get(i).add(resultSet.getString("sixthPeriod"));

                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<List<String>> getDays() {
        return days;
    }

    public List<String> getShifts() {
        return shifts;
    }
}
