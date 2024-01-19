/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

//import view.reclamation.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
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
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import classes.Reclamation;
import service.ServiceReclamation;

/**
 * FXML Controller class
 *
 * @author LENOVO GAMING
 */
public class Home_recController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
 slider.setTranslateX(0);
 menu.setVisible(true);
                menuclose.setVisible(false);
                

    }    

    @FXML
private void ajoutR(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("ajout_rec.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
}


    @FXML
private void retourHome(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("AllAdmin.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
}
@FXML
private ListView<Reclamation> list_rec;
@FXML
private Button supprimerRB;
@FXML
private Button trait;
@FXML
    private VBox slider;
    @FXML
    private Label menu;
    @FXML
    private Label menuclose;
    


@FXML
public void actualiserListViewR(ActionEvent event) {
    
    ServiceReclamation serviceReclamation = new ServiceReclamation();
    List<Reclamation> reclamations = serviceReclamation.afficher();

    
    ObservableList<Reclamation> observableReclamations = FXCollections.observableArrayList(reclamations);
    list_rec.getItems().clear();
    
    list_rec.setItems(observableReclamations);
}

 
public void modifierReclamation(ActionEvent event) throws IOException {
    
    Reclamation reclamationSelectionnee = list_rec.getSelectionModel().getSelectedItem();

    if (reclamationSelectionnee != null) {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("modif_rec.fxml"));
        Parent root = loader.load();
        Modif_recController controller = loader.getController();
        controller.initData(reclamationSelectionnee);

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.showAndWait(); 

        
    } else {
       
    }
}


public void supprimerReclamation(ActionEvent event) throws IOException {
    
    Reclamation reclamationSelectionnee = list_rec.getSelectionModel().getSelectedItem();

    if (reclamationSelectionnee != null) {
        
        int idReclamation = reclamationSelectionnee.getIdR();
        
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("conf_suppR.fxml"));
        Parent root = loader.load();
        Conf_suppRController controller = loader.getController();
        controller.initS(idReclamation);

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.showAndWait(); 

 
    } else {
       
    }
}

    @FXML
    private void trait_home(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("home_trait.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
}
    
    
     @FXML
    private Hyperlink voirStatistiquesButton;

    @FXML
    private void handleVoirStatistiquesButton(ActionEvent event) throws IOException {
        // Chargez la nouvelle interface des statistiques
        FXMLLoader loader = new FXMLLoader(getClass().getResource("stats.fxml"));
        Parent root = loader.load();

        // Obtenez le contrôleur de la nouvelle interface
        StatsController StatsController = loader.getController();

        // Calculez les statistiques et mettez-les à jour dans le contrôleur de statistiques
        ServiceReclamation stats = new ServiceReclamation();
        Map<Integer, Integer> statistics = stats.getReclamationStatistics();
        StringBuilder resultText = new StringBuilder("Statistiques des réclamations :\n\n");
        for (Map.Entry<Integer, Integer> entry : statistics.entrySet()) {
            int typeRec = entry.getKey();
            int count = entry.getValue();
            resultText.append(convertirTypeReclamationInverse(typeRec)).append(":     ").append(count).append("  réclamations\n");
        }
        StatsController.setStatisticsText(resultText.toString());
        StatsController.updateChart(statistics);

        // Affichez la nouvelle interface des statistiques dans une nouvelle fenêtre
        Stage statisticsStage = new Stage();
        statisticsStage.setScene(new Scene(root));
        statisticsStage.setTitle("Statistiques des réclamations");
        statisticsStage.show();
    }
    
        public String convertirTypeReclamationInverse(int typeRec) {
    
    String[] types = {"Réclamation liés aux produits", "Réclamation liés aux évènements", "Réclamation liés aux activités"};

    if (typeRec >= 1 && typeRec <= types.length) {
        return types[typeRec - 1]; 
    } else {
        return "Type de réclamation non valide"; 
    }
}
 @FXML
private void onmenuclicked(MouseEvent event) {
    TranslateTransition slide = new TranslateTransition(Duration.seconds(0.4), slider);
    slide.setToX(-210);
    slide.play();
    menu.setVisible(false);
    menuclose.setVisible(true);
}

@FXML
private void onmenuclickedclose(MouseEvent event) {
    TranslateTransition slide = new TranslateTransition(Duration.seconds(0.4), slider);
    slide.setToX(0);
    slide.play();
    menuclose.setVisible(false);
    menu.setVisible(true);
}
        }

