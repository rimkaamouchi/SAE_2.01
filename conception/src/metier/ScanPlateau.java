package conception.src.metier;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import conception.src.ControleurConception;

public class ScanPlateau
{
	private ControleurConception ctrl;

	public static void sauvegarder(ControleurConception ctrl)
	{
		String nom = ctrl.getNomPlateau();
		if (nom == null || nom.isBlank()) 
		{  // isBlank: ne contient pas de caractère & méthode de String
			System.err.println("Nom du plateau non défini.");
			return;
		}

		String nomFichier = nom.replaceAll(" ", "_"); // méthode de String qui va remplacer TOUT les espaces
		try {
			File dossier = new File("conception");
			if (!dossier.exists()) 
			{
				dossier.mkdirs(); // méthode qui crée un new dossier & qui appartient à Java.io.File 
			}

			PrintWriter pw = new PrintWriter(new OutputStreamWriter(
				new FileOutputStream("conception/" + nomFichier + ".data"), "UTF-8"));

			pw.println(ctrl.getNomPlateau());
			pw.println(ctrl.getTaillePlateauX());
			pw.println(ctrl.getTaillePlateauY());
			pw.println(ctrl.getNbSymboles());

			// Écriture des zones
			String[][] zones = ctrl.getZonesPlateau();
			int nbLig = ctrl.getTaillePlateauX();
			int nbCol = ctrl.getTaillePlateauY();

			for (int l = 0; l < nbLig; l++) 
			{
				for (int c = 0; c < nbCol; c++) {
					String zone = (zones[l][c] != null) ? zones[l][c] : "."; // opération ternaire: si case = null alors on met un point
					pw.print(zone + " ");
				}
				pw.println();
			}

			// Écriture des liens
			ArrayList<Lien> routes = ctrl.getRoutes();
			pw.println("LIENS " + routes.size());
			for (Lien l : routes) 
			{
				pw.println( l.getDepart() .getX() + " " + l.getDepart() .getY()      + " " +
							l.getArrivee().getX() + " " + l.getArrivee().getY()      + " " +
							l.getDirection()     +  " " + l.getDepart().getSymbole() + " " +
							l.getArrivee().getSymbole());
			}

			pw.close();
			System.out.println("Plateau sauvegardé : conception/" + nomFichier + ".data");

		} catch (Exception e)
		{
			e.printStackTrace();
			System.err.println("Erreur sauvegarde : " + e.getMessage());
		
		}
	}
}