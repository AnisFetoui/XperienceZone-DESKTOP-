/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Med Aziz
 */
public class inscription {
    private int Id_ins;
    private LocalDate date_ins; 
    private LocalTime heure_ins;
    private int nbr_tickes;
    private double frait_abonnement;
    private int activite_id ;
    private int user_id;

    public inscription(int Id_ins, LocalDate date_ins, LocalTime heure_ins, int nbr_tickes, double frait_abonnement, int activite_id, int user_id) {
        this.Id_ins = Id_ins;
        this.date_ins = date_ins;
        this.heure_ins = heure_ins;
        this.nbr_tickes = nbr_tickes;
        this.frait_abonnement = frait_abonnement;
        this.activite_id = activite_id;
        this.user_id = user_id;
    }

    public inscription(LocalDate date_ins, LocalTime heure_ins, int nbr_tickes, double frait_abonnement, int activite_id, int user_id) {
        this.date_ins = date_ins;
        this.heure_ins = heure_ins;
        this.nbr_tickes = nbr_tickes;
        this.frait_abonnement = frait_abonnement;
        this.activite_id = activite_id;
        this.user_id = user_id;
    }
    


  


      public inscription() {
    }

    public int getActivite_id() {
        return activite_id;
    }

    public int getUser_id() {
        return user_id;
    }

 
    public int getId_ins() {
        return Id_ins;
    }
    
    public LocalDate getDate_ins() {
        return date_ins;
    }

    public LocalTime getHeure_ins() {
        return heure_ins;
    }

    public int getNbr_tickes() {
        return nbr_tickes;
    }


    public double getFrait_abonnement() {
        return frait_abonnement;
    }



    public void setId_ins(int Id_ins) {
        this.Id_ins = Id_ins;
    }

    public void setDate_ins(LocalDate date_ins) {
        this.date_ins = date_ins;
    }

    public void setHeure_ins(LocalTime heure_ins) {
        this.heure_ins = heure_ins;
    }

    public void setNbr_tickes(int nbr_tickes) {
        this.nbr_tickes = nbr_tickes;
    }

    public void setFrait_abonnement(double frait_abonnement) {
        this.frait_abonnement = frait_abonnement;
    }

    
   
    

     /*public boolean isValidEmail() {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@gmail.com";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email_user);
        return matcher.matches();
    }
*/

    public void setActivite_id(int activite_id) {
        this.activite_id = activite_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

  
    
}
