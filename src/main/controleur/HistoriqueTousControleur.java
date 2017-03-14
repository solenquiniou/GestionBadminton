package main.controleur;

import main.tournoi.Joueur;
import main.tournoi.Tournoi;
import main.vue.FenetrePrincipale;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La classe HistoriqueTousControleur permet d'afficher l'historique de chaque joueur ainsi que celui de chaque match..
 * @author DERNONCOURT Cyril , DROUARD Antoine, LE BERT Lea, MARTINEAU Lucas
 */
public class HistoriqueTousControleur implements ActionListener {

    private FenetrePrincipale vue;
    private Tournoi tournoi;
    private JComboBox comboTour;
    private JComboBox comboJoueur;
    private JRadioButton selected;

    /**
     *
     * @param v
     * @param tour
     * @param joueur
     * @parma selected
     */
    public HistoriqueTousControleur(FenetrePrincipale v, JComboBox tour, JComboBox joueur, JRadioButton selected) {
        this.vue = v;
        this.tournoi = v.getTournoi();
        this.comboTour = tour;
        this.comboJoueur = joueur;
        this.selected = selected;
    }

    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
            if (selected.equals(vue.getSelectJoueur())){

                this.comboTour.setEnabled(false);
                this.comboJoueur.setEnabled(true);
                vue.affichertourparjoueur((Joueur) this.comboJoueur.getSelectedItem());
            }else{
                if (selected.equals(vue.getSelectTour())){

                    this.comboJoueur.setEnabled(false);
                    this.comboTour.setEnabled(true);
                    if(tournoi.getNbTour()>1){
                        vue.afficherTourParTour(this.comboTour.getSelectedIndex());
                    }



                }else{

                    this.comboTour.setEnabled(false);
                    this.comboJoueur.setEnabled(false);
                    System.out.println("nb tours:" + tournoi.getNbTour());
                    vue.afficherallTours();
                }
            }
    }
}
