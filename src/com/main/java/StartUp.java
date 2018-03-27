package com.main.java;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.main.java.games.Games;
import com.main.java.games.Mastermind;
import com.main.java.games.SearchMoreLess;
import com.main.java.modes.Challenger;
import com.main.java.modes.Defender;
import com.main.java.modes.Duel;
import com.main.java.modes.Modes;

/**
 * <p>Cette classe, StartUp, permet de facilité le lancement des jeux ainsi que des modes de jeu</p>
 * <p>Elle est composée :
 * <ul>
 * 	<li>D'un attribut Scanner</li>
 * 	<li>D'un attribut Games</li>
 *  <li>D'un attribut Modes</li>
 *  <li>D'un attribut boolean de premier lancement</li>
 *  <li>D'une méthode de choix de jeu</li>
 *  <li>D'une méthode de choix de mode de jeu</li>
 *  <li>D'une méthode de lancement</li>
 * </ul>
 * </p>
 * 
 * <p>De plus, un objet de la classe StartUp doit être instancier en "static" afin que le boolean "firstLaunch" fonctionne correctement lors d'une nouvelle partie en ne re-créant pas une instance de StartUp</p>
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
	 * <p>Attribut permettant de récupérer les saisies de l'utilisateur.</p>
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
	 * <p>Attribut de type boolean permettant l'affichage d'une formule de politesse au premier lancement (Bonjour, ect..)</p>
	 * 
	 * @see StartUp#launch()
	 */
	private boolean firstLaunch = true;		
	
	/**
	 * <p>Methode permettant de choisir le jeu au quel l'utilisateur souhaite jouer</p>
	 * <p>De plus seules les valeurs demandées seront acceptées lors de la saisie => une demande est effectué jusqu'a que le choix soit conforme</p>
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
				}
			}catch(InputMismatchException e) {sc.nextLine();} // Vide le chariot car si une exception est levé => Boucle infini
		}
	}
	
	/**
	 * <p>Methode permettant de choisir le mode de jeu au quel l'utilisateur souhaite jouer</p>
	 * <p>De plus seules les valeurs demandées seront acceptées lors de la saisie => une demande est effectué jusqu'a que le choix soit conforme</p>
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
		int choice;
		boolean again = true;
		
		while(again) {
			try {
				System.out.println("\nDans quel mode souhaitez - vous jouer ?");
				System.out.println("          1. Challenger          2. Défenseur          3. Duel");
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
				}
			}catch(InputMismatchException e) {sc.nextLine();}
		}
	}
	
	/**
	 * <p>Methode permettant de lancer les méthode de choix de jeu et de choix de mode de jeu puis de lancer le jeu ainsi que son mode de jeu associé</p>
	 * <p>De plus, une condition est effectuer à chaque appel de méthode afin de savoir si c'est le premier lancement et au quel cas, afficher une formule de politesse</p>
	 * 
	 * @see StartUp#firstLaunch
	 * @see StartUp#game
	 * @see StartUp#mode
	 * @see StartUp#choiceGame()
	 * @see StartUp#choiceMode()
	 * @see Games
	 * @see Modes
	 */
	public void launch() {
		if(firstLaunch) {
			System.out.println("Bonjour et bienvenue !");
			firstLaunch = false;
		}
		this.choiceGame();
		this.choiceMode();
		this.mode.init(this.game);
		this.mode.engine(this.game);
		this.mode.endGame(this.game);
	}
}
