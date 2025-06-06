public class Feld
{
    private Figur figur;
    private String name;
    private Schachbrett brett;
    public Feld(String name, Schachbrett brett)
    {
        this.name = name;
        this.brett = brett;
    }
    public void figurHinzufügen(Figur figur)
    {
        this.figur = figur;
    }
    public Figur figurGeben()
    {
        return figur;
    }
    public Schachbrett brettGeben()
    {
        return brett;
    }
    public void FigurEntfernen(){figur = null}
}
