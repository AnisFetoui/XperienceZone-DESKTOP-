/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

//import view.reclamation.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import classes.Reclamation;
import service.ServiceReclamation;

/**
 * FXML Controller class
 *
 * @author LENOVO GAMING
 */
public class Modif_recController implements Initializable {


    @FXML
    private TextField nomM;
    @FXML
    private TextField prenomM;
    @FXML
    private TextField emailM;
    @FXML
    private DatePicker dateincM;
    @FXML
    private DatePicker dateM;
    @FXML
    private ComboBox<String> typeRM;
    @FXML
    private TextField refRM;
    @FXML
    private TextArea detM;
    
    @FXML
    private Button modifierButt;
    @FXML
    private Button annulerMR;

    ServiceReclamation serviceReclamation = new ServiceReclamation();
    private Reclamation reclamationSelectionnee;
    private int idUM ;
    private int idRM;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    typeRM.getItems().removeAll(typeRM.getItems());
    typeRM.getItems().addAll("Réclamation liés aux produits", "Réclamation liés aux évènements", "Réclamation liés aux activités");
    }    

 public void initData(Reclamation reclamation) {
        reclamationSelectionnee = reclamation;
        
        

       
        refRM.setText(String.valueOf(reclamation.getRefObject()));
        typeRM.setValue(convertirTypeReclamationInverse(reclamation.getTypeRec())); 
        dateM.setValue(reclamation.getDateREC().toLocalDate()); 
        detM.setText(reclamation.getDetails());
        idUM = reclamation.getIdU();
        idRM = reclamation.getIdR();
    }
 
     @FXML
    private void validerModification() {
        
            if (reclamationSelectionnee == null) {
        
        System.out.println("L'objet reclamationSelectionnee est null.");
        return;
    }

if ( typeRM.getValue().isEmpty() || refRM.getText().isEmpty() || detM.getText().isEmpty()  || dateM.getValue() == null) {
    afficherAlerte("Tous les champs doivent être remplis");
    return;
}

    
   
try {
    int refObject1 = Integer.parseInt(refRM.getText());   
    java.sql.Date dateREC = Date.valueOf(dateM.getValue());
    int yearREC = dateREC.toLocalDate().getYear();

    if ( yearREC < 2022 || yearREC > 2023) {
        afficherAlerte("Veuillez entrer des dates comprises entre 2022 et 2023.");
        return;
    }
} catch (NumberFormatException e) {
    afficherAlerte("La référence de l'objet doit être un nombre entier.");
    return;
}
       
        reclamationSelectionnee.setIdR(idRM);
        reclamationSelectionnee.setIdU(idUM);
        reclamationSelectionnee.setRefObject(Integer.parseInt(refRM.getText()));
        reclamationSelectionnee.setTypeRec(convertirTypeReclamation(typeRM.getValue()));
        reclamationSelectionnee.setDateREC(Date.valueOf(dateM.getValue()));
        reclamationSelectionnee.setDetails(detM.getText());

       
        serviceReclamation.modifierR(reclamationSelectionnee);
      

        
        Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
        confirmation.setTitle("Modification réussie");
        confirmation.setHeaderText(null);
        confirmation.setContentText("La réclamation a été modifiée avec succès.");
        confirmation.showAndWait();

        
        Stage stage = (Stage) modifierButt.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void annulerModification() {
        
        Stage stage = (Stage) annulerMR.getScene().getWindow();
        stage.close();
    }
    
    

    
    
    public String convertirTypeReclamationInverse(int typeRec) {
    
    String[] types = {"Réclamation liés aux produits", "Réclamation liés aux évènements", "Réclamation liés aux activités"};

    if (typeRec >= 1 && typeRec <= types.length) {
        return types[typeRec - 1]; 
    } else {
        return "Type de réclamation non valide"; 
    }
}
    
        public int convertirTypeReclamation(String typeRec) {
     
        String[] types = {"Réclamation liés aux produits", "Réclamation liés aux évènements", "Réclamation liés aux activités"}; // Correspondance des types
        for (int i = 0; i < types.length; i++) {
            if (types[i].equals(typeRec)) {
                return i + 1; 
            }
        }
        return 0; 
    }
        
            public void afficherAlerte(String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}
    
    public boolean isValidEmail(String email) {
    
    return email.contains("@") && email.contains(".");
}
}
    

