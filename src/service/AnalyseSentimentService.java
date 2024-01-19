package service;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.simple.Document;
import edu.stanford.nlp.simple.Sentence;
import edu.stanford.nlp.simple.SentimentClass;
import util.MYDB;
import classes.Channel;
import classes.Evenement;
import classes.Sentiment;

public class AnalyseSentimentService {
    private Connection con;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private StanfordCoreNLP pipeline;
    ChannelService channelService=new ChannelService();
    public String analyzeSentiment(String inputText) throws IOException {

        Properties properties = new Properties();
        properties.setProperty("annotators", "tokenize,ssplit,pos,lemma,parse,sentiment");

        String sentimentResponse = getSentimentResponse(inputText);
        System.out.println("*****************/  Sentiment  " +sentimentResponse +"  /*********************");

        return sentimentResponse;


    }

    public AnalyseSentimentService() {
        con = MYDB.getinstance().getCon();
    }

    private String getSentimentResponse(String userMessage) {
        // Process user input using Stanford CoreNLP
        Document doc = new Document(userMessage);

        // Get the sentiment of the user's input
        String sentimentLabel = "Unknown";
        for (Sentence sentence : doc.sentences()) {
            SentimentClass sentimentClass = sentence.sentiment();
            if (sentimentClass == SentimentClass.VERY_NEGATIVE) {
                sentimentLabel = "Very Negative";
            } else if (sentimentClass == SentimentClass.NEGATIVE) {
                sentimentLabel = "Negative";
            } else if (sentimentClass == SentimentClass.NEUTRAL) {
                sentimentLabel = "Neutral";
            } else if (sentimentClass == SentimentClass.POSITIVE) {
                sentimentLabel = "Positive";
            } else if (sentimentClass == SentimentClass.VERY_POSITIVE) {
                sentimentLabel = "Very Positive";
            }
        }

        return  sentimentLabel;
    }
    public List<Sentiment> findAll() {

        List<Sentiment> sentiments = new ArrayList<>();
        String sql = "SELECT * FROM sentiment";
        try {
            preparedStatement = con.prepareStatement(sql);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                Sentiment sentiment = new Sentiment();
                int idChannel= result.getInt("idCh");
                Channel channel= channelService.findById(idChannel);
                sentiment.setIdSentiment(result.getInt("id"));
                sentiment.setNom(result.getString("nom"));
                sentiment.setChannel(channel);
                sentiments.add(sentiment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sentiments;
    }
    public void save(Sentiment sentiment) {

        String sql = "INSERT INTO sentiment (nom,idCh) VALUES (?,?)";
        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, sentiment.getNom());
            preparedStatement.setInt(2, sentiment.getChannel().getIdCh());
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("sentiment saved successfully.");
            } else {
                System.out.println("Failed to save sentiment.");
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }



    }


}
