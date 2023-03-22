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

    public void loadDictionary(String fileName, BinarySearchTree<String, Association<String, String>> englishBST, BinarySearchTree<String, Association<String, String>> spanishBST, BinarySearchTree<String, Association<String, String>> frenchBST) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            Association<String, String> association = new Association<String, String>(parts[0], parts[1], parts[2]);
            englishBST.put(parts[0].toLowerCase(), association);
            spanishBST.put(parts[1].toLowerCase(), association);
            frenchBST.put(parts[2].toLowerCase(), association);
        }
        reader.close();
    }
}
