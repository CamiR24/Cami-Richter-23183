import java.util.Comparator;

public class Dictionary {
    private BinarySearchTree<String, String, String> englishTree;
    private BinarySearchTree<String, String, String> spanishTree;
    private BinarySearchTree<String, String, String> frenchTree;

    public Dictionary() {
        Comparator<String> stringComparator = Comparator.naturalOrder();
        englishTree = new BinarySearchTree<>(stringComparator);
        spanishTree = new BinarySearchTree<>(stringComparator);
        frenchTree = new BinarySearchTree<>(stringComparator);

        englishTree.insertFromFile("diccionario.txt");
        spanishTree.insertFromFile("diccionario.txt");
        frenchTree.insertFromFile("diccionario.txt");
    }

    public String translate(String word, String language) {
        if ("english".equalsIgnoreCase(language)) {
            String translationEnglishToSpanish = spanishTree.find1(word); // Buscar la traducción en español
            String translationEnglishToFrench = englishTree.find2(word); // Buscar la traducción en francés
            // Construir la respuesta concatenando las traducciones
            return "Spanish: " + translationEnglishToSpanish + ", French: " + translationEnglishToFrench;
        } else if ("spanish".equalsIgnoreCase(language)) {
            String translationSpanishToEnglish = spanishTree.find1(word); // Buscar la traducción en español
            String translationSpanishToFrench = spanishTree.find2(word); // Buscar la traducción en francés
            // Construir la respuesta concatenando las traducciones
            return "English: " + translationSpanishToEnglish + ", French: " + translationSpanishToFrench;
        } else if ("french".equalsIgnoreCase(language)) {
            String translationFrenchToEnglish = frenchTree.find1(word); // Buscar la traducción en español
            String translationFrenchToSpanish = frenchTree.find2(word); // Buscar la traducción en francés
            // Construir la respuesta concatenando las traducciones
            return "English: " + translationFrenchToEnglish + ", Spanish: " + translationFrenchToSpanish;
        } else {
            return null; // Idioma no soportado
        }
    }
}
