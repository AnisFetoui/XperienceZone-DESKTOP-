package service;

import util.MYDB;
import classes.Channel;
import classes.Evenement;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import Interface.IChannel;
import service.EventService;

public class ChannelService implements IChannel{
    private Connection con;
    private Statement statement;
    private PreparedStatement preparedStatement;
    EventService evenementService=new EventService();

    public ChannelService() {
        con = MYDB.getinstance().getCon();
    }

    public List<Channel> findAll() {

        List<Channel> channels = new ArrayList<>();
        String sql = "SELECT * FROM channel";
        try {
            preparedStatement = con.prepareStatement(sql);  //stay t7athr req bad t'excute , preapr t3adiha direct mn ghir ma t7athr 
            ResultSet result = preparedStatement.executeQuery();   //executeUpdate traja3lik int , executeQuery traj3lik resaltset
            while (result.next()) {
                Channel channel = new Channel();
             int idEvenement=   result.getInt("id_event");
              Evenement evenement= evenementService.findById(idEvenement);
                channel.setIdCh(result.getInt("idCh"));
                channel.setNomCh(result.getString("nomCh"));
                channel.setEvenement(evenement);
                channels.add(channel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return channels;
    }

    public Channel findById(int idChannel) {
        Channel channel=new Channel();
        String sql = "SELECT * FROM channel where idCh=?";

        try{
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, idChannel);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {

                channel.setIdCh(result.getInt("idCh"));
                channel.setNomCh(result.getString("nomCh"));

            }

          } catch (Exception e) {
        e.printStackTrace();
    }
        return channel;
    }

    public Channel findByName(String nameChannel) {
        Channel channel = new Channel();
        String sql = "SELECT * FROM channel where nomCh=?";
        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, nameChannel);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {

                channel.setIdCh(result.getInt("idCh"));
                channel.setNomCh(result.getString("nomCh"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return channel;
    }
  public Boolean checkIfExist(String nomChannel){
     Boolean result =  findAll().stream().anyMatch(channel -> channel.getNomCh().equals(nomChannel));
        return result;
  }
    public void save(Channel channel) {
        System.out.println(channel.getEvenement().getId_event());
        System.out.println(channel.getNomCh());
        String sql = "INSERT INTO channel (nomCh,id_event) VALUES (?,?)";
        try {
             preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, channel.getNomCh());
            preparedStatement.setInt(2, channel.getEvenement().getId_event());
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Channel saved successfully.");
            } else {
                System.out.println("Failed to save channel.");
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }



    }
    public void update(int idChannel,Channel channel){
        String sql = "UPDATE channel SET nomCh = ?, id_event = ? WHERE idCh = ?";
        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, channel.getNomCh());
            preparedStatement.setInt(2, channel.getEvenement().getId_event());
            preparedStatement.setInt(3, idChannel);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Channel updated successfully.");
            } else {
                System.out.println("No channel found with the specified ID.");
            }
        }catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void deleteCascade(int idChannel){
        System.out.println("show the id channel"+idChannel);
        String sql = "DELETE FROM channel WHERE idCh = ?";
        deleteMessageByIdChannel(idChannel);
        deleteSentimentByIdChannel(idChannel);
        try  {
             preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, idChannel);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Channel deleted successfully.");
            } else {
                System.out.println("No channel found with the specified ID.");
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
   public void deleteMessageByIdChannel(int idChannel){
       String sql = "DELETE FROM message WHERE idCh = ?";       //hethi bch namlo chercher ll msg par id 9bal bad nfas5ou les msg eli f channel  deleteCascade
       try  {
           preparedStatement = con.prepareStatement(sql);
           preparedStatement.setInt(1, idChannel);

           int rowsAffected = preparedStatement.executeUpdate();

           if (rowsAffected > 0) {
               System.out.println("messages deleted successfully.");
           } else {
               System.out.println("No messages found with the specified ID.");
           }

       } catch (SQLException e) {
           e.printStackTrace();

       }
   }
    public void deleteSentimentByIdChannel(int idChannel){
        String sql = "DELETE FROM sentiment WHERE idCh = ?";       //hethi bch namlo chercher ll sentiment par id 9bal bad nfas5ou les sentiment eli tab3in channel  deleteCascade
        try  {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, idChannel);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("sentiments deleted successfully.");
            } else {
                System.out.println("No sentiment found with the specified ID.");
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}