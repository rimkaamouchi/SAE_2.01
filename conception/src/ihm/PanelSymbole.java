package conception.src.ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import conception.src.ControleurConception;


public class PanelSymbole extends JPanel
{
    private ControleurConception      ctrl;
    private PanelPara                 panelPara;

    public PanelSymbole( ControleurConception ctrl, PanelPara panelPara )
	{
		this.ctrl      = ctrl;
		this.panelPara = panelPara;
        this.setLayout( new GridLayout( 4, 3, 10, 10 ) );
        
		this.setBorder( BorderFactory.createTitledBorder( "Symboles" ) );

        //this.setVisible( false );
    }

    /*---- Mise à jour des symboles ----*/
    public void Symbole( int nbSymbole )
    {
        this.removeAll();
        this.setLayout( new GridLayout( 4, 3, 10, 10) );

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

            this.add( btnSymbole );


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

            this.add( btnCouleur );

            // Utilisation de la variable 'indice' devenue valide
            btnSymbole.addActionListener( ev -> { 
                this.panelPara.indiceImageSelectionnee = indice;
                this.panelPara.modeEstCouleur          = false; 
                this.panelPara.indiceZoneSelectionnee  = -1; 
            } );

            btnCouleur.addActionListener( ev -> { 
                this.panelPara.indiceImageSelectionnee = indice;
                this.panelPara.modeEstCouleur          = true;  
                this.panelPara.indiceZoneSelectionnee  = -1; 
            } );
        }
        
        this.ctrl.setImageSymbole( tabIconSymbole );
        this.ctrl.setImageCouleur( tabIconCouleur );

        this.revalidate();
        this.repaint();
    }
}