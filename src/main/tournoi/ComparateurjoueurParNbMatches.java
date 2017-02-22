package main.tournoi;

import java.util.Comparator;

/**
 * ComparateurjoueurParNbMatches est la classe permettant de comparer des
 * joueur pour les ranger par ordre croissant du nombre de matches joués
 * @author DERNONCOURT Cyril, DROUARD Antoine, LE BERT Lucas, MARTINEAU Lucas
 */
public class ComparateurjoueurParNbMatches implements Comparator<Joueur> {

    /**
     * Permet de comparer des joueurs pour les ranger par ordre croissant du nombre de matches joués
     * @param j1 le premier joueur à comparer
     * @param j2 le joueur avec qui comparer le premier joueur
     */
    @Override
    public int compare(Joueur j1, Joueur j2) {
        if(j1.getNbMatchJoues()>j2.getNbMatchJoues())
            return 1;
        else{
            if(j1.getNbMatchJoues()<j2.getNbMatchJoues())
                return -1;
            else
                return 0;
        }
    }
}