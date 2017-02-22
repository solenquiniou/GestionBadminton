package main.controleur;


import main.tournoi.Tournoi;
import main.vue.FenetreClassement;
import main.vue.FenetrePrincipale;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controleur d'affichage du classement
 * @author DERNONCOURT Cyril, DROUARD Antoine, LE BERT Léa, MARTINEAU Lucas
 * @version 1.1
 */
public class VoirClassementControleur implements ActionListener
{
    private main.tournoi.Tournoi tournoi;
    private FenetrePrincipale vue;

    /**
     * Constructeur de la classe VoirClassementControleur
     * @param tournoi le tournoi d'où importer les joueurs
     * @param vue la vue principale du tournoi
     */
    public VoirClassementControleur(Tournoi tournoi,FenetrePrincipale vue)
    {
        this.tournoi = tournoi;
        this.vue = vue;
    }

    /**
     * ouverture de la fenête
     * @param e evènement du clique sur le boutton "classement" de la vue principale
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(verif()){
            new FenetreClassement("Classement", tournoi);
        }
    }

    /**
     * Méthode qui vérifie si l'action est possible:
     * Si il y a des joueurs dans le tournoi et si il y a déja eut au moins un tour
     */
    public Boolean verif(){
        if (tournoi.nbJoueur()<1){
            JOptionPane.showMessageDialog(vue, "Il n'y a pas encore de Joueurs","Erreur",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (tournoi.getNbTour()<1){
            JOptionPane.showMessageDialog(vue,"Le tournoi n'a pas commencé!!","Erreur",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

}