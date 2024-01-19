/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.FX;
import classes.Channel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ktari
 */
public class FXMLController  {
/*
    @FXML
    private Button ajout;
    @FXML
    private TextField nomdechannel;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public Button getAjout() {
        return ajout;
    }

    public TextField getNomdechannel() {
        return nomdechannel;
    }

    public void setAjout(Button ajout) {
        this.ajout = ajout;
    }

    public void setNomdechannel(TextField nomdechannel) {
        this.nomdechannel = nomdechannel;
    }

    
    
    @FXML
    private void buttonajout(ActionEvent event) {
        String nom = ajout.getText();
        ajout.setText ("le nom"+ nom   );
        // int id = Integer.parseInt( this.id.getText());
        
        ServiceChannel cs = new ServiceChannel();
        Channel c = new Channel(nom);
        cs.ajouter(c);
        
          try {
            
            FXMLLoader loader = new FXMLLoader(getClass().
                getResource("Afficher.fxml"));
            Parent root = loader.load();
        FXMLController ac = loader.getController();
       // ac.SetRnom(nomdechannel.getText());
      
     //   ac.setRnom(cs.affihcer().toString());
        nomdechannel.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
    

    @FXML
    private void saisir(ActionEvent event) {
     nomdechannel.setText("");
    }

    */
}
