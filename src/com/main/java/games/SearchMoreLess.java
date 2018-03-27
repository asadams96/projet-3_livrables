package com.main.java.games;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

import com.main.java.modes.Modes;
	
/**
 * <p>Cette classe, SearchMoreLess, est une classe enfant de "Games", en plus des attributs/m�thodes que contient "Games", elle poss�de :
 * <ul>
 * 	<li>Une table de hachage "interval"</li>
 * 	<li>Une liste de chaine de caract�res "tryList"</li>
 * 	<li>Une m�thode "intervalAdjustment"</li>
 * </ul>
 * </p>
 * 
 * <p>Le but de ce jeu est de trouver une combinaison � x chiffres � l'aide d'indication : "+" / "-" / "=" pour chacun des chiffres de la combinaison.</p>
 * <p>Celui qui doit trouver la combinaison secr�te ou effectuer la saisie d�pend du mode de jeu choisi.</p> 
 * 
 * @see Games
 * @see SearchMoreLess#interval
 * @see SearchMoreLess#tryList
 * @see SearchMoreLess#SearchMoreLess()
 * @see SearchMoreLess#initGame()
 * @see SearchMoreLess#verifCombi(String, String)
 * @see SearchMoreLess#displayResultsCombi(Hashtable)
 * @see SearchMoreLess#defenderCombi(Hashtable)
 * @see SearchMoreLess#intervalAdjustment(Hashtable, String)
 * @see Modes
 * 
 * @author Ayrton
 * @version 1.0
 *
 */
public class SearchMoreLess extends Games{
	
	/**
	 * <p>L'interval des valeurs possible de la combinaison pour g�n�rer la proposition o� la cl� repr�sente l'emplacement de chaque chiffre dans la combinaison et la valeur repr�sente l'interval des valeurs possible qui s'ajuste au fur et � mesure des propositions</p>
	 * 
	 * @see SearchMoreLess#intervalAdjustment(Hashtable, String)
	 */
	private Hashtable<String,String> interval;
	
	/**
	 * <p>La liste des propositions effectu�es de fa�on � pouvoir les r�cup�rer</p>
	 */
	private ArrayList<String> tryList;
	
	/**
	 * <p>Le contructeur du jeu SearchMoreLess, o� le nombre de "couleurs" (<= pour ce jeu ce sera des chiffres) utilisable est d�finis avec une valeur qui restera inchang� ([0;9]), quelques soit la configuration mise en place.</p>
	 */
	public SearchMoreLess() {
		
		this.setNbrUsableFigures(10);
		
	}
	
	/**
	 * <p>La m�thode initialise le jeu SearchMoreLess en instanciant ses attributs avec une valeur par d�faut.</p>
	 * 
	 * @see SearchMoreLess#interval
	 * @see SearchMoreLess#tryList
	 * @see Games#initGame()
	 */
	public void initGame() {
		interval = new Hashtable<String,String>();
		tryList = new ArrayList<String>();
	}

	/**
	 * <p>La m�thode compare la proposition et la combinaison pour le jeu SearchMoreLess</p>
	 * 
	 * <p>Le fonctionnement de la m�thode est assez rudimentaire :
	 * <ul>
	 * <li>Chaque chiffre de la proposition est compar� avec le chiffre de la combinaison ayant le m�me emplacement.</li>
	 * <li>Chaque resultat de la comparaison est stocker dans une table de hachage avec l'emplacement en cl� et le resultat en valeur</li>
	 * <li>Il y a trois valeurs possible de resultat :
	 * <ul>
	 * 	<li>le r�sultat = "1" ==> correspond au "+"</li>
	 * 	<li>le resultat = "-1" ==> correspond au "-"</li>
	 * 	<li>le resulat = "0" ==> correspond au "="</li>
	 * </ul>
	 * </li>
	 * <li>Retourne la table de hachage contenant la liste des cl�s et leurs r�sultats associ�s</li>
	 * </ul>
	 * </p>
	 * 
	 * @see Games#verifCombi(String, String)
	 */
	public Hashtable<String, Integer> verifCombi(String combination, String proposal) {
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
		
		return out;
		
	}
	
