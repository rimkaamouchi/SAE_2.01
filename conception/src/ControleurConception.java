package conception.src;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import conception.src.ihm.FrameConception;
import conception.src.metier.Lien;
import conception.src.metier.Plateau;
import conception.src.metier.ScanPlateau;

public class ControleurConception
{
	private Plateau          metier;
	private FrameConception  frameConception;


	private ImageIcon[]      iconeSymbole;
	private ImageIcon[]      iconeCouleur;

	public ControleurConception()
	{
		this.metier          = new Plateau();
		this.frameConception = new FrameConception( this );
	}
	
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
	public boolean toutesLesCasesOntUneZone()
	{
		return this.metier.toutesLesCasesOntUneZone();
	}

	//accesseurs (getters)
	public int             getNbLigne()        { return this.metier.getNbLigne();                            } // méthode appartenant à la class Plateau
	public int             getNbColonne()      { return this.metier.getNbColonne();                          } // méthode appartenant à la class Plateau
	public String          getNomPlateau()     { return this.frameConception.getPanelPara().getNomPlateau(); }
	public int             getTaillePlateauX() { return this.taillePlateauX;                                 }
	public int             getTaillePlateauY() { return this.taillePlateauY;                                 }
	public int             getNbSymboles()     { return this.nbSymboles;                                     }
	public ArrayList<Lien> getRoutes()         { return this.metier.getRoutes();                             }
	public String[][]      getZonesPlateau()   { return this.metier.getZonesPlateau();                       }


	//modificateurs (setters)
	public void setZone(int lig, int col, String zone){ this.metier.setZone(lig, col, zone); }

	/*---- Méthode pour les icônes neutres ----*/
	public ImageIcon getImageSymbole(int indice)   { return this.iconeSymbole[indice]; }
	public void setImageSymbole(ImageIcon[] icone) { this.iconeSymbole = icone;        }

	/*---- Méthode pour les icônes en couleur ----*/
	public ImageIcon getImageCouleur(int indice)   { return this.iconeCouleur[indice]; }
	public void setImageCouleur(ImageIcon[] icone) { this.iconeCouleur = icone;        }

	/*---- Méthode dans PanelPara ---- */
	public ImageIcon getImageSelectionnee( int indice, boolean couleur )
	{
		if ( couleur ) return this.iconeCouleur[indice];
		else           return this.iconeSymbole[indice];
	}

	public void    actionSauvegarder()                           { ScanPlateau.sauvegarder( this );              }
	/*----Méthode contenue dans Jeu----*/
	public boolean verifZone( char zone )                        { return this.metier.verifZone(zone);           }
	public void    placerCellule(int lig, int col, char symbole) { this.metier.placerCellule(lig, col, symbole); }
	public void    supprimerCellule(int lig, int col)            { this.metier.supprimerCellule(lig, col);       }
	public void    supprimerZone( int lig, int col )             { this.metier.supprimerZone(lig, col);          }
	public void    calculerRoutes()                              { this.metier.calculerRoutes();                 }	
	public boolean validerToutesLesZones()                       { return this.metier.validerToutesLesZones();   }
	public boolean plateauEstComplet()                           { return this.metier.plateauEstComplet();       }

	public static void main( String[] args )
	{
		new ControleurConception();
	}
}