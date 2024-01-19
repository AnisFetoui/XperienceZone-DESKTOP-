/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

//import controllerevent.*;
//import com.itextpdf.text.Document;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.pdf.PdfWriter;
import classes.Categories;
import classes.Evenement;
import classes.Ticket;
import service.TicketService;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * FXML Controller class
 *
 * @author the_old_is_back
 */
public class ticketviewController implements Initializable {

    @FXML
    private TextField rechercher;
    @FXML
    private VBox chosenFruitCard;
    @FXML
    private Label num_ticket;
    @FXML
    private ImageView fruitImg;
    @FXML
    private Label prix;
    @FXML
    private Label categorie;
    @FXML
    private HBox hboxcamping;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private AnchorPane anchorforedit;
    Ticket currentticket;
   private Parent fxml;
   private List<Ticket> tickets = new ArrayList<>();
    private Image image;
    private MyListener myListener;
    @FXML
    private ImageView fruitImg1;
    /**
     * Initializes the controller class.
     */
    
     private List<Ticket> getData() throws SQLException {
      
            List<Ticket> tickets = new ArrayList<>();
          TicketService s = new TicketService();
        Ticket bac1;
     

        for (int i = 0; i < s.afficherticket().size(); i++) {
            Ticket t = s.afficherticket().get(i);
            
            
            bac1 = new Ticket();
             bac1.setId_ticket(t.getId_ticket());
            bac1.setImage(t.getImage());
            bac1.setPrix(t.getPrix());
            bac1.setNum_ticket(t.getNum_ticket());
            bac1.setCategorie(t.getCategorie());
              bac1.setId_event(t.getId_event());
            
         
            tickets.add(bac1);
        }
    

      
        return tickets;
    }

    private void setChosenCamping(Ticket event) {
        currentticket=event;
        num_ticket.setText(""+event.getNum_ticket());
        prix.setText(""+ event.getPrix());

      
      
        chosenFruitCard.setStyle("-fx-background-color: #a9cb56;\n" +
                "    -fx-background-radius: 30;");
                 Image image = new Image("/utils/"+event.getImage());
       fruitImg.setImage(image);
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
               tickets.addAll(getData());
        if (tickets.size() > 0) {
            setChosenCamping(tickets.get(0));
            myListener = new MyListener() {
       
                @Override
                public void onClickListener(Evenement Bac) {
                }

                @Override
                public void onClickListener(Ticket Ticket) {
                    setChosenCamping(Ticket);

                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < tickets.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("oneticketview.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                oneticketviewController oneticketviewController = fxmlLoader.getController();
                oneticketviewController.setData(tickets.get(i),myListener);

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
    private void search(KeyEvent event) {
    }


    @FXML
    private void modifierticket(ActionEvent event) throws IOException {
        anchorforedit.setVisible(true);
           
                            fxml = FXMLLoader.load(getClass().getResource("modifierticket.fxml"));
                         FXMLLoader load = new FXMLLoader(getClass().getResource("modifierticket.fxml"));
                           Parent root =load.load();
                           modifierticketController c2=  load.getController();
                           c2.setData(currentticket);
                          fxml=root;
                            anchorforedit.getChildren().removeAll();
                             anchorforedit.getChildren().setAll(fxml);
                              anchorforedit.setVisible(true);   
    }

    @FXML
    private void supprimerticket(ActionEvent event) throws IOException {
           TicketService s = new TicketService();
        
        Ticket r = new Ticket();
        r.setId_ticket(currentticket.getId_ticket());
        s.supprimerticket(r);
        Alert a = new Alert(Alert.AlertType.INFORMATION, "your ticket has been deleted");
                a.show();
                           FXMLLoader load = new FXMLLoader(getClass().getResource("ticketview.fxml"));
                           Parent root =load.load();
                           ticketviewController c2=  load.getController();
                           Scene ss= new Scene(root);
                           Stage se= new Stage();
                           se=(Stage)((Node)event.getSource()).getScene().getWindow();
                           se.setScene(ss);
                           se.show();
    }

    @FXML
    private void gototransport(MouseEvent event) throws IOException {
        
        
          FXMLLoader load = new FXMLLoader(getClass().getResource("evenementview.fxml"));
                           Parent root =load.load();
                           evenementviewController c2=  load.getController();
                           Scene ss= new Scene(root);
                           Stage se= new Stage();
                           se=(Stage)((Node)event.getSource()).getScene().getWindow();
                           se.setScene(ss);
                           se.show();
    }

    @FXML
    private void PDF(ActionEvent event) throws FileNotFoundException, DocumentException {

    if (currentticket != null) {
        // Obtenez les informations du ticket actuellement sélectionné
     
        int num_ticket = currentticket.getNum_ticket();
        float prix = currentticket.getPrix();
        Categories categorie  = currentticket.getCategorie();
        // Générez le PDF avec les informations du ticket
        generatePDF( categorie , num_ticket, prix);    
    }
}

private void generatePDF( Categories categorie, int num_ticket, float prix) {
    Document document = new Document();

    try {
        PdfWriter.getInstance(document, new FileOutputStream("ticket.pdf"));
        document.open();

        // Ajoutez les informations du ticket au PDF
        document.add(new Paragraph(" Categorie : " + categorie));
        document.add(new Paragraph("Numéro de Ticket : " + num_ticket));
        document.add(new Paragraph("Prix : " + prix));

        document.close();
        System.out.println("Le fichier PDF a été généré avec succès.");
    } catch (DocumentException | FileNotFoundException e) {
        e.printStackTrace();
    }
  
  
  
}

    }
    

