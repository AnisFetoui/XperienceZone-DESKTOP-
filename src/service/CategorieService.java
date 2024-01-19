/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import util.MYDB;
import Interface.CrudInterface;
import classes.Categorie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class CategorieService implements CrudInterface<Categorie> {

    
   
        Connection con;
        Statement ste;

    public CategorieService() throws SQLException {
         con = (Connection) MYDB.getinstance().getCon();
    }
       @Override
    public void ajout (Categorie c)  {
    
     
       try {
            String req;
           req = "INSERT INTO Categorie(Nom_categorie,Description_categorie,Type_categorie)values(?,?,?)";
            PreparedStatement pre =con.prepareStatement(req);
            pre.setString(1,c.getNom_categorie());          
            pre.setString(2,c.getDescription_categorie());            
            pre.setString(3,c.getType_categorie());
            
            pre.executeUpdate();
            System.out.println("Categorie Added Successfully!");
       
      } catch (SQLException ex) {
                System.out.println(ex);
            
      }
       
 }
     public ArrayList <Categorie> afficher()  {

    ArrayList<Categorie> categories = new ArrayList<>();
    //CategorieService cs = new CategorieService() ;
        try {
            
            String req = "SELECT * FROM categorie";
            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {                
                Categorie c = new Categorie();
                c.setId_categorie(rs.getInt(1));
                c.setNom_categorie(rs.getString(2));
                c.setDescription_categorie(rs.getString(3));
                c.setType_categorie(rs.getString(4));
                
                categories.add(c);
            }
            
        } catch (SQLException ex) {
        }
        
        return  categories;
    }
    public void supprimer(int id) {
try {
             String req = "Delete FROM `categorie` WHERE id_categorie='"+id+"';" ;

             Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
              st.executeUpdate(req);
              System.out.println("Categorie  supprimé avec succes");
          } catch (SQLException ex) {
          }}


       @Override
    public Categorie readById(int id) {
 Categorie c = new Categorie();
        try {
            
       String req="SELECT * FROM categorie WHERE `id_categorie`='"+id+"'";
            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery(req);
            rs.beforeFirst();
            rs.next();
                c.setId_categorie(rs.getInt(1));
                c.setNom_categorie(rs.getString(2));
                c.setDescription_categorie(rs.getString(3));           
                c.setType_categorie(rs.getString(4)); 
        } catch (SQLException ex) {
        }
        return  c;
    }

  
 @Override
    public void modifier(Categorie c) {
   try {
            String req ="UPDATE categorie SET `nom_categorie`= ? , `description_categorie`= ? ,`type_categorie`= ?  WHERE id_categorie = ?";
            PreparedStatement ps = con.prepareStatement(req);
            ps.setString(1,c.getNom_categorie());
            ps.setString(2, c.getDescription_categorie());
             ps.setString(3, c.getType_categorie());
            ps.setInt(4, c.getId_categorie());
            ps.executeUpdate();
            System.out.println("categorie updated successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }    }
     
    public ArrayList<Categorie> chercher(String nom_column, String valeur) {
 List<Categorie> ListeCategCherchee=new ArrayList<>();
        try {
              String req="SELECT * FROM categorie WHERE "+nom_column+" = '"+valeur+"'" ;
              Statement ste = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
              ResultSet res=ste.executeQuery(req);
              while(res.next()){
                  Categorie c=new Categorie();
                  c.setId_categorie(res.getInt(1));
                  c.setNom_categorie(res.getString(2));
                  c.setDescription_categorie(res.getString(3));
                  c.setType_categorie(res.getString(4));
                  ListeCategCherchee.add(c);
              }  
          } catch (SQLException ex) {
         ex.printStackTrace();         
          }
           return (ArrayList<Categorie>) ListeCategCherchee ;}


           public Categorie RetournerT(String s) {
        Categorie c = new Categorie();
        try {
            
       String req="SELECT * FROM categorie WHERE `nom_categorie`='"+s+"'";
            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery(req);
            rs.beforeFirst();
            rs.next();
                c.setId_categorie(rs.getInt(1));
                c.setNom_categorie(rs.getString(2));
                c.setDescription_categorie(rs.getString(3));           
                c.setType_categorie(rs.getString(4)); 
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return  c;
        
    }

        @Override
        public ArrayList<Categorie> sortBy(String nom_column, String Asc_Dsc) {
    List<Categorie> ListeCategTriee = new ArrayList<>();
    try {
        // Utilisez des requêtes préparées pour éviter les attaques SQL par injection
        String req = "SELECT * FROM categorie ORDER BY ? " + Asc_Dsc;
        PreparedStatement preparedStatement = con.prepareStatement(req);
        preparedStatement.setString(1, nom_column);

        ResultSet res = preparedStatement.executeQuery();

        while (res.next()) {
            Categorie c = new Categorie();
            c.setId_categorie(res.getInt(1));
            c.setNom_categorie(res.getString(2));
            c.setDescription_categorie(res.getString(3));
            c.setType_categorie(res.getString(4));

            ListeCategTriee.add(c);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }

    return new ArrayList<>(ListeCategTriee);
}


    @Override
    public ArrayList<Categorie> chercher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    }

   


  
   
    
        


   



