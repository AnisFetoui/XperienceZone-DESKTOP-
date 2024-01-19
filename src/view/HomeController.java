package view;

import pi.controller.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HomeController implements Initializable {
     private Parent fxml;
    @FXML
    private AnchorPane root;
    private Label menu;
    @FXML
    private Label menuclose;
    @FXML
    private VBox slider;

      
    @FXML
    void accueil() {
        try{
            fxml= FXMLLoader.load(getClass().getResource("Dashboard.fxml")); 
            root.getChildren().removeAll();   
            root.getChildren().setAll(fxml); 

        }catch (IOException e){
       e.printStackTrace();
           }
    }

    @FXML
    void channel() {
        try{
            fxml= FXMLLoader.load(getClass().getResource("Channel.fxml"));
            root.getChildren().removeAll(); 
            root.getChildren().setAll(fxml);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    void inbox() {
        try{
            fxml= FXMLLoader.load(getClass().getResource("Inbox.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
      @FXML
    void GotoAcceuil (){
        try{
            fxml= FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);

        }catch (IOException e){
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
        menu.setVisible(true);
        menuclose.setVisible(false);
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
            
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {  //initialize awel action tsir hatin hne dashbord ahyka f path
      
        try{
            fxml= FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
       
        }catch (IOException e){
            e.printStackTrace();
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
    private void produit(ActionEvent event) {
    
    try {
    Parent root = FXMLLoader.load(getClass().getResource("Homeproduit.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();} catch (IOException ex) {
            Logger.getLogger(GestionProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }}
    
}
