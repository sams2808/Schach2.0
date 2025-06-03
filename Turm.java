import java.util.ArrayList;

public class Turm extends Figur
{
    private Feld feld;
    public Turm(Feld feld)
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
