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
	private JPanel     panelZone;
	private JPanel     panelSymbole;
	private JPanel     panelBouton2;
	
	private JTextField txtNomPlateau;
	private JTextField txtTaillePlateauX;
	private JTextField txtTaillePlateauY;
	private JTextField txtNbCouleurs;
	private JTextField txtNbSymboles;

	private JButton    btnAnnuler;
	private JButton    btnValider;
	private JButton    btnAddZone;
	private JButton    btnMoinsZone;
	private JButton    btnSauvegarder;

	private int        indiceZoneSelectionnee = -1; 
	private int        indiceImageSelectionnee = 0;
	private int        cpt                     = 0;
	private boolean    modeEstCouleur          = false;
	
	static final String[] IMAGES_ZONES = 
	{
		"conception/src/Image/Zones/bleu_azur.png",
		"conception/src/Image/Zones/bleuVert.png",
		"conception/src/Image/Zones/candy.png",
		"conception/src/Image/Zones/lila.png",
		"conception/src/Image/Zones/orange_clair.png",
		"conception/src/Image/Zones/rose_clair.png",
		"conception/src/Image/Zones/rose_foncee.png",
		"conception/src/Image/Zones/vert_clair.png"
	};

	private static final Color[] COULEURS_ZONES =
	{
		new Color( 153, 204, 255 ),
		new Color( 204, 255, 255 ),
		new Color( 255, 204, 255 ),
		new Color( 204, 153, 255 ),
		new Color( 255, 204, 153 ),
		new Color( 255, 204, 204 ),
		new Color( 255, 153, 204 ),
		new Color( 153, 255, 204 )
	};

	public PanelPara( ControleurConception ctrl, PanelPlateau panelPlateau )
	{
		this.ctrl = ctrl;
		this.panelPlateau = panelPlateau; 
		this.setLayout( new GridLayout( 5,1 ) );

		/*-------------------------------*/
		/*    Création des composants    */
		/*-------------------------------*/
		this.panelCara         = new JPanel( new GridLayout( 8,1 ) );
		this.panelBouton       = new JPanel( new FlowLayout() );
		this.panelZone         = new JPanel( new GridLayout( 10,1 ) );
		this.panelSymbole      = new JPanel( new GridLayout( 4, 3, 10, 10 ) ); // espacement entre cellules
		this.panelBouton2      = new JPanel( new FlowLayout() );

		this.txtNomPlateau     = new JTextField( 3 );
		this.txtTaillePlateauX = new JTextField( 3 );
		this.txtTaillePlateauY = new JTextField( 3 );	
		this.txtNbCouleurs     = new JTextField( 3 );
		this.txtNbSymboles     = new JTextField( 3 );

		this.btnAnnuler        = new JButton( "Annuler"     );
		this.btnValider        = new JButton( "Valider"     );
		this.btnAddZone        = new JButton( "+"           );
		this.btnMoinsZone      = new JButton( "-"           );
		this.btnSauvegarder    = new JButton( "Sauvegarder" );
		
		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/
		this.panelCara   .add( new JLabel( " Nom du plateau : "     ) );
		this.panelCara   .add( this.txtNomPlateau                     );
		this.panelCara   .add( new JLabel( " Lignes : "             ) );
		this.panelCara   .add( this.txtTaillePlateauX                 );
		this.panelCara   .add( new JLabel( " Colonnes : "           ) );
		this.panelCara   .add( this.txtTaillePlateauY                 );
		this.panelCara   .add( new JLabel( " Nombre de couleurs : " ) );
		this.panelCara   .add( this.txtNbCouleurs                     );
		this.panelCara   .add( new JLabel( " Nombre de symboles : " ) );
		this.panelCara   .add( this.txtNbSymboles                     );

		this.panelBouton .add( this.btnAnnuler  );
		this.panelBouton .add( this.btnValider  );

		this.panelZone   .add( this.btnAddZone  );
		this.panelZone   .add( this.btnMoinsZone);

		this.panelBouton2.add( this.btnSauvegarder );

		this.add( this.panelCara    );
		this.add( this.panelBouton  );
		this.add( this.panelZone    );
		this.add( this.panelSymbole );
		this.add( this.panelBouton2 );

		this.panelZone   .setVisible( false );
		this.panelSymbole.setVisible( false );
		this.panelBouton2.setVisible( false );

		this.panelCara   .setBorder( BorderFactory.createTitledBorder( "Paramètres" ) );
		this.panelZone   .setBorder( BorderFactory.createTitledBorder( "Zones"      ) );
		this.panelSymbole.setBorder( BorderFactory.createTitledBorder( "Symboles"   ) );

		/*-------------------------------*/
		/*   Activation des composants   */
		/*-------------------------------*/
		this.btnAnnuler    .addActionListener( this );
		this.btnValider    .addActionListener( this );
		this.btnAddZone    .addActionListener( this );
		this.btnMoinsZone  .addActionListener( this );
		this.btnSauvegarder.addActionListener( this );

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
            JOptionPane.showMessageDialog( this, "Le maximum de boutons a été atteint." );
        }

        // tableaux à envoyer vers le contrôleur
        ImageIcon[] tabIconSymbole = new ImageIcon[nbSymbole];
        ImageIcon[] tabIconCouleur = new ImageIcon[nbSymbole];

        for ( int i = 0; i < nbSymbole; i++ ) //symboles misent dans les labels
        {
            // Déclaration d'une constante finale pour être capturée correctement par l'actionListener
            final int indice = i;

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

            // Utilisation de la variable 'indice' devenue valide
            btnSymbole.addActionListener( ev -> { 
                this.indiceImageSelectionnee = indice;
                this.modeEstCouleur          = false; 
                this.indiceZoneSelectionnee  = -1; 
            } );

            btnCouleur.addActionListener( ev -> { 
                this.indiceImageSelectionnee = indice;
                this.modeEstCouleur          = true;  
                this.indiceZoneSelectionnee  = -1; 
            } );
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

				this.ctrl.setParametres( lig, col, nbCouleurs, nbSymboles);
				this.panelPlateau.setPlateau( lig, col );
				this.Symbole( nbSymboles );
			}
			catch ( NumberFormatException ex )
			{
				JOptionPane.showMessageDialog( this, "Entrez des nombres valides !" );
			}
			this.panelZone   .setVisible( true );
			this.panelSymbole.setVisible( true );
			this.panelBouton2.setVisible( true );
		}

		if ( e.getSource() == this.btnAddZone )
		{
			if ( this.cpt >= this.COULEURS_ZONES.length )
			{
				JOptionPane.showMessageDialog( this, "Le maximum de zones a été atteint." );
				return;
			}

			JButton btnZone = new JButton();
			ImageIcon icon  = new ImageIcon( IMAGES_ZONES[ this.cpt ] );
			Image img       = icon.getImage().getScaledInstance( 60, 60, Image.SCALE_SMOOTH );

			btnZone.setIcon( new ImageIcon( img ) );
			btnZone.setBackground( COULEURS_ZONES[ this.cpt ] );
			btnZone.setPreferredSize( new Dimension( 80, 80 ) );

			final int indiceZone = this.cpt;

			btnZone.addActionListener( ev -> {          
                this.indiceZoneSelectionnee  = indiceZone;
                this.indiceImageSelectionnee = -1; // <-- Ajustement : évite le conflit avec un symbole déjà sélectionné
            	});

			this.panelZone.add( btnZone );

			this.cpt++;

			this.panelZone.revalidate();
			this.panelZone.repaint();
		}

 		if( e.getSource() == this.btnMoinsZone )
		{
			if ( this.cpt <= 0 )
				return;
			
			this.cpt--;

			this.panelZone.remove( this.cpt + 2 ); //+2 car btnAddZone et btnMoinsZone sont sur les 2 premières positions

			this.panelZone.revalidate();
			this.panelZone.repaint();
			

			if ( e.getSource() == this.btnSauvegarder )
			{
				this.ctrl.actionSauvegarder();

				JOptionPane.showMessageDialog( this, "Scan réussi avec succès" );
			
		////
		if ( e.getSource() == this.btnSauvegarder )
		{
			boolean zonesValides   = this.ctrl.validerToutesLesZones();
			boolean plateauComplet = this.ctrl.plateauEstComplet();

			if ( !plateauComplet )
			{
				JOptionPane.showMessageDialog( this, "Certaines cellules n'ont pas de zone !" );
				return;
			}
			if ( !zonesValides )
			{
				JOptionPane.showMessageDialog( this, "Certaines zones ne sont pas continues !" );
				return;
			}

			this.ctrl.actionSauvegarder();
			JOptionPane.showMessageDialog( this, "Sauvegarde réussie !" );
		}}
		}
	}

	public int getIndiceZoneSelectionnee() { return this.indiceZoneSelectionnee; }
	public String getNomPlateau()          { return this.txtNomPlateau.getText();}
}