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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import service.ServiceReclamation;

/**
 * FXML Controller class
 *
 * @author LENOVO GAMING
 */
public class Conf_suppRController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button ouiSR;
    @FXML
    private Button nonSR;
    int id;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
   public void initS(int idReclamation){
         id = idReclamation;          
   }
    
    public void suppR(){
                ServiceReclamation serviceReclamation = new ServiceReclamation();
        serviceReclamation.supprimerR(id);
        
        Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
        confirmation.setTitle("Suppression réussie");
        confirmation.setHeaderText(null);
        confirmation.setContentText("La réclamation a été suprimée avec succès.");
        confirmation.showAndWait();
        
        Stage stage = (Stage) ouiSR.getScene().getWindow();
        stage.close();
    }
    
    
    @FXML
private void retourHome_rec(ActionEvent event) throws IOException {
    
    Scene scene = ((Node) event.getSource()).getScene();
    Stage stage = (Stage) scene.getWindow();
    stage.close();
}
}
