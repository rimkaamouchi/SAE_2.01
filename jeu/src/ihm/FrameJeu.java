package jeu.src.ihm;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import jeu.src.ControleurJeu;

public class FrameJeu extends JFrame
{
	private ControleurJeu ctrl;
	private PanelJeu      panelJeu;

	public FrameJeu( ControleurJeu ctrl )
	{
		this.ctrl = ctrl;

		this.setTitle        ( "L'aventurier étranger" );
		this.setExtendedState( JFrame.MAXIMIZED_BOTH   );
		this.setLayout       ( new BorderLayout()      );

		this.panelJeu = new PanelJeu( ctrl );
		this.panelJeu.setPlateau( ctrl.getTaillePlateauX(), ctrl.getTaillePlateauY() );

		this.add( this.panelJeu );

		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setVisible( true );
	}
}