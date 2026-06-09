package conception.src.metier;

import java.util.ArrayList;

public class Plateau
{
	private int         hauteur;
	private int         largeur;
	private int         nbCoul = 4;
	private int         nbSymboles;
	private int         tailleCase;

	private char        val;
	private char[]      symboles;
	private String[][]  zonesPlateau;
	private Cellule[][] plateau;

	public Plateau()
	{
		this.hauteur      = 7;
		this.largeur      = 7;
		this.nbCoul       = 4;
		this.nbSymboles   = 4;
		this.tailleCase   = 50;

		this.symboles     = new char[4];

		this.plateau      = new Cellule[hauteur][largeur];
		this.zonesPlateau = new String[this.hauteur][this.largeur];
	}

	public Plateau(int hauteur,int largeur,int nbSymboles)
	{
		this.hauteur      = hauteur;
		this.largeur      = largeur;
		this.nbSymboles   = nbSymboles;
		this.tailleCase   = 50;

		this.symboles     = new char[4];

		this.plateau      = new Cellule[hauteur][largeur];
		this.zonesPlateau = new String [this.hauteur][this.largeur];
	}

	public void placerCellule(int lig, int col, char symbole) 
	{
		Cellule c = new Cellule(lig, col, symbole);
		
		// Lire zonesPlateau et l'affecter à la cellule
		if (this.zonesPlateau[lig][col] != null)
			c.setZone(this.zonesPlateau[lig][col].charAt(0));
		
		this.plateau[lig][col] = c;
	}

	public void setZone(int lig, int col, String zone) 
	{
		this.zonesPlateau[lig][col] = zone;
		
		// Mettre à jour la cellule si elle existe déjà
		if (this.plateau[lig][col] != null && zone != null)
			this.plateau[lig][col].setZone(zone.charAt(0));
	}

	public void supprimerCellule(int lig, int col) 
	{
		this.plateau[lig][col] = null;
	}

	public void supprimerZone(int lig, int col) 
	{
		this.zonesPlateau[lig][col] = null;
	}

	/** Place les cellules de départ sur le plateau pour tester */
	/*public boolean initPlat()
	{
		Cellule cel = new Cellule(3,4,'X');
		this.plateau[3][4] = cel;
		Cellule cel2 = new Cellule(5,0,'Y');
		this.plateau[5][0] = cel2;
		Cellule cel3 = new Cellule(0,1,'E');
		this.plateau[0][1] = cel3;
		Cellule cel4 = new Cellule(3,1,'5');
		this.plateau[3][1] = cel4;
		Cellule cel5 = new Cellule(5,3,'8');
		this.plateau[5][3] = cel5;
		return true;
	}
	*/

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
	private static final char[] DIRECTIONS = {'e', 'd', 's', 'q'};

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

	private ArrayList<Lien> routes = new ArrayList<>();

	/** Affiche le plateau en console avec le symbole de chaque cellule. */
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

	/** Vérifie si une cellule est reliée à au moins un lien (départ ou arrivée). */
	public boolean existeLien(Cellule cel) 
	{
		for ( Lien l : routes ) 
		{
			if ( l.getDepart() == cel || l.getArrivee() == cel ) return true;
		}
		return false;
	}

	/** Retourne tous les liens connectés à une cellule donnée. */
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

	/** Retourne vrai si toutes les cellules du plateau ont une zone attribuée. */
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
	 * Vérifie que chaque zone utilisée dans zonesPlateau forme un bloc continu
	 * (pas de morceaux séparés). Utilise un parcours récursif.
	 */
	public boolean validerToutesLesZones()
	{
		String[] toutesZones = { "A", "B", "C", "D" };

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


	// Accesseurs
	public String[][]      getZonesPlateau() { return this.zonesPlateau;      }
	public int             getNbLigne()      { return this.plateau   .length; }
	public int             getNbColonne()    { return this.plateau[0].length; }
	public Cellule[][]     getPlateau()      { return this.plateau;           }
	public ArrayList<Lien> getRoutes()       { return this.routes;            }
}