package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringJoiner;

public class Translator {

    public static String translateFromFile(String filename, BinarySearchTree<String, String, String> englishTree, BinarySearchTree<String, String, String> spanishTree, BinarySearchTree<String, String, String> frenchTree) {
        StringBuilder translatedSentence = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\s+");
                StringJoiner joiner = new StringJoiner(" ");
                for (String word : words) {
                    String translation = translateWord(word, englishTree, spanishTree, frenchTree);
                    joiner.add(translation);
                }
                translatedSentence.append(joiner.toString()).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return translatedSentence.toString();
    }

    private static String translateWord(String word, BinarySearchTree<String, String, String> englishTree, BinarySearchTree<String, String, String> spanishTree, BinarySearchTree<String, String, String> frenchTree) {
        String translation = null;

        if ((translation = englishTree.find1(word)) != null) {
            return translation;
        } else if ((translation = spanishTree.find1(word)) != null) {
            return translation;
        } else if ((translation = frenchTree.find1(word)) != null) {
            return translation;
        } else {
            return "*" + word + "*"; // Envolver la palabra entre asteriscos si no se encuentra en ningún árbol
        }
    }
}


