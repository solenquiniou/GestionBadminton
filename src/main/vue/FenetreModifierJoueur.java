package main.vue;

import main.controleur.DateInfefinieControlleur;
import main.controleur.ModifierJoueurBoutonControlleur;
import main.controleur.SupprimerJoueurBoutonControlleur;
import main.tournoi.Joueur;
import main.tournoi.Tournoi;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class FenetreModifierJoueur extends JFrame implements GestionJoueur {

	private Tournoi tournoi;
	FenetrePrincipale vue;
	private JTextField nom;
	private JTextField prenom;
	private JComboBox niveau;
	private JRadioButton fem;
	private JRadioButton hom;
	private JCheckBox nouv;
	private JCheckBox present;
	private JComboBox annee;
	private JComboBox mois;
	private JComboBox jour;
	private JCheckBox dateIndefinie;



	static private FenetreModifierJoueur derniereFenetre;

	/**
	 * constructeur de la fenêtre d'ajout d'un joueur
	 * @param titre le titre à donner à la fenêtre
	 * @param tournoi le main.tournoi dans lequel on veut ajouter un joueur
	 * @param vue la main.vue qui crée la fenêtre
	 * @param joueur le joueur a modifier
     */
	public FenetreModifierJoueur(String titre, Tournoi tournoi, FenetrePrincipale vue, Joueur joueur){

		this.tournoi = tournoi;
		this.vue = vue;

		if (derniereFenetre != null)
			derniereFenetre.dispose();


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

		JPanel date = new JPanel();
		ArrayList anneeCombobox = new ArrayList<Integer>() {
			{
				for (int i = LocalDate.now().getYear(); i > 1900; i--) {
					add(i);
				}
			}
		};
		annee = new JComboBox(anneeCombobox.toArray());
		mois = new JComboBox(new String[]{"janv.", "févr.", "mars", "avr.", "mai", "juin", "juil.", "juill.", "août", "sept.", "oct.", "nov.", "déc."});
		ArrayList jourCombobox = new ArrayList<Integer>() {
			{
				for (int i = 1; i <= 31; i++) {
					add(i);
				}
			}
		};
		jour = new JComboBox(jourCombobox.toArray());
		dateIndefinie = new JCheckBox("indefini");
		dateIndefinie.addActionListener(new DateInfefinieControlleur(dateIndefinie, this));
		date.add(jour);
		date.add(mois);
		date.add(annee);
		date.add(dateIndefinie);
		if (joueur.getDateN()==null)
		{
			dateIndefinie.setSelected(true);
			annee.setEnabled(false);
			jour.setEnabled(false);
			mois.setEnabled(false);
		}else{
			annee.setSelectedIndex(LocalDate.now().getYear() - joueur.getDateN().getYear());
			mois.setSelectedIndex(joueur.getDateN().getMonth().getValue()-1);
			jour.setSelectedIndex(joueur.getDateN().getDayOfMonth()-1);

		}
		gbc.gridx = 1;
		gbc.gridy = 3;
		corePanel.add(date, gbc);

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
		modifier.addActionListener(new ModifierJoueurBoutonControlleur(this, joueur.getId()));
		gbc.gridx = 1;
		gbc.gridy = 4;
		corePanel.add(modifier, gbc);
		JButton supprimer = new JButton("Supprimer le joueur");
		supprimer.addActionListener(new SupprimerJoueurBoutonControlleur(this, joueur.getId()));
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
	/**
	 * pour ajouter un joueur dans le main.tournoi et dans la liste de la fenetre principale
	 */
	public void modifierJoueur(int id)
	{
		LocalDate date = LocalDate.now();
		date.of((Integer)getAnnee().getSelectedItem(),  getMois().getSelectedIndex() +1, (Integer) getJour().getSelectedItem()) ;
		String nom = this.nom.getText(), prenom = this.prenom.getText();
		boolean sexe = !fem.isSelected();
		boolean nouve = nouv.isSelected();
		int niveau = this.niveau.getSelectedIndex();
		this.tournoi.modifierJoueur(id, nom, prenom, date, sexe, nouve, niveau);
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
		JTable table = this.vue.getListeJoueurs();
		int i;
		boolean supprimer = false;
		for (i = 0; i < this.vue.getListeJoueurs().getRowCount(); i++) {
			if((int)(table.getModel()).getValueAt(i,0) == id) {
				((DefaultTableModel)table.getModel()).removeRow(i);
				supprimer = true;
			}
		}
		if (supprimer)
			this.tournoi.supprimerJoueur(tournoi.getJoueur(id));
		dispose();
	}


	@Override
	public JComboBox getAnnee() {
		return this.annee;
	}

	@Override
	public JComboBox getMois() {
		return this.mois;
	}

	@Override
	public JComboBox getJour() {
		return this.jour;
	}

	@Override
	public JCheckBox getDateIndefinie() {
		return this.dateIndefinie;
	}
}
