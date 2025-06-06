package src;

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

    @Override
    public ArrayList<Feld> möglicheZüge() {
        ArrayList<Feld> M = new ArrayList<>();
        ArrayList<Feld> felder = feld.brettGeben().felder;
        int index = felder.indexOf(feld);

        int[] directions = {-9, -8, -7, -1, 1, 7, 8, 9};

        if (feld.brettGeben().schwarz.contains(this)) {
            for (int dir : directions) {
                int newIndex = index;
                while (true) {
                    newIndex += dir;
                    if (newIndex < 0 || newIndex >= 64) break;

                    if (Math.abs((newIndex % 8) - (index % 8)) > 1) break;

                    Feld zielFeld = felder.get(newIndex);
                    Figur figur = zielFeld.figurGeben();

                    if (figur == null) {
                        M.add(zielFeld);
                    } else if (feld.brettGeben().weiß.contains(figur)) {
                        M.add(zielFeld);
                        break;
                    } else {
                        break;
                    }
                }
            }
        }

        if (feld.brettGeben().weiß.contains(this)) {
            for (int dir : directions) {
                int newIndex = index;
                while (true) {
                    newIndex += dir;
                    if (newIndex < 0 || newIndex >= 64) break;
                    
                    if (Math.abs((newIndex % 8) - (index % 8)) > 1) break;

                    Feld zielFeld = felder.get(newIndex);
                    Figur figur = zielFeld.figurGeben();

                    if (figur == null) {
                        M.add(zielFeld);
                    } else if (feld.brettGeben().schwarz.contains(figur)) {
                        M.add(zielFeld);
                        break;
                    } else {
                        break;
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
