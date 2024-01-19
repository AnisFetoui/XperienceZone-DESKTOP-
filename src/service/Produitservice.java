/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import util.MYDB;
import Interface.CrudInterface;
import classes.Categorie;
import classes.Produit;
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
    public class Produitservice implements CrudInterface<Produit> {
        Connection con;
        Statement ste;

   public Produitservice() {
       con = MYDB.getinstance().getCon(); // Assuming MYDB.getinstance() returns a valid connection.
}
      
        

   
 
    
        @Override
    public void ajout (Produit p)  {
    
     
       try {
            String req;
           req = "INSERT INTO Produit(Nom_prod,Prix_prod,description_prod,quantite,image,id_categorie)values(?,?,?,?,?,?)";
            PreparedStatement pre;
           pre = con.prepareStatement(req);
            pre.setString(1,p.getNom_prod());
            pre.setDouble(2,p.getPrix_prod());
            pre.setString(3,p.getdescription_prod());
            pre.setInt(4,p.getquantite());
            pre.setString(5,p.getImage());
            pre.setInt(6, p.getCategorie().getId_categorie());            
            pre.executeUpdate();
            System.out.println("Produit Added Successfully!");
       
      } catch (SQLException ex) {
                System.out.println(ex);
            
      }
 }

    /**
     *
     * @return
     * @throws java.sql.SQLException
     */
    
public ArrayList<Produit> affihcer() {
    ArrayList<Produit> produits = new ArrayList<>();
    try {
        String req = "SELECT * FROM produit";
        Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = st.executeQuery(req);
        CategorieService cs = new CategorieService();
        while (rs.next()) {
            Produit p = new Produit();
            p.setId_prod(rs.getInt(1));
            p.setNom_prod(rs.getString(2));
            p.setPrix_prod(rs.getDouble(3));
            p.setdescription_prod(rs.getString(4));
            p.setquantite(rs.getInt(5));
            p.setImage(rs.getString(6));
            p.setCategorie(cs.readById(rs.getInt(7)));
            produits.add(p);
        }
    } catch (SQLException ex) {
        // Gérer les exceptions ici
    }
    return (ArrayList<Produit>) produits;
}

    

   
        @Override
    public Produit readById(int id) {  
        Produit p;
            //      cs = new CategorieService();
        // CategorieService cs;
            p = new Produit();

        try {
            
       String req="SELECT * FROM produit WHERE `id_prod`='"+id+"'";
            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery(req);
            
            rs.beforeFirst();
            rs.next();
            CategorieService cs;
            cs = new CategorieService();
                p.setId_prod(rs.getInt(1));
                p.setNom_prod(rs.getString(2));
                p.setPrix_prod(rs.getDouble(3));
                p.setdescription_prod(rs.getString(4));
                p.setquantite(rs.getInt(5));
                p.setImage(rs.getString(6));
                p.setCategorie(cs.readById(rs.getInt(7)));
            
        } catch (SQLException ex) {
        }
        
        return  p;

    }
    @Override
   public void modifier(Produit p) {
        
           
     
         try {
        CategorieService catService = new CategorieService();
        Categorie existingCategory = catService.readById(p.getCategorie().getId_categorie());
        
        if (existingCategory == null) {
            // La catégorie n'existe pas, vous pouvez l'ajouter ici
            catService.ajout(p.getCategorie());
        }
            String req ="UPDATE produit SET `nom_prod`= ? ,`prix_prod`= ? ,`description_prod`= ? ,`quantite`= ? ,`image`= ?,`id_categorie`= ? WHERE id_prod = ?";
            PreparedStatement ps = con.prepareStatement(req);
            ps.setString(1,p.getNom_prod());
            ps.setDouble(2, p.getPrix_prod());
            ps.setString(3, p.getdescription_prod());
            ps.setInt(4, p.getquantite());
            ps.setString(5, p.getImage());
            ps.setInt(6, p.getCategorie().getId_categorie());
            ps.setInt(7, p.getId_prod());
            ps.executeUpdate();
            System.out.println("produit updated successfully !");
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
       
        
        
    }
       

    /**
     *
     * @param id
     */
      


    @Override
    public void supprimer(int id) {
 
         try {
             String req = "Delete FROM `produit` WHERE id_prod ='"+id+"';" ;

             Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
              st.executeUpdate(req);
              System.out.println("Produit  supprimé avec succes");
          } catch (SQLException ex) {
}
    }

    public ArrayList<Produit> chercher(String nom_column ,String valeur ) throws SQLException {
        
        List<Produit> ListeProdCherchee=new ArrayList<>();
          CategorieService cs = new CategorieService() ;

        try {
              String req="SELECT * FROM produit WHERE "+nom_column+" = '"+valeur+"'" ;
              Statement ste = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
              ResultSet res=ste.executeQuery(req);
              while(res.next()){
                  Produit p=new Produit();
                  p.setId_prod(res.getInt(1));
                  p.setNom_prod(res.getString(2));
                  p.setPrix_prod(res.getDouble(3));
                  p.setdescription_prod(res.getString(4));
                  p.setquantite(res.getInt(5));
                  p.setImage(res.getString(6));
                  p.setCategorie(cs.readById(res.getInt(7)));

                  ListeProdCherchee.add(p);
              }  
          } catch (SQLException ex) {
         ex.printStackTrace();         
          }
           return (ArrayList<Produit>) ListeProdCherchee ;

    }

    @Override
    public ArrayList<Produit> chercher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Produit> afficher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Produit> sortBy(String nom_column, String Asc_Dsc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Produit readProduitFromPanierById(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

  

    }




  
   


