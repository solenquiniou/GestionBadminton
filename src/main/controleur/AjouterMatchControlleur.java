package main.controleur;

import main.tournoi.Joueur;
import main.tournoi.Paire;
import main.tournoi.Tournoi;
import main.vue.FenetreAjoutMatch;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by lea on 09/10/16.
 * @author DERNONCOURT Cyril , DROUARD Antoine, LE BERT Lea, MARTINEAU Lucas
 */
public class AjouterMatchControlleur implements ActionListener {
    private JSpinner sp_score1;
    private JSpinner sp_score2;
    private FenetreAjoutMatch vue;
    private Tournoi tournoi;
    private int score1;
    private int score2;
    private Paire paire1;
    private Paire paire2;
    private String j1;
    private String j2;
    private String j3;
    private String j4;


    /**
     * constructeur du main.controleur
     * @param vue la fenetre FenetreAjoutMatch
     * @param s1 JSpinner du score de la paire1
     * @param s2 JSpinner du score de la paire1
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
            paire1 = new Paire(j11, j12);
            paire2 = new Paire(j21,j22);

            tournoi.ajouterMatch(paire1, paire2, score1, score2);
            vue.getVue().actualiserJoueurs();

            //Comme ça on pourra réouvrir la fenêtre
            vue.setDerniereFenetre(null);

            //fermeture de la fenètre
            vue.dispose();
        }
    }

    /**
     * pour vérifier si on peut entrer les scores
     * @return true si les scores sont valables false sinon
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
            JOptionPane.showMessageDialog(vue, "Vous devez entrer un entier positif.");
            ret_val= false;
        }
        if(j1 == "none" ||j2 == "none" ||j3 == "none" ||j4 == "none" ){
            JOptionPane.showMessageDialog(vue, "il manque un joueur");
            ret_val= false;
        }
        if(j1 == j2 ||j1 == j3 ||j1 == j4||j2 == j3 ||j2 == j4 ||j3 == j4 ){
            JOptionPane.showMessageDialog(vue, "Il y a deux fois le même Joueur");
            ret_val= false;
        }



        return ret_val;
    }

}
