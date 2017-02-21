package main.controleur;

import main.tournoi.Chrono;
import main.vue.FenetreDureeChrono;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by E155859M on 07/02/17.
 */
public class ModifierDureeChronoControlleur implements ActionListener {

    private FenetreDureeChrono fenetre;
    private Chrono chrono;
    private JSpinner min;
    private JSpinner sec;

    /**
     * constructeur du controleur qui permet de recommmencer le chronometre
     *
     * @param vue le chrono
     * @param chronometre le bouton auquel ce controleur est appliqué
     * @param minutes La durée max actuelle du chrono en minute
     * @param secondes La durée max actuelle du chrono en seconde
     */
    public ModifierDureeChronoControlleur(FenetreDureeChrono vue, Chrono chronometre, JSpinner minutes, JSpinner secondes) {
        fenetre = vue;
        chrono = chronometre;
        min = minutes;
        sec = secondes;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        chrono.setTempsRestant((int)min.getValue()*60 + (int)sec.getValue());
        chrono.setTemps((int)min.getValue()*60 + (int)sec.getValue());
        chrono.repaint();
        fenetre.dispose();
    }
}
