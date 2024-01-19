package service;

import util.MYDB;
import classes.Message;
import classes.User;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import Interface.IMessage;
import service.ServiceUser;


public class MessageService extends Observable  implements IMessage {

    private Connection con;
    private Statement statement;
    private PreparedStatement preparedStatement;
    ChannelService channelService= new ChannelService();
    ServiceUser userService=new ServiceUser();


    public MessageService() {
        con = MYDB.getinstance().getCon();
    }
    public List<Message> findAllMessageByChannelName(String nameChannel){
     int idChannel =channelService.findByName(nameChannel).getIdCh();
        List<Message>messages=new ArrayList<>();
        //ajoute un cle etranger use_id dans la table message to know who send the message
        //and change the traitement of the getUser in this method
        String sql = "SELECT * FROM message where idCh="+idChannel;
        try {
            preparedStatement = con.prepareStatement(sql);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                Message message=new Message();
                message.setIdMsg (result.getInt("idMsg"));
                message.setContenu (result.getString("contenuMsg"));
                Timestamp timestamp = result.getTimestamp("heurEnvoiMsg");
                LocalDateTime dateMessage = timestamp.toLocalDateTime();
                message.setDateEnvoiMsg(dateMessage);
                int id_user=result.getInt("id_user");
                User user=userService.readById(id_user);
                message.setUser(user);
                messages.add(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return messages;
    }
    public void save(Message message) {
      
        String sql = "INSERT INTO message (contenuMsg,heurEnvoiMsg,idCh,id_user) VALUES (?, ?, ?,?)";
        System.out.println(message.toString());
        System.out.println(message.getUser().getId_user());
        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, message.getContenu());
            Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
            preparedStatement.setTimestamp(2,timestamp );
            preparedStatement.setInt(3, message.getChannel().getIdCh());      
            preparedStatement.setInt(4, message.getUser().getId_user());
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("message saved successfully.");

            } else {
                System.out.println("Failed to save messsage.");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        setChanged();
        notifyObservers(message);

    }
    public void delete(int idMessage){
        String sql = "DELETE FROM message WHERE idMsg = ?";
        try  {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, idMessage);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("message deleted successfully.");
            } else {
                System.out.println("No message found with the specified ID.");
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}
