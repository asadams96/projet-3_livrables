package com.main.java.modes;

import java.util.Hashtable;
import java.util.Random;

import com.main.java.games.Games;

/**
 * <p>Ce mode de jeu, Challenger, est une classe enfant de "Modes".</p>
 * 
 * <p>Ce mode de jeu consiste à faire générer une combinaison secrète par l'ordinateur puis la faire deviner à l'utilisateur dans un nombre d'essais imparti.</p>
 * 
 * @see Modes
 * @see Games
 * @see Modes#computerCombination
 * @see Challenger#init(Games)
 * @see Challenger#engine(Games)
 * @see Challenger#endGameConditions(String, int, int, String)
 * 
 * @author Ayrton
 * @version 1.0
 *
 */
public class Challenger extends Modes {
		
	/**
	 * <p>Cette méthode initialise le mode de jeu "Challenger"</p>
	 * <p>L'initialisation du mode de jeu permet :
	 * <ul>
	 * 	<li>D'instancier la combinaison avec une valeur par défaut</li>
	 * 	<li>D'initialiser le jeu choisi antérieurement</li>
	 * 	<li>De générer une combinaison secrète aléatoire</li>
	 * </ul>
	 * </p>
	 * 
	 * @see Challenger#engine(Games)
	 * @see Modes#computerCombination
	 * @see Modes#init(Games)
	 * @see Games
	 */
	public void init(Games game) {
		computerCombination = "";
		
		game.initGame();
		
		for(int i = 0; i < game.getNbrCombi(); i++) {
			
			this.computerCombination += new Random().nextInt(game.getNbrUsableFigures());
		}
	}
	
	/**
	 * <p>La méthode moteur du mode de jeu "Challenger".</p>
	 * <p>Cette méthode se charge de :
	 * <ul>
	 * 	<li>D'afficher la combinaison secrète si le mode développeur a été préalablement activé</li>
	 * 	<li>Faire devinez la combinaison secrète à l'utilisateur en lui permettant de saisir une proposition conforme</li>
	 * 	<li>D'obtenir le résultat de la comparaison entre la combinaison et de la proposition en fonction du jeu qui a été choisi</li>
	 * 	<li>D'obtenir ce même résultat formaté</li>
	 * 	<li>De faire vérifier les conditions de fin de partie, comme par exemple : le nombre d'essais imparti</li>
	 * </ul>
	 * </p>
	 * 
	 * @see Challenger#init(Games)
	 * @see Modes#computerCombination
	 * @see Challenger#endGameConditions(String, int, int, String)
	 * @see Modes#engine(Games)
	 * @see Modes#verificationOfCompliance(Games, String)
	 * @see Games#formattingTheResults(Hashtable)
	 * @see Games#comparison(String, String)
	 * @see Games#getNbrMaxTry()
	 * @see Games
	 */
	public void engine(Games game) {
		Hashtable<String,Integer> results = new Hashtable<String,Integer>();
		String userProposal;
		String out;
		
		if(game.isDevMode()) {
			System.out.println("La combinaison secrète est : "+this.computerCombination);
		}
		
		for(int i = 0; i < game.getNbrMaxTry(); i++) {
	
			while(true) {
				System.out.print("\nDevinez la combinaison secrète : ");
				userProposal = sc.next();
				if(this.verificationOfCompliance(game, userProposal)) {
					break;
				}
			}
			
			results = game.comparison(this.computerCombination, userProposal);
			out = game.formattingTheResults(results);
			
			System.out.println("Proposition : "+userProposal+" -> Réponse : "+out);
			
			if(this.endGameConditions(userProposal, i, game.getNbrMaxTry(), "")) {
				break;
			}
		}
	}
	
	/**
	 * <p>La méthode vérifiant les conditions de fin de partie pour le mode de jeu "Challenger".</p>
	 * <p>A chaque tour une des trois conditions est validé et le résultat est retourné sous la forme d'un boolean :
	 * <ul>
	 * 	<li>Si la proposition de l'utilisateur est égale à la combinaison secrète de l'ordinateur => Condition de fin de partie validé, retourne vrai (true)  => l'utilisateur gagne, l'ordinateur perd </li>
	 * 	<li>Sinon si le nombre d'essai autorisé a été atteint => Condition de fin de partie validé, retourne vrai (true) => l'utilisateur perd, l'ordinateur gagne</li>
	 * 	<li>Sinon => Condition de fin de partie non validé, retourne faux (false) => Passage au tour suivant</li>
	 * </ul>
	 * 
	 * @see Modes#endGameConditions(String, int, int, String)
	 * @see Modes#endGame(Games)
	 * @see Challenger#engine(Games)
	 */
	public boolean endGameConditions(String userProposal, int count, int nbrMaxTry, String computerProposal) {
		
		if(computerCombination.equals(userProposal)) {
			System.out.println("\nBravo ! Vous avez gagné ! Vous avez réussi à trouver la combinaison secrète de l'ordinateur.");
			return true;
		}
		else if(count == nbrMaxTry-1) {
			System.out.println("\nVous avez perdu ! Le nombre de "+nbrMaxTry+" essais autorisé a été atteint.\nLa combinaison secrète était : "+computerCombination+".");
			return true;
		}
		else {
			return false;
		}
	}
}
