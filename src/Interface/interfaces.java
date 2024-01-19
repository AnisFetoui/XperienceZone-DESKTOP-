/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.util.ArrayList;

/**
 *
 * @author ZAHRA
 * @param <E>
 */
public interface interfaces<E> {
   void ajouter (E e);
  void  modifier (E e);
   void  supprimer (E e);
  ArrayList<E>  afficher();
   ArrayList<E>   rechercher();
            
}
