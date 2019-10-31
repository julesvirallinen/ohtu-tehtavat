package ohtuesimerkki;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.*;
import java.util.ArrayList;
import java.util.List;


public class StatisticsTest {

    Reader readerStub = new Reader() {
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }

    @Test
    public void playerSearchToimii(){
        Player kurri = new Player("Kurri",   "EDM", 37, 53);
        assertEquals(kurri.toString(), stats.search("Kurri").toString());
    }

    @Test
    public void hakuPalauttaaNullJosPelaajaaEiOle(){
        assertNull(stats.search("enOoOlemassa"));
    }

    @Test
    public void teamListPalauttaaOikeanmaaranPelaajia(){
        List<Player> tiimi = stats.team("EDM");
        assertEquals(3, tiimi.size());
    }

    @Test
    public void teamListPalauttaaOikeanPelaajan(){
        List<Player> tiimi = stats.team("PIT");
        Player pelaaja = new Player("Lemieux", "PIT", 45, 54);
        assertEquals(pelaaja.toString(), tiimi.get(0).toString());

    }

    @Test
    public void topScorersPalauttaaOikeanMaaran(){
        assertEquals(4, stats.topScorers(3).size());
    }

    @Test
    public void topScorersPalauttaaOikeatPelaajat(){
        Player paras = new Player("Gretzky", "EDM", 35, 89);
        Player toka = new Player("Lemieux", "PIT", 45, 54);

        List<Player> parhaat = stats.topScorers(1);
        assertEquals(paras.toString(), parhaat.get(0).toString());
        assertEquals(toka.toString(), parhaat.get(1).toString());

    }
}
