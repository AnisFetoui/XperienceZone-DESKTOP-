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
public class Ajout_traitController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    typerecT.getItems().removeAll(typerecT.getItems());
    typerecT.getItems().addAll("Réclamation liés aux produits", "Réclamation liés aux évènements/activités", "Réclamation liés aux problèmes de communication");
   statT.getItems().removeAll(statT.getItems());
    statT.getItems().addAll("VALIDE",  "INVALIDE");
    }    
    
    
        @FXML
    private void annulerT(ActionEvent event) throws IOException {
     Stage stage = (Stage) annulerTR.getScene().getWindow();
        stage.close();
}
    
    @FXML
    private TextField refT; 
    @FXML
    private DatePicker dateT; 
    @FXML
    private ComboBox<String> typerecT; 
    @FXML
    private ComboBox<String> statT; 
    @FXML
    private TextArea resumeT; 
    @FXML
    private Button annulerTR;
    
    
   @FXML
private Button ajouT; 

    private int selectedReclamationId;
    private int selectedReclamationIdU;
    
     public void ouvrirAjoutTraitement(Reclamation reclamationSelectionnee ) {
        
//        Reclamation R = sharedData.getSelectedReclamation();
        refT.setText(String.valueOf(reclamationSelectionnee.getRefObject()));
        dateT.setValue(reclamationSelectionnee.getDateREC().toLocalDate());
        typerecT.setValue(convertirTypeReclamationInverse(reclamationSelectionnee.getTypeRec()));
        selectedReclamationId = reclamationSelectionnee.getIdR();
        selectedReclamationIdU = reclamationSelectionnee.getIdU();
    }

    @FXML
    public void ajouterTraitement( ) {
       
     
        
        if (statT.getValue().isEmpty()  || typerecT.getValue().isEmpty() || refT.getText().isEmpty() || resumeT.getText().isEmpty() ||  dateT.getValue() == null) {
    afficherAlerte("Tous les champs doivent être remplis");
    return;
}

    

   
try {
    int refObject1 = Integer.parseInt(refT.getText());
    java.sql.Date dateREC = Date.valueOf(dateT.getValue());
    int yearREC = dateREC.toLocalDate().getYear();

    if ( yearREC < 2022 || yearREC > 2023) {
        afficherAlerte("Veuillez entrer des dates comprises entre 2022 et 2023.");
        return;
    }
} catch (NumberFormatException e) {
    afficherAlerte("La référence de l'objet doit être un nombre entier.");
    return;
}

        int refObject = Integer.parseInt(refT.getText());
        Date dateRec = Date.valueOf(dateT.getValue());
        int typeRec = convertirTypeReclamation(typerecT.getValue());
        String resume = resumeT.getText();
        String stat = statT.getValue();
        
       


        
        Traitement traitement = new Traitement();
        traitement.setIdrec(selectedReclamationId); 
        traitement.setIdU(41); 
        traitement.setRefobj(refObject);
        traitement.setDateR(dateRec);
        traitement.setTypeR(typeRec);
        traitement.setResume(resume);
        traitement.setStat(stat);
        ServiceReclamation service = new ServiceReclamation();
        service.ajouterT(traitement);
        
        
        
    
        Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
        confirmation.setTitle("Ajout réussi");
        confirmation.setHeaderText(null);
        confirmation.setContentText("Le traitement a été ajouté avec succès.");
        confirmation.showAndWait();
          


        
        Stage stage = (Stage) ajouT.getScene().getWindow();
        stage.close();
    }
    
    
        public int convertirTypeReclamation(String typeRec) {
        
        String[] types = {"Réclamation liés aux produits", "Réclamation liés aux évènements", "Réclamation liés aux activités"}; 
        for (int i = 0; i < types.length; i++) {
            if (types[i].equals(typeRec)) {
                return i + 1; 
            }
        }
        return 0; 
    }
        
        
            public String convertirTypeReclamationInverse(int typeRec) {
    
    String[] types = {"Réclamation liés aux produits", "Réclamation liés aux évènements", "Réclamation liés aux activités"};

    if (typeRec >= 1 && typeRec <= types.length) {
        return types[typeRec - 1]; 
    } else {
        return "Type de réclamation non valide"; 
    }
}
            
            
    private void afficherAlerte(String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}
    

}








