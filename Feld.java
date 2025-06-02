public class Feld
{
    private Figur figur;
    public Feld(String name)
    {

    }
    public void figurHinzufügen(Figur figur)
    {
        this.figur = figur;
    }
    public Figur figurGeben()
    {
        return figur;
    }
}
