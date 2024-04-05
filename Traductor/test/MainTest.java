package test;
import org.junit.Test;
import src.BinarySearchTree;
import src.Main;
import static org.junit.Assert.*;

public class MainTest {

    // Prueba unitaria para el método translate
    @Test
    public void testTranslate() {
        BinarySearchTree<String, String, String> englishTree = new BinarySearchTree<>(String::compareTo);
        englishTree.insert("hello", "hola", "bonjour");
        BinarySearchTree<String, String, String> spanishTree = new BinarySearchTree<>(String::compareTo);
        spanishTree.insert("hola", "hello", "bonjour");
        BinarySearchTree<String, String, String> frenchTree = new BinarySearchTree<>(String::compareTo);
        frenchTree.insert("bonjour", "hello", "hola");
        
        assertEquals("hola, bonjour", Main.translate("hello", englishTree, spanishTree, frenchTree, "english"));
        assertEquals("hello, bonjour", Main.translate("hola", englishTree, spanishTree, frenchTree, "spanish"));
        assertEquals("hello, hola", Main.translate("bonjour", englishTree, spanishTree, frenchTree, "french"));
    }
}
