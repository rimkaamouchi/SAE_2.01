package jeu.src.ihm;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import jeu.src.ControleurJeu;

public class MenuBar extends JMenuBar implements ActionListener
{
    private ControleurJeu ctrl;
    private FrameJeu      frmJeu;

    private JMenuItem menuiAideRegle;
    private JMenuItem menuiNouvellePartie;

    public MenuBar( ControleurJeu ctrl )
    {
        this.ctrl = ctrl;

        /*-------------------------------*/
		/*    Création des composants    */
		/*-------------------------------*/
        JMenuBar  menuBar = new JMenuBar();
        menuBar.setBackground(new Color(30, 30, 38));
        menuBar.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));

        //éléments de la barre de menu
        JMenu menuAide   = new JMenu( "Aide"   );
        JMenu menuPartie = new JMenu( "Partie" );

        menuAide  .setMnemonic( 'A' );
        menuPartie.setMnemonic( 'P' );

        //les items des menus Aide et Partie
        this.menuiAideRegle      = new JMenuItem( "Règles du jeu"   );
        this.menuiNouvellePartie = new JMenuItem( "Nouvelle partie" );

        this.menuiAideRegle     .setMnemonic( 'R' );
        this.menuiNouvellePartie.setMnemonic( 'N' );

        /*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/
        menuAide  .add( this.menuiAideRegle );
        menuPartie.add( this.menuiNouvellePartie );

        this.add( menuAide   );
        this.add( menuPartie );


        /*-------------------------------*/
		/*   Activation des composants   */
		/*-------------------------------*/
        this.menuiAideRegle     .addActionListener( this );
        this.menuiNouvellePartie.addActionListener( this );
    }

    public void actionPerformed( ActionEvent e )
    {
       if ( e.getSource() == this.menuiAideRegle )
       {
            this.afficherAide( frmJeu );
       }

       if ( e.getSource() == this.menuiNouvellePartie )
       {
            int confirm = JOptionPane.showConfirmDialog( this,
                "Recommencer une nouvelle partie ?", "Nouvelle partie",
                JOptionPane.YES_NO_OPTION );

            if (confirm == JOptionPane.YES_OPTION)
            {
                ctrl.reset();
            }
       }
    }
    
    // Frame qui s'ouvre quand on clic sur Aide
    public static void afficherAide( FrameJeu frmJeu )
    {
        JDialog dialog = new JDialog( frmJeu, "Règles du jeu", true);
        dialog.setSize(480, 400);
        dialog.setLocationRelativeTo( frmJeu );

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