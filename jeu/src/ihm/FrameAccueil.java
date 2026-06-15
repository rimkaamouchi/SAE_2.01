package jeu.src.ihm;

import javax.swing.JFrame;

public class FrameAccueil extends JFrame
{
	private PanelAccueil panelAccueil; 

	public FrameAccueil()
	{
		this.setTitle        ( "L'Armateur étranger - Accueil" );
		this.setExtendedState( JFrame.MAXIMIZED_BOTH       ); // Plein écran
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		// Initialisation et ajout du panel d'accueil
		this.panelAccueil = new PanelAccueil( this );
		this.add( this.panelAccueil );

		this.setVisible( true );
	}

	// Un petit main pour tester directement ton écran d'accueil
	public static void main(String[] args) 
	{
		new FrameAccueil();
	}
}