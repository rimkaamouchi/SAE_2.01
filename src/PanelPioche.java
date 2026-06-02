
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelPioche extends JPanel
{
    private Controleur ctrl;

    private JButton btn1;
    private JButton btn2;
    private JButton btn3;
    private JButton btn4;
    private JButton btn5;

    public PanelPioche( FramePioche framePioche )
    {
        /*-------------------------------*/
		/*    Création des composants    */
		/*-------------------------------*/
        this.btn1 = new JButton( "Carte 1" );
        this.btn2 = new JButton( "Carte 2" );
        this.btn3 = new JButton( "Carte 3" );
        this.btn4 = new JButton( "Carte 4" );
        this.btn5 = new JButton( "Carte 5" );


        /*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/
        this.add( this.btn1 );
        this.add( this.btn2 );
        this.add( this.btn3 );
        this.add( this.btn4 );
        this.add( this.btn5 );

        /*-------------------------------*/
		/*   Activation des composants   */
		/*-------------------------------*/
    }
}