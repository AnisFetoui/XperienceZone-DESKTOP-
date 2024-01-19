/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import classes.Evenement;
import classes.Ticket;
import service.EventService;
import service.Pdfgenerator;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Interface.MyListener;

/**
 * FXML Controller class
 *
 * @author the_old_is_back
 */
public class evenementviewController implements Initializable {

    @FXML
    private TextField rechercher;
    @FXML
    private VBox v_box;
    @FXML
    private Label nom;
    @FXML
    private ImageView imgv;
    @FXML
    private Label description;
    @FXML
    private Label lieu;
    @FXML
    private Label heure;
    @FXML
    private HBox hboxcamping;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private AnchorPane anchorforedit;
    Evenement currentevenement;
    private Parent fxml;
    private List<Evenement> events = new ArrayList<>();
    private Image image;
    private MyListener myListener;
    @FXML
    private ImageView fruitImg;
    /**
     * Initializes the controller class.
     */
    
    
         private List<Evenement> getDatasearch(String se) throws SQLException {
         List<Evenement> evenements = new ArrayList<>();
          EventService s = new EventService();
        Evenement E;

        for (int i = 0; i < s.rechercher(se).size(); i++) {
            Evenement get = s.rechercher(se).get(i);  
            
            E = new Evenement();
            E.setId_event(get.getId_event());
            E.setNom_event(get.getNom_event());
            E.setDescript(get.getDescript());
            E.setDate_event(get.getDate_event());
            E.setHeure_event(get.getHeure_event());
            E.setLieu_event(get.getLieu_event());
            E.setNb_participant(get.getNb_participant());
            E.setImage(get.getImage());
            E.setOrganisateur(get.getOrganisateur());
         
            evenements.add(E);
        }
    
         return evenements;    }
         
         
         
      private List<Evenement> getData() throws SQLException {
      
            List<Evenement> evenements = new ArrayList<>();
          EventService s = new EventService();
        Evenement e;

        for (int i = 0; i < s.afficherevent().size(); i++) {
            Evenement get = s.afficherevent().get(i);
            
            
            e = new Evenement();
            e.setId_event(get.getId_event());
            e.setNom_event(get.getNom_event());
            e.setDescript(get.getDescript());
            e.setDate_event(get.getDate_event());
            e.setHeure_event(get.getHeure_event());
            e.setLieu_event(get.getLieu_event());
            e.setNb_participant(get.getNb_participant());
            e.setImage(get.getImage());
            e.setOrganisateur(get.getOrganisateur());
           
        
           
            
         
            evenements.add(e);
        }
    

      
        return evenements;
    }

