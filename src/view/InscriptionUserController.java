    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.github.sarxos.webcam.Webcam;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
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
import static view.MdpOubUserController.EmailReset;
import service.EmailSender;
import service.ServiceUser;
import javafx.application.Platform;
import javafx.collections.FXCollections;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.RandomStringUtils;
import org.mindrot.jbcrypt.BCrypt;

/**
 * FXML Controller class
 *
 * @author ANIS
 */
public class InscriptionUserController implements Initializable {

    @FXML
    private TextField tf_inscri_ident;
    @FXML
    private TextField tf_inscri_email;
    @FXML
    private PasswordField tf_inscri_mdp;
    @FXML
    private PasswordField tf_inscri_cfrmmdp;
    @FXML
    private TextField tf_inscri_age;
    @FXML
    private TextField tf_inscri_image;
  //  @FXML
   // private ComboBox<String> cb_inscri_rl;
    @FXML
    private ComboBox<String> cb_inscri_sx; 
    private String ImagePath;
     @FXML
    private Button btn_inscri_inscri;
      @FXML
    private Button btn_inscri_cnx;
        @FXML
        private Label imageLabel;
        @FXML
        private ImageView ImagePreviw;
        @FXML
        private Button Add_image_button;
        @FXML
        private MediaView MediaView;
        @FXML
        private Button prd_photo;
        private Webcam webcam = null;
        Thread webcamThread;
        @FXML
        private Button btnSnap;
        @FXML
        private Button btnCancel;
          @FXML
        private Label generatemail;
            @FXML
        private Label labelmail;
      
    /**
     * Initializes the controller class.
     */

      
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
         //  ObservableList<String> list = FXCollections.observableArrayList("CLIENT","MANAGER");
          // cb_inscri_rl.setItems(list);
           // cb_inscri_rl.setValue("CLIENT");
           
           ObservableList<String> listS = FXCollections.observableArrayList("female","male");
           cb_inscri_sx.setItems(listS);
           cb_inscri_sx.setValue("male");
           
