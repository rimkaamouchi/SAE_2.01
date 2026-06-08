package conception.src.metier;

import java.util.ArrayList;
import java.util.List;

public class Jeu
{
	private int         hauteur;
	private int         largeur;
	private int         nbCoul = 4;
	private int         nbSymboles;
	private int         tailleCase;

	private char        val;
	private char[]      symboles;

	private Cellule[][] plateau;

	private List<Cellule> lstSymbole;

	public Jeu()
	{
		/*Création du plateau */
		this.hauteur    = 7;
		this.largeur    = 7;
		this.nbCoul     = 4;
		this.nbSymboles = 4;
		this.tailleCase = 50;

		this.plateau = new Cellule[hauteur][largeur];

		this.symboles   = new char[4];

		this.lstSymbole = new ArrayList<Cellule>();
	}

	public boolean setPlateau(int hauteur, int largeur)
	{
		this.plateau = new Cellule[hauteur][largeur];
		return true;
	}

	public boolean initPlat()
	{
		Cellule cel        =  new Cellule(3,5,'X');
		this.plateau[3][5] =  cel;
		Cellule cel2       =  new Cellule(5,5,'Y');
		this.plateau[5][5] =  cel2;
		return true;
	}

    private Cellule verifLien(char choix, Cellule cel1) 
	{
		int posX = cel1.getX();
		int posY = cel1.getY();

		switch (choix) {
			case 'e': // droite
				for (int col = posY + 1; col < this.largeur; col++) 
				{
					if (plateau[posX][col] != null) return plateau[posX][col];
				}
				return null;

			case 'd': // diagonale bas-droite
				for (int lig = posX + 1, col = posY + 1; lig < this.hauteur && col < this.largeur; lig++, col++) 
				{
					if (plateau[lig][col] != null) return plateau[lig][col];
				}
				return null;

			case 's': // bas
				for (int lig = posX + 1; lig < this.hauteur; lig++) 
				{
					if (plateau[lig][posY] != null) return plateau[lig][posY];
				}
				return null;

			case 'q': // diagonale bas-gauche
				for (int lig = posX + 1, col = posY - 1; lig < this.hauteur && col >= 0; lig++, col--) 
				{
					if (plateau[lig][col] != null) return plateau[lig][col];
				}
				return null;

			default:
				System.out.println("caractère non indexé");
				return null;
    	}

	}
	
	private ArrayList<Lien> routes = new ArrayList<>();

	public void calculerRoutes() 
	{
		char[] directions = {'e', 'd', 's', 'q'};

		for (int lig = 0; lig < this.hauteur; lig++) 
		{
			for (int col = 0; col < this.largeur; col++) 
			{
				if (plateau[lig][col] == null) continue;

				Cellule depart = plateau[lig][col];

				for (char dir : directions) 
				{
					Cellule arrivee = verifLien(dir, depart);
					if (arrivee != null) 
					{
						routes.add(new Lien(depart, arrivee, dir));
					}
				}
			}
		}
	}

	public static void afficher(Cellule[][] plateau) 
	{
		for (int lig = 0; lig < plateau.length; lig++) 
		{
			for (int col = 0; col < plateau[0].length; col++) 
			{
				if (plateau[lig][col] != null) 
				{
					System.out.print("[" + plateau[lig][col].getSymbole() + "]");
				} 
				else 
				{
					System.out.print("[ ]");
				}
			}
			System.out.println();
		}
	}
	
	public boolean existeLien(Cellule cel) 
	{
		for (Lien l : routes) 
		{
			if (l.getDepart() == cel || l.getArrivee() == cel) return true;
		}
		return false;
	}

	public ArrayList<Lien> getLiens(Cellule cel) 
	{
		ArrayList<Lien> result = new ArrayList<>();
		for (Lien l : routes) 
		{
			if (l.getDepart() == cel || l.getArrivee() == cel) result.add(l);
		}
		return result;
	}

	/*public Integer getIndiceSymbole( int x, int y )
	{
		for ( int cpt = 0; cpt < this.lstSymbole.size(); cpt++ )
			if ( this.lstSymbole.get( cpt ).possede( x, y ) )
				return cpt;
		return null;
	}
	
	public void deplacerSymbole( Integer numSymbole, int x, int y )
	{
		if ( numSymbole != null && numSymbole >= 0 && numSymbole < this.lstSymbole.size() )
		{
			this.lstSymbole.get( numSymbole ).deplacerX(x);
			this.lstSymbole.get( numSymbole ).deplacerY(y);
		}
	}*/	

	public int             getNbLigne()     { return this.plateau   .length ; }
	public int             getNbColonne()   { return this.plateau[0].length ; }
	public Cellule[][]     getPlateau()     { return this.plateau;            }
	public ArrayList<Lien> getRoutes()      { return this.routes;             }
}