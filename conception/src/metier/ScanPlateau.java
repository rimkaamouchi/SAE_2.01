package conception.src.metier;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import conception.src.ControleurConception;

public class ScanPlateau
{
    private ControleurConception ctrl;

    public static void sauvegarder( ControleurConception ctrl )
    {
        // On remplace les espaces par des underscores pour le nom du fichier
        String nomFichier = ctrl.getNomPlateau().replaceAll(" ", "_");

        try
        {
            // Initialisation du flux d'écriture en UTF-8
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(
                new FileOutputStream("conception/" + nomFichier + ".data"), "UTF-8"));
            
            // Écriture des métadonnées du plateau
            pw.println( ctrl.getNomPlateau() );
            pw.println( ctrl.getTaillePlateauX() ); // Nombre de lignes
            pw.println( ctrl.getTaillePlateauY() ); // Nombre de colonnes
            pw.println( ctrl.getNbCouleurs() );
            pw.println( ctrl.getNbSymboles() );

            // Récupération de la grille des zones (ex: String[][] ou char[][])
            String[][] zones = ctrl.getZonesPlateau();
            int nbLig = ctrl.getTaillePlateauX();
            int nbCol = ctrl.getTaillePlateauY();

            // Écriture de la matrice des zones
            for (int l = 0; l < nbLig; l++)
            {
                for (int c = 0; c < nbCol; c++)
                {
                    // Si la zone est vide ou nulle, on met un caractère par défaut (ex: '.')
                    String zone = (zones[l][c] != null) ? zones[l][c] : ".";
                    pw.print(zone + " ");
                }
                pw.println(); 
            }
            pw.close();
            System.out.println("Plateau sauvegardé avec succès sous : " + nomFichier + ".data");
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }
}