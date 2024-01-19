/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import classes.User;
import javafx.geometry.Insets; 
//import java.awt.Insets;
import java.io.IOException;

import java.net.URL;
import java.net.HttpURLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;
import classes.activites;
import service.ServiceUser;
import service.serviceactivites;
import util.MYDB;
import view.ConnexionUserController;

/**
 * FXML Controller class
 *
 * @author Med Aziz
 */
public class ActivitéController implements Initializable {

    private TextField recname;
        Connection con; 
        Statement ste;

    private List<activites> activitestrouveé;
    @FXML
    private GridPane cardLayout;
    @FXML
    private ComboBox combo;
    @FXML
    private ComboBox combo2;
    private Label alert;
     int column = 0;
        int row = 1;
    @FXML
    private AnchorPane slider;
    @FXML
    private Label menu;
    @FXML
    private Button pageajouter;
    @FXML
    private Button pagemodifier;
    @FXML
    private Button pagesupprimer;
    @FXML
    private Button meteo;
    @FXML
    private AnchorPane meteopane;
    @FXML
    private ImageView i1;
    @FXML
    private ImageView i2;
    int iduserconnected;
    @FXML
    private Label testtest;

    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    public ActivitéController() {

         con = MYDB.getinstance().getCon();
    }    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
          ServiceUser su = new ServiceUser();
          User aold = su.readById(ConnexionUserController.id_modif);
          iduserconnected = aold.getId_user();
          //iduserconnected = 40;
          
        slider.setTranslateX(-210);
        meteopane.setTranslateX(400);
        
        menu.setVisible(true);
        activitestrouveé = new ArrayList<>();

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
    "Manouba");
               combo.setItems(lista); 
       

        try {
        String req = "SELECT nom_act FROM activites";
        PreparedStatement pre = con.prepareStatement(req);
        
            try (ResultSet resultSet = pre.executeQuery()) {
                
                while (resultSet.next()) {
                    
                    String nomActivite = resultSet.getString("nom_act");
                  
                     if (!combo2.getItems().contains(nomActivite)) {
                    combo2.getItems().add(nomActivite);
            }
                     }}
        } catch (SQLException ex) {
        }


    }
    
     public int envoyerid (int code) {
        code =iduserconnected;
        return code;
       
    }
    
    @FXML
    private void selectN(ActionEvent event) {
            activitestrouveé.clear();
    
        
        String name = combo2.getSelectionModel().getSelectedItem().toString();
        serviceactivites sa = new serviceactivites();
         List<activites> ls = new ArrayList<>(); 
         ArrayList<activites> foundActivities = sa.chercherActivites(name);
         
   
    for (activites foundActivity : foundActivities) {
        int codeact = foundActivity.getId_act();
        String foundName = foundActivity.getNom_act();
        String foundOrganisateur = foundActivity.getOrganisateur();
        String foundPrix = foundActivity.getPrix_act();
        int nbrparticipant = foundActivity.getPlace_dispo();
        String villeactivite = foundActivity.getLieu_act();
        int idutilisateur = foundActivity.getId_user();
        
     
        activites newActivite = new activites();
        newActivite.setId_act(codeact);
        newActivite.setIdUser(idutilisateur);
        newActivite.setNom_act(foundName);
        newActivite.setOrganisateur(foundOrganisateur);
        newActivite.setPrix_act(foundPrix);
        newActivite.setImages("/image/parachute.jpg");
        newActivite.setLieu_act(villeactivite);
        newActivite.setPlace_dispo(nbrparticipant);
        
       
        activitestrouveé.add(newActivite);
    }
    updateUIWithNewActivities();
  }
    private void updateUIWithNewActivities() {
    
    cardLayout.getChildren().clear();
    column = 0;
    row = 1;
    
    for (activites card : activitestrouveé) {
        try {
            FXMLLoader fxmlloader = new FXMLLoader();
            fxmlloader.setLocation(getClass().getResource("card.fxml"));
            VBox cardBox = fxmlloader.load();
            CardController cardcontroller = fxmlloader.getController();
            cardcontroller.setData(card);

            if (column == 2) {
                column = 0;
                ++row;
            }
            cardLayout.add(cardBox, column++, row);
            GridPane.setMargin(cardBox, new Insets(10));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
    
    @FXML
    private void selectG(ActionEvent event) {
       
         activitestrouveé.clear();
    
        
         String name = combo.getSelectionModel().getSelectedItem().toString();
        serviceactivites sa = new serviceactivites();
         List<activites> ls = new ArrayList<>(); 
         ArrayList<activites> foundActivities = sa.chercherparlieu(name);
         
   
    for (activites foundActivity : foundActivities) {
        int codeact = foundActivity.getId_act();
        String foundName = foundActivity.getNom_act();
        String foundOrganisateur = foundActivity.getOrganisateur();
        String foundPrix = foundActivity.getPrix_act();
        int nbrparticipant = foundActivity.getPlace_dispo();
        String villeactivite = foundActivity.getLieu_act();
        
     
        activites newActivite = new activites();
        newActivite.setId_act(codeact);
        newActivite.setNom_act(foundName);
        newActivite.setOrganisateur(foundOrganisateur);
        newActivite.setPrix_act(foundPrix);
        newActivite.setImages("/image/parachute.jpg");
        newActivite.setLieu_act(villeactivite);
        newActivite.setPlace_dispo(nbrparticipant);
        
       
        activitestrouveé.add(newActivite);
    }
    updateUIWithNewActivities();
       
    }

 


    @FXML
    private void onmenuclicked(MouseEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(slider);
        if( slider.getTranslateX()== 0){
        slide.setToX(-210);
        slide.play();
        }else{
            slide.setToX(0);
        slide.play();
        }
        }
       
          @FXML
 void openSupprimerPage(ActionEvent event) {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("supprimerac.fxml"));
            Parent root = loader.load();
             SupprimerController suppController = loader.getController();
             suppController.setIduser(iduserconnected);

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
    private void openAjouterPage(ActionEvent event) {
         try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ajouter.fxml"));
            Parent root = loader.load();
                
            AjouteracController ajController = loader.getController();
             ajController.setIduser(iduserconnected);
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
            
            ModifieractiviteController modController = loader.getController();
             modController.setIduser(iduserconnected);
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
    private void loadmeteo(ActionEvent event) {
         TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(meteopane);
        if( meteopane.getTranslateX()== 0){
        i1.setVisible(true);
        i2.setVisible(true);
        slide.setToX(400);
       
        slide.play();
        }else{
             i1.setVisible(false);
        i2.setVisible(false);
            slide.setToX(0);
        slide.play();
        }
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("weather.fxml"));
            Parent cardContent = loader.load();
            meteopane.getChildren().setAll(cardContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    private void activite(ActionEvent event) {
    
    try {
    Parent root = FXMLLoader.load(getClass().getResource("activité.fxml"));
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
    

 
    
    

    
    
    
    
    
    
    
    








     
    
    
    
    
    
    
    
    
    
    

    
    
    
    
    

