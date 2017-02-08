package main.vue;

import main.controleur.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Menu extends JMenuBar{
	//Il n'est pas possible de proceder à certaines actions tant que le main.tournoi n'a pas été crée ou affiché
	private ArrayList<JMenuItem> aAutoriser;

	public Menu(final FenetrePrincipale fen){
		super();
		//////////////////////////////
		//Dans le menu Fichier///////
		/////////////////////////////
		JMenu menuFichier = new JMenu("Fichier");
		JMenu menuEdition = new JMenu("Édition");
		//On inctancie la liste des item à auatoriser
		this.aAutoriser = new ArrayList<JMenuItem>();
		//On creer le bouton nouveau main.tournoi
		JMenuItem nouveauTournoi = new JMenuItem("Nouveau...");
		nouveauTournoi.setAccelerator(KeyStroke.getKeyStroke('N', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		nouveauTournoi.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				Object[] options = { "OUI", "ANNULER" };
				int choix = JOptionPane.showOptionDialog(null, "Le tournoi en cours sera abandoné. Êtes vous sur de vouloir en commencer un nouveau?", "Vérification",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
				if(choix == 0) {
					NouveauTournoi tourn = new NouveauTournoi(fen);
				}

			}
		});
		menuFichier.add(nouveauTournoi);
		this.add(menuFichier);
		this.add(menuEdition);

		//Ouvrir un fichier
		JMenuItem ouvrir = new JMenuItem("Ouvrir...");
		ouvrir.addActionListener(new ImporterTournoiControlleur(fen));
		ouvrir.setAccelerator(KeyStroke.getKeyStroke('O', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		menuFichier.add(ouvrir);
		menuFichier.addSeparator();

		//Importer des joueurs
		JMenuItem importerJ = new JMenuItem("Importer des joueurs");
		importerJ.addActionListener(new ImporterJoueursControlleur(fen));
		importerJ.setAccelerator(KeyStroke.getKeyStroke('I', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		menuFichier.add(importerJ);

		//Exporter des joueurs
		JMenuItem exporterJ = new JMenuItem("Exporter des joueurs");
		exporterJ.addActionListener(new ExporterJoueursControlleur(fen));
		exporterJ.setAccelerator(KeyStroke.getKeyStroke('E', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		menuFichier.add(exporterJ);
		menuFichier.addSeparator();

		//On creer le bouton enregister
		JMenuItem enregistrer = new JMenuItem("Enregistrer");
		enregistrer.setAccelerator(KeyStroke.getKeyStroke('S', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		menuFichier.add(enregistrer);
		this.aAutoriser.add(enregistrer);
		enregistrer.setEnabled(false);

		//On creer le bouton enregister sous
		JMenuItem enregistrerSous = new JMenuItem("Enregistrer sous...");
		enregistrerSous.setAccelerator(KeyStroke.getKeyStroke('S', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()+KeyEvent.SHIFT_MASK));
		enregistrerSous.setEnabled(false);
		enregistrerSous.addActionListener(new ExporterTournoiControlleur(fen));
		menuFichier.add(enregistrerSous);
		this.aAutoriser.add(enregistrerSous);


		//On creer le bouton exporter
		JMenuItem exporterC = new JMenuItem("Exporter le classement...");
		exporterC.setAccelerator(KeyStroke.getKeyStroke('R', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		exporterC.addActionListener(new ExporterClassementControlleur(fen));
		menuFichier.add(exporterC);
		this.aAutoriser.add(exporterC);

		//////////////////////////////
		//Dans le menu édition///////
		/////////////////////////////
		//ajouter joueur
		JMenuItem ajouterJoueur = new JMenuItem("Nouveau joueur");
		ajouterJoueur.setAccelerator(KeyStroke.getKeyStroke('J', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		ajouterJoueur.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fen.fenetreAjout();
			}

		});
		ajouterJoueur.setEnabled(false);
		menuEdition.add(ajouterJoueur);
		this.aAutoriser.add(ajouterJoueur);

		///editer nb terrain
		JMenuItem nbTerrain = new JMenuItem("Changer Nombre de Terrains");
		nbTerrain.setAccelerator(KeyStroke.getKeyStroke('T', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		nbTerrain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new FenetreNbTerrain(fen.getTournoi());
			}
		});
		nbTerrain.setEnabled(false);
		menuEdition.add(nbTerrain);
		this.aAutoriser.add(nbTerrain);

		///editer la durée du chrono
		JMenuItem dureeChrono = new JMenuItem("Changer la durée du chrono");
		dureeChrono.setAccelerator(KeyStroke.getKeyStroke('H', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		dureeChrono.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new FenetreDureeChrono(fen.getChrono());
			}
		});
		dureeChrono.setEnabled(false);
		menuEdition.add(dureeChrono);
		this.aAutoriser.add(dureeChrono);


	}
	public void enableSave(){
		for(int i = 0; i<this.aAutoriser.size();i++){
			(this.aAutoriser.get(i)).setEnabled(true);
		}
	}

}