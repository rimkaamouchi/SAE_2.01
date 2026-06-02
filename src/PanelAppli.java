
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelAppli extends JPanel
{
    private Controleur ctrl;
    private JLabel[][] tabLblCase;
    private JButton    btnPioche;

    public PanelAppli( Controleur ctrl )
    {
        /*-------------------------------*/
		/*    Création des composants    */
		/*-------------------------------*/

        this.ctrl = ctrl; 

        this.tabLblCase = new JLabel[this.ctrl.getNbLigne()][this.ctrl.getNbColone()];

        for(int lig = 0; lig <tabLblCase.length; lig++)
        {
            for(int col = 0; col <tabLblCase.length; col++)
            {
                this.tabLblCase[lig][col] = new JLabel();
            }
        }

        this.btnPioche = new JButton( "Pioche" );

        /*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/

        this.add( this.btnPioche );

        /*-------------------------------*/
		/*   Activation des composants   */
		/*-------------------------------*/
        //this.btnPioche.addActionListener( this );
    
    }
}