package main.vue;

import main.controleur.SupprimerJoueurBoutonControlleur;
import main.tournoi.Joueur;
import main.tournoi.Tournoi;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FenetreModifierJoueur extends JFrame {

	private Tournoi tournoi;
	FenetrePrincipale vue;
	private JTextField nom;
	private JTextField prenom;
	private JComboBox niveau;
	private JComboBox age;
	private JRadioButton fem;
	private JRadioButton hom;
	private JCheckBox nouv;
	private JCheckBox present;

	static private FenetreModifierJoueur derniereFenetre;

	/**
	 * constructeur de la fenêtre d'ajout d'un joueur
	 * @param titre le titre à donner à la fenêtre
	 * @param tournoi le main.tournoi dans lequel on veut ajouter un joueur
	 * @param vue la main.vue qui crée la fenêtre
     */
	public FenetreModifierJoueur(String titre, Tournoi tournoi, FenetrePrincipale vue, int id){

		this.tournoi = tournoi;
		this.vue = vue;

		if (derniereFenetre != null)
			derniereFenetre.dispose();

		Joueur joueur = this.tournoi.getJoueur(id);

		JPanel corePanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		//Ajout du nom
		nom = new JTextField(joueur.getNom());
		nom.setPreferredSize( new Dimension( 200, 24 ) );
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		corePanel.add(nom, gbc);

		//Ajout du prenom
		prenom = new JTextField(joueur.getPrenom());
		prenom.setPreferredSize( new Dimension( 200, 24 ) );
		gbc.gridx = 1;
		gbc.gridy = 0;
		corePanel.add(prenom, gbc);

		//Ajout de l'âge
		//Il faut bien laisser les âges dans cet ordre pour correspondre avec l'ajout du joueur (0 : Indéfini / 1 : -18 jeune / 2 : 18-35 senior / 3 : 35+ veteran)
		age = new JComboBox(new String[]{"Indéfini", "-18 ans (Jeune)", "18-35 ans (Senior)", "35 ans et + (Veteran)"});
		age.setSelectedIndex(joueur.getAge());
		gbc.gridx = 1;
		gbc.gridy = 3;
		corePanel.add(age, gbc);

		//Ajout du sexe
		hom = new JRadioButton("Homme");
		fem = new JRadioButton("Femme");
		if(!joueur.getSexe()) {
			hom.setSelected(false);
			fem.setSelected(true);
		} else {
			hom.setSelected(true);
			fem.setSelected(false);
		}
		ButtonGroup grSexe = new ButtonGroup();
		grSexe.add(hom);
		grSexe.add(fem);
		gbc.gridx = 0;
		gbc.gridy = 1;
		corePanel.add(hom, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		corePanel.add(fem, gbc);

		//Ajout du niveau
		//Il faut bien laisser les niveaux dans cet ordre pour correspondre avec l'ajout du joueur (0 : Indéfini / 1 : Débutant / 2 : Intermédiaire / 3 : Confirmé)
		niveau = new JComboBox(new String[]{"Indéfini", "Débutant", "Intermédiaire", "Confirmé"});
		niveau.setSelectedIndex(joueur.getNiveau());
		gbc.gridx = 0;
		gbc.gridy = 3;
		corePanel.add(niveau, gbc);

		//Ajout de nouveau
		nouv = new JCheckBox("Nouveau");
		if(joueur.getNouveau()) {
			nouv.setSelected(true);
		} else {
			nouv.setSelected(false);
		}
		gbc.gridx = 1;
		gbc.gridy = 1;
		corePanel.add(nouv, gbc);

		//Ajout presence
		present = new JCheckBox("Présent");
		if(joueur.peutJouer()) {
			present.setSelected(true);
		} else {
			present.setSelected(false);
		}
		gbc.gridx = 1;
		gbc.gridy = 2;
		corePanel.add(present, gbc);

		//Ajout des boutons de validation de modif et de supression de joueur
		JButton modifier = new JButton("Valider les modifications");
		modifier.addActionListener(new ModifierJoueurBoutonControlleur(this, id));
		gbc.gridx = 1;
		gbc.gridy = 4;
		corePanel.add(modifier, gbc);
		JButton supprimer = new JButton("Supprimer le joueur");
		supprimer.addActionListener(new SupprimerJoueurBoutonControlleur(this, id));
		gbc.gridx = 0;
		gbc.gridy = 4;
		corePanel.add(supprimer, gbc);


		this.setContentPane(corePanel);
		this.pack();
		this.setVisible(true);
		this.setTitle(titre);
		this.setLocation((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()-corePanel.getWidth())/2,(int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()-corePanel.getHeight())/2);
		this.setResizable(false);

		derniereFenetre = this;
	}

	/**
	 *
	 * @return le champ de saisi du nom
     */
	public JTextField getNom() {
		return nom;
	}

	/**
	 *
	 * @param str la chaine de caractère à écrire dans le champ de saisie du nom
     */
	public void setNom(String str){
		this.nom.setText(str);
	}

	/**
	 *
	 * @return le champ de saisie du prénom
     */
	public JTextField getPrenom() {
		return prenom;
	}

	/**
	 *
	 * @param str la chaine de caractère à écrire dans le champ de saisie du nom
     */
	public void setPrenom(String str){
		this.prenom.setText(str);
	}

	public JComboBox getNiveau() {
		return niveau;
	}

	/**
	 *
	 * @return l'outil de séléction de l'âge
     */
	public JComboBox getAge() {
		return age;
	}

	/**
	 * réinitialise l'age
	 */
	public void setAge(){
		this.age.setSelectedIndex(0);
	}

	public JRadioButton getFem() {
		return fem;
	}


	public JRadioButton getHom() {
		return hom;
	}

	/**
	 *
	 * @return la case qui indique l'ancienneté
     */
	public JCheckBox getNouv() {
		return nouv;
	}

	/**
	 * pour ajouter un joueur dans le main.tournoi et dans la liste de la fenetre principale
	 */
	public void modifierJoueur(int id)
	{
		int age = this.age.getSelectedIndex(); // 0 : -18 jeune / 1 : 18-35 senior / 2 : 35+ veteran
		String nom = this.nom.getText(), prenom = this.prenom.getText();
		boolean sexe = !fem.isSelected();
		boolean nouve = nouv.isSelected();
		int niveau = this.niveau.getSelectedIndex();
		this.tournoi.modifierJoueur(id, nom, prenom, age, sexe, nouve, niveau);
		if(present.isSelected()) {
			this.tournoi.getJoueur(id).setPeutJouer(true);
		} else {
			this.tournoi.getJoueur(id).setPeutJouer(false);
		}
		this.vue.actualiserJoueurs();
		dispose();
	}

	public void supprimerJoueur(int id)
	{
		Joueur jou = this.tournoi.getJoueur(id);
		boolean trouve = false;
		int i = 0;
		Joueur aSupprimer = new Joueur(id, true, true);

		if(jou.getNouveau())
		{
			ArrayList nouveauxJoueurs = this.tournoi.getNouveauxJoueurs();
			int tailleNouveauxJoueurs = nouveauxJoueurs.size();

			while(!trouve && i < tailleNouveauxJoueurs)
			{
				Joueur j = (Joueur) nouveauxJoueurs.get(i);
				int a = j.getId();
				if (a == id)
				{
					aSupprimer = j;
					trouve = true;
				}
				i++;
			}
		}

		else
		{
			ArrayList anciensJoueurs = this.tournoi.getAnciensJoueurs();
			int tailleAnciensJoueurs = anciensJoueurs.size();

			while(!trouve && i < tailleAnciensJoueurs)
			{
				Joueur j = (Joueur) anciensJoueurs.get(i);
				int a = j.getId();
				if (a == id)
				{
					aSupprimer = j;
					trouve = true;
				}
				i++;
			}
		}

		this.tournoi.supprimerJoueur(aSupprimer);
		this.vue.supprimerJoueurTable();
		this.vue.actualiserJoueurs();
		this.vue.actualiserNoms();
		dispose();

	}


}
