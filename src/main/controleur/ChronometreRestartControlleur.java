package main.controleur;

import main.tournoi.Chrono;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Antoine Drouard, Cyril Dernoncourt,MArtinneau Lucas, LE BErt Léa on 05/11/16.
 */
public class ChronometreRestartControlleur implements ActionListener {
    private Chrono temps;
    private JButton start;

    /**
     * constructeur du controleur qui permet de recommmencer le chronometre
     * @param t le chrono
     * @param b le bouton auquel ce controleur est appliqué
     */
    public ChronometreRestartControlleur(Chrono t, JButton b)
    {
        this.temps = t;
        this.start = b;
    }

    public void actionPerformed(ActionEvent e) {
        if(temps.getActif()) {
            temps.stop();
            temps.setTempsRestant((int)temps.getTemps());
            temps.repaint();
            start.setText("Lancer");
        } else {
            temps.setTempsRestant((int)temps.getTemps());
            temps.repaint();
        }
    }
}
