package src.ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.Controleur;

public class PanelAppli extends JPanel implements ActionListener
{
	private Controleur  ctrl;
	private FramePioche framePioche;

	private JLabel[][]  tabLblCase;

	private JButton     btnPioche;

	public PanelAppli( Controleur ctrl )
	{
		this.ctrl = ctrl; 

		/*-------------------------------*/
		/*    Création des composants    */
		/*-------------------------------*/
		this.btnPioche = new JButton( "Pioche" );

		this.tabLblCase = new JLabel[this.ctrl.getNbLigne()][this.ctrl.getNbColonne()];

		for(int lig = 0; lig <tabLblCase.length; lig++)
		{
			for(int col = 0; col <tabLblCase.length; col++)
			{
				this.tabLblCase[lig][col] = new JLabel();
			}
		}


		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/
		this.add( this.btnPioche );

		/*-------------------------------*/
		/*   Activation des composants   */
		/*-------------------------------*/
		this.btnPioche.addActionListener( this );
	
	}

	public void actionPerformed( ActionEvent e )
	{
		if ( e.getSource() == this.btnPioche )
		{
			if ( this.framePioche == null )
			{
				this.framePioche = new FramePioche( this.ctrl );
			}
			else
			{
				this.framePioche.setVisible( true );
			} 
		}
	}

}