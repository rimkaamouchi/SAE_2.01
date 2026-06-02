
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelPara extends JPanel
{
    private Controleur ctrl;
    private FramePara framePara;

    private JPanel     panelCara;
    private JPanel     panelBouton;

    private JTextField txtTaillePlateau;
    private JTextField txtNbCouleurs;
    private JTextField txtNbSymboles;
    private JTextField txtTailleCase;

    private JButton    btnAnnuler;
    private JButton    btnValider;

    //private 

    public PanelPara( FramePara framePara )
    {
        this.setLayout( new GridLayout( 2,1 ) );

        /*-------------------------------*/
		/*    Création des composants    */
		/*-------------------------------*/
        this.panelCara        = new JPanel();
        this.panelBouton      = new JPanel();

        this.txtTaillePlateau = new JTextField( "" );
        this.txtNbCouleurs    = new JTextField( "" );
        this.txtNbSymboles    = new JTextField( "" );
        this.txtTailleCase    = new JTextField( "" );

        this.btnAnnuler       = new JButton( "Annuler " );
        this.btnValider       = new JButton( "Valider " );

        this.panelCara  .setLayout( new GridLayout ( 8,1 ) );
        this.panelBouton.setLayout( new FlowLayout() );

        /*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/
        this.panelCara  .add  ( new JLabel( "Taille du plateau : " ) );
        this.panelCara  .add  ( this.txtTaillePlateau );
        this.panelCara  .add  ( new JLabel( "Nombre de couleurs : ") );
        this.panelCara  .add  ( this.txtNbCouleurs    );
        this.panelCara  .add  ( new JLabel( "Nombre de symboles : ") );
        this.panelCara  .add  ( this.txtNbSymboles    );
        this.panelCara  .add  ( new JLabel( "Taille des cases : "  ) );
        this.panelCara  .add  ( this.txtTailleCase    );

        this.panelBouton.add( this.btnAnnuler );
        this.panelBouton.add( this.btnValider );

        this.add( this.panelCara   );
        this.add( this.panelBouton );

        /*-------------------------------*/
		/*   Activation des composants   */
		/*-------------------------------*/
        //this.btnAnnuler.addActionListener();
        //this.btnValider.addActionListener();
    }
}