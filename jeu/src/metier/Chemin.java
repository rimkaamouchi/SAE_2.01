package jeu.src.metier;

import java.util.ArrayList;

public class Chemin
{
	private char              couleur;
	private ArrayList<Cellule> etapes;   // liste des sommets traversés dans l'ordre
	private boolean           termine;

	/*---- getters ----*/
	public int     getNbEtapes()          { return this.etapes.size();                       }
	public char    getCouleur()           { return this.couleur;                             }
	public boolean estTermine()           { return this.termine;                             }
	public ArrayList<Cellule> getEtapes() { return this.etapes;                              }
	/** L'extrémité est toujours le dernier élément ajouté */
	public Cellule getExtremite()         { return this.etapes.get( this.etapes.size() - 1 );}

	public Chemin( char couleur, Cellule depart )
	{
		this.couleur  = couleur;
		this.etapes   = new ArrayList<>();
		this.termine  = false;

		this.etapes.add( depart );
	}

	public void ajouterEtape( Cellule c ){ this.etapes.add( c );            }
	public boolean contient ( Cellule c ){ return this.etapes.contains( c );}
	public void    terminer()            { this.termine = true;             }
}