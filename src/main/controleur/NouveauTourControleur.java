package main.controleur;

import main.tournoi.Tournoi;
import main.vue.FenetrePrincipale;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Antoine Drouard, Cyril Dernoncourt,MArtinneau Lucas, LE BErt Léa on 14/11/16.
 */
public class NouveauTourControleur implements ActionListener {
    private FenetrePrincipale vue;
    private Tournoi tournoi;
    public NouveauTourControleur(FenetrePrincipale vue){
        this.vue = vue;
        this.tournoi = vue.getTournoi();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            if(tournoi.getNbTour()>0){
                this.tournoi.finirTour();//enregistre le tour et nettoye les paires
            }

            this.tournoi.nouveauTour();
            this.vue.actualiserTerrains();
            //this.main.vue.afficherTournoi();

        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Erreur: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }

    }
}



