package classes;

import java.util.List;
import java.util.Objects;


public class Channel {
    private int idCh;
    private String nomCh;
    private List<Message> messages;
    private Evenement evenement;
    private List<Sentiment> sentiments;

    public Channel() {
    }

    public Channel(int idCh, String nomCh, List<Message> messages, Evenement evenement) {
        this.idCh = idCh;
        this.nomCh = nomCh;
        this.messages = messages;
        this.evenement = evenement;
    }

    public int getIdCh() {
        return idCh;
    }

    public void setIdCh(int idCh) {
        this.idCh = idCh;
    }

    public String getNomCh() {
        return nomCh;
    }

    public void setNomCh(String nomCh) {
        this.nomCh = nomCh;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public Evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    public List<Sentiment> getSentiments() {
        return sentiments;
    }

    public void setSentiments(List<Sentiment> sentiments) {
        this.sentiments = sentiments;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "idCh=" + idCh +
                ", nomCh='" + nomCh + '\'' +
                ", messages=" + messages +
                ", evenement=" + evenement +
                ", sentiments=" + sentiments +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Channel channel = (Channel) o;
        return idCh == channel.idCh && Objects.equals(nomCh, channel.nomCh) && Objects.equals(messages, channel.messages) && Objects.equals(evenement, channel.evenement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCh, nomCh, messages, evenement);
    }
}
    