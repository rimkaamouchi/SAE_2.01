package conception.src;

import javax.swing.ImageIcon;

import conception.src.ihm.FrameConception;
import conception.src.metier.Jeu;

public class ControleurConception
{
    private Jeu              metier;
    private FrameConception  frameConception;

    private ImageIcon[]      iconeSymbole;
    private ImageIcon[]      iconeCouleur;

    public ControleurConception()
    {
        this.metier          = new Jeu();
        this.frameConception = new FrameConception( this );
    }

    public int getNbLigne()  { return this.metier.getNbLigne();   } // méthode appartenant à la class Jeu
    public int getNbColonne(){ return this.metier.getNbColonne(); } // méthode appartenant à la class Jeu

    private int taillePlateauX;
    private int taillePlateauY;
    private int nbCouleurs;
    private int nbSymboles;

    public void setParametres( /*int taillePlateauX, int taillePlateauY,*/ int nbCouleurs, int nbSymboles )
    {
        //this.taillePlateauX = taillePlateauX;
        //this.taillePlateauY = taillePlateauY;
        this.nbCouleurs     = nbCouleurs;
        this.nbSymboles     = nbSymboles;
    }

    public int getTaillePlateauX() { return this.taillePlateauX; }
    public int getTaillePlateauY() { return this.taillePlateauY; }
    public int getNbCouleurs()     { return this.nbCouleurs;     }
    public int getNbSymboles()     { return this.nbSymboles;     }

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
    
    public static void main(String[] args)
    {
        new ControleurConception();
    }
}