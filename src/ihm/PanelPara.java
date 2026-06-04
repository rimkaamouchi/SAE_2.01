package src.ihm;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import src.Controleur;

public class PanelPara extends JPanel implements ActionListener
{
	private Controleur ctrl;
	private FrameAppli frameAppli;
	private FramePara  framePara;

	private JPanel     panelCara;
	private JPanel     panelBouton;
	private JPanel     panelLabel;

	private JTextField txtTaillePlateau;
	private JTextField txtNbCouleurs;
	private JTextField txtNbSymboles;
	private JTextField txtTailleCase;

	private JButton    btnAnnuler;
	private JButton    btnValider;

	private JLabel     img1;
	private JLabel     img2;
	private JLabel     img3;
	private JLabel     img4;
	private JLabel     img5;
	private JLabel     img6;
	private JLabel     img7;
	private JLabel     img8;
	private JLabel     img9;
	private JLabel     img10;
	private JLabel     img11;
	private JLabel     img12;

	public PanelPara( Controleur ctrl )
	{
		this.ctrl = ctrl;
		this.setLayout( new GridLayout( 3,1 ) );

		/*-------------------------------*/
		/*    Création des composants    */
		/*-------------------------------*/
		this.panelCara        = new JPanel();
		this.panelBouton      = new JPanel();
		this.panelLabel       = new JPanel();

		this.txtTaillePlateau = new JTextField( 5 );
		this.txtNbCouleurs    = new JTextField( 5 );
		this.txtNbSymboles    = new JTextField( 5 );
		this.txtTailleCase    = new JTextField( 5 );

		this.btnAnnuler       = new JButton( "Annuler " );
		this.btnValider       = new JButton( "Valider " );

		this.img1             = new JLabel();
		this.img2             = new JLabel();
		this.img3             = new JLabel();
		this.img4             = new JLabel();
		this.img5             = new JLabel();
		this.img6             = new JLabel();
		this.img7             = new JLabel();
		this.img8             = new JLabel();
		this.img9             = new JLabel();
		this.img10            = new JLabel();
		this.img11            = new JLabel();
		this.img12            = new JLabel();


		this.panelCara  .setLayout( new GridLayout( 8,1 ) );
		this.panelBouton.setLayout( new FlowLayout() );
		this.panelLabel .setLayout( new GridLayout( 4, 3, 10, 10 ) ); // espacement entre cellules
		// this.panelLabel .setLayout( new GridLayout( 4, 3, 5, 5 ) ); // 5px d'écart entre cellules
		// this.panelLabel .setPreferredSize( new Dimension( 300, 250 ) );

		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/

		this.panelCara  .add( new JLabel( "Taille du plateau : " ) );
		this.panelCara  .add( this.txtTaillePlateau );
		this.panelCara  .add( new JLabel( "Nombre de couleurs : ") );
		this.panelCara  .add( this.txtNbCouleurs    );
		this.panelCara  .add( new JLabel( "Nombre de symboles : ") );
		this.panelCara  .add( this.txtNbSymboles    );
		this.panelCara  .add( new JLabel( "Taille des cases : "  ) );
		this.panelCara  .add( this.txtTailleCase    );

		this.panelBouton.add( this.btnAnnuler );
		this.panelBouton.add( this.btnValider );

		this.panelLabel .add( this.img1  );
		this.panelLabel .add( this.img2  );
		this.panelLabel .add( this.img3  );
		this.panelLabel .add( this.img4  );
		this.panelLabel .add( this.img5  );
		this.panelLabel .add( this.img6  );
		this.panelLabel .add( this.img7  );
		this.panelLabel .add( this.img8  );
		this.panelLabel .add( this.img9  );
		this.panelLabel .add( this.img10 );
		this.panelLabel .add( this.img11 );
		this.panelLabel .add( this.img12 );


		this.add( this.panelCara   );
		this.add( this.panelLabel  );
		this.add( this.panelBouton );

		/*-------------------------------*/
		/*   Activation des composants   */
		/*-------------------------------*/
		this.btnAnnuler.addActionListener( this );
		this.btnValider.addActionListener( this );


		JLabel[] labels = { img1, img2, img3, img4, img5, img6, img7, img8, img9, img10, img11, img12 };
		String[] images = { "./Image/Symboles/2.png", "./Image/Symboles/3.png", "./Image/Symboles/4.png", "./Image/Symboles/5.png", "./Image/Symboles/6.png", "./Image/Symboles/7.png", "./Image/Symboles/8.png", "./Image/Symboles/9.png", "./Image/Symboles/10.png", "./Image/Symboles/11.png", "./Image/Symboles/12.png", "./Image/Symboles/13.png" };

		for (int i = 0; i < labels.length; i++)
		{
			ImageIcon icon = new ImageIcon( images[i] );
			Image     img  = icon.getImage().getScaledInstance( 80, 55, Image.SCALE_SMOOTH );
			labels[i].setIcon( new ImageIcon( img ) );
			labels[i].setHorizontalAlignment( JLabel.CENTER );
			labels[i].setVerticalAlignment  ( JLabel.CENTER );
			// labels[i].setBorder( BorderFactory.createLineBorder( Color.LIGHT_GRAY ) ); // bordure autour de chaque image
		}

		/*String contenu;

		for (int i = 0; i < labels.length; i++)
		{
			ImageIcon icon = new ImageIcon(images[i]);
			Image     img  = icon.getImage().getScaledInstance( 150, 150, Image.SCALE_SMOOTH ); //bouge taille de image
			labels[i].setIcon( new ImageIcon( img ) );
			contenu = "<html><center>" + img.toString().replace("_","<br/>" ) + "</center></html>";
		}*/

	}


	public void actionPerformed( ActionEvent e )
	{
		if ( e.getSource() == this.btnAnnuler )
		{
			this.txtTaillePlateau.setText( "" );
			this.txtNbCouleurs   .setText( "" );
			this.txtNbSymboles   .setText( "" );
			this.txtTailleCase   .setText( "" );
		}

		if ( e.getSource() == this.btnValider )
		{
			if ( this.txtTaillePlateau.getText().isEmpty() || this.txtNbCouleurs.getText().isEmpty() ||
				this.txtNbSymboles   .getText().isEmpty() || this.txtTailleCase.getText().isEmpty()    )
			{   
				System.out.println( "Erreur" ); // à compléter
			}
			else
			{
				if ( this.frameAppli == null )
					this.frameAppli = new FrameAppli( this.ctrl );
				else
					this.frameAppli.setVisible( true );
			}
		}
	}
}