/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import service.PanierService;
import util.MYDB;
/**
 *
 * @author ASUS
 */
public class panier {
     private int id_panier; 
        

     private User u;
     private Double total;
     private int quantite_panier;
     private Produit p;

    public panier() {
      
    }

    public panier(int i, int i0, int i1) {
    }

   
   

    public int getId_panier() {
        return id_panier;
    }

    public void setId_panier(int id_panier) {
        this.id_panier = id_panier;
    }

   
    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public int getQuantite_panier() {
        return quantite_panier;
    }

    public void setQuantite_panier(int quantite_panier) {
        this.quantite_panier = quantite_panier;
    }

    public Produit getP() {
        return p;
    }

    public void setP(Produit p) {
        this.p = p;
    }

    public panier(int id_panier, User u, Double total, int quantite_panier, Produit p) {
        this.id_panier = id_panier;
        this.u = u;
        this.total = total;
        this.quantite_panier = quantite_panier;
        this.p = p;
    }

    
   



    @Override
    public String toString() {
        return "Panier{" + "id_panier=" + id_panier + ", User=" + u + '}';
    }

   public double totalmontantPanier(int id_user) {
    double somme = 0.0;
     Connection con;
      con = MYDB.getinstance().getCon(); 
    try {
        String sql = "SELECT SUM(total) AS total FROM panier WHERE id_user = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id_user);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            somme = rs.getDouble("total");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return somme;}
     public void QantitePlus1(int id_panier, int id_prod) throws SQLException {
    String query = "UPDATE panier SET quantite_panier = quantite_panier + 1 " +
                   "WHERE id_prod = ? AND id_panier = ?";

   
}


    

   

    

   

   
    
    
} 

