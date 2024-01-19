/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Med Aziz
 */
public class activites {
    private int Id_act;
    private String nom_act;
    private String description;
    private String organisateur;  
    private String lieu_act;
    private String adresse;
    private String images;
    private int place_dispo;
    private String prix_act;
    private int duree;
    private String periode;
    private int id_user;

    public activites(int Id_act, String nom_act, String description, String organisateur, String lieu_act, String adresse, String images, int place_dispo, String prix_act, int duree, String periode, int id_user) {
        this.Id_act = Id_act;
        this.nom_act = nom_act;
        this.description = description;
        this.organisateur = organisateur;
        this.lieu_act = lieu_act;
        this.adresse = adresse;
        this.images = images;
        this.place_dispo = place_dispo;
        this.prix_act = prix_act;
        this.duree = duree;
        this.periode = periode;
        this.id_user = id_user;
    }

    public activites(String nom_act, String description, String organisateur, String lieu_act, String adresse, String images, int place_dispo, String prix_act, int duree, String periode, int id_user) {
        this.nom_act = nom_act;
        this.description = description;
        this.organisateur = organisateur;
        this.lieu_act = lieu_act;
        this.adresse = adresse;
        this.images = images;
        this.place_dispo = place_dispo;
        this.prix_act = prix_act;
        this.duree = duree;
        this.periode = periode;
        this.id_user = id_user;
    }
    






 

    public activites() {
    }

    public String getOrganisateur() {
        return organisateur;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getImages() {
        return images;
    }

    public void setOrganisateur(String organisateur) {
        this.organisateur = organisateur;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public int getDuree() {
        return duree;
    }

    public String getPeriode() {
        return periode;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }
    // Setter for id_user
    public void setIdUser(int id_user) {
        this.id_user = id_user;
    }


    public int getId_act() {
        return Id_act;
    }

    public String getNom_act() {
        return nom_act;
    }

    public String getDescription() {
        return description;
    }


    public String getLieu_act() {
        return lieu_act;
    }

    public int getPlace_dispo() {
        return place_dispo;
    }

    public String getPrix_act() {
        return prix_act;
    }

    public void setId_act(int Id_act) {
        this.Id_act = Id_act;
    }

    public void setNom_act(String nom_act) {
        this.nom_act = nom_act;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void setLieu_act(String lieu_act) {
        this.lieu_act = lieu_act;
    }

    public void setPlace_dispo(int place_dispo) {
        this.place_dispo = place_dispo;
    }

    public void setPrix_act(String prix_act) {
        this.prix_act = prix_act;
    }

    public int getId_user() {
        return id_user;
    }
    


   
    
    
    
}
