//Predstava

import com.google.java.contract.Ensures;
import com.google.java.contract.Invariant;
import com.google.java.contract.Requires;

@Invariant({"naziv != null && naziv.length() > 0", "poz != null"})
public class Predstava {
    
	@Invariant("zaposleni != null")
    private static class Elem {
        Zaposleni zaposleni;
        Elem sled = null;
        
        @Requires("z != null")
        Elem(Zaposleni z) { zaposleni = z; }
    }
    
    private Elem prvi = null, posl = null;
    
    private String naziv;
    private Pozoriste poz;
    private int brKost = 0;
    private int brGlumaca = 0;
    
    @Requires({"naz != null && naz.length() > 0", "p != null", "red != null", "p == red.pozoriste()"})
    public Predstava(String naz, Pozoriste p, Reditelj red) {
        //if (red.pozoriste() != p) System.exit(1);
        naziv = naz;
        poz = p;
        prvi = posl = new Elem(red);
    }
    
    @Requires({"z != null", "z.pozoriste() == poz", "z.vrsta() != 'R'", "brKost < 2"})
    @Ensures("brKost == old(brKost) + 1 || brGlumaca == old(brGlumaca) + 1")
    public boolean dodaj(Zaposleni z) {
        //if (z.pozoriste() != poz || z.vrsta() == 'R' || z.vrsta() == 'K' && brKost == 2)
        //    return false;
        posl = posl.sled = new Elem(z);
        if (z.vrsta() == 'K') brKost++;
        else brGlumaca++;
        return true;
    }
    
    public String naziv() { return naziv; }
    public Pozoriste pozoriste() { return poz; }
    public int brKost() { return brKost; }
    public int brGlumaca() { return brGlumaca; }
    
    public String toString() {
        String s = "";
        s += naziv + ", " + poz.naziv() + "\n";
        for (Elem tek = prvi; tek != null; tek = tek.sled)
            s += tek.zaposleni + "\n";
        return s;
    }
}