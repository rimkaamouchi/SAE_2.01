package jeu.src;

import jeu.src.ihm.FrameJeu;
import jeu.src.ihm.FramePioche;
import jeu.src.metier.Jeu;
import jeu.src.metier.Plateau;
import jeu.src.metier.ScanJeu;

public class ControleurJeu
{
	private Plateau          metier;
	private FramePioche      framePioche;
	private FrameJeu         frameJeu;
	private Jeu              jeu;
	private ScanJeu          lecteurJeu;

	public ControleurJeu()
	{
		//this.metier      = ScanJeu.charger("le fichier qu'on veut");
		//lecteurJeu = ScanJeu.charger("le fichier qu'on veut");       La méthode charger n'existe pas 
		ScanJeu.LecteurJeu( this );

		System.out.println( this.metier.getNom() );
		System.out.println( this.metier.getNbLigne() );
		System.out.println( this.metier.getNbColonne() );
		System.out.println( this.metier.getRoutes().size() );
		
		this.frameJeu    = new FrameJeu   ( this );
		this.framePioche = new FramePioche( this );
	}

	public int getNbLigne()  { return this.metier.getNbLigne();   } // méthode appartenant à la class Jeu
	public int getNbColonne(){ return this.metier.getNbColonne(); } // méthode appartenant à la class Jeu

	private String nom;
	private int    taillePlateauX;
	private int    taillePlateauY;
	private int    nbSymboles;

	public void setParametres(String nom, int tailleX, int tailleY, int nbSymboles )
	{
		this.nom            = nom;
		this.taillePlateauX = tailleX;
		this.taillePlateauY = tailleY;
		this.nbSymboles     = nbSymboles;

		this.metier = new Plateau( nom, tailleX, tailleY, nbSymboles );
	}

	//getters
	public String getNomPlateau()     { return this.nom;}
	public int    getTaillePlateauX() { return this.taillePlateauX; }
	public int    getTaillePlateauY() { return this.taillePlateauY; }
	public int    getNbSymboles()     { return this.nbSymboles;     }

	public Plateau getPlateau()    { return this.metier;}

	public static void main(String[] args)
	{
		new ControleurJeu();
	}
}