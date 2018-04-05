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
	 * <p>La m�thode main de l'application</p>
	 * <p>Il y a trois possibilit� de lancement de l'application o� seul l'instanciation de l'objet "start" diff�re (mis sous forme de commentaire): 
	 * 	<ul>
	 * 		<li>"start = new StartUp(true);" => lancement de l'application avec le mode d�veloppeur activ�</li>
	 * 		<li>"start = new StartUp(false);" => lancement de l'application avec le mode d�veloppeur d�sactiv�</li>
	 * 		<li>"start = new StartUp();" => lancement de l'application o� le mode d�veloppeur est d�finit via le fichier de configuration "config.properties"</li>
	 * 	</ul>
	 * </p>
	 * 
	 * @param args
	 * 				Le param�tre de la m�thode main
	 */
	public static void main(String[] args)  {
		
		logMain.info("Lancement de l'application");
		
		boolean again = true;
		int answer;
		
		while(again) {
			try {
				
				System.out.println("Activer le mode d�veloppeur ?");
				System.out.println("1. Oui          2. Non          3. Param�tre par d�faut");
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
						logMain.error("Saisie invalide dans le choix du mode d�veloppeur => ["+answer+"]");
					
				}
				
			} catch (InputMismatchException e) {
				logMain.error("Saisie invalide dans choiceGame() => ["+scan.nextLine()+"]");				
			} 
		}
		
		start.launch();
		
		logMain.info("Fin de l'application");
			
	}

}
