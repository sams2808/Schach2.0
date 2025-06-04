import java.util.ArrayList;

public class König extends Figur {
    private Feld feld;

    public König(Feld feld) {
        this.feld = feld;
    }

    public Feld getFeld() {
        return feld;
    }

    @Override
    public ArrayList<Feld> möglicheZüge() {
        ArrayList<Feld> M = new ArrayList<>();
        ArrayList<Feld> felder = feld.brettGeben().felder;
        int index = felder.indexOf(feld);

        int[] directions = {-9, -8, -7, -1, 1, 7, 8, 9};

        if (feld.brettGeben().schwarz.contains(this)) {
            for (int dir : directions) {
                int newIndex = index + dir;

                if (newIndex >= 0 && newIndex < 64) {
                    if (Math.abs((newIndex % 8) - (index % 8)) > 1) continue;

                    Feld zielFeld = felder.get(newIndex);
                    Figur figur = zielFeld.figurGeben();

                    if (figur == null || feld.brettGeben().weiß.contains(figur)) {
                        M.add(zielFeld);
                    }
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
