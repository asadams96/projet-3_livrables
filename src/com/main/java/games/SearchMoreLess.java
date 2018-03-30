package com.main.java.games;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

import com.main.java.modes.Modes;
	
/**
 * <p>Cette classe, SearchMoreLess, est une classe enfant de "Games", en plus des attributs/méthodes que contient "Games", elle possède :
 * <ul>
 * 	<li>Une table de hachage de chaines de caractères : interval</li>
 * 	<li>Une liste de chaines de caractères : tryList</li>
 * 	<li>Une méthode : intervalAdjustment</li>
 * </ul>
 * </p>
 * 
 * <p>Le but de ce jeu est de trouver une combinaison à x chiffres à l'aide d'indication : "+" / "-" / "=" pour chacun des chiffres de la combinaison.</p>
 * <p>Celui qui doit trouver la combinaison secrète ou effectuer la saisie dépend du mode de jeu choisi.</p> 
 * 
 * @see Games
 * @see SearchMoreLess#interval
 * @see SearchMoreLess#tryList
 * @see SearchMoreLess#SearchMoreLess()
 * @see SearchMoreLess#initGame()
 * @see SearchMoreLess#comparison(String, String)
 * @see SearchMoreLess#formattingTheResults(Hashtable)
 * @see SearchMoreLess#proposalsGenerator(Hashtable)
 * @see SearchMoreLess#intervalAdjustment(Hashtable, String)
 * @see Modes
 * 
 * @author Ayrton
 * @version 1.0
 *
 */
public class SearchMoreLess extends Games{
	
	/**
	 * <p>L'interval des valeurs possible de la combinaison pour générer la proposition où la clé représente l'emplacement de chaque chiffre dans la combinaison et la valeur représente l'interval des valeurs possible qui s'ajuste au fur et à mesure des propositions</p>
	 * 
	 * @see SearchMoreLess#intervalAdjustment(Hashtable, String)
	 */
	private Hashtable<String,String> interval;
	
	/**
	 * <p>La liste des propositions effectuées de façon à pouvoir les récupérer</p>
	 */
	private ArrayList<String> tryList;
	
	/**
	 * <p>Le contructeur du jeu SearchMoreLess, où le nombre de "couleurs" (<= pour ce jeu ce sont des chiffres) utilisable est définis avec une valeur qui restera inchangé ([0;9]), quelques soit la configuration mise en place.</p>
	 */
	public SearchMoreLess() {
		
		this.setNbrUsableFigures(10);
		
	}
	
	/**
	 * <p>La méthode initialise le jeu SearchMoreLess en instanciant ses attributs avec une valeur par défaut.</p>
	 * 
	 * @see SearchMoreLess#interval
	 * @see SearchMoreLess#tryList
	 * @see Games#initGame()
	 */
	public void initGame() {
		
		logGames.debug("Début d'initGame()");
		
		interval = new Hashtable<String,String>();
		tryList = new ArrayList<String>();
		
		logGames.debug("Fin d'initGame()");
	}

	/**
	 * <p>La méthode compare la proposition et la combinaison pour le jeu SearchMoreLess</p>
	 * 
	 * <p>Le fonctionnement de la méthode est assez rudimentaire :
	 * <ul>
	 * <li>Chaque chiffre de la proposition est comparé avec le chiffre de la combinaison ayant le même emplacement.</li>
	 * <li>Chaque resultat de la comparaison est stocker dans une table de hachage avec l'emplacement en clé et le resultat en valeur</li>
	 * <li>Il y a trois valeurs possible de resultat :
	 * <ul>
	 * 	<li>le résultat = "1" ==> correspond au "+"</li>
	 * 	<li>le resultat = "-1" ==> correspond au "-"</li>
	 * 	<li>le resulat = "0" ==> correspond au "="</li>
	 * </ul>
	 * </li>
	 * <li>Retourne la table de hachage contenant la liste des clés et leurs résultats associés</li>
	 * </ul>
	 * </p>
	 * 
	 * @see Games#comparison(String, String)
	 */
	public Hashtable<String, Integer> comparison(String combination, String proposal) {
		
		logGames.debug("Début de comparison()");
		
		Hashtable<String,Integer> out= new Hashtable<String,Integer>();
		String key;
		
		for(int i = 0; i < this.getNbrCombi(); i++) {
			key = ""+i;
			
			if(Integer.valueOf(combination.substring(i, i+1)) > Integer.valueOf(proposal.substring(i, i+1))) {
				//key.
				out.put(key, 1);
			}
			
			else if(Integer.valueOf(combination.substring(i, i+1)) < Integer.valueOf(proposal.substring(i, i+1))) {
				out.put(key, -1);
			}
			
			else {
				out.put(key, 0);
			}
		}
		
		logGames.debug("Fin de comparison()");
		
		return out;
	}
	
