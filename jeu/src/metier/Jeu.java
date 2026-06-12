package jeu.src.metier;

import java.util.ArrayList;
//import java.util.Collections;

public class Jeu
{
	public static final char JOKER = '*';
	public static final int  NB_CARTES_PAR_COULEUR = 5;

	private Plateau        plateau;       // plateau chargé depuis le .data
	private ArrayList<Character> pioche; // cartes restantes
	private ArrayList<Character> defausse;

	// Chemins en cours : une liste de cellules parcourues par couleur
	// La clé est le symbole couleur (ex: 'A', 'B', ...)
	private ArrayList<Chemin> chemins;

	private int     tourActuel;    // 0 à NB_CARTES_PAR_COULEUR - 1
	private int     couleurActuelle; // indice de la couleur en cours de tracé
	private char[]  couleurs;      // liste des couleurs/symboles du plateau
	private boolean partieFinie;

	public Jeu( Plateau plateau )
	{
		this.plateau         = plateau;
		this.defausse        = new ArrayList<>();
		this.chemins         = new ArrayList<>();
		this.partieFinie     = false;
		this.tourActuel      = 0;
		this.couleurActuelle = 0;

		this.couleurs = plateau.getCouleurs(); // les symboles couleur placés sur le plateau

		this.initialiserChemins();
		this.initialiserPioche();
	}


	/** Crée un chemin vide pour chaque couleur, démarrant au sommet correspondant */
	private void initialiserChemins()
	{
		for ( char coul : this.couleurs )
		{
			Cellule sommet = this.plateau.getCelluleCouleur( coul );
			if ( sommet != null )
				this.chemins.add( new Chemin( coul, sommet ) );
		}
	}

	private void initialiserPioche()
	{
		//voir avec la classe pioche
	}

	/** Tire la prochaine carte de la pioche */
	public char tirerCarte()
	{
		//voir avec la classe pioche
		return ' ';
	}

	/**
	 * Tente d'avancer le chemin de la couleur donnée vers le sommet cible.
	 * Le sommet cible doit être directement relié (par un lien) à l'extrémité
	 * actuelle du chemin.
	 * Retourne true si le déplacement est valide et effectué.
	 */
	public boolean avancerChemin( char couleur, Cellule cible )
	{
		Chemin chemin = getCheminPourCouleur( couleur );
		if ( chemin == null || chemin.estTermine() ) return false;

		Cellule extremite = chemin.getExtremite();

		// Vérifie qu'un lien direct existe entre l'extrémité et la cible
		if ( !this.plateau.lienExiste( extremite, cible ) ) return false;

		// Vérifie que la cible n'est pas déjà dans ce chemin
		if ( chemin.contient( cible ) ) return false;

		chemin.ajouterEtape( cible );
		return true;
	}

	/** Le joueur passe son tour pour la couleur actuelle */
	public void passerTour()
	{
		// On avance simplement sans déplacer
		verifierFinTour();
	}

	/** Appelée après chaque action pour vérifier si le tour/la partie est finie */
	private void verifierFinTour()
	{
		this.tourActuel++;
		if ( this.tourActuel >= NB_CARTES_PAR_COULEUR )
		{
			this.tourActuel = 0;
			this.couleurActuelle++;
			if ( this.couleurActuelle >= this.couleurs.length )
				this.partieFinie = true;
		}
	}

	/** Calcule le score : nombre total de cases atteintes par tous les chemins */
	public int calculerScore()
	{
		int score = 0;
		
		return score;
	}

	/*---------------------------------------*/
	/*            Accesseurs                 */
	/*---------------------------------------*/

	public boolean              isPartieFinie()     { return this.partieFinie;                    }
	public int                  getTourActuel()     { return this.tourActuel;                     }
	public char                 getCouleurActuelle(){ return this.couleurs[this.couleurActuelle]; }
	public ArrayList<Chemin>    getChemins()        { return this.chemins;                        }
	//public ArrayList<Character> getPioche()       { return this.pioche;                         }
	public Plateau              getPlateau()        { return this.plateau;                        }

	public Chemin getCheminPourCouleur( char couleur )
	{
		for ( Chemin c : this.chemins )
			if ( c.getCouleur() == couleur ) return c;
		return null;
	}
}