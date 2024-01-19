package pi.controller;

import service.AnalyseSentimentService;
import service.ChannelService;
import service.MessageService;
import service.VulgarLanguageDetectorService;
import classes.Channel;
import classes.Message;
import classes.Sentiment;
import classes.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import org.controlsfx.control.Notifications;
import service.ServiceUser;
import static view.ConnexionUserController.id_modif;


public class
InboxController implements Initializable {

    @FXML
    private ListView<String> channelListView;
    @FXML
    private TextArea messageTextArea;
    private String currentChannelName;
   ChannelService channelService=new ChannelService();
   MessageService messageService=new MessageService();
   VulgarLanguageDetectorService vulgarLanguageDetectorService=new VulgarLanguageDetectorService();
    AnalyseSentimentService analyseSentimentService=new AnalyseSentimentService();
    private User currentUser;

    @FXML
    private TextField txt_message;
    @FXML
    void sendOnAction() { 
        
  
          try {
            Alert alert;

            if ( txt_message.getText().isEmpty() ) {

                alert = new Alert(Alert.AlertType.ERROR);  //ctr saisir
                alert.setTitle("Error Message"); 
                alert.setHeaderText(null);
                alert.setContentText("Please create message ");
                alert.showAndWait();

            } else {
                String[] profanityWords = VulgarLanguageDetectorService.loadProfanityWords();

                if (VulgarLanguageDetectorService.containsProfanity(txt_message.getText(), profanityWords)) {
                    alert = new Alert(Alert.AlertType.ERROR);  //ctr saisir
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Contains vulgar language ");
                    alert.showAndWait();

                } else {
                    ServiceUser su = new ServiceUser();
                    User aold = su.readById(id_modif);
                    Message message=new Message();
                    message.setContenu(txt_message.getText());
                    message.setChannel(channelService.findByName(currentChannelName)); //ili  mepointi aliha
                  //  currentUser=new User();     //bch nbdlha beli connecter direct
                 // SessionManager.setCurrentUser(su.readByID)
                   /* currentUser.setId_user(41);
                    currentUser.setNom("chayma");
                    currentUser.setEmail("chayma@gmail.com");*/

                    message.setUser(aold);  //bch namlo set utilisateur eli bath msg

                    messageService.save(message);
                    Sentiment sentiment=new Sentiment();
                    sentiment.setChannel(channelService.findByName(currentChannelName));
                    String sentimentAnlyse= analyseSentimentService.analyzeSentiment(txt_message.getText());
                    sentiment.setNom(sentimentAnlyse);
                    analyseSentimentService.save(sentiment);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();
                    updateScrollPane(messageService.findAllMessageByChannelName(currentChannelName));

                    txt_message.setText("");
                    
                    Notifications.create()
                            .title("Message sen")
                            .text("Received")
                            .showWarning();
                }
                   
                
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
       

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Channel> channelArrayList = channelService.findAll(); //cad awel methode t7bha tsir owel thel page

        ObservableList<String> channelNames = FXCollections.observableArrayList();// ObservableList fl fx Roomchat base al observable : eli heya ay haja andek tamlha refresh
        for (Channel channel : channelArrayList) {
            channelNames.add(channel.getNomCh());
        }
        channelListView.setItems(channelNames); //eli ana bch nhtouh f channel listeview


        channelListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {  //wa9t tu click y5rjlik liste msg
            if (newValue != null) {             
                currentChannelName=newValue;  //y7otlik enehi eli clickit alih
               List<Message> messages = messageService.findAllMessageByChannelName(newValue);          
                updateScrollPane(messages);
            }
        });
    }

    private void updateScrollPane(List<Message> messages) {StringBuilder messagesText = new StringBuilder();

        for (Message message : messages) {
            
             String formattedMessage =message.getDateEnvoiMsg() + "\nFrom " + message.getUser().getUsername() + ": " + message.getContenu();

             
            messagesText.append(formattedMessage); //display de msgt fi west msgtext

        }

        messageTextArea.setText(messagesText.toString());
    }

}


