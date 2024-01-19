/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import classes.User;
import util.MYDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.SQLException;s
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Interface.UService;
//import at.favre.lib.crypto.bcrypt.BCrypt;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author ANIS
 */
public class ServiceUser implements UService<User> {
    Connection con; 
    Statement ste;
    
    public ServiceUser() {
      con = MYDB.getinstance().getCon();  
    }

    @Override
    public void ajouter(User u) {
         try {
                String req = "INSERT INTO Userr(username,mail,password,roles,image,age,sexe)values(?,?,?,?,?,?,?)";
                
                PreparedStatement pre = con.prepareStatement(req); 
                pre.setString(1,u.getUsername() );
                pre.setString(2,u.getMail() );
                pre.setString(3,u.getMdp() );
                pre.setString(4,"[\"ROLE_CLIENT\"]");
                pre.setString(5,u.getImage() );
                pre.setInt(6,u.getAge() );
                pre.setString(7,u.getSexe());
               // pre.setString(9,NULL);
                
                pre.executeUpdate();
                 System.out.println("user ajouter successfully!");
            } catch (SQLException ex) {
                System.out.println(ex);
            }
    }

    @Override
    public void supprimer(int id) {
     String requete = "DELETE FROM Userr WHERE id_user = ?";
    try {
        PreparedStatement ps = con.prepareStatement(requete);
        ps.setInt(1, id);
        ps.executeUpdate();
    } catch (SQLException ex) {
            System.out.println(ex);
    }
    }

