/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

//import view.user.*;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import service.EmailSender;
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
public class MdpOubUserController implements Initializable {
     @FXML
    private TextField tf_mdpoub_email;
    @FXML
    private Button btn_mdpoub_env;
    @FXML
    private Button btn_mdpoub_cnx;
    public static int code;
    public static String EmailReset ; 

    /**
     * Initializes the controller class.
     */
    
    ServiceUser su = new ServiceUser();
     private int generateVerificationCode() {
        
        Random random = new Random();
        return 100000 + random.nextInt(900000);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    @FXML
    private void retour(ActionEvent event) throws IOException {
    
    
    Parent root = FXMLLoader.load(getClass().getResource("connexionUser.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
           
    }
 
    
     @FXML
    private void btnCodeAction(ActionEvent event) {
        code = generateVerificationCode();
        Alert A = new Alert(Alert.AlertType.WARNING);
        ServiceUser su = new ServiceUser();

        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
            boolean verifMail = tf_mdpoub_email.getText().matches(emailRegex);

        if (!tf_mdpoub_email.getText().equals("") && verifMail) {
            if (su.ChercherMail(tf_mdpoub_email.getText()) == 1) {
                EmailReset = tf_mdpoub_email.getText();
                EmailSender.sendEmail("anisfetoui2000@gmail.com", "iucw kjxs otpi fsas", tf_mdpoub_email.getText(), "Verification code", "Votre code est : " + code);

                try {

                    Parent page1 = FXMLLoader.load(getClass().getResource("verifCode.fxml"));

                    Scene scene = new Scene(page1);

                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    stage.setScene(scene);

                    stage.show();

                } catch (IOException ex) {

                    System.out.println(ex.getMessage());

                }

            } else {
                A.setContentText("pas de compte lié avec cette adresse ! ");
                A.show();
            }
        } else {
            A.setContentText("Veuillez saisir une adresse mail valide ! ");
            A.show();
        }
  
}
   
}
    
