//Zaposleni

import com.google.java.contract.*;

@Invariant({"ime != null && ime.length() > 0", "poz != null"})
public abstract class Zaposleni {
    
    private String ime;
    private Pozoriste poz;
    
    @Requires({"i != null && i.length() > 0", "p != null"})
    public Zaposleni(String i, Pozoriste p) {
        ime = i; poz = p;
    }
    
    public String ime() { return ime; }
    public Pozoriste pozoriste() { return poz; }
    
    public abstract char vrsta();
    
    public String toString() {
        return ime + "[" + vrsta()+ "," + poz.naziv() + "]";
    }
}