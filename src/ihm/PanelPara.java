package src.ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane; // Permet d'afficher des messages
import javax.swing.JPanel;
import javax.swing.JTextField;

import src.Controleur;

public class PanelPara extends JPanel implements ActionListener
{
	private Controleur      ctrl;
	private FrameConception frameConception;
	private PanelPlateau    panelPlateau;

	private JPanel     panelCara;
	private JPanel     panelBouton;
	private JPanel     panelLabel;
	
	private JTextField txtTaillePlateauX;
	private JTextField txtTaillePlateauY;
	private JTextField txtNbCouleurs;
	private JTextField txtNbSymboles;

	private JButton    btnAnnuler;
	private JButton    btnValider;

	private JButton    img1;
	private JButton    img2;
	private JButton    img3;
	private JButton    img4;
	private JButton    img5;

	private static final Color[] COULEURS_ZONES = 
	{
		new Color( 206,203,246 ),
		new Color( 159,225,203 ),
		new Color( 250,199,179 ),
		new Color( 245,196,244 ),
		new Color( 181,212,151 ),
		new Color( 192,221,209 ),
	};

	public PanelPara( Controleur ctrl, PanelPlateau panelPlateau )
	{
		this.ctrl = ctrl;
		this.panelPlateau = panelPlateau; 
		this.setLayout( new GridLayout( 4,1 ) );

		/*-------------------------------*/
		/*    Création des composants    */
		/*-------------------------------*/
		this.panelCara         = new JPanel( new GridLayout( 8,1 ) );
		this.panelBouton       = new JPanel( new FlowLayout() );
		this.panelLabel        = new JPanel( new GridLayout( 4, 3, 10, 10 ) ); // espacement entre cellules

		this.txtTaillePlateauX = new JTextField( 5 );
		this.txtTaillePlateauY = new JTextField( 5 );
		this.txtNbCouleurs     = new JTextField( 5 );
		this.txtNbSymboles     = new JTextField( 5 );


		this.btnAnnuler        = new JButton( "Annuler " );
		this.btnValider        = new JButton( "Valider " );

		this.img1              = new JButton();
		this.img2              = new JButton();
		this.img3              = new JButton();
		this.img4              = new JButton();
		this.img5              = new JButton();

		// this.panelLabel .setLayout( new GridLayout( 4, 3, 5, 5 ) ); // 5px d'écart entre cellules
		// this.panelLabel .setPreferredSize( new Dimension( 300, 250 ) );

		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/

		this.panelCara  .add( new JLabel( "Lignes du plateau : " ) );
		this.panelCara  .add( this.txtTaillePlateauX );
		this.panelCara  .add( new JLabel( "Colonnes du plateau : " ) );
		this.panelCara  .add( this.txtTaillePlateauY );
		this.panelCara  .add( new JLabel( "Nombre de couleurs : ") );
		this.panelCara  .add( this.txtNbCouleurs    );
		this.panelCara  .add( new JLabel( "Nombre de symboles : ") );
		this.panelCara  .add( this.txtNbSymboles    );

		this.panelBouton.add( this.btnAnnuler );
		this.panelBouton.add( this.btnValider );

		this.panelLabel .add( this.img1  );
		this.panelLabel .add( this.img2  );
		this.panelLabel .add( this.img3  );
		this.panelLabel .add( this.img4  );
		this.panelLabel .add( this.img5  );

		this.add( this.panelCara   );
		this.add( this.panelLabel  );
		this.add( this.panelBouton ); 

		/*-------------------------------*/
		/*   Activation des composants   */
		/*-------------------------------*/
		this.btnAnnuler.addActionListener( this );
		this.btnValider.addActionListener( this );

		this.img1.addActionListener( this );
		this.img2.addActionListener( this );
		this.img3.addActionListener( this );
		this.img4.addActionListener( this );
		this.img5.addActionListener( this );

		//GereSouris gereSouris =  new GereSouris();
		//this.addMouseListener      ( gereSouris );
		//this.addMouseMotionListener( gereSouris );


		JButton[] btn    = { img1, img2, img3, img4, img5 };
		String[]  images = { "./Image/Symboles/apple.png", "./Image/Symboles/orange.png",  "./Image/Symboles/moto.png", "./Image/Symboles/pain.png", "./Image/Symboles/montre.png" };

		for (int i = 0; i < btn.length; i++) //symboles misent dans les labels
		{
			ImageIcon icon = new ImageIcon( images[i] );
			/*JCheckBox jcbTmp = new JCheckBox(icon);
			this.add(jcbTmp);
		*/
		
			Image img  = icon.getImage().getScaledInstance( 80, 80, Image.SCALE_SMOOTH );
			btn[i].setIcon( new ImageIcon( img ) );
			btn[i].setHorizontalAlignment( JLabel.CENTER );
			btn[i].setVerticalAlignment  ( JLabel.CENTER );
			btn[i].setPreferredSize      ( new Dimension( 100, 100 ) ); // taille fixe du label
			btn[i].setBorder( BorderFactory.createLineBorder( Color.LIGHT_GRAY ) ); // bordure autour de chaque image
		
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
			this.txtTaillePlateauX.setText( "" );
			this.txtTaillePlateauY.setText( "" );
			this.txtNbCouleurs    .setText( "" );
			this.txtNbSymboles    .setText( "" );
		}

		if ( e.getSource() == this.btnValider )
		{
			try
			{
				int nbCouleurs = Integer.parseInt( this.txtNbCouleurs    .getText() );
				int nbSymboles = Integer.parseInt( this.txtNbSymboles    .getText() );
				int lig        = Integer.parseInt( this.txtTaillePlateauX.getText() );
				int col        = Integer.parseInt( this.txtTaillePlateauY.getText() );

				this.ctrl.setParametres( nbCouleurs, nbSymboles );
				this.panelPlateau.setPlateau( lig, col );
			}
			catch ( NumberFormatException ex )
			{
            	JOptionPane.showMessageDialog( this, "Entrez des nombres valides !" );
        	}

			if ( this.txtTaillePlateauX.getText().isEmpty() || this.txtTaillePlateauY.getText().isEmpty() ||
				 this.txtNbCouleurs    .getText().isEmpty() || this.txtNbSymboles    .getText().isEmpty()    )
			{
				System.out.println( "Erreur" ); // à compléter
			}
		}
	}

	/*private class GereSouris extends MouseAdapter
	{
		private Integer numSymbole = null;
		private int     posX, posY;

		public void mousePressed( MouseEvent e )
		{
			this.posX = e.Cellule.getX();
			this.posY = e.Cellule.getY();
			this.numSymbole = PanelPara.this.ctrl.getIndiceSymbole( this.posX, this.posY );
		}

		public void mouseDragged( MouseEvent e)
		{
			if ( numSymbole != null )
			{
				PanelPara.this.ctrl.deplacerSymbole( this.numSymbole, e.getX() - this.posX, e.getY() - this.posY );
				this.posX = e.getX();
				this.posY = e.getY();

				PanelPara.this.repaint();
			}
		}
	}*/
}