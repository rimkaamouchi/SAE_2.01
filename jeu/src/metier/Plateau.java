package jeu.src.metier;

import java.util.ArrayList;

/**
 * Représente le plateau de jeu concret utilisé lors de la partie.
 * <p>
 * Cette classe gère la grille de cellules, les zones du plateau, 
 * ainsi que l'ensemble des liaisons (routes) construites par le joueur.
 * </p>
 * * @author Groupe 11: Quentin Deshayes, Roxane Sidolle, Manon Rigoult, Rim Kaamouchi et Mykhailo Liapin
 * @version 1.0
 */

public class Plateau
{
	/*---------------------------------------*/
	/*            Attributs                  */
	/*---------------------------------------*/

	/** Nom ou identifiant du scénario de plateau chargé. */
	private String      nom;
	/** Hauteur (nombre de lignes) de la grille du plateau. */
	private int         hauteur;
	/** Largeur (nombre de colonnes) de la grille du plateau. */
	private int         largeur;
	/** Nombre de symboles présents sur le plateau. */
	private int         nbSymboles;

	/** Matrice bidimensionnelle contenant les instances de cellules. */
	private Cellule[][] plateau;
	/** Matrice représentant les identifiants textuels des zones géographiques. */
	private String[][]  zonesPlateau;
	/** Liste des liens (routes) reliant les cellules du plateau. */
	private ArrayList<Lien> routes;

	/*---- getters ----*/
	/**
     * Récupère le nom du plateau.
     * @return Le nom du plateau sous forme de chaîne.
     */
	public String          getNom()                   { return this.nom;               }
	/**
     * Récupère la hauteur du plateau.
     * @return Le nombre de lignes de la grille.
     */
	public int             getHauteur()               { return this.hauteur;           }
	/**
     * Récupère la largeur du plateau.
     * @return Le nombre de colonnes de la grille.
     */
	public int             getLargeur()               { return this.largeur;           }
	/**
     * Récupère le nombre de symboles présents sur le plateau.
     * @return Le nombre total de symboles.
     */
	public int             getNbSymboles()            { return this.nbSymboles;        }
	/**
     * Accesseur direct à la grille du plateau.
     * @return La matrice complète de {@link Cellule}.
     */
	public Cellule[][]     getPlateau()               { return this.plateau;           }
	/**
     * Récupère la matrice des zones du plateau.
     * @return Un tableau bidimensionnel représentant les zones.
     */
	public String[][]      getZonesPlateau()          { return this.zonesPlateau;      }
	/**
     * Récupère la liste de toutes les routes du plateau.
     * @return Une liste d'objets {@link Lien}.
     */
	public ArrayList<Lien> getRoutes()                { return this.routes;            }
	/**
     * Calcule le nombre de lignes à partir de la matrice du plateau.
     * @return Le nombre de lignes.
     */
	public int             getNbLigne()               { return this.plateau   .length; }
	/**
     * Calcule le nombre de colonnes à partir de la première ligne de la matrice.
     * @return Le nombre de colonnes.
     */
	public int             getNbColonne()             { return this.plateau[0].length; }
	/**
     * Récupère une cellule spécifique à partir de ses coordonnées.
     * @param x Coordonnée de la ligne.
     * @param y Coordonnée de la colonne.
     * @return La {@link Cellule} correspondante.
     */
	public Cellule         getCellule( int x, int y ) { return this.plateau[x][y];     }

	/*---------------------------------------*/
	/*            Constructeur               */
	/*---------------------------------------*/

	/**
	 * Crée une instance de Plateau initialisée avec ses dimensions et ses structures de données.
	 * @param nom          Le nom du scénario du plateau.
	 * @param hauteur      Le nombre de lignes.
	 * @param largeur      Le nombre de colonnes.
	 * @param nbSymboles   Le nombre de symboles uniques présents.
	 */
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

	/** Retourne tous les symboles couleur présents sur le plateau 
	 *  Tableau fixe répertoriant les caractères de symboles considérés comme des couleurs de chemin. */
	private static final char[] SYMBOLES_COULEURS = {'V','W','X','Y','Z'};

	/**
	 * Parcourt le plateau pour identifier et lister toutes les couleurs uniques de chemins présentes.
	 * @return Un tableau de caractères contenant les symboles de couleurs trouvés.
	 */
	public char[] getCouleurs()
	{
		ArrayList<Character> couleurs = new ArrayList<>();
		for ( int l = 0; l < hauteur; l++ )
			for ( int c = 0; c < largeur; c++ )
				if ( plateau[l][c] != null )
				{
					char sym = plateau[l][c].getSymbole();
					for ( char col : SYMBOLES_COULEURS )
						if ( sym == col && !couleurs.contains(col) )
							couleurs.add( sym );
				}

		char[] result = new char[couleurs.size()];
		for ( int i = 0; i < couleurs.size(); i++ )
			result[i] = couleurs.get(i);
		return result;
	}
	/**
	 * Retourne la cellule existante en (x, y) si elle existe,
	 * sinon en crée une nouvelle et la stocke dans le plateau.
	 * Récupère la cellule existante aux coordonnées spécifiées. 
	 * Si aucune cellule n'existe à cet emplacement, en crée une nouvelle, la stocke et la renvoie.
	 * @param x Coordonnée de la ligne.
	 * @param y Coordonnée de la colonne.
	 * @return La {@link Cellule} existante ou nouvellement créée.
	 */
	public Cellule getOuCreerCellule( int x, int y )
	{
		if ( this.plateau[x][y] == null )
			this.plateau[x][y] = new Cellule( x, y );
		return this.plateau[x][y];
	}

	/*---- setter ----*/
	/**
	 * Attribue un identifiant de zone géographique à une case précise du plateau.
	 * @param lig  L'indice de la ligne.
	 * @param col  L'indice de la colonne.
	 * @param zone La chaîne représentant l'identifiant de la zone.
	 */
	public void setZone( int lig, int col, String zone ){ this.zonesPlateau[lig][col] = zone; }

	/*---- méthodes ----*/
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

}