package com.main.java.configurations;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.main.java.StartUp;

/**
 * <p>La classe permettant de lire le fichier "config.properties" et de mettre à jour les paramètres dans l'énumération "Config.java".</p>
 * <p>Elle est composé : 
 * <ul>
 * 	<li>De 3 attributs :
 * 		<ul>
 * 			<li>l'attribut "properties" de type "Properties"</li>
 * 			<li>l'attribut "input" de type "InputStream"</li>
 * 			<li>l'attribut "path" de type "String"</li>
 * 		</ul>
 * 	</li>
 * 	<li>D'un constructeur</li>
 * </ul>
 * </p>
 * 
 * @see ReadProperties#properties
 * @see ReadProperties#input
 * @see ReadProperties#path
 * @see ReadProperties#ReadProperties()
 * @see StartUp
 * @see Config
 * 
 * @author Ayrton
 * @version 1.0
 *
 */
public class ReadProperties {
	
	/**
	 * <p>Le log de la classe ReadProperties.java</p>
	 */
	final private Logger logReadProperties = Logger.getLogger(ReadProperties.class);
	
	/**
	 * <p>L'attribut "properties" permet d'obtenir les propriétés du fichier "config.properties".</p>
	 * 
	 * @see ReadProperties#input
	 * @see ReadProperties#path
	 * @see ReadProperties#ReadProperties()
	 */
	final private Properties properties = new Properties();
	
	/**
	 * <p>L'attribut "path" représente le chemin absolu du fichier "config.properties".</p>
	 * 
	 * @see ReadProperties#properties
	 * @see ReadProperties#input
	 * @see ReadProperties#ReadProperties()
	 */
	final private String path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "com" + File.separator + "main" +
							File.separator + "resources" + File.separator + "config.properties";
	
	/**
	 * <p>L'attribut "input" représente le flux de lecture du fichier "config.properties".</p>
	 */
	private InputStream input = null;
	
	/**
	 * <p>Le constructeur de la classe "ReadProperties".</p>
	 * 
	 * <p>A l'instanciation, le role de se construteur est : 
	 * <ul>
	 * 	<li>D'initialisé l'attribut "input" en fonction de l'attribut "path" </li>
	 * 	<li>D'appelé la méthode de chargement de l'attribut "properties" sur le flux représenté par l'attribut "input"</li>
	 * 	<li>De mettre à jour les propriétés des paramètres de jeu dans l'énumération "Config.java" </li>
	 * 	<li>De fermer le flux "input"</li>
	 * </ul>
	 * </p>
	 * 
	 * <p>De plus, la classe "ReadProperties.java" est instancié dans la classe "StartUp.java"</p>
	 * 
	 * @see ReadProperties#input
	 * @see ReadProperties#path
	 * @see ReadProperties#properties
	 * @see Config#GameParameters
	 * @see StartUp
	 * @see Config
	 */
	public ReadProperties() {
		
		logReadProperties.info("Début de ReadProperties()");
		
		try {
			
			input = new FileInputStream(path);
			
			properties.load(input);
			
			Config.GameParameters.setNbrCombi(Integer.valueOf(properties.getProperty("parameter.nbrCombi")));
			Config.GameParameters.setNbrMaxTry(Integer.valueOf(properties.getProperty("parameter.nbrMaxTry")));
			Config.GameParameters.setNbrUsableFigures(Integer.valueOf(properties.getProperty("parameter.nbrUsableFigures")));
			Config.GameParameters.setDevMode(Boolean.valueOf(properties.getProperty("parameter.devMode")));
			
			logReadProperties.info("Lecture du fichier Config.properties et affectation de ses propriétés à Config.java -> Réussi.");
			
		} catch(FileNotFoundException e) {
			logReadProperties.error("Le fichier Config.properties n'a pas été trouvé -> Config.java garde ses valeurs par défaut. "+e);
		} catch (IOException e) {
			logReadProperties.error("Echec de l'ouverture du flux vers le fichier Config.properties -> Config.java garde ses valeurs par défaut. "+e);;
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					logReadProperties.error("Echec de la fermeture du flux. "+e);
				}
			}
		}
		
		logReadProperties.info("Format combinaison -> ["+Config.GameParameters.getNbrCombi()+"] / Nombre d'essais autorisé -> ["+Config.GameParameters.getNbrMaxTry()+"] "
				+ "/ Nombre de couleurs utilisables -> ["+Config.GameParameters.getNbrUsableFigures()+"] / Mode développeur -> ["+Config.GameParameters.isDevMode()+"].");
		
		logReadProperties.info("Fin de ReadProperties()");
	}


}
