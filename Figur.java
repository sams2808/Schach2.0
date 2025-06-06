import java.util.ArrayList;

public class Figur
{
    private Feld feld;
    public Feld getFeld()
    {
        return feld;
    }
    public ArrayList möglicheZüge()
    {
        return null;
    }
    public boolean obSetztSchach() {
        ArrayList<Feld> list = this.möglicheZüge();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).figurGeben() instanceof König) {
                return true;
            }
        }
        return false;
    }
    public void Ziehen(Feld zielfeld)
    {
        Schachbrett brett = feld.brettGeben();
        Feld aktuell = feld;
        feld.figurEntfernen();
        if(brett.MattPrüfen(brett.momentanerSpielerGeben()) != false)
        {
            aktuell.figurHinzufügen(this);
            return;
        }
        Figur Feind = zielfeld.figurGeben();
        if(brett.schwarz.contains(Feind))
        {
            brett.schwarz.remove(Feind);
        }
        else
        {
            brett.weiß.remove(Feind);
        }
        zielfeld.figurHinzufügen(this);
        feld = zielfeld;
        brett.MattPrüfen(brett.momentanerSpielerGeben()); //muss noch implementiert werden
        brett.spielerWechseln();
    }
}
