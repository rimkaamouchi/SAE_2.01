package jeu.src.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelAccueil extends JPanel
{
	private FrameAccueil frameAccueil;
	private JButton      btnAccueil;
	private JLabel       lblTitre;
	private Image        imageDeFond;

	public PanelAccueil( FrameAccueil frameAccueil )
	{
		this.frameAccueil = frameAccueil;
		
		this.setLayout( new BorderLayout() );

		ImageIcon icon = new ImageIcon("Accueil.png");
		this.imageDeFond = icon.getImage();

		/*-------------------------------*/
		/* Création des composants    */
		/*-------------------------------*/
		this.lblTitre   = new JLabel("L'ARMATEUR ÉTRANGER", JLabel.CENTER);
		this.btnAccueil = new JButton("Planifier Route");

		this.lblTitre.setFont(new Font("Serif", Font.BOLD, 36));
		this.lblTitre.setForeground(Color.WHITE);


		this.btnAccueil.setPreferredSize(new Dimension(200, 50));

		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/
		this.add( this.lblTitre,   BorderLayout.NORTH  );
		
		JPanel panelBoutonCentrer = new JPanel();
		panelBoutonCentrer.setOpaque(false); // Rend le panel transparent pour voir l'image derrière
		panelBoutonCentrer.add(this.btnAccueil);
		
		this.add( panelBoutonCentrer, BorderLayout.SOUTH );

		/*-------------------------------*/
		/* Activation des composants   */
		/*-------------------------------*/	
		//  ajouteras un actionListener pour basculer vers FrameConception quand on clique 
	}

	@Override
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		// Dessine l'image de fond 
		if (this.imageDeFond != null) 
		{
			g.drawImage(this.imageDeFond, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}
}