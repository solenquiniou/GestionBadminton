package main.controleur;

import main.tournoi.Tournoi;
import main.vue.FenetreNbTerrain;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La classe ChangerNbrTerrainControlleur est appliqué à la fenêtre de changement du nombre de terrain. Ce controlleur change le nombre de terrain au nombre renseignée dans le JSpinner paramètre
 * @author DERNONCOURT Cyril , DROUARD Antoine, LE BERT Lea, MARTINEAU Lucas
 */
public class ChangerNbrTerrainControlleur implements ActionListener {
    private Tournoi tournoi;
    private JSpinner nb;
    private FenetreNbTerrain vue;

    /**
     * Le constructeur de la calsse ChangerNbrTerrainControlleur
     * @param t le tournoi
     * @param nb le JSpinner contenant le nombre de terrain souhaité
     * @param vue la fenêtre à laquelle le controlleur est appliqué
     */
    public ChangerNbrTerrainControlleur(Tournoi t,JSpinner nb,FenetreNbTerrain vue) {
        this.nb = nb;
        this.tournoi = t;
        this.vue = vue;
    }

    /**
     * Change le nombre de terrain du tournoi en cours à la valeur contenue dans le JSpinner
     * @param e un appuie sur le bouton de validation de la fenêtre de changement du nombre de terrain.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.tournoi.setNbrTerrains((int)nb.getValue());
        vue.dispose();
    }

}
