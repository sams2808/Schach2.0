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
    @Override public ArrayList<Feld> möglicheZüge() {
        ArrayList<Feld> M = new ArrayList<>();
        ArrayList<Feld> felder = feld.brettGeben().felder;
        int i = felder.indexOf(feld) + 1;

        if (i < felder.size() && felder.get(i).figurGeben() == null) {
            M.add(felder.get(i));
        }

        if (ersterZug) {
            if (i + 1 < felder.size() && felder.get(i).figurGeben() == null && felder.get(i + 1).figurGeben() == null)
            {
                M.add(felder.get(i + 1));
            }
        }

        if (feld.brettGeben().schwarz.contains(this))
        {
            if (i + 8 < felder.size() && feld.brettGeben().weiß.contains(felder.get(i + 8).figurGeben()))
            {
                M.add(felder.get(i + 8));
            }
            if (i - 8 >= 0 && feld.brettGeben().weiß.contains(felder.get(i - 8).figurGeben()))
            {
                M.add(felder.get(i - 8));
            }
        }
        if (feld.brettGeben().weiß.contains(this))
        {
            if (i + 8 < felder.size() && feld.brettGeben().schwarz.contains(felder.get(i + 8).figurGeben()))
            {
                M.add(felder.get(i + 8));
            }
            if (i - 8 >= 0 && feld.brettGeben().schwarz.contains(felder.get(i - 8).figurGeben()))
            {
                M.add(felder.get(i - 8));
            }
        }
        return M;
    }
    public boolean obSetztSchach() {
        ArrayList<Feld> list = this.möglicheZüge();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).figurGeben() instanceof König) {
                return true;
            }
        }
        return false;
    }
}
