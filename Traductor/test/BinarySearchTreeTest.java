package test;
import org.junit.Test;
import src.BinarySearchTree;
import static org.junit.Assert.*;

public class BinarySearchTreeTest {

    @Test
    public void testInsertAndFind() {
        BinarySearchTree<String, String, String> tree = new BinarySearchTree<>(String::compareTo);
        tree.insert("house", "value1", "value2");
        assertEquals("value1", tree.find1("house"));
        assertEquals("value2", tree.find2("house"));
    }

    @Test
    public void testFindEmptyTree() {
        BinarySearchTree<Integer, String, String> tree = new BinarySearchTree<>(Integer::compareTo);
        assertNull(tree.find1(10));
        assertNull(tree.find2(10));
    }

    // Añade más pruebas según sea necesario para cubrir otros casos y métodos
}


