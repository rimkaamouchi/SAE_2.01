package src.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import src.Controleur;

public class PanelPlateau extends JPanel implements ActionListener
{
	private Controleur  ctrl;
	private FramePioche framePioche;

	private int nbLig;
	private int nbCol;

	private JButton     btnPioche;

	public PanelPlateau( Controleur ctrl )
	{
		this.ctrl = ctrl;
		this.nbLig = nbLig;
		this.nbCol = nbCol;

		/*-------------------------------*/
		/*    Création des composants    */
		/*-------------------------------*/
		this.btnPioche = new JButton( "Pioche" );

		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/
		this.add( this.btnPioche, BorderLayout.SOUTH );

		/*-------------------------------*/
		/*   Activation des composants   */
		/*-------------------------------*/
		this.btnPioche.addActionListener( this );
	
	}

	public void setPlateau(int nbLig, int nbCol)
	{
		this.nbLig = nbLig;
		this.nbCol = nbCol;
		this.repaint(); // redessine le plateau
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

	public void actionPerformed( ActionEvent e )
	{
		if ( e.getSource() == this.btnPioche )
		{
			if ( this.framePioche == null )
			{
				this.framePioche = new FramePioche( this.ctrl );
			}
			else
			{
				this.framePioche.setVisible( true );
			} 
		}
	}

}