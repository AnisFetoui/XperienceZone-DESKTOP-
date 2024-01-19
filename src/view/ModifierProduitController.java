/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import classes.Categorie;
import classes.Produit;
import java.io.File;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import service.CategorieService;
import service.Produitservice;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ModifierProduitController implements Initializable {
@FXML
    private VBox slider;
    @FXML
    private Label menu;
    @FXML
    private Label menuclose;
    @FXML
    private TextField nomprodmodif;
    @FXML
    private TextField prixpromodif;
    @FXML
    private TextArea despromodif;
    @FXML
    private TextField quantitepromodif;
    @FXML
    private ChoiceBox<String> choixpmodif;
    @FXML
    private ImageView image_view;
    @FXML
    private Label image_label;

    /**
     * Initializes the controller class.
     */
     private Produit selectedProduit;

    public void initializeData(Produit produit) {
        selectedProduit = produit;
        if (produit != null) {
            nomprodmodif.setText(produit.getNom_prod());
            prixpromodif.setText(String.valueOf(produit.getPrix_prod()));
            despromodif.setText(produit.getdescription_prod());
            quantitepromodif.setText(String.valueOf(produit.getquantite()));
            choixpmodif.setValue(produit.getCategorie().getNom_categorie());
        }
    }

    public void initializeChoiceBoxItems(ChoiceBox<String> choiceBox) throws SQLException {
        // Initialisez la ChoiceBox (choixpmodif) avec les catégories ici
        // Par exemple :
         CategorieService catService = new CategorieService();
         List<Categorie> categories = catService.afficher();
         for (Categorie categorie : categories) {
             choiceBox.getItems().add(categorie.getNom_categorie());
         
    

         }}
    @FXML
    private void modifierProduit(ActionEvent event) throws SQLException {
         CategorieService catser = new CategorieService();
        if (selectedProduit != null) {
            String nouveauNom = nomprodmodif.getText();
            double nouveauPrix = Double.parseDouble(prixpromodif.getText());
            String nouvelleDescription = despromodif.getText();
            int nouvelleQuantite = Integer.parseInt(quantitepromodif.getText());
             Categorie c1 = catser.RetournerT((String) choixpmodif.getSelectionModel().getSelectedItem());

            selectedProduit.setNom_prod(nouveauNom);
            selectedProduit.setPrix_prod(nouveauPrix);
            selectedProduit.setdescription_prod(nouvelleDescription);
            selectedProduit.setquantite(nouvelleQuantite);
              selectedProduit.setCategorie(c1);
            // Ajout du code de gestion de l'image si nécessaire
            // Appelez votre service pour mettre à jour le produit dans la base de données
            Produitservice produitService = new Produitservice();
            produitService.modifier(selectedProduit); // Gérez l'exception correctement, par exemple, affichez un message d'erreur

            // Fermez la fenêtre de modification
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void chooseImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Fichiers image", "*.png", "*.jpg", "*.gif")
        );
        File defaultDir = new File(System.getProperty("user.home"));
        fileChooser.setInitialDirectory(defaultDir);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            String imageName = selectedFile.getName().replaceAll("\\s+", "");
            image_label.setText(imageName);

            try {
                Image image = new Image(selectedFile.toURI().toURL().toString());
                if (!image.isError()) {
                    image_view.setImage(image);
                    System.out.println("Image chargée depuis : " + selectedFile.getPath());
                } else {
                    showErrorAlert("Impossible de charger l'image sélectionnée.");
                }
            } catch (Exception ex) {
                showErrorAlert("Une erreur s'est produite lors du chargement de l'image.");
                System.out.println(ex);
            }
        } else {
            showErrorAlert("Aucun fichier sélectionné.");
            System.out.println("Aucun fichier sélectionné.");
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
      //  List<String> categories = loadCategoriesFromDataSource();

        // Créer une liste observable pour les catégories
    //    ObservableList<String> observableCategories = FXCollections.observableArrayList(categories);

        // Remplir le ChoiceBox avec les catégories
        //choixpmodif.setItems(observableCategories);
        CategorieService catser = null;
         try {
             catser = new CategorieService();
             // ArrayList <Categorie> liste = catser.readAll();
         } catch (SQLException ex) {
             Logger.getLogger(AjouterproduitController.class.getName()).log(Level.SEVERE, null, ex);
         }
      
    /*for (Categorie c :liste){
       choixCP.getItems().add(c);  
    } */ 
     ObservableList<Categorie> categories =FXCollections.observableArrayList(catser.afficher());
    //choixCP.setItems(categories);
           for (Categorie c :categories){
           choixpmodif.getItems().add(c.getNom_categorie());
           
           }
             slider.setTranslateX(0);
        menuclose.setVisible(true);
        menu.setVisible(false);
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

   