	/**
	 * <p>Formatage des résultats dans une chaine de caratères pour le jeu SearchMoreLess</p>
	 * <p>
	 * <ul>
	 * 	<li>le résultat = "1" ==> correspond au "+"</li>
	 * 	<li>le resultat = "-1" ==> correspond au "-"</li>
	 * 	<li>le resulat = "0" ==> correspond au "="</li>
	 * </ul>
	 * </p>
	 * 
	 * @see SearchMoreLess#comparison(String, String)
	 * @see Games#formattingTheResults(Hashtable)
	 */
	public String formattingTheResults(Hashtable<String, Integer> table) {
		
		logGames.debug("Début de formattingTheResults()");
		
		String out = "";
		
		for(int i = 0; i < this.getNbrCombi(); i++) {
			
			if(table.get(""+i).equals(1)) {
				out += "+";
			}
			else if(table.get(""+i).equals(-1)) {
				out += "-";
			}
			else if(table.get(""+i).equals(0)) {
				out += "=";
			}
		}
		
		logGames.debug("Fin de formattingTheResults()");
		
		return out;
	}
	
	/**
	 * <p>Cette méthode va générer une proposition à x chiffres en fonction d'un interval de valeur qui sera sans cesse ajuster à chaque nouvelle proposition. Cependant lors du premier tour, la premier proposition généré
	 * sera un nombre aléatoire, plus précisément ce sera x fois le même nombre et ce nombre sera donc aléatoire. </p> 
	 * 
	 * <p>Le nombre x correspond à la taille de la combinaison</p>
	 * 
	 * @see SearchMoreLess#comparison(String, String)
	 * @see SearchMoreLess#intervalAdjustment(Hashtable, String)
	 * @see SearchMoreLess#tryList
	 * @see SearchMoreLess#interval
	 * @see Games#getNbrCombi()
	 * @see Games#proposalsGenerator(Hashtable)
	 */
	public String proposalsGenerator(Hashtable<String,Integer> table) {
		
		logGames.debug("Début de proposalsGenerator()");

		String out = "";
		String previousCombi = null;
		int random =0;
		
		
		if(!tryList.isEmpty()) {
			
			previousCombi = tryList.get(tryList.size()-1);
		}
		
		this.intervalAdjustment(table, previousCombi);
		
		if(previousCombi != null) {
			
			for(int i = 0; i < this.getNbrCombi(); i++) {
				while(true) {
					if(table.get(""+i).equals(1)) {
						random = new Random().nextInt(Integer.valueOf(interval.get(""+i).substring(1, 2))+1);
						if(random >= Integer.valueOf(interval.get(""+i).substring(0, 1)) && random <= Integer.valueOf(interval.get(""+i).substring(1, 2))) {
							break;
						}
					}
					else if(table.get(""+i).equals(-1)) {
						random = new Random().nextInt(Integer.valueOf(interval.get(""+i).substring(1, 2))+1);
						if(random >= Integer.valueOf(interval.get(""+i).substring(0, 1)) && random <= Integer.valueOf(interval.get(""+i).substring(1, 2))) {
							break;
						}
					}
					else if(table.get(""+i).equals(0)) {
						random = Integer.valueOf(interval.get(""+i).substring(0, 1));
						break;
					}
				}
				
				out += String.valueOf(random);
			}
		}
		
		
		else {
			for(int i = 0; i < this.getNbrCombi(); i++) {
				random = new Random().nextInt(10);
				out += String.valueOf(random);
			}
		}
		
		tryList.add(out);
		
		logGames.debug("Fin de proposalsGenerator()");
		
		return out;
	}
	
