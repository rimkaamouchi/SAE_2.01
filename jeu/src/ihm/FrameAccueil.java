package jeu.src.ihm;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import jeu.src.ControleurJeu;

public class FrameAccueil extends JFrame
{
	private ControleurJeu ctrl;
	private PanelAccueil  panelAccueil; 

	public FrameAccueil( ControleurJeu ctrl )
	{
		this.ctrl = ctrl;

		this.setTitle        ( "L'Armateur étranger - Accueil" );
		this.setExtendedState( JFrame.MAXIMIZED_BOTH       ); // Plein écran
		this.setLayout       ( new BorderLayout() );

		this.panelAccueil = new PanelAccueil( ctrl );
		this.add( this.panelAccueil );

		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setVisible( true );
	}
}