/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
/*
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfWriter;*/
import classes.User;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import classes.inscription;
import service.ServiceUser;
import service.serviceinscription;

/**
 * FXML Controller class
 *
 * @author Med Aziz
 */
public class TicketController implements Initializable {
    
    

    @FXML
    private Button save;
    @FXML
    private Button annuler;
    @FXML
    private Label nomorganisateur;
    @FXML
    private Label nomactivite;
    @FXML
    private Label date;
    @FXML
    private Label heuredereservation;
    @FXML
    private Label datedereservation;
    @FXML
    private Label nombreper;
    @FXML
    private Label prixtotale;
    String apiKey = "0b433ac544700c94e6945a0a9384ebd5";
    @FXML
    private Button telecharger;
    double priab ;
    int nbrtick ;
    int userid_in;
    int actid_in;
    LocalDate datein;
    LocalTime timein;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

        // TODO
    }    



    void setData(String nom, String org, String dateac, String dateres, String heureres, String nbrdepersonne, String freeab,int id) {
     nbrtick = Integer.parseInt(nbrdepersonne);
     priab = Double.parseDouble(freeab);
     //ServiceUser su = new ServiceUser();
        //  User aold = su.readById(ConnexionUserController.id_modif);
          //iduserconnected = aold.getId_user();
     userid_in = 40;
     actid_in = id;
        datein = LocalDate.parse(dateres);
        timein = LocalTime.parse(heureres);
        
     
    nomorganisateur.setText(org);
    nomactivite.setText(nom);
    date.setText(dateac);
    heuredereservation.setText(dateres);
    datedereservation.setText(heureres);
    nombreper.setText(nbrdepersonne);
    prixtotale.setText(freeab);
     
        
    }

    @FXML
    private void enregistrement(ActionEvent event) {
        inscription in = new inscription();
        in.setDate_ins(datein);
        in.setHeure_ins(timein);
        in.setFrait_abonnement(priab);
        in.setNbr_tickes(nbrtick);
        in.setUser_id(userid_in);
        in.setActivite_id(actid_in);
        serviceinscription s= new serviceinscription();
        s.Inscrire(in);
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert");
        alert.setHeaderText("Inscription made succesfully");

        ButtonType okButton = new ButtonType("return", ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(okButton);
        alert.showAndWait();
        
        
    }

    @FXML
    private void cancel(ActionEvent event) {
        
          try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("activité.fxml"));
            Parent root = loader.load();

            // Create a new stage
            Stage stage = new Stage();
            stage.setTitle("Acceuil");
            stage.setFullScreen(true);
            stage.setScene(new Scene(root));

            // Show the new stage
            stage.show();

            // Close the current stage (if needed)
            Stage currentStage = (Stage) annuler.getScene().getWindow();
            currentStage.close();
        } catch (Exception e) {
            e.printStackTrace(); // Handle any exceptions here
        }
        
        
        
    }

    @FXML
    private void download(ActionEvent event) {
        /*String nom = nomactivite.getText();
        String organisateur = nomorganisateur.getText();
        generatepdf(nom , organisateur);
    }
    private void generatepdf(String nom ,String organisateur){
        Document document = new Document();
        try{
            
        PdfWriter.getInstance(document, new FileOutputStream("ticket.pdf")); 
        document.open();
        document.add(new Paragraph("Nom activité : "+nom));
        document.add(new Paragraph("Organisateur : "+organisateur));
        document.close();
        //System.out.println("le fichier pdf est enregistrer");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("notification");
        alert.setHeaderText("le fichier pdf est enregistrer");

        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(okButton);
        alert.showAndWait();
        }catch(DocumentException | FileNotFoundException e){
        e.printStackTrace();
        }*/
        
    }
    
}
