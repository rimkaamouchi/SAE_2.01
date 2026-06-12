package jeu.src.ihm;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JPanel;

import conception.src.ihm.PanelPara;
import conception.src.ihm.PanelZone;
import jeu.src.ControleurJeu;
import jeu.src.metier.Lien;

public class PanelJeu extends JPanel
{
	private ControleurJeu  ctrl;
	private PanelPara      panelPara;
	private PanelZone      panelZone;

	private Image[][]      matriceZones;
	private Image[][]      matriceSymboles;
	private int            nbLig;
	private int            nbCol;

	//setters
	public void setPlateau( int nbLig, int nbCol )
	{
		this.nbLig = nbLig;
		this.nbCol = nbCol;

		this.matriceZones    = new Image[nbLig][nbCol];
		this.matriceSymboles = new Image[nbLig][nbCol];

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
		graph.setColor ( Color.BLACK          );
		graph.setStroke( new BasicStroke(1)   );

		for ( int i = 0; i <= nbLig; i++ )
			graph.drawLine( 0, i * hauteurCase, this.getWidth(), i * hauteurCase );

		for ( int j = 0; j <= nbCol; j++ )
			graph.drawLine( j * largeurCase, 0, j * largeurCase, this.getHeight() );
	}
}