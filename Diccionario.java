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

/**
 * Clase que representa un diccionario que permite traducir palabras entre los idiomas inglés, español y francés.
 */

public class Diccionario {
    // Árboles para cada uno de los idiomas
    protected BinarySearchTree<String, Association<String, String>> englishBST;
    protected BinarySearchTree<String, Association<String, String>> spanishBST;
    protected BinarySearchTree<String, Association<String, String>> frenchBST;

    /**
     * Constructor que crea los árboles para cada idioma.
     */

    public Diccionario() {

        englishBST = new BinarySearchTree<>();
        spanishBST = new BinarySearchTree<>();
        frenchBST = new BinarySearchTree<>();
    }

    /**
      * Método que añade una palabra y su traducción en los tres idiomas al diccionario.
      * @param englishWord la palabra en inglés.
      * @param spanishWord la palabra en español.
      * @param frenchWord la palabra en francés.
      */

     public void addWord(String englishWord, String spanishWord, String frenchWord) {
        englishBST.put(englishWord.toLowerCase(), new Association<String, String>(englishWord.toLowerCase(),
                spanishWord.toLowerCase(), frenchWord.toLowerCase()));
        spanishBST.put(spanishWord.toLowerCase(), new Association<String, String>(englishWord.toLowerCase(),
                spanishWord.toLowerCase(), frenchWord.toLowerCase()));
        frenchBST.put(frenchWord.toLowerCase(), new Association<String, String>(englishWord.toLowerCase(),
                spanishWord.toLowerCase(), frenchWord.toLowerCase()));
    }
    
    /**
     * Método que toma una línea de texto en un archivo de texto (texto.txt) y la traduce palabra por palabra a partir del idioma origen y destino especificados.
     * @param line la línea de texto a traducir.
     * @param sourceLanguage el idioma de origen.
     * @param targetLanguage el idioma de destino.
     * @return la línea de texto traducida.
     */

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
        if (words[0].trim().toLowerCase().equals(sourceLanguage + ":")){ 
            return translatedLine.toString();
        }
        else{
            String t="";
            return t;
        }
    }

    /**
     * Método que busca la traducción de una palabra a partir del idioma origen y destino especificados.
     * @param text la palabra a traducir.
     * @param sourceLanguage el idioma de origen.
     * @param targetLanguage el idioma de destino.
     * @return la palabra traducida, o null si no se encontró una traducción.
     */
    
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

    /**
    * Realiza el recorrido inorder del árbol, mostrando las asociaciones de las palabras en ingles en los demás idiomas.
    *
    * @return una lista de cadenas de texto con las asociaciones de las palabras en inglés y en otros idiomas.
    */
    
    public List<String> getInOrder() {
        List<String> lines = new ArrayList<>();
        for (String key : englishBST.keys()) { // English: key; Spanish, English, French: value
            Association<String, String> association = englishBST.search(key); // Asocia cada valor a la llave
            String englishWord = association.getKey();
            String spanishWord = association.getValue();
            String frenchWord = association.getValue2();
            String line = String.format("%-20s | %-20s | %-20s | %-20s", englishWord, spanishWord, englishWord,
                    frenchWord);
            lines.add(line);
        }
        return lines;
    }
}
