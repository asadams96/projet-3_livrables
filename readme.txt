Tout d'abord, r�cuperer le fichier "src" contenant les classes "*.java" et les deux fichiers de configuration "config.properties" et "log4j2.xml" sur GitHub.

Puis importer ce fichier dans votre IDE et modifier les param�tres de lancement (sur eclipse -> Run / Run Configurations... / Arguments / VM arguments) en ajoutant cette ligne : 
"-Dlog4j.configurationFile=src/com/main/resources/log4j2.xml".

Modifier les fichiers "config.properties" et "log4j2.xml" avec les configurations d�sir�es (suivre les instructions de mofications pr�sentes dans les fichiers) ou laisser les configurations par d�faut.

Vous pouvez � pr�sent lancer l'application via la classe "Main.java", et vous laissez guider par les instructions afficher dans la console.