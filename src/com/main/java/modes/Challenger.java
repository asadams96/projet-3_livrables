package com.main.java.modes;

import java.util.Hashtable;
import java.util.Random;

import com.main.java.games.Games;

/**
 * <p>Ce mode de jeu, Challenger, est une classe enfant de "Modes".</p>
 * 
 * <p>Ce mode de jeu consiste � faire g�n�rer une combinaison secr�te par l'ordinateur puis la faire deviner � l'utilisateur dans un nombre d'essais imparti.</p>
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
	 * <p>Cette m�thode initialise le mode de jeu "Challenger"</p>
	 * <p>L'initialisation du mode de jeu permet :
	 * <ul>
	 * 	<li>D'instancier la combinaison avec une valeur par d�faut</li>
	 * 	<li>D'initialiser le jeu choisi ant�rieurement</li>
	 * 	<li>De g�n�rer une combinaison secr�te al�atoire</li>
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
	 * <p>La m�thode moteur du mode de jeu "Challenger".</p>
	 * <p>Cette m�thode se charge de :
	 * <ul>
	 * 	<li>D'afficher la combinaison secr�te si le mode d�veloppeur a �t� pr�alablement activ�</li>
	 * 	<li>Faire devinez la combinaison secr�te � l'utilisateur en lui permettant de saisir une proposition conforme</li>
	 * 	<li>D'obtenir le r�sultat de la comparaison entre la combinaison et de la proposition en fonction du jeu qui a �t� choisi</li>
	 * 	<li>D'obtenir ce m�me r�sultat format�</li>
	 * 	<li>De faire v�rifier les conditions de fin de partie, comme par exemple : le nombre d'essais imparti</li>
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
			System.out.println("La combinaison secr�te est : "+this.computerCombination);
		}
		
		for(int i = 0; i < game.getNbrMaxTry(); i++) {
	
			while(true) {
				System.out.print("\nDevinez la combinaison secr�te : ");
				userProposal = sc.next();
				if(this.verificationOfCompliance(game, userProposal)) {
					break;
				}
			}
			
			results = game.comparison(this.computerCombination, userProposal);
			out = game.formattingTheResults(results);
			
			System.out.println("Proposition : "+userProposal+" -> R�ponse : "+out);
			
			if(this.endGameConditions(userProposal, i, game.getNbrMaxTry(), "")) {
				break;
			}
		}
	}
	
	/**
	 * <p>La m�thode v�rifiant les conditions de fin de partie pour le mode de jeu "Challenger".</p>
	 * <p>A chaque tour une des trois conditions est valid� et le r�sultat est retourn� sous la forme d'un boolean :
	 * <ul>
	 * 	<li>Si la proposition de l'utilisateur est �gale � la combinaison secr�te de l'ordinateur => Condition de fin de partie valid�, retourne vrai (true)  => l'utilisateur gagne, l'ordinateur perd </li>
	 * 	<li>Sinon si le nombre d'essai autoris� a �t� atteint => Condition de fin de partie valid�, retourne vrai (true) => l'utilisateur perd, l'ordinateur gagne</li>
	 * 	<li>Sinon => Condition de fin de partie non valid�, retourne faux (false) => Passage au tour suivant</li>
	 * </ul>
	 * 
	 * @see Modes#endGameConditions(String, int, int, String)
	 * @see Modes#endGame(Games)
	 * @see Challenger#engine(Games)
	 */
	public boolean endGameConditions(String userProposal, int count, int nbrMaxTry, String computerProposal) {
		
		if(computerCombination.equals(userProposal)) {
			System.out.println("\nBravo ! Vous avez gagn� ! Vous avez r�ussi � trouver la combinaison secr�te de l'ordinateur.");
			return true;
		}
		else if(count == nbrMaxTry-1) {
			System.out.println("\nVous avez perdu ! Le nombre de "+nbrMaxTry+" essais autoris� a �t� atteint.\nLa combinaison secr�te �tait : "+computerCombination+".");
			return true;
		}
		else {
			return false;
		}
	}
}
