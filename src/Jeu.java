

public class Jeu
{
	private int        hauteur;
    private int        largeur;
    private int        nbCoul = 4;
    private int        nbSymboles;
    private int        tailleCase;

    private char       val;
    private char[]     symboles;

    private Jeu[][]    plateau;
    
    public Jeu()
    {
		/*Création du plateau (base)*/
        this.hauteur    = 7;
        this.largeur    = 7;
        this.nbCoul     = 4;
        this.nbSymboles = 4;
        this.tailleCase = 50;

        this.val        = val; 
        this.symboles   = new char[4];
        
        this.plateau    = new Jeu[hauteur][largeur];
    }

    public int getNbLigne(){return this.plateau.length;}
    public int getNbColone(){return this.plateau[0].length;}
}