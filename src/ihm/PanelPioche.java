package src.ihm;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.Controleur;

public class PanelPioche extends JPanel
{
	private Controleur ctrl;

	private JPanel panelLight;
	private JPanel panelDark;

	private JLabel carteL1;
	private JLabel carteL2;
	private JLabel carteL3;
	private JLabel carteL4;
	private JLabel carteL5;
	private JLabel carteL6;
	private JLabel carteL7;
	private JLabel carteL8;
	private JLabel carteL9;
	private JLabel carteL10;
	private JLabel carteL11;
	private JLabel carteL12;

	private JLabel carteD1;
	private JLabel carteD2;
	private JLabel carteD3;
	private JLabel carteD4;
	private JLabel carteD5;
	private JLabel carteD6;
	private JLabel carteD7;
	private JLabel carteD8;
	private JLabel carteD9;
	private JLabel carteD10;
	private JLabel carteD11;
	private JLabel carteD12;


	public PanelPioche( FramePioche framePioche )
	{
		/*-------------------------------*/
		/*    Création des composants    */
		/*-------------------------------*/
		this.panelLight = new JPanel();
		this.panelDark  = new JPanel();
		//this.panelImage.setLayout( new GridLayout( 4, 3, 10, 10 ) );

		this.carteL1  = new JLabel();
		this.carteL2  = new JLabel();
		this.carteL3  = new JLabel();
		this.carteL4  = new JLabel();
		this.carteL5  = new JLabel();
		this.carteL6  = new JLabel();
		this.carteL7  = new JLabel();
		this.carteL8  = new JLabel();
		this.carteL9  = new JLabel();
		this.carteL10 = new JLabel();
		this.carteL11 = new JLabel();
		this.carteL12 = new JLabel();

		this.carteD1  = new JLabel();
		this.carteD2  = new JLabel();
		this.carteD3  = new JLabel();
		this.carteD4  = new JLabel();
		this.carteD5  = new JLabel();
		this.carteD6  = new JLabel();
		this.carteD7  = new JLabel();
		this.carteD8  = new JLabel();
		this.carteD9  = new JLabel();
		this.carteD10 = new JLabel();
		this.carteD11 = new JLabel();
		this.carteD12 = new JLabel();


		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/
		this.panelLight.add( this.carteL1  );
		this.panelLight.add( this.carteL2  );
		this.panelLight.add( this.carteL3  );
		this.panelLight.add( this.carteL4  );
		this.panelLight.add( this.carteL5  );
		this.panelLight.add( this.carteL6  );
		this.panelLight.add( this.carteL7  );
		this.panelLight.add( this.carteL8  );
		this.panelLight.add( this.carteL9  );
		this.panelLight.add( this.carteL10 );
		this.panelLight.add( this.carteL11 );
		this.panelLight.add( this.carteL12 );

		this.panelDark .add( this.carteD1  );
		this.panelDark .add( this.carteD2  );
		this.panelDark .add( this.carteD3  );
		this.panelDark .add( this.carteD4  );
		this.panelDark .add( this.carteD5  );
		this.panelDark .add( this.carteD6  );
		this.panelDark .add( this.carteD7  );
		this.panelDark .add( this.carteD8  );
		this.panelDark .add( this.carteD9  );
		this.panelDark .add( this.carteD10 );
		this.panelDark .add( this.carteD11 );
		this.panelDark .add( this.carteD12 );

		this.add( this.panelLight );
		this.add( this.panelDark  );

		/*-------------------------------*/
		/*   Activation des composants   */
		/*-------------------------------*/

		JLabel[] labels = { carteL1, carteL2, carteL3, carteL4, carteL5, carteL6, carteL7, carteL8, carteL9, carteL10, carteL11, carteL12, 
							carteD1, carteD2, carteD3, carteD4, carteD5, carteD6, carteD7, carteD8, carteD9, carteD10, carteD11, carteD12 };
		String[] images = { "./Image/carte/light/algerie.png", "./Image/carte/light/allemagne.png", "./Image/carte/light/antarctique.png", "./Image/carte/light/australie.png", "./Image/carte/light/bresil.png", "./Image/carte/light/chine.png", "./Image/carte/light/france.PNG", "./Image/carte/light/japon.png", "./Image/carte/light/maroc.png", "./Image/carte/light/usa.png", "./Image/carte/light/suisse.png", "./Image/carte/light/suisse.png",
							"./Image/carte/dark/algerie.png" , "./Image/carte/dark/allemagne.png" , "./Image/carte/dark/antarctique.png" , "./Image/carte/dark/australie.png" , "./Image/carte/dark/bresil.png" , "./Image/carte/dark/chine.png" , "./Image/carte/dark/france.PNG" , "./Image/carte/dark/japon.png" , "./Image/carte/dark/maroc.png" , "./Image/carte/dark/usa.png" , "./Image/carte/dark/suisse.png" , "./Image/carte/dark/suisse.png"   };

		for (int i = 0; i < labels.length; i++)
		{
			ImageIcon icon  = new ImageIcon( images[i] );
			Image     carte = icon.getImage().getScaledInstance( 80, 55, Image.SCALE_SMOOTH );
			labels[i].setIcon( new ImageIcon( carte ) );
			labels[i].setHorizontalAlignment( JLabel.CENTER );
			labels[i].setVerticalAlignment  ( JLabel.CENTER );
			// labels[i].setBorder( BorderFactory.createLineBorder( Color.LIGHT_GRAY ) ); // bordure autour de chaque image
		}
	}
}