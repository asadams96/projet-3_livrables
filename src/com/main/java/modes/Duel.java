package com.main.java.modes;

import java.util.Hashtable;
import java.util.Random;

import com.main.java.games.Games;
import com.main.java.games.Mastermind;

/**
 * <p>Ce mode de jeu, Duel, est une classe enfant de "Modes".</p>
 * 
 * <p>Ce mode de jeu consiste � faire saisir une combinaison secr�te � l'utilisateur et � l'ordianteur puis leur faire deviner dans un syst�me de tour-par-tour. Le premier � trouver la combinaison de l'autre a gagn�.</p>
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
	 * <p>Cette m�thode initialise le mode de jeu "Duel"</p>
	 * <p>L'initialisation du mode de jeu permet :
	 * <ul>
	 * 	<li>D'instancier la combinaison de l'ordinateur avec une valeur par d�faut</li>
	 * 	<li>D'initialiser le jeu choisi ant�rieurement</li>
	 * 	<li>D'instancier et de faire saisir une combinaison secr�te � l'utilisateur</li>
	 * 	<li>De v�rifier la conformit� de la combinaison saisi par l'utilisateur</li>
	 * 	<li>De v�rifier si la combinaison saisi contienent plusieurs fois la m�me couleur (utile seulement pour le jeu du "Mastermind"</li>
	 * 	<li>De g�n�rer une combinaison secr�te al�atoire � l'ordinateur</li>
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
		
		logModes.debug("D�but d'init()");

		computerCombination = "";
		
		game.initGame();

		while(true) {
			System.out.println("Entrez votre combinaison secr�te de "+game.getNbrCombi()+" chiffres: ");
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
		
		logModes.debug("Fin d'init()");
	}
	
	/**
	 * <p>La m�thode moteur du mode de jeu "Duel".</p>
	 * <p>Cette m�thode se charge de :
	 * <ul>
	 * 	<li>D'afficher les combinaisons secr�tes si le mode d�veloppeur a �t� pr�alablement activ�</li>
	 * 	<li>Faire devinez � l'utilisateur la combinaison secr�te de l'ordinateur</li>
	 * 	<li>Verifier la conformit� de la proposition de l'utilisateur</li>
	 * 	<li>D'obtenir le r�sultat de la comparaison entre la combinaison de l'ordinateur et de la proposition de l'utilisateur en fonction du jeu qui a �t� choisi</li>
	 * 	<li>D'obtenir ce m�me r�sultat format�</li>	
	 * 	<li>D'afficher la proposition de l'utilisateur suivi du r�sultat format�</li>
	 * 	<li>Faire devinez � l'ordinateur la combinaison secr�te de l'utilisateur</li>
	 * 	<li>D'obtenir le r�sultat de la comparaison entre la combinaison de l'utilisateur et de la proposition de l'ordinateur en fonction du jeu qui a �t� choisi</li>
	 * 	<li>D'obtenir ce m�me r�sultat format�</li>
	 * 	<li>D'afficher la proposition suivi du r�sultat format�</li>
	 * 	<li>De faire v�rifier les conditions de fin de partie</li>
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
		
		logModes.debug("D�but d'engine()");
		
		Hashtable<String,Integer> userResults = new Hashtable<String,Integer>();
		Hashtable<String,Integer> computerResults = new Hashtable<String,Integer>();

		String userProposal;
		String computerProposal;
		String out = "";
		int i = 0;
		
		if(game.isDevMode()) {
			System.out.println("Votre combinaison secr�te est : "+userCombination);
			System.out.println("La combinaison secr�te de l'ordinateur est : "+computerCombination);
		}
		
		while(true) {
			
			while(true) {
				
				System.out.print("\nDevinez la combinaison secr�te de "+game.getNbrCombi()+" chiffres de l'ordinateur : ");
				userProposal = sc.next();
				
				if(this.verificationOfCompliance(game, userProposal)) {
					break;
				}
			}
			
			userResults = game.comparison(computerCombination, userProposal);
			out = game.formattingTheResults(userResults);
			
			System.out.println("Votre proposition : "+userProposal+" -> R�ponse : "+out);
			logModes.trace("Combinaison ordinateur -> "+computerCombination+" / Proposition utilisateur -> "+userProposal+" / R�ponse -> "+out+".");
		
			
			computerProposal = game.proposalsGenerator(computerResults);
			computerResults = game.comparison(userCombination, computerProposal);
			out = game.formattingTheResults(computerResults);
			
			System.out.println("Proposition de l'ordinateur : "+computerProposal+" -> R�ponse : "+out);
			logModes.trace("Combinaison utilisateur -> "+userCombination+" / Proposition ordinateur -> "+computerProposal+" / R�ponse -> "+out+".");
			
			if(endGameConditions(userProposal, i, 0, computerProposal)) {
				break;
			}
			i++;
		}
		
		logModes.debug("Fin d'engine()");
	}
	
	/**
	 * <p>La m�thode v�rifiant les conditions de fin de partie pour le mode de jeu "Duel".</p>
	 * <p>A chaque tour une des quatre conditions est valid� et le r�sultat est retourn� sous la forme d'un boolean :
	 * <ul>
	 * 	<li>Si la proposition de l'ordinateur est �gale � la combinaison de l'utilisateur et que la proposition de l'utilisateur est �gale � la combinaison de l'ordinateur 
	 * 		=> Condition de fin de partie valid�, retourne vrai (true)  => Egalit� entre les deux joueurs </li>
	 * 	<li>Sinon si la proposition de l'ordinateur �gale la combinaison de l'utilisateur => Condition de fin de partie valid�, retourne vrai (true) => l'utilisateur perd / l'ordinateur gagne</li>
	 * 	<li>Sinon si la proposition de l'utilisateur �gale la combinaison de l'ordinateur => Condition de fin de partie valid�, retourne vrai (true) => l'utilisateur gagne / l'ordinateur perd</li>
	 * 	<li>Sinon => Condition de fin de partie non valid�, retourne faux (false) => Passage au tour suivant</li>
	 * </ul>
	 * 
	 * @see Modes#computerCombination
	 * @see Modes#userCombination
	 * @see Modes#endGameConditions(String, int, int, String)
	 * @see Duel#engine(Games)
	 */
	public boolean endGameConditions(String userProposal, int count, int nbrMaxTry, String computerProposal) {
		
		logModes.debug("D�but d'endGameConditions()");
		
		String logInformation;
		String information;
		boolean out;
		
		if(userCombination.equals(computerProposal) && computerCombination.equals(userProposal)) {
			logInformation = "Condition de fin de partie valid� -> Egalit� entre les deux joueurs au bout de "+(count+1)+" coups.";
			information = "\n�galit� ! L'ordinateur et vous avez trouv� la combinaison de l'autre au m�me tour, en "+(count+1)+" essais.";
			System.out.println(information);
			out = true;
		}
		else if(userCombination.equals(computerProposal)) {
			logInformation = "Condition de fin de partie valid� -> L'utilisateur a perdu au bout de "+(count+1)+" coups.";
			information = "\nVous avez perdu ! L'ordinateur a trouv� votre combinaison le premier en "+(count+1)+" essais.\nLa combinaison de l'ordinateur �tait : " + computerCombination;
			System.out.println(information);
			out =  true;
		}
		else if(computerCombination.equals(userProposal)) {
			logInformation = "Condition de fin de partie valid� -> L'utilisateur a gagn� au bout de "+(count+1)+" coups.";
			information = "\nBravo ! Vous avez gagn� ! Vous avez r�ussi � trouver la combinaison secr�te de l'ordinateur en "+(count+1)+" essais.";
			System.out.println(information);
			out = true;
		}
		else {
			logInformation = "Condition de fin de partie non valid�";
			out = false;
		}
		
		logModes.debug(logInformation);
		logModes.debug("Fin d'endGameConditions()");

		return out;
	}

}
