package conception.src.metier;

import java.util.ArrayList;

public class Main
{
	public static void main(String[] args) {

		Jeu jeu = new Jeu();

		// Test 1 : affichage plateau vide
		System.out.println("=== Plateau vide ===");
		Jeu.afficher(jeu.getPlateau());

		// Test 2 : initialisation
		jeu.initPlat();
		System.out.println("\n=== Plateau après initPlat ===");
		Jeu.afficher(jeu.getPlateau());

		// Test 3 : calcul des liens
		jeu.calculerRoutes();
		System.out.println("\n=== Liens trouvés ===");
		ArrayList<Lien> routes = jeu.getRoutes();
		if (routes.isEmpty()) {
			System.out.println("Aucun lien trouvé.");
		} else {
			for (Lien l : routes) {
				System.out.println("(" + l.getDepart().getX()  + "," + l.getDepart().getY() + ")"
								+ " --" + l.getDirection() + "--> "
								+ "(" + l.getArrivee().getX() + "," + l.getArrivee().getY() + ")");
			}
		}

		// Test 4 : existeLien sur (3,5) — doit avoir un lien
		System.out.println("\n=== existeLien (3,5) ===");
		Cellule cel1 = jeu.getPlateau()[3][5];
		System.out.println("Liens sur (3,5) : " + jeu.existeLien(cel1));
		for (Lien l : jeu.getLiens(cel1)) {
			System.out.println("  -> " + l.getDirection()
							+ " vers (" + l.getArrivee().getX() + "," + l.getArrivee().getY() + ")");
		}

		// Test 5 : existeLien sur (0,0) — ne doit pas avoir de lien
		System.out.println("\n=== existeLien (0,0) ===");
		Cellule cel2 = jeu.getPlateau()[0][0];
		if (cel2 == null) {
			System.out.println("(0,0) est vide, pas de lien possible.");
		} else {
			System.out.println("Liens sur (0,0) : " + jeu.existeLien(cel2));
		}

		// Test 6 : dimensions du plateau
		System.out.println("\n=== Dimensions ===");
		System.out.println("Lignes   : " + jeu.getNbLigne());
		System.out.println("Colonnes : " + jeu.getNbColonne());
	}
}