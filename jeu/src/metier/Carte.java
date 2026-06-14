package jeu.src.metier;

import javax.swing.ImageIcon;

public class Carte
{
	private char      teinte;
	private char      symbole;
	private ImageIcon img;

	//getters
	public int       getNbCarte()    { return 0;            }
	public ImageIcon getImageCarte() { return null;         }
	public char      getTeinte()     { return this.teinte;  }
	public char      getSymbole()    { return this.symbole; }

	//constructeur
	public Carte( char teinte, char symbole )
	{
		this.teinte  = teinte;
		this.symbole = symbole;
	}
}