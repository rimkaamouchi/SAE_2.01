package conception.src.metier;

import java.util.ArrayList;
/**
 * Représente le plateau de jeu composé de cellules, de zones géographiques
 * et de routes reliant ces cellules.
 * <p>
 * Le plateau gère la logique de positionnement des cellules, la validation 
 * de la connectivité au sein des zones ainsi que le calcul des chemins (routes).
 * </p>
 * 
 * @author Groupe 11: Quentin Deshayes, Roxane Sidolle, Manon Rigoult, Rim Kaamouchi et Mykhailo Liapin
 * @version 1.0
 */
public class Plateau
{
	/** Hauteur (nombre de lignes) du plateau. */
	private int         hauteur;
	/** Largeur (nombre de colonnes) du plateau. */
	private int         largeur;
	/** Nombre de couleurs utilisées dans le jeu. */
	private int         nbCoul;
	/** Nombre de symboles distincts sur le plateau. */
	private int         nbSymboles;
	/** Taille graphique en pixels d'une case. */
	private int         tailleCase;

	/** Valeur de caractère temporaire ou utilitaire. */
	private char        val;
	/** Tableau répertoriant les symboles disponibles. */
	private char[]      symboles;
	/** Matrice stockant l'identifiant de la zone (ex: "A", "B") pour chaque coordonnée. */
	private String[][]  zonesPlateau;
	/** Matrice représentant la grille des cellules réelles posées sur le plateau. */
	private Cellule[][] plateau;
	
	/** Liste de toutes les routes (liens) calculées entre les cellules du plateau. */
	private ArrayList<Lien> routes;

	/**
	 * Cherche la première cellule voisine dans la direction donnée (e/d/s/q)
	 * et retourne le lien correspondant avec les cases intermédiaires, ou null si aucune.
	 */
		/** Deltas (dx=ligne, dy=col) pour e, d, s, q */
	private static final int[][] DELTAS = 	{
												{0, 1},   // e → droite
												{1, 1},   // d → diagonale bas-droite
												{1, 0},   // s → bas
												{1, -1}   // q → diagonale bas-gauche
											};
	/** Liste des caractères représentant les directions de recherche de liens. */
	private static final char[] DIRECTIONS = {'e', 'd', 's', 'q'};
	
	//getter
	/**
     * Accesseur pour obtenir la matrice des zones du plateau.
     * @return Un tableau bidimensionnel de chaînes représentant les zones.
     */
	public String[][]      getZonesPlateau() { return this.zonesPlateau;      }

	/**
     * Obtient le nombre total de lignes du plateau de cellules.
     * @return La hauteur de la grille.
     */
	public int             getNbLigne()      { return this.plateau   .length; }

	/**
     * Obtient le nombre total de colonnes du plateau de cellules.
     * @return La largeur de la grille.
     */
	public int             getNbColonne()    { return this.plateau[0].length; }

	/**
     * Accesseur pour obtenir la grille contenant l'ensemble des cellules du plateau.
     * @return La matrice de {@link Cellule}.
     */
	public Cellule[][]     getPlateau()      { return this.plateau;           }

	/**
     * Récupère la liste de l'ensemble des routes calculées sur le plateau.
     * @return Une {@link ArrayList} de {@link Lien}.
     */
	public ArrayList<Lien> getRoutes()       { return this.routes;            }
	
	/** 
     * Récupère la liste de tous les liens (routes) connectés à une cellule spécifique.
     * 
     * @param cel La cellule cible.
     * @return Une {@link ArrayList} contenant tous les liens connectés à cette cellule.
	 * Retourne tous les liens connectés à une cellule donnée. */
	public ArrayList<Lien> getLiens(Cellule cel) 
	{
		ArrayList<Lien> result = new ArrayList<>();
		for ( Lien l : routes ) 
		{
			if ( l.getDepart() == cel || l.getArrivee() == cel ) result.add(l);
		}
		return result;
	}

