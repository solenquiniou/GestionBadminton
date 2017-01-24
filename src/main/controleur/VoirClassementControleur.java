package main.controleur;


import main.tournoi.Tournoi;
import main.vue.FenetreClassement;
import main.vue.FenetrePrincipale;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** La classe VoirClassementControleur permet d'ouvrir une fenêtre contenant le classement
 * @author DERNONCOURT Cyril , DROUARD Antoine, LE BERT Lea, MARTINEAU Lucas
 */

public class VoirClassementControleur implements ActionListener
{
    private main.tournoi.Tournoi tournoi;
    private FenetrePrincipale vue;

    /**
     * Constructeur de la classe VoirClassementControleur
     *
     * @param tournoi le main.tournoi où importer les joueurs
     */
    public VoirClassementControleur(Tournoi tournoi,FenetrePrincipale vue)
    {
        this.tournoi = tournoi;
        this.vue = vue;
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(verif()){
            new FenetreClassement("Classement", tournoi);
        }
    }

    public Boolean verif(){
        if (tournoi.nbJoueur()<1){
            JOptionPane.showMessageDialog(vue, "Il n'y a pas encore de Joueurs");
            return false;
        }
        if (tournoi.getNbTour()<1){
            JOptionPane.showMessageDialog(vue,"Le tournoi n'a pas commencé!!");
            return false;
        }
        return true;
    }

}