package com.example.eksheflyproject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

//all methods need to be implemented

public class Stock {

    public Stock() {}


    public void AddDrug(Drug drug, int amount){
        List<String> DrugsFound = new ArrayList<String>();
        int newAmount = 0;
        String myQuery = "SELECT * FROM DrugStock WHERE DrugName = " + "\"" + drug.getName() + "\"";
        try {
            ResultSet resultSet = Connector.execute(myQuery, Path.getPath());
            while(resultSet.next()){
                newAmount = resultSet.getInt("Amount");
                DrugsFound.add(resultSet.getString("DrugName"));
            }
            if(DrugsFound.contains(drug.getName())){
                newAmount += amount;
                myQuery = "UPDATE DrugStock SET Amount = " + newAmount + " WHERE DrugName = " + "\"" + drug.getName() + "\"";
                Connector.executeWithoutResults(myQuery, Path.getPath());
            }
            else{
                myQuery = "INSERT INTO DrugStock VALUES(" + "\"" + drug.getName() + "\"" + "," +
                        amount + "," +
                        drug.getPrice() + "," +
                        "\"" + drug.getExpireDate() + "\"" + ")";
                try {
                    Connector.executeWithoutResults(myQuery, Path.getPath());
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void remove(String DrugName){
        String myQuery = "DELETE FROM DrugStock WHERE DrugName = " + "\"" + DrugName.toUpperCase(Locale.ROOT) + "\"";
        try {
            Connector.executeWithoutResults(myQuery, Path.getPath());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<String> summary(){
        String myQuery = "SELECT COUNT(DrugName) as count FROM DrugStock";
        int UniqueDrugCount = 0;
        int TotalDrugCount = 0;
        try {
            ResultSet resultSet = Connector.execute(myQuery, Path.getPath());
            while(resultSet.next()){
                UniqueDrugCount = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        myQuery = "SELECT SUM(Amount) as count FROM DrugStock";
        try {
            ResultSet resultSet = Connector.execute(myQuery, Path.getPath());
            while(resultSet.next()){
                TotalDrugCount = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<String> myList  = new ArrayList<>();
        myList.add(UniqueDrugCount + "");
        myList.add(TotalDrugCount + "");
        return myList;
    }
    public int getCountInStock(String DrugName){
        DrugName = DrugName.toUpperCase(Locale.ROOT);
        String myQuery = "SELECT Amount from DrugStock WHERE DrugName = " + "\"" + DrugName + "\"";
        int amount = 0;
        try {
            ResultSet resultSet = Connector.execute(myQuery, Path.getPath());
            while (resultSet.next()){
                amount = resultSet.getInt("Amount");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return amount;
    }

}