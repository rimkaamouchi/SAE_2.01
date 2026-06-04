package src;

import src.ihm.FrameAppli;
import src.ihm.FramePara;
import src.ihm.FramePioche;
import src.metier.Jeu;
//commentaire
public class Controleur
{
    private Jeu         metier;
    private FrameAppli  frameAppli;
    private FramePara   framePara;
    private FramePioche framePioche;

    public Controleur()
    {
        this.metier      = new Jeu();
        this.framePara   = new FramePara( this );
    }

    public int getNbLigne()  { return this.metier.getNbLigne();   } // méthode appartenant à la class Jeu
    public int getNbColonne(){ return this.metier.getNbColonne(); } // méthode appartenant à la class Jeu


    public static void main(String[] args)
    {
        new Controleur();
    }
}