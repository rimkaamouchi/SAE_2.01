package jeu.src.metier;
import java.util.ArrayList;

/**
 * Calcul les points de la partie.
 * <p>
 * Cette classe les calculs du jeu.
 * </p>
 * * @author Groupe 11: Quentin Deshayes, Roxane Sidolle, Manon Rigoult, Rim Kaamouchi et Mykhailo Liapin
 * @version 1.0
 */
public class Point
{
	private Jeu jeu;

	public Point(Jeu jeu){this.jeu = jeu;}

	//return le nb de continent (zones) visités au total
	public int getNbContinentsVisites()
	{
		ArrayList<Character> zonesVues = new ArrayList<>();

		for (Chemin chemin : this.jeu.getChemins())
			for (Cellule c : chemin.getEtapes())
			{
				char zone = c.getZone();
				if(zone != 0 && !zonesVues.contains(zone))
					zonesVues.add(zone);
			}
		
		return zonesVues.size();
		
	}

	public int getMaxPaysRelier()
	{
		int max = 0;

		for (Chemin chemin : this.jeu.getChemins())
		{
			ArrayList<Character> zones     = new ArrayList<>();
			ArrayList<Integer> nbParZone = new ArrayList<>();
		
			for (Cellule c : chemin.getEtapes())
			{
				char zone = c.getZone();
				if( zone == 0) continue;

				boolean trouve = false;
				for(int i = 0; i < zones.size(); i++ )
				{
					if(zones.get(i) == zone)
					{
						nbParZone.set(i, nbParZone.get(i) + 1);
						trouve = true;
						break;
					}
				}
				if( !trouve )
				{
					zones.add(zone);
					nbParZone.add(1 );
				}
			}

			for (int nb : nbParZone)
				if(nb > max) max = nb;

		}

		return max;
	}

	public int calculerScore()
	{
		int nbContinents = getNbContinentsVisites();
		int maxPays      = getMaxPaysRelier();

		return maxPays * nbContinents;
	}
}