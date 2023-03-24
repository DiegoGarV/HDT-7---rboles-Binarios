import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

public class Lector {    


    /**
    Lee un archivo de texto y traduce cada línea utilizando el diccionario provisto.
    @param dictionary el diccionario a utilizar para la traducción.
    @param sourceLanguage el idioma origen del texto.
    @param targetLanguage el idioma destino de la traducción.
    @return una cadena de texto con todas las líneas del archivo traducidas.
    */

    public String lectura(Diccionario dictionary, String sourceLanguage, String targetLanguage){
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader("texto.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String translatedLine = dictionary.translateLine(line, sourceLanguage, targetLanguage);
                sb.append(translatedLine).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
    Lee un archivo de diccionario y agrega las palabras al diccionario provisto.
    @param dictionary el diccionario a actualizar con las nuevas palabras.
    */
    
    public void lecturaDic(Diccionario dictionary){
        Logger logger = Logger.getLogger("MyLogger");
        try (BufferedReader br = new BufferedReader(new FileReader("diccionario.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 3) {
                    logger.severe("Linea invalida en diccionario.txt: " + line);
                    continue;
                } else {
                    String englishWord = parts[0].trim().toLowerCase();
                    String spanishWord = parts[1].trim().toLowerCase();
                    String frenchWord = parts[2].trim().toLowerCase();
                    dictionary.addWord(englishWord, spanishWord, frenchWord);
                }
            }
        } catch (IOException e) {
            logger.severe(e.getMessage());
        }
    }
}
