/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Base64;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
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
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import classes.activites;
import service.serviceactivites;

/**
 * FXML Controller class
 *
 * @author Med Aziz
 */
public class AjouteracController implements Initializable {

    @FXML
    private TextField nomaj;
    
    @FXML
    private Spinner<Integer> nbrplaceaj;
    @FXML
    private TextField prixaj;
    private TextField descriptionaj;
    @FXML
    private TextField organisateur;
    @FXML
    private TextField adresse;
    @FXML
    private Spinner<Integer> durée;
    private TextField periode;
    @FXML
    private ComboBox combobox;
    private Label alert;
    @FXML
    private Label menu;
    @FXML
    private Label menuclose;
    @FXML
    private Button pagechercher;
    @FXML
    private Button pageajouter;
    @FXML
    private Button pagemodifier;
    @FXML
    private Button pagesupprimer;
    @FXML
    private VBox slider;
    @FXML
    private DatePicker datedebut;
    @FXML
    private DatePicker datefin;
    @FXML
    private TextField descriptionmod;
    @FXML
    private Button btnajouter;
    @FXML
    private Button uploadimages;
    @FXML
    private AnchorPane imagesslider;
      @FXML
    private Button upload;
    @FXML
    private ImageView ImagePreviw;
    @FXML
    private ImageView ImagePreviw2;
    @FXML
    private ImageView ImagePreviw3;
    @FXML
    private ImageView ImagePreviw4;
    @FXML
    private Button savedb;
    int idusercon;
    
    //private String selectedImagePath = null;
    private String[] selectedImagePaths = new String[4];
    private ImageView[] imageViews;
   
    


    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         imageViews = new ImageView[]{ImagePreviw, ImagePreviw2, ImagePreviw3, ImagePreviw4};

         datedebut.setValue(LocalDate.of(1999, 01, 01));
        datefin.setValue(LocalDate.of(1999, 01, 01));
        slider.setTranslateX(0);
        imagesslider.setTranslateX(-1300);
        
