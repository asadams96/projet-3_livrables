package com.main.java;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.log4j.Logger;;

public class Main {
	
	/**
	 * <p>Le log de la classe Main.java</p>
	 */
	static final private Logger logMain = Logger.getLogger(Main.class);
	
	/**
	 * <p>L'objet static permettant l'execution de l'application</p>
	 */
	public static StartUp start;
	
	private static Scanner scan = new Scanner(System.in);
	
	/**
	 * <p>La méthode main de l'application</p>
	 * <p>Il y a trois possibilité de lancement de l'application où seul l'instanciation de l'objet "start" diffère (mis sous forme de commentaire): 
	 * 	<ul>
	 * 		<li>"start = new StartUp(true);" => lancement de l'application avec le mode développeur activé</li>
	 * 		<li>"start = new StartUp(false);" => lancement de l'application avec le mode développeur désactivé</li>
	 * 		<li>"start = new StartUp();" => lancement de l'application où le mode développeur est définit via le fichier de configuration "config.properties"</li>
	 * 	</ul>
	 * </p>
	 * 
	 * @param args
	 * 				Le paramètre de la méthode main
	 */
	public static void main(String[] args)  {
		
		logMain.info("Lancement de l'application");
		
		boolean again = true;
		int answer;
		
		while(again) {
			try {
				
				System.out.println("Activer le mode développeur ?");
				System.out.println("1. Oui          2. Non          3. Paramètre par défaut");
				answer = scan.nextInt();
			
				switch(answer) {
					case 1: 
						start = new StartUp(true);
						again = false;
						break;
					case 2:
						start = new StartUp(false);
						again = false;
						break;
					case 3:
						start = new StartUp();
						again = false;
						break;
					default:
						logMain.error("Saisie invalide dans le choix du mode développeur => ["+answer+"]");
					
				}
				
			} catch (InputMismatchException e) {
				logMain.error("Saisie invalide dans choiceGame() => ["+scan.nextLine()+"]");				
			} 
		}
		
		start.launch();
		
		logMain.info("Fin de l'application");
			
	}

}
