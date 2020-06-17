//Pozoriste

import com.google.java.contract.*;

@Invariant("naziv != null && naziv.length() > 0")
public class Pozoriste {
    
    private static int pozId = 0;
    private int id = ++pozId;
    
    private String naziv;
    
    @Requires("naz != null && naz.length() > 0")
    public Pozoriste(String naz) {
        naziv = naz;
    }
    
    public int id() { return id; }
    public String naziv() { return naziv; }
    
    public String toString() {
        return naziv + "[" + id + "]";
    };
} 
