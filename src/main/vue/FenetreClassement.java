package main.vue;

import main.controleur.*;
import main.tournoi.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
/**
 * Fenêtre l' affichage du classement. On peut choisir de visualiser tout les joueurs, ou seulement les anciens/nouveaus joueurs.
 * @author DERNONCOURT Cyril, DROUARD Antoine, LE BERT Léa, MARTINEAU Lucas
 * @version 1.1
 */
public class FenetreClassement extends JFrame
{
    private Tournoi tournoi;
    private DefaultTableModel listeJoueursModele;


    static private FenetreClassement derniereFenetre;

    /**
     * constructeur de la fenêtre classement
     * @param titre le titre à donner à la fenêtre
     * @param t le main.tournoi dans lequel on veut voir le classement
     */
    public FenetreClassement(String titre, Tournoi t)
    {
        this.tournoi = t;

        if (derniereFenetre != null)
            derniereFenetre.dispose();

        JPanel joueurs = new JPanel();
        joueurs.setLayout(new GridLayout(1, 2));

        String  title[] = {"Nom", "Prénom", "Score","Ancienneté"};

        listeJoueursModele = new DefaultTableModel(title, 0)
        {
            //bien redefinir les types des colones pour que l'autosort marche
            public Class getColumnClass(int column)
            {
                switch (column)
                {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return Integer.class;
                    case 3:
                        return String.class;
                    default:
                        return String.class;
                }
            }
        };

        JTable listeJoueurs = new JTable(listeJoueursModele);
        listeJoueurs.setAutoCreateRowSorter(true);
        JScrollPane panJoueurs = new JScrollPane(listeJoueurs);

        joueurs.add(panJoueurs);



        JPanel a = new JPanel();
        JComboBox categorie = new JComboBox(new String[]{"Tous", "Nouveaux", "Anciens"});
        categorie.setSelectedIndex(0);
        categorie.addActionListener(new AfficherClassementControleur(this, categorie));
        a.add(categorie);
        joueurs.add(a);

        afficherJoueur("Tous");


        this.setContentPane(joueurs);
        this.pack();
        this.setTitle(titre);
        int tailleX = 600, tailleY = 200;
        this.setSize(tailleX,tailleY);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);

        derniereFenetre = this;
    }

    /**
     *  Affiche les joueurs en fonction de ce qui est séléctioné dans la combobox.
     * @param selection Groupes de joueures séléctionnées
     */
    public void afficherJoueur(String selection)
    {
        ArrayList<Joueur> listeAllJoueurs = new ArrayList<Joueur>();
        ArrayList<Joueur> listeNouveaux = new ArrayList<Joueur>();
        ArrayList<Joueur> listeAnciens = new ArrayList<Joueur>();
        listeJoueursModele.setRowCount(0);

        if(selection.equals("Tous"))
        {
            listeAllJoueurs = tournoi.getAllJoueurs();
            Collections.sort(listeAllJoueurs, new ComparateurJoueurScore());
            for (Joueur j : listeAllJoueurs)
            {
                ajouterJoueurTable(j);
            }
        }

        if(selection.equals("Nouveaux"))
        {
            listeNouveaux = tournoi.getClassementNouveaux();
            for (Joueur j : listeNouveaux)
            {
                ajouterJoueurTable(j);
            }
        }

        if(selection.equals("Anciens"))
        {
            listeAnciens = tournoi.getClassementAnciens();
            for (Joueur j : listeAnciens)
            {
                ajouterJoueurTable(j);
            }
        }
    }

    /**
     *  Ajoute les joueurs a la table
     * @param joueur  le joueur a ajouter
     */
    public void ajouterJoueurTable(Joueur joueur)
    {
        String ancien = "Ancien";
        if(joueur.getNouveau())
        {
            ancien = "Nouveau";
        }
        Object[] tJ = {joueur.getNom(), joueur.getPrenom(), joueur.getScore(), ancien};
        this.listeJoueursModele.addRow(tJ);
    }




}
