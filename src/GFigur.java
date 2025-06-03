package src;

public class GFigur {

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
