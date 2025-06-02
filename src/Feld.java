package src;

public class Feld {
    private Figur figur;
    private final int row;
    private final int col;

    public Feld(int row, int col, Figur figur) {
        this.row = row;
        this.col = col;
        this.figur = figur;
    }

    public Figur getFigur() {
        return figur;
    }

    public void setFigur(Figur figur) {
        this.figur = figur;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
