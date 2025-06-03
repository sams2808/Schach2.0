package src;

public class GFeld {
    private GFigur GFigur;
    private final int row;
    private final int col;

    public GFeld(int row, int col, GFigur GFigur) {
        this.row = row;
        this.col = col;
        this.GFigur = GFigur;
    }

    public GFigur getFigur() {
        return GFigur;
    }

    public void setFigur(GFigur GFigur) {
        this.GFigur = GFigur;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public String getName() {
        char colChar = (char) ('A' + col);
        int rowNum = 8 - row; // Umwandlung von 0-7 nach 1-8
        return String.valueOf(colChar) + rowNum;
    }
}
