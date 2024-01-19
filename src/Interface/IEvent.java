/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.util.List;
import classes.Evenement;

/**
 *
 * @author ktari
 */
public interface IEvent  {
public Evenement findById(int id);
public List<Evenement>findAll();

}
