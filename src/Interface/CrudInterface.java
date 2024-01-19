/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.sql.SQLException;
import java.util.ArrayList;


public interface CrudInterface<P> {
    void ajout(P p);
    void supprimer(int id);
    void modifier(P p);
    ArrayList<P> afficher();
    P readById(int id);

    ArrayList<P> chercher();

    
    ArrayList<P> sortBy(String nom_column,String Asc_Dsc);
}
