package main.tournoi;

import java.util.ArrayList;

/**
 * @author DERNONCOURT Cyril, DROUARD Antoine, LE BERT Lucas, MARTINEAU Lucas
 */
public class Tour {
    private ArrayList<Terrain> matches;

    public Tour() {
        matches = new ArrayList<Terrain>();
    }
    public void    addTerr(Terrain t){
        this.matches.add(t);
    }

    public ArrayList<Terrain> getMatches() {
        return matches;
    }
}
