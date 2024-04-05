import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el idioma inicial (english, spanish, french):");
        String language = scanner.nextLine().toLowerCase();

        BinarySearchTree<String, String, String> englishTree = new BinarySearchTree<>(String::compareTo);
        BinarySearchTree<String, String, String> spanishTree = new BinarySearchTree<>(String::compareTo);
        BinarySearchTree<String, String, String> frenchTree = new BinarySearchTree<>(String::compareTo);

        if (language.equalsIgnoreCase("english")) {
            Path englishPath = Paths.get("diccionario.txt");
            try (Stream<String> lines = Files.lines(englishPath)) {
                lines.forEach(line -> {
                    String[] parts = line.split(",");
                    englishTree.insert(parts[0].trim(), parts[1].trim(), parts[2].trim());
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (language.equalsIgnoreCase("spanish")) {
            Path spanishPath = Paths.get("diccionario.txt");
            try (Stream<String> lines = Files.lines(spanishPath)) {
                lines.forEach(line -> {
                    String[] parts = line.split(",");
                    spanishTree.insert(parts[1].trim(), parts[2].trim(), parts[0].trim());
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (language.equalsIgnoreCase("french")) {
            Path frenchPath = Paths.get("diccionario.txt");
            try (Stream<String> lines = Files.lines(frenchPath)) {
                lines.forEach(line -> {
                    String[] parts = line.split(",");
                    frenchTree.insert(parts[2].trim(), parts[1].trim(), parts[0].trim());
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Ingrese la palabra a traducir:");
        String word = scanner.nextLine();

        String translatedWord = translate(word, englishTree, spanishTree, frenchTree, language);

        if (translatedWord != null) {
            System.out.println("La traducción es: " + translatedWord);
        } else {
            System.out.println("Lo sentimos, no pudimos encontrar la traducción para la palabra '" + word + "'.");
        }

        System.out.println("Desea imprimir el diccionario completo?");
        System.out.println("1: español");
        System.out.println("2: inglés");
        System.out.println("3: francés");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    spanishTree.inorderTraversal(spanishTree);
                    break;

                case 2:
                    englishTree.inorderTraversal(englishTree);
                    break;

                case 3:
                    frenchTree.inorderTraversal(frenchTree);
                    break;
            
                default:
                    break;
            }
            
        }

    public static String translate(String word, BinarySearchTree<String, String, String> englishTree, BinarySearchTree<String, String, String> spanishTree, BinarySearchTree<String, String, String> frenchTree, String language) {
        String translation1 = null;
        String translation2 = null;

        if (language.equalsIgnoreCase("english")) {
            translation1 = englishTree.find1(word);
            translation2 = englishTree.find2(word);
        } else if (language.equalsIgnoreCase("spanish")) {
            translation1 = spanishTree.find1(word);
            translation2 = spanishTree.find2(word);
        } else if (language.equalsIgnoreCase("french")) {
            translation1 = frenchTree.find1(word);
            translation2 = frenchTree.find2(word);
        }

        return translation1 + ", " + translation2;
    }
    
}

