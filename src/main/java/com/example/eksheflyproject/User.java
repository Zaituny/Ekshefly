package com.example.eksheflyproject;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class User  {

    private String ID;
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private int age;
    private String gender;

    public User(String firstName, String lastName, String userName,String password, int age, String gender, String ID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.ID = ID;
    }
    public User(){
    }
    public User (String tableName, String ID){
        String myQuery = "SELECT * FROM "+ tableName + " WHERE ID = " + "\"" + ID + "\"";
        try {
            ResultSet resultSet = Connector.execute(myQuery, Path.getPath());
            while(resultSet.next()){
                this.firstName = resultSet.getString("FirstName");
                this.lastName = resultSet.getString("LastName");
                this.ID = ID;
                this.userName = resultSet.getString("UserName");
                this.age = resultSet.getInt("Age");
                this.gender = resultSet.getString("Gender");
                this.password = resultSet.getString("PassWord");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}