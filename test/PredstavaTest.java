import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.google.java.contract.PreconditionError;

//***************************
//Testovi za klasu Predstava
//***************************

class PredstavaTest {
    
    @Test
    public void novaPredstava() {
        Pozoriste po = new Pozoriste("Dusko Radovic");
        Reditelj r = new Reditelj("Iva Milosevic", po);
        Predstava pr = new Predstava("Princeza na zrnu graska", po, r);
        
        //"naziv != null && naziv.length() > 0"
        assertEquals(pr.naziv(), "Princeza na zrnu graska");
        
        //pozoriste != null
        assertEquals(pr.pozoriste(), po);
        
        //reditelj != null && pozoriste == reditelj.pozoriste()
        assertEquals(pr.pozoriste(), r.pozoriste());
    }
    
    @Test
    public void novaPredstavaBezImenaPozoristaIReditelja() {
        try {
            Predstava pr = new Predstava(null, null, null);
        } catch (PreconditionError expected) {
            assertEquals("[naz != null && naz.length() > 0]", expected.getMessages().toString());
        }
    }
    
    @Test
    public void novaPredstavaSaPraznimImenom() {
        try {
            Predstava pr = new Predstava("", null, null);
        } catch (PreconditionError expected) {
            assertEquals("[naz != null && naz.length() > 0]", expected.getMessages().toString());
        }
    }
    
    @Test
    public void novaPredstavaBezPozoristaIReditelja() {
        try {
            Predstava pr = new Predstava("Princeza na zrnu graska", null, null);
        } catch (PreconditionError expected) {
            assertEquals("[p != null]", expected.getMessages().toString());
        }
    }
    
    @Test
    public void novaPredstavaBezReditelja() {
        try {
            Pozoriste po = new Pozoriste("Dusko Radovic");
            Predstava pr = new Predstava("Princeza na zrnu graska", po, null);
        } catch (PreconditionError expected) {
            assertEquals("[red != null]", expected.getMessages().toString());
        }
    }
    
    @Nested
    class DefinisaniPozoristeIRediteljTest {
        
        Pozoriste po;
        Reditelj r;
        
        @BeforeEach
        public void init() {
            po = new Pozoriste("Dusko Radovic");
            r = new Reditelj("Iva Milosevic", po);
        }
        
        @Test
        public void novaPredstavaRediteljBezPozorista() {
            try {
                Reditelj r1 = new Reditelj("Ivan Milosevic", null);
                Predstava pr = new Predstava("Princeza na zrnu graska", po, r1);
            } catch (PreconditionError expected) {
                //Greska prilikom pravljenja reditelja. Pozoriste kome pripada ne sme biti null
                assertEquals("[p != null]", expected.getMessages().toString());
            }
        }
        
        @Test
        public void novaPredstavaRediteljIzDrugogPozorista() {
            try {
                Pozoriste po2 = new Pozoriste("BDP");
                Predstava pr = new Predstava("Princeza na zrnu graska", po2, r);
            } catch (PreconditionError expected) {
                assertEquals("[p == red.pozoriste()]", expected.getMessages().toString());
            }
        }
        
        @Test
        public void dodavanjeZaposlenog() {
            try {
                Predstava pr = new Predstava("Princeza na zrnu graska", po, r);
                pr.dodaj(null);
            } catch (PreconditionError expected) {
                assertEquals("[z != null]", expected.getMessages().toString());
            }
        }
        
        @Test
        public void dodavanjeGlumcaIzDrugogPozorista() {
            try {
                Pozoriste po2 = new Pozoriste("BDP");
                Predstava pr = new Predstava("Princeza na zrnu graska", po, r);
                pr.dodaj(new Glumac("Jovo Maksic", po2));
            } catch (PreconditionError expected) {
                assertEquals("[z.pozoriste() == poz]", expected.getMessages().toString());
            }
        }
        
        @Test
        public void dodavanjeDrugogReditelja() {
            try {
                Predstava pr = new Predstava("Princeza na zrnu graska", po, r);
                pr.dodaj(new Reditelj("Djurdja Tesic", po));
            } catch (PreconditionError expected) {
                assertEquals("[z.vrsta() != 'R']", expected.getMessages().toString());
            }
        }
        
        @Test
        public void dodavanjeViseOdDvaKostimografa() {
            try {
                Predstava pr = new Predstava("Princeza na zrnu graska", po, r);
                pr.dodaj(new Kostimograf("Maja Mirkovic", po));
                pr.dodaj(new Kostimograf("Zorana Petrov", po));
                pr.dodaj(new Kostimograf("Zoran Petrov", po));
            } catch (PreconditionError expected) {
                assertEquals("[brKost < 2]", expected.getMessages().toString());
            }
        }
        
        @Test
        public void dodavanjeKostimografaIGlumaca() {
                Predstava pr = new Predstava("Princeza na zrnu graska", po, r);
                
                int oldBrojKost = pr.brKost();
                pr.dodaj(new Kostimograf("Maja Mirkovic", po));
                int newBrojKost = pr.brKost();
                
                int oldBrojGlumaca = pr.brGlumaca();
                pr.dodaj(new Glumac("Milos Samolov", po));
                pr.dodaj(new Glumac("Jelena Ilic", po));
                pr.dodaj(new Glumac("Sandra Rodic", po));
                int newBrojGlumaca = pr.brGlumaca();
                
                assertEquals(oldBrojKost + 1, newBrojKost);
                assertEquals(oldBrojGlumaca + 3, newBrojGlumaca);
        }
    }
}
