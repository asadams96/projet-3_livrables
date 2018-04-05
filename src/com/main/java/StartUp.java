package com.main.java;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.main.java.configurations.Config;
import com.main.java.configurations.ReadProperties;
import com.main.java.games.Games;
import com.main.java.games.Mastermind;
import com.main.java.games.SearchMoreLess;
import com.main.java.modes.Challenger;
import com.main.java.modes.Defender;
import com.main.java.modes.Duel;
import com.main.java.modes.Modes;

/**
 * <p>Cette classe, StartUp, permet de facilit� le lancement des jeux ainsi que des modes de jeu</p>
 * <p>Elle est compos�e :
 * <ul>
 * 	<li>D'un attribut Scanner</li>
 * 	<li>D'un attribut Games</li>
 *  <li>D'un attribut Modes</li>
 *  <li>D'un attribut boolean de premier lancement</li>
 *  <li>D'une m�thode de choix de jeu</li>
 *  <li>D'une m�thode de choix de mode de jeu</li>
 *  <li>D'une m�thode de lancement</li>
 * </ul>
 * </p>
 * 
 * <p>De plus, un objet de la classe StartUp doit �tre instancier en "static" afin que le boolean "firstLaunch" fonctionne correctement lors d'une nouvelle partie en ne re-cr�ant pas une instance de StartUp</p>
 * 
 * @see StartUp#sc
 * @see StartUp#game
 * @see StartUp#mode
 * @see StartUp#firstLaunch
 * @see StartUp#choiceGame()
 * @see StartUp#choiceMode()
 * @see StartUp#launch()
 * @see Modes
 * @see Challenger
 * @see Defender
 * @see Duel
 * @see Games
 * @see SearchMoreLess
 * @see Mastermind
 * 
 * @author Ayrton
 * @version 1.0
 *
 */
public class StartUp {
	/**
	 * <p>Le log de la classe StartUp.java</p>
	 */
	final private Logger logStartUp = Logger.getLogger(StartUp.class);
	
	/**
	 * <p>Attribut permettant de r�cup�rer les saisies de l'utilisateur.</p>
	 * 
	 * @see StartUp#choiceGame()
	 * @see StartUp#choiceMode()
	 */
	private Scanner sc = new Scanner(System.in);
	
	/**
	 * <p>Attribut permettant de stocker le choix du jeu.</p>
	 * 
	 * @see StartUp#choiceGame()
	 * @see Games
	 */
	private Games game;
	
	/**
	 * <p>Attribut permettant de stocker le choix du mode de jeu.</p>
	 * 
	 * @see StartUp#choiceMode()
	 * @see Modes
	 */
	private Modes mode;
	
	/**
	 * <p>Attribut de type boolean permettant de charger les param�tres de configuration et d'afficher une formule de politesse au premier lancement (Bonjour, ect..)</p>
	 * 
	 * @see StartUp#launch()
	 * @see ReadProperties
	 */
	private boolean firstLaunch = true;
	
	/**
	 * <p>Attribut de type boolean permettant de modifier le mode d�veloppeur en passant outre le fichier de configuration "config.properties".<P>
	 * 
	 * @see StartUp#property
	 * @see StartUp#StartUp()
	 * @see StartUp#StartUp(boolean)
	 * @see StartUp#launch()
	 */
	private boolean devMode;
	
	/**
	 * <p>Attribut de type boolean permettant de savoir quel constructeur � �t� utilis�.<p>
	 * 
	 * @see StartUp#devMode
	 * @see StartUp#StartUp()
	 * @see StartUp#StartUp(boolean)
	 * @see StartUp#launch()
	 */
	private boolean property = false;
	
	/**
	 * <p>Le contructeur par d�faut o� le mode d�veloppeur est d�finit par le fichier "config.properties", en l'absence de ce fichier ce mode est d�finit par la valeur par d�faut.</p>
	 * 		pr�sente dans l'�num�ration "Config.java"<p>
	 * 
	 * @see StartUp#property
	 * @see StartUp#launch()
	 * @see StartUp#StartUp(boolean)
	 * @see Config
	 * @see Main
	 */
	public StartUp() {}
	
	/**
	 * <p>Le constructeur permettant de modifier le mode d�veloppeur en passant outre le fichier de configuration "config.properties".<p>
	 * 
	 * @param pDevMode
	 * 					Le param�tre de type boolean informant de l'activation (vrai => true) ou non (faux => false) du mode d�veloppeur
	 * 
	 * @see StartUp#property
	 * @see StartUp#devMode
	 * @see StartUp#StartUp()
	 * @see StartUp#launch()
	 * @see Config
	 * @see Main
	 */
	public StartUp(boolean pDevMode) {
		this.devMode = pDevMode;
		this.property = true;
	}
	
