//Kostimograf

public class Kostimograf extends Zaposleni {
    
    public Kostimograf(String i, Pozoriste poz) {
        super (i, poz);
    }
    
    @Override
    public char vrsta() {
        return 'K';
    }
}