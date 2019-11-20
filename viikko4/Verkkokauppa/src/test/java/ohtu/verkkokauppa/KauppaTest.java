package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;



public class KauppaTest {

    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;
    Kauppa k;

    @Before
    public void setUp() {
        pankki = mock(Pankki.class);
        viite = mock(Viitegeneraattori.class);
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);
        varasto = mock(Varasto.class);
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10

        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();

    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {

        k.lisaaKoriin(1); 
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455",5);    
    }

    @Test
    public void ostoksenPaaytyttyaKahdellaTuotteellaPankinMetodiaTilisiirtoKutsutaan () {
        when(varasto.saldo(2)).thenReturn(10);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "olut", 6));

        k.lisaaKoriin(1);
        k.lisaaKoriin(2);

        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455",11);    
    }

    @Test
    public void kaksiSamaaTuotettaJaTilisiirtoKutsutaan () {
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);

        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455",10);    
    }

    @Test
    public void ostoksenPaaytyttyaLoppuneellaTuotteellaPankinMetodiaTilisiirtoKutsutaan () {
        when(varasto.saldo(2)).thenReturn(0);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "olut", 6));

        k.lisaaKoriin(1);
        k.lisaaKoriin(2);

        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455",5);    
    }

    @Test
    public void aloitaAsiointiNollaaEdellisenOstoksenTiedot () {
        k.lisaaKoriin(1);
        k.aloitaAsiointi();
        k.lisaaKoriin(1);

        k.tilimaksu("pekka", "12345");


        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455",5);    
    }

    @Test
    public void kauppaPyytaaUudenViitenumeronJokaiselleTapahtumalle() {
        k.lisaaKoriin(1);

        k.tilimaksu("pekka", "12345");

        verify(viite, times(1)).uusi();
   }

   @Test
   public void poistaKoristaPalauttaaVarastoon() {
       k.lisaaKoriin(1);
       k.poistaKorista(1);
       verify(varasto, times(1)).palautaVarastoon(new Tuote(1, "maito", 5));
   }


}
