package test.tournoi;

import main.exception.TournoiVideException;
import main.tournoi.Joueur;
import main.tournoi.Paire;
import main.tournoi.Tournoi;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

/** Classe servant à tester les méthodes de MethodeTournoi
 *  @author Cyril Dernoncourt, Antoine Drouard, Léa Le Bert, Lucas Martineau
 *  Created by Lucas Martineau on 23/01/17.
 */
public class MethodeTournoiTest{

    @Test
    public void TestCreerPaire() {
        Tournoi tournoi = null;
        try {
            tournoi = new Tournoi(4, "Nom de tournoi arbitraire");
        } catch (Exception e) {
            e.getMessage();
        }

        Joueur[] joueurs = new Joueur[16];

        joueurs[0]  = new Joueur(0,  "Joueur0",  "Prénom0",  14, true,  true,  1, true);
        joueurs[1]  = new Joueur(1,  "Joueur1",  "Prénom1",  15, true,  false, 1, true);
        joueurs[2]  = new Joueur(2,  "Joueur2",  "Prénom2",  16, true,  true,  1, true);
        joueurs[3]  = new Joueur(3,  "Joueur3",  "Prénom3",  12, true,  true,  3, true);
        joueurs[4]  = new Joueur(4,  "Joueur4",  "Prénom4",  15, true,  true,  3, true);
        joueurs[5]  = new Joueur(5,  "Joueur5",  "Prénom5",  16, true,  false, 3, true);
        joueurs[6]  = new Joueur(6,  "Joueur6",  "Prénom6",  20, true,  true,  3, true);
        joueurs[7]  = new Joueur(7,  "Joueur7",  "Prénom7",  21, true,  true,  3, true);
        joueurs[8]  = new Joueur(8,  "Joueur8",  "Prénom8",  22, false, true,  3, true);
        joueurs[9]  = new Joueur(9,  "Joueur9",  "Prénom9",  23, false, false, 3, true);
        joueurs[10] = new Joueur(10, "Joueur10", "Prénom10", 24, false, true,  3, true);
        joueurs[11] = new Joueur(11, "Joueur11", "Prénom11", 25, false, true,  3, true);
        joueurs[12] = new Joueur(12, "Joueur12", "Prénom12", 27, false, true,  3, true);
        joueurs[13] = new Joueur(13, "Joueur13", "Prénom13", 29, false, false, 3, true);
        joueurs[14] = new Joueur(14, "Joueur14", "Prénom14", 35, false, true,  3, true);
        joueurs[15] = new Joueur(15, "Joueur15", "Prénom15", 44, false, true,  3, true);

        ArrayList<Joueur> ArrayJoueurs = new ArrayList<>();
        ArrayJoueurs.addAll(Arrays.asList(joueurs));

        //La moitié des joueurs n'est pas prio, pour tester les 4 dernières boucles
        for (int i = 8; i < 16; i++) {
            ArrayJoueurs.get(i).setPrio(false);
        }

        //Pour que le joueur 0 soit avec le joueur 1
        for (int i = 2; i < 8; i++) {
            ArrayJoueurs.get(0).ajouterAnciensPart(ArrayJoueurs.get(i));
        }

        //Pour que le 2 soit avec le 3
        for (int i = 0; i < 2; i++) {
            ArrayJoueurs.get(2).ajouterAnciensPart(ArrayJoueurs.get(i));
        }
        for (int i = 4; i < 8; i++) {
            ArrayJoueurs.get(2).ajouterAnciensPart(ArrayJoueurs.get(i));
        }
        for (int i = 0; i < 2; i++) {
            ArrayJoueurs.get(3).ajouterAnciensPart(ArrayJoueurs.get(i));
        }
        for (int i = 4; i < 8; i++) {
            ArrayJoueurs.get(3).ajouterAnciensPart(ArrayJoueurs.get(i));
        }

        //Pour que le 4 soit avec le 5
        for (int i = 0; i < ArrayJoueurs.size(); i++) {
            ArrayJoueurs.get(4).ajouterAnciensPart(ArrayJoueurs.get(i));
        }
        for (int i = 0; i < ArrayJoueurs.size(); i++) {
            ArrayJoueurs.get(5).ajouterAnciensPart(ArrayJoueurs.get(i));
        }

        //Pour que le 6 soit avec le 7
        for (int i = 0; i < ArrayJoueurs.size(); i++) {
            ArrayJoueurs.get(6).ajouterAnciensPart(ArrayJoueurs.get(i));
        }
        for (int i = 0; i < ArrayJoueurs.size(); i++) {
            ArrayJoueurs.get(7).ajouterAnciensPart(ArrayJoueurs.get(i));
        }

        //Pour que le joueur 8 soit avec le joueur 9
        for (int i = 10; i < 15; i++) {
            ArrayJoueurs.get(8).ajouterAnciensPart(ArrayJoueurs.get(i));
        }

        //Pour que le 10 soit avec le 11
        for (int i = 8; i < 10; i++) {
            ArrayJoueurs.get(10).ajouterAnciensPart(ArrayJoueurs.get(i));
        }
        for (int i = 12; i < 15; i++) {
            ArrayJoueurs.get(10).ajouterAnciensPart(ArrayJoueurs.get(i));
        }
        for (int i = 8; i < 10; i++) {
            ArrayJoueurs.get(11).ajouterAnciensPart(ArrayJoueurs.get(i));
        }
        for (int i = 12; i < 15; i++) {
            ArrayJoueurs.get(11).ajouterAnciensPart(ArrayJoueurs.get(i));
        }

        //Pour que le 12 soit avec le 13
        for (int i = 8; i < ArrayJoueurs.size(); i++) {
            ArrayJoueurs.get(12).ajouterAnciensPart(ArrayJoueurs.get(i));
        }
        for (int i = 8; i < ArrayJoueurs.size(); i++) {
            ArrayJoueurs.get(13).ajouterAnciensPart(ArrayJoueurs.get(i));
        }

        //Pour que le 14 soit avec le 15
        for (int i = 8; i < ArrayJoueurs.size(); i++) {
            ArrayJoueurs.get(14).ajouterAnciensPart(ArrayJoueurs.get(i));
        }
        for (int i = 8; i < ArrayJoueurs.size(); i++) {
            ArrayJoueurs.get(15).ajouterAnciensPart(ArrayJoueurs.get(i));
        }

        /* On attribue à chaque joueur des anciens partenaires de façon à ce que l'algorithme donne :
        //1 avec 2
        //2 avec 3
        //... etc jusqu'à 15
        Les valeurs prises sont tributaires, et sont faites de sorte à ce que les 8 boucles principales soient testées*/

        for (Joueur joueur : ArrayJoueurs) {
            tournoi.ajouterJoueur(joueur);
        }

        try {
            tournoi.nouveauTour();
        } catch (TournoiVideException e) {
            e.printStackTrace();
        }

        ArrayList<Paire> paires = tournoi.getPaires();

        //Ce que l'on voudrait obtenir
        Paire[] pairesAttendueTab = new Paire[]{
                new Paire(ArrayJoueurs.get(0),  ArrayJoueurs.get(1)),
                new Paire(ArrayJoueurs.get(2),  ArrayJoueurs.get(3)),
                new Paire(ArrayJoueurs.get(4),  ArrayJoueurs.get(5)),
                new Paire(ArrayJoueurs.get(6),  ArrayJoueurs.get(7)),
                new Paire(ArrayJoueurs.get(8),  ArrayJoueurs.get(9)),
                new Paire(ArrayJoueurs.get(10), ArrayJoueurs.get(11)),
                new Paire(ArrayJoueurs.get(12), ArrayJoueurs.get(13)),
                new Paire(ArrayJoueurs.get(14), ArrayJoueurs.get(15))};

        ArrayList<Paire> pairesAttendues = new ArrayList<>(Arrays.asList(pairesAttendueTab));
        assertTrue(pairesAttendues.size() == paires.size());

        for (Paire paire : paires) {
            System.out.println(paire);
        }
        for (Paire paire : pairesAttendues) {
            assertTrue(paires.contains(paire));
        }
    }
}