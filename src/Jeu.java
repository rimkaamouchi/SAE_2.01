public class Jeu
{
	private int        hauteur;
    private int        largeur;
    private int        nbCoul = 4;
    private int        nbSymboles;
    private int        tailleCase;

    private char       val;
    private char[]     symboles;

    private Cellule[][]    plateau;
    
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

        this.plateau    = new Cellule[hauteur][largeur];
    }
    public boolean verifLien( int posX, int posY, char choix )
	{
		switch( choix )
		{
			case 'e' :
				for ( int col = posY; col < this.largeur; col++ )
				{
					return ;
				}
				return false;

			case 'd' :
				for ( int lig = posX,col = posY; lig > 0 && col < this.largeur; lig--,col++  )
				{
					return true;
				}
				return false;

			case 's' :
				for ( int lig = posX; lig > 0; lig-- )
				{
					return true;
				}
				return false;

			case 'q' :
				for ( int lig = posX,col = posY; lig > 0 && col > 0; lig--,col-- )
				{
					return true;
				}
				return false;

			default : 
				System.out.print( "charactère non indexé" );
				return false;
		}
	}
    public int     getNbLigne()             { return this.plateau   .length ; }
    public int     getNbColonne()           { return this.plateau[0].length ; }
    public Cellule getVal(int lig, int col) { return this.plateau[lig][col] ; } //méthode à changer
}