import java.util.ArrayList;
public class Schachbrett
{
    private ArrayList<Figur> schwarz = new ArrayList<>();
    private ArrayList<Figur> weiß = new ArrayList<>();
    private ArrayList<Feld> felder = new ArrayList<>();
    private boolean momentanerSpieler;

    public Schachbrett()
    {
        for (char c = 'A'; c <= 'H'; c++)
        {
            for (int i = 1; i <= 8; i++)
            {
                String name = "" + c + i;
                felder.add(new Feld(name));
            }

            for (int i = 1; i <= 57; i += 8)
            {
                weiß.add(new Bauer(felder.get(i)));
            }

            for (int i = 7; i <= 61; i += 8)
            {
                schwarz.add(new Bauer(felder.get(i)));
            }
        }

    }
    //public boolean MattPrüfen() {}
    //public boolean MattPrüfen() {}

}
