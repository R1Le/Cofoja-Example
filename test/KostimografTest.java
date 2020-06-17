import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.google.java.contract.*;

//**********************************************************************
// Testovi za klasu Kostimograf
// (Testovi su identicni i za ostale klase izvedene iz klase Zaposleni)
//**********************************************************************

class KostimografTest {
    
    @Test
    public void noviKostimograf() {
        Pozoriste p = new Pozoriste("Dusko Radovic");
        Kostimograf k = new Kostimograf("Petar", p);
        
        //ime != null && ime.length() > 0
        assertEquals(k.ime(), "Petar");
        
        //pozoriste != null
        assertEquals(k.pozoriste(), p);
        
        assertEquals(k.vrsta(), 'K');
    }
    
    @Test
    public void noviKostimografBezImenaIPozorista() {
        try {
            Kostimograf k = new Kostimograf(null, null);
        } catch (PreconditionError expected) {
            assertEquals("[i != null && i.length() > 0]", expected.getMessages().toString());
        }
    }
    
    @Test
    public void noviKostimografBezImena() {
        try {
            Pozoriste p = new Pozoriste("Dusko Radovic");
            Kostimograf k = new Kostimograf(null, p);
        } catch (PreconditionError expected) {
            assertEquals("[i != null && i.length() > 0]", expected.getMessages().toString());
        }
    }
    
    @Test
    public void noviKostimografSaPraznimImenom() {
        try {
            Pozoriste p = new Pozoriste("Dusko Radovic");
            Kostimograf k = new Kostimograf("", p);
        } catch (PreconditionError expected) {
            assertEquals("[i != null && i.length() > 0]", expected.getMessages().toString());
        }
    }
    
    @Test
    public void noviKostimografBezPozorista() {
        try {
            Kostimograf k = new Kostimograf("Petar", null);
        } catch (PreconditionError expected) {
            assertEquals("[p != null]", expected.getMessages().toString());
        }
    }
}
