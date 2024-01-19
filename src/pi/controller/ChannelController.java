package pi.controller;

import service.ChannelService;
import service.EventService;
import classes.Channel;
import classes.Evenement;
import util.EvenementStringConverter;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class ChannelController implements Initializable {
    ChannelService channelService=new ChannelService();
    EventService evenementService=new EventService();
    @FXML
    private TableView<Channel> channel_tableView;

    @FXML
    private TableColumn<Channel, String> column_nom;

    @FXML
    private TableColumn<Channel, String> column_evenement;

    @FXML
    private TextField txt_nom;

    @FXML
    private ComboBox<Evenement> combo_evenement;

    @FXML
    private Button btn_update;

    @FXML
    private Button btn_add;

    @FXML
    private Button btn_clear;

    @FXML
    private Button btn_delete;
    
    @FXML
    private Label menu;
    @FXML
    private Label menuclose;
    @FXML
    private VBox slider;
    private int selectedChannelId;

    @FXML
    void addOnClick() {

        try {
            Alert alert;

            if ( txt_nom.getText().isEmpty() || combo_evenement.getSelectionModel().getSelectedItem() == null) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();

            } else {

                Boolean exsist= channelService.checkIfExist(txt_nom.getText());

                if (exsist) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Channel: " + txt_nom.getText() + " was already exist!");
                    alert.showAndWait();
                } else {
                    Channel channel=new Channel();
                    channel.setNomCh(txt_nom.getText());
                    channel.setEvenement(combo_evenement.getValue());
                    channelService.save(channel);
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();


                    availableChannelsShowListData();
                    clearOnClick();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void deleteOnClick() {

        try {
            Alert alert;

            if ( txt_nom.getText().isEmpty() || combo_evenement.getSelectionModel().getSelectedItem() == null
                   ) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please Select an element to delete");
                alert.showAndWait();

            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE Channel: " + txt_nom.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    channelService.deleteCascade(selectedChannelId);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();

                    availableChannelsShowListData();
                    clearOnClick();  //by=ut annuler
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void updateOnClick() {

        try {
            Alert alert;

            if (txt_nom.getText().isEmpty()
                    || combo_evenement.getSelectionModel().getSelectedItem() == null
                ) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();

            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Channel : " + txt_nom.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    Channel channel=new Channel();
                    channel.setNomCh(txt_nom.getText());
                    channel.setEvenement(combo_evenement.getValue());
                    channelService.update(selectedChannelId,channel);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    availableChannelsShowListData();
                    clearOnClick();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
 

    }
   private ObservableList<Channel> availableChannelList;
    public void availableChannelsShowListData() {
        List<Channel> channelArrayList = channelService.findAll();

        availableChannelList = FXCollections.observableList(channelArrayList);

        column_nom.setCellValueFactory(new PropertyValueFactory<>("nomCh"));


        column_evenement.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Channel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Channel, String> param) {
                Evenement evenement = param.getValue().getEvenement();
                String nomEvenement = evenement != null ? evenement.getNom_event() : "";
                return new SimpleStringProperty(nomEvenement);
            }
        });

        channel_tableView.setItems(availableChannelList);
    }
    List<Evenement> evenements =evenementService.findAll();
    public void availableEvenement() {
        List<Evenement> listS = new ArrayList<>();

        for (Evenement data : evenements) {
            listS.add(data);
        }

        ObservableList<Evenement> listData = FXCollections.observableArrayList(listS);
        combo_evenement.setItems(listData);

        // Set the StringConverter to display only the name but still get the Evenement object
        combo_evenement.setConverter(new EvenementStringConverter());
    }

    @FXML
    public void availableChannelSelect() {

        Channel channel = channel_tableView.getSelectionModel().getSelectedItem();
        int num = channel_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        selectedChannelId = channel.getIdCh();
        txt_nom.setText(channel.getNomCh());
        combo_evenement.setValue(channel.getEvenement());



    }

    @FXML
    void clearOnClick() {
        txt_nom.setText("");
        combo_evenement.getSelectionModel().clearSelection();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        availableChannelsShowListData();
        availableEvenement();
    }
}
