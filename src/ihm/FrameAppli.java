package src.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import src.Controleur;

public class FrameAppli extends JFrame
{
	private Controleur ctrl;
	private PanelAppli panelAppli;
	private PanelPara  panelPara;

	public FrameAppli( Controleur ctrl )
	{
		this.ctrl = ctrl;
		this.panelAppli = new PanelAppli( ctrl );

		this.setTitle   ( "L'Armateur étranger " );
		this.setSize    ( 800, 800 );
		this.setLocation(  50, 200 );
		this.setLayout  ( new BorderLayout() );

		JPanel panelGrille = new JPanel( new GridLayout( 5,5 ) );

		for (int i = 0; i < 25; i++)
		{
			JPanel cell = new JPanel();
			cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			panelGrille.add(cell);
		}
		this.add( panelGrille, BorderLayout.CENTER );

		this.add( this.panelAppli, BorderLayout.SOUTH );

		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setVisible( true );
	}
}