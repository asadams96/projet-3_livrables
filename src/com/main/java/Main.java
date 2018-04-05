package com.main.java;

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
		
		/*
		start = new StartUp(true);
		start.launch();
		*/
		
		/*
		start = new StartUp(false);
		start.launch();
		*/
		
		/*
		start = new StartUp();
		start.launch();
		*/
		
		logMain.info("Fin de l'application");
			
	}

}
