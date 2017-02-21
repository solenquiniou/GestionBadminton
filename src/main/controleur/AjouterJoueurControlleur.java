package main.controleur;

import main.tournoi.Joueur;
import main.vue.FenetreAjoutJoueur;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DateTimeException;
import java.time.LocalDate;

public class AjouterJoueurControlleur implements ActionListener {
	
	private FenetreAjoutJoueur vue;
	private JComboBox annee;
	private JComboBox mois;
	private JComboBox jour;
	private LocalDate datej = null;

	/**
	 * constructeur du main.controleur
	 * @param vue la fenetre principale
     */
	public AjouterJoueurControlleur(FenetreAjoutJoueur vue){
		this.vue = vue;
		this.annee = vue.getAnnee();
		this.jour = vue.getJour();
		this.mois = vue.getMois();
	}

	/**
	 * pour ajouter un joueur (voir ajouterJoueur dans FenetrePrincipale)
	 * @param e un clique sur le bouton ajouter un joueur (ou raccourci via menu)
     */
	public void actionPerformed(ActionEvent e) {
		if (verifier()){
			ajouterJoueur();
			//Comme ça on pourra réouvrir la fenêtre
			FenetreAjoutJoueur.setDerniereFenetre(null);
			JOptionPane.showMessageDialog(vue,"Joueur ajouté !");
		}

	}

	/**
	 * pour vérifier si les informations entrées sont valables
	 * @return vrai si on peut ajouter le joueur, faux s'il y a un problème
     */
	public boolean verifier(){
		// On vérifie qu'il y a un nom et un prénom
		if (vue.getNom().getText().equals("") || vue.getPrenom().getText().equals("")){
			JOptionPane.showMessageDialog(vue,"Merci d'indiquer un nom et un prenom","Erreur",JOptionPane.ERROR_MESSAGE);
			return false;
		}

		//On vérifie qu'il n'y ait pas de virgule dans le nom et le prenom pour ne pas poser de pb avec le csv
		if (vue.getNom().getText().contains(",") || vue.getPrenom().getText().contains(",")) {
			JOptionPane.showMessageDialog(vue,"Le prenom et le nom ne doivent pas contenir de virgule","Erreur",JOptionPane.ERROR_MESSAGE);
			return false;
		}

		try{
			datej.of((Integer) this.annee.getSelectedItem(), this.mois.getSelectedIndex() + 1, (Integer) this.jour.getSelectedItem()) ;
		}catch(DateTimeException ex){
			JOptionPane.showMessageDialog(null, "Erreur: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
			return false;
		}


		return true;
	}


	/**
	 * pour ajouter un joueur dans le main.tournoi et dans la liste de la fenetre principale
	 */
	public void ajouterJoueur(){
		int id = Joueur.nbJoueursCrees;
		if(!vue.getDateIndefinie().isSelected()) {
			datej = datej.of((Integer) this.annee.getSelectedItem(), this.mois.getSelectedIndex() + 1, (Integer) this.jour.getSelectedItem()) ;
		}
			String nom = vue.getNom().getText(), prenom = vue.getPrenom().getText();

			boolean sexe = vue.getHom().isSelected();
			boolean nouveau = vue.getNouv().isSelected();
			int niveau = vue.getNiveau().getSelectedIndex();
			boolean pres = vue.getPresent().isSelected();
			Joueur j = new Joueur(id, nom, prenom, datej, sexe, nouveau, niveau, pres);
			vue.setNom("");
			vue.setPrenom("");
			this.setAge();
			vue.getTournoi().ajouterJoueur(j);
			vue.getFenetrePrincipale().ajouterJoueurTable();





	}
	/**
	 * réinitialise l'age
	 */
	public void setAge(){
		LocalDate auj = LocalDate.now();
		this.jour.setSelectedIndex(0);
		this.mois.setSelectedIndex(0);
		this.annee.setSelectedIndex(0);
	}


}
