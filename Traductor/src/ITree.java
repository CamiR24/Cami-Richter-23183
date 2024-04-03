public interface ITree<K, V>{

    void insert(K key, V value);

    V find(K keyToFind);

    boolean isEmpty();

    void InOrderWalk(IWalk<V> walk);

}