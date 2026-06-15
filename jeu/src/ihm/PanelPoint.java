package jeu.src.ihm;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import jeu.src.ControleurJeu;

public class PanelPoint extends JPanel implements ActionListener
{
	private ControleurJeu ctrl;
	private FrameJeu      frameJeu;
	private JFrame        frame;
	private JButton       btnFin;

	//setter
	public void setBtnFin(JButton btnFin){ this.btnFin = btnFin;}

	public PanelPoint(FrameJeu frame, ControleurJeu ctrl)
	{
		this.ctrl = ctrl;
		this.setLayout(new FlowLayout());

		/*-------------------------------*/
		/*    Création des composants    */
		/*-------------------------------*/
		this.btnFin = new JButton("Fin du Jeu");

        /*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/
		this.add( this.btnFin );

        /*-------------------------------*/
		/*   Activation des composants   */
		/*-------------------------------*/ 
		this.btnFin.addActionListener( this );

		this.btnFin.setVisible(false);

	}

	public void actionPerformed( ActionEvent e )
	{
		if ( e.getSource() == this.btnFin )
		{
			this.afficherPoint(this.frameJeu); 
		}
	}

	public void afficherBtnFin()
	{
		btnFin.setVisible(true);
		revalidate();
		repaint();
	}

	public static void afficherPoint( FrameJeu frame)
    {
        JDialog dialog = new JDialog(frame, "Point", true);
		dialog.setLayout(new BorderLayout());
		dialog.setSize(300, 200);
		dialog.setLocationRelativeTo(frame);

		JPanel panelContenu = new JPanel();
		panelContenu.add(new JLabel("Résultat des points"));

		JButton btnFermer = new JButton("Fermer");
		btnFermer.addActionListener(e -> dialog.dispose());

		dialog.add( panelContenu, BorderLayout.CENTER );
		dialog.add( btnFermer   , BorderLayout.SOUTH  );
		dialog.setVisible( true );

	}
}