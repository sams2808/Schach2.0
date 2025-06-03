package src;

public class Feld {
    private GFigur GFigur;
    private final int row;
    private final int col;

    public Feld(int row, int col, GFigur GFigur) {
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
}
