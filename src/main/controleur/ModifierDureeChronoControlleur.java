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
    private int min;
    private int sec;

    public ModifierDureeChronoControlleur(FenetreDureeChrono vue, Chrono chronometre, JSpinner minutes, JSpinner secondes) {
        fenetre = vue;
        chrono = chronometre;
        min = (int)minutes.getValue();
        sec = (int)secondes.getValue();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("min: " + min + " sec: " + sec + " total: " + (min*60+sec));
        chrono.setTemps(min*60 + sec);
        fenetre.dispose();
    }
}
