package main.controleur;

import main.tournoi.Tournoi;
import main.vue.FenetrePrincipale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Antoine Drouard, Cyril Dernoncourt,MArtinneau Lucas, LE BErt Léa on 01/02/17.
 */
public class ExporterClassementControlleur implements ActionListener {
    private Tournoi tournoi;

    public ExporterClassementControlleur(FenetrePrincipale view) {
        this.tournoi = view.getTournoi();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (!(tournoi.getAnciensJoueurs().isEmpty() && tournoi.getNouveauxJoueurs().isEmpty())) {
            //Ouverture de la fenetre "enregistrer sous"
            Frame fr = new Frame("Choississez un répertoire");
            FileDialog dial = new FileDialog(fr, "Enregistrer sous", FileDialog.SAVE);
            dial.setFile(".csv"); //Pré-écrit l'extension .csv dans la fenêtre de dialogue
            dial.setVisible(true);
            fr.setVisible(false);
            try {
                if (dial.getDirectory() != null && dial.getFile() != null) {// Si l'utilisateur a bien spécifié un fichier où écrir
                    //on récupère le chemin spécifié par l'utilisateur
                    tournoi.exportClassement(dial.getDirectory().concat(dial.getFile()));
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

