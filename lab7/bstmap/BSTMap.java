package bstmap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{

    private class BSTNode{
        private K key;
        private V value;
        private BSTNode left, right;

        private BSTNode(K key, V value){
            this.key = key;
            this.value = value;
            this.left = this.right = null;
        }
    }
    private BSTNode root;
    private int size;

    /** Removes all of the mappings from this map. */
    @Override
    public void clear(){
        root = null;
        size = 0;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key){
        return containsKeyHelper(root, key);
    }

    private boolean containsKeyHelper(BSTNode node, K key) {
        if (node == null) return false;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) return containsKeyHelper(node.left, key);
        if (cmp > 0) return containsKeyHelper(node.right, key);
        return true; // 找到了
    }


    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key){
        return getHelper(root, key);
    }
    private V getHelper(BSTNode node, K key){
        if (node == null){
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0){
            return getHelper(node.left, key);
        }
        else if (cmp > 0){
            return getHelper(node.right, key);
        }
        else{
            return node.value;
        }
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size(){
        return size;
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value){
        root = putHelper(root, key, value);
    }
    private BSTNode putHelper(BSTNode root, K key, V value){
        if (root == null){
            size++;
            return new BSTNode(key, value);
        }
        int cmp = key.compareTo(root.key);
        if (cmp < 0){
            root.left = putHelper(root.left, key, value);
        }
        else if (cmp > 0){
            root.right = putHelper(root.right, key, value);
        }
        else{
            root.value = value;
        }
        return root;

    }
    @Override
    public V remove(K key) {
        V removed = get(key);
        if (removed != null){
            root = removeHelper(root, key);
            size--;
        }
        return removed;
    }
    // remove(K key) → removeHelper(...)
    @Override
    public V remove(K key, V value) {
        if (containsKey(key) && get(key).equals(value)) {
            return remove(key);
        }
        return null;
    }


    private BSTNode removeHelper(BSTNode node, K key){
        if (node == null){
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0){
            node.left = removeHelper(node.left, key);
        }
        else if (cmp > 0){
            node.right = removeHelper(node.right, key);
        }
        else{
            //找到了 key，判断它有几个子节点
            if (node.left == null) return node.right; // 只有右子树或是叶子
            if (node.right == null) return node.left; // 只有左子树

            //当节点 既有左子树也有右子树 时，才应该走 findMin() 逻辑
            //node with two children,找出当前节点 右子树中最小的节点
            BSTNode successor = findMin(node.right);
            node.key = successor.key;
            node.value = successor.value;//copy the value of the children
            node.right = removeHelper(node.right, successor.key);//我们进入以right为根的子树，递归查找并删除key。
            }
        return node;


    }

    private BSTNode findMin(BSTNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;

    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }
    @Override
    public Set<K> keySet() {
        Set<K> result = new java.util.HashSet<>();
        collectKeys(root, result);
        return result;
    }

    private void collectKeys(BSTNode node, Set<K> set) {
        if (node == null) return;
        collectKeys(node.left, set);
        set.add(node.key);
        collectKeys(node.right, set);
    }

        public void printInOrder(){
        printHelper(root);
    }
    private void printHelper(BSTNode node) {
        if (node == null){
            return;
        }
        printHelper(node.left);//从大到小，要先从左边看，再到current node，最后去右边
        System.out.println(node.key);
        printHelper(node.right);
    }
}

