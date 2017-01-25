package main.controleur;

import main.tournoi.Tournoi;
import main.vue.FenetrePrincipale;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Antoine Drouard, Cyril Dernoncourt,MArtinneau Lucas, LE BErt Léa on 02/11/16.
 */
public class ResetControlleur  implements ActionListener {
    private Tournoi tournoi;
    private FenetrePrincipale vue;



    public ResetControlleur(FenetrePrincipale vue) {
        this.vue = vue;
        this.tournoi = vue.getTournoi();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object[] options = { "OUI", "ANNULER" };
        int choix = JOptionPane.showOptionDialog(null, "Êtes vous sûr de vouloir remettre les scores à zéro?", "Vérification",
                                     JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
        if(choix == 0) {
            tournoi.resetAll();
        }
        vue.actualiserJoueurs();
    }
}
