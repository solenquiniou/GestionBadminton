package main.vue;

import main.controleur.ModifierDureeChronoControlleur;
import main.tournoi.Chrono;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Antoine Drouard, Cyril Dernoncourt,MArtinneau Lucas, LE BErt Léa on 23/01/17.
 */
public class FenetreDureeChrono extends JDialog {


    public FenetreDureeChrono(Chrono chrono) {

        JPanel corePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel label = new JLabel("Durée du chronomètre:");
        gbc.gridwidth = 2;
        corePanel.add(label, gbc);

        JSpinner minutes = new JSpinner(new SpinnerNumberModel(chrono.getTempsMin(), 0, 60, 1));
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        corePanel.add(minutes, gbc);

        JSpinner secondes = new JSpinner(new SpinnerNumberModel(chrono.getTempsSec(), 0, 59, 1));
        gbc.gridx = 1;
        corePanel.add(secondes, gbc);

        JButton valider = new JButton("Valider les modifications");
        valider.addActionListener(new ModifierDureeChronoControlleur(this, chrono, minutes, secondes));
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 2;
        corePanel.add(valider, gbc);

        //On pr�selectionne le bouton valider
        this.getRootPane().setDefaultButton(valider);

        //affichage final
        this.setContentPane(corePanel);
        this.pack();
        this.setVisible(true);
        this.setTitle("Durée du chronomètre");
        this.setLocationRelativeTo(null);
        this.setSize(410, 150);
        this.setResizable(false);
    }
}
