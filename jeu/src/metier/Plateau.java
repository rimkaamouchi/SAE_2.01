package jeu.src.metier;

import java.util.ArrayList;

public class Plateau
{
	/*---------------------------------------*/
	/*            Attributs                  */
	/*---------------------------------------*/
	private String      nom;
	private int         hauteur;
	private int         largeur;
	private int         nbSymboles;

	private Cellule[][] plateau;
	private String[][]  zonesPlateau;
	private ArrayList<Lien> routes;

	/*---------------------------------------*/
	/*            Constructeur               */
	/*---------------------------------------*/
	public Plateau( String nom, int hauteur, int largeur, int nbSymboles )
	{
		this.nom        = nom;
		this.hauteur    = hauteur;
		this.largeur    = largeur;
		this.nbSymboles = nbSymboles;

		this.plateau      = new Cellule[hauteur][largeur];
		this.zonesPlateau = new String [hauteur][largeur];
		this.routes       = new ArrayList<>();
	}


	public void setZone( int lig, int col, String zone )
	{
		this.zonesPlateau[lig][col] = zone;
	}

	public void ajouterLien( Lien lien )
	{
		this.routes.add( lien );
	}

	/** Retourne true si un lien direct existe entre les deux cellules */
	public boolean lienExiste( Cellule depart, Cellule arrivee )
	{
		for ( Lien l : this.routes )
		{
			if ( l.getDepart()  == depart  && l.getArrivee() == arrivee ) return true;
			if ( l.getDepart()  == arrivee && l.getArrivee() == depart  ) return true;
		}
		return false;
	}

	/** Retourne tous les voisins directs d'une cellule (cellules reliées par un lien) */
	public ArrayList<Cellule> getVoisins( Cellule cel )
	{
		ArrayList<Cellule> voisins = new ArrayList<>();
		for ( Lien l : this.routes )
		{
			if ( l.getDepart()  == cel ) voisins.add( l.getArrivee() );
			if ( l.getArrivee() == cel ) voisins.add( l.getDepart()  );
		}
		return voisins;
	}

	/** Retourne la cellule du sommet correspondant à la couleur donnée */
	public Cellule getCelluleCouleur( char couleur )
	{
		for ( int l = 0; l < hauteur; l++ )
			for ( int c = 0; c < largeur; c++ )
				if ( plateau[l][c] != null && plateau[l][c].getSymbole() == couleur )
					return plateau[l][c];
		return null;
	}

	/** Retourne tous les symboles couleur présents sur le plateau */
	public char[] getCouleurs()
	{
		ArrayList<Character> couleurs = new ArrayList<>();
		for ( int l = 0; l < hauteur; l++ )
			for ( int c = 0; c < largeur; c++ )
				if ( plateau[l][c] != null )
					couleurs.add( plateau[l][c].getSymbole() );

		char[] result = new char[couleurs.size()];
		for ( int i = 0; i < couleurs.size(); i++ )
			result[i] = couleurs.get(i);
		return result;
	}
	/**
	 * Retourne la cellule existante en (x, y) si elle existe,
	 * sinon en crée une nouvelle et la stocke dans le plateau.
	 */
	public Cellule getOuCreerCellule( int x, int y )
	{
		if ( this.plateau[x][y] == null )
			this.plateau[x][y] = new Cellule( x, y );
		return this.plateau[x][y];
	}

	public String          getNom()          { return this.nom;          }
	public int             getHauteur()      { return this.hauteur;      }
	public int             getLargeur()      { return this.largeur;      }
	public int             getNbSymboles()   { return this.nbSymboles;   }
	public Cellule[][]     getPlateau()      { return this.plateau;      }
	public String[][]      getZonesPlateau() { return this.zonesPlateau; }
	public ArrayList<Lien> getRoutes()       { return this.routes;       }
	public int             getNbLigne()      { return this.plateau   .length; }
	public int             getNbColonne()    { return this.plateau[0].length; }
	public Cellule         getCellule( int x, int y ) { return this.plateau[x][y]; }
}