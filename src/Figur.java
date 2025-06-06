package src;

import java.util.ArrayList;

public class Figur
{
    public enum Farbe { WEISS, SCHWARZ }
    private final String symbol;
    private final Farbe farbe;
    private Feld feld;

    public Figur(String symbol, Farbe farbe) {
        this.symbol = symbol;
        this.farbe = farbe;
    }

    public Feld getFeld()
    {
        return feld;
    }

    public String getSymbol() {
        return symbol;
    }

    public Farbe getFarbe() {
        return farbe;
    }

    public boolean istWeiss() {
        return farbe == Farbe.WEISS;
    }

    public String getUnicode() {
        switch (symbol) {
            case "♜": return "R"; // Turm
            case "♞": return "N"; // Springer
            case "♝": return "B"; // Läufer
            case "♛": return "Q"; // Dame
            case "♚": return "K"; // König
            case "♟": return "P"; // Bauer
            default: return symbol;
        }
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
