package src;

import src.ihm.FrameAppli;
import src.ihm.FramePara;
import src.ihm.FramePioche;
import src.metier.Jeu;

public class Controleur
{
    private Jeu         jeu;
    private FrameAppli  frameAppli;
    private FramePara   framePara;
    private FramePioche framePioche;

    public Controleur()
    {
        this.jeu         = new Jeu();
        this.framePara   = new FramePara( this );
    }

    public int getNbLigne()  { return this.jeu.getNbLigne();   } // méthode appartenant à la class Jeu
    public int getNbColonne(){ return this.jeu.getNbColonne(); } // méthode appartenant à la class Jeu


    public static void main(String[] args)
    {
        new Controleur();
    }
}