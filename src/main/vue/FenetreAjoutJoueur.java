package main.vue;

import main.controleur.AjouterJoueurControlleur;
import main.tournoi.Joueur;
import main.tournoi.Tournoi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;

public class FenetreAjoutJoueur extends JFrame {

	private Tournoi tournoi;
	FenetrePrincipale vue;

	private JTextField nom;
	private JTextField prenom;
	private JComboBox niveau;
	private JComboBox age;
	private JRadioButton fem;
	private JRadioButton hom;
	private JCheckBox nouv;


	static private FenetreAjoutJoueur derniereFenetre;

	/**
	 * Permet de remplacer la derniere fenêtre
	 * @param fenetre la fenêtre à remplacer
	 */
	public static void setDerniereFenetre(FenetreAjoutJoueur fenetre) {
		derniereFenetre = fenetre;
	}

	/**
	 * constructeur de la fenêtre d'ajout d'un joueur
	 * @param titre le titre à donner à la fenêtre
	 * @param tournoi le main.tournoi dans lequel on veut ajouter un joueur
	 * @param vue la main.vue qui crée la fenêtre
     */
	public FenetreAjoutJoueur(String titre, Tournoi tournoi, FenetrePrincipale vue){

		if (derniereFenetre == null) {

			this.tournoi = tournoi;
			this.vue = vue;
			JPanel corePanel = new JPanel(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();

			//Ajout du nom
			nom = new JTextField("");
			nom.setPreferredSize( new Dimension( 200, 24 ) );
			nom.addFocusListener(new FocusListener() {
				public void focusGained(FocusEvent e) {
					if (nom.getText().trim().equals("Nom"))
						nom.setText("");
				}

				public void focusLost(FocusEvent e) {
					if (nom.getText().trim().equals(""))
						nom.setText("Nom");
				}
			});
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridx = 0;
			gbc.gridy = 0;
			corePanel.add(nom, gbc);

			//Ajout du prenom
			prenom = new JTextField("Prénom");
			prenom.setPreferredSize( new Dimension( 200, 24 ) );
			prenom.addFocusListener(new FocusListener() {
				public void focusGained(FocusEvent e) {
					if (prenom.getText().trim().equals("Prénom"))
						prenom.setText("");
				}

				public void focusLost(FocusEvent e) {
					if (prenom.getText().trim().equals(""))
						prenom.setText("Prénom");
				}
			});
			gbc.gridx = 1;
			gbc.gridy = 0;
			corePanel.add(prenom, gbc);

			//Ajout de l'âge
			//Il faut bien laisser les âges dans cet ordre pour correspondre avec l'ajout du joueur (0 : Indéfini / 1 : -18 jeune / 2 : 18-35 senior / 3 : 35+ veteran)
			age = new JComboBox(new String[]{"Indéfini", "-18 ans (Jeune)", "18-35 ans (Senior)", "35 ans et + (Veteran)"});
			age.setSelectedIndex(0);
			gbc.gridx = 1;
			gbc.gridy = 3;
			corePanel.add(age, gbc);

			//Ajout du sexe
			hom = new JRadioButton("Homme");
			fem = new JRadioButton("Femme");
			hom.setSelected(true);
			fem.setSelected(false);
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
			niveau.setSelectedIndex(0);
			gbc.gridx = 0;
			gbc.gridy = 3;
			corePanel.add(niveau, gbc);

			//Ajout de nouveau
			nouv = new JCheckBox("Nouveau");
			nouv.setSelected(false);
			gbc.gridx = 1;
			gbc.gridy = 1;
			corePanel.add(nouv, gbc);

			//Ajout presence
			JCheckBox present = new JCheckBox("Présent");
			present.setSelected(true);
			gbc.gridx = 1;
			gbc.gridy = 2;
			corePanel.add(present, gbc);

			//Ajout du bouton de validation
			JButton ajouter = new JButton("Ajouter le joueur");
			ajouter.addActionListener(new AjouterJoueurControlleur(this));
			gbc.gridx = 0;
			gbc.gridy = 4;
			gbc.gridwidth = 2;
			corePanel.add(ajouter, gbc);

			this.setContentPane(corePanel);
			this.pack();
			this.setVisible(true);
			this.setTitle(titre);
			this.setLocation((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()-corePanel.getWidth())/2,(int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()-corePanel.getHeight())/2);
			this.setResizable(false);

			derniereFenetre = this;

		} else {
			derniereFenetre.toFront();
		}
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
	public void ajouterJoueur(){
		int id = Joueur.nbJoueursCrees;
		int age = this.age.getSelectedIndex(); // 0 : -18 jeune / 1 : 18-35 senior / 2 : 35+ veteran
		String nom = this.nom.getText(), prenom = this.prenom.getText();
		boolean sexe = hom.isSelected();
		boolean nouveau = nouv.isSelected();
		int niveau = this.niveau.getSelectedIndex();
		Joueur j = new Joueur(id, nom, prenom, age, sexe, nouveau, niveau, true);
		this.setNom("");
		this.setPrenom("");
		this.setAge();
		tournoi.ajouterJoueur(j);
		vue.ajouterJoueurTable();

	}

	/**
	 * Méthode traitant la fermeture de la fenêtre, afin de pouvoir rendre la derniere fenêtre à null,
	 * pour ne pas pouvoir en ouvrir 2, et réafficher la fenêtre courante en front
	 * @param e L'evenement de fermeture de la fenêtre
     */
	protected void processWindowEvent(WindowEvent e) {
		super.processWindowEvent(e);
		if(e.getID() == WindowEvent.WINDOW_CLOSING) {
			derniereFenetre = null;
		}
	}

}
