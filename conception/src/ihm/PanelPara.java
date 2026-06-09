package conception.src.ihm;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

	public  JPanel   panelBoutonScan;
    
	private JButton  btnSauvegarder;

	public  int      indiceZoneSelectionnee  = -1; 
	public  int      indiceImageSelectionnee =  0;
	public  boolean  modeEstCouleur          = false;

    public PanelPara( ControleurConception ctrl )
	{
        this.ctrl = ctrl;
        
		this.setLayout( new GridLayout( 4,1 ) );

        /*-------------------------------*/
		/*    Création des composants    */
		/*-------------------------------*/
		this.panelBoutonScan   = new JPanel( new FlowLayout() );
		this.btnSauvegarder    = new JButton( "Sauvegarder" );

		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/
		
		//this.add( this.panelDimension );
		//this.add( this.panelZone       );
		//this.add( this.panelSymbole    );
		this.add( this.panelBoutonScan );

		/*-------------------------------*/
		/*   Activation des composants   */
		/*-------------------------------*/		
		this.btnSauvegarder.addActionListener( this );

        /*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/
		this.panelBoutonScan.add( this.btnSauvegarder );

		//this.panelBoutonScan.setVisible( false );
	}

	public void actionPerformed( ActionEvent e )
	{
		if ( e.getSource() == this.btnSauvegarder )
		{
			if ( !this.ctrl.toutesLesCasesOntUneZone() )
			{
				JOptionPane.showMessageDialog( this,
					"Toutes les cases du plateau doivent avoir une zone assignée !",
					"Erreur - Cases sans zone",
					JOptionPane.ERROR_MESSAGE );
				return;
			}

			if ( !this.ctrl.validerToutesLesZones() )
			{
				JOptionPane.showMessageDialog( this,
					"Certaines zones ne sont pas continues !",
					"Erreur - Zones discontinues",
					JOptionPane.ERROR_MESSAGE );
				return;
			}

			this.ctrl.actionSauvegarder();
			JOptionPane.showMessageDialog( this,
				"Sauvegarde réussie !",
				"Succès",
				JOptionPane.INFORMATION_MESSAGE );
		}
	}

	public void init()
	{
		this.add( this.panelDimension  );
		this.add( this.panelZone       );
		this.add( this.panelSymbole    );
		this.add( this.panelBoutonScan );
		this.revalidate();
		this.repaint();
	}

	//accesseurs
	public int     getIndiceImageSelectionnee() { return this.indiceImageSelectionnee;        }
	public boolean getModeEstCouleur()          { return this.modeEstCouleur;                 }
	public int     getIndiceZoneSelectionnee()  { return this.indiceZoneSelectionnee;         }
	public String  getNomPlateau()              { return this.panelDimension.getNomPlateau(); }

	//modificateurs
	public void setPanelZone     ( PanelZone      panelZone      ) { this.panelZone      = panelZone;      }
	public void setPanelSymbole  ( PanelSymbole   panelSymbole   ) { this.panelSymbole   = panelSymbole;   }
	public void setPanelDimension( PanelDimension panelDimension ) { this.panelDimension = panelDimension; }
}