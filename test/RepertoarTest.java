import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.google.java.contract.PostconditionError;
import com.google.java.contract.PreconditionError;

//***************************
//Testovi za klasu Repertoar
//***************************

class RepertoarTest {
    
    @Test
    public void noviRepertoarUspesno() {
        Pozoriste p = new Pozoriste("Dusko Radovic");
        Repertoar r = new Repertoar(p);
        
        //p != null
        assertEquals(r.pozoriste(), p);
    }
    
    @Test
    public void noviRepertoarNeuspesno() {
        try {
            Repertoar r = new Repertoar(null);
        } catch (PreconditionError expected) {
            assertEquals("[p != null]", expected.getMessages().toString());
        }
    }
    
    @Test
    public void dodavanjeNullPredstaveURepertoar() {
        try {
            Pozoriste p = new Pozoriste("Dusko Radovic");
            Repertoar r = new Repertoar(p);
            r.dodaj(null);
        } catch (PreconditionError expected) {
            assertEquals("[p != null]", expected.getMessages().toString());
        }
    }
    
    @Test
    public void dodavanjePredstaveIzDrugogPozoristaURepertoar() {
        try {
            Pozoriste po1 = new Pozoriste("Dusko Radovic");
            Pozoriste po2 = new Pozoriste("BDP");
            Repertoar r = new Repertoar(po1);
            Predstava pr = new Predstava("Princeza na zrnu graska", po2, new Reditelj("Iva Milosevic", po2));
            r.dodaj(pr);
        } catch (PreconditionError expected) {
            assertEquals("[p.pozoriste() == poz]", expected.getMessages().toString());
        }
    }
    
    @Test
    public void dodavanjePredstaveURepertoar() {
            Pozoriste po = new Pozoriste("Dusko Radovic");
            Repertoar r = new Repertoar(po);
            Predstava pr = new Predstava("Princeza na zrnu graska", po, new Reditelj("Iva Milosevic", po));
            
            int oldBrojPredstava = r.brojPredstava();
            r.dodaj(pr);
            int newbrojPredstava = r.brojPredstava();
            
            assertEquals(oldBrojPredstava + 1, newbrojPredstava);
    }
    
    @Test
    public void uspesnoUklanjanjePredstaveIzRepertoara() {
            Pozoriste po = new Pozoriste("Dusko Radovic");
            Repertoar r = new Repertoar(po);
            
            Predstava pr1 = new Predstava("Princeza na zrnu graska", po, new Reditelj("Iva Milosevic", po));
            r.dodaj(pr1);
            
            Predstava pr2 = new Predstava("Cudne ljubavi", po, new Reditelj("Djurdja Tesic", po));
            r.dodaj(pr2);
            
            int oldBrojPredstava = r.brojPredstava();
            r.ukloni("Princeza na zrnu graska");
            int newbrojPredstava = r.brojPredstava();
            
            assertEquals(oldBrojPredstava - 1, newbrojPredstava);
    }
    
    @Test
    public void uklanjanjeNullPredstaveIzRepertoara() {
            try {
                Pozoriste po = new Pozoriste("Dusko Radovic");
                Repertoar r = new Repertoar(po);
                
                Predstava pr1 = new Predstava("Princeza na zrnu graska", po, new Reditelj("Iva Milosevic", po));
                r.dodaj(pr1);
                
                Predstava pr2 = new Predstava("Cudne ljubavi", po, new Reditelj("Djurdja Tesic", po));
                r.dodaj(pr2);
                
                r.ukloni(null);
                
            } catch (PreconditionError expected) {
                assertEquals("[naziv != null && naziv.length() > 0]", expected.getMessages().toString());
            }
    }
    
    @Test
    public void uklanjanjePredstaveSaPraznimImenomIzRepertoara() {
            try {
                Pozoriste po = new Pozoriste("Dusko Radovic");
                Repertoar r = new Repertoar(po);
                
                Predstava pr1 = new Predstava("Princeza na zrnu graska", po, new Reditelj("Iva Milosevic", po));
                r.dodaj(pr1);
                
                Predstava pr2 = new Predstava("Cudne ljubavi", po, new Reditelj("Djurdja Tesic", po));
                r.dodaj(pr2);
                
                r.ukloni("");
                
            } catch (PreconditionError expected) {
                assertEquals("[naziv != null && naziv.length() > 0]", expected.getMessages().toString());
            }
    }
    
    @Test
    public void uklanjanjeNepostojecePredstaveIzRepertoara() {
            try {
                Pozoriste po = new Pozoriste("Dusko Radovic");
                Repertoar r = new Repertoar(po);
                
                Predstava pr1 = new Predstava("Princeza na zrnu graska", po, new Reditelj("Iva Milosevic", po));
                r.dodaj(pr1);
                
                Predstava pr2 = new Predstava("Cudne ljubavi", po, new Reditelj("Djurdja Tesic", po));
                r.dodaj(pr2);
                
                //pokusaj uklanjanja predstave koja ne postoji
                r.ukloni("Cudna ljubav");
                
            } catch (PostconditionError expected) {
                assertEquals("[brojPredstava == old(brojPredstava) - 1]", expected.getMessages().toString());
            }
    }
}
