package graphiquePackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

import movePackage.Engine;

public class MenuBar extends JMenuBar{
	Engine engi;
	Panneau panneauDeJeu;
	
	/*
	 * 1 Constructeur
	 */
	public MenuBar (Engine referenceEngi, Panneau referencePanneau){
		engi = referenceEngi;
		panneauDeJeu = referencePanneau;
		initialisation();
	}
	/*
	 * FIN Constructeur
	 */
	
	/*
	 * Methodes Private de MenuBar
	 */
	private void initialisation (){
		JMenu menuPrincipal = new JMenu("Partie");
		
		JMenuItem nouvellePartie = new JMenuItem("Nouvelle Partie");
		nouvellePartie.addActionListener(new ActionNouvellePartie());
		
		JMenuItem choisirPartie = new JMenuItem("Choisir Partie");
		choisirPartie.addActionListener(new ActionChoisirPartie());
		
		JMenuItem quitter = new JMenuItem("Quitter");
		quitter.addActionListener(new ActionQuitter());
		
		
		menuPrincipal.add(nouvellePartie);
		menuPrincipal.add(choisirPartie);
		menuPrincipal.addSeparator();
		menuPrincipal.add(quitter);
		
		this.add(menuPrincipal);
	}
	
	
	/*
	 * Classes internes de ActionListener pour les differents menus
	 */
	private class ActionQuitter implements ActionListener {
		public void actionPerformed (ActionEvent action) {
			System.exit(0);
		}	
	}
	private class ActionNouvellePartie implements ActionListener {
		public void actionPerformed (ActionEvent action){
			engi.nouvellePartie();
			panneauDeJeu.repaint();
		}
	}
	private class ActionChoisirPartie implements ActionListener {
		public void actionPerformed (ActionEvent action){
			try {
//				JFrame petiteFenetre = new JFrame("Choisir une partie");
				
				JOptionPane PopUp = new JOptionPane();
				
				MaskFormatter mask = new MaskFormatter("#######");
				JFormattedTextField zoneEcriture = new JFormattedTextField(mask);
				
				PopUp.add(zoneEcriture);
/*
				petiteFenetre.add(zoneEcriture);
				petiteFenetre.setSize(200, 200);
				petiteFenetre.setLocationRelativeTo(null);
				petiteFenetre.setVisible(true);
*/
			} catch (ParseException e){e.printStackTrace();}
		}
	}
}