	/**
	 * <p>Cette méthode ajuste l'interval des valeurs possible de la combinaison, grâce à cet ajustement la proposition généré est de plus en plus proche du résultat.</p>
	 * 
	 * @param table
	 * 				Une table de hachage contenant les resultats avec leurs emplacements associés
	 * @param previousCombi
	 * 						Chaine de caractères (String) contenant la combinaison précédement joué
	 * 
	 * <p>Le fonctionnement de cette méthode se compose de deux étapes : </p>
	 * <p>La première s'applique dans le cas du premier tour, lorsque la table de hachage contenant les différents interval est vide, ça attribut à chaque emplacement de la combinaison un interval par défaut </p>
	 * <p>La seconde étape s'applique toujours après le premier tour, il faut voir l'interval de chaque élément de la combinaison comme un étau de deux valeurs qui se ressère après chaque proposition de façon
	 *  à ce que cet étau ne laisse plus qu'une seule valeur possible par emplacement dans le choix de la proposition</p>
	 *  
	 *  <p>Exemple simple avec un seule emplacement pour mieux comprendre où l'on cherche la valeur 5 :</p> 
	 *  <p>
	 *  <ul>
	 *  <li>L'interval par défaut est [0;9], la proposition généré est 2, le résultat de la combinaison sera "+", l'interval sera maintenant [3;9]</li>
	 *  <li>La seconde proposition sera 6, le résultat sera donc "-", l'interval sera ajusté à [3;5]</li>
	 *  <li>Et ainsi de suite jusqu'a ce que l'ordinateur trouve la bonne valeur et dans ce cas, ce ne sera plus un interval mais simplement la valeur qui sera enrengistré et qui sera toujours associé à son emplacement</li>
	 *  </ul>
	 *  </p>
	 * 
	 * @see SearchMoreLess#interval
	 * @see SearchMoreLess#comparison(String, String)
	 * @see SearchMoreLess#proposalsGenerator(Hashtable)
	 */
	public void intervalAdjustment(Hashtable<String,Integer> table, String previousCombi) {
		
		logGames.trace("Début d'intervalAdjustment()");

		String nwInterval;
		
		if(interval.isEmpty()) {
			for(int i = 0; i < this.getNbrCombi(); i++) {
				interval.put(""+i, "09");
			}
		}
		
		else {

			for(int i = 0; i < this.getNbrCombi(); i++) {
			
				if(table.get(""+i).equals(1) && Integer.valueOf(previousCombi.substring(i, i+1)) >= Integer.valueOf(interval.get(""+i).substring(0, 1))) {	
					nwInterval = String.valueOf(Integer.valueOf(previousCombi.substring(i, i+1))+1) + interval.get(""+i).substring(1, 2);
					interval.put(""+i, nwInterval);
				}
				else if(table.get(""+i).equals(-1) && Integer.valueOf(previousCombi.substring(i, i+1)) <= Integer.valueOf(interval.get(""+i).substring(1, 2))) {
					nwInterval = interval.get(""+i).substring(0, 1) + String.valueOf(Integer.valueOf(previousCombi.substring(i, i+1))-1);
					interval.put(""+i, nwInterval);
				}
				else if(table.get(""+i).equals(0)) {
					nwInterval = previousCombi.substring(i,i+1);
					interval.put(""+i, nwInterval);
				}			
			}
		}
		
		for(int i = 0; i < this.getNbrCombi(); i++) {
			if(interval.get(""+i).length() == 1) {
				logGames.trace("Valeur de l'interval à l'emplacement "+(i+1)+" : ["+interval.get(""+i).substring(0, 1)+"]");
			}
			else if (interval.get(""+i).length() == 2){
				logGames.trace("Valeur de l'interval à l'emplacement "+(i+1)+" : ["+interval.get(""+i).substring(0, 1)+";"+interval.get(""+i).substring(1, 2)+"]");
			}
		}
		
		logGames.trace("Fin d'intervalAdjustment()");
	}
}
