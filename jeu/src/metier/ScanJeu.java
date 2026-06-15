package jeu.src.metier;

import java.io.FileInputStream;
import java.util.Scanner;

import jeu.src.ControleurJeu;

/**
 * Scanneur du Jeu.
 * <p>
 * Ce Scanneur lit le dossier .data fait par la partie conception.
 * </p>
 * * @author Groupe 11: Quentin Deshayes, Roxane Sidolle, Manon Rigoult, Rim Kaamouchi et Mykhailo Liapin
 * @version 1.0
 */
public class ScanJeu
{
	private ControleurJeu ctrl;

	public static void LecteurJeu( ControleurJeu ctrl )
	{
		try 
		{
			Scanner sc = new Scanner( new FileInputStream( "conception/Test.data" ), "UTF8" );

			String nom    = sc.nextLine();
			int    nbLig  = Integer.parseInt( sc.nextLine() );
			int    nbCol  = Integer.parseInt( sc.nextLine() );
			int    nbCase = Integer.parseInt( sc.nextLine() );

			ctrl.setParametres( nom, nbLig, nbCol, nbCase );

			for( int i = 0; i < nbLig; i++ )
			{	
				String[] ligne = sc.nextLine().trim().split( " " );
				for( int j = 0; j < nbCol; j++ )
				{
					Cellule c = ctrl.getPlateau().getOuCreerCellule(i, j);
							c.setZone( ligne[j].charAt(0) );
				}
			}

			int nbLien = Integer.parseInt( sc.nextLine().split(" ")[1] );
			for( int i = 0; i < nbLien; i++ )
			{
				String[] obj     = sc.nextLine().trim().split( " " ); // construit les obj 1 par 1
				Cellule  depart  = ctrl.getPlateau().getOuCreerCellule( Integer.parseInt(obj[0]), Integer.parseInt(obj[1]) );
				Cellule  arrivee = ctrl.getPlateau().getOuCreerCellule( Integer.parseInt(obj[2]), Integer.parseInt(obj[3]) );
				Lien     lien    = new Lien( depart, arrivee, obj[4].charAt(0) );
				ctrl   .getPlateau().ajouterLien( lien );
				depart .setSymbole( obj[5].charAt(0)   );
				arrivee.setSymbole( obj[6].charAt(0)   );
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