package com.main.java.games;

import java.util.Hashtable;

import com.main.java.StartUp;
import com.main.java.configurations.Config;
import com.main.java.modes.Defender;
import com.main.java.modes.Duel;
import com.main.java.modes.Modes;


/**
 *<p> Games est la classe abstraite parente de tous les jeux.</p>
 *<p> Un jeu est compos� de plusieurs attributs et de m�thodes qui sont g�n�ralement communs (mais pas toujours) :
 *<ul>
 *	<li>Un nombre de couleurs possible dans la combinaison secr�te</li>
 *	<li>Un format de x chiffres pour la combinaison</li>
 *	<li>Un nombre d'essais maximal autoris�</li>
 *	<li>Un mode d�veloppeur</li>
 *	<li>Une information sur les couleurs de la combinaison (pour le mastermind)</li>
 *	<li>Une m�thode d'initialisation</li>
 *	<li>Une m�thode de comparaison entre la combinaison et la proposition</li>
 *	<li>Une m�thode de g�n�ration de proposition</li>
 *	<li>Une m�thode de formatage des r�sultats de la comparaison</li>
 * </ul>
 * </p>
 * 
 * <p>De plus la classe abstraite "Games" fonctionne en tandem avec la classe abstraite "Modes".</p>
 * 
 * @see Mastermind
 * @see SearchMoreLess
 * @see Games#nbrUsableFigures
 * @see Games#nbrCombi
 * @see Games#nbrTry
 * @see Games#devMode
 * @see Games#severalTimesSameColor
 * @see Games#getNbrCombi()
 * @see Games#setNbrCombi(int)
 * @see Games#getNbrTry()
 * @see Games#setNbrTry(int)
 * @see Games#getNbrUsableFigures()
 * @see Games#setNbrUsableFigures(int)
 * @see Games#isDevMode()
 * @see Games#setDevMode(boolean)
 * @see Games#isSeveralTimesSameColor()
 * @see Games#setSeveralTimesSameColor(boolean)
 * @see Games#initGame()
 * @see Games#verifCombi(String, String)
 * @see Games#displayResultsCombi(Hashtable)
 * @see Games#defenderCombi(Hashtable)
 * @see Modes
 * @see StartUp
 * 
 * @author Ayrton
 * @version 1.0
 */
public abstract class Games {
	
	/**
	 * <p>Le nombre de couleurs possible dans la combinaison, ce nombre de couleurs est repr�sent� par des chiffres, de 4 � 10 couleurs.</p>
	 * <p>Ce qui correspond aux chiffres utilisables suivant => [0;3] � [0;9].</p>
	 * 
	 * @see Games#getNbrUsableFigures()
	 * @see Games#setNbrUsableFigures(int)
	 */
	private int nbrUsableFigures;
	
	/**
	 * <p>Le format de la combinaison � x chiffres, il faut que cette valeur soit au minimum � 1.</p>
	 * <p>Le maximum est ind�fini et d�pend des gouts de chacun.</p>
	 * 
	 * @see Games#getNbrCombi()
	 * @see Games#setNbrCombi(int)
	 */
	private int nbrCombi = Config.GameParameters.getNbrCombi();
	
	/**
	 * <p>Le nombre d'essai possible autoris� pour trouver la combinaison secr�te.</p>
	 * <p>Si l'utilisateur trouve la combinaison dans nombre d'essai imparti, il a gagn�, sinon il a perdu.</p>
	 * 
	 * @see Games#getNbrTry()
	 * @see Games#setNbrTry(int)
	 */
	private int nbrTry = Config.GameParameters.getNbrTry();
	
	/**
	 * <p>Le mode d�veloppeur est un mode, lorsqu'il est activ� (valeur � vrai => true), permettant de connaitre la(es) combinaison(s) au d�but de la partie.</p>
	 * <p>Il permet notamment de v�rifier que le programme fonctionne correctement et doit �tre d�sactiv� pour une partie normal, sans tricherie (valeur � faux => false).</p>
	 *   
	 * @see Games#isDevMode()
	 * @see Games#setDevMode(boolean)
	 */
	private boolean devMode = Config.GameParameters.isDevMode();
	
