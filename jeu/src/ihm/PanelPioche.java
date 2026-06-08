package jeu.src.ihm;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import jeu.src.ControleurJeu;

public class PanelPioche extends JPanel
{
	private ControleurJeu ctrl;

	private JPanel panelLight;
	private JPanel panelDark;

	private JLabel carteL1;
	private JLabel carteL2;
	private JLabel carteL3;
	private JLabel carteL4;
	private JLabel carteL5;
	private JLabel carteL6;


	private JLabel carteD1;
	private JLabel carteD2;
	private JLabel carteD3;
	private JLabel carteD4;
	private JLabel carteD5;
	private JLabel carteD6;
	


	public PanelPioche( FramePioche framePioche )
	{
		/*-------------------------------*/
		/*    Création des composants    */
		/*-------------------------------*/

		this.panelLight = new JPanel();
		this.panelDark  = new JPanel();

		this.carteL1  = new JLabel();
		this.carteL2  = new JLabel();
		this.carteL3  = new JLabel();
		this.carteL4  = new JLabel();
		this.carteL5  = new JLabel();
		this.carteL6  = new JLabel();

		this.carteD1  = new JLabel();
		this.carteD2  = new JLabel();
		this.carteD3  = new JLabel();
		this.carteD4  = new JLabel();
		this.carteD5  = new JLabel();
		this.carteD6  = new JLabel();

		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/

		this.panelLight.add( this.carteL1  );
		this.panelLight.add( this.carteL2  );
		this.panelLight.add( this.carteL3  );
		this.panelLight.add( this.carteL4  );
		this.panelLight.add( this.carteL5  );
		this.panelLight.add( this.carteL6  );

		this.panelDark .add( this.carteD1  );
		this.panelDark .add( this.carteD2  );
		this.panelDark .add( this.carteD3  );
		this.panelDark .add( this.carteD4  );
		this.panelDark .add( this.carteD5  );
		this.panelDark .add( this.carteD6  );

		this.add( this.panelLight );
		this.add( this.panelDark  );

		/*-------------------------------*/
		/*   Activation des composants   */
		/*-------------------------------*/

		JLabel[] labels = { carteL1, carteL2, carteL3, carteL4, carteL5, carteL6,  
							carteD1, carteD2, carteD3, carteD4, carteD5, carteD6 };

		String[] images = {  "./Image/carte/light/france.PNG", "./Image/carte/light/japon.png", "./Image/carte/light/maroc.png", "./Image/carte/light/usa.png", "./Image/carte/light/suisse.png", "./Image/carte/light/suisse.png",
							 "./Image/carte/dark/france.PNG" , "./Image/carte/dark/japon.png" , "./Image/carte/dark/maroc.png" , "./Image/carte/dark/usa.png" , "./Image/carte/dark/suisse.png" , "./Image/carte/dark/suisse.png"   };

		for (int i = 0; i < labels.length; i++)
		{
			ImageIcon icon  = new ImageIcon( images[i] );
			Image     carte = icon.getImage().getScaledInstance( 80, 55, Image.SCALE_SMOOTH );
			labels[i].setIcon( new ImageIcon( carte ) );
			labels[i].setHorizontalAlignment( JLabel.CENTER );
			labels[i].setVerticalAlignment  ( JLabel.CENTER );
		}
	}
}