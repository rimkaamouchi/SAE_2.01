package jeu.src.ihm;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import jeu.src.ControleurJeu;
import jeu.src.metier.Cellule;
import jeu.src.metier.Chemin;
import jeu.src.metier.Lien;

public class PanelJeu extends JPanel
{
	private ControleurJeu ctrl;

	private Image[][] matriceZones;
	private Image[][] matriceSymboles;
	private int       nbLig;
	private int       nbCol;

	private Cellule   extremiteSelectionnee; // 1er clic
	private String    messageErreur;         // message affiché

	//images & lettres correspondants aux zones
	private static final char  [] LETTRES_ZONES    = {'A'                    ,'B'                 ,'C'                      ,'D'                     ,'E'              ,'F'                   ,'G'                   ,'H'};
	private static final String[] IMAGES_ZONES     = {"Zones/bleu_azur.png"  ,"Zones/bleuVert.png","Zones/lila.png"         ,"Zones/orange_clair.png","Zones/candy.png","Zones/rose_clair.png","Zones/vert_clair.png","Zones/jaune_clair.png"};
	//images & lettres correspondants aux symboles non-colorés
	private static final char  [] LETTRES_SYMBOLES = {'R'                    ,'Q'                 ,'S'                      /* ,'U'                     ,'T'               */                                             };
	private static final String[] IMAGES_SYMBOLES  = {"Symboles/orange.png"  ,"Symboles/apple.png","Symboles/moto.png"      /* ,"Symboles/cafe.png"     ,"Symboles/pain.png"*/                                           };
	//images & lettres correspondants aux symboles colorés
	private static final char  [] LETTRES_COULEURS = {'V'                    ,'W'                 ,'X'                      /* ,'Y'                     ,'Z'              */                                              };
	private static final String[] IMAGES_COULEURS  = {"Symboles/bordeaux.png","Symboles/rose.png" ,"Symboles/bleu_fonce.png"/* ,"Symboles/marron.png"   ,"Symboles/bleu.png" */                                           };
	// Couleurs des traits par couleur de chemin
	private static final char [] CODES_COULEURS    = {'V',               'W',                    /*'X',            /*  'Y',                  'Z'  */                                                                       };
	private static final Color[] COULEURS_TRAITS   = {new Color(139,0,0), new Color(255,105,180), /*new Color(0,0,139)/* , new Color(139,69,19), new Color(70,130,180)*/              };
	
	//getter
	private Color getCouleurTrait( char couleur )
	{
		for ( int i = 0; i < CODES_COULEURS.length; i++ )
			if ( CODES_COULEURS[i] == couleur ) return COULEURS_TRAITS[i];
		return Color.BLACK;
	}

	//setter
	public void setPlateau( int nbLig, int nbCol )
	{
		this.nbLig = nbLig;
		this.nbCol = nbCol;

		this.matriceZones    = new Image[nbLig][nbCol];
		this.matriceSymboles = new Image[nbLig][nbCol];

		for( int i = 0; i < nbLig; i++ )
		for( int j = 0; j < nbCol; j++ )
		{
			Cellule c = ctrl.getPlateau().getCellule(i, j);
			if ( c != null )
			{
				for( int l = 0; l < LETTRES_ZONES.length; l++ )
					if( LETTRES_ZONES[l] == c.getZone() )
					{
						ImageIcon icon = new ImageIcon( "conception/src/Image/" + IMAGES_ZONES[l] );
						matriceZones[i][j] = icon.getImage();
					}

				for( int k = 0; k < LETTRES_SYMBOLES.length; k++ )
					if( LETTRES_SYMBOLES[k] == c.getSymbole() )
					{
						ImageIcon icon = new ImageIcon( "conception/src/Image/" + IMAGES_SYMBOLES[k] );
						matriceSymboles[i][j] = icon.getImage();
					}

				for( int k = 0; k < LETTRES_COULEURS.length; k++ )
					if( LETTRES_COULEURS[k] == c.getSymbole() )
					{
						ImageIcon icon = new ImageIcon( "conception/src/Image/" + IMAGES_COULEURS[k] );
						matriceSymboles[i][j] = icon.getImage();
					}
			}
		}

		this.repaint();
	}

