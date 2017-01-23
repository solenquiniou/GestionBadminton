package main.controleur;

import main.tournoi.Tournoi;
import main.vue.FenetrePrincipale;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by E154981H on 14/11/16.
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
            this.tournoi.nouveauTour();
            this.vue.actualiserTerrains();
            //this.main.vue.afficherTournoi();

        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Il n'y a pas de joueurs!! " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }

    }
}
