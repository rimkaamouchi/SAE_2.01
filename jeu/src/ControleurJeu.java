package jeu.src;

import conception.src.metier.Plateau;
import jeu.src.ihm.FramePioche;
import jeu.src.metier.Jeu;

public class ControleurJeu
{
	private Plateau          metier;
	private FramePioche      framePioche;
	private Jeu              jeu;

	public ControleurJeu()
	{
		//this.metier      = ScanJeu.charger("le fichier qu'on veut");
		
		this.framePioche = new FramePioche( this );
	}

	public int getNbLigne()  { return this.metier.getNbLigne();   } // méthode appartenant à la class Jeu
	public int getNbColonne(){ return this.metier.getNbColonne(); } // méthode appartenant à la class Jeu

	private String txtNomPlateau;
	private int    taillePlateauX;
	private int    taillePlateauY;
	private int    nbSymboles;

	public void setParametres( int tailleX, int tailleY, int nbSymboles )
	{
		this.taillePlateauX = tailleX;
		this.taillePlateauY = tailleY;
		this.nbSymboles     = nbSymboles;

		this.metier = new Plateau( tailleX, tailleY, nbSymboles );
	}

	public int getTaillePlateauX() { return this.taillePlateauX; }
	public int getTaillePlateauY() { return this.taillePlateauY; }
	public int getNbSymboles()     { return this.nbSymboles;     }

	public static void main(String[] args)
	{
		new ControleurJeu();
	}
}