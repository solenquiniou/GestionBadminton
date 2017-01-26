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
     * constructeur de la fenêtre classement
     * @param b le boutton
     * @param t le main.tournoi dans lequel on veut voir le classement
     */
    public ChronometreRestartControlleur(Chrono t, JButton b)
    {
        this.temps = t;
        this.start = b;
    }

    public void actionPerformed(ActionEvent e) {
        if(temps.getActif()) {
            temps.stop();
            temps.setTempsRestant(300);
            temps.repaint();
            start.setText("Lancer");
        } else {
            temps.setTempsRestant(300);
            temps.repaint();
        }
    }
}
