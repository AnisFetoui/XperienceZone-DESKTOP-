/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.sql.Date;

/**
 *
 * @author the_old_is_back
 */
public class Evenement {
    private int id_event;
    private String nom_event;
    private String descript;
    private Date date_event;
    private String heure_event;
    private String lieu_event;
    private int nb_participant;
    private String image;
    private String organisateur;

    public Evenement() {
    }

    public Evenement(int id_event, String nom_event, String descript, Date date_event, String heure_event, String lieu_event, int nb_participant, String image, String organisateur) {
        this.id_event = id_event;
        this.nom_event = nom_event;
        this.descript = descript;
        this.date_event = date_event;
        this.heure_event = heure_event;
        this.lieu_event = lieu_event;
        this.nb_participant = nb_participant;
        this.image = image;
        this.organisateur = organisateur;
    }

    public Evenement(String nom_event, String descript, Date date_event, String heure_event, String lieu_event, int nb_participant, String image, String organisateur) {
        this.nom_event = nom_event;
        this.descript = descript;
        this.date_event = date_event;
        this.heure_event = heure_event;
        this.lieu_event = lieu_event;
        this.nb_participant = nb_participant;
        this.image = image;
        this.organisateur = organisateur;
    }

  



    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public String getNom_event() {
        return nom_event;
    }

    public void setNom_event(String nom_event) {
        this.nom_event = nom_event;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public Date getDate_event() {
        return date_event;
    }

    public void setDate_event(Date date_event) {
        this.date_event = date_event;
    }

    public String getHeure_event() {
        return heure_event;
    }

    public void setHeure_event(String heure_event) {
        this.heure_event = heure_event;
    }

    public String getLieu_event() {
        return lieu_event;
    }

    public void setLieu_event(String lieu_event) {
        this.lieu_event = lieu_event;
    }

    public int getNb_participant() {
        return nb_participant;
    }

    public void setNb_participant(int nb_participant) {
        this.nb_participant = nb_participant;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOrganisateur() {
        return organisateur;
    }

    public void setOrganisateur(String organisateur) {
        this.organisateur = organisateur;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id_event=" + id_event + ", nom_event=" + nom_event + ", descript=" + descript + ", date_event=" + date_event + ", heure_event=" + heure_event + ", lieu_event=" + lieu_event + ", nb_participant=" + nb_participant + ", image=" + image + ", organisateur=" + organisateur + '}';
    }

    
    
    
    
    
    
    
    
    
    
    
}
