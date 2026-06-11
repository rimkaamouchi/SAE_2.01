package jeu.src.metier;

import java.io.FileInputStream;
import java.util.Scanner;

import jeu.src.ControleurJeu;

public class ScanJeu
{
	private ControleurJeu ctrl;

	public static void LecteurJeu( ControleurJeu ctrl )
	{
		try 
		{
			Scanner sc = new Scanner( new FileInputStream( "conception/src/Test.data" ), "UTF8" );

			String nom = sc.nextLine();
			int nbLig  = Integer.parseInt( sc.nextLine() );
			int nbCol  = Integer.parseInt( sc.nextLine() );
			int nbCase = Integer.parseInt( sc.nextLine() );

			String[][] grille = new String[nbLig][nbCol];
			for( int i = 0; i < nbLig; i++ )
			{
				grille[i] = sc.nextLine().trim().split( " " ); //trim méthode de String pour et split remplace
			}
			
			int nbLien = Integer.parseInt( sc.nextLine().split(" ")[1] );
			for( int i = 0; i < nbLien; i++ )
			{
				String[] obj = sc.nextLine().trim().split( " " ); // construit les obj 1 par 1
			}
			sc.close();

			
		}
		catch (Exception e) { e.printStackTrace(); }
	}
}

/*--------------------*/
/* Lecture du Scanner */
/*--------------------*/

// l1 : Nom    du plateau
// l2 : Nombre de lignes
// l3 : Nombre de colonnes
// l4 : Nombre de symboles

// l5 : Zones
// autant de lignes que de zones

// l6 + nbLigneZone : Nombre de liens

// l7 + nbLigneZone + 1 : x y départ, x y arrivée, direction lien,
// 						  symbole départ, symbole d'arrivée
// autant de lignes que de liens