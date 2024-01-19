/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import classes.Categorie;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import service.CategorieService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class GestionCategorieController implements Initializable {
@FXML
    private VBox slider;
    @FXML
    private Label menu;
    @FXML
    private Label menuclose;
    @FXML
    private Button afficher;
    @FXML
    private Button ajouter;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
     @FXML
    private Button cherchercat;
       @FXML
    private TextField  chercher_categ;
    @FXML
    private TableView<Categorie> tableview;
    @FXML
    private TableColumn<Categorie, Integer> id_categ;
    @FXML
    private TableColumn<Categorie, String> nom_categ;
    @FXML
    private TableColumn<Categorie, String> des_col;
    @FXML
    private TableColumn<Categorie, String> type_col;
 public static int id_modif ;
    /**
     * Initializes the controller class.
     */
   

    @FXML
    private void afficher() {
        try {
            CategorieService c = new CategorieService();
            
            List<Categorie> lu = c.afficher();
            ObservableList<Categorie> categorieList = FXCollections.observableArrayList(lu);
            id_categ.setCellValueFactory(new PropertyValueFactory<>("id_categorie"));
            nom_categ.setCellValueFactory(new PropertyValueFactory<>("nom_categorie"));
            des_col.setCellValueFactory(new PropertyValueFactory<>("description_categorie"));
            type_col.setCellValueFactory(new PropertyValueFactory<>("type_categorie"));
            
            tableview.setItems(categorieList);
        } catch (SQLException ex) {
            Logger.getLogger(GestionCategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     @Override
    public void initialize(URL url, ResourceBundle rb) {
       
            // TODO
            afficher();
            slider.setTranslateX(0);
        menuclose.setVisible(true);
        menu.setVisible(false);
      
        
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
    private void afficherAction(ActionEvent event) throws SQLException {
       afficher();
    }
    

    @FXML
    private void ajouterCategorie(ActionEvent event) {
        try {

            Parent page1 = FXMLLoader.load(getClass().getResource("Ajoutercategorie.fxml"));

            Scene scene = new Scene(page1);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);

            stage.show();

        } catch (IOException ex) {

            System.out.println(ex.getMessage());

        }
        
    
    }
    @FXML
    private void supprimerCategorie(ActionEvent event) {
        try {

            Parent page1 = FXMLLoader.load(getClass().getResource("supprimercategorie.fxml"));

            Scene scene = new Scene(page1);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);

            stage.show();

        } catch (IOException ex) {

            System.out.println(ex.getMessage());

        }
        
    
    }


    @FXML

private void modifierCategorie(ActionEvent event) throws SQLException {
    int SelectedRowIndex = tableview.getSelectionModel().getSelectedIndex();
    int ColumnIndex = tableview.getColumns().indexOf(id_categ);
    
    Alert A = new Alert(Alert.AlertType.CONFIRMATION);
    if (SelectedRowIndex == -1) {
        A.setAlertType(Alert.AlertType.WARNING);
        A.setContentText("Selectionnez une colonne ! ");
        A.show();
    } else {
        String IdCell = tableview.getColumns().get(ColumnIndex).getCellData(SelectedRowIndex).toString();
        id_modif = Integer.parseInt(IdCell);
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Modifiercategorie.fxml"));
            Parent root = loader.load();
            ModifiercategorieController modifierController = loader.getController();
            
            // Obtenez la catégorie sélectionnée à partir de votre TableView
            Categorie selectedCategorie = tableview.getSelectionModel().getSelectedItem();
            
            // Transmettez la catégorie sélectionnée au contrôleur de modification
            modifierController.initializeData(selectedCategorie);
            
            // Ajoutez des messages de débogage pour vérifier la catégorie sélectionnée
            System.out.println("Selected category: " + selectedCategorie);
            
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }}}
    private boolean isSearchInputValid(String valeur) {
    if (valeur.isEmpty()) {
        showErrorAlert("Le champ de recherche est vide. Veuillez saisir une valeur.");
        return false;
    }
    return true;
}

    @FXML
private void chercherCategorie(ActionEvent event) throws SQLException {
    String colonne = "nom_categorie";
    String valeur = chercher_categ.getText(); // La valeur saisie par l'utilisateur

    if (isSearchInputValid(valeur)) {
        CategorieService categorieService = new CategorieService();
        List<Categorie> categoriesCherches = categorieService.chercher(colonne, valeur);

        ObservableList<Categorie> categoriesCherchesList = FXCollections.observableArrayList(categoriesCherches);
        tableview.setItems(categoriesCherchesList);
    }
}

    private void showErrorAlert(String le_champ_de_recherche_est_vide_Veuillez_s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
@FXML
    private void Retour(ActionEvent event) {
    
    try {
    Parent root = FXMLLoader.load(getClass().getResource("Gestion produit.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();} catch (IOException ex) {
            Logger.getLogger(GestionProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }}
 @FXML
    private void Produit(ActionEvent event) {
    
    try {
    Parent root = FXMLLoader.load(getClass().getResource("Gestion produit.fxml"));
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
    
   
}

