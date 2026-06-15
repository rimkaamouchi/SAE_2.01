package jeu.src.ihm;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics; //mesure aux pixels prés
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D; // coin rectangle arrondi

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import jeu.src.ControleurJeu;
import jeu.src.metier.Carte;

public class PanelPioche extends JPanel implements ActionListener
{
	private ControleurJeu ctrl;
	private Carte         carteActuelle;   // dernière carte tirée (null au départ)
	private boolean       piocheVide;

	private JButton btnPasse;
	private JPanel  panelBtn;

	// Dimensions des cartes
	private static final int CARTE_W = 80;
	private static final int CARTE_H = 120;
	private static final int MARGE   = 20;

	private static final char [] CODES_COULEURS  = {'V',               'W',                    'X',              'Y',                  'Z'             };
	private static final Color[] COULEURS_TRAITS = {new Color(139,0,0), new Color(255,105,180), new Color(0,0,139), new Color(139,69,19), new Color(70,130,180)};

	private Color getCouleurTrait( char couleur )
	{
		for ( int i = 0; i < CODES_COULEURS.length; i++ )
			if ( CODES_COULEURS[i] == couleur ) return COULEURS_TRAITS[i];
		return Color.WHITE;
	}

	public PanelPioche( ControleurJeu ctrl )
	{
		this.ctrl          = ctrl;
		this.carteActuelle = null;
		this.piocheVide    = false;

		this.setPreferredSize( new Dimension( 220, 400 ) );
		this.setBackground( new Color(40, 40, 50) );
		this.setLayout(new BorderLayout());

		/*-------------------------------*/
		/*    Création des composants    */
		/*-------------------------------*/

		this.panelBtn = new JPanel();
		this.btnPasse = new JButton( "Passez mon tour" );
		this.panelBtn.setBackground(new Color(40, 40, 50) );
		this.setLayout(new BorderLayout());

        /*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/
		this.panelBtn.add( this.btnPasse, BorderLayout.SOUTH);
		this.add( this.panelBtn, BorderLayout.SOUTH);

        /*-------------------------------*/
		/*   Activation des composants   */
		/*-------------------------------*/ 
		this.btnPasse.addActionListener( this );

		// Clic sur la zone de la carte-dos -> tirer une carte
		this.addMouseListener( new MouseAdapter() 
		{
			@Override
			public void mouseClicked( MouseEvent e )
			{
				int x = MARGE;
				int y = 80;
				if ( e.getX() >= x && e.getX() <= x + CARTE_W
				&& e.getY() >= y && e.getY() <= y + CARTE_H )
				{
					piocherCarte();
				}
			}
		});
	}

    public void actionPerformed(ActionEvent e)
	{
        if ( e.getSource() == this.btnPasse )
		{
			JOptionPane.showMessageDialog( this, "Vous avez passé votre tour." );
		}
		
	}

	private void piocherCarte()
	{
		if ( ctrl.piocheVide() )
		{
			// Pioche vide -> fin du tour
			JOptionPane.showMessageDialog( this,
				"Tour terminé ! Passage à la couleur suivante.",
				"Fin du tour", JOptionPane.INFORMATION_MESSAGE );
			ctrl.passerAuTourSuivant();
			if ( ctrl.getJeu().isPartieFinie() )
			{
    			ctrl.getFrameJeu().getPanelPoint().afficherBtnFin();
			}
			this.piocheVide    = false;
			this.carteActuelle = null;
			this.repaint();
			ctrl.getFrameJeu().getPanelJeu().repaint();
			return;
		}

		Carte c = ctrl.tirerCarte();
		if ( c == null )
		{
			this.piocheVide = true;
		}
		else
		{
			ctrl.getJeu().reinitialiserJoue();
			this.carteActuelle = c;
			this.piocheVide    = ctrl.piocheVide();
		}
		this.repaint();
		ctrl.getFrameJeu().getPanelJeu().repaint();
	}

	@Override
	public void paintComponent( Graphics g )
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		int y = 80;

		// Carte Dos (pioche)
		int xDos = MARGE;
		if ( !this.piocheVide )
		{
			dessinerCarteDosCachee( g2, xDos, y );
			// Indication "Cliquer"
			g2.setColor( Color.LIGHT_GRAY );
			g2.setFont( new Font("SansSerif", Font.PLAIN, 10) );
			g2.drawString( "cliquer", xDos + 12, y + CARTE_H + 16 );
		}
		else
		{
			// Pioche vide : afficher emplacement grisé
			g2.setColor( new Color(80,80,90) );
			g2.fill( new RoundRectangle2D.Float(xDos, y, CARTE_W, CARTE_H, 12, 12) );
			g2.setColor( Color.GRAY );
			g2.setFont( new Font("SansSerif", Font.BOLD, 11) );
			g2.drawString( "Vide", xDos + 22, y + CARTE_H/2 + 5 );
		}

		// Carte Retournée (dernière tirée)
		int xRetournee = MARGE + CARTE_W + MARGE;
		if ( this.carteActuelle != null )
			dessinerCarteRetournee( g2, xRetournee, y, this.carteActuelle );
		else
		{
			// Emplacement vide
			g2.setColor( new Color(60,60,70) );
			g2.setStroke( new BasicStroke(1.5f, BasicStroke.CAP_ROUND,
										BasicStroke.JOIN_ROUND, 0,
										new float[]{6,4}, 0) );
			g2.draw( new RoundRectangle2D.Float(xRetournee, y, CARTE_W, CARTE_H, 12, 12) );
		}

		// Titre panneau
		g2.setColor( Color.WHITE );
		g2.setFont( new Font("SansSerif", Font.BOLD, 14) );
		g2.drawString( "PIOCHE", MARGE, 40 );