	/**
     * Récupère la cellule présente aux coordonnées demandées. Si aucune cellule 
     * n'existe à cet endroit, elle est instanciée, stockée sur le plateau puis retournée.
     * 
     * @param x L'indice de la ligne (coordonnée X).
     * @param y L'indice de la colonne (coordonnée Y).
     * @return La {@link Cellule} existante ou celle fraîchement créée.
	 * Retourne la cellule existante en (x, y) si elle existe,
	 * sinon en crée une nouvelle et la stocke dans le plateau.
	 */
	public Cellule getOuCreerCellule( int x, int y )
	{
		if ( this.plateau[x][y] == null )
			this.plateau[x][y] = new Cellule( x, y );
		return this.plateau[x][y];
	}

	//setter
	/**
     * Associe un identifiant de zone à une coordonnée précise du plateau.
     * Si une cellule est déjà présente à cet emplacement, sa zone est mise à jour.
     * 
     * @param lig  L'indice de la ligne.
     * @param col  L'indice de la colonne.
     * @param zone Le nom ou identifiant de la zone sous forme de chaîne.
     */
	public void setZone(int lig, int col, String zone) 
	{
		this.zonesPlateau[lig][col] = zone;
		
		// Mettre à jour la cellule si elle existe déjà
		if (this.plateau[lig][col] != null && zone != null)
			this.plateau[lig][col].setZone(zone.charAt(0));
	}

	/**
     * Constructeur par défaut.
     * Initialise un plateau standard de dimension 7x7 avec 4 symboles.
     */
	public Plateau()
	{
		this.hauteur      = 7;
		this.largeur      = 7;
		this.nbSymboles   = 4;
		this.tailleCase   = 50;

		this.symboles     = new char[4];

		this.routes = new ArrayList<>();
		this.plateau      = new Cellule[hauteur][largeur];
		this.zonesPlateau = new String[this.hauteur][this.largeur];
	}

	/**
     * Constructeur personnalisé.
     * Permet de spécifier les dimensions du plateau et le nombre de symboles.
     * 
     * @param hauteur    Le nombre de lignes du plateau.
     * @param largeur    Le nombre de colonnes du plateau.
     * @param nbSymboles Le nombre de symboles différents à utiliser.
     */
	public Plateau(int hauteur,int largeur,int nbSymboles)
	{
		this.hauteur      = hauteur;
		this.largeur      = largeur;
		this.nbSymboles   = nbSymboles;
		this.tailleCase   = 50;

		this.symboles     = new char[4];

		this.routes = new ArrayList<>();
		this.plateau      = new Cellule[hauteur][largeur];
		this.zonesPlateau = new String [this.hauteur][this.largeur];
	}

	/**
     * Place une nouvelle cellule sur le plateau à des coordonnées spécifiques.
     * Si une zone est déjà définie à cet emplacement, elle est automatiquement associée à la cellule.
     * 
     * @param lig     L'indice de la ligne.
     * @param col     L'indice de la colonne.
     * @param symbole Le symbole de la cellule à créer.
     */
	public void placerCellule(int lig, int col, char symbole) 
	{
		Cellule c = new Cellule(lig, col, symbole);
		
		// Lire zonesPlateau et l'affecter à la cellule
		if (this.zonesPlateau[lig][col] != null)
			c.setZone(this.zonesPlateau[lig][col].charAt(0));
		
		this.plateau[lig][col] = c;
	}

	/**
     * Supprime la cellule située aux coordonnées spécifiées.
     * 
     * @param lig L'indice de la ligne.
     * @param col L'indice de la colonne.
     */
	public void supprimerCellule(int lig, int col) { this.plateau[lig][col] = null; }

	/**
     * Supprime la zone définie aux coordonnées spécifiées.
     * 
     * @param lig L'indice de la ligne.
     * @param col L'indice de la colonne.
     */
	public void supprimerZone(int lig, int col) { this.zonesPlateau[lig][col] = null; }

