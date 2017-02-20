package main.app;

import main.tournoi.Joueur;
import main.tournoi.Tournoi;

import java.time.LocalDate;

/**
 * Created by Antoine Drouard, Cyril Dernoncourt,MArtinneau Lucas, LE BErt Léa on 21/11/16.
 */
public class testPaires {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();

        try {
            Tournoi tourn = new Tournoi(3,"truc");
            Joueur joueur1 = new Joueur(0, "a", "aa", date.of(1990, 2, 1), false, true, 1, true);
            Joueur joueur2 = new Joueur(1, "b", "bb", date.of(1990, 2, 1), true, true, 1, true);
            Joueur joueur3 = new Joueur(2, "c", "cc", date.of(1999, 2, 1), false, false, 1, true);
            Joueur joueur4 = new Joueur(3, "d", "dd", date.of(1970, 2, 1), true, false, 0, true);
            Joueur joueur5 = new Joueur(4, "e", "ee", LocalDate.now(), false, true, 3, true);
            Joueur joueur6 = new Joueur(5, "f", "ff", date.of(1999, 2, 1), true, false, 2, true);
            Joueur joueur7 = new Joueur(6, "g", "gg", date.of(1990, 2, 1), true, true, 3, true);
            Joueur joueur8 = new Joueur(7, "h", "hh", date.of(1990, 2, 1), true, false, 3, true);
            tourn.ajouterJoueur(joueur1);
            tourn.ajouterJoueur(joueur2);
            tourn.ajouterJoueur(joueur3);
            tourn.ajouterJoueur(joueur4);
            tourn.ajouterJoueur(joueur5);
            tourn.ajouterJoueur(joueur6);
            tourn.ajouterJoueur(joueur7);
            tourn.ajouterJoueur(joueur8);
            System.out.println("Tour 1 :");
            tourn.nouveauTour();
            System.out.println("Tour 2 :");
            tourn.nouveauTour();
            System.out.println("Tour 3 :");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
