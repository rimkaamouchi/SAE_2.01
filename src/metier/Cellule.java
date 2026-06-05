package src.metier;

import src.Controleur;

public class Cellule
{
	private Controleur ctrl;

	private int  posX;
	private int  posY;
	
	private char symbole;
	
	private char couleur;

	public Cellule(Controleur ctrl, int x, int y )
	{
	   this.ctrl = ctrl;
	   
	   this.posX = x;
	   this.posY = y;
	}

	public Cellule( Controleur ctrl, int x, int y, char symbol )
	{
	   this.ctrl = ctrl;
	   
	   this.posX = x;
	   this.posY = y;
	   this.symbole = symbol;
	}

	public char getSymbole()
	{
		return this.symbole;
	}

	public int getX()
	{
		return posX;
	}

	public int getY()
	{
		return posY;
	}
}