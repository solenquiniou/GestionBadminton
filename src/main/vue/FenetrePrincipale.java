package main.vue;

import de.javasoft.plaf.synthetica.SyntheticaPlainLookAndFeel;
import main.controleur.*;
import main.tournoi.Chrono;
import main.tournoi.Joueur;
import main.tournoi.Tournoi;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
/**
 * Fenêtre permettant l'ajout des joueurs. Implémente l'interface gestion joueur pour forcer l'existance des getteurs nécessaires au controlleurs de dates
 * @author OUAKRIM Yanis, RICHARD Nicolas, ORHON Paul, RIALET Yohann, NIVELAIS Quentin
 * @author DERNONCOURT Cyril, DROUARD Antoine, LE BERT Léa, MARTINEAU Lucas
 * @version 1.1
 */
public class FenetrePrincipale extends JFrame {

	//La fen�tre principale � un main.tournoi surlequel elle peut agir
	private Tournoi tournoi;
	private DefaultTableModel listeJoueursModele;//pour l'affichage des joueurs
	private DefaultTableModel listeTourModel;
	private JTable listeJoueurs;
	private JTable listeTours;
	private String[] joueurs;
	private ArrayList<JComboBox> boxTerrains = new ArrayList<>();
	private JPanel tournois;
	private JPanel tours;
	private JTabbedPane onglets;
	private Chrono chronometre;
	private int verif;

