package main.tournoi;

import java.util.Comparator;

/**
 * Created by Antoine Drouard, Cyril Dernoncourt,MArtinneau Lucas, LE BErt Léa on 07/11/16.
 */
public class ComparateurjoueurParNbMatches implements Comparator<Joueur> {

    /*
    *
    * Permet de comparer des joueur pour les ranger par ordre croissant du nombre de matches joués
    *
     */
    @Override
    public int compare(Joueur j1, Joueur j2) {
        if(j1.getNbMatchJoues()>j2.getNbMatchJoues()){return 1;}
        else{
            if(j1.getNbMatchJoues()<j2.getNbMatchJoues()){return -1;}
            else{
                return 0;

            }
        }
    }
}