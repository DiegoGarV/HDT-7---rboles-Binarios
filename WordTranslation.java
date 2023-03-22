public class WordTranslation {
    private String englishWord;
    private String spanishWord;
    private String frenchWord;

    public WordTranslation(String englishWord, String spanishWord, String frenchWord) {
        this.englishWord = englishWord;
        this.spanishWord = spanishWord;
        this.frenchWord = frenchWord;
    }

    public String getEnglishWord() {
        return englishWord;
    }

    public String getSpanishWord() {
        return spanishWord;
    }

    public String getFrenchWord() {
        return frenchWord;
    }
}
