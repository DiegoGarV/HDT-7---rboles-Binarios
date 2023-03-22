import org.junit.Test;
import static org.junit.Assert.*;

public class Tests {

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

    @Test
    public void testTranslateWord() {
        Diccionario translator = new Diccionario();
        
        // Traducción de ingles a español
        String text1 = "My house";
        String expected1 = "*My* casa";
        String actual1 = translator.translateWord(text1, "english", "spanish");
        assertEquals(expected1, actual1);
        
        // Traducción de español a frances
        String text2 = "El perro";
        String expected2 = "*Hola* amigo";
        String actual2 = translator.translateWord(text2, "spanish", "french");
        assertEquals(expected2, actual2);
        
        // Traducción de frances a ingles
        String text3 = "Beautiful woman";
        String expected3 = "*Beautiful* femme";
        String actual3 = translator.translateWord(text3, "french", "english");
        assertEquals(expected3, actual3);
    }
    
}
