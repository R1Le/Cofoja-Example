//Glumac

public class Glumac extends Zaposleni {
    
    public Glumac(String i, Pozoriste poz) {
        super(i, poz);
    }
    
    @Override
    public char vrsta() {
        return 'G';
    }
}