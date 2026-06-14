package jeu.src.ihm;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import jeu.src.ControleurJeu;
import jeu.src.metier.Cellule;
import jeu.src.metier.Lien;

public class PanelJeu extends JPanel
{
	private ControleurJeu  ctrl;

	private Image[][]      matriceZones;
	private Image[][]      matriceSymboles;
	private int            nbLig;
	private int            nbCol;

	private static final char  [] LETTRES_ZONES    = {'A','B','C','D','E','F','G'};
	private static final String[] IMAGES_ZONES     = {"Zones/bleu_azur.png","Zones/bleuVert.png","Zones/lila.png","Zones/orange_clair.png","Zones/candy.png", "Zones/rose_clair.png", "Zones/vert_clair.png" };
	private static final char  [] LETTRES_SYMBOLES = {'R','Q','U','S','T'};
	private static final String[] IMAGES_SYMBOLES  = {"Symboles/orange.png","Symboles/apple.png","Symboles/cafe.png","Symboles/moto.png","Symboles/pain.png"};
	private static final char  [] LETTRES_COULEURS = {'V','W','X','Y','Z'};
	private static final String[] IMAGES_COULEURS  = {"Symboles/bordeaux.png","Symboles/rose.png","Symboles/bleu_fonce.png","Symboles/marron.png","Symboles/bleu.png"};

	//setters
	public void setPlateau( int nbLig, int nbCol )
	{
		this.nbLig = nbLig;
		this.nbCol = nbCol;

		this.matriceZones    = new Image[nbLig][nbCol];
		this.matriceSymboles = new Image[nbLig][nbCol];

		for( int i = 0; i < nbLig; i++ )
		for( int j = 0; j < nbCol; j++ )
		{
			Cellule c = ctrl.getPlateau().getCellule(i, j);
			if ( c != null )
			{
				// chercher la zone de c dans LETTRES_ZONES
				for( int l = 0; l < LETTRES_ZONES.length; l++ )
				{
					if( LETTRES_ZONES[l] == c.getZone() )
					{
						ImageIcon icon = new ImageIcon( "conception/src/Image/" + IMAGES_ZONES[l] );
						matriceZones[i][j] = icon.getImage();
					}
				}
				// chercher le symbole de c dans LETTRES_SYMBOLES et LETTRES_COULEURS
				for( int k = 0; k < LETTRES_SYMBOLES.length; k++ )
				{
					if( LETTRES_SYMBOLES[k] == c.getSymbole() )
					{
						ImageIcon icon = new ImageIcon( "conception/src/Image/" + IMAGES_SYMBOLES[k] );
						matriceSymboles[i][j] = icon.getImage(); // charger l'image IMAGES_ZONES[k] dans matriceZones[i][j]
					}
				}
				for( int h = 0; h < LETTRES_COULEURS.length; h++ )
				{
					if( LETTRES_COULEURS[h] == c.getSymbole() )
					{
						ImageIcon icon = new ImageIcon( "conception/src/Image/" + IMAGES_COULEURS[h] );
						matriceSymboles[i][j] = icon.getImage();
					}
				}
			}
		}

		this.repaint(); // redessine le plateau
	}

	public PanelJeu( ControleurJeu ctrl )
	{
		this.ctrl = ctrl;
		/*-------------------------------*/
		/*    Création des composants    */
		/*-------------------------------*/

		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/

		/*-------------------------------*/
		/*   Activation des composants   */
		/*-------------------------------*/
		
	}

	@Override // modifie une méthode dans JPanel
	public void paintComponent( Graphics g )
	{
		super.paintComponent(g);

		if ( this.nbLig == 0 || this.nbCol == 0 ) return;

		Graphics2D graph = (Graphics2D) g;

		int largeurCase = this.getWidth()  / this.nbCol;
		int hauteurCase = this.getHeight() / this.nbLig;

		// Dessin des zones (arrière-plan)
		if ( this.matriceZones != null )
		{
			for ( int i = 0; i < nbLig; i++ )
				for ( int j = 0; j < nbCol; j++ )
					if ( this.matriceZones[i][j] != null )
						graph.drawImage( this.matriceZones[i][j],
										j * largeurCase, i * hauteurCase,
										largeurCase, hauteurCase, this );
		}

		// Dessin des liens EN GRIS (avant les symboles mais après les zones)
		ArrayList<Lien> routes = ctrl.getPlateau().getRoutes();
		if ( routes != null )
		{
			graph.setColor ( Color.LIGHT_GRAY  );
			graph.setStroke( new BasicStroke(3) );

			for ( Lien l : routes )
			{
				int x1 = l.getDepart() .getY() * largeurCase + largeurCase / 2;
				int y1 = l.getDepart() .getX() * hauteurCase + hauteurCase / 2;
				int x2 = l.getArrivee().getY() * largeurCase + largeurCase / 2;
				int y2 = l.getArrivee().getX() * hauteurCase + hauteurCase / 2;

				graph.drawLine( x1, y1, x2, y2 );
			}
		}

		// Dessin des symboles (premier plan)
		if ( this.matriceSymboles != null )
		{
			for ( int i = 0; i < nbLig; i++ )
				for ( int j = 0; j < nbCol; j++ )
					if ( this.matriceSymboles[i][j] != null )
						graph.drawImage( this.matriceSymboles[i][j],
										j * largeurCase, i * hauteurCase,
										largeurCase, hauteurCase, this );
		}

		// Dessin de la grille EN NOIR (par-dessus tout)
		graph.setColor ( Color.BLACK        );
		graph.setStroke( new BasicStroke(1) );

		for ( int i = 0; i <= nbLig; i++ )
			graph.drawLine( 0, i * hauteurCase, this.getWidth(), i * hauteurCase );

		for ( int j = 0; j <= nbCol; j++ )
			graph.drawLine( j * largeurCase, 0, j * largeurCase, this.getHeight() );

	}
}