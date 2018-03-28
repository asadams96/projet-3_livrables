package com.main.java.configurations;

import com.main.java.games.Games;

/**
 * <p>Cette �num�ration enregistre les param�tres de jeu que la classe "ReadProperties.java" lui attribut.</p>
 * 
 * <p>Elle est consitu� :
 * <ul>
 * 	<li>De quatre param�tres de construction :
 * 		<ul>
 * 			<li>Un entier : "nbrCombi"</li>
 * 			<li>Un entier : "nbrMaxtry"</li>
 * 			<li>Un entier : "nbrUsableFigures"</li>
 * 			<li>Un boolean : "devMode"</li>
 * 		</ul>
 * </li>
 * 	<li>Des getters/setters de ses param�tres de construction</li>
 * 	<li>D'un objet "GameParameters"</li>
 * </ul>
 * </p>
 * 
 * <p>De plus, les param�tres de jeu ont d�ja une valeur pas d�faut, au cas o� la classe "ReadProperties" n'est pas en mesure de lui fournir
 *  les param�tres attendus, pour quelque raison que ce soit, afin de tout de m�me permettre � l'utilisateur de jouer.</p>
 *  
 *  @see Config#nbrCombi
 *  @see Config#nbrMaxTry
 *  @see Config#nbrUsableFigures
 *  @see Config#devMode
 *  @see Config#getNbrCombi()
 *  @see Config#setNbrCombi(int)
 *  @see Config#getNbrMaxTry()
 *  @see Config#setNbrMaxTry(int)
 *  @see Config#getNbrUsableFigures()
 *  @see Config#setNbrUsableFigures(int)
 *  @see Config#isDevMode()
 *  @see Config#setDevMode(boolean)
 *  @see Config#GameParameters
 *  @see ReadProperties#ReadProperties()
 *  @see ReadProperties
 * 
 * @author Ayrton
 * @version 1.0
 *
 */
public enum Config {
	
	/**
	 * <p>L'objet permettant de stocker les param�tres de jeu d�sir�</p> 
	 * 
	 * @see Config#nbrCombi
	 * @see Config#nbrMaxTry
	 * @see Config#nbrUsableFigures
	 * @see Config#devMode
	 * @see ReadProperties#ReadProperties()
	 * @see ReadProperties
	 */
	GameParameters(4,15,10,false);
	
	/**
	 * <p>L'attribut repr�sentant le format de la combinaison � x chiffres. L'interval de valeur est de [1;10].</p>
	 * 
	 * @see Config#getNbrCombi()
	 * @see Config#setNbrCombi(int)
	 * @see Config#GameParameters
	 * @see Games
	 */
	private int nbrCombi;
	
	/**
	 * <p>L'attribut repr�sentant le nombre maximal d'essai autoris� avant la fin d'une phase de jeu.</p>
	 * 
	 * @see Config#getNbrMaxTry()
	 * @see Config#setNbrMaxTry(int)
	 * @see Config#GameParameters
	 * @see Games
	 */
	private int nbrMaxTry;
	
	/**
	 * <p>L'attribut repr�sentant le nombre de couleurs/chiffres utilisables dans une combinaison. L'interval de valeurs est : [4;10].</p>
	 * 
	 * @see Config#getNbrUsableFigures()
	 * @see Config#setNbrUsableFigures(int)
	 * @see Config#GameParameters
	 * @see Games
	 */
	private int nbrUsableFigures;
	
	/**
	 * <p>L'attribut permettant d'activ� le mode d�veloppeur (vrai => true), ou non (faux => false).</p>
	 * 
	 * @see Config#isDevMode()
	 * @see Config#setDevMode(boolean)
	 * @see Config#GameParameters
	 * @see Games
	 */
	private boolean devMode;
	
