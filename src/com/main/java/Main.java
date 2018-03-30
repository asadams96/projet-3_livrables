package com.main.java;

import org.apache.log4j.Logger;;

public class Main {

	//-Dlog4j.configurationFile=src/com/main/resources/log4j2.xml
	// La ligne ci dessus est a mettre dans RunConfiguration... <= Expliquer dans le read me
	
	/**
	 * <p>Le log de la classe Main.java</p>
	 */
	static final private Logger logMain = Logger.getLogger(Main.class);
	
	/**
	 * <p>L'objet static permettant l'execution de l'application</p>
	 */
	public static StartUp start;
	
	public static void main(String[] args)  {
		
		logMain.info("Lancement de l'application");
		
		start = new StartUp();
		start.launch();
		
		logMain.info("Fin de l'application");
			
	}

}
