/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import classes.Evenement;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import Interface.MyListener;

/**
 * FXML Controller class
 *
 * @author the_old_is_back
 */
public class oneevenementviewController implements Initializable {

    @FXML
    private Label nom;
    @FXML
    private Label description;
    @FXML
    private Label date;
    @FXML
    private Label heure;
    private Label etat;
    @FXML
    private Label nb_participant;

    private Evenement bac;
    private MyListener myListener;
    @FXML
    private Label lieu;
    @FXML
    private ImageView photo;
    @FXML
    private AnchorPane anchorcolor;
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onclick(MouseEvent event) {
            myListener.onClickListener(bac);
                                

    }
    
     public void setData(Evenement newbac, MyListener myListener) {
        this.bac = newbac;
        this.myListener = myListener;
       
        nom.setText(newbac.getNom_event());
        description.setText(newbac.getDescript());
        date.setText(""+newbac.getDate_event());
        heure.setText(""+newbac.getHeure_event());
        lieu.setText(""+newbac.getLieu_event());
        nb_participant.setText(""+newbac.getNb_participant());
        Image image = new Image("/util/"+newbac.getImage());
       photo.setImage(image);
         
    }
    
}
