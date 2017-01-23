package main.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import main.exception.*;
import main.tournoi.*;
import main.vue.*;

public class NouveauTournoiControleur implements ActionListener {
	private JSpinner nbTerrains;
	private JTextField nom;
	private FenetrePrincipale fenetre;
	private NouveauTournoi nouveauTournoiFen;

	/**
	 * constructeur du main.controleur de la fenêtre de lancement
	 * @param f la fenêtre principale associée
	 * @param leNom le champs de saisie du nom du main.tournoi
	 * @param leNbTerrains l'outil de sélection du nombre de terrains
     * @param nt la fenêtre de lancement associée
     */
	public NouveauTournoiControleur(FenetrePrincipale f, JTextField leNom, JSpinner leNbTerrains, NouveauTournoi nt){
		this.nbTerrains = leNbTerrains;
		this.nom = leNom;
		this.nouveauTournoiFen = nt;
		this.fenetre = f;

	}

	/**
	 * pour créer un main.tournoi
	 * @param e un clic sur me bouton de lancement du main.tournoi
     */
	@Override
	public void actionPerformed(ActionEvent e){
		//On créer un nouveau main.tournoi
		try{
			Tournoi t = new Tournoi((int) this.nbTerrains.getValue(), this.nom.getText());
			//On assigne un main.tournoi à la fenetre principale
			fenetre.setTournoi(t);
			//Fermeture de la fenetre
			this.nouveauTournoiFen.dispose();
		}
		//On vérifie qu'on peut créer un main.tournoi avec les informations indiquées
		catch(NomVideException e1){
			JOptionPane.showMessageDialog(null, "Entrez un nom de main.tournoi", "Erreur", JOptionPane.ERROR_MESSAGE);
		} 
		catch(NbTerrainNeg e1){
			JOptionPane.showMessageDialog(null, "Entrez un nombre de terrain positif", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
		
		
		

	}
}
