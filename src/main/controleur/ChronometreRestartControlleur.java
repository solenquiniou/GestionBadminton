package main.controleur;

import main.tournoi.Chrono;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La classe ChronometreRestartControlleur est appliquée au bouton "Redémarrer" qui accompagne le chronometre. Elle stoppe le chronomètre si il est actif et le redémarre à sa durée actuelle.
 * @author DERNONCOURT Cyril , DROUARD Antoine, LE BERT Lea, MARTINEAU Lucas
 */
public class ChronometreRestartControlleur implements ActionListener {
    private Chrono temps;
    private JButton start;

    /**
     * Constructeur du controleur qui permet de recommmencer le chronometre
     * @param t le chrono
     * @param b le bouton auquel ce controleur est appliqué
     */
    public ChronometreRestartControlleur(Chrono t, JButton b)
    {
        this.temps = t;
        this.start = b;
    }

    /**
     * Redémarre le chronomètre et le stoppe si celui-ci est actif.
     * @param e un appuie sur le bouton pour redémarrer le chronometre.
     */
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
