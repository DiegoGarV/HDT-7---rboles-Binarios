import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

public class Lector {    


    // Leer archivo de texto
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

    // Leer archivo de diccionario
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
