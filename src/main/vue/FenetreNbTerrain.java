package main.vue;

import main.controleur.ChangerNbrTerrainControlleur;
import main.tournoi.Tournoi;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Fenêtre permettant l'ajout des joueurs. Implémente l'interface gestion joueur pour forcer l'existance des getteurs nécessaires au controlleurs de dates
 * @author DERNONCOURT Cyril, DROUARD Antoine, LE BERT Léa, MARTINEAU Lucas
 * @version 1.1
 */
public class FenetreNbTerrain extends JDialog {

    /**
     * constructeur de la fenêtre de modification du nombre de terrains
     * @param tournoi le main.tournoi dans lequel on veut ajouter un joueur
     */
    public FenetreNbTerrain(Tournoi tournoi) {
        JPanel container = new JPanel();
        container.setLayout(new BorderLayout());

        //on cr�er les �l�ments
        JTextField nom = new JTextField();
        //On crée un Jspinner ayant pour valeur de base 1, un minimum de 1, un maximum de Integer.MAX_VALUE et un pas de 1
        JSpinner nbTerrains = new JSpinner(new SpinnerNumberModel(tournoi.getNbrTerrains(),1,Integer.MAX_VALUE,1));
        JLabel nbTerrainsLabel = new JLabel("Nombre de terrains : ", SwingConstants.RIGHT);

        //On les ajoute a des panels de position
        JPanel etiquettes = new JPanel();
        etiquettes.setLayout(new GridLayout(2,1));
        etiquettes.add(nbTerrainsLabel);

        //On creer un panel de marge a droite
        JPanel margeDroite = new JPanel();
        margeDroite.setPreferredSize(new Dimension(60, 30));

        //On creer un panel de marge en haut
        JPanel margeHaut = new JPanel();
        margeHaut.setPreferredSize(new Dimension(30, 15));

        //On creer un panel de marge en bas
        JPanel margeBas = new JPanel();
        margeBas.setPreferredSize(new Dimension(30, 20));

        //On cr�er un panel avec les champs de texte
        JPanel champsTexte = new JPanel();
        champsTexte.setLayout(new GridLayout(2, 1, 0, 5));
        champsTexte.add(nbTerrains);

        //On les ajoute au contenaire du haut
        JPanel topContainer= new JPanel();
        topContainer.setLayout(new BorderLayout());
        etiquettes.setPreferredSize(new Dimension(150,30));
        topContainer.add(etiquettes, BorderLayout.WEST);
        topContainer.add(margeHaut, BorderLayout.NORTH);
        topContainer.add(margeDroite, BorderLayout.EAST);
        topContainer.add(margeBas, BorderLayout.SOUTH);
        topContainer.add(champsTexte, BorderLayout.CENTER);

        //On ajoute les boutons
        JPanel buttonContainer = new JPanel(new BorderLayout());
        JButton valider = new JButton("Valider");
        //On assigne un main.controleur au bouton pour g�n�rer la cr�ation du main.tournoi
        valider.addActionListener(new ChangerNbrTerrainControlleur(tournoi,nbTerrains,this));

        //On assigne un main.controleur au bouton annuler pour fermer la fenetre
        JButton annuler = new JButton("Annuler");
        final JDialog frameToClose = this;
        annuler.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                frameToClose.dispose();

            }
        });

        JPanel boutonsAnVal = new JPanel();
        boutonsAnVal.setLayout(new BoxLayout(boutonsAnVal, BoxLayout.X_AXIS));
        boutonsAnVal.add(annuler);
        boutonsAnVal.add(valider);
        buttonContainer.add(boutonsAnVal, BorderLayout.EAST);
        buttonContainer.setBorder(new EmptyBorder(0, 10, 0, 10));

        //On ajoute le conteneur du haut au content pane
        container.add(topContainer, BorderLayout.NORTH);
        container.add(buttonContainer, BorderLayout.SOUTH);

        //On ajoute le content pane � la fen�tre
        this.setContentPane(container);

        //On pr�selectionne le bouton valider
        this.getRootPane().setDefaultButton(valider);

        //affichage final
        this.pack();
        this.setTitle("Nouveau Tournoi");
        this.setSize(410, 160);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }
}
