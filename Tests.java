import org.junit.Test;
import static org.junit.Assert.*;

public class Tests {

    /**
    Prueba el método addWord de la clase Diccionario.
    Agrega una palabra al diccionario y comprueba si se agregó correctamente en los tres idiomas.
    */

    @Test
    public void testAddWord() {
        Diccionario dictionary = new Diccionario();

        // aguega una palabra al diccionario
        dictionary.addWord("dog", "perro", "chien");
        
        // revisa si la palabra se agregó correctamente en ingles
        Association<String, String> englishAssociation = dictionary.englishBST.get("dog");
        assertEquals("dog", englishAssociation.getKey());
        assertEquals("perro", englishAssociation.getValue());
        assertEquals("chien", englishAssociation.getValue2());

        // revisa si la palabra se agregó correctamente en español
        Association<String, String> spanishAssociation = dictionary.spanishBST.get("perro");
        assertEquals("dog", spanishAssociation.getKey());
        assertEquals("perro", spanishAssociation.getValue());
        assertEquals("chien", spanishAssociation.getValue2());

        // revisa si la palabra se agregó correctamente en frances
        Association<String, String> frenchAssociation = dictionary.frenchBST.get("chien");
        assertEquals("dog", frenchAssociation.getKey());
        assertEquals("perro", frenchAssociation.getValue());
        assertEquals("chien", frenchAssociation.getValue2());
    }

    /**
    Prueba el método translateWord de la clase Diccionario.
    Traduce una oración en el idioma de origen al idioma de destino y comprueba si la traducción es correcta.
    */
    
    @Test
    public void testTranslateWord() {
        Diccionario translator = new Diccionario();

        translator.addWord("house", "casa", "loger");
        translator.addWord("dog", "perro", "chien");
        translator.addWord("woman", "mujer", "femme");

        // Prueba traducir una oración del inglés al español
        String result = translator.translateWord("My house", "english", "spanish");
        String expected = "*My* casa";
        assertEquals(expected, result);

        // Traducción de español a frances
        String result1 = translator.translateWord("El perro", "spanish", "french");
        String expected1 = "*El* chien";
        assertEquals(expected1,result1);

        // Traducción de frances a ingles
        String result2 = translator.translateWord("Belle femme", "french", "english");
        String expected2 = "*Belle* woman";
        assertEquals(expected2,result2);
    }
    
}
