import java.util.ArrayList;

public class Dame extends Figur
{
    private Feld feld;
    public Dame(Feld feld)
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
