Tout d'abord, r�cuperer le fichier "src" contenant les classes "*.java" et les deux fichiers de configuration "config.properties" et "log4j2.xml" puis importer ce fichier dans votre IDE.

Importer �galement la l'API "log4j2". Si vous ne la possedez pas, elle est aussi disponible sur GitHub dans le fichier "libs".

Modifier les param�tres de lancement de votre IDE (sur eclipse -> Run / Run Configurations... / Arguments / VM arguments) en ajoutant cette ligne : 
"-Dlog4j.configurationFile=src/com/main/resources/log4j2.xml".
Cela va permettre � l'API "log4j2" de trouver le fichier "log4j2.xml" dans le dossier de ressources.

Modifier les fichiers "config.properties" et "log4j2.xml" avec les configurations d�sir�es (suivre les instructions de mofications pr�sentes dans les fichiers).
Vous pouvez laisser les configurations par d�faut.

Vous pouvez � pr�sent lancer l'application via la classe "Main.java", et vous laissez guider par les instructions afficher dans la console.