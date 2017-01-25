package main.tournoi;

import java.util.ArrayList;

/**
 * Created by Antoine Drouard, Cyril Dernoncourt,MArtinneau Lucas, LE BErt Léa on 07/12/16.
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