	//constructeur
	public PanelJeu( ControleurJeu ctrl )
	{
		this.ctrl                  = ctrl;
		this.extremiteSelectionnee = null;
		this.messageErreur         = null;

		/*-------------------------------*/
		/*    Création des composants    */
		/*-------------------------------*/


		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/


		/*-------------------------------*/
		/*   Activation des composants   */
		/*-------------------------------*/ 

		this.addMouseListener( new MouseAdapter()
		{
			@Override
			public void mouseClicked( MouseEvent e )
			{
				gererClic( e.getX(), e.getY() );
			}
		});

		this.setFocusable( true );
		this.requestFocusInWindow();
		this.addKeyListener( new java.awt.event.KeyAdapter()
		{
			@Override
			public void keyPressed( java.awt.event.KeyEvent e )
			{
				if ( e.isControlDown() && e.getKeyCode() == java.awt.event.KeyEvent.VK_T )
				{
					ctrl.activerModeTriche();
					ctrl.getFrameJeu().getPanelPioche().reinitialiserAffichage();
					repaint();
				}
			}
		});
	}

	//remplace MouseListener
	private void gererClic( int px, int py )
	{
		if ( nbLig == 0 || nbCol == 0 ) return;

		int largeurCase = this.getWidth()  / this.nbCol;
		int hauteurCase = this.getHeight() / this.nbLig;

		int col = px / largeurCase;
		int lig = py / hauteurCase;

		if ( lig < 0 || lig >= nbLig || col < 0 || col >= nbCol ) return;

		Cellule celluleCliquee = ctrl.getPlateau().getCellule( lig, col );
		if ( celluleCliquee == null )
		{
			messageErreur = "Aucun sommet ici.";
			repaint();
			return;
		}

		// Récupérer le chemin actuel
		char couleur = ctrl.getCouleurActuelle();
		Chemin chemin = ctrl.getJeu().getCheminPourCouleur( couleur );
		if ( chemin == null ) return;

		ArrayList<Cellule> etapes = chemin.getEtapes();
		Cellule debut = etapes.get(0);
		Cellule fin   = chemin.getExtremite();

		if ( extremiteSelectionnee == null )
		{
			// 1er clic : doit être une extrémité du chemin
			if ( celluleCliquee == debut || celluleCliquee == fin )
			{
				extremiteSelectionnee = celluleCliquee;
				messageErreur = null;
			}
			else
			{
				messageErreur = "Cliquez sur une extrémité du chemin !";
			}
		}
		else
		{
			// 2ème clic : tenter le déplacement
			if ( !ctrl.peutJouer() )
			{
				messageErreur = "Vous avez déjà joué cette carte ! Piochez la suivante.";
				extremiteSelectionnee = null;
				repaint();
				return;
			}

			boolean ok = ctrl.deplacerExtremite( extremiteSelectionnee, celluleCliquee );
			if ( ok )
			{
				messageErreur = null;
				ctrl.signalerJoue();
			}
			else
			{
				messageErreur = "Déplacement invalide : voisin non adjacent, symbole incorrect ou tracé non autorisé.";
			}
			extremiteSelectionnee = null;
			repaint();
		}
		
		repaint();
		this.requestFocusInWindow();
	}

	//méthode pour dessiner 
	@Override
	public void paintComponent( Graphics g )
	{
		super.paintComponent(g);

		if ( this.nbLig == 0 || this.nbCol == 0 ) return;

		Graphics2D graph = (Graphics2D) g;
		graph.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );

		int largeurCase = this.getWidth()  / this.nbCol;
		int hauteurCase = this.getHeight() / this.nbLig;

		// Dessin des zones
		if ( this.matriceZones != null )
			for ( int i = 0; i < nbLig; i++ )
				for ( int j = 0; j < nbCol; j++ )
					if ( this.matriceZones[i][j] != null )
						graph.drawImage( this.matriceZones[i][j],
										j * largeurCase, i * hauteurCase,
										largeurCase, hauteurCase, this );

