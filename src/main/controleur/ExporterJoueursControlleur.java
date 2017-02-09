package main.controleur;

import main.tournoi.Joueur;
import main.tournoi.Tournoi;
import main.vue.FenetrePrincipale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;

/** La classe ExporterJoueursControlleur permet d'exporter les anciens ainsi que les nouveaux
 *  joueurs contenus dans la classe main.tournoi dans un fichier CSV
 * @author DERNONCOURT Cyril , DROUARD Antoine, LE BERT Lea, MARTINEAU Lucas
 */
public class ExporterJoueursControlleur implements ActionListener {

    private final Tournoi tournoi;

    /** Constructeur de la classe ExporterJoueursControlleur
     *
     * @param vue la vue principale
     */
    public ExporterJoueursControlleur(FenetrePrincipale vue) {
        this.tournoi = vue.getTournoi();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Si il y des joueurs dans le main.tournoi
        if (!(tournoi.getAnciensJoueurs().isEmpty())) {
            //Ouverture de la fenetre "enregistrer sous"
            Frame fr = new Frame("Choississez un répertoire");
            FileDialog dial = new FileDialog(fr, "Enregistrer sous", FileDialog.SAVE);
            dial.setFile(".csv"); //Pré-écrit l'extension .csv dans la fenêtre de dialogue
            dial.setVisible(true);
            fr.setVisible(false);
            try {
                if (dial.getDirectory() != null && dial.getFile() != null) {// Si l'utilisateur a bien spécifié un fichier où écrire
                    //on récupère le chemin spécifié par l'utilisateur
                    tournoi.exportJoueurs(dial.getDirectory().concat(dial.getFile()));
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } else {
            //S'il n'y a pas de joueurs dans le main.tournoi
            JOptionPane.showMessageDialog(null, "Il n'y a pas de joueurs à exporter", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }


}