package main.controleur;

import main.vue.FenetreClassement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La classe AfficherClassementControleur est appliquée au JComboBox de la fenêtre d'affichage du classement. Cette méthode permet de changer les joueurs affichés en fonction de la valeur sélectionnée dans le JComboBox.
 * @author DERNONCOURT Cyril , DROUARD Antoine, LE BERT Lea, MARTINEAU Lucas
 */
public class AfficherClassementControleur implements ActionListener
{
    private FenetreClassement vue;
    private JComboBox cat;

    /**
     * Constructeur de la classe AfficherClassementControleur
     * @param fenetreClassement la fenêtre qui affiche le classement
     * @param categorie sélection des catégories de joueur à afficher
     */
    public AfficherClassementControleur(FenetreClassement fenetreClassement, JComboBox categorie) {
            vue = fenetreClassement;
            cat = categorie;
    }

    /**
     * Affiche les joueurs correspondant à la catégorie sélectionnée
     * @param e un changement de sélection de la valeur du JComboBox
     */
    public void actionPerformed(ActionEvent e) {
            this.vue.afficherJoueur((String) cat.getSelectedItem());
    }

}
