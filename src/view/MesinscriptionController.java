/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import classes.activites;
import classes.inscription;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.serviceactivites;
import service.serviceinscription;
import util.MYDB;

/**
 * FXML Controller class
 *
 * @author Med Aziz
 */
public class MesinscriptionController implements Initializable {
            Connection con; 
        Statement ste;
    @FXML
    private Button supp;
    @FXML
    private TableView<inscription> tableview;
    @FXML
    private TableColumn<inscription, LocalDate> date;
    @FXML
    private TableColumn<inscription, LocalTime> heure;
    @FXML
    private TableColumn<inscription, Integer> nbpers;
    @FXML
    private TableColumn<inscription, Double> Pt;
    int idinscription;
    @FXML
    private Button precedent;

    /**
     * Initializes the controller class.
     */
    
    public MesinscriptionController() {
        con = MYDB.getinstance().getCon();
         if (con == null) {
        System.out.println("Database connection is not initialized.");
    }else{System.out.println("Database connection iss initialized.");
         }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        serviceinscription sa = new serviceinscription();
       ArrayList<inscription> inscriptions  = sa.chercherpariduser(1);
        ObservableList<inscription> inscriptionsList = FXCollections.observableArrayList(sa.chercherpariduser(1));
        
        tableview.setItems(inscriptionsList);
        
      
        date.setCellValueFactory(new PropertyValueFactory<>("date_ins"));
       heure.setCellValueFactory(new PropertyValueFactory<>("heure_ins"));
       nbpers.setCellValueFactory(new PropertyValueFactory<>("nbr_tickes"));
        Pt.setCellValueFactory(new PropertyValueFactory<>("frait_abonnement"));
        
       
    }    


    @FXML
    private void deleteselectedact(ActionEvent event) {
          inscription selectedItem = tableview.getSelectionModel().getSelectedItem();
    if (selectedItem != null) {
        
        int id = selectedItem.getId_ins();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation ");
        alert.setHeaderText("Etes vous sur de vouloir supprimer cet activité?");
        //alert.setContentText("ID: " + selectedItem.getId_ins() + "\nDate: " + selectedItem.getDate_ins());

        
        ButtonType buttonTypeOK = new ButtonType("OK");
        ButtonType buttonTypeCancel = new ButtonType("Annuler");

        alert.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);

        // Handle the button actions
        alert.showAndWait().ifPresent(response -> {
            if (response == buttonTypeOK) {
                serviceinscription  sa = new serviceinscription();
                sa.SupprimerAbonnement(id);
               
                tableview.getItems().remove(selectedItem);
            }
        });
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
    Parent root = FXMLLoader.load(getClass().getResource("evenementview.fxml"));
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
    Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();} catch (IOException ex) {
            Logger.getLogger(GestionProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }}
    
    @FXML
    private void goback(ActionEvent event) {
    }

  
    
}
