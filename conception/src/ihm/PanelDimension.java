package conception.src.ihm;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane; // Permet d'afficher des messages
import javax.swing.JPanel;
import javax.swing.JTextField;

import conception.src.ControleurConception;

public class PanelDimension extends JPanel implements ActionListener
{
	private ControleurConception      ctrl;
	private PanelPara                 panelPara;
	private PanelPlateau              panelPlateau;
	private PanelZone                 panelZone;
	private PanelSymbole              panelSymbole;

	private JPanel     panelCara;
	private JPanel     panelBouton;
	
	private JTextField txtNomPlateau;
	private JTextField txtTaillePlateauX;
	private JTextField txtTaillePlateauY;
	private JTextField txtNbSymboles;

	private JButton    btnAnnuler;
	private JButton    btnValider;


	public PanelDimension( ControleurConception ctrl, PanelPara panelPara, PanelPlateau panelPlateau )
	{
		this.ctrl         = ctrl;
		this.panelPara    = panelPara;
		this.panelPlateau = panelPlateau;
		this.setLayout( new BorderLayout() );

		/*-------------------------------*/
		/*    Création des composants    */
		/*-------------------------------*/		
		this.panelCara         = new JPanel( new GridLayout( 8, 1) );
		this.panelBouton       = new JPanel( new FlowLayout()    );

		this.txtNomPlateau     = new JTextField( );
		this.txtTaillePlateauX = new JTextField( );
		this.txtTaillePlateauY = new JTextField( );
		this.txtNbSymboles     = new JTextField( );

		this.btnAnnuler        = new JButton( "Annuler" );
		this.btnValider        = new JButton( "Valider" );
		
		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/
		this.panelCara  .add( new JLabel( " Nom du plateau : "     ) );
		this.panelCara  .add( this.txtNomPlateau                     );
		this.panelCara  .add( new JLabel( " Lignes : "             ) );
		this.panelCara  .add( this.txtTaillePlateauX                 );
		this.panelCara  .add( new JLabel( " Colonnes : "           ) );
		this.panelCara  .add( this.txtTaillePlateauY                 );
		this.panelCara  .add( new JLabel( " Nombre de symboles : " ) );
		this.panelCara  .add( this.txtNbSymboles                     );

		this.panelBouton.add( this.btnAnnuler  );
		this.panelBouton.add( this.btnValider  );


		this.add( this.panelCara,   BorderLayout.CENTER );
		this.add( this.panelBouton, BorderLayout.SOUTH  );

		this.panelCara.setBorder( BorderFactory.createTitledBorder( "Paramètres" ) );

		/*-------------------------------*/
		/*   Activation des composants   */
		/*-------------------------------*/
		this.btnAnnuler.addActionListener( this );
		this.btnValider.addActionListener( this );
	}

	public String getNomPlateau() { return this.txtNomPlateau.getText(); }

	public void actionPerformed( ActionEvent e )
	{
		if ( e.getSource() == this.btnAnnuler )
		{
			this.txtNomPlateau    .setText( "" );
			this.txtTaillePlateauX.setText( "" );
			this.txtTaillePlateauY.setText( "" );
			this.txtNbSymboles    .setText( "" );
		}

		if ( e.getSource() == this.btnValider )
		{
			try
			{
				int nbSymboles = Integer.parseInt( this.txtNbSymboles    .getText() );
				int lig        = Integer.parseInt( this.txtTaillePlateauX.getText() );
				int col        = Integer.parseInt( this.txtTaillePlateauY.getText() );

				this.ctrl.setParametres( lig, col, nbSymboles);
				this.panelPlateau.setPlateau( lig, col );
				this.panelSymbole.Symbole( nbSymboles );
			}
			catch ( NumberFormatException ex )
			{
				JOptionPane.showMessageDialog( this, "Entrez des nombres valides !" );
			}

			//this.panelZone      .setVisible( true );
			//this.panelSymbole   .setVisible( true );
			//this.panelPara.panelBoutonScan.setVisible( true );
		}
	}

	public void setPanelSymbole( PanelSymbole panelSymbole ) { this.panelSymbole = panelSymbole; }
}