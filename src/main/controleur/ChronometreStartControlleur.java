package main.controleur;

import main.tournoi.Chrono;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La classe ChronometreStartControlleur est appliquée au bouton "Lancer"/"Stopper" qui accompagne le chronometre. Elle lance ou stoppe le chronomètre en fonction de son état.
 * @author DERNONCOURT Cyril , DROUARD Antoine, LE BERT Lea, MARTINEAU Lucas
 */
public class ChronometreStartControlleur implements ActionListener {
    private Chrono temps;
    private JButton bouton;

    /**
     * Constructeur du controleur qui permet de lancer/stopper le chronometre
     * @param t le chrono
     * @param b le bouton auquel ce controleur est appliqué
     */
    public ChronometreStartControlleur(Chrono t, JButton b)
    {
        this.temps = t;
        this.bouton = b;
    }

    /**
     * Lance le chronomètre si il est stoppé, stoppe le chronomètre si il est lancé.
     * @param e un appuie sur le bouton de lancement/arrêt du chronometre.
     */
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
