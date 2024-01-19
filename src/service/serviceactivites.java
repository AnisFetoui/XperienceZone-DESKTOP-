/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import util.MYDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import classes.activites;

/**
 *
 * @author Med Aziz
 */
public class serviceactivites implements Iserviceactivites<activites>{
    Connection con; 
    Statement ste;
    
    public serviceactivites() {
    con = MYDB.getinstance().getCon();    }
    
    //@Override


    @Override
    public void ajouterActivite(activites a) {
        try {
            String req = "INSERT INTO activites (nom_act, description, organisateur,lieu_act,adresse,images, place_dispo, prix_act,duree,periode,id_user)values(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pre = con.prepareStatement(req);
            pre.setString(1, a.getNom_act());
            pre.setString(2, a.getDescription());
            pre.setString(3, a.getOrganisateur());
            pre.setString(4, a.getLieu_act());
            pre.setString(5, a.getAdresse());
            pre.setString(6, a.getImages());
            pre.setInt(7, a.getPlace_dispo());
            pre.setString(8, a.getPrix_act());
            pre.setInt(9, a.getDuree());
            pre.setString(10, a.getPeriode());
            pre.setInt(11,a.getId_user());//lajout de id user
            //ajouter la fonction de anis connexion pour recuperer lid du utilisateur connecté
            pre.executeUpdate();
            
            } catch (SQLException ex) {
                System.out.println(ex);
            
        }
       
    }
    @Override
public void supprimerActivite(int id) {
    try {
        String req = "DELETE FROM activites WHERE Id_act=?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, id);
        pre.executeUpdate();
    } catch (SQLException ex) {
        System.out.println(ex);
    }
}
    @Override
   public void mettreAJourActivite(activites activite) {
    try {
        // Récupérer les valeurs actuelles de l'activité
        String selectReq = "SELECT * FROM activites WHERE Id_act=?";
        PreparedStatement selectPre = con.prepareStatement(selectReq);
        selectPre.setInt(1, activite.getId_act());
        ResultSet resultSet = selectPre.executeQuery();
        
        if (resultSet.next()) {
            // Lire les valeurs actuelles de l'activité
            String currentNom_act = resultSet.getString("nom_act");
            String currentDescription = resultSet.getString("description");
            String currentOrganisateur = resultSet.getString("organisateur");
            String currentLieu_act = resultSet.getString("lieu_act");
            String currentAdresse = resultSet.getString("adresse");
            String currentImages = resultSet.getString("images");
            int currentPlace_dispo = resultSet.getInt("place_dispo");
            String currentPrix_act = resultSet.getString("prix_act");
            int currentDurée = resultSet.getInt("duree");
            String currentPeriode = resultSet.getString("periode");
            
            // Mettre à jour uniquement les attributs spécifiés
            String req = "UPDATE activites SET nom_act=?, description=?, organisateur=?, lieu_act=?, adresse=?, images=?, place_dispo=?, prix_act=?, duree=?, periode=? WHERE Id_act=? ";
            PreparedStatement pre = con.prepareStatement(req);
            pre.setString(1, (activite.getNom_act() != null) ? activite.getNom_act() : currentNom_act);
            pre.setString(2, (activite.getDescription() != null) ? activite.getDescription() : currentDescription);
            pre.setString(3, (activite.getOrganisateur() != null) ? activite.getOrganisateur() : currentOrganisateur);
            pre.setString(4, (activite.getLieu_act() != null) ? activite.getLieu_act() : currentLieu_act);
            pre.setString(5, (activite.getAdresse() != null) ? activite.getAdresse() : currentAdresse);
            pre.setString(6, (activite.getImages() != null) ? activite.getImages() : currentImages);
            pre.setInt(7, (activite.getPlace_dispo() != 0) ? activite.getPlace_dispo() : currentPlace_dispo);
            pre.setString(8, (activite.getPrix_act() != null) ? activite.getPrix_act() : currentPrix_act); 
            pre.setInt(9, (activite.getDuree() != 0) ? activite.getDuree() : currentDurée);
            pre.setString(10, (activite.getPeriode() != null) ? activite.getPeriode() : currentPeriode);
            pre.setInt(11, activite.getId_act()); // Ceci est utilisé dans la clause WHERE pour identifier la ligne
            pre.executeUpdate();
        }
    } catch (SQLException ex) {
        System.out.println(ex);
    }
}



