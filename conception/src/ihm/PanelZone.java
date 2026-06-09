package conception.src.ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import conception.src.ControleurConception;

public class PanelZone extends JPanel implements ActionListener
{
    private ControleurConception ctrl;
    private PanelPara            panelPara;

    private JButton    btnAddZone;
	private JButton    btnMoinsZone;

    private int        indiceZoneSelectionnee = -1; 
	private int        indiceImageSelectionnee = 0;
	private int        cpt = 0;

    public static final String[] IMAGES_ZONES = 
	{
		"conception/src/Image/Zones/bleu_azur.png",
		"conception/src/Image/Zones/bleuVert.png",
		"conception/src/Image/Zones/candy.png",
		"conception/src/Image/Zones/lila.png",
		"conception/src/Image/Zones/orange_clair.png",
		"conception/src/Image/Zones/rose_clair.png",
		//"conception/src/Image/Zones/rose_foncee.png",
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
		//new Color( 255, 153, 204 ),
		new Color( 153, 255, 204 )
	};

    public PanelZone( ControleurConception ctrl, PanelPara panelPara )
	{
		this.ctrl      = ctrl;
		this.panelPara = panelPara; 
		this.setLayout( new GridLayout( 10,1 ) );

        /*-------------------------------*/
        /*    Création des composants    */
        /*-------------------------------*/
        this.btnAddZone   = new JButton( "+" );
        this.btnMoinsZone = new JButton( "-" );

        /*-------------------------------*/
        /* Positionnement des composants */
        /*-------------------------------*/
        this.add( this.btnAddZone   );
        this.add( this.btnMoinsZone );

        this.setBorder( BorderFactory.createTitledBorder( "Zones" ) );


        /*-------------------------------*/
        /*   Activation des composants   */
        /*-------------------------------*/
        this.btnAddZone  .addActionListener( this );
        this.btnMoinsZone.addActionListener( this );

		//this.setVisible( false );
    }

    public void actionPerformed( ActionEvent e )
	{
        if ( e.getSource() == this.btnAddZone )
		{
			if ( this.cpt >= this.COULEURS_ZONES.length )
			{
				JOptionPane.showMessageDialog( this, "Le maximum de zones a été atteint." );
				return;
			}

			JButton   btnZone = new JButton();
			ImageIcon icon    = new ImageIcon( IMAGES_ZONES[ this.cpt ] );
			Image     img     = icon.getImage().getScaledInstance( 60, 60, Image.SCALE_SMOOTH );

			btnZone.setIcon( new ImageIcon( img ) );
			btnZone.setBackground( COULEURS_ZONES[ this.cpt ] );
			btnZone.setPreferredSize( new Dimension( 80, 80 ) );

			final int indiceZone = this.cpt;

			btnZone.addActionListener( ev ->
			{          
                this.indiceZoneSelectionnee  = indiceZone;
				this.panelPara.indiceZoneSelectionnee = indiceZone;
                this.indiceImageSelectionnee = -1; 
				this.panelPara.indiceImageSelectionnee = -1;
            }  );

			this.add( btnZone );

			this.cpt++;

			this.revalidate();
			this.repaint();
		}

 		if( e.getSource() == this.btnMoinsZone )
		{
			if ( this.cpt <= 0 ) return;
			
			this.cpt--;

			this.remove( this.cpt + 2 ); //+2 car btnAddZone et btnMoinsZone sont sur les 2 premières positions

			this.revalidate();
			this.repaint();
		}
    }
}