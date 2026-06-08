package conception.src.ihm;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import conception.src.ControleurConception;

public class FrameConception extends JFrame
{
	private ControleurConception   ctrl;
	private PanelPlateau           panelPlateau;
	private PanelPara              panelPara;

	public FrameConception( ControleurConception ctrl )
	{
		this.ctrl = ctrl;

		this.panelPlateau = new PanelPlateau( ctrl );
		this.panelPara    = new PanelPara( ctrl, this.panelPlateau ); 
		this.panelPlateau.setPanelPara( this.panelPara );

		JSplitPane splitPane = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT, this.panelPara, this.panelPlateau ); //1
		splitPane.setDividerLocation( 430 );

		this.setTitle   ( "L'Armateur étranger " );
		this.setSize    ( 800, 800 );
		this.setLocation(  20, 0 );
		this.setLayout  ( new BorderLayout() );
		//this.setBackground( Color.ORANGE ); // test

		this.add( this.panelPara, BorderLayout.WEST   );
		this.add( splitPane,      BorderLayout.CENTER );

		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setVisible( true );
	}

	public PanelPara getPanelPara() 
	{
		return this.panelPara;
	}
}

/* 1: JSplitPane sert à délimiter les panels entre eux, donc il n'y a pas besoin de faire des add et de BorderLayout.
Sinon, ça fera comme si on l'a mit en double.*/
