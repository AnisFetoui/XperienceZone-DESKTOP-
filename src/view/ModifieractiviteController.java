/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
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
import javafx.stage.Stage;
import javafx.util.Duration;
import classes.activites;
import service.serviceactivites;
import util.MYDB;

/**
 * FXML Controller class
 *
 * @author Med Aziz
 */
public class ModifieractiviteController implements Initializable {
            Connection con; 
        Statement ste;
    @FXML
    private Label menu;
    @FXML
    private Label menuclose;
    @FXML
    private VBox slider;

    @FXML
    private ComboBox combobox;
    @FXML
    private Button btnmodifier;
    @FXML
    private TextField nommod;
    @FXML
    private TextField organisateurmod;
    @FXML
    private TextField adressemod;
    @FXML
    private Spinner<Integer> nbrplacemod;
    @FXML
    private TextField prixmod;
    @FXML
    private TextField descriptionmod;
    @FXML
    private Spinner<Integer> duréemod;
    @FXML
    private TextField periodemod;
    @FXML
    private Button pagechercher;
    @FXML
    private Button pageajouter;
    @FXML
    private Button pagemodifier;
    @FXML
    private Button pagesupprimer;
    
    @FXML
    private Label msg;
    @FXML
    private DatePicker datedebut;
    @FXML
    private DatePicker datefin;
    
    public int idAct;
    int idusercon;
    String image;
            
    

    
        public ModifieractiviteController() {
         con = MYDB.getinstance().getCon();
    }  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateUIBasedOnIdAct();
        System.out.println("id a modifier initialised to = "+idAct);
        slider.setTranslateX(0);
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
        nbrplacemod.setValueFactory(valueFactory);
        // ..
        SpinnerValueFactory<Integer> A;
        A = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 72, 0);
        duréemod.setValueFactory(A);  
        
        

        
        
        
    }

    void setIdAct(int code) {
       idAct = code;
        System.out.println("id a modifier setted to = "+idAct);
        updateUIBasedOnIdAct();
    }
    void setIduser(int iduserconnected) {
        idusercon = iduserconnected;
    }
      private void updateUIBasedOnIdAct() {
          
        System.out.println(idAct);
        try{
        serviceactivites sa = new serviceactivites();
        activites activite = new activites();
        activite = sa.chercherbyidact(idAct);
          System.out.println("id a modifier recu = "+idAct);
          image=activite.getImages();
        organisateurmod.setText(activite.getOrganisateur());
        System.out.println(activite.getOrganisateur());
        nommod.setText(activite.getNom_act());
        adressemod.setText(activite.getAdresse());
        prixmod.setText(activite.getPrix_act());
        descriptionmod.setText(activite.getDescription());
        //periodemod.setText(activite.getPeriode());
        int duree = activite.getDuree();
        SpinnerValueFactory<Integer> dureeValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 72, duree);
        duréemod.setValueFactory(dureeValueFactory);
        
        int nbrplace = activite.getPlace_dispo();
        SpinnerValueFactory<Integer> nbrplaceValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 72, nbrplace);
        nbrplacemod.setValueFactory(nbrplaceValueFactory);
        combobox.getEditor().setText(activite.getLieu_act());
        //recupuration de date periode
        String dbperiode;
        dbperiode = activite.getPeriode();
        String[] dateParts = dbperiode.split(" - ");
        String debutdate = dateParts[0];
        String findate = dateParts[1];
        LocalDate startDate = LocalDate.parse(debutdate);
        LocalDate endDate = LocalDate.parse(findate);
        datedebut.setValue(startDate);
        datefin.setValue(endDate);
          } catch (Exception ex) {
        System.out.println(ex);
    }
       
    }

    @FXML
    private void changeActivity(ActionEvent event) {
    try {
        String Nom = nommod.getText();//nom
        
        String Description = descriptionmod.getText();//disc
        String Prix = prixmod.getText();//prix
        
        // ..
        String Organisateur = organisateurmod.getText();
        String Adresse = adressemod.getText();
       
        String images = image;
        System.out.println(images);
        // ..
//
LocalDate selectedDatedebut = datedebut.getValue();
String debutdate = selectedDatedebut.toString();

LocalDate selectedDatefin = datefin.getValue();
String findate = selectedDatefin.toString();


String Periode = debutdate + " - " + findate;

//
        int Durée = duréemod.getValue();
        int Placedispo = nbrplacemod.getValue();
        
         String selectedGouvernorat = selectG(null);
        String Gouvernorat = selectedGouvernorat;
       
        
        
        serviceactivites sa = new serviceactivites();
       if (!sa.isValidPrice(Prix)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert ");
            alert.setHeaderText("Veuiller respecter la forme de prix 99.99 !");
            alert.showAndWait();
     
        } else {
        
        activites activite1 = new activites(Nom,Description,Organisateur,Gouvernorat,Adresse,image,Placedispo,Prix,Durée,Periode,idusercon);
         activite1.setId_act(idAct); //neqsa fonction qui recupere lid
        sa.mettreAJourActivite(activite1);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("confirmation de modification de "+ Nom);
            alert.setHeaderText("Modification Enregistrer!");
            ImageView customIcon = new ImageView(new Image("/image/tick.png"));
            customIcon.setFitHeight(45); 
            customIcon.setFitWidth(45);  
            alert.setGraphic(customIcon);
            ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            alert.getButtonTypes().setAll(okButton);

            alert.showAndWait();
         
        }}catch(Exception e){
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("supprimer.fxml"));
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
    private String selectG(ActionEvent event) {
        
        String namec = combobox.getSelectionModel().getSelectedItem().toString();
        return namec;
    }



  
   
    
}