        ImagePath = "src/view/Profile.jpg";
        ImagePreviw.setImage(new Image(new File(ImagePath).toURI().toString()));
          webcam = Webcam.getDefault();
    }        
    private String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }
    

        @FXML 
        private void insc(ActionEvent event) throws IOException {
        ServiceUser su = new ServiceUser();
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        String mdp1 = tf_inscri_mdp.getText();
        String mdp2 = tf_inscri_cfrmmdp.getText();
        String age1 = tf_inscri_age.getText();
      //  String role1 = cb_inscri_rl.getSelectionModel().getSelectedItem();
        String sexe = cb_inscri_sx.getSelectionModel().getSelectedItem();

            
        if (tf_inscri_ident.getText().isEmpty() || tf_inscri_email.getText().isEmpty() || mdp1.isEmpty() || 
            mdp2.isEmpty()  || 
            age1.isEmpty()
         
            ) {
      
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Champs manquants");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs !");
        alert.showAndWait();
    }else if (!tf_inscri_email.getText().matches(emailRegex)) {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Format email incorrect");
    alert.setHeaderText(null);
    alert.setContentText("Veuillez saisir un email valide !");
    alert.showAndWait();
   
}else if (su.checkEmailExists(tf_inscri_email.getText())) {
    
    //generateSuggestedEmail(tf_inscri_email.getText());
     String randomSuffix = RandomStringUtils.randomAlphanumeric(2);
     generatemail.setText(randomSuffix+tf_inscri_email.getText());
     labelmail.setVisible(true);
     generatemail.setVisible(true);
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Email existe déjà");
    alert.setHeaderText(null);
    alert.setContentText("Veuillez saisir un email différent !");
    alert.showAndWait();
    
 
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

    } else if(!mdp1.matches(mdp2)){
    
     Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText(null);
        alert.setContentText("vous devez revoir votre mot de passe");
        alert.showAndWait();
    }else if(!age1.matches(".*\\d.*")){
     
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
            User u = new User();
            String[] roles = {"ROLE_CLIENT"};
            u.setUsername(tf_inscri_ident.getText());
            u.setMail(tf_inscri_email.getText());
            String hashedPassword = hashPassword(mdp1);
            u.setMdp(hashedPassword);
            u.setAge(Integer.parseInt(age1));
            u.setRole(roles[0]);
            u.setSexe(sexe);
            u.setImage(ImagePath);

            su.ajouter(u);
             Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setAlertType(Alert.AlertType.INFORMATION);
           alert.setContentText("Inscription avec success");
            alert.show();
                EmailReset = u.getMail();
                EmailSender.sendEmail("anisfetoui2000@gmail.com", "iucw kjxs otpi fsas", tf_inscri_email.getText(), "Bienvenue dans notre communauté !", "Cher(e) " +u.getUsername()+",\n" +
                "\n" +
                "Bienvenue ! Nous sommes ravis de vous accueillir sur XperienceZone. Profitez au maximum de votre expérience parmi nous.\n" +
                "\n" +
                    "Cordialement,\n" +
                "[L'équipe de votre application]");
     try {
            Parent page1 = FXMLLoader.load(getClass().getResource("connexionUser.fxml"));
            
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
    Parent root = FXMLLoader.load(getClass().getResource("connexionUser.fxml"));
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
        File defaultDir = new File("C:\\Users\\ANIS\\Documents");
        fc.setInitialDirectory(defaultDir);
        File SelectedFile = fc.showOpenDialog(null);

        if (SelectedFile != null) {
            copyFileToDirectory(SelectedFile, defaultDir);

            ImagePath = defaultDir + "/" + SelectedFile.getName();
            imageLabel.setText(ImagePath);
            ImagePreviw.setImage(new Image(new File(ImagePath).toURI().toString()));
        } else {

            ImagePath = "Profile.JPG";
            imageLabel.setText(ImagePath);
            ImagePreviw.setImage(new Image(new File(ImagePath).toURI().toString()));
        }
    }
    
       @FXML
private void cancelCameraOpening(ActionEvent event) {
   
    if (webcam != null && webcam.isOpen()) {
        webcam.close(); 
        imageLabel.setText(ImagePath);
        ImagePreviw.setImage(new Image(new File(ImagePath).toURI().toString()));
    }
}
    
@FXML
private void TakePhotoAction(ActionEvent event) {
    if (tf_inscri_email.getText().isEmpty()) {
        Alert test = new Alert(Alert.AlertType.ERROR);
        test.setContentText("Remplir tout les champs pour prendre une photo ! ");
        test.show();
    } else {
        if (webcam != null && !webcam.isOpen()) {
            try {
                webcam.close();
                webcam.setViewSize(new Dimension(640, 480));
                webcam.open();
            } catch (Exception ex) {
                
                System.err.println("Failed to open the webcam: " + ex.getMessage());
                return; 
            }
        }
        
        webcamThread = new Thread(() -> {
            while (webcam != null && webcam.isOpen()) {
                try {
                    Image image2 = SwingFXUtils.toFXImage(webcam.getImage(), null);
                    Platform.runLater(() -> {
                        ImagePreviw.setImage(image2);
                    });
                    Thread.sleep(50); 
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            btnCancel.setVisible(false);
            btnSnap.setVisible(false);
        });
        webcamThread.setDaemon(true);
        webcamThread.start();
        btnSnap.setVisible(true);
        btnCancel.setVisible(true);
    }
// if (tf_inscri_email.getText().isEmpty()) {
//        Alert test = new Alert(Alert.AlertType.ERROR);
//        test.setContentText("Remplir tout les champs pour prendre une photo !");
//        test.show();
//    } else {
//        if (webcam != null && !webcam.isOpen()) {
//            try {
//                webcam.close();
//                webcam.setViewSize(new Dimension(640, 480));
//                webcam.open();
//            } catch (Exception ex) {
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setContentText("Failed to open the webcam: " + ex.getMessage());
//                alert.show();
//                return; // Exit the method to prevent further execution
//            }
//        }
//
//        webcamThread = new Thread(() -> {
//            while (webcam != null && webcam.isOpen()) {
//                try {
//                    Image image2 = SwingFXUtils.toFXImage(webcam.getImage(), null);
//                    Platform.runLater(() -> {
//                        ImagePreviw.setImage(image2);
//                    });
//                    Thread.sleep(50);
//                } catch (InterruptedException ex) {
//                    ex.printStackTrace();
//                }
//            }
//            btnCancel.setVisible(false);
//            btnSnap.setVisible(false);
//        });
//        webcamThread.setDaemon(true);
//        webcamThread.start();
//        btnSnap.setVisible(true);
//        btnCancel.setVisible(true);
//    }
}

 @FXML
    private void btnSnapAction(ActionEvent event) {
        if (webcam.isOpen()) {
            BufferedImage image = webcam.getImage();
            ImagePreviw.setImage(SwingFXUtils.toFXImage(image, null));
            webcam.close();
            saveImage(image);
            btnSnap.setVisible(false);
        }

    }
    
     private void saveImage(BufferedImage image) {
        try {
            ImagePath = "src/view/" + tf_inscri_email.getText() + ".png";
            File outputfile = new File(ImagePath);
            imageLabel.setText(ImagePath);
            ImageIO.write(image, "png", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
