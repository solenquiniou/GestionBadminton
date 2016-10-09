package VueSimple;

import java.awt.BorderLayout; 
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import tournoi.*;

public class FenetreModifierJoueur extends JFrame {

	private Tournoi tournoi;
	FenetrePrincipale vue;
	private int id;

	private JTextField nom;
	private JTextField prenom;
	private JComboBox niveau;
	private JSpinner age;
	private JRadioButton fem;
	private JRadioButton hom;
	private JCheckBox nouv;

	/**
	 * constructeur de la fenêtre d'ajout d'un joueur
	 * @param titre le titre à donner à la fenêtre
	 * @param tournoi le tournoi dans lequel on veut ajouter un joueur
	 * @param vue la vue qui crée la fenêtre
     */
	public FenetreModifierJoueur(String titre, Tournoi tournoi, FenetrePrincipale vue, int id){

		this.tournoi = tournoi;
		this.vue = vue;

		//Les différents champs de saisie
		nom = new JTextField();
		prenom = new JTextField();
		niveau = new JComboBox(new String[]{"Debutant","Intermediaire", "Confirme"});
		age = new JSpinner();
		fem = new JRadioButton("Femme");
		hom = new JRadioButton("Homme");
		hom.setSelected(true);
		ButtonGroup grSexe = new ButtonGroup();
		grSexe.add(hom);
		grSexe.add(fem);
		nouv = new JCheckBox("Nouveau");
		nouv.setSelected(true);
		
		JButton modifier = new JButton("Modifier le joueur");
		modifier.addActionListener(new ModifierJoueurBoutonControlleur(this));
		
		JPanel corePanel = new JPanel();
		corePanel.setLayout(new BorderLayout());
		
		JPanel gauche = new JPanel();
		
		gauche.setLayout(new GridLayout(3,2));
		gauche.add(new JLabel("Nom :"));
		gauche.add(nom);
		
		JPanel panelSexe = new JPanel();
		panelSexe.setLayout(new GridLayout(2,1));
		panelSexe.add(hom);
		panelSexe.add(fem);
		
		gauche.add(new JLabel("Sexe :"));
		gauche.add(panelSexe);
		
		gauche.add(new JLabel("Niveau :"));
		gauche.add(niveau);
		
		corePanel.add(gauche,BorderLayout.WEST);
		
		JPanel droite = new JPanel();
		
		droite.setLayout(new GridLayout(3,2));
		droite.add(new JLabel("Prenom :"));
		droite.add(prenom);
		
		JPanel panelAnc = new JPanel();
		panelAnc.setLayout(new GridLayout(2,1));
		panelAnc.add(nouv);
		
		droite.add(new JLabel("Anciennete :"));
		droite.add(panelAnc);
		
		droite.add(new JLabel("Age :"));
		droite.add(age);
		
		corePanel.add(droite,BorderLayout.EAST);
		
		corePanel.add(modifier,BorderLayout.SOUTH);
		
		this.setContentPane(corePanel);
		this.pack();
		this.setVisible(true);
		this.setTitle(titre);
		int tailleX = 600, tailleY = 200;
		this.setLocation((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()-tailleX)/2,(int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()-tailleY)/2);
		this.setSize(tailleX,tailleY);
		this.setResizable(false);
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
	 * @returnle champ de saisie du prénom
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
	public JSpinner getAge() {
		return age;
	}

	/**
	 * réinitialise l'age
	 */
	public void setAge(){
		this.age.setValue(0);
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
	 * pour ajouter un joueur dans le tournoi et dans la liste de la fenetre principale
	 */
	public void modifierJoueur(){
		int id = Joueur.nbJoueursCrees;
		int age = ((int)this.age.getValue());
		String nom = this.nom.getText(), prenom = this.prenom.getText();
		boolean sexe = fem.isSelected();
		boolean nouve = nouv.isSelected();
		int niveau = this.niveau.getSelectedIndex();
		this.tournoi.modifierJoueur(id, nom, prenom, age, sexe, nouve, niveau);
		this.vue.actualiserJoueurs();

	}


}
