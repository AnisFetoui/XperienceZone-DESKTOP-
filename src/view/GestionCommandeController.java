/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import classes.Exemple;
import classes.Produit;
import classes.User;
import classes.panier;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleObjectProperty;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import service.PanierService;
import service.Produitservice;
import service.ServiceUser;
import util.MYDB;
//import view.user.ConnexionUserController;


/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class GestionCommandeController implements Initializable {
    private Button Produit;
        private Button produit;
            private Button home;
                private Button categorie;



     @FXML
    private VBox slider;
    @FXML
    private Label menu;
    @FXML
    private Label menuclose;
@FXML
    private TableView<Produit> tableview;
   @FXML
  private  ImageView image_View;
 @FXML
private Label totalMontantLabel; // Label pour afficher le total du panier
 
    @FXML
    private TableColumn<Produit, String> type_col;
    @FXML
    private TableColumn<Produit, String> nomprod_col;
    @FXML
    private TableColumn<Produit, String> prixprod_col;
    @FXML
    private TableColumn<Produit, String> desprod_col;
    @FXML
    private TableColumn<Produit, Integer> quantite_col;
    @FXML
    private TableColumn<Produit, String> image_col;
    @FXML
    private TableColumn<Produit, Integer> categorie_col;
    private ObservableList<Produit> panier = FXCollections.observableArrayList(); // Déclarez et initialisez la liste de panier.


    /**
     * Initializes the controller class.
     
    */
       ServiceUser su = new ServiceUser();
                    User aold = su.readById(ConnexionUserController.id_modif);
    public GestionCommandeController() {
        Connection con = MYDB.getinstance().getCon();
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
private void afficheraction(ActionEvent event)throws SQLException{
    afficher();
}
   
    @FXML   
private void afficher() {
    
    Produitservice produitService = new Produitservice();
    List<Produit> produits = produitService.affihcer();
    ObservableList<Produit> produitList = FXCollections.observableArrayList(produits);
    //idprod_col.setCellValueFactory(new PropertyValueFactory<>("id_prod"));
    nomprod_col.setCellValueFactory(new PropertyValueFactory<>("nom_prod"));
    prixprod_col.setCellValueFactory(new PropertyValueFactory<>("prix_prod"));
    desprod_col.setCellValueFactory(new PropertyValueFactory<>("description"));
     quantite_col.setCellValueFactory(new PropertyValueFactory<>("quantite produit"));
    image_col.setCellValueFactory(new PropertyValueFactory<>("image"));
    categorie_col.setCellValueFactory(new PropertyValueFactory<>("categorie"));
   


    tableview.setItems(produitList);
}
 
      
PanierService panierService = new PanierService(); // Créez une instance de PanierService.

// ...

@FXML
private void ajouterAuPanier(ActionEvent event) {
    Produit produitSelectionne = tableview.getSelectionModel().getSelectedItem();

    if (produitSelectionne != null) {
        TextInputDialog dialog = new TextInputDialog("1");
        dialog.setTitle("Saisir la Quantité");
        dialog.setHeaderText("Entrez la quantité du produit");
        dialog.setContentText("Quantité:");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(quantiteString -> {
            try {
                int quantite_panier = Integer.parseInt(quantiteString);
                if (quantite_panier <= 0) {
                    System.out.println("La quantité doit être supérieure à zéro.");
                } else {
                    // Utilisation d'un utilisateur factice aux fins de test
                   User user = new User();
                    user.setId_user(20); // ID de l'utilisateur factice
                     
                     

                    panierService.ajout(produitSelectionne, aold, quantite_panier);
                    System.out.println("Produit ajouté au panier avec succès !");
                    
                  //Twilo API              //Twilo API
        /**/      String recipientPhoneNumber = "+21651791220"; // Replace with the recipient's phone number
        /**/  String messageText = "Votre commande est ajoutée avec succès au panier"; // Provide your message
        /**/ Exemple.sendTwilioSMS(recipientPhoneNumber, messageText);
      
                }
            } catch (NumberFormatException e) {
                System.out.println("Veuillez saisir une quantité valide (un nombre entier).");
            }
        });}
}



@FXML
private void afficherTotalMontant(ActionEvent event) {
   // User user = new User();
        //            user.setId_user(20);
    if (aold != null) {
        int id_user = aold.getId_user();
        double totalMontant = panierService.totalmontantPanier(id_user);
        totalMontantLabel.setText("Total du panier : " + totalMontant + " DinarTunisien");
    } else {
        totalMontantLabel.setText("Utilisateur non connecté");
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
    // Méthode pour charger et afficher l'image de l'offre

    public void initialize() {
    // ... votre code existant ...

    tableview.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
        if (newSelection != null) {
            String imagePath = newSelection.getImage();
            if (imagePath != null && !imagePath.isEmpty()) {
                Image image = new Image("file:" + imagePath);
                image_View.setImage(image);
            } else {
                image_View.setImage(null);
            }
        } else {
            image_View.setImage(null);
        }
    });

    // ... votre code existant ...
    }
    
@FXML
private void sommePrixParUtilisateurAvecRemise(ActionEvent event) {
  

    if (aold != null) {
        int id_user = aold.getId_user();
        double totalMontant = panierService.totalmontantPanier(id_user);

        if (totalMontant <= 1000) {
            totalMontantLabel.setText("Total du panier : " + totalMontant + " Dinar Tunisien");
            
            // Afficher un message pour indiquer que le montant est inférieur ou égal à 1000.
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Montant inférieur ou égal à 1000");
            alert.setContentText("Aucune remise appliquée.");
            alert.showAndWait();
        } else {
            double remise = 0.10 * totalMontant;
            double montantAvecRemise = totalMontant - remise;

            totalMontantLabel.setText("Total du panier avec remise : " + montantAvecRemise + " Dinar Tunisien");
            
            // Afficher un message pour indiquer que le montant dépasse 1000 et qu'une remise a été appliquée.
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Montant supérieur à 1000");
            alert.setContentText("Remise de 10 % appliquée.");
            alert.showAndWait();
        }
    } else {
        totalMontantLabel.setText("Utilisateur non connecté");
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
    private void Produit(ActionEvent event) {
    
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
    private void categorie(ActionEvent event) {
    
    try {
    Parent root = FXMLLoader.load(getClass().getResource("Gestion categorie.fxml"));
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
















      

