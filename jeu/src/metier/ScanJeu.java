package jeu.src.metier;

import java.io.FileInputStream;
import java.util.Scanner;

import jeu.src.ControleurJeu;

public class ScanJeu
{
	private ControleurJeu ctrl;

	public static void ScanJeu(ControleurJeu ctrl)
	{

		try 
		{
			Scanner sc = new Scanner(new FileInputStream( "conception/src/Test.data" ), "UTF8");

			while(sc.hasNextLine())
				System.out.println(sc.nextLine());
			sc.close();

		}
		catch (Exception e) { e.printStackTrace(); }
	}
}