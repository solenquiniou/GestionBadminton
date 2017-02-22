package main.controleur;

import main.tournoi.Tournoi;
import main.vue.FenetrePrincipale;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Controleur de la validation d'un match. Au moment de la validation, les scores des matches sonts calculées dans le model
 * @author DERNONCOURT Cyril, DROUARD Antoine, LE BERT Léa, MARTINEAU Lucas
 * @version 1.1
 */
public class SaisirScoreControlleur implements ActionListener{
	
	private JSpinner scoreP1;
	private JSpinner scoreP2;
	private int score1;
	private int score2;
	private Tournoi tournoi;
	private int numeroTerrain;
	private FenetrePrincipale vue;
	private boolean maj;

	/**
	 * constructeur du controleur pour entrer les scores sur un terrain
	 * @param jt1 le champ de saisie du score de la première paire
	 * @param jt2 le champ de saisie du score de la seconde paire
	 * @param v la fenetre principale
	 * @param t le tournoi
	 * @param numTer le numéro de terrain
     *
     */
	public SaisirScoreControlleur(JSpinner jt1, JSpinner jt2, FenetrePrincipale v, Tournoi t, int numTer){
		scoreP1 = jt1;
		scoreP2 = jt2;
		tournoi = t;
		vue = v;
		numeroTerrain = numTer;
		maj = false;
	}

	/**
	 * pour essayer d'entrer un score
	 * @param e un clic sur le bouton "valider" scores d'un tournoi
     */
	public void actionPerformed(ActionEvent e){
		//recupération des scores en int
		 this.score1 =(int) scoreP1.getValue();
		 this.score2 =(int) scoreP2.getValue();

		if (verifier()) {
			tournoi.setScore(numeroTerrain, score1, score2);
			vue.actualiserJoueurs();
		}
		//fermeture de la fenètre si
		if (!maj){
			maj = true;
			vue.rentrerVerif();
		}
	}

	/**
	 * pour vérifier si on peut entrer les scores
	 * @return true si les scores  et les paires sont valables false sinon
     */
	private boolean verifier(){
		boolean ret_val = true;
		if(this.tournoi.terrainVide(this.numeroTerrain)){
			JOptionPane.showMessageDialog(vue, "Il manque un joueur","Erreur",JOptionPane.ERROR_MESSAGE);
			ret_val= false;
		}

		if(!this.tournoi.paireValide(this.numeroTerrain)){
			JOptionPane.showMessageDialog(vue, "Vous avez modifier les paires et maintenant il y deux fois le même joueur dans ce match ;( ","Erreur",JOptionPane.ERROR_MESSAGE);
			ret_val= false;
		}


		int test = score1;
		if(test<0){
			JOptionPane.showMessageDialog(vue, "Vous devez entrer un entier positif.","Erreur",JOptionPane.ERROR_MESSAGE);
			ret_val= false;
		}


		test = score2;
		if(test<0){
			JOptionPane.showMessageDialog(vue, "Vous devez entrer un entier positif.","Erreur",JOptionPane.ERROR_MESSAGE);
			ret_val= false;
		}

		return ret_val;
	}

}
