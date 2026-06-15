package jeu.src.ihm;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import jeu.src.ControleurJeu;

public class FrameJeu extends JFrame
{
	private ControleurJeu ctrl;
	private PanelJeu      panelJeu;
	private PanelPioche   panelPioche;
	private PanelPoint    panelPoint;
	//private PanelDefausse panelDefausse;

	//getter
	public PanelJeu   getPanelJeu()    { return this.panelJeu;    }
	public PanelPoint getPanelPoint()  { return this.panelPoint;  }
	public PanelPioche getPanelPioche(){ return this.panelPioche; }

	public FrameJeu( ControleurJeu ctrl )
	{
		this.ctrl = ctrl;

		ctrl.setFrameJeu( this );

		this.setTitle        ( "L'armateur étranger" );
		this.setExtendedState( JFrame.MAXIMIZED_BOTH );
		this.setLayout       ( new BorderLayout()    );
		this.setJMenuBar     ( new MenuBar( ctrl )   );

		this.panelJeu    = new PanelJeu   ( ctrl     );
		this.panelPioche = new PanelPioche( ctrl     );
		this.panelPoint  = new PanelPoint (this, ctrl);
		
		this.panelJeu.setPlateau( ctrl.getTaillePlateauX(), ctrl.getTaillePlateauY() );

		this.add( this.panelPioche, BorderLayout.WEST   );
		this.add( this.panelJeu,    BorderLayout.CENTER );
		this.add( this.panelPoint,  BorderLayout.SOUTH  );

		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setVisible( true );
	}

	//remets à jour l'ihm des cartes
	public void resetGraphique()
	{
		// On demande au panelPioche de se réinitialiser graphiquement
		this.panelPioche.reset();
		// On demande aussi au plateau de jeu de se redessiner pour effacer les anciens chemins
		this.panelJeu.repaint();
	}
}