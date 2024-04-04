import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;

public class Dictionary {
    private BinarySearchTree<String, String, String> englishTree;
    private BinarySearchTree<String, String, String> spanishTree;
    private BinarySearchTree<String, String, String> frenchTree;
    private int language;

    public Dictionary(String filename) {
        Comparator<String> stringComparator = Comparator.naturalOrder();
        englishTree = new BinarySearchTree<>(stringComparator);
        spanishTree = new BinarySearchTree<>(stringComparator);
        frenchTree = new BinarySearchTree<>(stringComparator);

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (filename.contains("english")) {
                    englishTree.insert(parts[0].trim(), parts[1].trim(), parts[2].trim());
                } else if (filename.contains("spanish")) {
                    spanishTree.insert(parts[1].trim(), parts[2].trim(), parts[0].trim());
                    language = 2;
                } else if (filename.contains("french")) {
                    frenchTree.insert(parts[0].trim(), parts[1].trim(), parts[2].trim());
                    language = 3;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String translate(String word, String language) {
        TreeNode<String, String, String> node = null;
        if ("english".equals(language)) {
            node = spanishTree.getFirstNode();
        } else if ("spanish".equals(language)) {
            node = englishTree.getFirstNode();
        } else if ("french".equals(language)) {
            node = englishTree.getFirstNode();
        }
    
        if (node != null) {
            do {
                if (node.getKey().equals(word)) {
                    if (language.equals("english")) {
                        return node.getValue2();
                    } else if (language.equals("spanish")) {
                        return node.getValue1();
                    } else {
                        return node.getValue1();
                    }
                }
                node = node.getRight();
            } while (node != null && !node.getKey().equals(word));
    
            node = node.getLeft();
    
            if (node != null && node.getKey().equals(word)) {
                if (language.equals("english")) {
                    return node.getValue2();
                } else if (language.equals("spanish")) {
                    return node.getValue1();
                } else {
                    return node.getValue1();
                }
            }
        }
    
        return null;
    }

    public String translate1(String word, String language) {
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