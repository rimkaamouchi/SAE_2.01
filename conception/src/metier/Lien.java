package conception.src.metier;

public class Lien
{
	private Cellule depart;
	private Cellule arrivee;
	private char direction;

	public Lien( Cellule depart, Cellule arrivee, char direction ) 
	{
		this.depart    = depart;
		this.arrivee   = arrivee;
		this.direction = direction;
	}

	public Cellule getDepart()
	{ 
		return depart; 
	}
	public Cellule getArrivee()
	{ 
		return arrivee; 
	}
	public char    getDirection()
	{ 
		return direction;
	}
}
