package com.main.java.modes;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.main.java.Main;
import com.main.java.StartUp;
import com.main.java.games.Games;

/**
 * <p>Cette classe, Modes, est la classe abstraite parente de tous les modes de jeux.</p>
 * <p>Un mode de jeu est composé de plusieurs attributs et méthodes communs :
 * <ul>
 * 	<li>Une méthode d'initialisation : init(Games)</li>
 * 	<li>Une méthode moteur : engine(Games)</li>
 * 	<li>Une méthode de fin de jeu : endGame(Games)</li>
 * 	<li>Une méthode de conformité de saisie : verificationOfCompliance(Games,String)</li>
 * 	<li>Une méthode de vérification des conditions de fin de partie : endGameConditions(String, int, int, String)</Li>
 * 	<li>Un attribut de récupération de saisie de type Scanner : sc</li>
 * 	<li>Deux attributs de type chaine de caractères (String) : 
 * 		<ul>
 * 			<li>userCombination</li>
 * 			<li>computerCombination</li>
 * 		</ul>
 * 	</li>
 * </ul>
 * </p>
 * 
 * <p>De plus la classe abstraite "Modes" fonctionne en tandem avec la classe abstraite "Games".</p>
 * 
 * @see Challenger
 * @see Defender
 * @see Duel
 * @see Modes#init(Games)
 * @see Modes#engine(Games)
 * @see Modes#endGame(Games)
 * @see Modes#endGameConditions(String, int, int, String)
 * @see Modes#verificationOfCompliance(Games, String)
 * @see Modes#userCombination
 * @see Modes#computerCombination
 * @see Modes#sc
 * @see Games
 * @see StartUp
 * 
 * @author Ayrton
 * @version 1.0
 *
 */
public abstract class Modes {

	/**
	 * <p>L'attribut servant à récupérer les saisies de l'utilisateur.</p>
	 * 
	 * @see Modes#endGame(Games)
	 * @see Challenger#engine(Games)
	 * @see Defender#init(Games)
	 * @see Duel#init(Games)
	 * @see Duel#engine(Games)
	 */
	protected Scanner sc = new Scanner(System.in);
	
	/**
	 * <p>L'attribut permettant de stocker la combinaison secrète de l'utilisateur.</p>
	 * 
	 * @see Defender#init(Games)
	 * @see Defender#engine(Games)
	 * @see Defender#endGameConditions(String, int, int, String)
	 * @see Duel#init(Games)
	 * @see Duel#engine(Games)
	 * @see Duel#endGameConditions(String, int, int, String)
	 * @see Defender
	 * @see Duel
	 */
	protected String userCombination;
	
	/**
	 * <p>L'attribut permettant de stocker la combinaison secrète de l'ordinateur.</p>
	 * 
	 * @see Challenger#init(Games)
	 * @see Challenger#engine(Games)
	 * @see Challenger#endGameConditions(String, int, int, String)
	 * @see Duel#init(Games)
	 * @see Duel#engine(Games)
	 * @see Duel#endGameConditions(String, int, int, String)
	 * @see Challenger
	 * @see Duel
	 */
	protected String computerCombination;
	
	/**
	 * <P>La méthode servant à initialisé un mode de jeu avec le du jeu choisi.</p>
	 * 
	 * @param game
	 * 				Le jeu choisi
	 * 
	 * @see Modes#engine(Games)
	 * @see Modes#endGame(Games)
	 * @see Games
	 */
	public void init(Games game) {}

	/**
	 * <p>La méthode moteur permettant de faire fonctionner le mode de jeu avec le jeu choisi.</p>
	 * 
	 * @param game
	 * 				Le jeu choisi
	 * 
	 * @see Modes#init(Games)
	 * @see Modes#endGame(Games)
	 * @see Games
	 */
	public void engine (Games game) {}
	
