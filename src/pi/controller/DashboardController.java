package pi.controller;

import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import service.AnalyseSentimentService;
import classes.Sentiment;

import java.awt.Dimension;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class DashboardController implements Initializable {
    @FXML
    private StackPane stackPanePositive;
    @FXML
    private StackPane stackPaneNegative;
    AnalyseSentimentService analyseSentimentService=new AnalyseSentimentService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        JFreeChart chartPositive = ChartFactory.createRingChart("Positive Channel Sentiment", createDatasetPositive(), true, true, false);
        ChartPanel chartPanel = new ChartPanel(chartPositive);
        chartPanel.setPreferredSize(new Dimension((int) stackPanePositive.getWidth(), (int) stackPanePositive.getHeight()));
        SwingNode swingNode = new SwingNode();
        swingNode.setContent(chartPanel);
        stackPanePositive.getChildren().add(swingNode);


        JFreeChart chartNegative = ChartFactory.createRingChart("Negative Channel Sentiment", createDatasetNegative(), true, true, false);
        ChartPanel chartPanelNeg = new ChartPanel(chartNegative);
        chartPanelNeg.setPreferredSize(new Dimension((int) stackPaneNegative.getWidth(), (int) stackPaneNegative.getHeight()));
        SwingNode swingNodeNeg = new SwingNode();
        swingNodeNeg.setContent(chartPanelNeg);
        stackPaneNegative.getChildren().add(swingNodeNeg);

    }

    private DefaultPieDataset createDatasetPositive() {
        List<Sentiment> sentiments = analyseSentimentService.findAll();
        DefaultPieDataset dataset = new DefaultPieDataset();

   // Group sentiments by channel and count positive sentiments, converting the channel to a string
        Map<String, Long> positiveSentimentCounts = sentiments.stream()
                .collect(Collectors.groupingBy(sentiment -> sentiment.getChannel().getNomCh(), Collectors.summingLong(sentiment ->
                        sentiment.getNom().equals("Positive") ? 1 : 0
                )));

        positiveSentimentCounts.forEach((channel, count) -> dataset.setValue(channel, count));

        return dataset;
    }
    private DefaultPieDataset createDatasetNegative() {
        List<Sentiment> sentiments = analyseSentimentService.findAll();
        DefaultPieDataset dataset = new DefaultPieDataset();

        // Group sentiments by channel and count positive sentiments, converting the channel to a string
        Map<String, Long> positiveSentimentCounts = sentiments.stream()
                .collect(Collectors.groupingBy(sentiment -> sentiment.getChannel().getNomCh(), Collectors.summingLong(sentiment ->
                        sentiment.getNom().equals("Negative") ? 1 : 0
                )));

        positiveSentimentCounts.forEach((channel, count) -> dataset.setValue(channel, count));

        return dataset;
    }
}
