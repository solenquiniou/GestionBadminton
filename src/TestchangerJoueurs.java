import exception.NbTerrainNeg;
import exception.NomVideException;
import org.junit.Test;
import tournoi.Joueur;
import tournoi.Paire;
import tournoi.Terrain;
import tournoi.Tournoi;

/**
 * Created by E154981H on 23/01/17.
 */
public class TestTournoi {
    @Test
    public void TestImport() {
        Tournoi t1 = null;
        try {
            t1 = new Tournoi(4,"kjh");
            assert(true, t1.tournoisVide();
            t1.importer("LeCsv.csv");
            assert(false, t1.tournoisVide());

        } catch (Exception e) {
            e.getMessage();


        }

        @Test
        public void TestChangerJoueurs() {
            try {
                Tournoi t1 = new Tournoi(4,"kjh");
                t1.importer("LeCsv.csv");
                System.out.println(t1.getAllJoueurs().toString());
                //Verif algo paire
                t1.creerPaires();
                for (Paire p : t1.getPaires()){
                    //System.out.println(p.getJoueur1().toString() +"  +  " + p.getJoueur2().toString());
                }//ok
                //test terrains cree
                t1.attribuerMatchs();
                for (Terrain t : t1.getTerrains()){
                    System.out.println(t.j1().toString()+"   +   " +t.j2().toString()+"   VS   " +t.j3().toString()+"   +   "+ t.j4().toString());
                }//oks
                System.out.println("\n");

                //echange au sein d'un meme match
                Joueur prec = t1.getTerrain(2).j1();
                Joueur nouv = t1.getTerrain(2).j4();
                t1.changerJoueurs(prec, nouv);
                for (Terrain t : t1.getTerrains()){
                    System.out.println(t.j1().toString()+"   +   " +t.j2().toString()+"   VS   " +t.j3().toString()+"   +   "+ t.j4().toString());
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());

            }

        }

    }