        menuclose.setVisible(true);
        menu.setVisible(false);
        ObservableList<String> lista = FXCollections.observableArrayList(
    "Nabeul",
    "Zaghouan",
    "Bizerte",
    "Béja",
    "Jendouba",
    "Kef",
    "Siliana",
    "Sousse",
    "Monastir",
    "Mahdia",
    "Sfax",
    "Kairouan",
    "Kasserine",
    "Sidi Bouzid",
    "Gabès",
    "Medenine",
    "Tataouine",
    "Tozeur",
    "Gafsa",
    "Tunis",
    "Ariana",
    "BEN Arous",
    "Kébili",
    "Manouba"
        );

combobox.setItems(lista); 
        
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        nbrplaceaj.setValueFactory(valueFactory);
        // ..
        SpinnerValueFactory<Integer> A;
            A = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 72, 0);
        durée.setValueFactory(A);
    }
    
    @FXML
    private String selectG(ActionEvent event) {
        
        String namec = combobox.getSelectionModel().getSelectedItem().toString();
        return namec;
    }
    
    @FXML
    private void AddActivity(ActionEvent event) {
        try {
        String Nom = nomaj.getText();//nom
        
        String Description = descriptionmod.getText();//disc
        String Prix = prixaj.getText();//prix
        
        // ..
        String Organisateur = organisateur.getText();
        String Adresse = adresse.getText();
        
          //String image = selectedImagePath;
           String image = String.join(",", selectedImagePaths);
            //System.out.println("image");
        
        LocalDate selectedDatedebut = datedebut.getValue();
        LocalDate selectedDatefin = datefin.getValue();

        
        String debutdate = selectedDatedebut.toString();
        String findate = selectedDatefin.toString();

        String Periode = debutdate + " - " + findate;
        // ..
        

        int Durée = durée.getValue();
        int Placedispo = nbrplaceaj.getValue();
        // ...
        //String selectedGouvernorat = selectG(null);
        String selectedGouvernorat = combobox.getValue() != null ? combobox.getValue().toString() : "";

        String Gouvernorat = selectedGouvernorat;
         serviceactivites sa = new serviceactivites();
        
        if (Nom.isEmpty() || Description.isEmpty() || Prix.isEmpty() || Organisateur.isEmpty() || Adresse.isEmpty() ||
        Durée <= 0 || Placedispo <= 0 ||  selectedGouvernorat.isEmpty() 
                || datedebut.getValue().equals(LocalDate.of(1999, 01, 01))
                || datefin.getValue().equals(LocalDate.of(1999, 01, 01))
                
                
                ) {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert");
        alert.setHeaderText("Veuillez remplir tous les champs requis.");

        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(okButton);
        alert.showAndWait();

            
        } else if (!sa.isValidPrice(Prix)) {
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setHeaderText("Prix invalide. Veuillez saisir une période valide");
            ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            alert.getButtonTypes().setAll(okButton);
            alert.showAndWait();
       
        } else {
           
           
            activites activite1 = new activites(Nom, Description, Organisateur, Gouvernorat,Adresse, image,  Placedispo, Prix, Durée, Periode,idusercon);
            sa.ajouterActivite(activite1);
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("confirmation d'ajout de "+ Nom);
            alert.setHeaderText("Activité ajouter avec succée!");
            ImageView customIcon = new ImageView(new Image("/image/tick.png"));
            customIcon.setFitHeight(45); 
            customIcon.setFitWidth(45);  
            alert.setGraphic(customIcon);
            ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            alert.getButtonTypes().setAll(okButton);

            alert.showAndWait();
        }
        
        }catch(Exception e){
            System.out.println("Error occurred: " + e.getMessage());
            e.printStackTrace(); 
        
        }
    
    
    
    
    
    }

 @FXML
    private void onmenuclicked(MouseEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(slider);
        slide.setToX(0);
        slide.play();
        slider.setTranslateX(-176);
        
        menu.setVisible(false);
        menuclose.setVisible(true);
        
    }

    @FXML
    private void onmenuclickedclose(MouseEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(slider);
        slide.setToX(-176);
        slide.play();
        slider.setTranslateX(0);
        
        menuclose.setVisible(false);
        menu.setVisible(true);
        
    }   
    
    @FXML
    void openSupprimerPage(ActionEvent event) {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("supprimerac.fxml"));
            Parent root = loader.load();

            // Create a new stage
            Stage stage = new Stage();
            stage.setTitle("Supprimer Page");
              stage.setFullScreen(true);
            stage.setScene(new Scene(root));

            // Show the new stage
            stage.show();

            // Close the current stage (if needed)
            Stage currentStage = (Stage) pagesupprimer.getScene().getWindow();
            currentStage.close();
        } catch (Exception e) {
            e.printStackTrace(); // Handle any exceptions here
        }
    }

    @FXML
    private void openChercherPage(ActionEvent event) {
         try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("activité.fxml"));
            Parent root = loader.load();

            // Create a new stage
            Stage stage = new Stage();
            stage.setTitle("Page d'acceuil ");
            stage.setFullScreen(true);
            stage.setScene(new Scene(root));

            // Show the new stage
            stage.show();

            // Close the current stage (if needed)
            Stage currentStage = (Stage) pagechercher.getScene().getWindow();
            currentStage.close();
        } catch (Exception e) {
            e.printStackTrace(); // Handle any exceptions here
        }
    }

    @FXML
    private void openAjouterPage(ActionEvent event) {
         try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ajouter.fxml"));
            Parent root = loader.load();

            // Create a new stage
            Stage stage = new Stage();
            stage.setTitle(" Page Ajouter");
            stage.setFullScreen(true);
            stage.setScene(new Scene(root));

            // Show the new stage
            stage.show();

            // Close the current stage (if needed)
            Stage currentStage = (Stage) pageajouter.getScene().getWindow();
            currentStage.close();
        } catch (Exception e) {
            e.printStackTrace(); // Handle any exceptions here
        }
    }

    @FXML
    private void openModifierPage(ActionEvent event) {
         try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("modifieractivite.fxml"));
            Parent root = loader.load();

            // Create a new stage
            Stage stage = new Stage();
            stage.setTitle("Page Modifier");
            stage.setFullScreen(true);
            stage.setScene(new Scene(root));

            // Show the new stage
            stage.show();

            // Close the current stage (if needed)
            Stage currentStage = (Stage) pagemodifier.getScene().getWindow();
            currentStage.close();
        } catch (Exception e) {
            e.printStackTrace(); // Handle any exceptions here
        }
    }

    @FXML
    private void ajouterimages(ActionEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(imagesslider);
        if( imagesslider.getTranslateX()== -1300){
        slide.setToX(0);
        slide.play();
        }else{
            slide.setToX(-1300);
        slide.play();
        }
        
    }

    @FXML
    private void selectimage(ActionEvent event) throws MalformedURLException{
        FileChooser  fileChooser = new FileChooser();
        fileChooser.setTitle("open a file");
        fileChooser.setInitialDirectory(new File("C:\\"));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPEG image", "*jpg"),
                new FileChooser.ExtensionFilter("PNG image", "*png"));
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
         for (int i = 0; i < 6; i++) {
        if (selectedImagePaths[i] == null) { // Vérifiez si un emplacement est disponible
            File selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile != null) {
                selectedImagePaths[i] = selectedFile.toURI().toURL().toExternalForm();
                Image image = new Image(selectedImagePaths[i]);
                imageViews[i].setImage(image);
            } else {
                System.out.println("Aucun fichier n'a été sélectionné");
            }
            break; // Sortez de la boucle après avoir ajouté une image
        }
    }
           
           
    }

    @FXML
    private void savetodb(ActionEvent event) {
         TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(imagesslider);
        if( imagesslider.getTranslateX()== -1300){
        slide.setToX(0);
        slide.play();
        }else{
            slide.setToX(-1300);
        slide.play();
        }
      
    }

    void setIduser(int iduserconnected) {
       idusercon = iduserconnected;  
    }

 @FXML
    private void home(ActionEvent event) {
    
    try {
    Parent root = FXMLLoader.load(getClass().getResource("Homeproduit.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();} catch (IOException ex) {
            Logger.getLogger(GestionProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }}
    
    @FXML
    private void evenement(ActionEvent event) {
    
    try {
    Parent root = FXMLLoader.load(getClass().getResource("ajouterevenement.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();} catch (IOException ex) {
            Logger.getLogger(GestionProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }}
    @FXML
    private void reclamation(ActionEvent event) {
    
    try {
    Parent root = FXMLLoader.load(getClass().getResource("ajout_rec.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();} catch (IOException ex) {
            Logger.getLogger(GestionProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }}

    @FXML
    private void channel(ActionEvent event) {
    
    try {
    Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();} catch (IOException ex) {
            Logger.getLogger(GestionProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }}
    


  
    
}