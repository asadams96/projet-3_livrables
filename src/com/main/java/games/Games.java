package com.main.java.games;

import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.main.java.StartUp;
import com.main.java.configurations.Config;
import com.main.java.modes.Defender;
import com.main.java.modes.Duel;
import com.main.java.modes.Modes;


/**
 *<p> Games est la classe abstraite parente de tous les jeux.</p>
 *<p> Un jeu est composé de plusieurs attributs et de méthodes qui sont généralement communs (mais pas toujours) :
 *<ul>
 *	<li>Trois entiers (avec leurs getters/setters) : 
 *		<ul>
 *			<li>nbrUsablesFigures</li>
 *			<li>nbrCombi</li>
 *			<li>nbrMaxTry</li>
 *		</ul>
 *	</li>
 *	<li>Deux booleans (avec leurs getters/setters) :
 *		<ul>
 *			<li>devMode</li>
 *			<li>severalTimesSameColor</li>
 *		</ul>
 *	</li>
 *	<li>Une méthode d'initialisation : initGame()</li>
 *	<li>Une méthode de comparaison entre la combinaison et la proposition : comparison()</li>
 *	<li>Une méthode de génération de proposition : proposalsGenerator()</li>
 *	<li>Une méthode de formatage des résultats de la comparaison : formattingTheResults()</li>
 * </ul>
 * </p>
 * 
 * <p>De plus la classe abstraite "Games" fonctionne en tandem avec la classe abstraite "Modes".</p>
 * 
 * @see Mastermind
 * @see SearchMoreLess
 * @see Games#nbrUsableFigures
 * @see Games#nbrCombi
 * @see Games#nbrMaxTry
 * @see Games#devMode
 * @see Games#severalTimesSameColor
 * @see Games#getNbrCombi()
 * @see Games#setNbrCombi(int)
 * @see Games#getNbrMaxTry()
 * @see Games#setNbrMaxTry(int)
 * @see Games#getNbrUsableFigures()
 * @see Games#setNbrUsableFigures(int)
 * @see Games#isDevMode()
 * @see Games#setDevMode(boolean)
 * @see Games#isSeveralTimesSameColor()
 * @see Games#setSeveralTimesSameColor(boolean)
 * @see Games#initGame()
 * @see Games#comparison(String, String)
 * @see Games#formattingTheResults(Hashtable)
 * @see Games#proposalsGenerator(Hashtable)
 * @see Modes
 * @see StartUp
 * @see Config
 * 
 * @author Ayrton
 * @version 1.0
 */
public abstract class Games {
	
	/**
	 * <p>Le log de la classe abstraite Games.java et de ses classes enfants</p>
	 */
	protected final Logger logGames = Logger.getLogger(this.getClass());
	
	/**
	 * <p>Le nombre de couleurs possible dans la combinaison, ce nombre de couleurs est représenté par des chiffres, de 4 à 10 couleurs.</p>
	 * <p>Ce qui correspond aux chiffres utilisables suivant => [0;3] à [0;9].</p>
	 * 
	 * @see Games#getNbrUsableFigures()
	 * @see Games#setNbrUsableFigures(int)
	 * @see Config#getNbrUsableFigures()
	 */
	private int nbrUsableFigures = Config.GameParameters.getNbrUsableFigures();
	
	/**
	 * <p>Le format de la combinaison à x chiffres, il faut que cette valeur soit au minimum à 1.</p>
	 * <p>Le maximum est indéfini et dépend des gouts de chacun.</p>
	 * 
	 * @see Games#getNbrCombi()
	 * @see Games#setNbrCombi(int)
	 * @see Config#getNbrCombi()
	 */
	private int nbrCombi = Config.GameParameters.getNbrCombi();
	
	/**
	 * <p>Le nombre d'essai possible autorisé pour trouver la combinaison secrète.</p>
	 * <p>Si l'utilisateur trouve la combinaison dans nombre d'essai imparti, il a gagné, sinon il a perdu.</p>
	 * 
	 * @see Games#getNbrMaxTry()
	 * @see Games#setNbrMaxTry(int)
	 * @see Config#getNbrMaxTry()
	 */
	private int nbrMaxTry = Config.GameParameters.getNbrMaxTry();
	