	/**
     * Cherche en ligne droite ou diagonale la première cellule voisine existante 
     * dans la direction spécifiée, en enregistrant les cases vides intermédiaires.
     * 
     * @param dirIndex L'indice de la direction dans les tableaux {@link #DELTAS} et {@link #DIRECTIONS}.
     * @param depart   La cellule de départ de la recherche.
     * @return Un objet {@link Lien} contenant le chemin tracé si une cellule voisine est trouvée, 
     *         ou {@code null} si le bord du plateau est atteint sans rencontre.
     */
	private Lien verifLien(int dirIndex, Cellule depart) 
	{
		int dx = DELTAS[dirIndex][0];
		int dy = DELTAS[dirIndex][1];
		char choix = DIRECTIONS[dirIndex];

		int lig = depart.getX() + dx;
		int col = depart.getY() + dy;
		ArrayList<int[]> chemin = new ArrayList<>();

		while (lig >= 0 && lig < this.hauteur && col >= 0 && col < this.largeur) 
		{
			if (plateau[lig][col] != null) 
			{
				Lien l = new Lien(depart, plateau[lig][col], choix);
				for (int[] c : chemin) l.ajouterCase(c[0], c[1]);
				return l;
			}
			chemin.add(new int[]{lig, col});
			lig += dx;
			col += dy;
		}
		return null;
	}

	/**
     * Parcourt l'ensemble du plateau pour recalculer et lister toutes les routes 
     * valides reliant les cellules entre elles. Les anciennes routes sont effacées.
     */
	public void calculerRoutes() {
		routes.clear();
		for (int lig = 0; lig < this.hauteur; lig++) 
		{
			for (int col = 0; col < this.largeur; col++) 
			{
				if (plateau[lig][col] == null) continue;
				Cellule depart = plateau[lig][col];
				for (int i = 0; i < DIRECTIONS.length; i++) 
				{
					Lien l = verifLien(i, depart);
					if (l != null) routes.add(l);
				}
			}
		}
	}

	
	/** 
     * Affiche une représentation textuelle du plateau dans la console.
     * Les cases contenant une cellule affichent leur symbole, les cases vides affichent "[ ]".
     * 
     * @param plateau La matrice de cellules à afficher.
     * Affiche le plateau en console avec le symbole de chaque cellule. */
	public static void afficher( Cellule[][] plateau ) 
	{
		for ( int lig = 0; lig < plateau.length; lig++ ) 
		{
			for ( int col = 0; col < plateau[0].length; col++ ) 
			{
				if ( plateau[lig][col] != null ) 
				{
					System.out.print( "[" + plateau[lig][col].getSymbole() + "]" );
				} 
				else 
				{
					System.out.print( "[ ]" );
				}
			}
			System.out.println();
		}
	}

	/** 
     * Vérifie si une cellule donnée est impliquée dans au moins une route du plateau.
     * 
     * @param cel La cellule à tester.
     * @return {@code true} si la cellule est un point de départ ou d'arrivée d'un lien, {@code false} sinon.
	 * Vérifie si une cellule est reliée à au moins un lien (départ ou arrivée). */
	public boolean existeLien(Cellule cel) 
	{
		for ( Lien l : routes ) 
		{
			if ( l.getDepart() == cel || l.getArrivee() == cel ) return true;
		}
		return false;
	}

	/**
     * Vérifie la validité d'une zone en s'assurant que toutes ses cellules sont interconnectées.
     * Règles de validation :
     * <ul>
     *   <li>Une zone vide est invalide (retourne false).</li>
     *   <li>Une zone contenant une seule cellule est toujours valide (retourne true).</li>
     *   <li>Pour plus d'une cellule, chaque cellule de la zone doit être liée à une autre cellule de la même zone.</li>
     * </ul>
     * 
     * @param zone Le caractère identifiant la zone à vérifier.
     * @return {@code true} si la zone respecte les règles d'interconnexion, {@code false} sinon.
	 * Vérifie que toutes les cellules d'une zone sont bien connectées entre elles.
	 * Une zone isolée à une seule cellule est toujours valide.
	 */
	public boolean verifZone(char zone) 
	{
		ArrayList<Cellule> cellZone = new ArrayList<>();
		for ( int lig = 0; lig < this.hauteur; lig++ ) 
		{
			for ( int col = 0; col < this.largeur; col++  ) 
			{
				if ( plateau[lig][col] != null && plateau[lig][col].getZone() == zone ) 
				{
					cellZone.add( plateau[lig][col] );
				}
			}
		}

		if ( cellZone.isEmpty() )
			return false;

		if ( cellZone.size() == 1 )
			return true;

		for ( Cellule cel : cellZone ) 
		{
			boolean liee = false;

			for ( Lien l : routes ) 
			{
				if ( l.getDepart() == cel && l.getArrivee().getZone() == zone ) 
				{
					liee = true;
					break;
				}
				if ( l.getArrivee() == cel && l.getDepart().getZone() == zone ) 
				{
					liee = true;
					break;
				}
			}
			if ( !liee )
				return false; 
		}
		return true;
	}

