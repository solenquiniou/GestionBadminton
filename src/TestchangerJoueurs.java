import org.junit.Test;
import main.tournoi.Terrain;
import main.tournoi.Tournoi;

import static org.junit.Assert.assertEquals;

/**
 * Created by E154981H on 23/01/17.
 */
public class TestchangerJoueurs {

    @Test
    public void TestchangerJoueurs() {
        try {
            Tournoi t1 = new Tournoi(2,"kjh");
            t1.csvReader("LeCsv.csv");
            t1.demarrerTour();
            for (Terrain t : t1.getTerrains()){
                System.out.println(t.j1().toString() +"  +  " + t.j2().toString()+"  vs  "+t.j3().toString() +"  +  " + t.j4().toString());
            }

            assertEquals(1,1 );
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

    }

}
