package src;

import java.util.ArrayList;
public class Schachbrett
{
    public final ArrayList<Figur> schwarz = new ArrayList<>();
    public final ArrayList<Figur> weiß = new ArrayList<>();
    public final ArrayList<Feld> felder = new ArrayList<>();
    private boolean momentanerSpieler;

    public Schachbrett()
    {
        //Felder erstellen
        for (char c = 'A'; c <= 'H'; c++)
        {
            for (int i = 1; i <= 8; i++)
            {
                String name = "" + c + i;
                felder.add(new Feld(name, this));
            }
        }
            //Bauern erstellen und ihnen Felder zuweisen
            for (int i = 1; i <= 57; i += 8)
            {
                weiß.add(new Bauer(felder.get(i)));
            }

            for (int i = 6; i <= 62; i += 8)
            {
                schwarz.add(new Bauer(felder.get(i)));
            }
            //den Figuren Felder zuweisen
            weiß.add(new Turm(felder.get(0)));
            weiß.add(new Turm(felder.get(56)));
            weiß.add(new Springer(felder.get(8)));
            weiß.add(new Springer(felder.get(48)));
            weiß.add(new Läufer(felder.get(16)));
            weiß.add(new Läufer(felder.get(40)));
            weiß.add(new König(felder.get(24)));
            weiß.add(new Dame(felder.get(32)));

            //den Feldern figuren zuweisen
            for (Figur figur : schwarz)
            {
                figur.getFeld().figurHinzufügen(figur);
            }

            //den Figuren Felder zuweisen
            schwarz.add(new Turm(felder.get(7)));
            schwarz.add(new Turm(felder.get(63)));
            schwarz.add(new Springer(felder.get(15)));
            schwarz.add(new Springer(felder.get(55)));
            schwarz.add(new Läufer(felder.get(23)));
            schwarz.add(new Läufer(felder.get(47)));
            schwarz.add(new König(felder.get(31)));
            schwarz.add(new Dame(felder.get(39)));

            //den Feldern Figuren zuweisen
            for (Figur figur_ : schwarz)
            {
                figur_.getFeld().figurHinzufügen(figur_);
            }
    }

    public boolean SchachPrüfen(boolean Farbe) //prüft ob Frabe im Schach steht
    {
        if(Farbe == false)
        {
            for (int i = 0; i < schwarz.size(); i++)
            {
                if(schwarz.get(i).obSetztSchach())
                {
                    return true;
                }
            }
        }
        else
        {
            for (int i = 0; i < weiß.size(); i++)
            {
                if(weiß.get(i).obSetztSchach())
                {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean momentanerSpielerGeben()
    {
        return momentanerSpieler;
    }
    public void spielerWechseln()
    {
        momentanerSpieler = !momentanerSpieler;
    }

}



