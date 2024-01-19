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
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
public class Conf_suppTController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
      @FXML
    private Button ouiST;
    @FXML
    private Button nonST;
    int id;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
       public void initST(int idTrait){
         id = idTrait;          
   }
    
       
          public void suppT(){
                ServiceReclamation serviceReclamation = new ServiceReclamation();
        serviceReclamation.supprimerT(id);
        
        Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
        confirmation.setTitle("Suppression réussie");
        confirmation.setHeaderText(null);
        confirmation.setContentText("Le traitement a été suprimée avec succès.");
        confirmation.showAndWait();
        
        Stage stage = (Stage) ouiST.getScene().getWindow();
        stage.close();
        
        //        Stage stage = new Stage();
//        stage.initModality(Modality.APPLICATION_MODAL);
//        stage.setScene(new Scene(root));
//        stage.showAndWait(); 
//        ServiceReclamation serviceReclamation = new ServiceReclamation();
//        serviceReclamation.supprimerT(idTrait);
//        
//                Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
//        confirmation.setTitle("Suppression réussie");
//        confirmation.setHeaderText(null);
//        confirmation.setContentText("Le traitement a été suprimée avec succès.");
//        confirmation.showAndWait();
    }
          
              @FXML
private void retourHome_trait(ActionEvent event) throws IOException {
    
    Scene scene = ((Node) event.getSource()).getScene();
    Stage stage = (Stage) scene.getWindow();
    stage.close();
}
}
