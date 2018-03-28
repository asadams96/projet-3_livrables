package com.main.java.modes;

import java.util.Hashtable;
import java.util.Random;

import com.main.java.games.Games;
import com.main.java.games.Mastermind;

/**
 * <p>Ce mode de jeu, Duel, est une classe enfant de "Modes".</p>
 * 
 * <p>Ce mode de jeu consiste à faire saisir une combinaison secrète à l'utilisateur et à l'ordianteur puis leur faire deviner dans un système de tour-par-tour. Le premier à trouver la combinaison de l'autre a gagné.</p>
 * 
 * @see Modes
 * @see Games
 * @see Modes#userCombination
 * @see Modes#computerCombination
 * @see Duel#init(Games)
 * @see Duel#engine(Games)
 * @see Duel#endGameConditions(String, int, int, String)
 * 
 * @author Ayrton
 * @version 1.0
 *
 */
public class Duel extends Modes {
	
	/**
	 * <p>Cette méthode initialise le mode de jeu "Duel"</p>
	 * <p>L'initialisation du mode de jeu permet :
	 * <ul>
	 * 	<li>D'instancier la combinaison de l'ordinateur avec une valeur par défaut</li>
	 * 	<li>D'initialiser le jeu choisi antérieurement</li>
	 * 	<li>D'instancier et de faire saisir une combinaison secrète à l'utilisateur</li>
	 * 	<li>De vérifier la conformité de la combinaison saisi par l'utilisateur</li>
	 * 	<li>De vérifier si la combinaison saisi contienent plusieurs fois la même couleur (utile seulement pour le jeu du "Mastermind"</li>
	 * 	<li>De générer une combinaison secrète aléatoire à l'ordinateur</li>
	 * </ul>
	 * </p>
	 * 
	 * @see Duel#engine(Games)
	 * @see Modes#computerCombination
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
		computerCombination = "";
		
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
				
		for(int i = 0; i < game.getNbrCombi(); i++) {
			
			computerCombination += new Random().nextInt(game.getNbrUsableFigures());
		}		
	}
	
	/**
	 * <p>La méthode moteur du mode de jeu "Duel".</p>
	 * <p>Cette méthode se charge de :
	 * <ul>
	 * 	<li>D'afficher les combinaisons secrètes si le mode développeur a été préalablement activé</li>
	 * 	<li>Faire devinez à l'utilisateur la combinaison secrète de l'ordinateur</li>
	 * 	<li>Verifier la conformité de la proposition de l'utilisateur</li>
	 * 	<li>D'obtenir le résultat de la comparaison entre la combinaison de l'ordinateur et de la proposition de l'utilisateur en fonction du jeu qui a été choisi</li>
	 * 	<li>D'obtenir ce même résultat formaté</li>	
	 * 	<li>D'afficher la proposition de l'utilisateur suivi du résultat formaté</li>
	 * 	<li>Faire devinez à l'ordinateur la combinaison secrète de l'utilisateur</li>
	 * 	<li>D'obtenir le résultat de la comparaison entre la combinaison de l'utilisateur et de la proposition de l'ordinateur en fonction du jeu qui a été choisi</li>
	 * 	<li>D'obtenir ce même résultat formaté</li>
	 * 	<li>D'afficher la proposition suivi du résultat formaté</li>
	 * 	<li>De faire vérifier les conditions de fin de partie</li>
	 * </ul>
	 * </p>
	 * 
	 * @see Duel#init(Games)
	 * @see Modes#userCombination
	 * @see Modes#computerCombination
	 * @see Duel#endGameConditions(String, int, int, String)
	 * @see Modes#engine(Games)
	 * @see Modes#verificationOfCompliance(Games, String)
	 * @see Games#proposalsGenerator(Hashtable)
	 * @see Games#formattingTheResults(Hashtable)
	 * @see Games#comparison(String, String)
	 * @see Games
	 */
	public void engine (Games game) {
		
		Hashtable<String,Integer> userResults = new Hashtable<String,Integer>();
		Hashtable<String,Integer> computerResults = new Hashtable<String,Integer>();

		String userProposal;
		String computerProposal;
		String out = "";
		
		if(game.isDevMode()) {
			System.out.println("Votre combinaison secrète est : "+userCombination);
			System.out.println("La combinaison secrète de l'ordinateur est : "+computerCombination);
		}
		
		while(true) {
			
			while(true) {
				
				System.out.print("\nDevinez la combinaison secrète de l'ordinateur : ");
				userProposal = sc.next();
				
				if(this.verificationOfCompliance(game, userProposal)) {
					break;
				}
			}
			
			userResults = game.comparison(computerCombination, userProposal);
			out = game.formattingTheResults(userResults);
			
			System.out.println("Votre proposition : "+userProposal+" -> Réponse : "+out);
			
		
			
			computerProposal = game.proposalsGenerator(computerResults);
			computerResults = game.comparison(userCombination, computerProposal);
			out = game.formattingTheResults(computerResults);
			
			System.out.println("Proposition de l'ordinateur : "+computerProposal+" -> Réponse : "+out);
			
			if(endGameConditions(userProposal, 0, 0, computerProposal)) {
				break;
			}
		}
	}
	
	/**
	 * <p>La méthode vérifiant les conditions de fin de partie pour le mode de jeu "Duel".</p>
	 * <p>A chaque tour une des quatre conditions est validé et le résultat est retourné sous la forme d'un boolean :
	 * <ul>
	 * 	<li>Si la proposition de l'ordinateur est égale à la combinaison de l'utilisateur et que la proposition de l'utilisateur est égale à la combinaison de l'ordinateur 
	 * 		=> Condition de fin de partie validé, retourne vrai (true)  => Egalité entre les deux joueurs </li>
	 * 	<li>Sinon si la proposition de l'ordinateur égale la combinaison de l'utilisateur => Condition de fin de partie validé, retourne vrai (true) => l'utilisateur perd / l'ordinateur gagne</li>
	 * 	<li>Sinon si la proposition de l'utilisateur égale la combinaison de l'ordinateur => Condition de fin de partie validé, retourne vrai (true) => l'utilisateur gagne / l'ordinateur perd</li>
	 * 	<li>Sinon => Condition de fin de partie non validé, retourne faux (false) => Passage au tour suivant</li>
	 * </ul>
	 * 
	 * @see Modes#computerCombination
	 * @see Modes#userCombination
	 * @see Modes#endGameConditions(String, int, int, String)
	 * @see Duel#engine(Games)
	 */
	public boolean endGameConditions(String userProposal, int count, int nbrMaxTry, String computerProposal) {
		if(userCombination.equals(computerProposal) && computerCombination.equals(userProposal)) {
			System.out.println("\nÉgalité ! L'ordinateur et vous avez trouvé la combinaison de l'autre au même tour.");
			return true;
		}
		else if(userCombination.equals(computerProposal)) {
			System.out.println("\nVous avez perdu ! L'ordinateur a trouvé votre combinaison le premier.\nLa combinaison de l'ordinateur était : "+computerCombination);
			return true;
		}
		else if(computerCombination.equals(userProposal)) {
			System.out.println("\nBravo ! Vous avez gagné ! Vous avez réussi à trouver la combinaison secrète de l'ordinateur.");
			return true;
		}
		else {
			return false;
		}
	}

}
