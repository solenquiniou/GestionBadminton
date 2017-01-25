package test.tournoi;

import main.tournoi.Joueur;
import main.tournoi.Paire;
import main.tournoi.Terrain;
import main.tournoi.Tournoi;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Created by Antoine Drouard, Cyril Dernoncourt,MArtinneau Lucas, LE BErt Léa on 23/01/17.
 */
public class TestTournoi {
    Tournoi t1 = null;

    @Test
    public void TestsetScore() {
        try {
            t1 = new Tournoi(3, "kjh");
            t1.importer("LeCsv.csv");
            t1.nouveauTour();
            t1.setScore(0, 1, 2);
            t1.setScore(1, 7, 2);
            t1.setScore(2, 1, 5);
            System.out.println("Verif Scores set dans les matchs");
            assertEquals(1, t1.getTerrains().get(0).getMatch().getScore1());
            assertEquals(2, t1.getTerrains().get(0).getMatch().getScore2());
            assertEquals(7, t1.getTerrains().get(1).getMatch().getScore1());
            assertEquals(2, t1.getTerrains().get(1).getMatch().getScore2());
            assertEquals(1, t1.getTerrains().get(2).getMatch().getScore1());
            assertEquals(5, t1.getTerrains().get(2).getMatch().getScore2());
            System.out.println("Verif Scores set dans les paires");
            assertEquals(-1, t1.getTerrains().get(0).getMatch().getPaire1().getScore());
            assertEquals(1, t1.getTerrains().get(0).getMatch().getPaire2().getScore());
            assertEquals(5, t1.getTerrains().get(1).getMatch().getPaire1().getScore());
            assertEquals(-5, t1.getTerrains().get(1).getMatch().getPaire2().getScore());
            assertEquals(-4, t1.getTerrains().get(2).getMatch().getPaire1().getScore());
            assertEquals(4, t1.getTerrains().get(2).getMatch().getPaire2().getScore());
            System.out.println("Verif Scores set sur les joueurs");
            assertEquals(-1, t1.getTerrains().get(0).getMatch().getPaire1().getJoueur1().getScore());
            assertEquals(-1, t1.getTerrains().get(0).getMatch().getPaire1().getJoueur2().getScore());
            assertEquals( 1, t1.getTerrains().get(0).getMatch().getPaire2().getJoueur1().getScore());
            assertEquals( 1, t1.getTerrains().get(0).getMatch().getPaire2().getJoueur2().getScore());
            assertEquals( 5, t1.getTerrains().get(1).getMatch().getPaire1().getJoueur1().getScore());
            assertEquals( 5, t1.getTerrains().get(1).getMatch().getPaire1().getJoueur2().getScore());
            assertEquals(-5, t1.getTerrains().get(1).getMatch().getPaire2().getJoueur1().getScore());
            assertEquals(-5, t1.getTerrains().get(1).getMatch().getPaire2().getJoueur2().getScore());
            assertEquals(-4, t1.getTerrains().get(2).getMatch().getPaire1().getJoueur1().getScore());
            assertEquals(-4, t1.getTerrains().get(2).getMatch().getPaire1().getJoueur2().getScore());
            assertEquals( 4, t1.getTerrains().get(2).getMatch().getPaire2().getJoueur1().getScore());
            assertEquals( 4, t1.getTerrains().get(2).getMatch().getPaire2().getJoueur2().getScore());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void TestImport() {
        try {
            System.out.println("Vide avat l'export");
            t1 = new Tournoi(4, "kjh");
            assertEquals(true, t1.tournoisVide());
            t1.importer("LeCsv.csv");
            System.out.println("Plus vide après l'export");
            assertEquals(false, t1.tournoisVide());

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
            //t1.creerPaires(); méthode mis en private après validation donc plus testable
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

