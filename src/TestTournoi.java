import main.tournoi.*;

import org.junit.Test;


/**
 * Created by E154981H on 23/01/17.
 */
public class TestTournoi {
    Tournoi t1 = null;

    @Test
    public void TestImport() {
        try {
            t1 = new Tournoi(4, "kjh");
            //assert(true, t1.tournoisVide();
            t1.importer("LeCsv.csv");
            //assert(false, t1.tournoisVide());

        } catch (Exception e) {
            e.getMessage();


        }
    }

    @Test
    public void TestAlgoPaire() {
        try {
            t1 = new Tournoi(4, "kjh");
            t1.importer("LeCsv.csv");
            System.out.println(t1.getAllJoueurs().toString());
            //Verif algo paire
            //t1.creerPaires();   mis en private après validation
            for (Paire p : t1.getPaires()) {
                System.out.println(p.getJoueur1().toString() + "  +  " + p.getJoueur2().toString());
            }//ok
        }
        catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    @Test
    public void TestAttribuerMatch() {
        try {
            t1 = new Tournoi(4, "kjh");
            t1.importer("LeCsv.csv");
            System.out.println(t1.getAllJoueurs().toString());
            //Verif algo paire
            t1.nouveauTour();
            for (Terrain t : t1.getTerrains()) {
                System.out.println(t.j1().toString() + "   +   " + t.j2().toString() + "   VS   " + t.j3().toString() + "   +   " + t.j4().toString());
            }//oks
        }
        catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    @Test
    public void TestChangerJoueurs() {
        try {
            t1 = new Tournoi(4, "kjh");
            t1.importer("LeCsv.csv");
            t1.nouveauTour();
            //initial
            for (Terrain t : t1.getTerrains()) {
                System.out.println(t.j1().toString() + "   +   " + t.j2().toString() + "   VS   " + t.j3().toString() + "   +   " + t.j4().toString());
            }
            System.out.println(" \n Echange intra Match \n");
            //echange au sein d'un meme match
            Joueur prec = t1.getTerrain(2).j1();
            Joueur nouv = t1.getTerrain(2).j4();
            t1.changerJoueurs(prec, nouv);
            for (Terrain t : t1.getTerrains()) {
                System.out.println(t.j1().toString() + "   +   " + t.j2().toString() + "   VS   " + t.j3().toString() + "   +   " + t.j4().toString());
            }
            System.out.println(" \n Echange interMatch\n");

            //echange entre deux matches
            prec = t1.getTerrain(2).j1();
            nouv = t1.getTerrain(3).j1();
            t1.changerJoueurs(prec, nouv);
            for (Terrain t : t1.getTerrains()) {
                System.out.println(t.j1().toString() + "   +   " + t.j2().toString() + "   VS   " + t.j3().toString() + "   +   " + t.j4().toString());
            }
            System.out.println(" \n Echange avec quelqu'un qui ne joue pas\n");
            //echange avec quelqu'un qui ne joue pas
            prec = t1.getTerrain(2).j1();
            nouv = t1.chercherJoueur("MASSON Loïc");
            t1.changerJoueurs(prec, nouv);
            for (Terrain t : t1.getTerrains()) {
                System.out.println(t.j1().toString() + "   +   " + t.j2().toString() + "   VS   " + t.j3().toString() + "   +   " + t.j4().toString());
            }


            System.out.println(" \n Echange avec quelqu'un  de la paire opposée dans le mëme match\n");
            prec = t1.getTerrain(2).j1();
            nouv = t1.getTerrain(2).j3();
            t1.changerJoueurs(prec, nouv);
            for (Terrain t : t1.getTerrains()) {
                System.out.println(t.j1().toString() + "   +   " + t.j2().toString() + "   VS   " + t.j3().toString() + "   +   " + t.j4().toString());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

    }

}

