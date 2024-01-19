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
import java.util.logging.Level;
import java.util.logging.Logger;
import classes.SessionManager;
import classes.User;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ANIS
 */
public class ConnexionUserController implements Initializable {
    public static int id_modif ;  
    @FXML
    private Button btn_connexion_inscri;
     @FXML
    private Button btn_connexion_mdpoub;
      @FXML
    private Button btn_connexion_connecter;
       @FXML
    private TextField tf_connexion_email;
        @FXML
    private PasswordField tf_connexion_mdp;
     
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }    
    
  @FXML
private void cnx(ActionEvent event) {
    String page = "";
    String email = tf_connexion_email.getText();
    String password = tf_connexion_mdp.getText();
    int id = -1;
    String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    if (email.isEmpty() || password.isEmpty()) {
        // Afficher un message d'alerte
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Champs manquants");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs !");
        alert.showAndWait();
    } else if (!email.matches(emailRegex)) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Format email incorrect");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez saisir un email valide !");
        alert.showAndWait();
    } else {
        ServiceUser su = new ServiceUser();
        Alert alert = new Alert(Alert.AlertType.NONE);
        SessionManager sessionManager = SessionManager.getInstance();
        id = su.authentification(email, password);

        if (id == -1) {
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setContentText("Email ou mot de passe erroné !");
            alert.show();
        } else {
            // Check if the password is correct using BCrypt.checkpw
                // Password is correct
                sessionManager.setCurrentUser(su.readById(id));
                id_modif = id;
                String role = su.readById(id).getRole();
                switch (role) {
                    case "[\"ROLE_ADMIN\"]":
                        alert.setAlertType(Alert.AlertType.INFORMATION);
                        alert.setContentText("Admin connecté");
                        alert.show();
                        page = "AllAdmin.fxml";
                        break;

                    case "[\"ROLE_CLIENT\"]":
                        alert.setAlertType(Alert.AlertType.INFORMATION);
                        alert.setContentText("Client connecté");
                        alert.show();
                        page = "HomeUser.fxml";
                        break;
                }

                try {
                    Parent page1 = FXMLLoader.load(getClass().getResource(page));
                    Scene scene = new Scene(page1);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } 
        }
    
}

     @FXML
    private void oublier(ActionEvent event) {
    
    try {
    Parent root = FXMLLoader.load(getClass().getResource("mdpOubUser.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();} catch (IOException ex) {
            Logger.getLogger(InscriptionUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void inscri(ActionEvent event) {
    
    try {
    Parent root = FXMLLoader.load(getClass().getResource("inscriptionUser.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();} catch (IOException ex) {
            Logger.getLogger(InscriptionUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
 
}