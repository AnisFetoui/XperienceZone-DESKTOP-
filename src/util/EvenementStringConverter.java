package util;

import classes.Evenement;
import javafx.util.StringConverter;


public class EvenementStringConverter extends StringConverter<Evenement> {  //classe hethi 3mlnha bch tjib ml base k object donc bch nbdlouha string
    @Override
    public String toString(Evenement evenement) {
        return evenement == null ? null : evenement.getNom_event();
    }

    @Override
    public Evenement fromString(String s) {
        return null;
    }
}
