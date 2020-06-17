//Repertoar

import com.google.java.contract.*;

@Invariant("poz != null")
public class Repertoar {
    
    @Invariant("predstava != null")
    private static class Elem {
        Predstava predstava; 
        Elem sled = null;
        
        @Requires("p != null")
        Elem(Predstava p) { predstava = p; }
    }
    
    private Elem prvi = null, posl = null;
    
    private Pozoriste poz;
    
    private int brojPredstava = 0;
    
    @Requires("p != null")
    public Repertoar(Pozoriste p) { poz = p; }
    
    @Requires({"p != null", "p.pozoriste() == poz"})
    @Ensures("brojPredstava == old(brojPredstava) + 1")
    public boolean dodaj(Predstava p) {
        //if (p.pozoriste() != poz) return false;
        
        Elem novi = new Elem(p);
        if (prvi == null) prvi = novi;
        else posl.sled = novi;
        posl = novi;
        
        brojPredstava++;
        return true;
    }
    
    @Requires("naziv != null && naziv.length() > 0")
    @Ensures("brojPredstava == old(brojPredstava) - 1")
    public boolean ukloni(String naziv) {
        Elem tek = prvi, pret = null;
        while (tek != null && !tek.predstava.naziv().equals(naziv)) {
            pret = tek;
            tek = tek.sled;
        }
        if (tek == null) return false;
        if (pret == null) prvi = tek.sled;
        else pret.sled = tek.sled;
        if (tek == posl) posl = pret;
        
        brojPredstava--;
        return true;
    }
    
    public int brojPredstava() { return brojPredstava; }
    public Pozoriste pozoriste() { return poz; }
    
    public String toString() {
        String s = "";
        for (Elem tek = prvi; tek != null; tek = tek.sled)
            s += tek.predstava + "\n";
        return s;
    }
}
