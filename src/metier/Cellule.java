package src.metier;

public class Cellule
{
	private int  posX;
	private int  posY;
	
	private char symbole;
	
	private char couleur;

	public Cellule( int x, int y )
	{  
	   this.posX = x;
	   this.posY = y;
	}

	public Cellule( int x, int y, char symbol )
	{	   
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