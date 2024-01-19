/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

//import view.reclamation.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO GAMING
 */
public class HomeAdminController implements Initializable {

    @FXML
    private HBox navbar;
    @FXML
    private ImageView home_btn;
    @FXML
    private Label produit_btn;
    @FXML
    private Label evenement_btn;
    @FXML
    private Label activité_btn;
    @FXML
    private Label Reclamation_btn;
    @FXML
    private Label Channel_btn;
    @FXML
    private ImageView profile_btn;
    @FXML
    private Label dc_btn;
    @FXML
    private HBox bottom_content;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Go_Reclamations(MouseEvent event)throws IOException  {
    
        Parent root = FXMLLoader.load(getClass().getResource("home_rec.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
}}
