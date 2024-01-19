/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

//import view.reclamation.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import classes.Reclamation;
import classes.Traitement;
import service.ServiceReclamation;

/**
 * FXML Controller class
 *
 * @author LENOVO GAMING
 */
public class Home_traitController implements Initializable {

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
private ListView<Reclamation> listNT;
        @FXML
private ListView<Traitement> listT;
            @FXML
    private Label menu;
    @FXML
    private Label menuclose;
    @FXML
    private VBox slider;
 

    @FXML
    private void rec_home(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("home_rec.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
}
    
    @FXML
    private void traiter(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("ajout_trait.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
}
    
    
    
    @FXML
public void actualiserListViewT(ActionEvent event) {
    
    ServiceReclamation serviceReclamation = new ServiceReclamation();
    List<Traitement> traitement = serviceReclamation.afficherT();

    
    ObservableList<Traitement> observableTraitement = FXCollections.observableArrayList(traitement);
    listT.getItems().clear();
    
    listT.setItems(observableTraitement);
}



@FXML
public void actualiserListViewNT(ActionEvent event) {
    
    ServiceReclamation serviceReclamation = new ServiceReclamation();
    List<Reclamation> reclamations = serviceReclamation.afficher();

    
    ObservableList<Reclamation> observableReclamations = FXCollections.observableArrayList(reclamations);
    listNT.getItems().clear();
    
    listNT.setItems(observableReclamations);
}
    

//  private SharedData sharedData;
//
//    public void setSharedData(SharedData sharedData) {
//        this.sharedData = sharedData;
//    }

    


    @FXML
    public void traiterReclamation() throws IOException {
 Reclamation reclamationSelectionnee = listNT.getSelectionModel().getSelectedItem();
//            sharedData.setSelectedReclamation(reclamationSelectionnee);

    if (reclamationSelectionnee != null) {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ajout_trait.fxml"));
        Parent root = loader.load();
        Ajout_traitController controller = loader.getController();
        controller.ouvrirAjoutTraitement(reclamationSelectionnee);
        

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.showAndWait(); 

    } 
    }

    
    
    public void modifierTraitement(ActionEvent event) throws IOException {
    
    Traitement traitementSelectionnee = listT.getSelectionModel().getSelectedItem();

    if (traitementSelectionnee != null) {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("modif_trait.fxml"));
        Parent root = loader.load();
        Modif_traitController controller = loader.getController();
        controller.initData(traitementSelectionnee);

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.showAndWait(); 

    } else {
        
    }
}
    
    
    
    public void supprimerTraitement(ActionEvent event) throws IOException {
    
    Traitement traitementSelectionnee = listT.getSelectionModel().getSelectedItem();

    if (traitementSelectionnee != null) {
        
        int idTrait = traitementSelectionnee.getIdT();

         FXMLLoader loader = new FXMLLoader(getClass().getResource("conf_suppT.fxml"));
        Parent root = loader.load();
        Conf_suppTController controller = loader.getController();
        controller.initST(idTrait);
        
          Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.showAndWait(); 

        
        
    } else {
       
    }
}
    
        @FXML
    private void vers_confSP(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("conf_suppT.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
}
    
  public void afficher_trait_select(MouseEvent event) throws IOException, SQLException {
        Reclamation reclamationSelectionnee = listNT.getSelectionModel().getSelectedItem();

    if (reclamationSelectionnee != null) {
        
        int idReclamation = reclamationSelectionnee.getIdR();
        chargerTraitements(idReclamation); 
    }
  }
    
    private void chargerTraitements(int idReclamation) throws SQLException {
    ServiceReclamation service = new ServiceReclamation();
    List<Traitement> traitementsReclamation = new ArrayList<>();
    Traitement traitement = service.getTraitementParIdReclamation(idReclamation);

    if (traitement != null && traitement instanceof Traitement) {
        traitementsReclamation.add(traitement);
      
    }

    ObservableList<Traitement> traitements = FXCollections.observableArrayList(traitementsReclamation);
    listT.setItems(traitements);
}

    
 @FXML
private void handleVoirStatistiquesButtonT(ActionEvent event) throws IOException {
    // Chargez la nouvelle interface des statistiques
    FXMLLoader loader = new FXMLLoader(getClass().getResource("statT.fxml"));
    Parent root = loader.load();

    // Obtenez le contrôleur de la nouvelle interface
    StatTController StatsTController = loader.getController();

    // Calculez les statistiques et mettez-les à jour dans le contrôleur de statistiques
    ServiceReclamation stats = new ServiceReclamation();
    Map<String, Integer> statistics = stats.getTraitementStatistics();

    // Transformez les statistiques en un format compatible avec la PieChart
    Map<String, Integer> pieChartData = new HashMap<>();

    for (Map.Entry<String, Integer> entry : statistics.entrySet()) {
        String stat = entry.getKey();
        int count = entry.getValue();

        pieChartData.put(stat, count);
    }

    // Mettez à jour la PieChart dans le contrôleur de statistiques

    StatsTController.updatePieChart(pieChartData);
    
       

    // Mettez à jour le texte dans l'espace de texte
    StringBuilder resultText = new StringBuilder("Statistiques des traitements :\n\n");
    for (Map.Entry<String, Integer> entry : statistics.entrySet()) {
        String stat = entry.getKey();
        int count = entry.getValue();
        resultText.append("Traitement ").append(stat).append("  :    ").append(count).append("   traitements\n");
    }
    StatsTController.setStatisticsTextT(resultText.toString());
    

    // Affichez la nouvelle interface des statistiques dans une nouvelle fenêtre
    Stage statisticsStage = new Stage();
    statisticsStage.setScene(new Scene(root));
    statisticsStage.setTitle("Statistiques des traitements");
    statisticsStage.show();
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
