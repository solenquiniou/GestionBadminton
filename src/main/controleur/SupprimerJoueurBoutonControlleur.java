package main.controleur;

import main.vue.FenetreModifierJoueur;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SupprimerJoueurBoutonControlleur implements ActionListener
{
    private FenetreModifierJoueur fmj;
    private int id;

    public SupprimerJoueurBoutonControlleur(FenetreModifierJoueur fmj, int id)
    {
        this.fmj = fmj;
        this.id = id;
    }

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
