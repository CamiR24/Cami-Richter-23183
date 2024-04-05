package src;

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

    public BinarySearchTree<String, String, String> getTree(int language) {
        if (language == 1) {
            return englishTree;
        } else if (language == 2) {
            return spanishTree;
        } else if (language == 3) {
            return frenchTree;
        } else {
            throw new IllegalArgumentException("Invalid language code");
        }
    }

}