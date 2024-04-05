import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class BinarySearchTree<K,V1,V2> implements ITree<K,V1,V2> {

    private int count;
    private TreeNode<K,V1,V2> root;
    private Comparator<K> keyComparator;
    private Map<String, Integer> languageMap;

    public BinarySearchTree(Comparator<K> keyComparator){
        this.root = null;
        this.count = 0;
        this.keyComparator = keyComparator;
        this.languageMap = new HashMap<>();
        languageMap.put("english", 1);
        languageMap.put("spanish", 2);
        languageMap.put("french", 3);

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
    public TreeNode<K, V1, V2> getFirstNode() {
        if (isEmpty()) {
            return null;
        }

        TreeNode<K, V1, V2> node = root;

        while (node.getLeft() != null) {
            node = node.getLeft();
        }

        return node;
    }

    @Override
    public void InOrderWalk(IWalk<V1,V2> walk) {
        InternalInOrderWalk(root, walk);
    }

    private void InternalInOrderWalk(TreeNode<K,V1,V2> actualNode, IWalk<V1,V2> walk){
        if (actualNode != null){
            InternalInOrderWalk(actualNode.getLeft(), walk);

            walk.doWalk(actualNode.getValue1(), actualNode.getValue2());

            InternalInOrderWalk(actualNode.getRight(), walk);
        }
    }

    public void insertFromFile(String filename) {
        Path path = Paths.get(filename);
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(line -> {
                String[] parts = line.split(",");

                int maxLanguageIndex = Math.max(Math.max(languageMap.get("english"), languageMap.get("spanish")), languageMap.get("french"));
                K key;
                V1 value1;
                V2 value2;
                if (maxLanguageIndex == languageMap.get("english")) {
                    key = (K) parts[0].trim();
                    value1 = (V1) parts[1].trim();
                    value2 = (V2) parts[2].trim();
                } else if (maxLanguageIndex == languageMap.get("spanish")) {
                    key = (K) parts[1].trim();
                    value1 = (V1) parts[2].trim();
                    value2 = (V2) parts[0].trim();
                } else {
                    key = (K) parts[2].trim();
                    value1 = (V1) parts[0].trim();
                    value2 = (V2) parts[1].trim();
                }
                this.insert(key, value1, value2);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void inOrderWalkForLanguage(IWalk<V1, V2> walk, String language) {
        if ("english".equalsIgnoreCase(language)) {
            InternalInOrderWalk(root, walk);
        } else if ("spanish".equalsIgnoreCase(language)) {
            internalInOrderWalkForSpanish(root, walk);
        } else if ("french".equalsIgnoreCase(language)) {
            internalInOrderWalkForFrench(root, walk);
        }
    }

    private void internalInOrderWalkForSpanish(TreeNode<K, V1, V2> actualNode, IWalk<V1, V2> walk) {
        if (actualNode != null) {
            internalInOrderWalkForSpanish(actualNode.getLeft(), walk);
            walk.doWalk(actualNode.getValue1(), actualNode.getValue2());
            internalInOrderWalkForSpanish(actualNode.getRight(), walk);
        }
    }


    private void internalInOrderWalkForFrench(TreeNode<K, V1, V2> actualNode, IWalk<V1, V2> walk) {
        if (actualNode != null) {
            internalInOrderWalkForFrench(actualNode.getLeft(), walk);
            walk.doWalk(actualNode.getValue1(), actualNode.getValue2()); // Corrected V2 to V1
            internalInOrderWalkForFrench(actualNode.getRight(), walk);
        }
    }

    public void inorderTraversal(TreeNode<K,V1,V2> node) {
        if (node != null) {
            inorderTraversal(node.getLeft());
            System.out.println(node.getKey() + ": " + node.getValue1() + ", " + node.getValue2());
            inorderTraversal(node.getRight());
        }
    }

    public void inorderTraversal(BinarySearchTree<K,V1,V2> tree) {
        inorderTraversal(tree.root);
    
}
}
