import java.util.ArrayList;

public class Läufer extends Figur
{
    private Feld feld;
    public Läufer(Feld feld)
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