    @Override
public ArrayList<activites> chercherActivites(String nomActivite) {
    ArrayList<activites> resultats = new ArrayList<>();

    try {
        String req = "SELECT * FROM activites WHERE nom_act = ?";
        
        

        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1,nomActivite);
        try (ResultSet rs = pre.executeQuery()) {
            while (rs.next()) {
                activites activite = new activites();
                
                activite.setId_act(rs.getInt("Id_act"));
                activite.setNom_act(rs.getString("nom_act"));
                activite.setDescription(rs.getString("description"));
                activite.setOrganisateur(rs.getString("Organisateur"));
                activite.setAdresse(rs.getString("Adresse"));
                activite.setImages(rs.getString("Images"));
                activite.setLieu_act(rs.getString("lieu_act"));
                activite.setPlace_dispo(rs.getInt("place_dispo"));
                activite.setPrix_act(rs.getString("prix_act"));
                activite.setDuree(rs.getInt("duree"));
                activite.setPeriode(rs.getString("periode"));
                activite.setIdUser(rs.getInt("id_user"));
                resultats.add(activite);
            }
        }
    } catch (SQLException ex) {
        System.out.println(ex);
    }

    return resultats;
}
    @Override
public ArrayList<activites> chercherparlieu(String Gouvernorat) {
    ArrayList<activites> resultats = new ArrayList<>();

    try {
        String req = "SELECT * FROM activites WHERE lieu_act = ? ";
        
        

        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, Gouvernorat);
        
        try (ResultSet rs = pre.executeQuery()) {
            while (rs.next()) {
                activites activite = new activites();
                activite.setId_act(rs.getInt("Id_act"));
                activite.setNom_act(rs.getString("nom_act"));
                activite.setDescription(rs.getString("description"));
                activite.setOrganisateur(rs.getString("Organisateur"));
                activite.setAdresse(rs.getString("Adresse"));
                activite.setImages(rs.getString("Images"));
                activite.setLieu_act(rs.getString("lieu_act"));
                activite.setPlace_dispo(rs.getInt("place_dispo"));
                activite.setPrix_act(rs.getString("prix_act"));
                activite.setDuree(rs.getInt("duree"));
                activite.setPeriode(rs.getString("periode"));
                resultats.add(activite);
            }
        }
    } catch (SQLException ex) {
        System.out.println(ex);
    }

    return resultats;
}
@Override
public ArrayList<activites> chercherpariduser(Integer id_user) {
    ArrayList<activites> resultats = new ArrayList<>();

    try {
        String req = "SELECT * FROM activites WHERE id_user = ?";
        
        

        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, id_user);
        try (ResultSet rs = pre.executeQuery()) {
            while (rs.next()) {
                activites activite = new activites();
                activite.setId_act(rs.getInt("Id_act"));
                activite.setNom_act(rs.getString("nom_act"));
                activite.setDescription(rs.getString("description"));
                activite.setOrganisateur(rs.getString("Organisateur"));
                activite.setAdresse(rs.getString("Adresse"));
                //activite.setImages(rs.getString("Images"));
                activite.setLieu_act(rs.getString("lieu_act"));
                activite.setPlace_dispo(rs.getInt("place_dispo"));
                activite.setPrix_act(rs.getString("prix_act"));
                activite.setDuree(rs.getInt("duree"));
                activite.setPeriode(rs.getString("periode"));
                resultats.add(activite);
            }
        }
    } catch (SQLException ex) {
        System.out.println(ex);
    }

    return resultats;
}


    @Override
    public boolean isValidPrice(String input) {
        
        String regex = "^[0-9]+(\\.[0-9]{2})?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
    @Override
     public  boolean isValidPeriode(String input) {
       
        String regex = "\\d{2}/\\d{2}/\\d{4}\\s-\\s\\d{2}/\\d{2}/\\d{4}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
    @Override
    public activites chercherbyidact(Integer idact) {
    activites activite = null;

    try {
        String req = "SELECT * FROM activites WHERE Id_act = ?";
        
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, idact);

        try (ResultSet rs = pre.executeQuery()) {
            if (rs.next()) {
                activite = new activites();
                activite.setId_act(rs.getInt("Id_act"));
                activite.setNom_act(rs.getString("nom_act"));
                activite.setDescription(rs.getString("description"));
                activite.setOrganisateur(rs.getString("Organisateur"));
                activite.setAdresse(rs.getString("Adresse"));
                activite.setImages(rs.getString("Images"));
                activite.setLieu_act(rs.getString("lieu_act"));
                activite.setPlace_dispo(rs.getInt("place_dispo"));
                activite.setPrix_act(rs.getString("prix_act"));
                activite.setDuree(rs.getInt("duree"));
                activite.setPeriode(rs.getString("periode"));
                activite.setIdUser(rs.getInt("id_user"));
            }
        }
    } catch (SQLException ex) {
        System.out.println(ex);
    }

    return activite;
}


  


   




    



}

    







    
    
    

