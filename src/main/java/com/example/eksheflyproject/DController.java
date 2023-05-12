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
import java.util.ResourceBundle;

public class DController implements Initializable{

    @FXML
    private AnchorPane Main_pane;

    @FXML
    private ComboBox<String> Test_type;

    @FXML
    private Button dig_btn;

    @FXML
    private AnchorPane dig_pane;

    @FXML
    private Button drgs_btn;

    @FXML
    private AnchorPane drgs_pane;

    @FXML
    private Button his_btn;

    @FXML
    private AnchorPane his_pane;

    @FXML
    private Button home_btn;

    @FXML
    private ImageView imageview;

    @FXML
    private Button lg_btn;

    @FXML
    private Button medHistory_btn;

    @FXML
    private Button prscDrug_btn;

    @FXML
    private Button req_btn;

    @FXML
    private Button rqstTest_btn;

    @FXML
    private AnchorPane rqst_pane;

    @FXML
    private Button sch_btn;

    @FXML
    private AnchorPane sch_pane;

    @FXML
    private Button updateHis_btn;

    @FXML
    private AnchorPane actualHis_pane;
    @FXML
            Button requestTestBtn;
    @FXML
            TextField drgName;
    @FXML
            TextField drgID;


    String[] testType = {"Complete Blood Count", "Prothrombin Time", "Basic Metabolic Panel",
            "Thyroid Stimulating Hormone", "Hemoglobin A1C", "Urinalysis"};
    Doctor doctor=new Doctor();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Test_type.getItems().addAll(testType);
    }

    public void do_request(ActionEvent event){

        if (reqID.getText()==null||Test_type.getValue()==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Fields");
            alert.setHeaderText("Fields are empty or no type has been specified");
            alert.setContentText("Please Choose a type and fill in the fields.");
            alert.show();
        }else{
            String ID=reqID.getText();
            String test=Test_type.getValue();
            doctor.requestTest(ID,test);
            rqst_pane.setVisible(false);
            Main_pane.setVisible(true);
        }
    }
    public void presDrug(ActionEvent event){
        try {
            if (drgID.getText().equals("")||drgName.getText().equals("")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Empty Fields");
                alert.setHeaderText("Fields are empty or no type has been specified");
                alert.setContentText("Please Choose a type and fill in the fields.");
                alert.show();
            }else{
                String name=drgName.getText();
                String id=drgID.getText();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Update");
                alert.setHeaderText("Update patient history");
                alert.setContentText("Press ok if you want to update patient history");
                if(alert.showAndWait().get()== ButtonType.OK){
                    doctor.prescribeDrugs(name,id);
                    drgs_pane.setVisible(false);
                    Main_pane.setVisible(true);
                }
            }
        }catch (Exception e){
            System.out.println(e.getCause());
        }

    }
    @FXML
    TextField hisID;
    @FXML
    TextField hisDig;
    @FXML
    public void updateHistory(ActionEvent event) {
        if (hisDig.getText().equals("")||hisID.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Fields");
            alert.setHeaderText("Fields are empty or no type has been specified");
            alert.setContentText("Please Choose a type and fill in the fields.");
            alert.show();
        }else{
        try {
            String Diagnosis=hisDig.getText();
            String id=hisID.getText();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Update");
            alert.setHeaderText("Update patient history");
            alert.setContentText("Press ok if you want to update patient history");
            if(alert.showAndWait().get()== ButtonType.OK){
                doctor.diagnosis(Diagnosis,id);

                dig_pane.setVisible(false);
                Main_pane.setVisible(true);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        }
    }
    @FXML
    TextField reqID;

    @FXML
    void logout(ActionEvent event) {
        try {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Warning");
            alert.setHeaderText("Logging Out");
            alert.setContentText("Are you sure you want to Log Out?");
            if (alert.showAndWait().get() == ButtonType.OK) {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }


        } catch (Exception exception) {
            exception.printStackTrace();
            exception.getCause();
        }
    }

    public void rqstPaneShow() {

        rqst_pane.setVisible(true);
        Main_pane.setVisible(false);
    }

    public void rqstReturnToHome() {

        rqst_pane.setVisible(false);
        Main_pane.setVisible(true);
    }

    public void digPaneShow() {

        dig_pane.setVisible(true);
        Main_pane.setVisible(false);

    }

    public void digReturnToHome() {

        Main_pane.setVisible(true);
        dig_pane.setVisible(false);
    }

    public void drgsPaneShow() {

        drgs_pane.setVisible(true);
        Main_pane.setVisible(false);
    }

    public void drgsReturnToHome() {

        Main_pane.setVisible(true);
        drgs_pane.setVisible(false);
    }

    public void hisPaneShow() {

        his_pane.setVisible(true);
        Main_pane.setVisible(false);
    }

    public void hisReturnToHome() {

        Main_pane.setVisible(true);
        his_pane.setVisible(false);
    }

    public void schPaneShow() {

        sch_pane.setVisible(true);
        Main_pane.setVisible(false);
    }

    public void schReturnToHome() {
        Main_pane.setVisible(true);
        sch_pane.setVisible(false);
    }

    public void actualHisPaneShow() {

        actualHis_pane.setVisible(true);
        his_pane.setVisible(false);
    }

    public void actualHisPaneReturnToHome() {

        actualHis_pane.setVisible(false);
        Main_pane.setVisible(true);
    }

}
