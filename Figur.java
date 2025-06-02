import java.util.ArrayList;

public class Figur
{
    private Feld feld;
    public Feld getFeld()
    {
        return feld;
    }
    public ArrayList möglicheZüge()
    {
        return null;
    }
    public boolean obSetztSchach()
    {
        int k = 0;
        ArrayList<Feld> list = this.möglicheZüge();
        while (int i = 0; i < list.size(); i++)
        {
            if(list.get(i).figurGeben() instanceof König);
            k++;
        }
        if(k > 0)
        {
            return true
        }
        else
        {
            return false;
        }
    }
}
