package main.controleur;

import main.tournoi.Tournoi;
import main.vue.FenetrePrincipale;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by LE Bert Léa on 06/02/17.
 */
public class DeleteKeyListener implements KeyListener {
    private FenetrePrincipale vue;
    private Tournoi tournoi;
    private JTable listeJoueur;
    public DeleteKeyListener(FenetrePrincipale fenetrePrincipale,JTable listeJoueur) {
        vue = fenetrePrincipale;
        tournoi = vue.getTournoi();
        this.listeJoueur = listeJoueur;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == 127 && listeJoueur.getSelectedRow() != -1)
        {

            int[] index_lignes =  listeJoueur.getSelectedRows();
            for (int ligne :index_lignes){
                String nom = (String)listeJoueur.getValueAt(ligne, 0)+" "+listeJoueur.getValueAt(ligne, 1);
                tournoi.supprimerJoueur(tournoi.chercherJoueur(nom));

            }
            vue.actualiserJoueurs();


        }
    }
}
