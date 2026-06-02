

public class Controleur
{
    private Jeu         jeu;
    private FrameAppli  frameAppli;
    private FramePara   framePara;
    private FramePioche framePioche;

    public Controleur()
    {
        this.jeu         = new Jeu();
        this.frameAppli  = new FrameAppli( this );
        this.framePara   = new FramePara( this );
        this.framePioche = new FramePioche( this );
    }

    public int getNbLigne() { return this.jeu.getNbLigne() ; }
    public int getNbColone(){ return this.jeu.getNbColone(); }

    public static void main(String[] args)
    {
        new Controleur();
    }
}