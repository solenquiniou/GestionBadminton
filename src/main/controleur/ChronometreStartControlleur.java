package main.controleur;

import main.tournoi.Chrono;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Antoine Drouard, Cyril Dernoncourt,MArtinneau Lucas, LE BErt Léa on 05/11/16.
 */
public class ChronometreStartControlleur implements ActionListener {
    private Chrono temps;
    private JButton bouton;

    /**
     * constructeur de la fenêtre classement
     * @param b le titre à donner à la fenêtre
     * @param t le main.tournoi dans lequel on veut voir le classement
     */
    public ChronometreStartControlleur(Chrono t, JButton b)
    {
        this.temps = t;
        this.bouton = b;
    }

    public void actionPerformed(ActionEvent e) {
        if(temps.getActif()) {
            temps.stop();
            bouton.setText("Lancer");
        } else {
            temps.start();
            bouton.setText("Stopper");
        }
    }
}
