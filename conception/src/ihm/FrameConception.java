package conception.src.ihm;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import conception.src.ControleurConception;

public class FrameConception extends JFrame
{
	private ControleurConception   ctrl;
	private PanelPara              panelPara;
	private PanelDimension         panelDimension;
	private PanelZone              panelZone;
	private PanelSymbole           panelSymbole;
	private PanelPlateau           panelPlateau;

	public PanelPara getPanelPara() 
	{
		return this.panelPara;
	}

	public FrameConception( ControleurConception ctrl )
	{
		this.ctrl = ctrl;

		this.panelPlateau   = new PanelPlateau  ( ctrl );
		this.panelPara      = new PanelPara     ( ctrl );
		this.panelDimension = new PanelDimension( ctrl, this.panelPara, this.panelPlateau );
		this.panelZone      = new PanelZone     ( ctrl, this.panelPara);
		this.panelSymbole   = new PanelSymbole  ( ctrl, this.panelPara);

		this.panelPara     .setPanelDimension( this.panelDimension );
		this.panelPara     .setPanelZone     ( this.panelZone      );
		this.panelPara     .setPanelSymbole  ( this.panelSymbole   );
		this.panelPlateau  .setPanelPara     ( this.panelPara      );
		this.panelDimension.setPanelSymbole  ( this.panelSymbole   );
		this.panelPara.init();

		JSplitPane splitPane = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT, this.panelPara, this.panelPlateau ); //1
		splitPane.setDividerLocation( 430 );

		this.setTitle        ( "L'Armateur étranger " );
		this.setExtendedState( JFrame.MAXIMIZED_BOTH  );
		this.setLayout       ( new BorderLayout()     );

		this.add( this.panelPara, BorderLayout.WEST   );
		this.add( splitPane,      BorderLayout.CENTER );

		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setVisible( true );
	}
}

/* 1: JSplitPane sert à délimiter les panels entre eux, donc il n'y a pas besoin de faire des add et de BorderLayout.
Sinon, ça fera comme si on l'a mit en double.*/
