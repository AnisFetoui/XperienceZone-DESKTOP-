/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

//import view.reclamation.*;
import javafx.scene.control.ListCell;
import classes.Evenement;
import classes.Produit;
import classes.activites;

/**
 *
 * @author LENOVO GAMING
 */
public class CustomListCell<T> extends ListCell<T> {
    @Override
    protected void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
        } else {
            if (item instanceof Evenement) {
                Evenement evenement = (Evenement) item;
                setText(evenement.getNom_event());
            } else if (item instanceof Produit) {
                Produit produit = (Produit) item;
                setText(produit.getNom_prod());
            } else if (item instanceof activites) {
                activites activite = (activites) item;
                setText(activite.getNom_act());
            }
        }
    }
}
