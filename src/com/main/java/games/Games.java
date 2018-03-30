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
 *<p> Un jeu est compos� de plusieurs attributs et de m�thodes qui sont g�n�ralement communs (mais pas toujours) :
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
 *	<li>Une m�thode d'initialisation : initGame()</li>
 *	<li>Une m�thode de comparaison entre la combinaison et la proposition : comparison()</li>
 *	<li>Une m�thode de g�n�ration de proposition : proposalsGenerator()</li>
 *	<li>Une m�thode de formatage des r�sultats de la comparaison : formattingTheResults()</li>
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
	 * <p>Le nombre de couleurs possible dans la combinaison, ce nombre de couleurs est repr�sent� par des chiffres, de 4 � 10 couleurs.</p>
	 * <p>Ce qui correspond aux chiffres utilisables suivant => [0;3] � [0;9].</p>
	 * 
	 * @see Games#getNbrUsableFigures()
	 * @see Games#setNbrUsableFigures(int)
	 * @see Config#getNbrUsableFigures()
	 */
	private int nbrUsableFigures = Config.GameParameters.getNbrUsableFigures();
	
	/**
	 * <p>Le format de la combinaison � x chiffres, il faut que cette valeur soit au minimum � 1.</p>
	 * <p>Le maximum est ind�fini et d�pend des gouts de chacun.</p>
	 * 
	 * @see Games#getNbrCombi()
	 * @see Games#setNbrCombi(int)
	 * @see Config#getNbrCombi()
	 */
	private int nbrCombi = Config.GameParameters.getNbrCombi();
	
	/**
	 * <p>Le nombre d'essai possible autoris� pour trouver la combinaison secr�te.</p>
	 * <p>Si l'utilisateur trouve la combinaison dans nombre d'essai imparti, il a gagn�, sinon il a perdu.</p>
	 * 
	 * @see Games#getNbrMaxTry()
	 * @see Games#setNbrMaxTry(int)
	 * @see Config#getNbrMaxTry()
	 */
	private int nbrMaxTry = Config.GameParameters.getNbrMaxTry();
	
	/**
	 * <p>Le mode d�veloppeur est un mode, lorsqu'il est activ� (valeur � vrai => true), permettant de connaitre la(es) combinaison(s) au d�but de la partie.</p>
	 * <p>Il permet notamment de v�rifier que le programme fonctionne correctement et doit �tre d�sactiv� pour une partie normal, sans tricherie (valeur � faux => false).</p>
	 *   
	 * @see Games#isDevMode()
	 * @see Games#setDevMode(boolean)
	 * @see Config#isDevMode()
	 */
	private boolean devMode = Config.GameParameters.isDevMode();
	
	/**
	 * <p>Litteralement "Plusieurs fois la m�me couleur" dans la combinaison.</p>
	 * <p>Sert � aider l'ordinateur � trouver la combinaison secr�te en l'informant s'il y a plusieurs fois la pr�sence d'une m�me couleur dans la combinaison secr�te.</p>
	 * <p>Cette v�rification est faites lorsque l'utilisateur doit rentrer une combinaison pour le mode "Defender" et "Dual",cependant cette v�rification n'est prise en compte par la suite que pour le jeu MasterMind.</p>
	 * 
	 * @see Games#isSeveralTimesSameColor();
	 * @see Games#setSeveralTimesSameColor(boolean)
	 * @see Mastermind
	 * @see Defender#init(Games)
	 * @see Duel#init(Games)
	 */
	private boolean severalTimesSameColor = false;

	/**
	 * <p>Retourne le format de la combinaison � x chiffres.</p>
	 * 
	 * @return Un entier correspondant au format de la combinaison 
	 * 
	 * @see Games#nbrCombi
	 */
	public int getNbrCombi() {
		return nbrCombi;
	}

	/**
	 * <p>Met � jour le format de la combinaison � x chiffres.</p>
	 * 
	 * @param nbrCombi
	 * 					Le nouveau format de la combinaison
	 * 
	 * @deprecated Depuis la mise en place d'un fichier de configuration o� l'on doit mettre � jour les param�tres
	 * 
	 * @see Games#nbrCombi
	 */
	protected void setNbrCombi(int nbrCombi) {
		logGames.warn("Utilisation de setNbrCombi() -> Deprecated");
		this.nbrCombi = nbrCombi;
	}
	
	/**
	 * <p>Retourne le nombre d'essais autoris�.</p>
	 * 
	 * @return Une entier correspond au nombre d'essai autoris�
	 * 
	 * @see Games#nbrMaxTry
	 */
	public int getNbrMaxTry() {
		return nbrMaxTry;
	}
	
	/**
	 * <p>Met � jour le nombre d'essais autoris�.</p>
	 * 
	 * @param nbrMaxTry
	 * 				Le nouveau nombre d'essai autoris�
	 * 
	 * @deprecated Depuis la mise en place d'un fichier de configuration o� l'on doit mettre � jour les param�tres
	 * 
	 * @see Games#nbrMaxTry
	 */
	protected void setNbrMaxTry(int nbrMaxTry) {
		logGames.warn("Utilisation de setNbrMaxTry() -> Deprecated");
		this.nbrMaxTry = nbrMaxTry;
	}

	/**
	 * <p>Retourne le nombre de couleurs diff�rentes autoris� dans la combinaison.</p>
	 * 
	 * @return Un entier correspond au nombre de couleurs diff�rentes autoris� dans la combinaison
	 * 
	 * @see Games#nbrUsableFigures
	 */
	public int getNbrUsableFigures() {
		return nbrUsableFigures;
	}

	/**
	 * <p>Met � jour le nombre de couleurs diff�rentes autoris� dans la combinaison.</p>
	 * 
	 * @param nbrUsableFigures
	 * 							Un entier correspondant au nouveau nombre de couleurs diff�rentes autoris� dans la combinaison
	 * 
	 * @see Games#nbrUsableFigures
	 * 
	 */
	protected void setNbrUsableFigures(int nbrUsableFigures) {
		this.nbrUsableFigures = nbrUsableFigures;
	}

	/**
	 * <p>Retourne une information concernant l'activation du mode d�veloppeur ou non.</p>
	 * 
	 * @return Un boolean qui sera � "faux" (false) si le mode d�veloppeur est d�sactiv�, ou � "vrai" (true) si au contraire il est activ�
	 * 
	 * @see Games#devMode
	 */
	public boolean isDevMode() {
		return devMode;
	}

	/**
	 * <p>Met � jour le statut du mode d�veloppeur.</p>
	 * 
	 * @param devMode
	 * 				Un boolean correspond au nouveau statut du mode d�veloppeur
	 * 
	 * @deprecated Depuis la mise en place d'un fichier de configuration o� l'on doit mettre � jour les param�tres
	 * 
	 * @see Games#devMode
	 * 				
	 */
	protected void setDevMode(boolean devMode) {
		this.devMode = devMode;
	}
	
	/**
	 * <p>Retourne une information de type vrai/faux correspondant au fait qu'il y a plusieurs fois la m�me couleur dans la combinaison.</p>
	 * 
	 * @return Un boolean informant qu'il y a plusieurs fois la m�me couleur dans la combinaison (vrai == true), ou non (faux == false)
	 * 
	 * @see Games#severalTimesSameColor
	 */
	public boolean isSeveralTimesSameColor() {
		return severalTimesSameColor;
	}
	
	/**
	 * <p>Met � jour l'information de type vrai/faux correspondant au fait qu'il y a plusieurs fois la m�me couleur dans la combinaison.</p>
	 * 
	 * @param severalTimesSameColor
	 * 								Un boolean mettant � jour le fait qu'il y a plusieurs fois la m�me couleur dans la combinaison, ou non
	 * 
	 * @see Games#severalTimesSameColor
	 */
	public void setSeveralTimesSameColor(boolean severalTimesSameColor) {
		logGames.trace("Utilisation de setSeveralTimesSameColor() -> ["+severalTimesSameColor+"].");
		this.severalTimesSameColor = severalTimesSameColor;
	}

	/**
	 * <p>Initialise le jeu qui a appel� cette m�thode en instanciant tous ses param�tres � des valeurs par d�faut (ou en les r�-instanciant dans le cadre d'une nouvelle partie).</p>
	 * 
	 * @see Mastermind#initGame() 
	 * @see SearchMoreLess#initGame()
	 */
	public void initGame() {}
	
	/**
	 * <p>Cette m�thode compare deux chaines de caract�res : la combinaison et la proposition, puis renvoie le r�sultat de cette comparaison sous forme d'une table de hachage.</p>
	 * 
	 * @param combination
	 * 					  Chaine de caract�res (String) repr�sentant la combinaison
	 * 
	 * @param proposal
	 * 					Chaine de caract�res (String) repr�sentant la proposition
	 * 
	 * @return Une table de hachage contenant les r�sultats de la comparaison entre la combinaison et la proposition
	 * 
	 * 
	 *  <p>Les r�sultats d�pendent du jeu qui a appel� cette m�thode.</p>
	 * 
	 * @see Mastermind#comparison(String, String)
	 * @see SearchMoreLess#comparison(String, String)
	 */
	public Hashtable<String,Integer> comparison(String combination, String proposal) {
		return null;
	}
	
	/**
	 * <p>Cette m�thode sert � formater dans une chaine de caract�res (String) les r�sultats de la comparaison entre la combinaison et la proposition de fa�on � ce qu'ils soient bien lisible et bien pr�sent� dans la console</p>
	 * 
	 * @param table
	 * 				Table de hachage repr�sentant les r�sultats de la comparaison entre la combinaison et la proposition
	 * 
	 * @return Une chaine de caract�res (String) contenant les resultats de la comparaison format�s
	 * 
	 * 
	 * <p>Le formatage d�pend du jeu qui a appel� cette m�thode.</p>
	 * 
	 * @see Mastermind#formattingTheResults(Hashtable)
	 * @see SearchMoreLess#formattingTheResults(Hashtable)
	 * @see Games#comparison(String, String)
	 */
	public String formattingTheResults(Hashtable<String, Integer> table) {
		return null;
	}
	
	/**
	 * <p>Cette m�thode fait g�n�rer une proposition � l'ordinateur en fonction des r�sultats de la comparaison entre la combinaison et la proposition pr�c�dente(s'il y en a une) lorsqu'un jeu � �t� lanc� en mode "Defender" ou "Duel".</p>
	 * 
	 * @param table
	 * 				Table de hachage repr�sentant les r�sultats de la comparaison entre la combinaison et la proposition	
	 * 
	 * @return Une chaine de caract�res (String) repr�sentant la proposition de l'ordinateur
	 * 
	 * 
	 * <p>La proposition d�pend du jeu qui a appel� cette m�thode.</p>
	 * 
	 * @see Mastermind#proposalsGenerator(Hashtable)
	 * @see SearchMoreLess#proposalsGenerator(Hashtable)
	 * @see Games#comparison(String, String)
	 */
	public String proposalsGenerator(Hashtable<String,Integer> table) {
		return null;
	}
}
