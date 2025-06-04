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
    @Override
    public ArrayList<Feld> möglicheZüge() {
        ArrayList<Feld> M = new ArrayList<>();
        ArrayList<Feld> felder = feld.brettGeben().felder;
        int index = felder.indexOf(feld);

        int[] jumps = {-17, -15, -10, -6, 6, 10, 15, 17};

        if (feld.brettGeben().schwarz.contains(this)) {
            for (int jump : jumps) {
                int newIndex = index + jump;
                if (newIndex < 0 || newIndex >= 64) continue;

                int colDiff = Math.abs((index % 8) - (newIndex % 8));
                if (colDiff < 1 || colDiff > 2) continue;

                Feld ziel = felder.get(newIndex);
                Figur figur = ziel.figurGeben();

                if (figur == null || feld.brettGeben().weiß.contains(figur)) {
                    M.add(ziel);
                }
            }
        }

        if (feld.brettGeben().weiß.contains(this)) {
            for (int jump : jumps) {
                int newIndex = index + jump;
                if (newIndex < 0 || newIndex >= 64) continue;

                int colDiff = Math.abs((index % 8) - (newIndex % 8));
                if (colDiff < 1 || colDiff > 2) continue;

                Feld ziel = felder.get(newIndex);
                Figur figur = ziel.figurGeben();

                if (figur == null || feld.brettGeben().schwarz.contains(figur)) {
                    M.add(ziel);
                }
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