	/**
	 * <p>La méthode qui vérifie les conditions de fin de partie en fonction des paramètres du jeu choisi.</p>
	 * <p>Les paramètres de la méthode ne sont pas tous utlisé lors de l'appel de cette méthode par une classe enfant de "Games" :
	 * <ul>
	 * 	<li>"userProposal" est utilisé dans les modes "Challenger" et "Duel".</li>
	 * 	<li>"count" et nbrMaxTry sont utilisés dans les modes "Challenger" et "Defender"</li>
	 * 	<li>"computerProposal" est utilisé dans les modes "Defender" et "Duel"</li>
	 * </ul>
	 * </p>
	 * 
	 * @param userProposal
	 * 				   La proposition de l'utilisateur sous forme de chaine de caractères (String)
	 * 
	 * @param count
	 * 				Le compteur d'essai sous forme d'entier  
	 * 
	 * @param nbrMaxTry
	 * 					Le nombre maximum de tour sous forme d'entier
	 * 
	 * @param computerProposal
	 * 					La proposition de l'ordinateur sous forme de chaine de caractères (String)
	 * 
	 * @return Un boolean qui informe si les conditions de fin de partie ont été remplis (vrai => true) ou non (faux => false)
	 * 
	 * @see Modes#endGame(Games)
	 * @see Challenger#endGameConditions(String, int, int, String)
	 * @see Defender#endGameConditions(String, int, int, String)
	 * @see Duel#endGameConditions(String, int, int, String)
	 */
	public boolean endGameConditions(String userProposal, int count, int nbrMaxTry, String computerProposal) {
		return false;
	}
	
	/**
	 * <p>La méthode de fin de jeu permettant de rejouer une partie avec le jeu choisi antérieurement, de changer de jeu ou de quitter.</p>
	 * 
	 * @param game
	 * 				Le jeu choisi
	 * @see Modes#init(Games)
	 * @see Modes#engine(Games)
	 * @see Games
	 * @see StartUp
	 */
	public void endGame(Games game) {
		int choice;
		boolean again = true;
		
		while(again) {
			try {
				System.out.println("\n1. Rejouer          2. Changer de jeu          3. Quitter");
				choice = sc.nextInt();
		
				switch(choice) {
					case 1: 
						this.init(game);
						this.engine(game);
						this.endGame(game);
						again = false;
						break;
					case 2:
						Main.start.launch();
						again = false;
						break;
					case 3:
						System.out.println("Au revoir.");
						again = false;
						break;
				}
			}catch(InputMismatchException e) {sc.nextLine();}
		}		
	}
	
	/**
	 * <p>La méthode de vérification de la conformité de saisie (pour les combinaison et les propositions) en fonction des paramètres du jeu choisi, comme par exemple le format de la combinaison.</p>
	 * <p>De plus cette méthode affiche le motif d'invalidité de la saisie.</p>
	 * 
	 * @param game
	 * 				Le jeu choisi
	 * 
	 * @param str
	 * 				La saisie de l'utilisateur sous forme de chaine de caractères (String)
	 * 
	 * @return Un boolean informant si la saisie est valide (vrai => true), ou non (faux => false)
	 * 
	 * @see Modes#init(Games)
	 * @see Modes#engine(Games)
	 * @see Games
	 */
	protected boolean verificationOfCompliance(Games game, String str) {
		boolean out = true;
		String informations = "";
		
		if(str.length() != game.getNbrCombi()) {
			informations += "\n!-- Veuillez respecter le format de "+game.getNbrCombi()+" chiffres --!";
			out = false;
		}

		for(int i = 0; i < str.length(); i++) {
			try {
				if(Integer.valueOf(str.substring(i, i+1)) >= game.getNbrUsableFigures() ) {
					informations += "\n!-- Saisi de chiffre(s) invalide(s) --! \n!-- Choisissez uniquement les chiffres suivants :";
					for(int j = 0; j < game.getNbrUsableFigures(); j++) {
						informations += " "+j;
					}
					informations += " --!";
					out = false;
					break;
				}
			}catch(NumberFormatException e) {
				informations += "\n!-- Saisissez uniquement des chiffres --!";
				out = false;
				break;
			}
		}
		informations += "\n";
		System.out.println(informations);
		
		return out;
	}
}