	/**
	 * <p>Le mode développeur est un mode, lorsqu'il est activé (valeur à vrai => true), permettant de connaitre la(es) combinaison(s) au début de la partie.</p>
	 * <p>Il permet notamment de vérifier que le programme fonctionne correctement et doit être désactivé pour une partie normal, sans tricherie (valeur à faux => false).</p>
	 *   
	 * @see Games#isDevMode()
	 * @see Games#setDevMode(boolean)
	 * @see Config#isDevMode()
	 */
	private boolean devMode = Config.GameParameters.isDevMode();
	
	/**
	 * <p>Litteralement "Plusieurs fois la même couleur" dans la combinaison.</p>
	 * <p>Sert à aider l'ordinateur à trouver la combinaison secrète en l'informant s'il y a plusieurs fois la présence d'une même couleur dans la combinaison secrète.</p>
	 * <p>Cette vérification est faites lorsque l'utilisateur doit rentrer une combinaison pour le mode "Defender" et "Dual",cependant cette vérification n'est prise en compte par la suite que pour le jeu MasterMind.</p>
	 * 
	 * @see Games#isSeveralTimesSameColor();
	 * @see Games#setSeveralTimesSameColor(boolean)
	 * @see Mastermind
	 * @see Defender#init(Games)
	 * @see Duel#init(Games)
	 */
	private boolean severalTimesSameColor = false;

	/**
	 * <p>Retourne le format de la combinaison à x chiffres.</p>
	 * 
	 * @return Un entier correspondant au format de la combinaison 
	 * 
	 * @see Games#nbrCombi
	 */
	public int getNbrCombi() {
		return nbrCombi;
	}

	/**
	 * <p>Met à jour le format de la combinaison à x chiffres.</p>
	 * 
	 * @param nbrCombi
	 * 					Le nouveau format de la combinaison
	 * 
	 * @deprecated Depuis la mise en place d'un fichier de configuration où l'on doit mettre à jour les paramètres
	 * 
	 * @see Games#nbrCombi
	 */
	protected void setNbrCombi(int nbrCombi) {
		logGames.warn("Utilisation de setNbrCombi() -> Deprecated");
		this.nbrCombi = nbrCombi;
	}
	
	/**
	 * <p>Retourne le nombre d'essais autorisé.</p>
	 * 
	 * @return Une entier correspond au nombre d'essai autorisé
	 * 
	 * @see Games#nbrMaxTry
	 */
	public int getNbrMaxTry() {
		return nbrMaxTry;
	}
	
	/**
	 * <p>Met à jour le nombre d'essais autorisé.</p>
	 * 
	 * @param nbrMaxTry
	 * 				Le nouveau nombre d'essai autorisé
	 * 
	 * @deprecated Depuis la mise en place d'un fichier de configuration où l'on doit mettre à jour les paramètres
	 * 
	 * @see Games#nbrMaxTry
	 */
	protected void setNbrMaxTry(int nbrMaxTry) {
		logGames.warn("Utilisation de setNbrMaxTry() -> Deprecated");
		this.nbrMaxTry = nbrMaxTry;
	}

	/**
	 * <p>Retourne le nombre de couleurs différentes autorisé dans la combinaison.</p>
	 * 
	 * @return Un entier correspond au nombre de couleurs différentes autorisé dans la combinaison
	 * 
	 * @see Games#nbrUsableFigures
	 */
	public int getNbrUsableFigures() {
		return nbrUsableFigures;
	}

	/**
	 * <p>Met à jour le nombre de couleurs différentes autorisé dans la combinaison.</p>
	 * 
	 * @param nbrUsableFigures
	 * 							Un entier correspondant au nouveau nombre de couleurs différentes autorisé dans la combinaison
	 * 
	 * @see Games#nbrUsableFigures
	 * 
	 */
	protected void setNbrUsableFigures(int nbrUsableFigures) {
		this.nbrUsableFigures = nbrUsableFigures;
	}

	/**
	 * <p>Retourne une information concernant l'activation du mode développeur ou non.</p>
	 * 
	 * @return Un boolean qui sera à "faux" (false) si le mode développeur est désactivé, ou à "vrai" (true) si au contraire il est activé
	 * 
	 * @see Games#devMode
	 */
	public boolean isDevMode() {
		return devMode;
	}

