public class TreeNode<K, V1, V2> {
    
    private K key;
    private V1 value1;
    private V2 value2;
    private TreeNode<K,V1,V2> left;
    private TreeNode<K,V1,V2> right;
    private TreeNode<K,V1,V2> parent;

    public TreeNode(K key, V1 value1, V2 value2){
        this.key = key;
        this.value1 = value1;
        this.value2 = value2;
        parent = null;
        left = null;
        right = null;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V1 getValue1() {
        return value1;
    }

    public void setValue1(V1 value1) {
        this.value1 = value1;
    }

    public V2 getValue2() {
        return value2;
    }

    public void setValue2(V2 value2) {
        this.value2 = value2;
    }

    public TreeNode<K,V1,V2> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<K,V1,V2> left) {
        this.left = left;
    }

    public TreeNode<K,V1,V2> getRight() {
        return right;
    }

    public void setRight(TreeNode<K,V1,V2> right) {
        this.right = right;
    }

    public TreeNode<K,V1,V2> getParent() {
        return parent;
    }

    public void setParent(TreeNode<K,V1,V2> parent) {
        this.parent = parent;
    }
}
