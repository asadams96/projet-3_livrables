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
		
	/**
	 * <p>La méthode main de l'application</p>
	 * 
	 * <p>Pour lancer cette méthode en modifiant le mode développeur (et passer outre le fichier config.properties) 
	 * 	l'argument à mettre en paramètre est :
	 * 	<ul>
	 * 		<li>pour l'activation du mode développeur -> devMode=enable</li>
	 * 		<li>pour la désactivation du mode développeur -> devMode=disable</li>
	 * 	</ul>
	 * </p>
	 * 
	 * @param args
	 * 				Le paramètre de la méthode main
	 */
	public static void main(String[] args)  {
		
		logMain.info("Lancement de l'application");
		
		boolean devMode = false;
		boolean property = false;
		
		for(String str : args) {
			if(str.equals("devMode=enable")) {
				devMode = true;
				property = true;
				break;
			}
			else if(str.equals("devMode=disable")) {
				property = true;
				break;
			}
		}
		
		if(property) {
			start = new StartUp(devMode);
		}
		else {
			start = new StartUp();
		}
		
		start.launch();
		
		logMain.info("Fin de l'application");
			
	}

}
