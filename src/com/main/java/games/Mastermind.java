package com.main.java.games;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

import com.main.java.modes.Modes;

/**
 * <p>Cette classe, Mastermind, est une classe enfant de "Games", en plus des attributs/méthodes que contient "Games", elle possède :
 * <ul>
 * 	<li>Une table de hachage d'entiers : wellPlacedSaved</li>
 * 	<li>Une liste d'entiers : goodValuesSaved</li>
 * 	<li>Une liste d'entiers : badValuesSaved</li>
 * 	<li>Une liste de chaines de caractères : tryList</li>
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
 * <p>Le but de ce jeu est de trouver les x couleurs de la combinaison secrète à l'aide d'indication sur le nombre de couleur présente et bien placé de la proposition par rapport à la combinaison.</p>
 * <p>Celui qui doit trouver la combinaison secrète ou effectuer la saisie dépend du mode de jeu choisi.</p> 
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
	 * <p>La table de hachage d'entiers contenant l'emplacement ainsi que les valeurs des couleurs bien placés.</p>
	 * 
	 * @see Mastermind#proposalsGenerator(Hashtable)
	 */
	private Hashtable<Integer,Integer> wellPlacedSaved;
		
	/**
	 * <p>La liste d'entier contenant des couleurs présentes ou bien placés non ajouté à la table de hachage wellPlacedSaved</p>
	 * 
	 * @see Mastermind#proposalsGenerator(Hashtable)
	 * @see Mastermind#wellPlacedSaved
	 */
	private ArrayList<Integer> goodValuesSaved; 
		
	/**
	 * <p>La liste de chaine de caractères contenant des propositions effectuées par l'ordinateur afin de trouver la bonne combinaison.</p>
	 * 
	 * @see Mastermind#proposalsGenerator(Hashtable)
	 */
	private ArrayList<String> tryList;
		
	/**
	 * <p>La liste d'entier contenant les mauvaises couleurs, reduit le nombre de coup necessaire pour trouver la combinaison en ne réutilisant pas les mauvaises couleurs lors des prochaines proposition.</p>
	 * 
	 * @see Mastermind#proposalsGenerator(Hashtable)
	 */
	private ArrayList<Integer> badValuesSaved;
		
	/**
	 * <p>Boolean informant la méthode de génération de proposition afin de savoir si elle doit rester à la première étape ou passer à la deuxième.</p>
	 * <p>Vrai (true) => On reste dans la première étape / Faux (false) => On passe à la seconde étape. </p>
	 * 
	 * @see Mastermind#proposalsGenerator(Hashtable)
	 */
	private boolean firstStep;
		
	/**
	 * <p>Boolean informant la méthode de génération de propositon afin de savoir si une nouvelle couleur doit être testé ou non.</p>
	 * <p>Vrai (true) => On test avec une nouvelle valeur / Faux (false) => On garde l'ancienne valeur.</p>
	 * 
	 * @see Mastermind#proposalsGenerator(Hashtable)
	 * @see Mastermind#value
	 */
	private boolean nwValue;
		
	/**
	 * <p>Un entier servant à indiquer le placement de la valeur à tester lors de la génération de la proposition.</p> 
	 * 
	 * @see Mastermind#proposalsGenerator(Hashtable)
	 * @see Mastermind#value
	 * @see Mastermind#savingPlacement
	 */
	private int placement;
		
	/**
	 * <p>Un entier servant a enregistrer la valeur du placement dans la dernière proposition, permettra de l'enrengistrer dans la table de hachage "wellPlacedValue" si la valeur était bien placé. </p>
	 * 
	 * @see Mastermind#proposalsGenerator(Hashtable)
	 * @see Mastermind#value
	 * @see Mastermind#placement
	 */
	private int savingPlacement;
		
	/**
	 * <p>Un entier servant à indiqué la couleur qu'on est entrain de tester</p>
	 * 
	 * @see Mastermind#proposalsGenerator(Hashtable)
	 * @see Mastermind#nwValue
	 */
	private int value;

		
	/**
	 * <p>Le constructeur par défaut du jeu Mastermind,  non obligatoire <= il est vide.</p>
	 */
	public Mastermind() {}
	
	/**
	 * <p>La méthode initialise le jeu Mastermind en instanciant ses attributs avec une valeur par défaut.</p>
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
	 * La méthode compare la combinaison avec la proposition pour le jeu du Mastermind
	 * 
	 * Dans les grandes lignes, la méthode fonctionne par récurrence :
	 * 
	 * <ul>
	 * 	<li>Dans la première boucle "FOR" => pour les couleurs bien placés => on compare la couleur de la proposition avec la couleur de la combinaison de même emplacement.</li>
	 * 	<li>Dans la deuxième boucle "FOR" imbriqué dans la première => pour les couleurs présentes => on compare une couleur de la proposition avec toutes les couleurs de la combinaison (l'une après l'autre) jusqu'a trouvé une correspondance. 
	 * 		Dans ce cas la boucle s'arrête prématurément. </li>
	 *  <li>Puis on repart sur la première boucle "FOR" en incrémentant le compteur afin de passer à la couleur suivante... et ainsi de suite</li>
	 *  <li>Lorsque la comparaison est terminée, on soustrait le nombre de présent au nombre de bien placés.</li>
	 *  <li>On stock les résultats dans une table de hachage</li>
	 *  <li>Et enfin, on retourne cette table de hachage contenant les résultats</li>
	 * </ul>
	 * 
	 * <p>De plus, pour le bon fonctionnement de la méthode et éviter les doublons dans les couleurs présentes s'il y a plusieurs fois la même couleur dans la proposition, 
	 * une liste est crée afin d'ajouter les couleurs de la combinaison qui ont déja été validés avec une couleur de la proposition.</p>
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
		
		present = present-wellPlaced; // Un chiffre bien placé est forcement présent donc present > wellPlaced (quelle que soit la situation)
									 // Evite les situation comme 4 présents / 4 bien placés => 0 présent / 4 bien placés
		

		out.put("present", present);
		out.put("wellPlaced", wellPlaced);
		
		return out;
	}
	
	/**
	 * <p>Formatage des résultats dans une chaine de caratères pour le jeu du Mastermind</p>
	 * <p> Le fonctionnement de la méthode :
	 * <ul>
	 * 	<li>Récupération des résultats</li>
	 * 	<li>Formatage des résultats selon leurs valeurs dans la chaine de caractères (String)</li>
	 * 	<li>Retourne la chaine de caractères</li>
	 * 
	 * @see Mastermind#comparison(String, String)
	 * @see Games#formattingTheResults(Hashtable)
	 */
	public String formattingTheResults(Hashtable<String, Integer> table) {
		int present = table.get("present");
		int wellPlaced = table.get("wellPlaced");
		String out = "";
		if(present == 0 && wellPlaced == 0) {
			out = "Aucun chiffre de présent et de bien placé.";
		}
		else if(present != 0 && wellPlaced != 0) {
			out = ""+present+" présent(s) / "+wellPlaced+" bien placé(s).";
		}
		else if(present == 0 && wellPlaced != 0) {
			out = ""+wellPlaced+" bien placé(s).";
		}
		else if(present != 0 && wellPlaced == 0) {
			out = ""+present+" présent(s).";
		}
		
		return out;
	}
	
	/**
	 * <p>Cette méthode est assez complexe, elle génère une proposition à x couleurs en fonction des couleurs bien placés et présentes dans la proposition précédente (s'il y en a une).</p>
	 * <p>Le nombre x de couleurs correspond à la taille de la combinaison.</p>
	 * <p>Cette méthode prend en paramètre la table de hachage conteant les résultats fourni par la méthode de vérification des propositions.</p> 
	 * <p>Cette méthode se décompose en deux étapes :
	 * <ul>
	 * 	<li>
	 * 		La première étape consiste à générer x fois la même couleur aléatoirement. puis lors de la prochaine génération de proposition deux cas de figures sont possibles :
	 * 		<ul>
	 * 			<li>Le résultat contient au moins un bien placé par rapport à la combinaison, dans ce cas on l'ajoute à la liste "goodValuesSaved" et on reste dans la première étape.</li>
	 * 			<li>Le résultat ne contient pas de bien placé, on l'ajoute à la liste "badValuedSaved" et on passe à la seconde étape.</li>
	 * 		</ul>
	 * 	</li>
	 * 	<li>
	 * 		<p>La seconde étape consite à placer une fois le première élément de la liste des bonnes valeurs "goodValuedSaved" avec des mauvaises valeurs pour completer la proposition, excepté lorsque des couleurs bien placés ont été trouvé (dans ce cas
	 *  	elle garde leur positionnement dans les futurs propositions), jusqu'a ce que cet élément soit bien placé en décalant sont placement à chaque essai.</p> <br />
	 * 		<p>Lorsque la couleur est bien placé, on supprime la couleur de la liste "goodValuesSaved" et on l'enrengistre ainsi que son placement dans la liste "wellPlacedSaved"</p><br />
	 * 		<p>Si/Quand la liste "goodValuesSaved" est vide, une nouvelle couleur est tenté de la même manière que si elle était présente dans la liste. Cependant lors du résultat, si elle n'est pas présente dans la combinaison,
	 * 		elle sera ajouté à la liste des mauvaises couleurs "badValuesSaved" à l'essai suivant et une autre couleur sera généré.</p>
	 * 	</li>
	 * </ul>
	 * </p>
	 * 
	 * <p>De plus, si le nombre de bonne couleurs enregistrés dans la liste "goodValuedSaved" est égal à la taille de la combinaison, on peut passer à la deuxième étape</p>
	 * <p>Cette algorithme étant basé sur le fait qu'il faut trouvé une mauvaise couleur pour passer à la seconde étape, s'il n'y en a pas, l'ordinateur peut "tricher" et en crée une qui sera "-1".</p> 
	 * <p>A la fin de la méthode, ce "-1" sera transformé en "X".</p>
	 * 
	 * <br />
	 * <p>Liste des différents attributs propre à la méthode ainsi que leurs fonctions : 
	 * <ul>
	 * 	<li>String previousCombi => Chaine de caractères représentant la proposition précédente (lorsqu'il y en a une).</li>
	 * 	<li>int present => Entier représentant le nombre de couleurs présentes, initialisé à -99. </li>
	 * 	<li>int wellPlaced => Entier repésentant le nombre de couleurs bien placés, initialisé à -99.</li>
	 * 	<li>int random => Entier représentant une couleur aléatoire. </li>
	 * 	<li>boolean toAddTable => Boolean qui informe si l'on peut ajouter une couleur à la table des couleurs bien placés "wellPlacedSaved" <= lorsqu'il est à vrai (true), initialisé à vrai.  </li>
	 * 	<li>String out => Chaine de caractères représentant la valeur de retour, et contenant la proposition. </li>
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
	 * <p>Cette méthode retourne le nouveau placement pour la prochaine génération de proposition.</p>
	 * <p>Cette méthode peut s'exécuter de deux facons différentes, qui sont assez similaires : 
	 * <ul>
	 * 	<li>Si le boolean "nwValue" est vrai (true), le nouveau placement sera le premier placement, en partant du placement "0", où une couleur bien placé n'a encore pas été trouvé.</li>
	 * 	<li>Sinon le nouveau placement sera la prochaine position où une couleur bien placé n'a pas encore été trouvé, cette fois en partant de l'ancien placement, quelques soit sa valeur.</li>
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
