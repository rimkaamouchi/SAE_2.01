import java.awt.BorderLayout;

import javax.swing.JFrame;

public class FramePara extends JFrame
{
    private Controleur ctrl;
    private PanelPara panelPara;

    public FramePara( Controleur ctrl )
    {
        this.ctrl = ctrl;
        this.panelPara = new PanelPara( this );

        this.setTitle   ( "Paramètres du jeu" );
        this.setSize    ( 500, 400 );
        this.setLocation(  20, 200 );
        this.setLayout  ( new BorderLayout() );

        this.add( this.panelPara );

        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.setVisible( true );
    }
}