package com.example.eksheflyproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PController {

    @FXML
    private AnchorPane Main_pane;

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

    public void hisPaneShow() {

        his_pane.setVisible(true);
        Main_pane.setVisible(false);
    }

    public void returnToHome() {

        his_pane.setVisible(false);
        Main_pane.setVisible(true);
    }


}
