/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author the_old_is_back
 */
public class Ticket {
    
    private int id_ticket ;
    private int num_ticket;
    private String image ;
    private  float prix;
    private Categories categorie; 
    private int id_user;
    private int id_event;
 
    

    public Ticket() {
    }

    public Ticket(int id_ticket, int num_ticket, String image, float prix, Categories categorie, int id_user, int id_event) {
        this.id_ticket = id_ticket;
        this.num_ticket = num_ticket;
        this.image = image;
        this.prix = prix;
        this.categorie = categorie;
        this.id_user = id_user;
        this.id_event = id_event;
    }

    public int getId_ticket() {
        return id_ticket;
    }

    public void setId_ticket(int id_ticket) {
        this.id_ticket = id_ticket;
    }

    public int getNum_ticket() {
        return num_ticket;
    }

    public void setNum_ticket(int num_ticket) {
        this.num_ticket = num_ticket;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public Categories getCategorie() {
        return categorie;
    }

    public void setCategorie(Categories categorie) {
        this.categorie = categorie;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

   

    @Override
    public String toString() {
        return "Ticket{" + "id_ticket=" + id_ticket + ", num_ticket=" + num_ticket + ", image=" + image + ", prix=" + prix + ", categorie=" + categorie + ", id_user=" + id_user + ", id_event=" + id_event + '}';
    }
    
    
    
    
    
    
    
    
}
