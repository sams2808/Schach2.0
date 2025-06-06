package src;

public class Feld {
    private Figur figur;
    private String name;
    private Schachbrett brett;
    private int row = -1;
    private int col = -1;

    // Konstruktor für Logik (Name und Brett)
    public Feld(String name, Schachbrett brett) {
        this.name = name;
        this.brett = brett;
        // Versuche, Koordinaten aus dem Namen zu berechnen
        if (name != null && name.length() == 2) {
            this.col = name.charAt(0) - 'A';
            this.row = 8 - Character.getNumericValue(name.charAt(1));
        }
    }
    // Konstruktor für GUI (Koordinaten und Figur)
    public Feld(int row, int col, Figur figur) {
        this.row = row;
        this.col = col;
        this.figur = figur;
        this.name = getName();
    }
    public void figurHinzufügen(Figur figur) {
        this.figur = figur;
    }
    public Figur figurGeben() {
        return figur;
    }
    public Schachbrett brettGeben() {
        return brett;
    }
    public void figurEntfernen() { figur = null; }
    public int getRow() { return row; }
    public int getCol() { return col; }
    public String getName() {
        if (name != null) return name;
        char colChar = (char) ('A' + col);
        int rowNum = 8 - row;
        return String.valueOf(colChar) + rowNum;
    }
    public void setFigur(Figur figur) { this.figur = figur; }
}
