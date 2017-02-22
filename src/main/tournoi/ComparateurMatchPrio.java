package main.tournoi;

import java.util.Comparator;

/**
 * ComparateurMatchPrio est la classe permettant de comparer des
 * matches pour les ranger par ordre croissant de leurs priorités
 * @author DERNONCOURT Cyril, DROUARD Antoine, LE BERT Lucas, MARTINEAU Lucas
 */
public class ComparateurMatchPrio implements Comparator<Match> {

    /**
     * Permet de comparer des matches pour les ranger par ordre croissant de leurs priorités
     * @param p1 le premier match à comparer
     * @param p2 le match avec lequel comparer le premier match
     */
    @Override
    public int compare(Match p1, Match p2) {
        if (p1.prio() < p2.prio()) {
            return 1;
        } else {
            if (p1.prio() > p2.prio())
                return -1;
            else
                return 0;
        }

    }
}


