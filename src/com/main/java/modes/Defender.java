package com.main.java.modes;

import java.util.Hashtable;

import com.main.java.games.Games;
import com.main.java.games.Mastermind;

/**
 * <p>Ce mode de jeu, Defender, est une classe enfant de "Modes".</p>
 * 
 * <p>Ce mode de jeu consiste à faire saisir une combinaison secrète à l'utilisateur puis la faire deviner à l'ordinateur dans un nombre d'essais imparti.</p>
 * 
 * @see Modes
 * @see Games
 * @see Modes#userCombination
 * @see Defender#init(Games)
 * @see Defender#engine(Games)
 * @see Defender#endGameConditions(String, int, int, String)
 * 
 * @author Ayrton
 * @version 1.0
 *
 */
public class Defender extends Modes {
	
	/**
	 * <p>Cette méthode initialise le mode de jeu "Defender"</p>
	 * <p>L'initialisation du mode de jeu permet :
	 * <ul>
	 * 	<li>D'initialiser le jeu choisi antérieurement</li>
	 * 	<li>D'instancier et de faire saisir une combinaison secrète à l'utilisateur</li>
	 * 	<li>De vérifier la conformité de la combinaison</li>
	 * 	<li>De vérifier si la combinaison saisi contienent plusieurs fois la même couleur (utile seulement pour le jeu du "Mastermind"</li>
	 * </ul>
	 * </p>
	 * 
	 * @see Defender#engine(Games)
	 * @see Modes#userCombination
	 * @see Modes#init(Games)
	 * @see Modes#verificationOfCompliance(Games, String)
	 * @see Modes#sc
	 * @see Games#getNbrCombi()
	 * @see Games#setSeveralTimesSameColor(boolean)
	 * @see Games
	 * @see Mastermind
	 */
	public void init(Games game) {

		game.initGame();
		
		while(true) {
			System.out.println("Entrez votre combinaison secrète de "+game.getNbrCombi()+" chiffres: ");
			userCombination = sc.next();
			if(this.verificationOfCompliance(game, userCombination)) {
				break;
			}
		}
		
		for(int i = 0; i < game.getNbrCombi(); i++) {
			for(int j = i+1; j < game.getNbrCombi(); j++) {
				if(userCombination.substring(i, i+1).equals(userCombination.substring(j, j+1))) {
					game.setSeveralTimesSameColor(true);
					break;
				}
			}
		}
	}
	
	/**
	 * <p>La méthode moteur du mode de jeu "Defender".</p>
	 * <p>Cette méthode se charge de :
	 * <ul>
	 * 	<li>D'afficher la combinaison secrète si le mode développeur a été préalablement activé</li>
	 * 	<li>Faire devinez la combinaison secrète à l'ordinateur</li>
	 * 	<li>D'obtenir le résultat de la comparaison entre la combinaison et de la proposition en fonction du jeu qui a été choisi</li>
	 * 	<li>D'obtenir ce même résultat formaté</li>
	 * 	<li>D'afficher la proposition suivi du résultat formaté</li>
	 * 	<li>De faire vérifier les conditions de fin de partie, comme par exemple : le nombre d'essais imparti</li>
	 * </ul>
	 * </p>
	 * 
	 * @see Defender#init(Games)
	 * @see Modes#userCombination
	 * @see Defender#endGameConditions(String, int, int, String)
	 * @see Modes#engine(Games)
	 * @see Modes#verificationOfCompliance(Games, String)
	 * @see Games#displayResultsCombi(Hashtable)
	 * @see Games#verifCombi(String, String)
	 * @see Games
	 */
	public void engine (Games game) {
		
		Hashtable<String,Integer> results = new Hashtable<String,Integer>();
		String out = "";
		
		if(game.isDevMode()) {
			System.out.println("La combinaison secrète est : "+this.userCombination);
		}
		
		for(int i = 0; i < game.getNbrTry(); i++) {
			
			String proposal = game.defenderCombi(results);

			results = game.verifCombi(this.userCombination, proposal);
			out = game.displayResultsCombi(results);
			
			System.out.println("Proposition : "+proposal+" -> Réponse : "+out);
			
			if(this.endGameConditions(proposal, i, game.getNbrTry(), "")) {
				break;
			}
			
		}
	}
	
	/**
	 * <p>La méthode vérifiant les conditions de fin de partie pour le mode de jeu "Defender".</p>
	 * <p>A chaque tour une des trois conditions est validé et le résultat est retourné sous la forme d'un boolean :
	 * <ul>
	 * 	<li>Si la proposition de l'ordinateur est égale à la combinaison secrète de l'utilisateur => Condition de fin de partie validé, retourne vrai (true)  => l'utilisateur perd / l'ordinateur gagne </li>
	 * 	<li>Sinon si le nombre d'essai autorisé a été atteint => Condition de fin de partie validé, retourne vrai (true) => l'utilisateur gagne / l'ordinateur perd</li>
	 * 	<li>Sinon => Condition de fin de partie non validé, retourne faux (false) => Passage au tour suivant</li>
	 * </ul>
	 * 
	 * @see Modes#endGameConditions(String, int, int, String)
	 * @see Defender#engine(Games)
	 */
	public boolean endGameConditions(String proposal, int count, int nbrMaxTry, String proposal2) {
		
		if(userCombination.equals(proposal)) {
			System.out.println("\nVous avez perdu ! L'ordinateur a trouvé votre combinaison secrète en "+(count+1)+" essais.");
			return true;
		}
		else if(count == nbrMaxTry-1) {
			System.out.println("\nBravo ! Vous avez gagné ! L'ordinateur n'a pas réussi à trouver votre combinaison secrète dans les "+nbrMaxTry+" essais imparti.");
			return true;
		}
		else {
			return false;
		}
	}
}
