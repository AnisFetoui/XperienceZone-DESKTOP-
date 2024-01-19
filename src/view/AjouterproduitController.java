/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import classes.ExcelGenerator;
import classes.Categorie;
import classes.Produit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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
public class AjouterproduitController implements Initializable {
    // CategorieService catser = new CategorieService();
@FXML
    private VBox slider;
    @FXML
    private Label menu;
    @FXML
    private Label menuclose;
    @FXML
    private TextField nomprod;
    @FXML
    private TextField prixprod;
    @FXML
    private TextArea descprod;
    @FXML
    private TextField quantiteprod;
    @FXML
    private ChoiceBox<String> choixcp;
     private File selectedFile;
  

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    Produitservice prodser= new Produitservice();
    @FXML
    private ImageView image_view;
@FXML
private Text image_label;
  
    private BorderPane borderPane;

    @Override
      public void initialize(URL url, ResourceBundle rb) {
        // TODO
         slider.setTranslateX(0);
        menuclose.setVisible(true);
        menu.setVisible(false);
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
           choixcp.getItems().add(c.getNom_categorie());
           
           }
      }
      @FXML
    private void ajoutProduit(ActionEvent event) throws SQLException {
    Produit p = new Produit();
 CategorieService catser = new CategorieService();
    if (!validateFields()) {
        return;
    }

    /*try*/ {
       p.setNom_prod(nomprod.getText());
        p.setPrix_prod(Double.parseDouble(prixprod.getText()));
        p.setdescription_prod(descprod.getText());
        p.setquantite(Integer.parseInt(quantiteprod.getText()));
        Categorie c1 = catser.RetournerT((String) choixcp.getSelectionModel().getSelectedItem());
        p.setImage(image_label.getText());
        p.setCategorie(c1);
        
    String htdocsPath = "C:/Users/ASUS/Desktop/"; // Utilisez le répertoire Desktop au lieu de Bureau
File destinationFile = new File(htdocsPath + selectedFile.getName());
      

if (selectedFile != null) {
    try (InputStream in = new FileInputStream(selectedFile);
         OutputStream out = new FileOutputStream(destinationFile)) {
        byte[] buf = new byte[8192];
        int length;
        while ((length = in.read(buf)) > 0) {
            out.write(buf, 0, length);
        }
        prodser.ajout(p);
    } catch (IOException ex) {
        showErrorAlert("Erreur lors de la copie du fichier.");
        System.out.println(ex);
    }
} else {
    showErrorAlert("Le fichier sélectionné est nul.");
}
    }}
private boolean validateFields() {
    if (nomprod.getText().isEmpty() || prixprod.getText().isEmpty() || descprod.getText().isEmpty() || quantiteprod.getText().isEmpty() || choixcp.getValue() == null || image_view.getImage() == null) {
        showErrorAlert("Veuillez remplir tous les champs");
        return false;
    }

    if (nomprod.getText().matches("\\d*")) {
        showErrorAlert("Le nom de produit doit être une chaîne");
        return false;
    }

    if (!quantiteprod.getText().matches("\\d*")) {
        showErrorAlert("La quantité doit être un nombre positif");
        return false;
    }

    double prix = Double.parseDouble(prixprod.getText());
    if (prix <= 0) {
        showErrorAlert("Le prix doit être un nombre positif");
        return false;
    }

    return true;
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
        selectedFile = fileChooser.showOpenDialog(stage); // Stocker le fichier sélectionné

        if (selectedFile != null) {
            String imageName = selectedFile.getName().replaceAll("\\s+", "");
            image_label.setText(imageName);

            try {
                Image image = new Image(selectedFile.toURI().toURL().toString());
                if (!image.isError()) {
                    image_view.setImage(image);
                    System.out.println("Image chargée depuis : " + selectedFile.getPath());
                } else {
                    // L'image n'a pas pu être chargée, afficher un message d'erreur.
                    showErrorAlert("Impossible de charger l'image sélectionnée.");
                }
            } catch (Exception ex) {
                // Gérer les exceptions, par exemple, afficher un message d'erreur.
                showErrorAlert("Une erreur s'est produite lors du chargement de l'image.");
                System.out.println(ex);
            }
        } else {
            // Aucun fichier n'a été sélectionné, vous pouvez afficher un message si nécessaire.
            showErrorAlert("Aucun fichier sélectionné.");
            System.out.println("Aucun fichier sélectionné.");
        }
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
    
}


