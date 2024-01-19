/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import classes.User;
import service.ServiceUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ANIS
 */
public class AddUserController implements Initializable {
@FXML
    private TextField tf_ajout_ident;
    @FXML
    private TextField tf_ajout_email;
    @FXML
    private PasswordField tf_ajout_mdp;
    @FXML
    private PasswordField tf_ajout_cfrmmdp;
    @FXML
    private TextField tf_ajout_age;
   // @FXML
    //private ComboBox<String> cb_ajout_rl;
    @FXML
    private ComboBox<String> cb_ajout_sx;
    
    private String ImagePath;
     @FXML
    private Button btn_ajout_ajout;
      @FXML
    private Button btn_ajout_retour;

        @FXML
        private Label imageLabel;
         @FXML
        private ImageView ImagePreviw;
         @FXML
        private Button Add_image_button;
         @FXML
        private MediaView MediaView;
      

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        // TODO
         //  ObservableList<String> list = FXCollections.observableArrayList("CLIENT","MANAGER");
           //cb_ajout_rl.setItems(list);
           //cb_ajout_rl.setValue("CLIENT");
           
           ObservableList<String> listS = FXCollections.observableArrayList("male","female");
           cb_ajout_sx.setItems(listS);
           cb_ajout_sx.setValue("male");
           
        ImagePath = "C:\\Users\\Med Aziz\\Desktop\\pidev-hooligans-master\\src\\view/Profile.jpg";
        ImagePreviw.setImage(new Image(new File(ImagePath).toURI().toString()));

            }
    @FXML
    private void ajout(ActionEvent event) throws IOException {
        User u = new User();
        ServiceUser su = new ServiceUser();
        //String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.(com|tn)$";
        String mdp1 = tf_ajout_mdp.getText();
        String mdp2 = tf_ajout_cfrmmdp.getText();
        String age1 = tf_ajout_age.getText();
      //  String role1 = cb_ajout_rl.getSelectionModel().getSelectedItem();
        String sexe = cb_ajout_sx.getSelectionModel().getSelectedItem();

            
        if (tf_ajout_ident.getText().isEmpty() || tf_ajout_email.getText().isEmpty() || mdp1.isEmpty() || 
            mdp2.isEmpty()  || 
            age1.isEmpty()
         
            ) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Champs manquants");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs !");
        alert.showAndWait();
    }else if (!tf_ajout_email.getText().matches(emailRegex)) {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Format email incorrect");
    alert.setHeaderText(null);
    alert.setContentText("Veuillez saisir un email valide !");
    alert.showAndWait();
    //return;
}else if (su.checkEmailExists(tf_ajout_email.getText())) {
    
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Email existe déjà");
    alert.setHeaderText(null);
    alert.setContentText("Veuillez saisir un email différent !");
    alert.showAndWait();
   // return;
}else if (mdp1.length() < 8) {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Attention");
    alert.setHeaderText(null);
    alert.setContentText("Le mot de passe doit avoir au moins 8 caractères.");
    alert.showAndWait();
    }   else if (!mdp1.matches(".*[A-Z].*") || !mdp1.matches(".*\\d.*")) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText(null);
        alert.setContentText("Le mot de passe doit contenir au moins une lettre majuscule et un chiffre.");
        alert.showAndWait();
      //  return;
    } else if(!mdp1.matches(mdp2)){
    
     Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText(null);
        alert.setContentText("vous devez revoir votre mot de passe");
        alert.showAndWait();
    }else if(!age1.matches(".*\\d.*")){
        // Afficher un message d'alerte
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Format d'age invalide");
        alert.setHeaderText(null);
        alert.setContentText("Format d'age invalide !");
        alert.showAndWait();
      //  return;
    }else if (Integer.parseInt(age1) < 1 || Integer.parseInt(age1) > 150) {
    

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Âge hors de la plage valide");
        alert.setHeaderText(null);
        alert.setContentText("L'âge doit être compris entre 1 et 150.");
        alert.showAndWait();
    } else {

            u.setUsername(tf_ajout_ident.getText());
            u.setMail(tf_ajout_email.getText());
            u.setMdp(mdp1);
            u.setAge(Integer.parseInt(age1));
            u.setRole("[\"ROLE_CLIENT\"]");
            u.setSexe(sexe);
            u.setImage(ImagePath);

            su.ajouter(u);
             Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setAlertType(Alert.AlertType.INFORMATION);
           alert.setContentText("Utilisateur ajouté");
            alert.show();
     try {
            Parent page1 = FXMLLoader.load(getClass().getResource("GestionAdmin.fxml"));
            
            Scene scene = new Scene(page1);
            
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
            stage.setScene(scene);
            
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(InscriptionUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
    }
    @FXML
    private void Retour(ActionEvent event) {
    
    try {
    Parent root = FXMLLoader.load(getClass().getResource("GestionAdmin.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();} catch (IOException ex) {
            Logger.getLogger(InscriptionUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     
    public void copyFileToDirectory(File sourceFile, File destDir) throws IOException {
    Path sourcePath = sourceFile.toPath();
    Path destPath = destDir.toPath().resolve(sourceFile.getName());
    Files.copy(sourcePath, destPath, StandardCopyOption.REPLACE_EXISTING);
    }
    
    @FXML
    private void add_image_action(ActionEvent event) throws IOException {
        FileChooser fc = new FileChooser();
        File defaultDir = new File("C:\\Users\\Med Aziz\\Documents");
        fc.setInitialDirectory(defaultDir);
        File SelectedFile = fc.showOpenDialog(null);

        if (SelectedFile != null) {
            copyFileToDirectory(SelectedFile, defaultDir);

            ImagePath = defaultDir + "/" + SelectedFile.getName();
            imageLabel.setText(ImagePath);
            ImagePreviw.setImage(new Image(new File(ImagePath).toURI().toString()));
        } else {

            ImagePath = "/image/profile.jpg";
            imageLabel.setText(ImagePath);
            ImagePreviw.setImage(new Image(new File(ImagePath).toURI().toString()));

        }
    }
}