	/**
	 * <p>Met à jour le statut du mode développeur.</p>
	 * 
	 * @param devMode
	 * 				Un boolean correspond au nouveau statut du mode développeur
	 * 
	 * @deprecated Depuis la mise en place d'un fichier de configuration où l'on doit mettre à jour les paramètres
	 * 
	 * @see Games#devMode
	 * 				
	 */
	protected void setDevMode(boolean devMode) {
		this.devMode = devMode;
	}
	
	/**
	 * <p>Retourne une information de type vrai/faux correspondant au fait qu'il y a plusieurs fois la même couleur dans la combinaison.</p>
	 * 
	 * @return Un boolean informant qu'il y a plusieurs fois la même couleur dans la combinaison (vrai == true), ou non (faux == false)
	 * 
	 * @see Games#severalTimesSameColor
	 */
	public boolean isSeveralTimesSameColor() {
		return severalTimesSameColor;
	}
	
	/**
	 * <p>Met à jour l'information de type vrai/faux correspondant au fait qu'il y a plusieurs fois la même couleur dans la combinaison.</p>
	 * 
	 * @param severalTimesSameColor
	 * 								Un boolean mettant à jour le fait qu'il y a plusieurs fois la même couleur dans la combinaison, ou non
	 * 
	 * @see Games#severalTimesSameColor
	 */
	public void setSeveralTimesSameColor(boolean severalTimesSameColor) {
		logGames.trace("Utilisation de setSeveralTimesSameColor() -> ["+severalTimesSameColor+"].");
		this.severalTimesSameColor = severalTimesSameColor;
	}

	/**
	 * <p>Initialise le jeu qui a appelé cette méthode en instanciant tous ses paramètres à des valeurs par défaut (ou en les ré-instanciant dans le cadre d'une nouvelle partie).</p>
	 * 
	 * @see Mastermind#initGame() 
	 * @see SearchMoreLess#initGame()
	 */
	public void initGame() {}
	
	/**
	 * <p>Cette méthode compare deux chaines de caractères : la combinaison et la proposition, puis renvoie le résultat de cette comparaison sous forme d'une table de hachage.</p>
	 * 
	 * @param combination
	 * 					  Chaine de caractères (String) représentant la combinaison
	 * 
	 * @param proposal
	 * 					Chaine de caractères (String) représentant la proposition
	 * 
	 * @return Une table de hachage contenant les résultats de la comparaison entre la combinaison et la proposition
	 * 
	 * 
	 *  <p>Les résultats dépendent du jeu qui a appelé cette méthode.</p>
	 * 
	 * @see Mastermind#comparison(String, String)
	 * @see SearchMoreLess#comparison(String, String)
	 */
	public Hashtable<String,Integer> comparison(String combination, String proposal) {
		return null;
	}
	
	/**
	 * <p>Cette méthode sert à formater dans une chaine de caractères (String) les résultats de la comparaison entre la combinaison et la proposition de façon à ce qu'ils soient bien lisible et bien présenté dans la console</p>
	 * 
	 * @param table
	 * 				Table de hachage représentant les résultats de la comparaison entre la combinaison et la proposition
	 * 
	 * @return Une chaine de caractères (String) contenant les resultats de la comparaison formatés
	 * 
	 * 
	 * <p>Le formatage dépend du jeu qui a appelé cette méthode.</p>
	 * 
	 * @see Mastermind#formattingTheResults(Hashtable)
	 * @see SearchMoreLess#formattingTheResults(Hashtable)
	 * @see Games#comparison(String, String)
	 */
	public String formattingTheResults(Hashtable<String, Integer> table) {
		return null;
	}
	
	/**
	 * <p>Cette méthode fait générer une proposition à l'ordinateur en fonction des résultats de la comparaison entre la combinaison et la proposition précédente(s'il y en a une) lorsqu'un jeu à été lancé en mode "Defender" ou "Duel".</p>
	 * 
	 * @param table
	 * 				Table de hachage représentant les résultats de la comparaison entre la combinaison et la proposition	
	 * 
	 * @return Une chaine de caractères (String) représentant la proposition de l'ordinateur
	 * 
	 * 
	 * <p>La proposition dépend du jeu qui a appelé cette méthode.</p>
	 * 
	 * @see Mastermind#proposalsGenerator(Hashtable)
	 * @see SearchMoreLess#proposalsGenerator(Hashtable)
	 * @see Games#comparison(String, String)
	 */
	public String proposalsGenerator(Hashtable<String,Integer> table) {
		return null;
	}
}
