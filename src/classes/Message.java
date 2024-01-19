package classes;



import classes.User;
import java.time.LocalDateTime;
import java.util.Objects;


public class Message {
    private int idMsg;
    private String contenu;
    private LocalDateTime dateEnvoiMsg;
    private Channel channel;
    private User user;

    public Message() {
    }

    public Message(int idMsg, String contenu, LocalDateTime dateEnvoiMsg, Channel channel, User user) {
        this.idMsg = idMsg;
        this.contenu = contenu;
        this.dateEnvoiMsg = dateEnvoiMsg;
        this.channel = channel;
        this.user = user;
    }

    public int getIdMsg() {
        return idMsg;
    }

    public void setIdMsg(int idMsg) {
        this.idMsg = idMsg;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public LocalDateTime getDateEnvoiMsg() {
        return dateEnvoiMsg;
    }

    public void setDateEnvoiMsg(LocalDateTime dateEnvoiMsg) {
        this.dateEnvoiMsg = dateEnvoiMsg;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Message{" +
                "idMsg=" + idMsg +
                ", contenu='" + contenu + '\'' +
                ", dateEnvoiMsg=" + dateEnvoiMsg +
                ", channel=" + channel +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return idMsg == message.idMsg && Objects.equals(contenu, message.contenu) && Objects.equals(dateEnvoiMsg, message.dateEnvoiMsg) && Objects.equals(channel, message.channel) && Objects.equals(user, message.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMsg, contenu, dateEnvoiMsg, channel, user);
    }
}
