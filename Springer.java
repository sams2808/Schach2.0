import java.util.ArrayList;

public class Springer extends Figur
{
    private Feld feld;
    public Springer(Feld feld)
    {
        this.feld = feld;
    }
    public Feld getFeld()
    {
        return feld;
    }
    @Override public ArrayList möglicheZüge()
    {
        return null;
    }
}