	/**
	 * <p>Methode permettant de choisir le jeu au quel l'utilisateur souhaite jouer</p>
	 * <p>De plus seules les valeurs demand�es seront accept�es lors de la saisie => une demande est effectu� jusqu'a que le choix soit conforme</p>
	 * 
	 * @see StartUp#game
	 * @see StartUp#sc
	 * @see StartUp#choiceMode()
	 * @see StartUp#launch()
	 * @see Games
	 * @see Mastermind
	 * @see SearchMoreLess
	 */
	public void choiceGame() {
		
		logStartUp.info("D�but de choiceGame()");
		
		int choice;
		boolean again = true;
		
		while(again) {			
			try {
				System.out.println("\nA quel jeu voulez - vous jouer ?");
				System.out.println("          1. Recherche +/-          2. Mastermind");

				choice = sc.nextInt();
		
				switch(choice) {
					case 1: 
						this.game = new SearchMoreLess();
						again = false;
						break;
					case 2:
						this.game = new Mastermind();
						again = false;
						break;
					default:
						logStartUp.error("Saisie invalide dans choiceGame() => ["+choice+"]");
						break;
					
				}
			} catch(InputMismatchException e) {
				
				logStartUp.error("Saisie invalide dans choiceGame() => ["+sc.nextLine()+"]");				
			} 
		}
		
		logStartUp.info("Fin de choiceGame()");
	}
	
	/**
	 * <p>Methode permettant de choisir le mode de jeu au quel l'utilisateur souhaite jouer</p>
	 * <p>De plus seules les valeurs demand�es seront accept�es lors de la saisie => une demande est effectu� jusqu'a que le choix soit conforme</p>
	 * 
	 * @see StartUp#mode
	 * @see StartUp#sc
	 * @see StartUp#choiceGame()
	 * @see StartUp#launch()
	 * @see Modes
	 * @see Challenger
	 * @see Defender
	 * @see Duel
	 */
	public void choiceMode() {
		
		logStartUp.info("D�but de choiceMode()");
		
		int choice;
		boolean again = true;
		
		while(again) {
			try {
				System.out.println("\nDans quel mode souhaitez - vous jouer ?");
				System.out.println("          1. Challenger          2. D�fenseur          3. Duel");
				choice = sc.nextInt();
		
				switch(choice) {
					case 1: 
						this.mode = new Challenger();
						again = false;
						break;
					case 2:
						this.mode = new Defender();
						again = false;
						break;
					case 3:
						this.mode = new Duel();
						again = false;
						break;
					default:
						logStartUp.error("Saisie invalide dans choiceMode() => ["+choice+"]");
						break;
				}
			}catch(InputMismatchException e) {
				
				logStartUp.error("Saisie invalide dans choiceMode() => ["+sc.nextLine()+"]");				
			}
		}
		
		logStartUp.info("Fin de choiceMode()");
	}
	
	/**
	 * <p>Methode permettant de lancer les m�thode de choix de jeu et de choix de mode de jeu puis de lancer le jeu ainsi que son mode de jeu associ�</p>
	 * <p>De plus, une condition est effectuer � chaque appel de m�thode afin de savoir si c'est le premier lancement et au quel cas, les param�tres de l'application sont definis et une formule de politesse est afficher</p>
	 * 
	 * @see StartUp#firstLaunch
	 * @see StartUp#game
	 * @see StartUp#mode
	 * @see StartUp#devMode
	 * @see StartUp#property
	 * @see StartUp#choiceGame()
	 * @see StartUp#choiceMode()
	 * @see ReadProperties
	 * @see Config
	 * @see Games
	 * @see Modes
	 */
	public void launch() {
		if(firstLaunch) {
			
			logStartUp.info("D�but de launch()");
			
			new ReadProperties();
			
			if(this.property) {
				Config.GameParameters.setDevMode(this.devMode);
			}
			
			System.out.println("Bonjour et bienvenue !");
			firstLaunch = false;
		}
		this.choiceGame();
		this.choiceMode();
		this.mode.init(this.game);
		this.mode.engine(this.game);
		this.mode.endGame(this.game);
		
		logStartUp.info("Fin de launch()");
	}
}
