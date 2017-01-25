package main.controleur;

import main.tournoi.Joueur;
import main.tournoi.Tournoi;
import main.vue.FenetrePrincipale;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by Lea on 29/12/2016.
 */
public class InverserJoueurControlleur implements ItemListener {
    private FenetrePrincipale vue;
    private Tournoi tournoi;
    private Joueur jprec;//joueru precedament electionné
    private int nuJ;
    private int nuterr;


        public InverserJoueurControlleur(JComboBox jselect, FenetrePrincipale vue){
            this.vue = vue;
            this.tournoi = vue.getTournoi();
            this.jprec = (Joueur) jselect.getSelectedItem();

        }
    @Override
        public void itemStateChanged(ItemEvent event) {
            if (event.getStateChange() == ItemEvent.SELECTED) {
                Joueur jnouv = (Joueur) event.getItem();
                ////  verifier que jnouv n'est pas  ()joueur 420
                if(!(jnouv.getId()==420)){
                    this.tournoi.changerJoueurs(this.jprec,jnouv);
                }
                vue.actualiserTerrains();
            }
        }
}
