package main.controleur;

import main.tournoi.Tournoi;
import main.vue.FenetreAjoutMatch;
import main.vue.FenetrePrincipale;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by lea on 09/10/16.
 */
public class newMatchController implements ActionListener{
        private Tournoi tournoi;
        private FenetrePrincipale vue;



        public newMatchController(Tournoi tournoi) {
        this.tournoi = tournoi;
    }


    public void actionPerformed(ActionEvent e) {
        new FenetreAjoutMatch("Entrer un match",tournoi,vue);
    }
}

