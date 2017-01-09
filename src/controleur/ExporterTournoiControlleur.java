package controleur;

import tournoi.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

/** La classe ExporterTournoiControlleur permet d'exporter un tournoi
 * @author DROUARD Antoine, DERNONCOURT Cyril, LE BERT Lea, MARTINEAU Lucas
 */
public class ExporterTournoiControlleur implements ActionListener {



    private Tournoi tournoi;

    /** Constructeur de la classe ExporterTournoiControlleur
     *
     * @param tournoi le tournoi où trouver les tours joués
     */
    public ExporterTournoiControlleur(Tournoi tournoi) {
        this.tournoi = tournoi;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        //Ouverture de la fenetre "enregistrer sous"
        Frame fr = new Frame("Choississez un répertoire");
        FileDialog dial = new FileDialog(fr, "Enregistrer sous", FileDialog.SAVE);
        dial.setFile(".csv"); //Pré-écrit l'extension .csv dans la fenêtre de dialogue
        dial.setVisible(true);
        fr.setVisible(false);
        try {
            if (dial.getDirectory() != null && dial.getFile() != null) {// Si l'utilisateur a bien spécifié un fichier où écrire
                // On crée un BufferedWriter (FileWriter avec la possibilité de créer une nouvelle ligne)
                // qui va créer un fichier du nom qu'à choisi l'utilisateur et écrire dans celui ci
                BufferedWriter fichier = new BufferedWriter(new FileWriter(dial.getDirectory().concat(dial.getFile())));

                //Première ligne (entête)
                fichier.write("Prénom,Nom,Sexe,Ancienneté,Âge,Niveau");
                fichier.newLine();

                //On parcourt tous les anciensJoueurs de tournoi, on les découpe et on les écrit dans le fichier
                for (Joueur j : tournoi.getAnciensJoueurs()) {
                    fichier.write(tournoi.decouperJoueur(j));
                    fichier.newLine();
                }
                //On parcourt tous les NoueauxJoueurs de tournoi, on les découpe et on les écrit dans le fichier
                for (Joueur j : tournoi.getNouveauxJoueurs()) {
                    fichier.write(tournoi.decouperJoueur(j));
                    fichier.newLine();
                }
                fichier.write("#");
                fichier.newLine();
                fichier.write("NuméroTour,NuméroTerrain,NomJoueur1,PrenomJoueur1,NomJoueur2,PrenomJoueur2,Score,NomJoueur3,PrenomJoueur3,NomJoueur4,PrenomJoueur4,Score");
                fichier.newLine();
                ArrayList<Tour> lesTours = tournoi.getTours();
                for (int i = 0; i < lesTours.size(); i++)
                {
                    int numeroTour = i;
                    ArrayList<Terrain> lesTerrains = lesTours.get(i).getMatches();
                    for (int j = 0; j < lesTerrains.size(); j++)
                    {
                        Terrain leTerrain = lesTerrains.get(j);
                        int numeroTerrain = leTerrain.getNumero();
                        Match match = leTerrain.getMatch();
                        Paire paire1 = match.getPaire1();
                        Joueur Joueur1 = paire1.getJoueur1();
                        Joueur Joueur2 = paire1.getJoueur2();
                        int score1 = match.getScore1();
                        Paire paire2 = match.getPaire2();
                        Joueur Joueur3 = paire2.getJoueur1();
                        Joueur Joueur4 = paire2.getJoueur2();
                        int score2 = match.getScore2();
                        fichier.write(numeroTour+","+numeroTerrain+","+Joueur1.getNom()+","+Joueur1.getPrenom()+","+Joueur2.getNom()+","+Joueur2.getPrenom()+","+score1+","+Joueur3.getNom()+","+Joueur3.getPrenom()+","+Joueur4.getNom()+","+Joueur4.getPrenom()+","+score2);
                        fichier.newLine();
                    }

                }
                fichier.close();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }






}
