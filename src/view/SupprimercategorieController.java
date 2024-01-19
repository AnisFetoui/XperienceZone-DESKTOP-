/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import classes.Categorie;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import service.CategorieService;
import util.MYDB;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class SupprimercategorieController implements Initializable {
 
 @FXML
 private TableView<Categorie> tableview;
    @FXML
    private TableColumn< Categorie, Integer> idcategorie_col;
    @FXML
    private TableColumn< Categorie, String> nomcateg_col;
    @FXML
    private TableColumn<Categorie, String> des_col;
    @FXML
    private TableColumn<Categorie, String> type;
     @FXML
    private Button supp;
     @FXML
    private Button actualiser;
     @FXML
    private VBox slider;
    @FXML
    private Label menu;
    @FXML
    private Label menuclose;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     * @throws java.sql.SQLException
     */
 @FXML
 public void afficher() throws SQLException
    {
         CategorieService c = new CategorieService();

        ArrayList<Categorie> lc  = c.afficher();
        ObservableList<Categorie> categorieArrayList = FXCollections.observableArrayList(lc);
        idcategorie_col.setCellValueFactory( new PropertyValueFactory<>("Id_categorie"));
         nomcateg_col.setCellValueFactory(new PropertyValueFactory<>("nom_categorie"));
            des_col.setCellValueFactory(new PropertyValueFactory<>("description_categorie"));
            type.setCellValueFactory(new PropertyValueFactory<>("type_categorie"));
           
         tableview.setItems(categorieArrayList);
       
    }

public SupprimercategorieController() {
        Connection con = MYDB.getinstance().getCon();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     try {
         afficher();
     } catch (SQLException ex) {
         Logger.getLogger(SupprimercategorieController.class.getName()).log(Level.SEVERE, null, ex);
     }
     slider.setTranslateX(0);
        menuclose.setVisible(true);
        menu.setVisible(false);
     
    }
    private void actualiserAction(ActionEvent event) throws SQLException {
       afficher();
    }
    @FXML
   private void deleteselectedact(ActionEvent event) {
    Categorie selectedItem = tableview.getSelectionModel().getSelectedItem();
    
    if (selectedItem != null) {
        // Get the ID of the selected item from your data model
        int idCategorie = selectedItem.getId_categorie(); // Remplacez par la propriété contenant l'ID
        
        // Show a confirmation dialog
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Confirmation");
        alert.setHeaderText("Are you sure you want to delete this item?");
        alert.setContentText(selectedItem.getNom_categorie());

        // Add OK and Cancel buttons
        ButtonType buttonTypeOK = new ButtonType("OK");
        ButtonType buttonTypeCancel = new ButtonType("Cancel"); 

        alert.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);

        // Handle the button actions
        alert.showAndWait().ifPresent(response -> {
            if (response == buttonTypeOK) {
                try {
                    CategorieService c = new CategorieService();
                    c.supprimer(idCategorie); // Utilisez l'ID de la catégorie
                    System.out.println("Supprimé avec succès");
                    
                    tableview.getItems().remove(selectedItem);
                } catch (SQLException ex) {
                    Logger.getLogger(SupprimercategorieController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
@FXML
    private void Retour(ActionEvent event) {
    
    try {
    Parent root = FXMLLoader.load(getClass().getResource("Gestion categorie.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();} catch (IOException ex) {
            Logger.getLogger(GestionProduitController.class.getName()).log(Level.SEVERE, null, ex);
    
    }}
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
    private void produit(ActionEvent event) {
    
    try {
    Parent root = FXMLLoader.load(getClass().getResource("Gestion produit.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();} catch (IOException ex) {
            Logger.getLogger(GestionProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
 }
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
    }

   
    
  
