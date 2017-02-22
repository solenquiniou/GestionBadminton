package main.controleur;

import main.vue.FenetreModifierJoueur;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controleur d'affichage du classement
 * @author DERNONCOURT Cyril, DROUARD Antoine, LE BERT Léa, MARTINEAU Lucas
 * @version 1.1
 */
public class SupprimerJoueurBoutonControlleur implements ActionListener
{
    private FenetreModifierJoueur fmj;
    private int id;

    /**
     * Constructeur de la classe VoirClassementControleur
     * @param id l'id du joueur a supprimer
     * @param fmj la fenètre de de modification des joueur contenant le boutton supprimé
     */
    public SupprimerJoueurBoutonControlleur(FenetreModifierJoueur fmj, int id)
    {
        this.fmj = fmj;
        this.id = id;
    }

    /**
     * Suppression du joueur dont l'id est en attribut
     * @param e evènement du clique sur le boutton "supprimer" de la vue de modification d'un joueur
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        JTable table = fmj.getfenetrePrincipale().getListeJoueurs();
        int i;
        boolean supprimer = false;
        for (i = 0; i < fmj.getfenetrePrincipale().getListeJoueurs().getRowCount(); i++) {
            if((int)(table.getModel()).getValueAt(i,0) == id) {
                ((DefaultTableModel)table.getModel()).removeRow(i);
                supprimer = true;
            }
        }
        if (supprimer)
            fmj.getTournoi().supprimerJoueur(fmj.getTournoi().getJoueur(id));
        fmj.dispose();
    }
}
