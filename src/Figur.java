package src;

public class Figur {

    public enum Farbe { WEISS, SCHWARZ }

    private final String symbol;
    private final Farbe farbe;

    public Figur(String symbol, Farbe farbe) {
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
