import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.google.java.contract.PreconditionError;

//***************************
//Testovi za klasu Pozoriste
//***************************

class PozoristeTest {
    
    @Test
    public void NovoPozoristeUspesnoTest() {
        Pozoriste p = new Pozoriste("Dusko Radovic");
        
        //"naziv != null && naziv.length() > 0"
        assertEquals(p.naziv(), "Dusko Radovic");
    }
    
    @Test
    public void novoPozoristeNull() {
        try {
            Pozoriste p = new Pozoriste(null);
        } catch (PreconditionError expected) {
            assertEquals("[naz != null && naz.length() > 0]", expected.getMessages().toString());
        }
    }
    
    @Test
    public void novoPozoristePrazanString() {
        try {
            Pozoriste p = new Pozoriste("");
        } catch (PreconditionError expected) {
            assertEquals("[naz != null && naz.length() > 0]", expected.getMessages().toString());
        }
    }
}
