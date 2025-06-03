package src;

public class GFigur {

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
            default: return symbol; // Für andere Figuren oder leere Felder
        }
    }

    public enum Farbe { WEISS, SCHWARZ }

    private final String symbol;
    private final Farbe farbe;

    public GFigur(String symbol, Farbe farbe) {
        this.symbol = symbol;
        this.farbe = farbe;
    }
    public String getSymbol() {
        return symbol;
    }

    public Farbe getFarbe() {
        return farbe;
    }
}
