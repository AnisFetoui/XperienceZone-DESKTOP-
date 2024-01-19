/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.util.List;

/**
 *
 * @author LENOVO GAMING
 */
public interface IService<T> {
    void ajouterR(T t);
    void supprimerR(int x);
    void modifierR(T t);
    List<T> afficher();
}
