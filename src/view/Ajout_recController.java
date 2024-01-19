/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mail.MailSender;
import classes.Evenement;
import classes.Produit;
import classes.Reclamation;
import classes.User;
import classes.activites;
import service.ServiceReclamation;
//import  view.reclamation.CustomListCell;
import com.mewebstudio.captcha.Captcha;
import com.mewebstudio.captcha.GeneratedCaptcha;
import java.awt.image.BufferedImage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mail.Spellchecker;
import service.ServiceUser;

/**
 * FXML Controller class
 *
 * @author LENOVO GAMING
 */
public class Ajout_recController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    String expectedCaptchaAnswer ;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            corr.setVisible(false);

        // Ajouter un écouteur de changement de texte au TextArea
        detR.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // Si le texte dans le TextArea change, vérifiez s'il est vide ou non
                if (newValue != null && !newValue.trim().isEmpty()) {
                    // Si le texte n'est pas vide, rendez le Hyperlink visible
                    corr.setVisible(true);
                } else {
                    // Si le texte est vide, rendez le Hyperlink invisible
                    corr.setVisible(false);
                }
            }
        });
        captcha = new Captcha();
    GeneratedCaptcha generatedcaptcha = captcha.generate();
     expectedCaptchaAnswer = generatedcaptcha.getCode();
    BufferedImage bufferedImage = generatedcaptcha.getImage();
    Image captchaImage = SwingFXUtils.toFXImage(bufferedImage, null);
    captchaImageView.setImage(captchaImage);
    typeR.getItems().removeAll(typeR.getItems());
    typeR.getItems().addAll("Réclamation liés aux produits", "Réclamation liés aux évènements", "Réclamation liés aux activités");
       typeR.setOnAction(event -> {
        String selectedType = typeR.getValue();
        
        if (selectedType.equals("Réclamation liés aux produits")) {
            listEVE.setDisable(true);
            listACT.setDisable(true);
            listPROD.setDisable(false);
        } else if (selectedType.equals("Réclamation liés aux évènements")) {
            listEVE.setDisable(false);
            listACT.setDisable(true);
            listPROD.setDisable(true);
        } else if (selectedType.equals("Réclamation liés aux activités")) {
            listEVE.setDisable(true);
            listACT.setDisable(false);
            listPROD.setDisable(true);
        }
    });
    listEVE.setCellFactory((ListView<Evenement> param) -> new CustomListCell<Evenement>());
    listPROD.setCellFactory((ListView<Produit> param) -> new CustomListCell<Produit>());
    listACT.setCellFactory((ListView<activites> param) -> new CustomListCell<activites>());
    
    }    