	/**
	 * <p>Le constructeur de l'�num�ration.</p>
	 * 
	 * @param nbrCombi
	 * 					Le format de la combinaison
	 * 
	 * @param nbrMaxTry
	 * 					Le nombre d'essai maximal autoris�
	 * 
	 * @param nbrUsableFigures
	 * 							Le nombre de couleurs/figures utilisables dans la combinaison
	 * 
	 * @param devMode
	 * 					Le boolean permettant l'activation (ou non) du mode d�veloppeur
	 * 
	 * @see Config#nbrCombi
	 * @see Config#nbrMaxTry
	 * @see Config#nbrUsableFigures
	 * @see Config#devMode
	 * @see Config#GameParameters
	 */
	Config(int nbrCombi, int nbrMaxTry, int nbrUsableFigures, boolean devMode){
		this.nbrCombi = nbrCombi;
		this.nbrMaxTry = nbrMaxTry;
		this.nbrUsableFigures = nbrUsableFigures;
		this.devMode = devMode;
	}
	
	/**
	 * <p>Retourn le format de la combinaison.</p>
	 * 
	 * @return Une entier correspond au format de la combinaison
	 * 
	 * @see Config#nbrCombi
	 * @see Games
	 */
	public int getNbrCombi() {
		return nbrCombi;
	}

	/**
	 * <p>Modifie le format de la combinaison.</p>
	 * 
	 * @param nbrCombi
	 * 					Un entier correspondant au nouveau format de la combinaison
	 * 
	 * @see Config#nbrCombi
	 * @see ReadProperties#ReadProperties()
	 * @see ReadProperties
	 */
	protected void setNbrCombi(int nbrCombi) {
		this.nbrCombi = nbrCombi;
	}

	/**
	 * <p>Retourne le nombre maximal d'essai autoris�.</p>
	 * 
	 * @return Un entier correspond au nombre d'essai autoris�
	 * 
	 * @see Config#nbrMaxTry
	 * @see Games
	 */
	public int getNbrMaxTry() {
		return nbrMaxTry;
	}

	/**
	 * <p>Modifie le nombre maximal d'essai autoris�.</p>
	 * 
	 * @param nbrMaxTry
	 * 					Un entier repr�sentant le nouveau nombre maximal d'essai
	 * 
	 * @see Config#nbrMaxTry
	 * @see ReadProperties#ReadProperties()
	 * @see ReadProperties 
	 */
	protected void setNbrMaxTry(int nbrMaxTry) {
		this.nbrMaxTry = nbrMaxTry;
	}

	/**
	 * <p>Retourne le nombre de couleurs/chiffres utilisables dans la combinaison.</p>
	 * 
	 * @return Un entier correspondant au nombre de couleur/chiffres utilisables dans la combinaison
	 * 
	 * @see Config#nbrUsableFigures
	 * @see Games
	 */
	public int getNbrUsableFigures() {
		return nbrUsableFigures;
	}

	/**
	 * <p>Modifie le nombre de couleurs/chiffres utilisables dans la combinaison.</p>
	 * 
	 * @param nbrUsableFigures
	 * 							Un entier correspondant au nouveau nombre de couleurs/chiffres utilisables dans la combinaison
	 * 
	 * @see Config#nbrUsableFigures
	 * @see ReadProperties#ReadProperties()
	 * @see ReadProperties
	 */
	protected void setNbrUsableFigures(int nbrUsableFigures) {
		this.nbrUsableFigures = nbrUsableFigures;
	}
	
	/**
	 * <p>Retourne l'information concernant l'activation du mode d�veloppeur, ou non.</p>
	 * 
	 * @return un boolean informant de l'activation du mode d�veloppeur, ou non
	 * 
	 * @see Config#devMode
	 * @see Games
	 */
	public boolean isDevMode() {
		return devMode;
	}
	
	/**
	 * <p>Active ou d�sactive le mode d�veloppeur</p>
	 * 
	 * @param devMode
	 * 					Un boolean modifiant le statut du mode d�veloppeur
	 * 
	 * @see Config#devMode
	 * @see ReadProperties#ReadProperties()
	 * @see ReadProperties
	 */
	protected void setDevMode(boolean devMode) {
		this.devMode = devMode;
	}

}
