package main.tournoi;

import java.util.Comparator;

/**
 * ComparateurJoueurScore est la classe permettant de comparer des
 * joueurs pour les ranger par ordre croissant de leurs scores
 * @author DERNONCOURT Cyril, DROUARD Antoine, LE BERT Lucas, MARTINEAU Lucas
 */
public class ComparateurJoueurScore implements Comparator<Joueur> {

    /**
     * Permet de comparer des joueurs pour les ranger par ordre croissant de leurs scores
     * @param j1 le premier joueur à comparer
     * @param j2 le joueur avec qui comparer le premier joueur
     */
    @Override
    public int compare(Joueur j1, Joueur j2) {
        if (j1.getScore() < j2.getScore())
            return 1;
        else {
            if (j1.getScore() > j2.getScore())
                return -1;
            else
                return 0;
        }

    }
}


