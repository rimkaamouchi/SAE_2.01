package src.ihm;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import src.Controleur;

public class FramePioche extends JFrame
{
	private Controleur  ctrl;
	private PanelPioche panelPioche;

	public FramePioche( Controleur ctrl )
	{
		this.ctrl = ctrl;
		this.panelPioche = new PanelPioche( this );

		this.setTitle   ( "Pioche" );
		this.setSize    ( 500, 500 );
		this.setLocation(  20, 200 );
		this.setLayout  ( new BorderLayout() );

		this.add( this.panelPioche);

		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setVisible( true );
	}
}