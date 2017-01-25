package main.controleur;

import main.tournoi.Tournoi;
import main.vue.FenetreNbTerrain;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Antoine Drouard, Cyril Dernoncourt,MArtinneau Lucas, LE BErt Léa on 23/01/17.
 */
public class ChangerNbrTerrainControlleur implements ActionListener {
    private Tournoi tournoi;
    private JSpinner nb;
    private FenetreNbTerrain vue;


    public ChangerNbrTerrainControlleur(Tournoi t,JSpinner nb,FenetreNbTerrain vue) {
        this.nb = nb;
        this.tournoi = t;
        this.vue = vue;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.tournoi.setNbrTerrains((int)nb.getValue());
        vue.dispose();

    }

}
