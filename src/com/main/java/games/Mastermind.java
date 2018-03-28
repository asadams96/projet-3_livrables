package com.main.java.games;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

import com.main.java.modes.Modes;

/**
 * <p>Cette classe, Mastermind, est une classe enfant de "Games", en plus des attributs/m�thodes que contient "Games", elle poss�de :
 * <ul>
 * 	<li>Une table de hachage d'entiers : wellPlacedSaved</li>
 * 	<li>Une liste d'entiers : goodValuesSaved</li>
 * 	<li>Une liste d'entiers : badValuesSaved</li>
 * 	<li>Une liste de chaines de caract�res : tryList</li>
 * 	<li>Deux booleans :
 * 		<ul>		
 *  		<li>firstStep</li>
 * 			<li>nwValue</li>
 * 		</ul>
 * 	<li>Trois entiers :
 *		<ul> 
 *  		<li>placement</li>
 * 			<li>savingPlacement</li>
 * 			<li>value</li>
 * 		</ul>
 * </ul>
 * </p>
 * 
 * <p>Le but de ce jeu est de trouver les x couleurs de la combinaison secr�te � l'aide d'indication sur le nombre de couleur pr�sente et bien plac� de la proposition par rapport � la combinaison.</p>
 * <p>Celui qui doit trouver la combinaison secr�te ou effectuer la saisie d�pend du mode de jeu choisi.</p> 
 * 
 * @see Games
 * @see Modes
 * @see Mastermind#wellPlacedSaved
 * @see Mastermind#goodValuesSaved
 * @see Mastermind#tryList
 * @see Mastermind#badValuesSaved
 * @see Mastermind#firstStep
 * @see Mastermind#nwValue
 * @see Mastermind#placement
 * @see Mastermind#savingPlacement
 * @see Mastermind#value
 * @see Mastermind#Mastermind()
 * @see Mastermind#initGame()
 * @see Mastermind#comparison(String, String)
 * @see Mastermind#formattingTheResults(Hashtable)
 * @see Mastermind#proposalsGenerator(Hashtable)
 * @see Mastermind#placementUpdate()
 * 
 * @author Ayrton
 * @version 1.0
 *
 */
public class Mastermind extends Games{
		
	/**
	 * <p>La table de hachage d'entiers contenant l'emplacement ainsi que les valeurs des couleurs bien plac�s.</p>
	 * 
	 * @see Mastermind#proposalsGenerator(Hashtable)
	 */
	private Hashtable<Integer,Integer> wellPlacedSaved;
		
	/**
	 * <p>La liste d'entier contenant des couleurs pr�sentes ou bien plac�s non ajout� � la table de hachage wellPlacedSaved</p>
	 * 
	 * @see Mastermind#proposalsGenerator(Hashtable)
	 * @see Mastermind#wellPlacedSaved
	 */
	private ArrayList<Integer> goodValuesSaved; 
		
	/**
	 * <p>La liste de chaine de caract�res contenant des propositions effectu�es par l'ordinateur afin de trouver la bonne combinaison.</p>
	 * 
	 * @see Mastermind#proposalsGenerator(Hashtable)
	 */
	private ArrayList<String> tryList;
		
	/**
	 * <p>La liste d'entier contenant les mauvaises couleurs, reduit le nombre de coup necessaire pour trouver la combinaison en ne r�utilisant pas les mauvaises couleurs lors des prochaines proposition.</p>
	 * 
	 * @see Mastermind#proposalsGenerator(Hashtable)
	 */
	private ArrayList<Integer> badValuesSaved;
		
	/**
	 * <p>Boolean informant la m�thode de g�n�ration de proposition afin de savoir si elle doit rester � la premi�re �tape ou passer � la deuxi�me.</p>
	 * <p>Vrai (true) => On reste dans la premi�re �tape / Faux (false) => On passe � la seconde �tape. </p>
	 * 
	 * @see Mastermind#proposalsGenerator(Hashtable)
	 */
	private boolean firstStep;
		
