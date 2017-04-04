package main.controleur;

import main.tournoi.Tournoi;
import main.vue.FenetrePrincipale;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * La classe DeleteKeyListener permet de supprimer des joueurs avec la touche 'Suppr'.
 * @author DERNONCOURT Cyril , DROUARD Antoine, LE BERT Lea, MARTINEAU Lucas
 */
public class DeleteKeyListener implements KeyListener {
    private FenetrePrincipale vue;
    private Tournoi tournoi;
    private JTable listeJoueur;

    /**
     * Le constructeur de la classe DeleteKeyListener
     * @param fenetrePrincipale la fenêtre principale
     * @param listeJoueur la liste des joueurs de la fenere principale
     */
    public DeleteKeyListener(FenetrePrincipale fenetrePrincipale,JTable listeJoueur) {
        vue = fenetrePrincipale;
        tournoi = vue.getTournoi();
        this.listeJoueur = listeJoueur;
    }

    /**
     * Méthode de KeyListener à implémenter
     * @param e à l'appuie d'une touche
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Méthode de KeyListener à implémenter
     * @param e au début de l'appuie d'une touche
     */
    @Override
    public void keyPressed(KeyEvent e) {

    }

    /**
     * Méthode de KeyListener à implémenter. Quand on appuie sur 'Suppr' le joueur peut être supprimé
     * @param e à la fin de l'appuie d'une touche
     */
    @Override
    public void keyReleased(KeyEvent e) {
        Object[] options = { "OUI", "ANNULER" };
        int choix = JOptionPane.showOptionDialog(null, "Vous êtes sur le point de supprimer un joueur, êtes vous sur?", "Vérification",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
        if(choix == 0) {
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
}
