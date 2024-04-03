import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Stream;

public class BinarySearchTree<K,V1,V2> implements ITree<K,V1,V2> {

    private int count;
    private TreeNode<K,V1,V2> root;
    private Comparator<K> keyComparator;
    private int language;

    public BinarySearchTree(Comparator<K> keyComparator){
        this.root = null;
        this.count = 0;
        this.keyComparator = keyComparator;
    }

    @Override
    public void insert(K key, V1 value1, V2 value2) {
        TreeNode<K,V1,V2> newNode = new TreeNode<K,V1,V2>(key, value1, value2);
        if (isEmpty()){ //Insert in the root
            this.root = newNode;
            this.count++;
        } else { //Insert in a leaf
            internalInsert(this.root, newNode);
        }
    }

    private void internalInsert(TreeNode<K,V1,V2> parent, TreeNode<K,V1,V2> node){
        if (parent != null){
            //0 si K del parent y K del node son iguales
            //-1 si K del node es menor a K del parent
            //1 si K del node es mayor a K del parent
            int result = this.keyComparator.compare(node.getKey(), parent.getKey());
            if (result > 0){  //1 si K del node es mayor a K del parent se va para la derecha
                if (parent.getRight() == null){ //PUedo insertarlo a la derecha
                    parent.setRight(node);
                    node.setParent(parent); //easy for deletion to know who the parent is
                    this.count++;
                } else {
                    internalInsert(parent.getRight(), node);
                }
            } else if (result < 0){ //-1 si K del node es menor a K del parent por lo tanto se va a la izquierda
                if (parent.getLeft() == null){ //PUedo insertarlo a la derecha
                    parent.setLeft(node);
                    node.setParent(parent);
                    this.count++;
                } else {
                    internalInsert(parent.getLeft(), node);
                }
            }
        }
    }

    @Override
    public V1 find1(K keyToFind) {
        if (!isEmpty()){
            return internalSearch1(root, keyToFind);
        } else {
            return null;
        }
    }

    private V1 internalSearch1(TreeNode<K,V1,V2> actualNode, K keyToFind){
        
        if (actualNode != null){
            //Comparar la llave del nodo actual con el valor buscado
            int result = this.keyComparator.compare(keyToFind, actualNode.getKey());
            if (result == 0){ //La llave buscada y la llave del nodo son iguales, devuelvo el valor
                return actualNode.getValue1();
            } else if (result > 0){ //La llave a buscar es mayor a la llave del nodo, me voy a la derecha
                return internalSearch1(actualNode.getRight(), keyToFind);
            } else if (result < 0){ //La llave a buscar es menor, por lo tanto me muevo a la izquierda
                return internalSearch1(actualNode.getLeft(), keyToFind);
            }
        }

        return null; //if the node is null hen the K was not found
    }

    @Override
    public V2 find2(K keyToFind) {
        if (!isEmpty()){
            return (V2) internalSearch2(root, keyToFind);
        } else {
            return null;
        }
    }

    private V2 internalSearch2(TreeNode<K,V1,V2> actualNode, K keyToFind){
        
        if (actualNode != null){
            //Comparar la llave del nodo actual con el valor buscado
            int result = this.keyComparator.compare(keyToFind, actualNode.getKey());
            if (result == 0){ //La llave buscada y la llave del nodo son iguales, devuelvo el valor
                return actualNode.getValue2();
            } else if (result > 0){ //La llave a buscar es mayor a la llave del nodo, me voy a la derecha
                return internalSearch2(actualNode.getRight(), keyToFind);
            } else if (result < 0){ //La llave a buscar es menor, por lo tanto me muevo a la izquierda
                return internalSearch2(actualNode.getLeft(), keyToFind);
            }
        }

        return null; //if the node is null hen the K was not found
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public void InOrderWalk(IWalk<V1,V2> walk) {
        InternalInOrderWalk(root, walk);
    }

    private void InternalInOrderWalk(TreeNode<K,V1,V2> actualNode, IWalk<V1,V2> walk){
        if (actualNode != null){
            InternalInOrderWalk(actualNode.getLeft(), walk);

            walk.doWalk(actualNode.getValue1());

            walk.walk(actualNode.getValue2());

            InternalInOrderWalk(actualNode.getRight(), walk);
        }
    }

    public void insertFromFile(String filename) {
        Path path = Paths.get(filename);
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(line -> {
                String[] parts = line.split(",");

                switch (language) {
                    case 1:
                        K keyEnglish = (K) parts[0].trim(); // La llave sería la palabra en inglés
                        V1 value1Spanish = (V1) parts[1].trim(); // El valor sería la palabra en español
                        V2 value2French = (V2) parts[2].trim();
                        this.insert(keyEnglish, value1Spanish, value2French);
                        break;
                    case 2:
                        K keySpanish = (K) parts[1].trim(); // La llave sería la palabra en inglés
                        V1 value1French = (V1) parts[2].trim(); // El valor sería la palabra en español
                        V2 value2English = (V2) parts[0].trim();
                        this.insert(keySpanish, value1French, value2English);
                        break;
                    case 3:
                        K keyFrench = (K) parts[0].trim(); // La llave sería la palabra en inglés
                        V1 value1English = (V1) parts[1].trim(); // El valor sería la palabra en español
                        V2 value2Spanish = (V2) parts[2].trim();
                        this.insert(keyFrench, value1English, value2Spanish);
                        break;
                    default:
                        break;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