	/**
	 * <p>Boolean informant la m�thode de g�n�ration de propositon afin de savoir si une nouvelle couleur doit �tre test� ou non.</p>
	 * <p>Vrai (true) => On test avec une nouvelle valeur / Faux (false) => On garde l'ancienne valeur.</p>
	 * 
	 * @see Mastermind#proposalsGenerator(Hashtable)
	 * @see Mastermind#value
	 */
	private boolean nwValue;
		
	/**
	 * <p>Un entier servant � indiquer le placement de la valeur � tester lors de la g�n�ration de la proposition.</p> 
	 * 
	 * @see Mastermind#proposalsGenerator(Hashtable)
	 * @see Mastermind#value
	 * @see Mastermind#savingPlacement
	 */
	private int placement;
		
	/**
	 * <p>Un entier servant a enregistrer la valeur du placement dans la derni�re proposition, permettra de l'enrengistrer dans la table de hachage "wellPlacedValue" si la valeur �tait bien plac�. </p>
	 * 
	 * @see Mastermind#proposalsGenerator(Hashtable)
	 * @see Mastermind#value
	 * @see Mastermind#placement
	 */
	private int savingPlacement;
		
	/**
	 * <p>Un entier servant � indiqu� la couleur qu'on est entrain de tester</p>
	 * 
	 * @see Mastermind#proposalsGenerator(Hashtable)
	 * @see Mastermind#nwValue
	 */
	private int value;

		
	/**
	 * <p>Le constructeur par d�faut du jeu Mastermind,  non obligatoire <= il est vide.</p>
	 */
	public Mastermind() {}
	
	/**
	 * <p>La m�thode initialise le jeu Mastermind en instanciant ses attributs avec une valeur par d�faut.</p>
	 * 
	 * @see Mastermind#wellPlacedSaved
	 * @see Mastermind#goodValuesSaved
	 * @see Mastermind#tryList
	 * @see Mastermind#badValuesSaved
	 * @see Mastermind#firstStep
	 * @see Mastermind#nwValue
	 * @see Mastermind#placement
	 * @see Mastermind#savingPlacement
	 * @see Mastermind#value
	 * @see Games#initGame()
	 */
	public void initGame() {
		wellPlacedSaved = new Hashtable<Integer,Integer>();
		goodValuesSaved = new ArrayList<Integer>();
		tryList = new ArrayList<String>();
		badValuesSaved = new ArrayList<Integer>();
		firstStep = true;
		nwValue = true;
		placement = 0;
		savingPlacement = 0;
		value = 0;
	}
	
	/**
	 * La m�thode compare la combinaison avec la proposition pour le jeu du Mastermind
	 * 
	 * Dans les grandes lignes, la m�thode fonctionne par r�currence :
	 * 
	 * <ul>
	 * 	<li>Dans la premi�re boucle "FOR" => pour les couleurs bien plac�s => on compare la couleur de la proposition avec la couleur de la combinaison de m�me emplacement.</li>
	 * 	<li>Dans la deuxi�me boucle "FOR" imbriqu� dans la premi�re => pour les couleurs pr�sentes => on compare une couleur de la proposition avec toutes les couleurs de la combinaison (l'une apr�s l'autre) jusqu'a trouv� une correspondance. 
	 * 		Dans ce cas la boucle s'arr�te pr�matur�ment. </li>
	 *  <li>Puis on repart sur la premi�re boucle "FOR" en incr�mentant le compteur afin de passer � la couleur suivante... et ainsi de suite</li>
	 *  <li>Lorsque la comparaison est termin�e, on soustrait le nombre de pr�sent au nombre de bien plac�s.</li>
	 *  <li>On stock les r�sultats dans une table de hachage</li>
	 *  <li>Et enfin, on retourne cette table de hachage contenant les r�sultats</li>
	 * </ul>
	 * 
	 * <p>De plus, pour le bon fonctionnement de la m�thode et �viter les doublons dans les couleurs pr�sentes s'il y a plusieurs fois la m�me couleur dans la proposition, 
	 * une liste est cr�e afin d'ajouter les couleurs de la combinaison qui ont d�ja �t� valid�s avec une couleur de la proposition.</p>
	 * 
	 * @see Games#comparison(String, String) 
	 */
	public Hashtable<String,Integer> comparison(String combination, String proposal) {
		
		Hashtable<String, Integer> out = new Hashtable<String, Integer>();
		ArrayList<Integer> presentListIndex = new ArrayList<Integer>();
		int wellPlaced = 0;
		int present = 0;
						
		for(int i = 0; i < this.getNbrCombi(); i++) {
			
			if(proposal.substring(i,i+1).equals(combination.substring(i, i+1))) {
				wellPlaced++;
			}
			
			for(int j = 0; j < this.getNbrCombi(); j++) {
								
				if(proposal.substring(i, i+1).equals(combination.substring(j, j+1)) && !presentListIndex.contains(j)) {
					presentListIndex.add(j);
					present++;
					break;
				}
			}
		}
		
		present = present-wellPlaced; // Un chiffre bien plac� est forcement pr�sent donc present > wellPlaced (quelle que soit la situation)
									 // Evite les situation comme 4 pr�sents / 4 bien plac�s => 0 pr�sent / 4 bien plac�s
		

		out.put("present", present);
		out.put("wellPlaced", wellPlaced);
		
		return out;
	}
	
