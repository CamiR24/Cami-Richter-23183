public interface ITree<K,V1,V2>{

    void insert(K key, V1 value1, V2 value2);

    V1 find1(K keyToFind);

    V2 find2(K keyToFind);

    TreeNode<K,V1,V2> getFirstNode();

    boolean isEmpty();

    void InOrderWalk(IWalk<V1,V2> walk);

}