package classes;

import java.util.List;
import java.util.Objects;

public class Sentiment {
    private int idSentiment;
    private String nom;
    private Channel channel;

    public Sentiment() {
    }

    public Sentiment(int idSentiment, String nom, Channel channel) {
        this.idSentiment = idSentiment;
        this.nom = nom;
        this.channel = channel;
    }

    public int getIdSentiment() {
        return idSentiment;
    }

    public void setIdSentiment(int idSentiment) {
        this.idSentiment = idSentiment;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sentiment sentiment = (Sentiment) o;
        return idSentiment == sentiment.idSentiment && Objects.equals(nom, sentiment.nom) && Objects.equals(channel, sentiment.channel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSentiment, nom, channel);
    }

    @Override
    public String toString() {
        return "Sentiment{" +
                "idSentiment=" + idSentiment +
                ", nom='" + nom + '\'' +
                ", channel=" + channel +
                '}';
    }
}
