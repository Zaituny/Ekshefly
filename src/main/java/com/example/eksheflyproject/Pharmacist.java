package com.example.eksheflyproject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Pharmacist extends User{

    public Pharmacist(String firstName,String lastName, String userName,String passWord, int age, String gender ,String ID) {
        super(firstName, lastName, userName, passWord, age, gender, ID);
    }

    public Pharmacist(){}
    public void viewStock(){
        String myQuery = "SELECT * FROM DrugStock";
        List<String> DrugName = new ArrayList<>();
        List<Integer> Amount = new ArrayList<>();
        List<Integer> Price = new ArrayList<>();
        List<LocalDate> ExpireDate = new ArrayList<>();
        try {
            ResultSet resultSet = Connector.execute(myQuery, Path.getPath());
            while (resultSet.next()){
                DrugName.add(resultSet.getString("DrugName"));
                Amount.add(resultSet.getInt("Amount"));
                Price.add(resultSet.getInt("Price"));
                ExpireDate.add(LocalDate.parse(resultSet.getString("ExpireDate")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean dispenseDrug(String DrugName, int needAmount) {
        int amount = 0;
        DrugName = DrugName.toUpperCase(Locale.ROOT);

        String myQuery = "SELECT Amount FROM DrugStock WHERE DrugName = " + "\"" + DrugName + "\"";
        try {
            ResultSet resultSet = Connector.execute(myQuery, Path.getPath());
            while(resultSet.next()){
                amount = resultSet.getInt("Amount");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(needAmount > amount){
            myQuery = "UPDATE DrugStock SET Amount = 0 WHERE DrugName = " + "\"" + DrugName + "\"";
            try {
                Connector.executeWithoutResults(myQuery, Path.getPath());
                return false;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else{
            myQuery = "UPDATE DrugStock SET Amount = " + (amount - needAmount) + " WHERE DrugName = " + "\"" + DrugName + "\"";
            try {
                Connector.executeWithoutResults(myQuery, Path.getPath());
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean checkAvailability(String drugName){
        Stock stock = new Stock();
        if(stock.getCountInStock(drugName) > 0){
            return true;
        }
        return false;

    }
}
