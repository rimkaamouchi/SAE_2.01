package conception.src.metier;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import conception.src.ControleurConception;

public class ScanPlateau
{
    private ControleurConception ctrl;

   public static void sauvegarder(ControleurConception ctrl)
    {
        String nom = ctrl.getNomPlateau();
        if (nom == null || nom.isBlank()) {
            System.err.println("Nom du plateau non défini.");
            return;
        }

        String nomFichier = nom.replaceAll(" ", "_");

        try {
            File dossier = new File("conception");
            if (!dossier.exists()) {
                dossier.mkdirs();
            }

            PrintWriter pw = new PrintWriter(new OutputStreamWriter(
                new FileOutputStream("conception/" + nomFichier + ".data"), "UTF-8"));

            pw.println(ctrl.getNomPlateau());
            pw.println(ctrl.getTaillePlateauX());
            pw.println(ctrl.getTaillePlateauY());
            pw.println(ctrl.getNbSymboles());

            String[][] zones = ctrl.getZonesPlateau();
            int nbLig = ctrl.getTaillePlateauX();
            int nbCol = ctrl.getTaillePlateauY();

            for (int l = 0; l < nbLig; l++) {
                for (int c = 0; c < nbCol; c++) {
                    String zone = (zones[l][c] != null) ? zones[l][c] : ".";
                    pw.print(zone + " ");
                }
                pw.println();
            }

            pw.close();
            System.out.println("Plateau sauvegardé : conception/" + nomFichier + ".data");

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erreur sauvegarde : " + e.getMessage());
        }
    }
}