package src;

import java.util.List;

public class GSchachbrett {
    private final int ROWS = 8;
    private final int COLS = 8;
    private final Feld[][] board = new Feld[ROWS][COLS];

    public GSchachbrett() {
        initBoard();
    }

    private void initBoard() {
        String[][] start = {
                {"♜", "♞", "\u265D", "\u265B", "\u265A", "\u265D", "\u265E", "\u265C"},
                {"\u265F", "\u265F", "\u265F", "\u265F", "\u265F", "\u265F", "\u265F", "\u265F"},
                {"", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", ""},
                {"\u2659", "\u2659", "\u2659", "\u2659", "\u2659", "\u2659", "\u2659", "\u2659"},
                {"\u2656", "\u2658", "\u2657", "\u2655", "\u2654", "\u2657", "\u2658", "\u2656"},
        };
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                String s = start[row][col];
                Figur gFigur = null;
                if (!s.isEmpty()) {
                    Figur.Farbe farbe = (row < 2) ? Figur.Farbe.SCHWARZ : Figur.Farbe.WEISS;
                    gFigur = new Figur(s, farbe);
                }
                board[row][col] = new Feld(row, col, gFigur);
            }
        }
    }

    public Feld getFeld(int row, int col) {
        return board[row][col];
    }

    public int getRows() { return ROWS; }
    public int getCols() { return COLS; }

    // Hier können später Methoden für Züge, Prüfung usw. ergänzt werden

    public void updateFromLogic(GSchachbrett logikBrett) {
        java.util.List<Feld> felder = logikBrett.getGFelder();
        for (Feld feld : felder) {
            String name = feld.getName();
            int col = name.charAt(0) - 'A';
            int row = 8 - Character.getNumericValue(name.charAt(1));
            Figur figur = feld.figurGeben();
            if (figur != null) {
                String symbol = figur.getUnicode();
                Figur.Farbe farbe = figur.istWeiss() ? Figur.Farbe.WEISS : Figur.Farbe.SCHWARZ;
                board[row][col].setFigur(new Figur(symbol, farbe));
            } else {
                board[row][col].setFigur(null);
            }
        }
    }

    /**
     * Führt einen Zug von einem Feld auf ein anderes aus und gibt die Schachnotation des Zuges aus.
     * @param fromRow Startreihe
     * @param fromCol Startspalte
     * @param toRow Zielreihe
     * @param toCol Zielspalte
     */
    public void moveFigur(int fromRow, int fromCol, int toRow, int toCol) {
        Feld from = getFeld(fromRow, fromCol);
        Feld to = getFeld(toRow, toCol);

        if (from.figurGeben() != null) {
            String von = from.getName();
            String nach = to.getName();
            // Exakte Zugausgabe
            System.out.println("Zug von " + von.toLowerCase() + " nach " + nach.toLowerCase());
            // ... bisherige Schachnotation kann optional zusätzlich ausgegeben werden...
            to.setFigur(from.figurGeben());
            from.setFigur(null);

        }
        else
        {
            System.out.println("Ungültiger Zug: Kein Figur auf dem Startfeld " + from.getName());
            //return null; // oder eine andere Fehlerbehandlung
        }

    }

    private List<Feld> getGFelder() {
        List<Feld> felder = new java.util.ArrayList<>();
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                felder.add(board[row][col]);
            }
        }
        return felder;
    }
}
