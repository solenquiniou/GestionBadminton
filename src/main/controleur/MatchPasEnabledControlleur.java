package main.controleur;

import main.tournoi.Joueur;
import main.tournoi.Match;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controleur de validation d'un match
 * @author DERNONCOURT Cyril, DROUARD Antoine, LE BERT Léa, MARTINEAU Lucas
 * @version 1.1
 */
public class MatchPasEnabledControlleur implements ActionListener{
    private Match match;
    private JComboBox<Joueur> comboJ1;
    private JComboBox<Joueur> comboJ2;
    private JComboBox<Joueur> comboJ3;
    private JComboBox<Joueur> comboJ4;
    private JSpinner spinnerE1;
    private JSpinner spinnerE2;
    private JButton buttonValider;

    /**
     * Constructeur du controlleur des boutons valider de l'onglet terrain
     * @param match le match concerné
     * @param j1 Le premier joueur de la première paire
     * @param j2 Le deuxième joueur de la première paire
     * @param e1 le spinner du score de la première paire
     * @param j3 Le premier joueur de la deuxième paire
     * @param j4 Le deuxième joueur de la deuxième paire
     * @param e2 le spinner du score de la deuxième paire
     * @parma valider le boutton que le controleur écoute
     */
    public MatchPasEnabledControlleur(Match match, JComboBox<Joueur> j1, JComboBox<Joueur> j2, JSpinner e1, JComboBox<Joueur> j3, JComboBox<Joueur> j4, JSpinner e2, JButton valider) {
        this.match = match;
        this.comboJ1 = j1;
        this.comboJ2 = j2;
        this.spinnerE1 = e1;
        this.comboJ3 = j3;
        this.comboJ4 = j4;
        this.spinnerE2 = e2;
        this.buttonValider = valider;
    }

    /**
     * Mise en gris du terrain dont les scores ont étés validés
     * @param e evènement du clique sur le boutton "validé" d' un tournoi
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //Le match est fini -> il n'est donc plus en cours
        match.setEnCours(false);

        //Match validé -> on désactive ses composants
        comboJ1.setEnabled(false);
        comboJ2.setEnabled(false);
        spinnerE1.setEnabled(false);
        comboJ3.setEnabled(false);
        comboJ4.setEnabled(false);
        spinnerE2.setEnabled(false);
        buttonValider.setEnabled(false);
    }
}