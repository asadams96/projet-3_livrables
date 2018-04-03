Tout d'abord, récuperer le fichier "src" contenant les classes "*.java" et les deux fichiers de configuration "config.properties" et "log4j2.xml" sur GitHub.

Puis importer ce fichier dans votre IDE et modifier les paramètres de lancement (sur eclipse -> Run / Run Configurations... / Arguments / VM arguments) en ajoutant cette ligne : 
"-Dlog4j.configurationFile=src/com/main/resources/log4j2.xml".

Modifier les fichiers "config.properties" et "log4j2.xml" avec les configurations désirées (suivre les instructions de mofications présentes dans les fichiers) ou laisser les configurations par défaut.

Vous pouvez à présent lancer l'application via la classe "Main.java", et vous laissez guider par les instructions afficher dans la console.