//Reditelj

public class Reditelj extends Zaposleni {
    
    public Reditelj(String i, Pozoriste poz) {
        super (i, poz);
    }
    
    @Override
    public char vrsta() {
        return 'R';
    }
}