package com.example.eksheflyproject;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Main extends Application implements Initializable  {

    @FXML
    private Button btn_login;

    @FXML
    private AnchorPane pane_login;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private ComboBox<String> type;

    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        Admin admin = new Admin("Ahmed", "Sakr", "Admin", "Password", 20, "M","AD1009");
        String choice;
        String userName;
        //Doctor myDoctor = new Doctor();
        //myDoctor.requestTest("sasa");
        /*Stock myStock = new Stock();
        myStock.AddDrug(new Drug("Aspirin", LocalDate.now(), 10), 1000);
        myStock.AddDrug(new Drug("Cocaine", LocalDate.now(), 10), 1000);
        myStock.AddDrug(new Drug("Methamphetamine", LocalDate.now(), 10), 1000);
        myStock.AddDrug(new Drug("Morphine", LocalDate.now(), 10), 1000);
        myStock.summary();
        System.out.println(myStock.getCountInStock("Cocaine"));
        //Pharmacist hamada = new Pharmacist("admon", "123sardimardi", 69, 'N', "bossBitch69");
        //hamada.dispenseDrug("aspirin", 3000);
        //myStock.remove("aspirin");*/

        /*do {
        System.out.println("Enter User type");
        choice = myScanner.nextLine();
        if(choice.toLowerCase(Locale.ROOT).equals("doc")){
            System.out.println("choose delete (del) or create (cr)");
            choice = myScanner.nextLine();
            if (choice.toLowerCase(Locale.ROOT).equals("del")){
                System.out.println("enter Doctor username");
                userName = myScanner.nextLine();
                myAdmin.deleteDoctorAccount(userName);
            }else if (choice.toLowerCase(Locale.ROOT).equals("cr")){
                myAdmin.createDoctorAccount();
            }else {
                System.out.println("invalid input");
            }
        }
        else if(choice.toLowerCase(Locale.ROOT).equals("pat")){
            System.out.println("choose delete (del) or create (cr)");
            choice = myScanner.nextLine();
            if (choice.toLowerCase(Locale.ROOT).equals("del")){
                System.out.println("enter patient username");
                userName = myScanner.nextLine();
                myAdmin.deletePatientAccount(userName);
            }else if (choice.toLowerCase(Locale.ROOT).equals("cr")){
                myAdmin.createPatientAccount();
            }else {
                System.out.println("invalid input");
            }
        }
        else if(choice.toLowerCase(Locale.ROOT).equals("phar")){
            System.out.println("choose delete (del) or create (cr)");
            choice = myScanner.nextLine();
            if (choice.toLowerCase(Locale.ROOT).equals("del")){
                System.out.println("enter Pharmacist username");
                userName = myScanner.nextLine();
                myAdmin.deletePharmacistAccount(userName);
            }else if (choice.toLowerCase(Locale.ROOT).equals("cr")){
                myAdmin.createPharmacistAccount();
            }else {
                System.out.println("invalid input");
            }
        }
        else if(choice.toUpperCase(Locale.ROOT).charAt(0) == 'Q'){
            System.out.println("Exit");
        }
        else{
            System.out.println("Invalid Choice");
        }

        }while (choice.toUpperCase(Locale.ROOT).charAt(0) != 'Q');*/
        //LocalDateTime dt = LocalDateTime.parse("2022-05-03T13:13");
        //System.out.println(dt.getDayOfWeek());
//        myAdmin.viewSchedule("DR1176");
        //Doctor doc = new Doctor("DR1176");
        //doc.viewHistory("PT5394");
        launch();
    }



    @FXML
    private void login(ActionEvent event) throws Exception {
        if(type.getValue() == null || username.getText() == null || password.getText() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Fields");
            alert.setHeaderText("Fields are empty or no user type has been specified");
            alert.setContentText("Please Choose a user type and fill in the fields.");
            alert.show();
        }
        else {
            String myQuery = "SELECT Username, Password FROM " + "\"" + type.getValue() +
                    "\" WHERE Username = " + "\"" + username.getText() +
                    "\"AND Password = " + "\"" + password.getText() + "\"";

            try {
                ResultSet resultSet = Connector.execute(myQuery, Path.getPath());
                if (resultSet.next()) {
                    resultSet.next();
                    if (type.getValue().equals("Doctor")) {
//                    btn_login.getScene().getWindow().hide();
                        try {
                            Stage stage = new Stage();
                            Parent root = FXMLLoader.load(getClass().getResource("Doctor.fxml"));
                            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            Scene scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();

                        } catch (Exception exception) {
                            exception.printStackTrace();
                            exception.getCause();
                        }
                    } else if (type.getValue().equals("Patient")) {
                        try {
                            Stage stage = new Stage();
                            Parent root = FXMLLoader.load(getClass().getResource("Patient.fxml"));
                            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            Scene scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();

                        } catch (Exception exception) {
                            exception.printStackTrace();
                            exception.getCause();
                        }

                    } else if (type.getValue().equals("Admin")) {
                        try {
                            Stage stage = new Stage();
                            Parent root = FXMLLoader.load(getClass().getResource("Admin.fxml"));
                            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            Scene scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();

                        } catch (Exception exception) {
                            exception.printStackTrace();
                            exception.getCause();
                        }

                    } else if (type.getValue().equals("Pharmacist")) {
                        try {
                            Stage stage = new Stage();
                            Parent root = FXMLLoader.load(getClass().getResource("Pharmacist.fxml"));
                            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            Scene scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();

                        } catch (Exception exception) {
                            exception.printStackTrace();
                            exception.getCause();
                        }

                    }

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Incorrect Login Information");
                    alert.setHeaderText("Incorrect Username or Password");
                    alert.setContentText("Pleaser Try again.");
                    alert.show();

                }

            } catch (SQLException e) {
                e.printStackTrace();
                e.getCause();
            }
        }

    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();
            Main controller = loader.getController();
//            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
//            try{
//                Image icon=new Image("com/example/eksheflyproject/icon.png");
//                stage.getIcons().add(icon);
//            }catch (Exception e){
//                e.printStackTrace();
//                System.out.println(e.getCause());
//            }

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            stage.setTitle("Ekshefly");

//            password.setOnKeyPressed(new EventHandler<KeyEvent>() {
//                @Override
//                public void handle(KeyEvent event) {
//                    if (event.getCode().equals(KeyCode.ENTER)){
//                        try {
//                           btn_login.fire();
//                        }catch (Exception e){
//                            e.printStackTrace();
//                            System.out.println(e.getCause());
//                        }
//                    }
//                }
//            });

//            scene.setOnKeyPressed(event->{
//                if (event.getCode()== KeyCode.ENTER){
////                    System.out.println("You Pressed Enter");
//                    try {
//                        controller.login2();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            });

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getCause());
        }
    }
    public void login2(KeyEvent event){
        String myQuery = "SELECT Username, Password FROM " + "\"" + type.getValue() +
                "\" WHERE Username = " + "\"" + username.getText() +
                "\"AND Password = " + "\"" + password.getText() + "\"";
        System.out.println(myQuery);

        try {
            ResultSet resultSet = Connector.execute(myQuery, Path.getPath());
            if (resultSet.next()) {
                if (type.getValue() .equals("Doctor") ) {
//                    btn_login.getScene().getWindow().hide();
                    try {
                        Stage stage = new Stage();
                        Parent root = FXMLLoader.load(getClass().getResource("Doctor.fxml"));
                        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();

                    } catch (Exception exception) {
                        exception.printStackTrace();
                        exception.getCause();
                    }
                }
                else if(type.getValue() .equals("Patient") ){
                    try {
                        Stage stage = new Stage();
                        Parent root = FXMLLoader.load(getClass().getResource("Patient.fxml"));
                        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();

                    } catch (Exception exception) {
                        exception.printStackTrace();
                        exception.getCause();
                    }

                } else if(type.getValue() .equals("Admin")){
                    try {
                        Stage stage = new Stage();
                        Parent root = FXMLLoader.load(getClass().getResource("Admin.fxml"));
                        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();

                    } catch (Exception exception) {
                        exception.printStackTrace();
                        exception.getCause();
                    }

                } else if(type.getValue() .equals("Pharmacist") ){
                    try {
                        Stage stage = new Stage();
                        Parent root = FXMLLoader.load(getClass().getResource("Pharmacist.fxml"));
                        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();

                    } catch (Exception exception) {
                        exception.printStackTrace();
                        exception.getCause();
                    }

                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Incorrect Login Information");
                alert.setHeaderText("Incorrect Username or Password");
                alert.setContentText("Pleaser Try again.");
                alert.show();

            }
        } catch (SQLException e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    String[] items = {"Admin", "Doctor", "Patient", "Pharmacist"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        type.getItems().addAll(items);
        type.setOnAction(this::getItems);
    }

    public void getItems(ActionEvent e) {
        String items = type.getValue();
    }

    @FXML
    void keypressed(KeyEvent event){

            if (event.getCode().equals(KeyCode.ENTER)){
                        try {
                           btn_login.fire();
                        }catch (Exception e){
                            e.printStackTrace();
                            System.out.println(e.getCause());
                        }
                    }

    }
}
