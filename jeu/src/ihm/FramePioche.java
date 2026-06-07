package jeu.src.ihm;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import jeu.src.ControleurJeu;

public class FramePioche extends JFrame
{
	private ControleurJeu  ctrl;
	private PanelPioche panelPioche;

	public FramePioche( ControleurJeu ctrl )
	{
		this.ctrl = ctrl;
		this.panelPioche = new PanelPioche( this );

		this.setTitle   ( "Pioche" );
		this.setSize    ( 500, 500 );
		this.setLocation(  30, 200 );
		this.setLayout  ( new BorderLayout() );

		this.add( this.panelPioche );

		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setVisible( true );
	}
}