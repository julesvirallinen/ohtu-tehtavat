
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
            OLETUSKASVATUS = 5; // luotava uusi taulukko on
    // näin paljon isompi kuin vanha
    private int kasvatuskoko; // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] lukujono; // Joukon luvut säilytetään taulukon alkupäässä.
    private int alkioidenLkm; // Tyhjässä joukossa alkioiden_määrä on nolla.

    public IntJoukko() {
        alustaArvot(KAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        alustaArvot(kapasiteetti, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Kasvatuskoko liian pieni");
        }
        alustaArvot(kapasiteetti, kasvatuskoko);
    }

    private void alustaArvot(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti väärin");
        }
        lukujono = new int[kapasiteetti];
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    private void tarkistaTaulukonKoko() {
        if (alkioidenLkm == lukujono.length) {
            int[] uusiTaulukko = new int[lukujono.length + kasvatuskoko];

            for (int i = 0; i < lukujono.length; i++) {
                uusiTaulukko[i] = lukujono[i];
            }

            lukujono = uusiTaulukko;

        }

    }

    public boolean lisaa(int luku) {
        if (!kuuluu(luku)) {
            lukujono[alkioidenLkm] = luku;
            alkioidenLkm++;
            tarkistaTaulukonKoko();
            return true;
        }
        return false;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujono[i])
                return true;
        }
        return false;
    }

    public boolean poista(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujono[i]) {
                lukujono[i] = lukujono[alkioidenLkm - 1];
                alkioidenLkm--;
                return true;
            }
        }
        return false;
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        String tuotos = "{";
        for (int i = 0; i < alkioidenLkm; i++) {
            tuotos += lukujono[i];
            if (i != alkioidenLkm - 1)
                tuotos += ", ";
        }
        tuotos += "}";
        return tuotos;

    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = lukujono[i];
        }
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            x.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            x.lisaa(bTaulu[i]);
        }
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    y.lisaa(bTaulu[j]);
                }
            }
        }
        return y;

    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            z.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            z.poista(bTaulu[i]);
        }

        return z;
    }

}
