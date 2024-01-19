/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

//import view.user.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import service.ServiceUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ANIS
 */
public class ResetPasswordController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
     @FXML
    private TextField tfPassword;
    @FXML
    private TextField tfConfirm;
    @FXML
    private Button btnReset;
    @FXML
    private Button btnAnnuler;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     @FXML
    private void btnResetAction(ActionEvent event) {
        Alert A = new Alert(Alert.AlertType.INFORMATION);
        if (!tfPassword.getText().equals("") && tfPassword.getText().equals(tfConfirm.getText())) {
            ServiceUser su = new ServiceUser();
            su.ResetPassword(MdpOubUserController.EmailReset, tfPassword.getText());
            A.setContentText("Mot de passe modifié avec succes ! ");
            A.show();
            try {

                Parent page1 = FXMLLoader.load(getClass().getResource("ConnexionUser.fxml"));

                Scene scene = new Scene(page1);

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                stage.setScene(scene);

                stage.show();

            } catch (IOException ex) {

                System.out.println(ex.getMessage());

            }
        } else {
            A.setContentText("veuillez saisir un mot de passe conforme !");
            A.show();
        }

    }

    @FXML
    private void btnAnnulerResetAction(ActionEvent event) {
         try {

                Parent page1 = FXMLLoader.load(getClass().getResource("ResetPassword.fxml"));

                Scene scene = new Scene(page1);

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                stage.setScene(scene);

                stage.show();

            } catch (IOException ex) {

                System.out.println(ex.getMessage());

            }
    }

}
