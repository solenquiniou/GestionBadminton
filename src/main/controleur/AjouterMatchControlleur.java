package main.controleur;

import main.tournoi.Joueur;
import main.tournoi.Paire;
import main.tournoi.Terrain;
import main.tournoi.Tournoi;
import main.vue.FenetreAjoutMatch;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La calsse AjouterMatchControlleur est appliquée au bouton de validation de la fenêtre d'ajout de match. Ce controlleur ajouter un match avec les informations renseignée dans la fenêtre d'ajout de match.
 * @author DERNONCOURT Cyril , DROUARD Antoine, LE BERT Lea, MARTINEAU Lucas
 */
public class AjouterMatchControlleur implements ActionListener {
    private JSpinner sp_score1;
    private JSpinner sp_score2;
    private FenetreAjoutMatch vue;
    private Tournoi tournoi;
    private int score1;
    private int score2;

    private String j1;
    private String j2;
    private String j3;
    private String j4;


    /**
     * Constructeur de la classe AjouterMatchControlleur
     * @param vue la fenetre FenetreAjoutMatch
     * @param s1 JSpinner du score de la première paire
     * @param s2 JSpinner du score de la seconde paire
     */
    public AjouterMatchControlleur(FenetreAjoutMatch vue,JSpinner s1,JSpinner s2){
        this.vue = vue;
        this.sp_score1 = s1;
        this.sp_score2 = s2;
        this.tournoi = vue.getTournoi();
         j1 = "none";
         j2 = "none";
         j3 = "none";
         j4 = "none";
    }

    /**
     * Ajoute un match
     * @param e un changement de sélection de la valeur du JComboBox
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        score1 = (int)sp_score1.getValue();
        score2 = (int)sp_score2.getValue();
        j1 = ((String) vue.getJoueur1().getSelectedItem());
        j2 = ((String) vue.getJoueur2().getSelectedItem());
        j3 = ((String) vue.getJoueur3().getSelectedItem());
        j4 = ((String) vue.getJoueur4().getSelectedItem());

        //rappatriement des joueurs
        if (verifier()) {
            Joueur j11 = tournoi.chercherJoueur(j1);
            Joueur j21 = tournoi.chercherJoueur(j2);
            Joueur j12 = tournoi.chercherJoueur(j3);
            Joueur j22 = tournoi.chercherJoueur(j4);

            //creation des paires
            Paire paire1 = new Paire(j11, j12);
            Paire paire2 = new Paire(j21,j22);

            Terrain t =new Terrain(tournoi.getNbrTerrains()+1);
            t.setMatch(tournoi.ajouterMatch(paire1, paire2, score1, score2));
            tournoi.getTours().get(tournoi.getNbTour()).addTerr(t);
            vue.getVue().actualiserJoueurs();
            //Comme ça on pourra réouvrir la fenêtre
            FenetreAjoutMatch.setDerniereFenetre(null);

            //fermeture de la fenètre
            vue.dispose();
        }
    }

    /**
     * Méthode vérifiant la validité des informations renseignées
     * @return true si les scores sont valibles false sinon
     */
    private boolean verifier(){
        boolean ret_val = true;

        int test = score1;
        if(test<0){
            JOptionPane.showMessageDialog(vue, "Vous devez entrer un entier positif.");
            ret_val= false;
        }

        test = score2;
        if(test<0){
            JOptionPane.showMessageDialog(vue, "Vous devez entrer un entier positif.","Erreur",JOptionPane.ERROR_MESSAGE);
            ret_val= false;
        }
        if(j1.equals(j2) ||j1.equals(j3) ||j1.equals(j4)||j2.equals(j3) ||j2.equals(j4) ||j3.equals(j4) ){
            JOptionPane.showMessageDialog(vue, "Il y a deux fois le même Joueur","Erreur",JOptionPane.ERROR_MESSAGE);
            ret_val= false;
        }

        return ret_val;
    }

}
