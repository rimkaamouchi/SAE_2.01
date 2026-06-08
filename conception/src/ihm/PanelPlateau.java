package conception.src.ihm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import conception.src.ControleurConception;

public class PanelPlateau extends JPanel implements MouseListener
{
	private ControleurConception  ctrl;
	private PanelPara panelPara;

	private Image[][] matriceImage;
	private int nbLig;
	private int nbCol;

	public PanelPlateau( ControleurConception ctrl )
	{
		this.ctrl = ctrl;
		this.nbLig = 0;
		this.nbCol = 0;

		/*-------------------------------*/
		/*    Création des composants    */
		/*-------------------------------*/

		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/

		/*-------------------------------*/
		/*   Activation des composants   */
		/*-------------------------------*/
		this.addMouseListener(this);

	}

	public void setPlateau(int nbLig, int nbCol)
	{
		this.nbLig = nbLig;
		this.nbCol = nbCol;

		this.matriceImage = new Image[nbLig][nbCol];

		this.repaint(); // redessine le plateau
	}

	public void setPanelPara( PanelPara panelPara )
	{
		this.panelPara = panelPara;
	}

	public void placerImage(int lig, int col, Image image)
	{
		if(lig >= 0 && lig < nbLig && col >= 0 && col < nbCol )
		{
			this.matriceImage[lig][col] = image;
			this.repaint();
		}
	}

	@Override // modifie une méthode dans JPanel
	public void paintComponent( Graphics g)
	{
		super.paintComponent(g);

		if ( this.nbLig == 0 || this.nbCol == 0 ) return;

		Graphics2D graph = (Graphics2D) g;
		graph.setColor(Color.BLACK);

		int largeurCase = this.getWidth()  / this.nbCol; // méthode JComponent: Renvoie largeur actuelle du composant
		int hauteurCase = this.getHeight() / this.nbLig; // méthode JComponent: Renvoie hauteur actuelle du composant

		/*----  Test Images ----*/
        if ( this.matriceImage != null )
        {
            for ( int i = 0; i < nbLig; i++ )
                for ( int j = 0; j < nbCol; j++ )
                    if ( this.matriceImage[i][j] != null )
                        graph.drawImage( this.matriceImage[i][j],
                                         j * largeurCase, i * hauteurCase,
                                         largeurCase, hauteurCase, this );
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

	/*---------------------------------------*/
    /* Méthodes de l'interface MouseListener */
    /*---------------------------------------*/

	@Override
	public void mousePressed( MouseEvent e )
	{
		if ( this.nbLig == 0 || this.nbCol == 0 ) return;
		if ( this.panelPara == null )             return;

		int largeurCase = this.getWidth()  / this.nbCol;
		int hauteurCase = this.getHeight() / this.nbLig;

		int colCliquee  = e.getX() / largeurCase;
		int ligCliquee  = e.getY() / hauteurCase;

		if ( ligCliquee >= 0 && ligCliquee < this.nbLig &&
			 colCliquee >= 0 && colCliquee < this.nbCol    )
		{
			int       indice  = this.panelPara.getIndiceImageSelectionnee();
			boolean   couleur = this.panelPara.getModeEstCouleur();
			ImageIcon icone   = this.ctrl.getImageSelectionnee( indice, couleur );

			if ( icone != null )
				this.placerImage( ligCliquee, colCliquee, icone.getImage() );
		}
	}

    @Override public void mouseClicked ( MouseEvent e ) {}
    @Override public void mouseReleased( MouseEvent e ) {}
    @Override public void mouseEntered ( MouseEvent e ) {}
    @Override public void mouseExited  ( MouseEvent e ) {}

}
