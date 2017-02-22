package main.controleur;

import main.tournoi.Tournoi;
import main.vue.FenetrePrincipale;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controleur de remise a zéro des scores (Permet d'avoir un nouveau tournoi tout en conservant les joueurs)
 * @author DERNONCOURT Cyril, DROUARD Antoine, LE BERT Léa, MARTINEAU Lucas
 * @version 1.1
 */
public class ResetControlleur  implements ActionListener {
    private Tournoi tournoi;
    private FenetrePrincipale vue;

    /**
     * Constructeur de la classe VoirClassementControleur
     * @param vue la vue principale du tournoi
     */
    public ResetControlleur(FenetrePrincipale vue) {
        this.vue = vue;
        this.tournoi = vue.getTournoi();
    }

    /**
     * Remise a zéro de tout les scores
     * @param e evènement du clique sur le boutton "reset" de la vue principale
     */
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
