package jeu.src;

import jeu.src.ihm.FrameJeu;
import jeu.src.ihm.PanelJeu;
import jeu.src.ihm.PanelPioche;
import jeu.src.metier.Carte;
import jeu.src.metier.Cellule;
import jeu.src.metier.Jeu;
import jeu.src.metier.Plateau;
import jeu.src.metier.ScanJeu;

public class ControleurJeu
{
	private Plateau          metier;
	private FrameJeu         frameJeu;
	private Jeu              jeu;
	private ScanJeu          lecteurJeu;
	private PanelPioche      panelPioche;
	private PanelJeu         panelJeu;

	/*---- getters ----*/
	public String   getNomPlateau()     { return this.nom;                      }
	public int      getTaillePlateauX() { return this.taillePlateauX;           }
	public int      getTaillePlateauY() { return this.taillePlateauY;           }
	public int      getNbSymboles()     { return this.nbSymboles;               }
	public Plateau  getPlateau()        { return this.metier;                   }
	public FrameJeu getFrameJeu()       { return this.frameJeu;                 }
	public Jeu      getJeu()            { return this.jeu;                      }
	public char     getCouleurActuelle(){ return this.jeu.getCouleurActuelle(); }
	public int      getNbLigne()        { return this.metier.getNbLigne();      } // méthode appartenant à la class Jeu
	public int      getNbColonne()      { return this.metier.getNbColonne();    } // méthode appartenant à la class Jeu

	//setter
	public void setParametres(String nom, int tailleX, int tailleY, int nbSymboles )
	{
		this.nom            = nom;
		this.taillePlateauX = tailleX;
		this.taillePlateauY = tailleY;
		this.nbSymboles     = nbSymboles;

		this.metier = new Plateau( nom, tailleX, tailleY, nbSymboles );
	}

	//constructeur
	public ControleurJeu()
	{
		//this.metier      = ScanJeu.charger("le fichier qu'on veut");
		//lecteurJeu = ScanJeu.charger("le fichier qu'on veut");       La méthode charger n'existe pas 
		ScanJeu.LecteurJeu( this );

		this.jeu      = new Jeu(this.metier);
		this.frameJeu = new FrameJeu( this );
	}

	private String nom;
	private int    taillePlateauX;
	private int    taillePlateauY;
	private int    nbSymboles;

	public boolean deplacerExtremite( Cellule extremite, Cellule cible )
	{
		Carte carte = this.jeu.getCarteActuelle();
		if ( carte == null ) return false;
		return this.jeu.deplacerExtremite( extremite, cible, carte.getSymbole() );
	}

	//remets à jour la partie
	public void reset()
	{
		// On réinitialise les données du modèle
		this.jeu.reset(); 
		// On rafraîchit l'interface graphique
		this.frameJeu.resetGraphique(); 
	}

	// Ajouter les méthodes :
	public void    initialiserJeu()      { this.jeu = new Jeu(this.metier);      }
	public Carte   tirerCarte()          { return this.jeu.tirerCarte();         }
	public Carte   getCarteActuelle()    { return this.jeu.getCarteActuelle();   }
	public boolean piocheVide()          { return this.jeu.piocheVide();         }
	public boolean peutJouer()           { return this.jeu.peutJouer();          }
	public void    signalerJoue()        { this.jeu.signalerJoue();              }
	public void    passerAuTourSuivant() { this.jeu.passerAuTourSuivant();       }
	
	public int calculerScoreCouleur( char couleur )  { return this.jeu.calculerScoreCouleur( couleur ); }
	public int calculerScore()                       { return this.jeu.calculerScore();                 }


	public static void main(String[] args)
	{
		new ControleurJeu();
	}
}