     public void ResetPassword(String email, String password) {
        try {

            String req = "UPDATE Userr SET password = ? WHERE mail = ?";
            PreparedStatement ps = con.prepareStatement(req);

            ps.setString(1, password);
            ps.setString(2, email);

            ps.executeUpdate();
            System.out.println("Password updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void modifier(User u) {
                try {
            String req="UPDATE Userr SET username =?,`mail`=? ,`password` =?,`image` =?,`age` =?,`sexe` =? WHERE id_user =?";
            PreparedStatement pre = con.prepareStatement(req); 
            pre.setString(1,u.getUsername() );
            pre.setString(2,u.getMail() );
            pre.setString(3,u.getMdp() );
            pre.setString(4,"[\"ROLE_CLIENT\"]");
            pre.setString(5,u.getImage() );
            pre.setInt(6,u.getAge() );
            pre.setString(7,u.getSexe() );    
            pre.setInt(8, u.getId_user());
            pre.executeUpdate();
            System.out.println("user updated successfully!");
        } catch (SQLException ex) {
            System.out.println(ex);
        }  
    }

    @Override
    public List<User> afficher() {
 List<User> utilisateurs = new ArrayList<>();
       String sql ="select * from Userr";
       try {
          ste= con.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next()){
              User u = new User(rs.getInt("id_user"),
                      rs.getString("username"), rs.getString("mail"),rs.getString("password"), rs.getString("roles"),rs.getString("image"), rs.getInt("age"), rs.getString("sexe")); 
                utilisateurs.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return utilisateurs;
    }

    public boolean checkEmailExists(String email) {

    boolean result = false;

    try {
        String req = "SELECT * FROM Userr WHERE mail = ?";
        PreparedStatement st = con.prepareStatement(req);
        st.setString(1, email);
        ResultSet rs = st.executeQuery();
        result = rs.next();
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }

    return result;
}

    public User readById(int id) {
        User u = null;  
    try  {
        String req ="SELECT * from Userr WHERE id_user= '" + id +"'";
        PreparedStatement ps = con.prepareStatement(req);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {

        u= new User(rs.getInt("id_user"),
                      rs.getString("username"), rs.getString("mail"),rs.getString("password"), rs.getString("roles"),rs.getString("image"), rs.getInt("age"), rs.getString("sexe")); 
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return u;
}   
    
   public User readByEmail(String email) {
    User u = new User();
    String req = "SELECT * from Userr WHERE mail=?";
    try (PreparedStatement ps = con.prepareStatement(req)) {
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {

            u = new User(
                rs.getInt("id_user"),
                rs.getString("username"),
                rs.getString("mail"),
                rs.getString("password"),
                rs.getString("roles"),
                rs.getString("image"),
                rs.getInt("age"),
                rs.getString("sexe")
            );
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return u;
}
   private String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }
   
public int authentification(String email, String password) {
    int id = -1;

    // Fetch the hashed password from the database
    String hashedPasswordFromDB = getHashedPasswordByEmail(email);

    String req = "SELECT * from Userr WHERE mail = ? AND password = ?";

    try (PreparedStatement ps = con.prepareStatement(req)){
        ps.setString(1, email);
        ps.setString(2, hashedPasswordFromDB); // Compare with hashed password from DB

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
                id = rs.getInt("id_user");
            
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    return id;
}


    public int ChercherMail(String email) {

        try {
            String req = "SELECT * from Userr WHERE mail ='" + email + "'  ";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                if (rs.getString("mail").equals(email)) {
                    System.out.println("mail trouvé ! ");
                    return 1;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return -1;
    }
 
    public User chercherByEmail(String email) {
    String sql ="SELECT * from Userr WHERE mail='"+email+"'";
    User u = new User();
    try {
            ste= con.createStatement();
            ResultSet rs = ste.executeQuery(sql);
        while (rs.next()) {
            
            u = new User(rs.getInt("id_user"),
                      rs.getString("username"), rs.getString("mail"),rs.getString("password"), rs.getString("roles"),rs.getString("image"), rs.getInt("age"), rs.getString("sexe"));
        }
    } catch (SQLException ex) {
            System.out.println(ex.getMessage());
    }
    return u;
    }
public List<User> searchUsersByEmailStartingWithLetter(String searchAttribute,String startingLetter) {
    List<User> matchingUsers = new ArrayList<>();
    
    String sql = "SELECT * FROM Userr WHERE " + searchAttribute + " LIKE ?";
    
    try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
        preparedStatement.setString(1, startingLetter + "%");
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            User user = new User(
                rs.getInt("id_user"),
                rs.getString("username"),
                rs.getString("mail"),
                rs.getString("password"),
                rs.getString("roles"),
                rs.getString("image"),
                rs.getInt("age"),
                rs.getString("sexe")
            );
            matchingUsers.add(user);
        }
    } catch (SQLException ex) {
        System.out.println("Error while searching for users by email: " + ex.getMessage());
    }
    
    return matchingUsers;
    }
 
    public void modifierUsername(User p) {

        try {

            String req = "UPDATE Userr SET username = ? WHERE id_user = ?";
            PreparedStatement ps = con.prepareStatement(req);
            ps.setString(1, p.getUsername());
            ps.setInt(2, p.getId_user());

            ps.executeUpdate();
            System.out.println("Username updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifierAge(User p) {

        try {

            String req = "UPDATE Userr SET age = ? WHERE id_user = ?";

            PreparedStatement ps = con.prepareStatement(req);
            ps.setInt(1, p.getAge());
            ps.setInt(2, p.getId_user());

            ps.executeUpdate();
            System.out.println("prenom updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifierEmail(User p) {

        if (ChercherMail(p.getMail()) == -1) {
            try {

                String req = "UPDATE Userr SET mail = ? WHERE id_user = ?";
                PreparedStatement ps = con.prepareStatement(req);
                ps.setString(1, p.getMail());
                ps.setInt(2, p.getId_user());

                ps.executeUpdate();
                System.out.println("Nom updated !");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            System.out.println("Mail existant ! ");
        }
    }

    public void modifierPassword(User p) {

        try {

            String req = "UPDATE Userr SET password = ? WHERE id_user = ?";

            PreparedStatement ps = con.prepareStatement(req);
            ps.setString(1, p.getMdp());
            ps.setInt(2, p.getId_user());

            ps.executeUpdate();
            System.out.println("password updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifierImage(User p) {

        try {

            String req = "UPDATE Userr SET image = ? WHERE id_user = ?";
            PreparedStatement ps = con.prepareStatement(req);
            ps.setString(1, p.getImage());
            ps.setInt(2, p.getId_user());

            ps.executeUpdate();
            System.out.println("Image updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
   


public List<User> chercherByEmailTV(String email) {
    String sql = "SELECT * FROM Userr WHERE mail = ?";
    List<User> userList = new ArrayList<>();

    try {
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, email);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            User u = new User(
                rs.getInt("id_user"),
                rs.getString("username"),
                rs.getString("mail"),
                rs.getString("password"),
                rs.getString("roles"),
                rs.getString("image"),
                rs.getInt("age"),
                rs.getString("sexe")
            );
            userList.add(u);
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }

    return userList;
}
public String getHashedPasswordByEmail(String email) {
    String hashedPassword = null;
    String req = "SELECT password FROM Userr WHERE mail = ?";

    try (PreparedStatement ps = con.prepareStatement(req)) {
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            hashedPassword = rs.getString("password");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }

    return hashedPassword;
}

     
    }