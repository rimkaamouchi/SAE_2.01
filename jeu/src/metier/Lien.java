package jeu.src.metier;

import java.util.ArrayList;

public class Lien
{
	private Cellule depart;
	private Cellule arrivee;
	private char    direction;

	/*---- getters ----*/
	public Cellule          getDepart()   { return depart;    }
	public Cellule          getArrivee()  { return arrivee;   }
	public char             getDirection(){ return direction; }
	public ArrayList<int[]> getChemin()   { return chemin;    }

	public Lien( Cellule depart, Cellule arrivee, char direction ) 
	{
		this.depart    = depart;
		this.arrivee   = arrivee;
		this.direction = direction;
	}
	
	private ArrayList<int[]> chemin = new ArrayList<>();

	public void ajouterCase( int x, int y ) 
	{
		chemin.add( new int[] { x, y } );
	}
}