	/**
	 * <p>Litteralement "Plusieurs fois la m�me couleur" dans la combinaison.</p>
	 * <p>Sert � aider l'ordinateur � trouver la combinaison secr�te en l'informant s'il y a plusieurs fois la pr�sence d'une m�me couleur dans la combinaison secr�te.</p>
	 * 
	 * @see Games#isSeveralTimesSameColor();
	 * @see Games#setSeveralTimesSameColor(boolean)
	 * 
	 * <p>Cette v�rification est faites lorsque l'utilisateur doit rentrer une combinaison pour le mode "Defender" et "Dual",cependant cette v�rification n'est prise en compte par la suite que pour le jeu MasterMind.</p>
	 * 
	 * @see Mastermind
	 * @see Defender#init(Games)
	 * @see Duel#init(Games)
	 */
	private boolean severalTimesSameColor = false;

	/**
	 * <p>Retourne le format de la combinaison � x chiffres.</p>
	 * 
	 * @return Un entier correspondant au format de la combinaison 
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
	 */
	public void setNbrCombi(int nbrCombi) {
		this.nbrCombi = nbrCombi;
	}
	
	/**
	 * <p>Retourne le nombre d'essais autoris�.</p>
	 * 
	 * @return Une entier correspond au nombre d'essai autoris�
	 */
	public int getNbrTry() {
		return nbrTry;
	}
	
	/**
	 * <p>Met � jour le nombre d'essais autoris�.</p>
	 * 
	 * @param nbrTry
	 * 				Le nouveau nombre d'essai autoris�
	 * 
	 * @deprecated Depuis la mise en place d'un fichier de configuration o� l'on doit mettre � jour les param�tres
	 */
	public void setNbrTry(int nbrTry) {
		this.nbrTry = nbrTry;
	}

	/**
	 * <p>Retourne le nombre de couleurs diff�rentes autoris� dans la combinaison.</p>
	 * 
	 * @return Un entier correspond au nombre de couleurs diff�rentes autoris� dans la combinaison
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
	 */
	public void setNbrUsableFigures(int nbrUsableFigures) {
		this.nbrUsableFigures = nbrUsableFigures;
	}

	/**
	 * <p>Retourne une information concernant l'activation du mode d�veloppeur ou non.</p>
	 * 
	 * @return Un boolean qui sera � "faux" (false) si le mode d�veloppeur est d�sactiv�, ou � "vrai" (true) si au contraire il est activ�
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
	 */
	public void setDevMode(boolean devMode) {
		this.devMode = devMode;
	}
	
	/**
	 * <p>Retourne une information de type vrai/faux correspondant au fait qu'il y a plusieurs fois la m�me couleur dans la combinaison.</p>
	 * 
	 * @return Un boolean informant qu'il y a plusieurs fois la m�me couleur dans la combinaison (vrai == true), ou non (faux == false)
	 */
	public boolean isSeveralTimesSameColor() {
		return severalTimesSameColor;
	}
	
	/**
	 * <p>Met � jour l'information de type vrai/faux correspondant au fait qu'il y a plusieurs fois la m�me couleur dans la combinaison.</p>
	 * 
	 * @param severalTimesSameColor
	 * 								Un boolean mettant � jour le fait qu'il y a plusieurs fois la m�me couleur dans la combinaison, ou non
	 */
	public void setSeveralTimesSameColor(boolean severalTimesSameColor) {
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
	 * @see Mastermind#verifCombi(String, String)
	 * @see SearchMoreLess#verifCombi(String, String)
	 */
	public Hashtable<String,Integer> verifCombi(String combination, String proposal) {
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
	 * @see Mastermind#displayResultsCombi(Hashtable)
	 * @see SearchMoreLess#displayResultsCombi(Hashtable)
	 * @see Games#verifCombi(String, String)
	 */
	public String displayResultsCombi(Hashtable<String, Integer> table) {
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
	 * @see Mastermind#defenderCombi(Hashtable)
	 * @see SearchMoreLess#defenderCombi(Hashtable)
	 * @see Games#verifCombi(String, String)
	 */
	public String defenderCombi(Hashtable<String,Integer> table) {
		return null;
	}
}
