package jeu.src.metier;


public class Cellule
{
	private int     posX;
	private int     posY;
	private char    zone;
	private char    symbole;

	public Cellule( int x,int y )
	{
	   this.posX = x;
	   this.posY = y;
	}

	public Cellule( int x,int y,char symbol )
	{
	   this.posX = x;
	   this.posY = y;
	   this.symbole = symbol;
	}

	//accesseurs
	public char getSymbole()       { return this.symbole; }
	public int  getX()             { return posX;         }
	public int  getY()             { return posY;         }
	public char getZone()          { return this.zone;    }

	//modificateurs
	public void setZone(char zone) { this.zone = zone;    }
}