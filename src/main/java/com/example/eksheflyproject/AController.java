package com.example.eksheflyproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AController implements Initializable {
    @FXML
    Button createDoctorButton;
    @FXML
    TextField firstNameField;
    @FXML
    TextField lastNameField;
    @FXML
    TextField ageTF;
    @FXML
    TextField specialityTF;
    @FXML
    TextField phoneNum;
    @FXML
    TextField pass;
    @FXML
    TextField user;
    @FXML
    ComboBox<String> gender_box;
    @FXML
    AnchorPane  sched_pane;
    @FXML
    private ComboBox<String> friday_box;
    @FXML
    private ComboBox<String> monday_box;
    @FXML
    private ComboBox<String> saturday_box;
    @FXML
    private ComboBox<String> sunday_box;
    @FXML
    private ComboBox<String> thursday_box;
    @FXML
    private ComboBox<String> tuesday_box;
    @FXML
    private ComboBox<String> wednesday_box;

    @FXML
    private AnchorPane Main_pane;

    @FXML
    private Button app_btn;

    @FXML
    private AnchorPane app_pane;

    @FXML
    private AnchorPane chooseUserAcc_pane;

    @FXML
    private AnchorPane createDrAcc_pane;

    @FXML
    private AnchorPane createPatAcc_pane;

    @FXML
    private AnchorPane createPharmAcc_pane;

    @FXML
    private TextField pharLast;
    @FXML
    private TextField pharAge;

    @FXML
    private TextField pharFirst;
    @FXML
    private TextField pharPass;

    @FXML
    private TextField pharUser;

    @FXML
    private Button phar_btn;
    @FXML
    private ComboBox<String> pharGender;
    @FXML
    private TextField patAge;

    @FXML
    private TextField patBT;

    @FXML
    private TextField patFirst;

    @FXML
    private ComboBox<String> patGender;

    @FXML
    private TextField patLast;

    @FXML
    private TextField patPW;

    @FXML
    private Button pat_btn;

    @FXML
    private TextField patuser;
    @FXML
    private Button delete_btn;

    @FXML
    private Button drAcc_btn;

    @FXML
    private Button home_btn;

    @FXML
    private ImageView imageview;

    @FXML
    private Button lg_btn;

    @FXML
    private Button new_btn;

    @FXML
    private Button patAcc_btn;

    @FXML
    private Button pharAcc_btn;

    @FXML
    private AnchorPane removeAcc_pane;

    @FXML
    private Button rmv_btn;

    @FXML
    private AnchorPane rmv_pane;

    @FXML
    private Button sch_btn;

    @FXML
    private AnchorPane sch_pane;

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
    @FXML
    TextField userDelete;
    @FXML
    ComboBox<String> deleteType;
    @FXML
    TextField drgName;
    @FXML
    TextField drgID;
    @FXML
    Button prscDrg_btn;

    @FXML
    public void deleteAccount(ActionEvent event) {
        if (userDelete.getText() == null || deleteType.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Fields");
            alert.setHeaderText("Fields are empty or no type has been specified");
            alert.setContentText("Please Choose a type and fill in the fields.");
            alert.show();
        } else {
            try {
                String username = userDelete.getText();
                String type = deleteType.getValue();
                if (type.equals("Doctor")){
                    admin.deleteDoctorAccount(username);
                }else if (type.equals("Patient")){
                    admin.deletePatientAccount(username);
                }else{
                    admin.deletePharmacistAccount(username);
                }
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Warning");
                alert.setHeaderText("Delete Account");
                alert.setContentText("Are you sure you want to permanently delete this account?");
                if (alert.showAndWait().get() == ButtonType.OK) {
                    removeAcc_pane.setVisible(false);
                    Main_pane.setVisible(true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void newAccPaneShow() {

        chooseUserAcc_pane.setVisible(true);
        Main_pane.setVisible(false);
    }

    public void newAccReturnToHome() {

        chooseUserAcc_pane.setVisible(false);
        Main_pane.setVisible(true);
    }

    public void rmvPaneShow() {

        rmv_pane.setVisible(true);
        Main_pane.setVisible(false);
    }

    public void rmvReturnToHome() {

        rmv_pane.setVisible(false);
        Main_pane.setVisible(true);
    }

    public void appPaneShow() {

        app_pane.setVisible(true);
        Main_pane.setVisible(false);
    }

    public void appReturnToHome() {

        app_pane.setVisible(false);
        Main_pane.setVisible(true);
    }

    public void schPaneShow() {

        sch_pane.setVisible(true);
        Main_pane.setVisible(false);
    }

    public void schReturnToHome() {

        sch_pane.setVisible(false);
        Main_pane.setVisible(true);
    }

    public void createDrAccPaneShow() {

        createDrAcc_pane.setVisible(true);
        chooseUserAcc_pane.setVisible(false);
    }

    public void createDrAccReturnToHome() {

        createDrAcc_pane.setVisible(false);
        Main_pane.setVisible(true);
    }

    public void createPharmAccShow() {

        createPharmAcc_pane.setVisible(true);
        chooseUserAcc_pane.setVisible(false);
    }

    public void createPharmAccReturnToHome() {

        createPharmAcc_pane.setVisible(false);
        Main_pane.setVisible(true);
    }

    public void createPatAccShow() {

        createPatAcc_pane.setVisible(true);
        chooseUserAcc_pane.setVisible(false);
    }

    public void createPatAccReturnToHome() {

        createPatAcc_pane.setVisible(false);
        Main_pane.setVisible(true);
    }

    public void removeAccShow() {

        removeAcc_pane.setVisible(true);
        Main_pane.setVisible(false);
    }

    public void removeAccReturnToHome() {

        removeAcc_pane.setVisible(false);
        Main_pane.setVisible(true);
    }
    Admin admin=new Admin();

    ArrayList<String> list=new ArrayList<>();
    public void createDr(ActionEvent event){
        if (firstNameField.getText()==null||lastNameField.getText()==null||ageTF.getText()==null||
                specialityTF.getText()==null||phoneNum.getText()==null||pass.getText()==null||user.getText()==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Fields");
            alert.setHeaderText("Fields are empty");
            alert.setContentText("Please fill in the fields.");
            alert.show();
        }
        String firstName= firstNameField.getText();
        String lastName= lastNameField.getText();
        int age = Integer.parseInt(ageTF.getText());
        String speciality= specialityTF.getText();
        String phoneNumber= phoneNum.getText();
        String password= pass.getText();
        String username= user.getText();
        ArrayList<String> schedule=list;
        String gender=gender_box.getValue();
        admin.createDoctorAccount(firstName,lastName,username,password,age,gender,speciality,phoneNumber,
                list.toArray(new String[0]));
        createDrAcc_pane.setVisible(false);
        Main_pane.setVisible(true);
    }
    String [] genders={"Male","Female"};
    String [] shifts={"Morning","Afternoon","Evening","Midnight","OFF"};
    String [] types={"Doctor","Patient","Pharmacist"};
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gender_box.getItems().addAll(genders);
        saturday_box.getItems().addAll(shifts);
        sunday_box.getItems().addAll(shifts);
        monday_box.getItems().addAll(shifts);
        tuesday_box.getItems().addAll(shifts);
        wednesday_box.getItems().addAll(shifts);
        thursday_box.getItems().addAll(shifts);
        friday_box.getItems().addAll(shifts);
        pharGender.getItems().addAll(genders);
        patGender.getItems().addAll(genders);
        deleteType.getItems().addAll(types);
    }
    public void add_sched(ActionEvent event){
        createDrAcc_pane.setVisible(false);
        sched_pane.setVisible(true);

    }

    public void createPat(ActionEvent event){
        if (patFirst.getText()==null||patLast.getText()==null||patBT.getText()==null
        ||patAge.getText()==null||patPW.getText()==null||patuser.getText()==null
        ||patGender.getValue()==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Fields");
            alert.setHeaderText("Fields are empty or no gender has been specified");
            alert.setContentText("Please Choose a gender and fill in the fields.");
            alert.show();
        }else{
            String first=patFirst.getText();
            String last=patLast.getText();
            String BT =patBT.getText();
            int Age=Integer.parseInt(patAge.getText());
            String pw=patPW.getText();
            String user=patuser.getText();
            String gender=patGender.getValue();
            admin.createPatientAccount(first,last,user,pw,Age,gender,BT);
            createPatAcc_pane.setVisible(false);
            Main_pane.setVisible(true);
        }
    }

    public void createPhar(ActionEvent event){
        if (pharFirst.getText()==null||pharLast.getText()==null||pharAge.getText()==null||pharPass.getText()==null||
                pharUser.getText()==null||pharGender.getValue()==null||pharGender.getValue()==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Fields");
            alert.setHeaderText("Fields are empty or no gender has been specified");
            alert.setContentText("Please Choose a gender and fill in the fields.");
            alert.show();
        }else{
            String first=pharFirst.getText();
            String last=pharLast.getText();
            int age=Integer.parseInt(pharAge.getText());
            String pw=pharPass.getText();
            String username= pharUser.getText();
            String gender=pharGender.getValue();
            admin.createPharmacistAccount(first,last,username,pw,age,gender);
            createPharmAcc_pane.setVisible(false);
            Main_pane.setVisible(true);
        }

    }
    public void go_back(ActionEvent event){
        list.add(0,saturday_box.getValue());
        list.add(1,sunday_box.getValue());
        list.add(2,monday_box.getValue());
        list.add(3,tuesday_box.getValue());
        list.add(4,wednesday_box.getValue());
        list.add(5,thursday_box.getValue());
        list.add(6,friday_box.getValue());
        createDrAcc_pane.setVisible(true);
        sched_pane.setVisible(false);
        createDoctorButton.setDisable(false);
    }
}