	/**
	 * <p>Formatage des r�sultats dans une chaine de carat�res pour le jeu du Mastermind</p>
	 * <p> Le fonctionnement de la m�thode :
	 * <ul>
	 * 	<li>R�cup�ration des r�sultats</li>
	 * 	<li>Formatage des r�sultats selon leurs valeurs dans la chaine de caract�res (String)</li>
	 * 	<li>Retourne la chaine de caract�res</li>
	 * 
	 * @see Mastermind#comparison(String, String)
	 * @see Games#formattingTheResults(Hashtable)
	 */
	public String formattingTheResults(Hashtable<String, Integer> table) {
		int present = table.get("present");
		int wellPlaced = table.get("wellPlaced");
		String out = "";
		if(present == 0 && wellPlaced == 0) {
			out = "Aucun chiffre de pr�sent et de bien plac�.";
		}
		else if(present != 0 && wellPlaced != 0) {
			out = ""+present+" pr�sent(s) / "+wellPlaced+" bien plac�(s).";
		}
		else if(present == 0 && wellPlaced != 0) {
			out = ""+wellPlaced+" bien plac�(s).";
		}
		else if(present != 0 && wellPlaced == 0) {
			out = ""+present+" pr�sent(s).";
		}
		
		return out;
	}
	
	/**
	 * <p>Cette m�thode est assez complexe, elle g�n�re une proposition � x couleurs en fonction des couleurs bien plac�s et pr�sentes dans la proposition pr�c�dente (s'il y en a une).</p>
	 * <p>Le nombre x de couleurs correspond � la taille de la combinaison.</p>
	 * <p>Cette m�thode prend en param�tre la table de hachage conteant les r�sultats fourni par la m�thode de v�rification des propositions.</p> 
	 * <p>Cette m�thode se d�compose en deux �tapes :
	 * <ul>
	 * 	<li>
	 * 		La premi�re �tape consiste � g�n�rer x fois la m�me couleur al�atoirement. puis lors de la prochaine g�n�ration de proposition deux cas de figures sont possibles :
	 * 		<ul>
	 * 			<li>Le r�sultat contient au moins un bien plac� par rapport � la combinaison, dans ce cas on l'ajoute � la liste "goodValuesSaved" et on reste dans la premi�re �tape.</li>
	 * 			<li>Le r�sultat ne contient pas de bien plac�, on l'ajoute � la liste "badValuedSaved" et on passe � la seconde �tape.</li>
	 * 		</ul>
	 * 	</li>
	 * 	<li>
	 * 		<p>La seconde �tape consite � placer une fois le premi�re �l�ment de la liste des bonnes valeurs "goodValuedSaved" avec des mauvaises valeurs pour completer la proposition, except� lorsque des couleurs bien plac�s ont �t� trouv� (dans ce cas
	 *  	elle garde leur positionnement dans les futurs propositions), jusqu'a ce que cet �l�ment soit bien plac� en d�calant sont placement � chaque essai.</p> <br />
	 * 		<p>Lorsque la couleur est bien plac�, on supprime la couleur de la liste "goodValuesSaved" et on l'enrengistre ainsi que son placement dans la liste "wellPlacedSaved"</p><br />
	 * 		<p>Si/Quand la liste "goodValuesSaved" est vide, une nouvelle couleur est tent� de la m�me mani�re que si elle �tait pr�sente dans la liste. Cependant lors du r�sultat, si elle n'est pas pr�sente dans la combinaison,
	 * 		elle sera ajout� � la liste des mauvaises couleurs "badValuesSaved" � l'essai suivant et une autre couleur sera g�n�r�.</p>
	 * 	</li>
	 * </ul>
	 * </p>
	 * 
	 * <p>De plus, si le nombre de bonne couleurs enregistr�s dans la liste "goodValuedSaved" est �gal � la taille de la combinaison, on peut passer � la deuxi�me �tape</p>
	 * <p>Cette algorithme �tant bas� sur le fait qu'il faut trouv� une mauvaise couleur pour passer � la seconde �tape, s'il n'y en a pas, l'ordinateur peut "tricher" et en cr�e une qui sera "-1".</p> 
	 * <p>A la fin de la m�thode, ce "-1" sera transform� en "X".</p>
	 * 
	 * <br />
	 * <p>Liste des diff�rents attributs propre � la m�thode ainsi que leurs fonctions : 
	 * <ul>
	 * 	<li>String previousCombi => Chaine de caract�res repr�sentant la proposition pr�c�dente (lorsqu'il y en a une).</li>
	 * 	<li>int present => Entier repr�sentant le nombre de couleurs pr�sentes, initialis� � -99. </li>
	 * 	<li>int wellPlaced => Entier rep�sentant le nombre de couleurs bien plac�s, initialis� � -99.</li>
	 * 	<li>int random => Entier repr�sentant une couleur al�atoire. </li>
	 * 	<li>boolean toAddTable => Boolean qui informe si l'on peut ajouter une couleur � la table des couleurs bien plac�s "wellPlacedSaved" <= lorsqu'il est � vrai (true), initialis� � vrai.  </li>
	 * 	<li>String out => Chaine de caract�res repr�sentant la valeur de retour, et contenant la proposition. </li>
	 * </ul>
	 * </p>
	 * 
	 * @see Mastermind#comparison(String, String)
	 * @see Mastermind#placementUpdate()
	 * @see Mastermind#wellPlacedSaved
	 * @see Mastermind#goodValuesSaved
	 * @see Mastermind#tryList
	 * @see Mastermind#badValuesSaved
	 * @see Mastermind#firstStep
	 * @see Mastermind#nwValue
	 * @see Mastermind#placement
	 * @see Mastermind#savingPlacement
	 * @see Mastermind#value
	 * @see Games#proposalsGenerator(Hashtable)
	 */
	public String proposalsGenerator(Hashtable<String,Integer> table) {
		String out = "";
		String previousCombi = "";
		int present = -99;
		int wellPlaced = -99;
		int random;
		boolean toAddTable = true;
		
		try {
			previousCombi = tryList.get(tryList.size()-1);
		}catch(ArrayIndexOutOfBoundsException e) {}
		
		try {
			present = table.get("present");
			wellPlaced = table.get("wellPlaced");
		}catch(NullPointerException e) {}
		
		
		if(firstStep) {
			if(wellPlaced == 0) {
				if(previousCombi.contains("-1")) {
					badValuesSaved.add(Integer.valueOf(previousCombi.substring(0, 2)));	// CAS -1
				}
				else {
					badValuesSaved.add(Integer.valueOf(previousCombi.substring(0, 1)));
				}
								
				toAddTable = false;
				firstStep = false;
			}
			
			else  {
				if(wellPlaced > 0) {
					for(int i = 0; i < wellPlaced; i++) {
						goodValuesSaved.add(Integer.valueOf(previousCombi.substring(0, 1)));
					}
				}
				
				while(true) {
					random = new Random().nextInt(this.getNbrUsableFigures());
					if(goodValuesSaved.size() == this.getNbrCombi() && this.getNbrCombi() >= this.getNbrUsableFigures()) {
						if(tryList.size() < this.getNbrUsableFigures()) {							
							while(true) {
								random = new Random().nextInt(this.getNbrUsableFigures());
								if(!goodValuesSaved.contains(random)) {								
									badValuesSaved.add(0, random);
									break;
								}
							}
						}
						else {																	
							badValuesSaved.add(0, -1);
						}
						firstStep = false;
						toAddTable = false;
						wellPlaced = 0;
						present =0 ;
						break;
					}
					else if(!goodValuesSaved.contains(random)) {									
						for(int i = 0; i < this.getNbrCombi(); i++) {
							out += String.valueOf(random);
						}
						break;
					}
				}
				tryList.add(out);				
			}
			
		}
		
		if(!firstStep) {
			if(present == 0 && wellPlaced == wellPlacedSaved.size() &&toAddTable) {
				badValuesSaved.add(value);
				nwValue = true;
			}
				
			if(wellPlaced > wellPlacedSaved.size() && present == 0  && !wellPlacedSaved.containsKey(savingPlacement)) {
					if(!this.isSeveralTimesSameColor()) {
						badValuesSaved.add(value);	
					}
					
					if(!goodValuesSaved.isEmpty()) {
						goodValuesSaved.remove(0);
					}
					
					wellPlacedSaved.put(savingPlacement, value );
					nwValue = true;
				}
				
			if(nwValue) {
				if(!goodValuesSaved.isEmpty()) {
					value = goodValuesSaved.get(0);
				}
				
				else {
					while(true) {
						value = new Random().nextInt(this.getNbrUsableFigures());
						if(!badValuesSaved.contains(value)) {
							break;
						}
					}
				}
				
				placement = placementUpdate();
				nwValue = false;
			}
				
			for(int i = 0; i < this.getNbrCombi(); i++) {
				if(wellPlacedSaved.get(i) != null) {
					out += String.valueOf(wellPlacedSaved.get(i));
				}
				
				else if(placement == i) {
					out += String.valueOf(value);
				}
					
				else {
					out += String.valueOf(badValuesSaved.get(0));
				}
			}
			
			savingPlacement = placement;
			placement = placementUpdate();
		}
				
		if(out.contains("-1")) { 
			out = out.replaceAll("-1", "X");
		}
		
		return out;
	}
	
