package graphiquePackage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import movePackage.Engine;
import constantesPackage.Constantes;

public class Fenetre extends JFrame{
	/*
	 * 1 CONSTRUCTEUR
	 */
	public Fenetre (String title){
		super(title);
		setSize(800, 800);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	/*
	 * FIN CONSTRUCTEUR
	 */
	
	/*
	 * Methodes Public de Fenetre
	 */	
	public void disposition_v2 (Engine referenceEngi){
		Panneau panneauDeJeu = new Panneau (referenceEngi);
		MenuBar barre = new MenuBar(referenceEngi, panneauDeJeu);
		add(panneauDeJeu);
		setJMenuBar(barre);
	}
}