private Captcha captcha;
    @FXML
    private void annulerR(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("HomeUser.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
}
    GeneratedCaptcha generatedCaptcha;
        @FXML
    private void validerAjout(ActionEvent event) throws IOException {
        
  String userCaptchaResponse = captchaTextField.getText(); // Obtenez la réponse de l'utilisateur

    if (userCaptchaResponse.isEmpty()) {
        captchaErrorLabel.setText("Veuillez entrer la réponse CAPTCHA.");
        return;
    }

    // Obtenez la réponse du CAPTCHA généré précédemment
     // Assurez-vous que cette variable est accessible

    if (userCaptchaResponse.equals(expectedCaptchaAnswer)) {
        // La réponse CAPTCHA est correcte, continuez à ajouter la réclamation
        captchaErrorLabel.setText(""); // Effacez tout message d'erreur
         ajouterReclamation();
    } else {
        // Réponse CAPTCHA incorrecte, affichez un message d'erreur
        captchaErrorLabel.setText("Réponse CAPTCHA incorrecte. Veuillez réessayer.");
    }
      
}
    
    
    @FXML
    private TextField emailR; 
    @FXML
    private DatePicker dateR; 
    @FXML
    private ComboBox<String> typeR; 
    @FXML
    private TextArea detR; 
    @FXML
    private TextField refR; 
    @FXML
    private Button ajoutR;
    @FXML
    private ListView<Evenement> listEVE;
    @FXML
    private ListView<activites> listACT;
    @FXML
    private ListView<Produit> listPROD;
    @FXML
    private Button choixref;
    @FXML
    private ImageView captchaImageView;
    @FXML
    private TextField captchaTextField;
    @FXML
    private Label captchaErrorLabel;
    @FXML
    private Hyperlink corr;
    


    

    
    private void ajouterReclamation() {
        

        
if ( emailR.getText().isEmpty() || typeR.getValue().isEmpty() || refR.getText().isEmpty() || detR.getText().isEmpty()  || dateR.getValue() == null) {
    afficherAlerte("Tous les champs doivent être remplis");
    return;
}

    
    if (!isValidEmail(emailR.getText())) {
        afficherAlerte("L'adresse email n'est pas valide");
        return;
    }
   
try {
    int refObject1 = Integer.parseInt(refR.getText());
    java.sql.Date dateREC = Date.valueOf(dateR.getValue());
    int yearREC = dateREC.toLocalDate().getYear();

    if ( yearREC < 2022 || yearREC > 2023) {
        afficherAlerte("Veuillez entrer des dates comprises entre 2022 et 2023.");
        return;
    }
} catch (NumberFormatException e) {
    afficherAlerte("La référence de l'objet doit être un nombre entier.");
    return;
}

        ServiceReclamation service = new ServiceReclamation();
        
        String email = emailR.getText();
        Date dateREC = Date.valueOf(dateR.getValue()); 
        String typeRec = typeR.getValue(); 
        int refObject = Integer.parseInt(refR.getText()); 
        String details = detR.getText(); 
        
        /**/
         ServiceUser su = new ServiceUser();
          User aold = su.readById(ConnexionUserController.id_modif);
           int  idU = aold.getId_user();
       // User user = new User() ;
        //user = service.chercherByEmail(email);
        //int idU = user.getId_user();
        
        Reclamation reclamation = new Reclamation();
        reclamation.setIdU(idU);
        reclamation.setDateREC(dateREC);
        reclamation.setTypeRec(convertirTypeReclamation(typeRec));
        reclamation.setRefObject(refObject);
        reclamation.setDetails(details);

        
        boolean emailSent = MailSender.sendReclamationEmail(email,dateREC, typeRec, refObject, details);

if (emailSent) {
    // Le courrier électronique a été envoyé avec succès
} else {
    // Échec de l'envoi du courrier électronique
}
        service.ajouterR(reclamation);
        
        Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
        confirmation.setTitle("Ajout réussi");
        confirmation.setHeaderText(null);
        confirmation.setContentText("La réclamation a été ajoutée avec succès. \n \n  Un mail contenant les details de votre réclamation vous a été adressé ");
        confirmation.showAndWait();

        
        Stage stage = (Stage) ajoutR.getScene().getWindow();
        stage.close();
                try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeUser.fxml"));
            Parent root = loader.load();
            Stage nouvelleStage = new Stage();
            nouvelleStage.setScene(new Scene(root));
            nouvelleStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    public void actualiserListViewE(ActionEvent event) {
    
    ServiceReclamation serviceReclamation = new ServiceReclamation();
    List<Evenement> evenement = serviceReclamation.afficherevent();

    
    ObservableList<Evenement> observableEVE = FXCollections.observableArrayList(evenement);
    listEVE.getItems().clear();
    
    listEVE.setItems(observableEVE);
}
    
        public void actualiserListViewA(ActionEvent event) {
    
    ServiceReclamation serviceReclamation = new ServiceReclamation();
    List<activites> activite = serviceReclamation.afficherA();

    
    ObservableList<activites> observableACT = FXCollections.observableArrayList(activite);
    listACT.getItems().clear();
    
    listACT.setItems(observableACT);
}
        
            public void actualiserListViewP(ActionEvent event) {
    
    ServiceReclamation serviceReclamation = new ServiceReclamation();
    List<Produit> prod = serviceReclamation.affihcer();

    
    ObservableList<Produit> observablePROD = FXCollections.observableArrayList(prod);
    listPROD.getItems().clear();
    
    listPROD.setItems(observablePROD);
}

    
    public int convertirTypeReclamation(String typeRec) {
       
        String[] types = {"Réclamation liés aux produits", "Réclamation liés aux évènements", "Réclamation liés aux activités"}; // Correspondance des types
        for (int i = 0; i < types.length; i++) {
            if (types[i].equals(typeRec)) {
                return i + 1; 
            }
        }
        return 0; 
    }
    
    private void afficherAlerte(String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}
    
    private boolean isValidEmail(String email) {
    
    return email.contains("@") && email.contains(".");
}
    
    
@FXML
private void handleChoisirButton(ActionEvent event) {
    Evenement evenementSelectionne = listEVE.getSelectionModel().getSelectedItem();
    Produit produitSelectionne = listPROD.getSelectionModel().getSelectedItem();
    activites activiteSelectionne = listACT.getSelectionModel().getSelectedItem();

    if (evenementSelectionne != null) {
        int idEvenementSelectionne = evenementSelectionne.getId_event();
           refR.setText(String.valueOf(idEvenementSelectionne));
           listEVE.getSelectionModel().clearSelection();
        
    } else if (produitSelectionne != null) {
        int idProduitSelectionne = produitSelectionne.getId_prod();
           refR.setText(String.valueOf(idProduitSelectionne));
           listPROD.getSelectionModel().clearSelection();
        
    } else if (activiteSelectionne != null) {
        int idActiviteSelectionne = activiteSelectionne.getId_act();
           refR.setText(String.valueOf(idActiviteSelectionne));
           listACT.getSelectionModel().clearSelection();
        
    } else {
        // Aucun élément sélectionné
    }
    

    
}

    public void corriger(ActionEvent event) {
    
   Spellchecker spellCheckerWrapper = new Spellchecker();
   String textToCorrect = detR.getText();
   String correctedText = spellCheckerWrapper.autoCorrectText(textToCorrect);
   detR.setText(correctedText);
}
        
    }
    

