package lru;

import java.util.HashMap;

/**
 * 基于双向链表+哈希表实现的LRU缓存淘汰算法
 *
 * 双向链表和哈希表各维护一份数据，哈希表用来快速获取节点，双向链表用来快速插入、删除和维护顺序关系
 *
 * @author xulei
 * @date 2020/8/2 2:08 上午
 */
public class LRUBaseHashTable<K, V> {

    /** 默认链表容量 */
    private final static Integer DEFAULT_CAPACITY = 10;

    /** 头结点 */
    private final DNode<K, V> headNode = new DNode<>();
    /** 尾节点 */
    private final DNode<K, V> tailNode = new DNode<>();

    /** 链表长度 */
    private Integer length;
    /** 链表容量 */
    private final Integer capacity;

    /** 散列表存储key */
    private final HashMap<K, DNode<K, V>> table = new HashMap<>();

    public LRUBaseHashTable() {
        this(DEFAULT_CAPACITY);
    }

    public LRUBaseHashTable(int capacity) {
        this.length = 0;
        this.capacity = capacity;
        headNode.next = tailNode;
        tailNode.prev = headNode;
    }

    /**
     * 新增
     *
     * 1. 如果已经在其中，需要将其移动到双向链表的尾部
     * 2. 如果不在其中，还要看缓存有没有满
     *      2.1 如果满了，则将双向链表头部的结点删除，然后再将数据放到链表的尾部
     *      2.2 如果没有满，就直接将数据放到链表的尾部。
     */
    public void add(K key, V value) {
        DNode<K, V> node = table.get(key);
        if (node == null) {
            DNode<K, V> newNode = new DNode<>(key, value);
            table.put(key, newNode);
            addNodeToHead(newNode);

            if (++length > capacity) {
                DNode<K, V> n = tailNode.prev;
                removeNode(n);
                table.remove(n.key);
                length--;
            }
        } else {
            node.value = value;
            removeNode(node);
            addNodeToHead(node);
        }
    }

    /**
     * 获取节点数据
     *
     * 通过散列表，我们可以很快地在缓存中找到一个数据。当找到数据后，我们还需要将它移动到双向链表的尾部
     */
    public V get(K key) {
        DNode<K, V> node = table.get(key);
        if (node == null) {
            return null;
        }
        removeNode(node);
        addNodeToHead(node);
        return node.value;
    }

    /**
     * 移除节点数据
     *
     * 找到数据所在的结点，然后将结点删除
     * 因为我们的链表是双向链表，可以通过前驱指针获取前驱结点，所以在双向链表中，删除结点只需要O(1)的时间复杂度
     */
    public void remove(K key) {
        DNode<K, V> node = table.get(key);
        if (node == null) {
            return;
        }
        removeNode(node);
        length--;
        table.remove(node.key);
    }

    private void addNodeToHead(DNode<K, V> newNode) {
        newNode.next = headNode.next;
        newNode.prev = headNode;
        headNode.next.prev = newNode;
        headNode.next = newNode;
    }

    private void removeNode(DNode<K, V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    /**
     * 双向链表节点
     */
    public static class DNode<K, V> {

        private K key;
        private V value;
        private DNode<K, V> prev;
        private DNode<K, V> next;

        DNode() {}

        DNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
