/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class MYDB {
    
    static final String URL ="jdbc:mysql://localhost:3306/pidev";
    static final String USER ="root";
    static final String PASSWORD ="";
    
    
    
    private Connection con;
    
    //3 
    static MYDB instance;
     //1 rendre le constructeur prive
    private MYDB() {
        
        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            
            System.out.println("connexion etablie");
        } catch (SQLException ex) {
            Logger.getLogger(MYDB.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Probeleme de connexion");
        }
    }
    
    // 2 etape: de creer une methode static pour utiliser le const 
    public static MYDB getinstance(){
        if(instance == null){
            instance =  new MYDB();
        }
        return instance;
        
    }

    public Connection getCon() {
        return con;
    }
    
    
    
}

