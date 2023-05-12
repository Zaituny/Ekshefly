package com.example.eksheflyproject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Rooms {
    private String roomType;
    private String roomLocation;
    private boolean availability;
    private int capacity;
    private int roomNO;
    private int currentPatientCount;

    public Rooms(int roomNO) {
        this.roomNO = roomNO;
        String myQuery = "SELECT * FROM Rooms WHERE RoomNumber = " + "\"" + roomNO + "\"";
        try {
            ResultSet resultSet = Connector.execute(myQuery, Path.getPath());
            while(resultSet.next()){
                this.currentPatientCount = resultSet.getInt("CurrentPatientCount");
                this.capacity = resultSet.getInt("Capacity");
                this.roomLocation = resultSet.getString("RoomLocation");
                this.availability = (this.currentPatientCount < this.capacity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static List<Rooms> availableRooms(){
        String myQuery = "SELECT RoomNumber FROM Rooms WHERE CurrentPatientCount < Capacity";
        List<Integer> roomNOs= new ArrayList<Integer>();
        List<Rooms> rooms = new ArrayList<Rooms>();
        try {
            ResultSet resultSet = Connector.execute(myQuery, Path.getPath());
            while(resultSet.next()){
                roomNOs.add(resultSet.getInt("RoomNumber"));
            }
            for (Integer roomNo : roomNOs) {
                rooms.add(new Rooms(roomNo));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rooms;

    }




    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getRoomLocation() {
        return roomLocation;
    }

    public void setRoomLocation(String roomLocation) {
        this.roomLocation = roomLocation;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getRoomNO() {
        return roomNO;
    }

    public void setRoomNO(int roomNO) {
        this.roomNO = roomNO;
    }

    public int getCurrentPatientCount() {
        return currentPatientCount;
    }

    public void setCurrentPatientCount(int currentPatientCount) {
        this.currentPatientCount = currentPatientCount;
    }

    public boolean checkAvailability(){
        return false;
    }

    public boolean isAvailable() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
