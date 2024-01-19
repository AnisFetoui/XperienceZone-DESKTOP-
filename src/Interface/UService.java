/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.util.List;

/**
 *
 * @author ANIS
 */
public interface UService<U> {
    void ajouter(U u);
    void supprimer(int id);
    void modifier(U u);
    List<U> afficher();
}
