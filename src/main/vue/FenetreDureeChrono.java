package main.vue;

import main.controleur.ModifierDureeChronoControlleur;
import main.tournoi.Chrono;

import javax.swing.*;
import java.awt.*;

/**
 * Fenêtre permettant de changer la durée du chronomètre
 * @author DERNONCOURT Cyril, DROUARD Antoine, LE BERT Léa, MARTINEAU Lucas
 * @version 1.1
 */
public class FenetreDureeChrono extends JDialog {

    /**
     * constructeur de la fenêtre durée chrono
     * @param chrono le chronomètre
     */
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
        this.setTitle("Durée du chronomètre");
        this.setSize(410, 150);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }
}
