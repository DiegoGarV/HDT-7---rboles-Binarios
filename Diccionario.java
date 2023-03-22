/*
* Autores:
* Diego García 22404
* Mónica Salvatierra 22249 
* Fecha: 19/03/2023
* Hoja de Trabajo #7
* Diccionario
*/

import java.util.List;
import java.util.ArrayList;

public class Diccionario {
    protected BinarySearchTree<String, Association<String, String>> englishBST;
    protected BinarySearchTree<String, Association<String, String>> spanishBST;
    protected BinarySearchTree<String, Association<String, String>> frenchBST;

    public Diccionario() {
        englishBST = new BinarySearchTree<>();
        spanishBST = new BinarySearchTree<>();
        frenchBST = new BinarySearchTree<>();
    }

    public List<String> getInOrderTraversal() {
        List<String> lines = new ArrayList<>();
        for (String key : englishBST.keys()) {
            Association<String, String> association = englishBST.search(key);
            String englishWord = association.getKey();
            String spanishWord = association.getValue();
            String frenchWord = association.getValue2();
            String line = String.format("%-20s | %-20s | %-20s | %-20s", englishWord, spanishWord, englishWord,
                    frenchWord);
            lines.add(line);
        }
        return lines;
    }

    public String translateLine(String line, String sourceLanguage, String targetLanguage) {
        StringBuilder translatedLine = new StringBuilder();
        String[] words = line.split("\\s+");
        for (String word : words) {
            word = word.trim().toLowerCase();
            String translatedWord = translateWord(word, sourceLanguage, targetLanguage);
            if (translatedWord == null) {
                translatedLine.append("*").append(word).append("* ");
            } else {
                translatedLine.append(translatedWord).append(" ");
            }
        }
        return translatedLine.toString();
    }

    public void addWord(String englishWord, String spanishWord, String frenchWord) {
        englishBST.put(englishWord.toLowerCase(), new Association<String, String>(englishWord.toLowerCase(),
                spanishWord.toLowerCase(), frenchWord.toLowerCase()));
        spanishBST.put(spanishWord.toLowerCase(), new Association<String, String>(englishWord.toLowerCase(),
                spanishWord.toLowerCase(), frenchWord.toLowerCase()));
        frenchBST.put(frenchWord.toLowerCase(), new Association<String, String>(englishWord.toLowerCase(),
                spanishWord.toLowerCase(), frenchWord.toLowerCase()));
    }

    public String translateWord(String text, String sourceLanguage, String targetLanguage) {
        StringBuilder result = new StringBuilder();

        // Obtener el árbol BST correspondiente al idioma de origen y destino
        BinarySearchTree<String, Association<String, String>> fromTree = null;
        BinarySearchTree<String, Association<String, String>> toTree = null;
        if (sourceLanguage.equalsIgnoreCase("english")) {
            fromTree = englishBST;
        } else if (sourceLanguage.equalsIgnoreCase("spanish")) {
            fromTree = spanishBST;
        } else if (sourceLanguage.equalsIgnoreCase("french")) {
            fromTree = frenchBST;
        }
        if (targetLanguage.equalsIgnoreCase("english")) {
            toTree = englishBST;
        } else if (targetLanguage.equalsIgnoreCase("spanish")) {
            toTree = spanishBST;
        } else if (targetLanguage.equalsIgnoreCase("french")) {
            toTree = frenchBST;
        }

        // Iterar sobre las palabras en el texto
        for (String word : text.split("\\s+")) {
            // Buscar la palabra en el árbol BST correspondiente al idioma de origen
            Association<String, String> association = null;
            if (fromTree != null) {
                association = fromTree.search(word.toLowerCase());
            }

            // Si se encontró la palabra, buscar la traducción en el árbol BST
            // correspondiente al idioma de destino
            String translation = null;
            if (association != null && toTree != null) {
                if (toTree == englishBST) {
                    translation = association.getKey();
                } else if (toTree == spanishBST) {
                    translation = association.getValue();
                } else if (toTree == frenchBST) {
                    translation = association.getValue2();
                }
            }

            // Agregar la palabra traducida al resultado si se encontró la traducción,
            // de lo contrario agregar la palabra original entre asteriscos
            if (translation != null) {
                result.append(translation).append(" ");
            } else {
                result.append("*").append(word).append("* ");
            }
        }
        return result.toString().trim();

    }
}
