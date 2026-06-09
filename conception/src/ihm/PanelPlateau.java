package conception.src.ihm;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import conception.src.ControleurConception;
import conception.src.metier.Lien;

public class PanelPlateau extends JPanel 
{
	private ControleurConception  ctrl;
	private PanelPara             panelPara;

	private Image[][]             matriceZones;
	private Image[][]             matriceSymboles;
	private int                   nbLig;
	private int                   nbCol;

	public PanelPlateau( ControleurConception ctrl )
	{
		this.ctrl = ctrl;
		this.nbLig = 0;
		this.nbCol = 0;

		this.addMouseListener(new GererSouris());
	}

	public void setPlateau(int nbLig, int nbCol)
	{
		this.nbLig = nbLig;
		this.nbCol = nbCol;

		this.matriceZones    = new Image[nbLig][nbCol];
		this.matriceSymboles = new Image[nbLig][nbCol];

		this.repaint(); // redessine le plateau
	}

	public void setPanelPara( PanelPara panelPara )
	{
		this.panelPara = panelPara;
	}

	// Méthode pour l'arrière-plan
	public void placerZone(int lig, int col, Image image)
	{
		if ( lig >= 0 && lig < nbLig && col >= 0 && col < nbCol )
		{
			this.matriceZones[lig][col] = image;
			this.repaint();

		}
	}

	// Méthode pour le premier plan
	public void placerSymbole(int lig, int col, Image image)
	{
		if ( lig >= 0 && lig < nbLig && col >= 0 && col < nbCol )
		{
			this.matriceSymboles[lig][col] = image;
			this.repaint();

			this.ctrl.calculerRoutes(); 		}
	}

	public void placerImage(int lig, int col, Image image)
	{
		this.placerSymbole(lig, col, image);
	}

	private boolean imagePresente( ImageIcon iconeSymbole )
	{
		if ( this.matriceSymboles == null ) return false;

		for ( int i = 0; i < nbLig; i++ )
			for ( int j = 0; j < nbCol; j++ )
				if ( this.matriceSymboles[i][j] == iconeSymbole.getImage() )
					return true;
		return false;
	}

	@Override // modifie une méthode dans JPanel
	public void paintComponent( Graphics g)
	{
		super.paintComponent(g);

		if ( this.nbLig == 0 || this.nbCol == 0 ) return;

		Graphics2D graph = (Graphics2D) g;
		graph.setColor(Color.BLACK);

		int largeurCase = this.getWidth()  / this.nbCol; 
		int hauteurCase = this.getHeight() / this.nbLig; 

		if ( this.matriceZones != null )
		{
			for ( int i = 0; i < nbLig; i++ )
				for ( int j = 0; j < nbCol; j++ )
					if ( this.matriceZones[i][j] != null )
						graph.drawImage( this.matriceZones[i][j],
											j * largeurCase, i * hauteurCase,
											largeurCase, hauteurCase, this );
		}
		if ( this.matriceSymboles != null )
		{
			for ( int i = 0; i < nbLig; i++ )
				for ( int j = 0; j < nbCol; j++ )
					if ( this.matriceSymboles[i][j] != null )
						graph.drawImage( this.matriceSymboles[i][j],
											j * largeurCase, i * hauteurCase,
											largeurCase, hauteurCase, this );
		}



		// dessin les liens
		ArrayList<Lien> routes = PanelPlateau.this.ctrl.getRoutes();
		if ( routes != null )
		{
			graph.setColor ( Color.LIGHT_GRAY         );
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

		// Dessine les lignes horizontales
		for ( int i = 0; i <= nbLig; i++ )
		{
			int y = i * hauteurCase;
			graph.drawLine( 0, y, this.getWidth(), y );
		}

		// Dessine les lignes verticales
		for ( int j = 0; j <= nbCol; j++ )
		{
			int x = j * largeurCase;
			graph.drawLine( x, 0, x, this.getHeight() );
		}
	}
		
	
	/*-----------------------------------------*/
	/* Définition de la classe interne Adapter */
	/*-----------------------------------------*/

	private class GererSouris extends MouseAdapter
	{
		public void mouseClicked( MouseEvent e )
		{
			if ( PanelPlateau.this.nbLig == 0 || PanelPlateau.this.nbCol == 0 || PanelPlateau.this.panelPara == null )
				return;

			int largeurCase = PanelPlateau.this.getWidth()  / PanelPlateau.this.nbCol;
			int hauteurCase = PanelPlateau.this.getHeight() / PanelPlateau.this.nbLig;

			int colCliquee  = e.getX() / largeurCase;
			int ligCliquee  = e.getY() / hauteurCase;

			if ( ligCliquee >= 0 && ligCliquee < PanelPlateau.this.nbLig &&
				colCliquee >= 0 && colCliquee < PanelPlateau.this.nbCol    )
			{
				// CLIC GAUCHE ou CLIC MILIEU : Placement 
				if ( e.getButton() == MouseEvent.BUTTON1 || e.getButton() == MouseEvent.BUTTON2 )
				{
					int indiceZone = PanelPlateau.this.panelPara.getIndiceZoneSelectionnee();

					if ( indiceZone >= 0 ) // une zone est sélectionnée dans le menu latéral
					{
						ImageIcon iconeZone = new ImageIcon( PanelPara.IMAGES_ZONES[ indiceZone ] );
						Image img = iconeZone.getImage().getScaledInstance( largeurCase, hauteurCase, Image.SCALE_SMOOTH );
						
						PanelPlateau.this.placerZone( ligCliquee, colCliquee, img );
						
						String idZone = "" + (char)('A' + indiceZone);
						PanelPlateau.this.ctrl.setZone( ligCliquee, colCliquee, idZone );
					}
					else // symbole ou pion couleur
					{
						int indice      = PanelPlateau.this.panelPara.getIndiceImageSelectionnee();
						boolean couleur = PanelPlateau.this.panelPara.getModeEstCouleur();
						ImageIcon icone = PanelPlateau.this.ctrl.getImageSelectionnee( indice, couleur );

						if ( icone != null )
						{
							if ( couleur && PanelPlateau.this.imagePresente( icone ) )
							{
								JOptionPane.showMessageDialog( PanelPlateau.this, "Cette couleur est déjà placée !" );
							}
							else
							{
								PanelPlateau.this.placerSymbole( ligCliquee, colCliquee, icone.getImage() );

								// On place toujours la cellule, couleur ou symbole
								char sym = (char) ('A' + indice);
								PanelPlateau.this.ctrl.placerCellule( ligCliquee, colCliquee, sym );
							}
						}
					}
				}
				// CLIC DROIT : Tout effacer sur cette case
				else if ( e.getButton() == MouseEvent.BUTTON3 ) 
				{
					PanelPlateau.this.placerSymbole( ligCliquee, colCliquee, null );
					PanelPlateau.this.placerZone   ( ligCliquee, colCliquee, null );

					PanelPlateau.this.ctrl.supprimerCellule( ligCliquee, colCliquee );
					PanelPlateau.this.ctrl.supprimerZone   ( ligCliquee, colCliquee );
				}
			}
		}
	}
}