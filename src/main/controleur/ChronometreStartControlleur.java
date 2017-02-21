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
     * constructeur du controleur qui permet de lancer le chronometre
     * @param t le chrono
     * @param b le bouton auquel ce controleur est appliqué
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
