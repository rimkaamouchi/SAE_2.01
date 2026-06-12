package jeu.src.ihm;

import javax.swing.JLabel;
import javax.swing.JPanel;

import jeu.src.ControleurJeu;

public class PanelPioche extends JPanel
{
	private ControleurJeu ctrl;

	private JLabel lblCarte;

	public PanelPioche( ControleurJeu ctrl )
	{
		this.ctrl = ctrl;

		/*-------------------------------*/
		/*    Création des composants    */
		/*-------------------------------*/
		//this.lblCarte = new JLabel( ctrl.getImageCarte() );		


		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/

		/*-------------------------------*/
		/*   Activation des composants   */
		/*-------------------------------*/
		/*String[] images = {  "./Image/carte/light/france.PNG", "./Image/carte/light/japon.png", "./Image/carte/light/maroc.png", "./Image/carte/light/usa.png", "./Image/carte/light/suisse.png", "./Image/carte/light/suisse.png",
							 "./Image/carte/dark/france.PNG" , "./Image/carte/dark/japon.png" , "./Image/carte/dark/maroc.png" , "./Image/carte/dark/usa.png" , "./Image/carte/dark/suisse.png" , "./Image/carte/dark/suisse.png"   };

		for (int i = 0; i < labels.length; i++)
		{
			ImageIcon icon  = new ImageIcon( images[i] );
			Image     carte = icon.getImage().getScaledInstance( 80, 55, Image.SCALE_SMOOTH );
			labels[i].setIcon( new ImageIcon( carte ) );
			labels[i].setHorizontalAlignment( JLabel.CENTER );
			labels[i].setVerticalAlignment  ( JLabel.CENTER );
		}*/
	}
}