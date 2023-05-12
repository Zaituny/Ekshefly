package com.example.eksheflyproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.time.LocalDate;

public class PharController {

    @FXML
    private AnchorPane Main_pane;
    @FXML
    private Button dispense_btn;

    @FXML
    private TextField drgAmount;

    @FXML
    private TextField drgName;

    @FXML
    private Button disp_btn;

    @FXML
    private AnchorPane disp_pane;

    @FXML
    private Button dispenseDrug_btn;

    @FXML
    private Button home_btn;

    @FXML
    private ImageView imageview;

    @FXML
    private Button lg_btn;

    @FXML
    private Button stk_btn;

    @FXML
    private AnchorPane stk_pane;
    Pharmacist pharmacist =new Pharmacist();
    Stock stock=new Stock();
    @FXML
    private TextField stockAmount;

    @FXML
    private TextField stockName;

    @FXML
    private Button addBtn;
    @FXML
    private TextField stockPrice;
    @FXML
    DatePicker stockDate;
    @FXML
    void add_stock(ActionEvent event) {
        if (stockName.getText().equals("")|| stockAmount.getText().equals("")||stockPrice.getText().equals("")
        ||stockDate.getValue()==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Fields");
            alert.setHeaderText("Fields are empty");
            alert.setContentText("Please fill in the fields.");
            alert.show();
        }else{
                String name=stockName.getText();
                int amount=Integer.parseInt(stockAmount.getText());
                float price=Float.parseFloat(stockPrice.getText());
            LocalDate date=stockDate.getValue();
                Drug drug=new Drug(name,date,price);
                stock.AddDrug(drug,amount);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Add");
            alert.setHeaderText("Drug Add to Stock");
            alert.setContentText("Press ok if you want to Add this drug");
            if (alert.showAndWait().get() == ButtonType.OK) {
                stk_pane.setVisible(false);
                Main_pane.setVisible(true);
            }

        }
    }

    @FXML
    public void DispenseDrug(ActionEvent event) {
        try {
            if(drgAmount.getText().equals("")||drgName.getText().equals("")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Empty Fields");
                alert.setHeaderText("Fields are empty");
                alert.setContentText("Please fill in the fields.");
                alert.show();
            }else {
                String name = drgName.getText();
                int amount = Integer.parseInt(drgAmount.getText());
                if (pharmacist.dispenseDrug(name, amount)) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Dispense");
                    alert.setHeaderText("Drug Dispense");
                    alert.setContentText("Press ok if you want to dispense this drug");
                    if (alert.showAndWait().get() == ButtonType.OK) {
                        disp_pane.setVisible(false);
                        Main_pane.setVisible(true);
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Dispense");
                    alert.setHeaderText("Not sufficient amount for this Drug but will dispense all remaining stock!");
                    alert.setContentText("Press ok if you want to get all remaining stock");
                    if (alert.showAndWait().get() == ButtonType.OK) {
                        disp_pane.setVisible(false);
                        Main_pane.setVisible(true);
                    }
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void logout(ActionEvent event) {
        try {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Warning");
            alert.setHeaderText("Logging Out");
            alert.setContentText("Are you sure you want to Log Out?");
            if(alert.showAndWait().get()== ButtonType.OK){
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
                stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }


        } catch (Exception exception) {
            exception.printStackTrace();
            exception.getCause();
        }
    }

    public void stkPaneShow() {

        stk_pane.setVisible(true);
        Main_pane.setVisible(false);
    }

    public void stkReturnToHome() {

        stk_pane.setVisible(false);
        Main_pane.setVisible(true);
    }

    public void dispPaneShow() {

        disp_pane.setVisible(true);
        Main_pane.setVisible(false);
    }

    public void dispReturnToHome() {

        disp_pane.setVisible(false);
        Main_pane.setVisible(true);
    }
}
