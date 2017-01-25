package main.controleur;

import main.tournoi.Tournoi;
import main.vue.FenetreAjoutMatch;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Antoine Drouard, Cyril Dernoncourt,MArtinneau Lucas, LE BErt Léa on 09/10/16.
 */
public class newMatchController implements ActionListener{
        private Tournoi tournoi;





        public newMatchController(Tournoi tournoi) {
        this.tournoi = tournoi;
    }


    public void actionPerformed(ActionEvent e) {
        new FenetreAjoutMatch("Entrer un match",tournoi);
    }
}

