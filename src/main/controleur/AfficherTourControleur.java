package main.controleur;

import main.tournoi.Joueur;
import main.vue.FenetrePrincipale;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Antoine Drouard, Cyril Dernoncourt,MArtinneau Lucas, Le BErt Léa in 2017
 */
public class AfficherTourControleur implements ActionListener {
    private FenetrePrincipale vue;
    private JComboBox select;

    /**
     * Constructeur de la classe AfficherTourControleur
     * @param vue la fenêtre qui affiche le classement
     * @param selection combobox qui indique ce qui doit etre affiché
     */
    public AfficherTourControleur(FenetrePrincipale vue, JComboBox selection) {
        this.vue = vue;
        select = selection;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
       if(vue.getSelectJoueur().isSelected()){
           vue.affichertourparjoueur((Joueur) select.getSelectedItem());
       }else{
           if(vue.getSelectTour().isSelected()){
                vue.afficherTourParTour(select.getSelectedIndex());
           }

       }
    }
}
