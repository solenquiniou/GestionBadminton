package main.vue;

import javax.swing.*;

/**
 * Interface gestion joueur: pour forcer l'existance des getteurs nécessaires au controlleurs de dates de naissances pour les vues ajout joueur et modifier joueur
 * @author DERNONCOURT Cyril, DROUARD Antoine, LE BERT Léa, MARTINEAU Lucas
 * @version 1.1
 */
public interface GestionJoueur  {
     JComboBox getAnnee();
     JComboBox getMois() ;
     JComboBox getJour();
     JCheckBox getDateIndefinie() ;

}
