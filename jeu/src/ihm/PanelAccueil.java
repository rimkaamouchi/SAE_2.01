package jeu.src.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import jeu.src.ControleurJeu;

public class PanelAccueil extends JPanel implements ActionListener
{
	private ControleurJeu ctrl;
	private FrameJeu      frameJeu;

	private JLabel  lblTitre;
	private JButton btnJouer;
	private Image   imageDeFond;

	public PanelAccueil( ControleurJeu ctrl )
	{
		this.ctrl = ctrl;
		
		this.setLayout( new BorderLayout() );

		/*-------------------------------*/
		/* Création des composants       */
		/*-------------------------------*/
		JPanel panelBoutonCentrer = new JPanel();

		ImageIcon icon   = new ImageIcon( "jeu/src/Image/Accueil.png" );
		this.imageDeFond = icon.getImage();

		this.lblTitre    = new JLabel ( "L'ARMATEUR ÉTRANGER", JLabel.CENTER );
		this.btnJouer    = new JButton( "Lancer la partie" );

		this.lblTitre.setFont( new Font( "Serif", Font.BOLD, 36 ) );
		this.lblTitre.setForeground( Color.WHITE );

		this.btnJouer.setPreferredSize( new Dimension( 200, 50 ) );

		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/
		this.add( this.lblTitre, BorderLayout.NORTH  );
		
		panelBoutonCentrer.add( this.btnJouer );
		
		this.add( panelBoutonCentrer, BorderLayout.SOUTH );

		/*-------------------------------*/
		/*   Activation des composants   */
		/*-------------------------------*/	
		this.btnJouer.addActionListener( this );

		panelBoutonCentrer.setOpaque(false); // Rend le panel transparent pour voir l'image derrière
	}

	public void actionPerformed( ActionEvent e )
	{
		if ( e.getSource() == this.btnJouer )
		{
			if ( this.frameJeu == null )
				this.frameJeu = new FrameJeu( this.ctrl );
			else
				this.frameJeu.setVisible( true );			
		}
	}

	@Override
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		// Dessine l'image de fond 
		if (this.imageDeFond != null) 
		{
			g.drawImage( this.imageDeFond, 0, 0, this.getWidth(), this.getHeight(), this );
		}
	}
}