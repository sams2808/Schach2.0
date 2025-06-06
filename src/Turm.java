package src;

import java.util.ArrayList;

public class Turm extends Figur
{
    private Feld feld;
    public Turm(Feld feld)
    {
        this.feld = feld;
    }
    public Feld getFeld()
    {
        return feld;
    }
    @Override public ArrayList<Feld> möglicheZüge() {
        ArrayList<Feld> M = new ArrayList<>();
        ArrayList<Feld> felder = feld.brettGeben().felder;
        int index = felder.indexOf(feld);

        int[] directions = {-1, 1, -8, 8};

        if (feld.brettGeben().schwarz.contains(this)) {
            for (int dir : directions) {
                int pos = index;
                while (true) {
                    int next = pos + dir;
                    if (next < 0 || next >= 64) break;

                    if (dir == -1 || dir == 1) {
                        int colDiff = Math.abs((pos % 8) - (next % 8));
                        if (colDiff != 1) break;
                    }

                    Feld ziel = felder.get(next);
                    Figur figur = ziel.figurGeben();


                    if (figur != null && feld.brettGeben().schwarz.contains(figur)) {
                        break;
                    }

                    M.add(ziel);

                    if (figur != null && feld.brettGeben().weiß.contains(figur)) {
                        break;
                    }
                    pos = next;
                }
            }
        }

        if (feld.brettGeben().weiß.contains(this)) {
            for (int dir : directions) {
                int pos = index;
                while (true) {
                    int next = pos + dir;
                    if (next < 0 || next >= 64) break;

                    if (dir == -1 || dir == 1) {
                        int colDiff = Math.abs((pos % 8) - (next % 8));
                        if (colDiff != 1) break;
                    }

                    Feld ziel = felder.get(next);
                    Figur figur = ziel.figurGeben();

                    if (figur != null && feld.brettGeben().weiß.contains(figur)) {
                        break;
                    }

                    M.add(ziel);

                    if (figur != null && feld.brettGeben().schwarz.contains(figur)) {
                        break;
                    }
                    pos = next;
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