	/**
	 * <p>Formatage des r�sultats dans une chaine de carat�res pour le jeu SearchMoreLess</p>
	 * <p>
	 * <ul>
	 * 	<li>le r�sultat = "1" ==> correspond au "+"</li>
	 * 	<li>le resultat = "-1" ==> correspond au "-"</li>
	 * 	<li>le resulat = "0" ==> correspond au "="</li>
	 * </ul>
	 * </p>
	 * 
	 * @see SearchMoreLess#verifCombi(String, String)
	 */
	public String displayResultsCombi(Hashtable<String, Integer> table) {
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
		
		return out;
	}
	
	/**
	 * <p>Cette m�thode va g�n�rer une proposition � x chiffres en fonction d'un interval de valeur qui sera sans cesse ajuster � chaque nouvelle proposition. Cependant lors du premier tour, la premier proposition g�n�r�
	 * sera un nombre al�atoire, plus pr�cis�ment ce sera x fois le m�me nombre et ce nombre sera donc al�atoire. </p> 
	 * 
	 * <p>Le nombre x correspond � la taille de la combinaison</p>
	 * 
	 * @see SearchMoreLess#verifCombi(String, String)
	 * @see SearchMoreLess#intervalAdjustment(Hashtable, String)
	 * @see SearchMoreLess#tryList
	 * @see SearchMoreLess#interval
	 * @see Games#getNbrCombi()
	 * @see Games#defenderCombi(Hashtable)
	 */
	public String defenderCombi(Hashtable<String,Integer> table) {
		
		String out = "";
		String previousCombi = null;
		int random =0;
		
		
		try {
			previousCombi = tryList.get(tryList.size()-1);
							
		}catch(ArrayIndexOutOfBoundsException e) {}
		
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
		
		
		else { // Sinon (si) previousCombi est null => On est au premier tour
			for(int i = 0; i < this.getNbrCombi(); i++) {
				random = new Random().nextInt(10);
				out += String.valueOf(random);
			}
		}
		
		tryList.add(out);
		
		return out;
	}
	
	/**
	 * <p>Cette m�thode ajuste l'interval des valeurs possible de la combinaison, gr�ce � cet ajustement la proposition g�n�r� est de plus en plus proche du r�sultat.</p>
	 * 
	 * @param table
	 * 				Une table de hachage contenant les resultats avec leurs emplacements associ�s
	 * @param previousCombi
	 * 						Chaine de caract�res (String) contenant la combinaison pr�c�dement jou�
	 * 
	 * <p>Le fonctionnement de cette m�thode se compose de deux �tapes : </p>
	 * <p>La premi�re s'applique dans le cas du premier tour, lorsque la table de hachage contenant les diff�rents interval est vide, �a attribut � chaque emplacement de la combinaison un interval par d�faut </p>
	 * <p>La seconde �tape s'applique toujours apr�s le premier tour, il faut voir l'interval de chaque �l�ment de la combinaison comme un �tau de deux valeurs qui se ress�re apr�s chaque proposition de fa�on
	 *  � ce que cet �tau ne laisse plus qu'une seule valeur possible par emplacement dans le choix de la proposition</p>
	 *  
	 *  <p>Exemple simple avec un seule emplacement pour mieux comprendre o� l'on cherche la valeur 5 :</p> 
	 *  <p>
	 *  <ul>
	 *  <li>L'interval par d�faut est [0;9], la proposition g�n�r� est 2, le r�sultat de la combinaison sera "+", l'interval sera maintenant [3;9]</li>
	 *  <li>La seconde proposition sera 6, le r�sultat sera donc "-", l'interval sera ajust� � [3;5]</li>
	 *  <li>Et ainsi de suite jusqu'a ce que l'ordinateur trouve la bonne valeur et dans ce cas, ce ne sera plus un interval mais simplement la valeur qui sera enrengistr� et qui sera toujours associ� � son emplacement</li>
	 *  </ul>
	 *  </p>
	 * 
	 * @see SearchMoreLess#interval
	 * @see SearchMoreLess#verifCombi(String, String)
	 * @see SearchMoreLess#defenderCombi(Hashtable)
	 */
	public void intervalAdjustment(Hashtable<String,Integer> table, String previousCombi) {
		
		String nwInterval;

		
		if(interval.isEmpty()) {							// Si c'est le premier tour, applique un interval par defaut pour chaque nombre de la combi
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
	}
}
