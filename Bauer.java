import java.util.ArrayList;

public class Bauer extends Figur
{
    private Feld feld;
    private boolean ersterZug;
    public Bauer(Feld feld)
    {
        this.feld = feld;
        ersterZug = true;
    }
    public Feld getFeld()
    {
        return feld;
    }
    @Override public ArrayList möglicheZüge()
    {
        ArrayList<Feld> M = new ArrayList();
        feld.brettGeben().felder.indexOf(feld) + 1 = int i
            if(feld.brettGeben().felder(i).figurGeben() == null)
            {
                M.add(feld.brettGeben().felder(i));
            }
            if(ersterZug == true)
            {
                if(feld.brettGeben().felder(i+1).figurGeben() == null && if(feld.brettGeben().felder(i).figurGeben() == null))
                {
                    M.add(feld.brettGeben().felder(i+1));
                }
            }
            if(feld.brettGeben().schwarz.contains(this))
            {
            if(feld.brettGeben().schwarz.contains(feld.brettGeben().felder(i+8).figurGeben()) == true)
            }
    }

}
