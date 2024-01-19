/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

//import view.reclamation.*;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO GAMING
 */
public class StatTController implements Initializable {

    @FXML
    private Text statTXTT;
    @FXML
    private Button retourSTAT;
    @FXML
    private PieChart piechart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void retourHome_rec(ActionEvent event) {
        
     Scene scene = ((Node) event.getSource()).getScene();
    Stage stage = (Stage) scene.getWindow();
    stage.close();
    }
    @FXML
    public void setStatisticsTextT(String text) {
    statTXTT.setText(text);
    }
    
    public void updatePieChart(Map<String, Integer> statistics) {
    piechart.getData().clear(); // Effacez les données précédentes

    for (Map.Entry<String, Integer> entry : statistics.entrySet()) {
        String typeRec = entry.getKey();
        int count = entry.getValue();

        PieChart.Data slice = new PieChart.Data(typeRec, count);
        piechart.getData().add(slice);
    }
}
}
