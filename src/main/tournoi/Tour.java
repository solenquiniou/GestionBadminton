package main.tournoi;

import java.util.ArrayList;

/**
 * Tour est la classe représentant un tour dans le tournoi, et un
 * tour représente un certain nombre de matchs qui ont eu lieu
 * @author DERNONCOURT Cyril, DROUARD Antoine, LE BERT Lucas, MARTINEAU Lucas
 */
public class Tour {

    /**
     * Tous les matchs qui ont eu lieu pour le tour (on enregistre les terrains
     * puisque l'on peut accèder aux matches via le terrain)
     */
    private ArrayList<Terrain> matches;

    /**
     * Crée un tour avec aucun match
     */
    public Tour() {
        matches = new ArrayList<>();
    }

    /**
     * ajoute un terrain à la liste des terrains
     * @param t le terrain à ajouter
     */
    public void addTerr(Terrain t){
        this.matches.add(t);
    }

    /**
     * Retourne tous les terrains
     * @return la liste de tous les terrains
     */
    public ArrayList<Terrain> getMatches() {
        return matches;
    }
}