		// Nb de cartes restantes 
		int restantes = ctrl.getJeu().getNbCartesRestantes();
		g2.setColor( Color.LIGHT_GRAY );
		g2.setFont( new Font("SansSerif", Font.PLAIN, 12) );
		g2.drawString( "Restantes : " + restantes, MARGE, y + CARTE_H + 50 );


		// Tour actuel
		char couleurActuelle = ctrl.getCouleurActuelle();
		Color couleurRond = getCouleurTrait( couleurActuelle );
		g2.setColor( Color.WHITE );
		g2.setFont( new Font("SansSerif", Font.BOLD, 13) );
		g2.drawString( "Tour actuel :", MARGE, y + CARTE_H + 80 );
		g2.setColor( couleurRond );
		g2.fillOval( MARGE + 110, y + CARTE_H + 68, 20, 20 );
		g2.setColor( Color.WHITE );
		g2.setStroke( new BasicStroke(1) );
		g2.drawOval( MARGE + 110, y + CARTE_H + 68, 20, 20 );
	}

	/** Dessine une carte face cachée (dos décoré) */
	private void dessinerCarteDosCachee( Graphics2D g2, int x, int y )
	{
		// Fond de la carte
		g2.setColor( new Color(30, 60, 120) );
		g2.fill( new RoundRectangle2D.Float(x, y, CARTE_W, CARTE_H, 12, 12) );

		// Bordure
		g2.setColor( new Color(80, 120, 200) );
		g2.setStroke( new BasicStroke(2) );
		g2.draw( new RoundRectangle2D.Float(x, y, CARTE_W, CARTE_H, 12, 12) );

		// Motif dos (petits losanges)
		g2.setColor( new Color(50, 90, 160) );
		g2.setStroke( new BasicStroke(1) );
		for ( int dy = y + 14; dy < y + CARTE_H - 10; dy += 14 )
			for ( int dx = x + 10; dx < x + CARTE_W - 6; dx += 14 )
			{
				int[] px = { dx, dx+5, dx, dx-5 };
				int[] py = { dy-5, dy, dy+5, dy };
				g2.drawPolygon(px, py, 4);
			}

		// Symbole central
		g2.setColor( Color.WHITE );
		g2.setFont( new Font("SansSerif", Font.BOLD, 22) );
		FontMetrics fm = g2.getFontMetrics();
		String s = "?";
		g2.drawString( s, x + (CARTE_W - fm.stringWidth(s))/2,
						y + CARTE_H/2 + fm.getAscent()/2 - 4 );
	}

	/** Dessine une carte retournée (face visible) */
	private void dessinerCarteRetournee( Graphics2D g2, int x, int y, Carte carte )
	{
		boolean noire = (carte.getTeinte() == 'N');
		Color fond    = noire ? new Color(30, 30, 35)   : new Color(245, 240, 225);
		Color texte   = noire ? Color.WHITE              : Color.BLACK;
		Color bordure = noire ? new Color(160,160,180)   : new Color(80, 80, 80);

		g2.setColor( fond );
		g2.fill( new RoundRectangle2D.Float(x, y, CARTE_W, CARTE_H, 12, 12) );

		g2.setColor( bordure );
		g2.setStroke( new BasicStroke(2) );
		g2.draw( new RoundRectangle2D.Float(x, y, CARTE_W, CARTE_H, 12, 12) );

		// Teinte (N / B) en petit en haut à gauche — seulement pour joker
		if ( carte.getSymbole() == '*' )
		{
			g2.setColor( texte );
			g2.setFont( new Font("SansSerif", Font.BOLD, 11) );
			g2.drawString( String.valueOf(carte.getTeinte()), x + 8, y + 18 );
			g2.drawString( String.valueOf(carte.getTeinte()), x + CARTE_W - 18, y + CARTE_H - 8 );
		}

		// Image du symbole au centre
		Image img = getImageSymbole( carte.getSymbole() );
		if ( img != null )
			g2.drawImage( img, x + 10, y + 25, CARTE_W - 20, CARTE_H - 50, this );
		else
		{
			// fallback : afficher la lettre si image non trouvée
			g2.setFont( new Font("SansSerif", Font.BOLD, 32) );
			FontMetrics fm = g2.getFontMetrics();
			String sym = String.valueOf( carte.getSymbole() );
			g2.drawString( sym, x + (CARTE_W - fm.stringWidth(sym))/2, y + CARTE_H/2 + 10 );
		}

		// Teinte en bas à droite
		g2.setFont( new Font("SansSerif", Font.BOLD, 11) );
		g2.drawString( String.valueOf(carte.getTeinte()), x + CARTE_W - 18, y + CARTE_H - 8 );
	}

	private static final char  [] LETTRES_SYMBOLES = {'R','Q','U','S','T','*'};
	private static final String[] IMAGES_SYMBOLES  = {"Symboles/orange.png","Symboles/apple.png","Symboles/cafe.png","Symboles/moto.png","Symboles/pain.png","Symboles/montre.png"};

	private Image getImageSymbole( char symbole )
	{
		for ( int k = 0; k < LETTRES_SYMBOLES.length; k++ )
			if ( LETTRES_SYMBOLES[k] == symbole )
				return new ImageIcon( "conception/src/Image/" + IMAGES_SYMBOLES[k] ).getImage();
		return null;
	}

	public void reset()
	{
		this.carteActuelle = null;  // On efface la carte du visuel
		this.piocheVide    = false; // On remet le drapeau de la pioche à faux
		this.repaint();             // On force Swing à redessiner le panel tout propre
	}
}