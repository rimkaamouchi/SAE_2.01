
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelPara extends JPanel implements ActionListener
{
    private Controleur ctrl;
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

    public PanelPara( FramePara framePara )
    {
        this.setLayout( new GridLayout( 2,1 ) );

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
    

        this.panelCara  .setLayout( new GridLayout ( 8,1 ) );
        this.panelBouton.setLayout( new FlowLayout() );

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
        this.add( this.panelBouton );
        this.add( this.panelLabel  );

        /*-------------------------------*/
		/*   Activation des composants   */
		/*-------------------------------*/
        this.btnAnnuler.addActionListener( this );
        this.btnValider.addActionListener( this );

        int image = 12;
        
        for (int i = 1; i < image; i++)
        {
            
        }
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

        /*if( e.getSource() == this.btnValider )
        {
            
        }*/
    }
}