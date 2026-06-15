package jeu.src;

import jeu.src.ihm.FrameAccueil;
import jeu.src.ihm.FrameJeu;
import jeu.src.ihm.PanelJeu;
import jeu.src.ihm.PanelPioche;
import jeu.src.metier.Carte;
import jeu.src.metier.Cellule;
import jeu.src.metier.Jeu;
import jeu.src.metier.Plateau;
import jeu.src.metier.Point;
import jeu.src.metier.ScanJeu;

public class ControleurJeu
{
	private Plateau          metier;
	private FrameAccueil     frameAccueil;
	private FrameJeu         frameJeu;
	private Jeu              jeu;
	private ScanJeu          lecteurJeu;
	private PanelPioche      panelPioche;
	private PanelJeu         panelJeu;

	/*---- getters ----*/
	public String       getNomPlateau()     { return this.nom;                      }
	public int          getTaillePlateauX() { return this.taillePlateauX;           }
	public int          getTaillePlateauY() { return this.taillePlateauY;           }
	public int          getNbSymboles()     { return this.nbSymboles;               }
	public Plateau      getPlateau()        { return this.metier;                   }
	public FrameJeu     getFrameJeu()       { return this.frameJeu;                 }
	public FrameAccueil getFrameAccueil()   { return this.frameAccueil;             }
	public Jeu          getJeu()            { return this.jeu;                      }
	public char         getCouleurActuelle(){ return this.jeu.getCouleurActuelle(); }
	public int          getNbLigne()        { return this.metier.getNbLigne();      } // méthode appartenant à la class Jeu
	public int          getNbColonne()      { return this.metier.getNbColonne();    } // méthode appartenant à la class Jeu

	//setter
	public void setFrameJeu( FrameJeu frameJeu ) { this.frameJeu = frameJeu; }
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

		this.jeu          = new Jeu(this.metier);
		this.frameAccueil = new FrameAccueil( this );
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
		this.jeu.reset(); // On réinitialise les données du modèle
		this.frameJeu.resetGraphique(); // On rafraîchit l'interface graphique
	}

	// Ajouter les méthodes :
	public void    initialiserJeu()                  { this.jeu = new Jeu(this.metier);                 }
	public void    signalerJoue()                    { this.jeu.signalerJoue();                         }
	public void    passerAuTourSuivant()             { this.jeu.passerAuTourSuivant();                  }
	public void    activerModeTriche()               { this.jeu.activerModeTriche();                    }
	public Carte   tirerCarte()                      { return this.jeu.tirerCarte();                    }
	public Carte   getCarteActuelle()                { return this.jeu.getCarteActuelle();              }
	public boolean piocheVide()                      { return this.jeu.piocheVide();                    }
	public boolean peutJouer()                       { return this.jeu.peutJouer();                     }
	public boolean isModeTriche()                    { return this.jeu.isModeTriche();                  }
	public boolean toutesCartesNoiresTirees()        { return this.jeu.toutesCartesNoiresTirees();      }
	public int calculerScoreCouleur( char couleur )  { return this.jeu.calculerScoreCouleur( couleur ); }
	public int calculerScore()                       { return this.jeu.calculerScore();                 }

	public int calculerScorePoint()
	{
		Point point = new Point( this.jeu );
		return point.calculerScore() ;
	}

	public static void main(String[] args)
	{
		new ControleurJeu();
	}
}