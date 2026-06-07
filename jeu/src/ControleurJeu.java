package jeu.src;

import conception.src.metier.Jeu;
import jeu.src.ihm.FramePioche;

public class ControleurJeu
{
    private Jeu              metier;
    //private FrameConception  frameConception;
    private FramePioche      framePioche;

    public ControleurJeu()
    {
        this.metier      = new Jeu();
        this.framePioche = new FramePioche( this );
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

    public static void main(String[] args)
    {
        new ControleurJeu();
    }
}