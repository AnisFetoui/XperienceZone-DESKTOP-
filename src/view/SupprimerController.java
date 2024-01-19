/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import static java.time.Clock.system;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import classes.activites;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;
import service.serviceactivites;
import util.MYDB;

/**
 * FXML Controller class
 *
 * @author Med Aziz
 */
public class SupprimerController implements Initializable {
        Connection con; 
        Statement ste;

    @FXML
    private AnchorPane slider;
    @FXML
    private Label menu;
    @FXML
    private Label menuclose;
    @FXML
    private TableView<activites> tableview;
    @FXML
    private TableColumn<activites,String> act_col;
    @FXML
    private TableColumn<activites,String> des_col;
    @FXML
    private TableColumn<activites,String> org_col;
    @FXML
    private TableColumn<activites,String> couv_col;
    @FXML
    private TableColumn<activites,String> add_col;
    @FXML
    private TableColumn<activites,Integer> part_col;
    @FXML
    private TableColumn<activites,String> prix_col;
    @FXML
    private TableColumn<activites,Integer> duree_col;
    @FXML
    private TableColumn<activites,String> periode_col;
    @FXML
    private Button supp;
    @FXML
    private Button pagechercher;
    @FXML
    private Button pageajouter;
    @FXML
    private Button pagemodifier;
    @FXML
    private Button pagesupprimer;
    int userid ;

    public SupprimerController() {
         con = MYDB.getinstance().getCon();
    }
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         updateUIBasedOnIdAct();
        slider.setTranslateX(0);
        menuclose.setVisible(true);
        menu.setVisible(false);
        
        ////////////////////////////////
        
        
        
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
    private void deleteselectedact(ActionEvent event){
    activites selectedItem = tableview.getSelectionModel().getSelectedItem();
    if (selectedItem != null) {
        
        int id = selectedItem.getId_act();
        System.out.println(id);
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation ");
        alert.setHeaderText("Etes vous sur de vouloir supprimer cet activité?");
        alert.setContentText(selectedItem.getNom_act());

        
        ButtonType buttonTypeOK = new ButtonType("OK");
        ButtonType buttonTypeCancel = new ButtonType("Annuler");

        alert.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);

        // Handle the button actions
        alert.showAndWait().ifPresent(response -> {
            if (response == buttonTypeOK) {
                serviceactivites  sa = new serviceactivites();
                sa.supprimerActivite(id);
               
                tableview.getItems().remove(selectedItem);
            }
        });
    }
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

    void setIduser(int iduserconnected) {
        userid =iduserconnected;
        System.out.println("id dans supp = "+userid);
        updateUIBasedOnIdAct();
    }

    private void updateUIBasedOnIdAct() {
        try{
        serviceactivites sa = new serviceactivites();
        
        ArrayList<activites> activitesTrouvees = sa.chercherpariduser(userid);
        ObservableList<activites> activitesList = FXCollections.observableArrayList(sa.chercherpariduser(userid));
        tableview.setItems(activitesList);

        act_col.setCellValueFactory(new PropertyValueFactory<>("nom_act"));
        des_col.setCellValueFactory(new PropertyValueFactory<>("description"));
       org_col.setCellValueFactory(new PropertyValueFactory<>("organisateur"));
        couv_col.setCellValueFactory(new PropertyValueFactory<>("lieu_act"));
        add_col.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        part_col.setCellValueFactory(new PropertyValueFactory<>("place_dispo"));
        prix_col.setCellValueFactory(new PropertyValueFactory<>("prix_act"));
        duree_col.setCellValueFactory(new PropertyValueFactory<>("durée"));
        periode_col.setCellValueFactory(new PropertyValueFactory<>("periode"));
      } catch (Exception e) {
            e.printStackTrace(); // Handle any exceptions here
        }
    }

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

    @FXML
    private void produit(ActionEvent event) {
    }
    
    
}



    
    
        
        




