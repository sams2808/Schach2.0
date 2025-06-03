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
    @Override
    public ArrayList<Feld> möglicheZüge() {
        ArrayList<Feld> M = new ArrayList<>();
        ArrayList<Feld> felder = feld.brettGeben().felder;
        int i = felder.indexOf(feld) + 1;

        // Prüfe, ob i im gültigen Bereich ist
        if (i < felder.size() && felder.get(i).figurGeben() == null) {
            M.add(felder.get(i));
        }

        if (ersterZug) {
            // Prüfe, ob i+1 im gültigen Bereich ist
            if (i + 1 < felder.size() && felder.get(i).figurGeben() == null && felder.get(i + 1).figurGeben() == null)
            {
                M.add(felder.get(i + 1));
            }
        }

        if (feld.brettGeben().schwarz.contains(this)) {
            // Prüfe, ob i+8 und i-8 im gültigen Bereich sind
            if (i + 8 < felder.size() && feld.brettGeben().weiß.contains(felder.get(i + 8).figurGeben()))
            {
                M.add(felder.get(i + 8));
            }
            if (i - 8 >= 0 && feld.brettGeben().weiß.contains(felder.get(i - 8).figurGeben()))
            {
                M.add(felder.get(i - 8));
            }
        }
        return M;
    }
}
