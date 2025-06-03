import java.util.ArrayList;

public class König extends Figur
{
    private Feld feld;
    public König(Feld feld)
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
