package main.controleur;

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

    /**
     *
     * @param v
     * @param t
     * @param tour
     * @param joueur
     */
    public HistoriqueTousControleur(FenetrePrincipale v, Tournoi t, JComboBox tour, JComboBox joueur) {
        this.vue = v;
        this.tournoi = t;
        this.comboTour = tour;
        this.comboJoueur = joueur;
    }

    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
