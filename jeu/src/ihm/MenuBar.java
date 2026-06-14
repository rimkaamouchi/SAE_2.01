package jeu.src.ihm;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import jeu.src.ControleurJeu;

public class MenuBar
{
    private ControleurJeu ctrl;

    public static JMenuBar creerMenu(JFrame frame, ControleurJeu ctrl)
    {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(30, 30, 38));
        menuBar.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));

        // Menu Aide 
        JMenu menuAide = new JMenu("Aide");
        menuAide.setForeground(Color.WHITE);

        JMenuItem itemRegles = new JMenuItem("Règles du jeu");
        itemRegles.addActionListener(e -> afficherAide(frame));
        menuAide.add(itemRegles);

        // Menu Partie 
        JMenu menuPartie = new JMenu("Partie");
        menuPartie.setForeground(Color.WHITE);

        JMenuItem itemNouvelle = new JMenuItem("Nouvelle partie");
        itemNouvelle.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(frame,
                "Recommencer une nouvelle partie ?", "Nouvelle partie",
                JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION)
            {
                ctrl.reset();
            }
        });
        menuPartie.add(itemNouvelle);

        menuBar.add(menuAide);
        menuBar.add(menuPartie);

        return menuBar;
    }

    // Frame qui s'ouvre quand on clic sur Aide
    private static void afficherAide(JFrame frame)
    {
        JDialog dialog = new JDialog(frame, "Règles du jeu", true);
        dialog.setSize(480, 400);
        dialog.setLocationRelativeTo(frame);

        JTextArea texte = new JTextArea();
        texte.setEditable(false);
        texte.setLineWrap(true);
        texte.setWrapStyleWord(true); // pour que les mots soient bien découpés
        texte.setBackground(new Color(30, 30, 38));
        texte.setForeground(Color.LIGHT_GRAY);
        texte.setFont(new Font("SansSerif", Font.PLAIN, 13));
        texte.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        texte.setText(
            "Systeme de Point\n\n"+
            "À la fin de la partie, les joueurs comptent les points obtenus grâce aux continents qu'ils ont visités au cours de leur voyage. \n" +
            "Pour calculer son score, chaque joueur repère le continent dans lequel il possède le plus grand nombre de pays reliés entre eux par ses bateaux. \n" +
            "Le nombre de points obtenus est égal au nombre de pays reliés dans ce continent multiplié par le nombre total de continents visités durant la partie. \n" + 
            "Exemple : Un joueur a visité 3 continents au cours de la partie. Dans le continent où son réseau est le plus développé, il a relié 3 pays entre eux avec ses bateaux. \n" +
            "Le calcul est donc : \n" +
            "3 pays reliés × 3 continents visités = 9 points \n" +
            "Le joueur marque donc 9 points."
        );

        dialog.add(new JScrollPane(texte));
        dialog.setVisible(true);
    }
}