    private void setChosen(Evenement event) {
        currentevenement=event;
        nom.setText(event.getNom_event());
        description.setText(""+ event.getDescript());
       lieu.setText(""+event.getLieu_event());
       heure.setText(""+event.getHeure_event());
      
      
        v_box.setStyle("-fx-background-color: #a9cb56;\n" +
                "    -fx-background-radius: 30;");
          Image image = new Image("/util/"+event.getImage());
       
       fruitImg.setImage(image);
                 EventService se = new EventService();
                     System.out.println(se.getNomEvenementById(event.getId_event()).get(0).getNom_event());
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          anchorforedit.setVisible(false);
        try {
         

            // TODO
            afficher();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    } 
    
    
    
       public void afficher() throws SQLException
    {
               events.addAll(getData());
        if (events.size() > 0) {
            setChosen(events.get(0));
            myListener = new MyListener() {
           

            

          

                @Override
                public void onClickListener(Evenement Bac) {
                            
                      setChosen(Bac);
                    
                      
                }

                @Override
                public void onClickListener(Ticket Ticket) {
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < events.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("oneevenementview.fxml"));
             
                AnchorPane anchorPane = fxmlLoader.load();
                oneevenementviewController oneevenementviewController = fxmlLoader.getController();
                oneevenementviewController.setData(events.get(i),myListener);

                if (column ==2 ) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
       
       
       

    @FXML
    private void search(KeyEvent event) throws SQLException {
            grid.getChildren().clear();
        events.clear();
            events.addAll(getDatasearch(rechercher.getText()));
        if (events.size() > 0) {
            setChosen(events.get(0));
            myListener = new MyListener() {
           

            

          

             
                @Override
                public void onClickListener(Evenement Bac) {
                                          setChosen(Bac);

                }

                @Override
                public void onClickListener(Ticket Ticket) {
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < events.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/edu/devapps/Interface/oneevenementview.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                oneevenementviewController oneevenementviewController = fxmlLoader.getController();
                oneevenementviewController.setData(events.get(i),myListener);

                if (column == 2) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void gototransport(MouseEvent event) throws IOException {
         FXMLLoader load = new FXMLLoader(getClass().getResource("/edu/devapps/Interface/ticketview.fxml"));
                           Parent root =load.load();
                           ticketviewController c2=  load.getController();
                           Scene ss= new Scene(root);
                           Stage se= new Stage();
                           se=(Stage)((Node)event.getSource()).getScene().getWindow();
                           se.setScene(ss);
                           se.show();
    }

  

    @FXML
    private void modifierevenement(ActionEvent event) throws IOException {
                 anchorforedit.setVisible(true);
           
                            fxml = FXMLLoader.load(getClass().getResource("/edu/devapps/Interface/modifierevent.fxml"));
                         FXMLLoader load = new FXMLLoader(getClass().getResource("/edu/devapps/Interface/modifierevent.fxml"));
                           Parent root =load.load();
                           modifiereventController c2=  load.getController();
                           c2.setData(currentevenement);
                          fxml=root;
                            anchorforedit.getChildren().removeAll();
                             anchorforedit.getChildren().setAll(fxml);
                              anchorforedit.setVisible(true);   
    
    }

    @FXML
    private void supprimerevenement(ActionEvent event) throws IOException {
         EventService s = new EventService();
        
        Evenement r = new Evenement();
        r.setId_event(currentevenement.getId_event());
        s.supprimerevenement(r);
        Alert a = new Alert(Alert.AlertType.INFORMATION, "your event has been deleted");
                a.show();
                           FXMLLoader load = new FXMLLoader(getClass().getResource("/edu/devapps/Interface/evenementview.fxml"));
                           Parent root =load.load();
                           evenementviewController c2=  load.getController();
                           Scene ss= new Scene(root);
                           Stage se= new Stage();
                           se=(Stage)((Node)event.getSource()).getScene().getWindow();
                           se.setScene(ss);
                           se.show();
    
    }

    @FXML
    private void ajouterticket(ActionEvent event) throws IOException {
          anchorforedit.setVisible(true);
           Ticket t  = new Ticket();
           t.setId_event(currentevenement.getId_event());
           t.setImage(currentevenement.getImage());
                fxml = FXMLLoader.load(getClass().getResource("/edu/devapps/Interface/ajouterticket.fxml"));
                         FXMLLoader load = new FXMLLoader(getClass().getResource("/edu/devapps/Interface/ajouterticket.fxml"));
                           Parent root =load.load();
                           ajouterticketController c2=  load.getController();
                           c2.setData(t);
                          fxml=root;
                            anchorforedit.getChildren().removeAll();
                             anchorforedit.getChildren().setAll(fxml);
                              anchorforedit.setVisible(true); 
    }

    @FXML
    private void ajouterevenement(ActionEvent event) throws IOException {
            anchorforedit.setVisible(true);
           
                fxml = FXMLLoader.load(getClass().getResource("/edu/devapps/Interface/ajouterevenement.fxml"));
                         FXMLLoader load = new FXMLLoader(getClass().getResource("/edu/devapps/Interface/ajouterevenement.fxml"));
                           Parent root =load.load();
                           ajouterevenementController c2=  load.getController();
                          fxml=root;
                            anchorforedit.getChildren().removeAll();
                             anchorforedit.getChildren().setAll(fxml);
                              anchorforedit.setVisible(true); 
    }

    @FXML
    private void export(MouseEvent event) {
              Pdfgenerator d = new Pdfgenerator();
        d.generateExcel();
        
    }

    @FXML
    private void exportpdf(MouseEvent event) {
             Pdfgenerator d = new Pdfgenerator();
        d.generatePdfe();
    }

    
}
