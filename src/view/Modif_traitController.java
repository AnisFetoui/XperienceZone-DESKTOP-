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
import classes.Traitement;
import service.ServiceReclamation;

/**
 * FXML Controller class
 *
 * @author LENOVO GAMING
 */
public class Modif_traitController implements Initializable {

           @FXML
    private TextField refTM; 
    @FXML
    private DatePicker dateTM; 
    @FXML
    private ComboBox<String> typeRTM; 
    @FXML
    private ComboBox<String> statTM; 
    @FXML
    private TextArea resumeTM; 
    @FXML
    private Button modifMT;
    @FXML
    private Button annulerMT;       
     
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    typeRTM.getItems().removeAll(typeRTM.getItems());
    typeRTM.getItems().addAll("Réclamation liés aux produits", "Réclamation liés aux évènements", "Réclamation liés aux activités");
    statTM.getItems().removeAll(statTM.getItems());
    statTM.getItems().addAll("VALIDE",  "INVALIDE");
    }    
    
     ServiceReclamation serviceReclamation = new ServiceReclamation();
     private Traitement traitementSelectionnee;
     private int selectedTraitementIdR;
     private int selectedTraitementIdT;
     private int selectedTraitementIdU;
     
         
     public void initData(Traitement traitement) {
        traitementSelectionnee = traitement;
        
        

        
        refTM.setText(String.valueOf(traitementSelectionnee.getRefobj()));
        dateTM.setValue(traitementSelectionnee.getDateR().toLocalDate());
        typeRTM.setValue(convertirTypeReclamationInverse(traitementSelectionnee.getTypeR()));
        statTM.setValue(traitementSelectionnee.getStat());
        resumeTM.setText(traitementSelectionnee.getResume());
        selectedTraitementIdR = traitementSelectionnee.getIdrec();
        selectedTraitementIdT = traitementSelectionnee.getIdT();
        selectedTraitementIdU = traitementSelectionnee.getIdU();
        
    }
     
     
     
      @FXML
    private void validerModificationT() {
        
            if (traitementSelectionnee == null) {
        
        System.out.println("L'objet reclamationSelectionnee est null.");
        return;
    }

       if (statTM.getValue().isEmpty() || typeRTM.getValue().isEmpty() || refTM.getText().isEmpty() || resumeTM.getText().isEmpty() ||  dateTM.getValue() == null) {
    afficherAlerte("Tous les champs doivent être remplis");
    return;
}

   
   
try {
    int refObject1 = Integer.parseInt(refTM.getText());
    java.sql.Date dateREC = Date.valueOf(dateTM.getValue());
    int yearREC = dateREC.toLocalDate().getYear();

    if ( yearREC < 2022 || yearREC > 2023) {
        afficherAlerte("Veuillez entrer des dates comprises entre 2022 et 2023.");
        return;
    }
} catch (NumberFormatException e) {
    afficherAlerte("La référence de l'objet doit être un nombre entier.");
    return;
}  

        traitementSelectionnee.setRefobj(Integer.parseInt(refTM.getText()));
        traitementSelectionnee.setDateR(Date.valueOf(dateTM.getValue()));
        traitementSelectionnee.setTypeR(convertirTypeReclamation(typeRTM.getValue()));
        traitementSelectionnee.setStat(statTM.getValue());
        traitementSelectionnee.setResume(resumeTM.getText());
        traitementSelectionnee.setIdT(selectedTraitementIdT);
        traitementSelectionnee.setIdrec(selectedTraitementIdR);
        traitementSelectionnee.setIdU(selectedTraitementIdU);
        
        


        
        serviceReclamation.modifierT(traitementSelectionnee);
      

        
        Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
        confirmation.setTitle("Modification réussie");
        confirmation.setHeaderText(null);
        confirmation.setContentText("Le traitement a été modifiée avec succès.");
        confirmation.showAndWait();

        
        Stage stage = (Stage) modifMT.getScene().getWindow();
        stage.close();
    }
     
       @FXML
    private void annulerModification() {
        
        Stage stage = (Stage) annulerMT.getScene().getWindow();
        stage.close();
    }  
    
            @FXML
    private void annulerT(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("home_trait.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
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
    

            
}
