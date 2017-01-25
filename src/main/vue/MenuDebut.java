package main.vue;

import main.controleur.ImporterTournoiControlleur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Antoine Drouard, Cyril Dernoncourt,MArtinneau Lucas, LE BErt Léa on 25/01/17.
 */
public class MenuDebut extends  JMenuBar {
        //Il n'est pas possible de proceder à certaines actions tant que le main.tournoi n'a pas été crée ou affiché
        private ArrayList<JMenuItem> aAutoriser;

        public MenuDebut(final FenetrePrincipale fen){
            super();
            JMenu menuFichier = new JMenu("Fichier");
            //On inctancie la liste des item à auatoriser
            this.aAutoriser = new ArrayList<JMenuItem>();
            //On creer le bouton nouveau main.tournoi
            JMenuItem nouveauTournoi = new JMenuItem("Nouveau...");
            nouveauTournoi.setAccelerator(KeyStroke.getKeyStroke('N', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
            nouveauTournoi.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                        NouveauTournoi tourn = new NouveauTournoi(fen);
                }
            });
            menuFichier.add(nouveauTournoi);
            this.add(menuFichier);

            //Ouvrir un fichier
            JMenuItem ouvrir = new JMenuItem("Ouvrir...");
            ouvrir.addActionListener(new ImporterTournoiControlleur(fen));
            ouvrir.setAccelerator(KeyStroke.getKeyStroke('O', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
            menuFichier.add(ouvrir);
            menuFichier.addSeparator();




        }


    }
