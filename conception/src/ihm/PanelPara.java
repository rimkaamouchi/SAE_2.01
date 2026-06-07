package conception.src.ihm;

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

import conception.src.ControleurConception;

public class PanelPara extends JPanel implements ActionListener
{
	private ControleurConception      ctrl;
	private PanelPlateau              panelPlateau;

	private JPanel     panelCara;
	private JPanel     panelBouton;
	private JPanel     panelSymbole;
	
	private JTextField txtTaillePlateauX;
	private JTextField txtTaillePlateauY;
	private JTextField txtNbCouleurs;
	private JTextField txtNbSymboles;

	private JButton    btnAnnuler;
	private JButton    btnValider;

	private int indiceImageSelectionnee = 0;
	private boolean modeEstCouleur      = false;

	private static final Color[] COULEURS_ZONES = 
	{
		new Color( 206,203,246 ),
		new Color( 159,225,203 ),
		new Color( 250,199,179 ),
		new Color( 245,196,244 ),
		new Color( 181,212,151 ),
		new Color( 192,221,209 ),
	};

	public PanelPara( ControleurConception ctrl, PanelPlateau panelPlateau )
	{
		this.ctrl = ctrl;
		this.panelPlateau = panelPlateau; 
		this.setLayout( new GridLayout( 4,1 ) );

		/*-------------------------------*/
		/*    Création des composants    */
		/*-------------------------------*/
		this.panelCara         = new JPanel( new GridLayout( 8,1 ) );
		this.panelBouton       = new JPanel( new FlowLayout() );
		this.panelSymbole      = new JPanel( new GridLayout( 4, 3, 10, 10 ) ); // espacement entre cellules

		this.txtTaillePlateauX = new JTextField( 5 );
		this.txtTaillePlateauY = new JTextField( 5 );
		this.txtNbCouleurs     = new JTextField( 5 );
		this.txtNbSymboles     = new JTextField( 5 );

		this.btnAnnuler        = new JButton( "Annuler" );
		this.btnValider        = new JButton( "Valider" );
		
		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/
		this.panelCara   .add( new JLabel( "Lignes du plateau : "   ) );
		this.panelCara   .add( this.txtTaillePlateauX                 );
		this.panelCara   .add( new JLabel( "Colonnes du plateau : " ) );
		this.panelCara   .add( this.txtTaillePlateauY                 );
		this.panelCara   .add( new JLabel( "Nombre de couleurs : "  ) );
		this.panelCara   .add( this.txtNbCouleurs                     );
		this.panelCara   .add( new JLabel( "Nombre de symboles : "  ) );
		this.panelCara   .add( this.txtNbSymboles                     );

		this.panelBouton .add( this.btnAnnuler );
		this.panelBouton .add( this.btnValider );

		this.add( this.panelCara    );
		this.add( this.panelBouton  );
		this.add( this.panelSymbole );

		/*-------------------------------*/
		/*   Activation des composants   */
		/*-------------------------------*/
		this.btnAnnuler.addActionListener( this );
		this.btnValider.addActionListener( this );

	}


	/*---- Mise à jour des symboles ----*/
	public void Symbole( int nbSymbole )
	{
		this.panelSymbole.removeAll();
		this.panelSymbole.setLayout( new GridLayout( 4, 3, 10, 10) );

		String[]  imagesSymbole = { "conception/src/Image/Symboles/apple.png",
		                    		"conception/src/Image/Symboles/orange.png",
									"conception/src/Image/Symboles/moto.png",
									"conception/src/Image/Symboles/pain.png",
									"conception/src/Image/Symboles/cafe.png"
								  };
							
		String[]  imagesCouleur = { "conception/src/Image/Symboles/bordeaux.png",
		                     		"conception/src/Image/Symboles/rose.png",
									"conception/src/Image/Symboles/bleu_fonce.png",
									"conception/src/Image/Symboles/marron.png",
									"conception/src/Image/Symboles/bleu.png"
								  };

		if ( nbSymbole > 5 )
		{
			JOptionPane.showMessageDialog( this, "Les maximums de boutons à été atteint." );
		}

		// tableaux à envoyer vers le contrôleur
		ImageIcon[] tabIconSymbole = new ImageIcon[nbSymbole];
    	ImageIcon[] tabIconCouleur = new ImageIcon[nbSymbole];

		for ( int i = 0; i < nbSymbole; i++ ) //symboles misent dans les labels
		{
			/*----- Boutons neutres -----*/
			ImageIcon iconSymbole  = new ImageIcon( imagesSymbole[i] );
			JButton   btnSymbole   = new JButton();
			Image     imgS         = iconSymbole.getImage().getScaledInstance( 80, 80, Image.SCALE_SMOOTH );
			ImageIcon finalSymbole = new ImageIcon( imgS ); //Pour ctrl

			tabIconSymbole[i] = finalSymbole;

			btnSymbole.setIcon( new ImageIcon( imgS ) );
			btnSymbole.setHorizontalAlignment( JLabel.CENTER );
			btnSymbole.setVerticalAlignment  ( JLabel.CENTER );
			btnSymbole.setPreferredSize      ( new Dimension( 100, 100 ) ); // taille fixe du label
			btnSymbole.setBorder( BorderFactory.createLineBorder( Color.LIGHT_GRAY ) ); // bordure autour de chaque image

			this.panelSymbole.add( btnSymbole );


			/*----- Boutons Couleurs -----*/
			ImageIcon icon         = new ImageIcon( imagesCouleur[i] );
			JButton   btnCouleur   = new JButton();
			Image     imgC         = icon.getImage().getScaledInstance( 80, 80, Image.SCALE_SMOOTH );
			ImageIcon finalCouleur = new ImageIcon( imgC ); //Pour ctrl

			tabIconCouleur[i] = finalCouleur;

			btnCouleur.setIcon( new ImageIcon( imgC ) );
			btnCouleur.setHorizontalAlignment( JLabel.CENTER );
			btnCouleur.setVerticalAlignment  ( JLabel.CENTER );
			btnCouleur.setPreferredSize      ( new Dimension( 100, 100 ) ); // taille fixe du label
			btnCouleur.setBorder( BorderFactory.createLineBorder( Color.LIGHT_GRAY ) ); // bordure autour de chaque image

			this.panelSymbole.add( btnCouleur );

			final int indice = i;
									btnSymbole.addActionListener( ev -> { this.indiceImageSelectionnee = indice;
                                      this.modeEstCouleur          = false; } );

									btnCouleur.addActionListener( ev -> { this.indiceImageSelectionnee = indice;
                                      this.modeEstCouleur          = true;  } );
		}
		
		this.ctrl.setImageSymbole( tabIconSymbole );
    	this.ctrl.setImageCouleur( tabIconCouleur );

		this.panelSymbole.revalidate();
		this.panelSymbole.repaint();
	}	

	public int     getIndiceImageSelectionnee() { return this.indiceImageSelectionnee; }
	public boolean getModeEstCouleur()          { return this.modeEstCouleur;          }

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

				this.panelPlateau.setPlateau( lig, col );
				this.Symbole( nbSymboles );
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
}