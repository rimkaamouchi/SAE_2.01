package controleur.src.ihm;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import conception.src.ControleurConception;

public class PanelPara extends JPanel implements ActionListener
{
    private ControleurConception      ctrl;
	private PanelZone                 panelZone;
	private PanelSymbole              panelSymbole;
	private PanelPlateau              panelPlateau;
	private PanelDimension            panelDimension;

	

	private JPanel     panelBoutonScan;
    
	private JButton    btnSauvegarder;

	
	public int         indiceZoneSelectionnee  = -1; 
	public int         indiceImageSelectionnee = 0;
	public boolean     modeEstCouleur          = false;

    public PanelPara( ControleurConception ctrl, PanelDimension panelDimension, PanelZone panelZone, PanelSymbole panelSymbole, PanelPlateau panelPlateau )
	{
        this.ctrl           = ctrl;
		this.panelDimension = panelDimension;
		this.panelZone      = panelZone;
		this.panelSymbole   = panelSymbole;
		this.panelPlateau   = panelPlateau;
        
		this.setLayout( new GridLayout( 5,1 ) );

        /*-------------------------------*/
		/*    Création des composants    */
		/*-------------------------------*/	
		this.panelBoutonScan   = new JPanel( new FlowLayout() );

		this.btnSauvegarder    = new JButton( "Sauvegarder" );

		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/
		this.add( this.panelDimension  );
		this.add( this.panelZone       );
		this.add( this.panelSymbole    );
		this.add( this.panelBoutonScan );

		/*-------------------------------*/
		/*   Activation des composants   */
		/*-------------------------------*/		
		this.btnSauvegarder.addActionListener( this );

        /*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/
		this.panelBoutonScan.add( this.btnSauvegarder );

		
	}

	public void actionPerformed( ActionEvent e )
	{
		if ( e.getSource() == this.btnSauvegarder )
		{
			boolean zonesValides   = this.ctrl.validerToutesLesZones();
			boolean plateauComplet = this.ctrl.plateauEstComplet();

			if ( !plateauComplet )
			{
				JOptionPane.showMessageDialog( this, "Certaines cellules n'ont pas de zone !" );
				return;
			}
			if ( !zonesValides )
			{
				JOptionPane.showMessageDialog( this, "Certaines zones ne sont pas continues !" );
				return;
			}

			this.ctrl.actionSauvegarder();
			JOptionPane.showMessageDialog( this, "Sauvegarde réussie !" );

			if ( e.getSource() == this.btnSauvegarder )
		{
			boolean zonesValides   = this.ctrl.validerToutesLesZones();
			boolean plateauComplet = this.ctrl.plateauEstComplet();

			if ( !plateauComplet )
			{
				JOptionPane.showMessageDialog( this, "Certaines cellules n'ont pas de zone !" );
				return;
			}
			if ( !zonesValides )
			{
				JOptionPane.showMessageDialog( this, "Certaines zones ne sont pas continues !" );
				return;
			}

			this.ctrl.actionSauvegarder();
			JOptionPane.showMessageDialog( this, "Sauvegarde réussie !" );
		}
		}
	}        
}