		// Dessin des liens en gris
		ArrayList<Lien> routes = ctrl.getPlateau().getRoutes();
		if ( routes != null )
		{
			graph.setColor ( Color.LIGHT_GRAY );
			graph.setStroke( new BasicStroke(3) );
			for ( Lien l : routes )
			{
				int x1 = l.getDepart() .getY() * largeurCase + largeurCase / 2;
				int y1 = l.getDepart() .getX() * hauteurCase + hauteurCase / 2;
				int x2 = l.getArrivee().getY() * largeurCase + largeurCase / 2;
				int y2 = l.getArrivee().getX() * hauteurCase + hauteurCase / 2;
				graph.drawLine( x1, y1, x2, y2 );
			}
		}

		// Dessin des chemins en couleur
		ArrayList<Chemin> chemins = ctrl.getJeu().getChemins();
		if ( chemins != null )
		{
			for ( Chemin chemin : chemins )
			{
				Color couleurTrait = getCouleurTrait( chemin.getCouleur() );
				graph.setColor ( couleurTrait      );
				graph.setStroke( new BasicStroke(6, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND) );

				ArrayList<Cellule> etapes = chemin.getEtapes();
				for ( int i = 0; i < etapes.size() - 1; i++ )
				{
					Cellule a = etapes.get(i);
					Cellule b = etapes.get(i + 1);
					int x1 = a.getY() * largeurCase + largeurCase / 2;
					int y1 = a.getX() * hauteurCase + hauteurCase / 2;
					int x2 = b.getY() * largeurCase + largeurCase / 2;
					int y2 = b.getX() * hauteurCase + hauteurCase / 2;
					graph.drawLine( x1, y1, x2, y2 );
				}

				// Dessiner les extrémités en surbrillance
				if ( etapes.size() > 0 )
				{
					dessinerExtremite( graph, etapes.get(0),                largeurCase, hauteurCase, couleurTrait );
					dessinerExtremite( graph, etapes.get(etapes.size() - 1), largeurCase, hauteurCase, couleurTrait );
				}
			}
		}

		// Surligner l'extrémité sélectionnée
		if ( extremiteSelectionnee != null )
		{
			graph.setColor( Color.YELLOW );
			graph.setStroke( new BasicStroke(3) );
			int ex = extremiteSelectionnee.getY() * largeurCase + largeurCase / 4;
			int ey = extremiteSelectionnee.getX() * hauteurCase + hauteurCase / 4;
			graph.drawOval( ex, ey, largeurCase / 2, hauteurCase / 2 );
		}

		// Dessin des symboles
		if ( this.matriceSymboles != null )
			for ( int i = 0; i < nbLig; i++ )
				for ( int j = 0; j < nbCol; j++ )
					if ( this.matriceSymboles[i][j] != null )
						graph.drawImage( this.matriceSymboles[i][j],
										j * largeurCase, i * hauteurCase,
										largeurCase, hauteurCase, this );

		// Dessin de la grille
		graph.setColor ( Color.BLACK       );
		graph.setStroke( new BasicStroke(1) );
		for ( int i = 0; i <= nbLig; i++ )
			graph.drawLine( 0, i * hauteurCase, this.getWidth(), i * hauteurCase );
		for ( int j = 0; j <= nbCol; j++ )
			graph.drawLine( j * largeurCase, 0, j * largeurCase, this.getHeight() );

		// Message d'erreur
		if ( messageErreur != null )
		{
			graph.setColor( new Color(200, 0, 0, 200) );
			graph.fillRoundRect( 10, this.getHeight() - 40, this.getWidth() - 20, 30, 10, 10 );
			graph.setColor( Color.WHITE );
			graph.setFont( new java.awt.Font("SansSerif", java.awt.Font.BOLD, 14) );
			graph.drawString( messageErreur, 20, this.getHeight() - 20 );
		}
	}

	private void dessinerExtremite( Graphics2D g, Cellule c, int largeurCase, int hauteurCase, Color couleur )
	{
		g.setColor( couleur.brighter() );
		g.setStroke( new BasicStroke(3) );
		int ex = c.getY() * largeurCase + largeurCase / 4;
		int ey = c.getX() * hauteurCase + hauteurCase / 4;
		g.drawOval( ex, ey, largeurCase / 2, hauteurCase / 2 );
	}
}