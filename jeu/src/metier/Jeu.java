package jeu.src.metier;

import java.util.ArrayList;
import java.util.Random;

public class Jeu
{
	public static Carte[] ToutesLesCartes = new Carte[]
	{
		new Carte('N','R'),
		new Carte('N','Q'),
		new Carte('N','U'),
		new Carte('N','S'),
		new Carte('N','T'),
		new Carte('B','R'),
		new Carte('B','Q'),
		new Carte('B','U'),
		new Carte('B','S'),
		new Carte('B','T'),
		new Carte('N','*'),
		new Carte('B','*')
	};

	private static final char[] COULEURS_VERS_SYMBOLES_CLE = {'V', 'W', 'X', 'Y', 'Z'};
	private static final char[] COULEURS_VERS_SYMBOLES_VAL = {'Q', 'R', 'S', 'T', 'U'};

	private int indexPioche = 0;
	public static final char JOKER = '*';
	public static final int  NB_CARTES_PAR_COULEUR = 5;

	private Plateau        plateau;       // plateau chargé depuis le .data
	private ArrayList<Character> piocheR; // cartes restantes
	private ArrayList<Character> defausse;

	// Chemins en cours : une liste de cellules parcourues par couleur
	// La clé est le symbole couleur (ex: 'A', 'B', ...)
	private ArrayList<Chemin> chemins;

	private int     tourActuel;      // 0 à NB_CARTES_PAR_COULEUR - 1
	private int     couleurActuelle; // indice de la couleur en cours de tracé
	private char[]  couleurs;        // liste des couleurs/symboles du plateau
	private Carte[] pioche;
	private boolean partieFinie;
	private boolean aJoue = false; // a-t-on joué ce tour de carte ?

	/*---------------------------------------*/
	/*            Getters                    */
	/*---------------------------------------*/
	public int                  getTourActuel()        { return this.tourActuel;                                       }
	public char                 getCouleurActuelle()   { return this.couleurs[this.couleurActuelle];                   }
	public ArrayList<Chemin>    getChemins()           { return this.chemins;                                          }
	//public ArrayList<Character> getPioche()          { return this.pioche;                                           }
	public Plateau              getPlateau()           { return this.plateau;                                          }
	public int                  getNbCartesRestantes() { return Math.max( 0, this.pioche.length - this.indexPioche );}
	public char                 getSymbolePourCouleur( char couleur )
	{
		for ( int i = 0; i < COULEURS_VERS_SYMBOLES_CLE.length; i++ )
			if ( COULEURS_VERS_SYMBOLES_CLE[i] == couleur )
				return COULEURS_VERS_SYMBOLES_VAL[i];
		return ' ';
	}

	public Carte getCarteActuelle()
	{
		if ( this.indexPioche == 0 ) return null;
		return this.pioche[this.indexPioche - 1];
	}

	public Chemin getCheminPourCouleur( char couleur )
	{
		for ( Chemin c : this.chemins )
			if ( c.getCouleur() == couleur ) return c;
		return null;
	}

	/*---------------------------------------*/
	/*            Constructeur               */
	/*---------------------------------------*/
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

	/*---------------------------------------*/
	/*            Méthodes                   */
	/*---------------------------------------*/
	
	public boolean deplacerExtremite( Cellule extremite, Cellule cible, char symboleCarte )
	{
		char couleur = getCouleurActuelle();
		Chemin chemin = getCheminPourCouleur( couleur );
		if ( chemin == null ) return false;

		ArrayList<Cellule> etapes = chemin.getEtapes();
		Cellule debut = etapes.get( 0 );
		Cellule fin   = chemin.getExtremite();
		if ( extremite != debut && extremite != fin ) return false;

		// Vérifier lien direct
		if ( !this.plateau.lienExiste( extremite, cible ) ) return false;

		// Vérifier symbole (joker ou symbole correspondant)
		// Pour les sommets de couleur, on vérifie le symbole équivalent
		char symboleReel = cible.getSymbole();
		if ( estSommetCouleur( cible ) )
			symboleReel = getSymbolePourCouleur( cible.getSymbole() );

		if ( symboleCarte != JOKER && symboleReel != symboleCarte ) return false;

		// Vérifier que le lien n'est pas déjà utilisé par n'importe quel chemin
		if ( lienDejaUtilise( extremite, cible ) ) return false;

		// Si on étend depuis le début, on insère en tête
		if ( extremite == debut )
			etapes.add( 0, cible );
		else
			chemin.ajouterEtape( cible );

		return true;
	}