	/** 
     * Vérifie si toutes les cellules posées sur le plateau possèdent une zone attribuée.
     * 
     * @return {@code true} si aucune cellule n'a de zone par défaut ('\u0000'), {@code false} sinon.
	 * Retourne vrai si toutes les cellules du plateau ont une zone attribuée. */
	public boolean plateauEstComplet()
	{
		for ( int lig = 0; lig < this.hauteur; lig++ )
		{
			for ( int col = 0; col < this.largeur; col++ )
			{
				if ( this.plateau[lig][col] != null )
				{
					if ( this.plateau[lig][col].getZone() == '\u0000' )
					{
						return false;
					}
				}
			}
		}
		return true;
	}

	
	/**
     * Valide la cohérence morphologique des zones du plateau.
     * Vérifie que chaque zone ("A", "B", "C", "D") forme un bloc d'un seul tenant (continu).
     * Si une zone est scindée en morceaux distants, la validation échoue.
     * 
     * @return {@code true} si toutes les zones sont d'un seul bloc, {@code false} si une zone est fragmentée.
	 * Vérifie que chaque zone utilisée dans zonesPlateau forme un bloc continu
	 * (pas de morceaux séparés). Utilise un parcours récursif.
	 */
	public boolean validerToutesLesZones()
	{
		String[] toutesZones = { "A", "B", "C", "D", "E", "F", "G", "H" };

		for ( String z : toutesZones )
		{
			int totalCasesZone = 0;
			int ligDepart      = -1;
			int colDepart      = -1;

			for ( int l = 0; l < hauteur; l++ )
			{
				for ( int c = 0; c < largeur; c++ )
				{
					if ( z.equals(zonesPlateau[l][c]) )
					{
						totalCasesZone++;
						if ( ligDepart == -1 )
						{
							ligDepart = l;
							colDepart = c;
						}
					}
				}
			}

			if ( totalCasesZone <= 1 )
				continue;

			boolean[][] visite = new boolean[hauteur][largeur];
			int casesAtteintes = parcourirZone( ligDepart, colDepart, z, visite );

			if ( casesAtteintes != totalCasesZone )
			{
				System.out.println( "Erreur : La zone " + z + " est coupée en plusieurs morceaux !" );
				return false;
			}
		}
		return true;
	}

	/**
     * Méthode utilitaire récursive effectuant un parcours en profondeur (Flood Fill) 
     * pour compter les cases contiguës appartenant à une même zone.
     * 
     * @param l      Ligne actuelle de l'exploration.
     * @param c      Colonne actuelle de l'exploration.
     * @param z      Identifiant de la zone ciblée.
     * @param visite Matrice de suivi des cases déjà comptabilisées.
     * @return Le nombre de cases connectées trouvées depuis le point de départ.
     */
	/**
	 * Parcours récursif en 4 pour compter les cases d'une zone atteignables
	 * depuis (l, c). Retourne le nombre de cases visitées.
	 */
	private int parcourirZone( int l, int c, String z, boolean[][] visite )
	{
		if ( l < 0 || l >= hauteur || c < 0 || c >= largeur ) return 0;
		if ( visite[l][c] || !z.equals(zonesPlateau[l][c])  ) return 0;

		visite[l][c] = true;

		int somme = 1;
		somme += parcourirZone( l + 1, c, z, visite );
		somme += parcourirZone( l - 1, c, z, visite );
		somme += parcourirZone( l, c + 1, z, visite );
		somme += parcourirZone( l, c - 1, z, visite );

		return somme;
	}

	/**
     * Vérifie si l'intégralité de la grille du plateau possède une zone définie 
     * (aucune case à null dans la matrice des zones).
     * 
     * @return {@code true} si toutes les cases possèdent une zone, {@code false} sinon.
     */
	public boolean toutesLesCasesOntUneZone()
	{
		for ( int lig = 0; lig < this.hauteur; lig++ )
			for ( int col = 0; col < this.largeur; col++ )
				if ( this.zonesPlateau[lig][col] == null )
					return false;
		return true;
	}
}