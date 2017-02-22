package main.tournoi;

import java.util.Comparator;

/**
 * ComparateurPairePrio est la classe permettant de comparer des paires pour les
 * ranger par ordre croissant des paires contenant le plus de joueurs prioritaires
 * @author DERNONCOURT Cyril, DROUARD Antoine, LE BERT Lucas, MARTINEAU Lucas
 */
public class ComparateurPairePrio implements Comparator<Paire> {

    /**
     * Permet de comparer des paires pour les ranger par ordre croissant des paires contenant le plus de joueurs prioritaires
     * @param p1 la première paire à comparer
     * @param p2 la paire avec lequel comparer la première paire
     */
    @Override
    public int compare(Paire p1, Paire p2) {
        if(p1.prio()<p2.prio()){return 1;}
        else{
            if(p1.prio()>p2.prio())
                return -1;
            else
                return 0;
        }
    }
}