	private boolean estSommetCouleur( Cellule c )
	{
		for ( char couleur : COULEURS_VERS_SYMBOLES_CLE )
			if ( c.getSymbole() == couleur ) return true;
		return false;
	}

	private boolean lienDejaUtilise( Cellule a, Cellule b )
	{
		for ( Chemin chemin : this.chemins )
		{
			ArrayList<Cellule> etapes = chemin.getEtapes();
			for ( int i = 0; i < etapes.size() - 1; i++ )
			{
				Cellule c1 = etapes.get(i);
				Cellule c2 = etapes.get(i + 1);

				// Lien identique ou inverse
				if ( (c1 == a && c2 == b) || (c1 == b && c2 == a) )
					return true;

				// Croisement en diagonale
				// Deux liens (a,b) et (c1,c2) se croisent si :
				// a=(r1,col1) b=(r2,col2) et c1=(r1,col2) c2=(r2,col1)
				// ou c1=(r2,col1) c2=(r1,col2)
				if ( a.getX() == c1.getX() && b.getX() == c2.getX()
				&& a.getY() == c2.getY() && b.getY() == c1.getY() )
					return true;

				if ( a.getX() == c2.getX() && b.getX() == c1.getX()
				&& a.getY() == c1.getY() && b.getY() == c2.getY() )
					return true;
			}
		}
		return false;
	}

	public void passerAuTourSuivant()
	{
		// Remettre la pioche à zéro
		this.pioche      = Melanger( ToutesLesCartes );
		this.indexPioche = 0;
		this.aJoue       = false;
		
		// Passer à la couleur suivante
		this.couleurActuelle++;
		if ( this.couleurActuelle >= this.couleurs.length )
			this.partieFinie = true;
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

	public static Carte[] Melanger(Carte[] cartes) 
	{
		Carte[] melange = cartes.clone();
		Random rand = new Random();
		
		for (int i = melange.length - 1; i > 0; i--) 
		{
			int j = rand.nextInt(i + 1);
			Carte temp = melange[i];
			melange[i] = melange[j];
			melange[j] = temp;
		}
		
		return melange;
	}

	/** Tire la prochaine carte de la pioche */
	public Carte tirerCarte()
	{
		if ( this.indexPioche >= this.pioche.length ) return null;
		Carte c = this.pioche[this.indexPioche];
		this.indexPioche++;
		return c;
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
	
	public void reset()
    {
        // Remettre les symboles et les index à zéro
        this.partieFinie     = false;
        this.tourActuel      = 0;
        this.couleurActuelle = 0;
        this.indexPioche     = 0;
        this.aJoue           = false;

        // Vider les listes de cartes
        this.defausse.clear();

        // Réinitialiser les chemins
        this.chemins.clear();
        this.initialiserChemins(); // Re-crée les chemins de départ depuis le plateau

        // Mélanger et distribuer une new pioche
        this.pioche      = Melanger( ToutesLesCartes );
    	this.indexPioche = 0;
    }

	public boolean isPartieFinie()     { return this.partieFinie;                       }
	public boolean isPiocheVide()      { return this.indexPioche >= this.pioche.length; }
	public void    reinitialiserJoue() { this.aJoue = false;                            }
	public boolean piocheVide()        { return this.indexPioche >= this.pioche.length; }
	/** Le joueur passe son tour pour la couleur actuelle */
	public void    passerTour()        { verifierFinTour(); /*On avance simplement sans déplacer*/}
	private void   initialiserPioche() { this.pioche = Melanger(ToutesLesCartes);       }
	public boolean peutJouer()         { return !aJoue;                                 }
	public void    signalerJoue()      { aJoue = true;                                  }

}