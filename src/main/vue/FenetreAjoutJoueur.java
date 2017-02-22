package main.vue;

import main.controleur.AjouterJoueurControlleur;
import main.controleur.DateIndefinieControlleur;
import main.tournoi.Tournoi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.util.ArrayList;
/**
 * Fenêtre permettant l'ajout des joueurs. Implémente l'interface gestion joueur pour forcer l'existance des getteurs nécessaires au controlleurs de dates
 * @author OUAKRIM Yanis, RICHARD Nicolas, ORHON Paul, RIALET Yohann, NIVELAIS Quentin
 * @author DERNONCOURT Cyril, DROUARD Antoine, LE BERT Léa, MARTINEAU Lucas
 * @version 1.1
 */
public class FenetreAjoutJoueur extends JFrame implements GestionJoueur {

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

			//Ajout de la date
			JPanel date = new JPanel();
			//année
			ArrayList anneeCombobox = new ArrayList<Integer>() {
				{
					for (int i = LocalDate.now().getYear(); i > 1900; i--) {
						add(i);
					}
				}
			};
			annee = new JComboBox(anneeCombobox.toArray());
			//mois
			mois = new JComboBox(new String[]{"janv.", "févr.", "mars", "avr.", "mai", "juin", "juil.", "août", "sept.", "oct.", "nov.", "déc."});
			//jour
			ArrayList jourCombobox = new ArrayList<Integer>() {
				{
					for (int i = 1; i <= 31; i++) {
						add(i);
					}
				}
			};
			jour = new JComboBox(jourCombobox.toArray());
			dateIndefinie = new JCheckBox("indefini");
			dateIndefinie.addActionListener(new DateIndefinieControlleur(dateIndefinie, this));
			date.add(jour);
			date.add(mois);
			date.add(annee);
			date.add(dateIndefinie);

			gbc.gridx = 1;
			gbc.gridy = 3;
			corePanel.add(date, gbc);

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
			present = new JCheckBox("Présent");
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
	 * Met a jour le champs du nom
	 * @param str la chaine de caractère à écrire dans le champ de saisie du nom
     */
	public void setNom(String str){
		this.nom.setText(str);
	}

	/**
	 *
	 * @return le champs de présence
	 */
	public JCheckBox getPresent() {
		return present;
	}

	/**
	 *
	 * @return le champ de saisie du prénom
     */
	public JTextField getPrenom() {
		return prenom;
	}

	/**
	 * Met a jour le champs du prénom
	 * @param str la chaine de caractère à écrire dans le champ de saisie du nom
     */
	public void setPrenom(String str){
		this.prenom.setText(str);
	}

	/**
	 *
	 * @return Le combobox du niveau
	 */
	public JComboBox getNiveau() {
		return niveau;
	}

	/**
	 *
	 * @return le tournoi
	 */
	public Tournoi getTournoi() {
		return tournoi;
	}

	/**
	 *
	 * @return le RadioButton homme
	 */
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
	 *
	 * @return la fenetre principale
	 */
	public FenetrePrincipale getFenetrePrincipale() {
		return vue;
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

	/**
	 *
	 * @return le menu déroulant qui donne l'année
	 */
	@Override
	public JComboBox getAnnee() {
		return this.annee;
	}

	/**
	 *
	 * @return le menu déroulant qui donne le mois
	 */
	@Override
	public JComboBox getMois() {
		return this.mois;
	}

	/**
	 *
	 * @return le menu déroulant qui donne le jour
	 */
	@Override
	public JComboBox getJour() {
		return this.jour;
	}

	/**
	 *
	 * @return la case indiquant si on veut donner sa date de naissance ou non
	 */
	@Override
	public JCheckBox getDateIndefinie() {
		return this.dateIndefinie;
	}

}
