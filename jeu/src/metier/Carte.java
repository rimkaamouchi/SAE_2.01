package jeu.src.metier;

import javax.swing.ImageIcon;

/**
 * Représente les cartes du jeu utilisé lors de la partie. Les cartes sont des ressources liées à chaque continent. 
 * <p>
 * Cette classe gère l'apparence des cartes: teinte et symbole de la carte.
 * </p>
 * * @author Groupe 11: Quentin Deshayes, Roxane Sidolle, Manon Rigoult, Rim Kaamouchi et Mykhailo Liapin
 * @version 1.0
 */
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