	/**
	 * Constructeur de la classe FenetrePrincipale
	 * @param titre le titre que l'on souhaite donner à la fenêtre
	 */
	public FenetrePrincipale(String titre) {
		super(titre);

		//On charge le look and feel du syst�me de l'utilisateur (� la place de GTK) auquel il est habitu�
		try {
			UIManager.setLookAndFeel(new SyntheticaPlainLookAndFeel());
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			SwingUtilities. updateComponentTreeUI(this);
			//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,this, "Problème rencontré: " + e.getMessage(),JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

		//Les declarations de base
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setTitle(titre);
		this.setLocation(0, 0);
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
		this.setVisible(true);
		this.setJMenuBar(new MenuDebut(this));
	}

	/**
	 * Méthode permetant de définir le tournoi
	 * @param t le tournoi avec lequel la fenêtre va interagir
	 */
	public void setTournoi(Tournoi t){
		this.tournoi=t;
		this.setTitle(t.getNom());
		//On assigne le menu � la fenetres
		this.setJMenuBar(new Menu(this));
		((Menu) this.getJMenuBar()).enableSave();
		this.afficherTournoi();
	}

	/**
	 * Méthode retournant le tournoi
	 * @return le tournoi
	 */
	public Tournoi getTournoi(){
		return this.tournoi;
	}

	/**
	 * Méthode retournant la liste des joueurs
	 * @return la liste des joueurs
     */
	public  JTable getListeJoueurs() {
		return this.listeJoueurs;
	}

	/**
	 * initialise l'affichage de la fenêtre
	 */
	public void afficherTournoi(){
		onglets = new JTabbedPane(SwingConstants.TOP);
		this.joueurs= new String[]{"Pas de joueur"};

		//Notre onglet pour les joueur
		JPanel joueurs = new JPanel();
		joueurs.setLayout(new BorderLayout());
		String  title[] = {"Nom", "Prénom", "Score","Ancienneté","Present"};
		listeJoueursModele = new DefaultTableModel(title,0){
			@Override
			//bien redefinir les types des colones pour que l'autosort marche
			public Class getColumnClass(int column) {
				switch (column) {
					case 0:
						return String.class;
					case 1:
						return String.class;
					case 2:
						return Integer.class;
					case 3:
						return String.class;
					case 4:
						return String.class;
					default:
						return String.class;
				}
			}

			//Pour ne pas pouvoir editer une valeur en doublecliquant
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		listeJoueurs = new JTable(listeJoueursModele);
		listeJoueurs.getTableHeader().setReorderingAllowed(false);
		listeJoueurs.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);//selection multiple
		//supression par la touche suppr
		listeJoueurs.addKeyListener(new DeleteKeyListener(this,listeJoueurs));
		//modif d'un joueur en doublecliquant sur le joueur
		listeJoueurs.addMouseListener(new ModifierJoueurControlleur(this,listeJoueurs));
		//Nous ajoutons notre tableau à notre contentPane dans un scroll
		//Sinon les titres des colonnes ne s'afficheront pas !
		listeJoueurs.setAutoCreateRowSorter(true);
		JScrollPane panJoueurs = new JScrollPane(listeJoueurs);

		joueurs.add(panJoueurs,BorderLayout.CENTER);

		//panel west qui contiens les boutons
		JPanel westButtonPan = new JPanel(new GridBagLayout());
		GridBagConstraints gbcWest = new GridBagConstraints();

		//Ajout du chronometre
		chronometre = new Chrono(300);
		gbcWest.gridx = 0;
		gbcWest.gridy = 0;
		westButtonPan.add(chronometre, gbcWest);
		chronometre.stop();

		//Ajout du bouton Lancer/Pauser
		JButton start;
		start = new JButton("Lancer");
		start.setPreferredSize(new Dimension(140, 40));
		gbcWest.gridy += 1;
		westButtonPan.add(start, gbcWest);
		start.addActionListener(new ChronometreStartControlleur(chronometre, start));

		//Ajout du bouton Redémarrer
		JButton restart;
		restart = new JButton("Redémarrer");
		restart.setPreferredSize(new Dimension(140, 40));
		gbcWest.gridy += 1;
		westButtonPan.add(restart, gbcWest);
		restart.addActionListener(new ChronometreRestartControlleur(chronometre, start));

		//JPanel d'espace
		JPanel espace =new JPanel();
		espace.setPreferredSize(new Dimension(140, 40));
		gbcWest.gridy += 1;
		westButtonPan.add(espace, gbcWest);

		//Ajout d'un joueur
		JButton ajouterJoueur = new JButton("Ajouter un joueur");
		ajouterJoueur.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fenetreAjout();
			}
		});
		ajouterJoueur.setPreferredSize(new Dimension(140, 40));
		gbcWest.gridy += 1;
		westButtonPan.add(ajouterJoueur, gbcWest);


		//Nouveau Tour
		JButton setPaires = new JButton("Nouveau Tour");
		setPaires.addActionListener(new NouveauTourControleur(this));
		setPaires.setPreferredSize(new Dimension(140, 40));
		gbcWest.gridy += 1;
		westButtonPan.add(setPaires, gbcWest);

		//Bouton Ajout match (ajout manuel d'un score entre deux joueurs :
		JButton newMatch = new JButton("Ajouter un match");
		newMatch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fenetreAjoutMatch();
			}
		});
		newMatch.setPreferredSize(new Dimension(140, 40));
		gbcWest.gridy += 1;
		westButtonPan.add(newMatch, gbcWest);

		//JPanel d'espace
		JPanel espace2 =new JPanel();
		espace2.setPreferredSize(new Dimension(140, 40));
		gbcWest.gridy += 1;
		westButtonPan.add(espace2, gbcWest);

		//Bouton reset tout les scores a zero
		JButton reset = new JButton("reset scores");
		reset.addActionListener(new ResetControlleur(this));
		reset.setPreferredSize(new Dimension(140, 40));
		gbcWest.gridy += 1;
		westButtonPan.add(reset, gbcWest);

		//Bouton pour voir le classement
		JButton classement = new JButton("Classement");
		classement.addActionListener(new VoirClassementControleur(tournoi,this));
		classement.setPreferredSize(new Dimension(140, 40));
		gbcWest.gridy += 1;
		westButtonPan.add(classement, gbcWest);
		westButtonPan.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.gray));


		////Onglet Tournoi
		tournois = new JPanel();
		tournois.setLayout(new BorderLayout());

		////Onglet historique des tours
		tours = new JPanel();
		tours.setLayout(new BorderLayout());
		initialiserOngletTour();


		//On ajoute tous les onglets
		onglets.addTab("Joueurs", joueurs);
		onglets.addTab("Tournois", tournois);
		onglets.addTab("Tours Précédents", tours);
		onglets.setOpaque(true);


		JPanel principal = new JPanel();
		principal.setLayout(new BorderLayout());
		principal.add(onglets, BorderLayout.CENTER);
		principal.add(westButtonPan,BorderLayout.WEST);
		this.setContentPane(principal);
		this.setVisible(true);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
	}

	/**
	 * pour dire que l'on a rentré les résultats d'un match
	 */
	public void rentrerVerif(){
		verif--;
	}

	/**
	 * pour créer une fenêtre d'ajout de joueur
	 */
	public void fenetreAjout(){
		new FenetreAjoutJoueur("Ajouter un joueur",tournoi,this);
	}

	/**
	 * pour créer une fenêtre d'ajout de match
	 */
	public void fenetreAjoutMatch(){
		if(tournoi.nbJoueur()>0){
			new FenetreAjoutMatch("Entrer nouveau Match",tournoi,this);
		}else{
			JOptionPane.showMessageDialog(null, "Il n'y a aucun joueur","erreur",JOptionPane.ERROR_MESSAGE);
		}

	}

	/**
	 * pour actualiser l'affichage de joueurs dans l'onglet joueur
	 */
	public void actualiserJoueurs(){
		this.listeJoueursModele.setRowCount(0);
		ArrayList<Joueur> allJoueur = tournoi.getAllJoueurs();
		//On rentre les joueurs anciens dans les X premières cases
		for (Joueur j : allJoueur)
		{
			ajouterJoueurTable(j);
		}

	}

	/**
	 * Méthode permetant d'ajouter un joueur à la liste des joueurs
	 * @param j le joueur à ajouter
     */
	public void ajouterJoueurTable(Joueur j)
	{
		Object[] tJ = {j.getNom(), j.getPrenom(), j.getScore(), j.getAnciennte(), j.statut()};
		this.listeJoueursModele.addRow(tJ);
	}



	/**
	 * on vérirife que tous les terrains on eu leurs scores rentrés
	 * @return vrai si on peut finir le tour faux sinon
	 */
	public boolean verifFinir(){
		return verif==0;
	}

	/**
	 * pour mettre à jour la liste des noms de joueurs
	 */
	public void actualiserNoms(){
		joueurs = new String[tournoi.getNouveauxJoueurs().size()+tournoi.getAnciensJoueurs().size()+1];
		for (int i = 0; i < tournoi.getNouveauxJoueurs().size(); i++){
			Joueur j = tournoi.getNouveauxJoueurs().get(i);
			joueurs[i] = j.getNom()+" "+j.getPrenom();
		}
		for (int i = 0;i<tournoi.getAnciensJoueurs().size();i++){
			Joueur j = tournoi.getAnciensJoueurs().get(i);
			joueurs[i+tournoi.getNouveauxJoueurs().size()] = j.getNom()+" "+j.getPrenom();
		}
		joueurs[tournoi.getNouveauxJoueurs().size()+tournoi.getAnciensJoueurs().size()] = "Pas de joueur";
	}

	/**
	 * pour insérer un joueur dans la liste des joueurs (onglet joueurs)
	 */
	public void ajouterJoueurTable(){
		Object[]tJ = {};
		this.listeJoueursModele.addRow(tJ);
		this.actualiserJoueurs();
		this.actualiserNoms();
	}

	/**
	 * Méthode qui crée l'interface d'un terrain dans l'onglet terrain
	 * @param nuterr le numéro du terrain
	 * @return le jpanel correspondant à l'interface du terrain nuterr
     */
	public JPanel nouveauTerrain(int nuterr){
		JPanel terrain = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		ArrayList<Joueur> joueurDansCombo = tournoi.getAllJoueurs();
		//joueurDansCombo.add(new Joueur(420, "", "", 42, true, true, 0, false));
		Collections.sort(joueurDansCombo, new Comparator<Joueur>() {
			@Override
			public int compare(Joueur j1, Joueur j2) {
				return j1.toString().compareToIgnoreCase(j2.toString());
			}
		});

		//Terrain n° X
		int o = nuterr + 1;
		JLabel numTer = new JLabel("Terrain "+o);
		gbc.gridx = 0;
		gbc.gridy = 0;
		terrain.add(numTer, gbc);

		//Equipe 1 et leur score
		JComboBox j1 = new JComboBox();
		JComboBox j2 = new JComboBox();
		if(!this.tournoi.tournoisVide()) {
			j1 = new JComboBox(joueurDansCombo.toArray());
			j2 = new JComboBox(joueurDansCombo.toArray());
			try {
				if((nuterr*2) <= tournoi.getAllJoueurs().size()) {
					j1.setSelectedItem(tournoi.getTerrain(nuterr).getMatch().getPaire1().getJoueur1());//depuis terrain je recupere match qui donne les paires qui donne les joueurs
				} else {
					j1.setSelectedIndex(0);
				}
				if((nuterr*2)+1 <= tournoi.getAllJoueurs().size()) {
					j2.setSelectedItem(tournoi.getTerrain(nuterr).getMatch().getPaire1().getJoueur2());
				} else {
					j2.setSelectedIndex(0);
				}
			} catch(Exception e) {
				//System.out.println("moins de joueur que de terrain?");
			}
		}
		JSpinner score1 = new JSpinner(new SpinnerNumberModel(0,0,Integer.MAX_VALUE,1));
		JPanel equipeUn = new JPanel(new GridBagLayout());
		j1.setPreferredSize(new Dimension(125, 25));
		j2.setPreferredSize(new Dimension(125, 25));
		score1.setPreferredSize(new Dimension(50, 30));
		GridBagConstraints gbcUn = new GridBagConstraints();
		gbcUn.gridx = 0;
		gbcUn.gridy = 0;
		equipeUn.add(j1, gbcUn);
		gbcUn.gridx = 1;
		equipeUn.add(j2, gbcUn);
		gbcUn.gridx = 2;
		equipeUn.add(score1, gbcUn);
		gbc.gridy = 1;

		//padding bas pour centrer la separation
		Border border = equipeUn.getBorder();
		Border margin = new EmptyBorder(0,0,10,0);
		equipeUn.setBorder(new CompoundBorder(border, margin));
		terrain.add(equipeUn, gbc);


		//Equipe 2 et leur score
		JComboBox j3 = new JComboBox();
		JComboBox j4 = new JComboBox();
		if(!this.tournoi.tournoisVide()) {
			j3 = new JComboBox(joueurDansCombo.toArray());
			j4 = new JComboBox(joueurDansCombo.toArray());
			try {
				if((nuterr*2)+2 <= tournoi.getAllJoueurs().size()) {
					j3.setSelectedItem(tournoi.getTerrain(nuterr).getMatch().getPaire2().getJoueur1());
				} else {
					j3.setSelectedIndex(0);
				}
				if((nuterr*2)+3 <= tournoi.getAllJoueurs().size()) {
					j4.setSelectedItem(tournoi.getTerrain(nuterr).getMatch().getPaire2().getJoueur2());
				} else {
					j4.setSelectedIndex(0);
				}
			} catch(Exception e) {
				//System.out.println("moins de joueur que de terrain?");
			}
		}

		JSpinner score2 = new JSpinner(new SpinnerNumberModel(0,0,Integer.MAX_VALUE,1));
		JPanel equipeDeux = new JPanel(new GridBagLayout());
		j3.setPreferredSize(new Dimension(125, 25));
		j4.setPreferredSize(new Dimension(125, 25));
		score2.setPreferredSize(new Dimension(50, 30));
		GridBagConstraints gbcDeux = new GridBagConstraints();
		gbcDeux.gridx = 0;
		gbcDeux.gridy = 0;
		equipeDeux.add(j3);
		gbcDeux.gridx = 1;
		equipeDeux.add(j4);
		gbcDeux.gridx = 2;
		equipeDeux.add(score2);
		gbc.gridy = 3;

		//Border et padding
		equipeDeux.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.gray));
		border = equipeDeux.getBorder();
		margin = new EmptyBorder(10,0,0,0);
		equipeDeux.setBorder(new CompoundBorder(border, margin));
		terrain.add(equipeDeux, gbc);

		//Bouton valider
		JButton valider = new JButton("Valider");
		valider.addActionListener(new SaisirScoreControlleur(score1,score2,this,this.tournoi,nuterr));
		valider.addActionListener(new MatchPasEnabledControlleur(tournoi.getTerrain(nuterr).getMatch(), j1, j2, score1, j3, j4, score2, valider));
		gbc.gridy = 4;
		terrain.add(valider, gbc);

		//Ajout des JComboBox au tableau qui les regroupe
		this.boxTerrains.add(j1);
		this.boxTerrains.add(j2);
		this.boxTerrains.add(j3);
		this.boxTerrains.add(j4);
		j1.addItemListener(new InverserJoueurControlleur(j1, this));
		j2.addItemListener(new InverserJoueurControlleur(j2,this));
		j3.addItemListener(new InverserJoueurControlleur(j3,this));
		j4.addItemListener(new InverserJoueurControlleur(j4, this));

		//design: gestion des vordures et des paddings
		terrain.setBorder(BorderFactory.createLineBorder(Color.gray));
		border = terrain.getBorder();
		margin = new EmptyBorder(10,10,10,10);
		terrain.setBorder(new CompoundBorder(border, margin));
		JPanel terrainContainer = new JPanel();

		terrainContainer.add(terrain);
		return  terrainContainer;
	}

	/**
	 * Méthode permettant d'actualiser le terrain en fonction des terrains du modèle
     */
	public void actualiserTerrains() {
		tournois = new JPanel();
		tournois.setLayout(new BorderLayout());
		JPanel panTour = new JPanel();

		panTour.setLayout(new GridLayout((int)Math.floor(this.tournoi.getNbrTerrains()/((int) Math.floor(this.getBounds().width/600))), (int) Math.floor(this.getBounds().width/600), 10, 10));
		//On parcours les terrains pour les afficher

		ArrayList<Joueur> joueursActifs = new ArrayList<>();

		//ArrayList des nouveaux joueurs actifs
		for (Joueur joueur : tournoi.getAllJoueurs()) {
			if (joueur.peutJouer()) {
				joueursActifs.add(joueur);
			}
		}

		if(this.tournoi.getNbrTerrains() > joueursActifs.size()/4) {
			for (int i = 0; i < joueursActifs.size()/4; i++) {
				panTour.add(nouveauTerrain(i));
			}
		} else {
			for (int i = 0; i < this.tournoi.getNbrTerrains(); i++) {
				panTour.add(nouveauTerrain(i));
			}
		}

		JScrollPane terrains = new JScrollPane(panTour);
		terrains.getVerticalScrollBar().setUnitIncrement(20);

		tournois.add(terrains, BorderLayout.CENTER);
		onglets.removeTabAt(1);
		onglets.addTab("Tournois", tournois);
		onglets.setSelectedIndex(1);
	}

	/**
	 * Initialise l' onglet tour
	 *
	 */
	public void initialiserOngletTour(){
		ArrayList allTours = tournoi.getTours();
		ArrayList<Integer> nbtours = new ArrayList<Integer>();
		for (int i = 0; i < allTours.size(); i++)
			nbtours.add(i+1);


		//combobox de choix de quoi afficher

		String  entetes[] = {"Tour","Paire 1", "Paire 1", "Paire 2","Paire 2","Score"};
		listeTourModel = new DefaultTableModel(entetes, 0)
		{
			//bien redefinir les types des colones pour que l'autosort marche
			public Class getColumnClass(int column)
			{
				switch (column)
				{
					case 0:
						return Integer.class;
					case 1:
						return String.class;
					case 2:
						return String.class;
					case 3:
						return String.class;
					case 4:
						return String.class;
					case 5:
						return Integer.class;
					default:
						return String.class;
				}
			}
		};
		listeTours = new JTable(listeTourModel);
		listeTours.setAutoCreateRowSorter(true);
		JScrollPane panTour = new JScrollPane(listeTours);
		tours.add(panTour,BorderLayout.CENTER);

		//menu de choix de l'affichage
		JPanel menuChoix = new JPanel();
		menuChoix.setLayout(new GridLayout(1, 5));

		JRadioButton tout = new JRadioButton("Tous");
		JRadioButton selectJoueur = new JRadioButton("Tour");
		JRadioButton selectTour = new JRadioButton("Joueur");
		tout.setSelected(true);
		selectJoueur.setSelected(false);
		selectTour.setSelected(false);

		ButtonGroup grChoix = new ButtonGroup();
		grChoix.add(selectJoueur);
		grChoix.add(selectTour);
		grChoix.add(tout);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		menuChoix.add(tout, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		menuChoix.add(selectJoueur, gbc);
		ArrayList<Joueur> joueurDansCombo = tournoi.getAllJoueurs();
		JComboBox listeTour = new JComboBox(nbtours.toArray());

		//listeTour.setEnabled(false);
		gbc.gridx = 0;
		gbc.gridy = 3;
		menuChoix.add(listeTour, gbc);
		gbc.gridx = 0;
		gbc.gridy = 4;
		menuChoix.add(selectTour, gbc);
		JComboBox listeJoueur = new JComboBox(joueurDansCombo.toArray());
		listeJoueur.setEnabled(false);
		gbc.gridx = 0;
		gbc.gridy = 5;
		menuChoix.add(listeJoueur, gbc);






		tours.add(menuChoix, BorderLayout.NORTH);






	}
	/**
	 *
	 * @return le chronomètre
	 */
	public Chrono getChrono() {
		return chronometre;
	}

}
