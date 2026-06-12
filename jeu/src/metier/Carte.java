package jeu.src.metier;

import javax.swing.ImageIcon;

public class Carte
{
	private char      teinte;
	private char      symbole;
	private ImageIcon img;

	public Carte( char teinte, char symbole )
	{
		this.teinte  = teinte;
		this.symbole = symbole;
	}

	public int       getNbCarte()    { return 0;    }
	public ImageIcon getImageCarte() { return null; }
}