	/**
	 * <p>Cette m�thode retourne le nouveau placement pour la prochaine g�n�ration de proposition.</p>
	 * <p>Cette m�thode peut s'ex�cuter de deux facons diff�rentes, qui sont assez similaires : 
	 * <ul>
	 * 	<li>Si le boolean "nwValue" est vrai (true), le nouveau placement sera le premier placement, en partant du placement "0", o� une couleur bien plac� n'a encore pas �t� trouv�.</li>
	 * 	<li>Sinon le nouveau placement sera la prochaine position o� une couleur bien plac� n'a pas encore �t� trouv�, cette fois en partant de l'ancien placement, quelques soit sa valeur.</li>
	 * </ul>
	 * </p>
	 * 
	 * @return
	 * 			Un entier qui sera le nouveau placement
	 * 
	 * @see Mastermind#nwValue
	 * @see Mastermind#wellPlacedSaved
	 * @see Mastermind#placement
	 * @see Mastermind#proposalsGenerator(Hashtable)
	 */
	public int placementUpdate() {
		int out = 0;
		
		if(nwValue) {
			for(int i = 0; i < this.getNbrCombi(); i++) {
				if(wellPlacedSaved.get(i) != null) {
					out++;
				}
				else {
					break;
				}
			}
		}
		else {
			out = placement;
			while(true) {
				if(wellPlacedSaved.get(out+1) != null) {
					out++;
				}
				else {
					out++;
					break;
				}
			}
		}
		
		return out;
	}

}
