package src.ihm;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import src.Controleur;

public class FrameConception extends JFrame
{
	private Controleur   ctrl;
	private PanelPlateau panelPlateau;
	private PanelPara    panelPara;

	public FrameConception( Controleur ctrl )
	{
		this.ctrl = ctrl;
		this.panelPlateau = new PanelPlateau( ctrl );
		this.panelPara    = new PanelPara( ctrl, this.panelPlateau ); 


		JSplitPane splitPane = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT, this.panelPara, this.panelPlateau ); //1
		splitPane.setDividerLocation( 430 );

		this.setTitle   ( "L'Armateur étranger " );
		this.setSize    ( 800, 800 );
		this.setLocation(  50, 200 );
		this.setLayout  ( new BorderLayout() );
		this.setBackground( Color.ORANGE ); // test

		/*JPanel panelGrille = new JPanel( new GridLayout( 5,5 ) );

		for (int i = 0; i < 25; i++)
		{
			JPanel cell = new JPanel();
			cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			panelGrille.add(cell);
		}
		this.panelPlateau.add( panelGrille );*/

		//this.panelPara.add(this.zone);
		this.add( this.panelPara,    BorderLayout.WEST );
		//this.add( this.panelPlateau, BorderLayout.EAST );
		this.add( splitPane, BorderLayout.CENTER );

		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setVisible( true );
	}
}

/* 1: JSplitPane sert à délimiter les panels entre eux, donc il n'y a pas besoin de faire des add et de BorderLayout.
Sinon, ça fera comme si on l'a mit en double.*/
