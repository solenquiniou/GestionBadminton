package main.controleur;

import main.tournoi.Joueur;
import main.tournoi.Match;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Antoine Drouard, Cyril Dernoncourt,MArtinneau Lucas, LE BErt Léa on 08/01/17.
 */
public class ValiderMatchControlleur implements ActionListener{
    private Match match;
    private JComboBox<Joueur> comboJ1;
    private JComboBox<Joueur> comboJ2;
    private JComboBox<Joueur> comboJ3;
    private JComboBox<Joueur> comboJ4;
    private JSpinner spinnerE1;
    private JSpinner spinnerE2;
    private JButton buttonValider;


    public ValiderMatchControlleur(Match match, JComboBox<Joueur> j1, JComboBox<Joueur> j2, JSpinner e1, JComboBox<Joueur> j3, JComboBox<Joueur> j4, JSpinner e2, JButton valider) {
        this.match = match;
        this.comboJ1 = j1;
        this.comboJ2 = j2;
        this.spinnerE1 = e1;
        this.comboJ3 = j3;
        this.comboJ4 = j4;
        this.spinnerE2 = e2;
        this.buttonValider = valider;
    }

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