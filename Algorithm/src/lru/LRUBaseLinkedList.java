package lru;

/**
 * 基于单链表实现的LRU缓存淘汰算法
 * 1. 如果此数据之前已经被缓存在链表中了，我们遍历得到这个数据对应的结点，并将其从原来的位置删除，然后再插入到链表的头部
 * 2. 如果此数据没有在缓存链表中
 *      2.1 如果此时缓存未满，则将此结点直接插入到链表的头部
 *      2.2 如果此时缓存已满，则链表尾结点删除，将新的数据结点插入链表的头部
 *
 * 查找、插入、删除的时间复杂度都是 O(n)
 *
 * @author xulei
 * @date 2020/8/2 2:22 上午
 */
public class LRUBaseLinkedList<T> {

    /** 默认链表容量 */
    private final static Integer DEFAULT_CAPACITY = 10;
    /** 头结点 */
    private final SNode<T> headNode;
    /** 链表长度 */
    private Integer length;
    /** 链表容量,即缓存容量 */
    private final Integer capacity;

    public LRUBaseLinkedList() {
        this(DEFAULT_CAPACITY);
    }

    public LRUBaseLinkedList(Integer capacity) {
        this.headNode = new SNode<>();
        this.capacity = capacity;
        this.length = 0;
    }

    /**
     * 添加元素
     */
    public void add(T data) {
        SNode<T> preNode = null;
        SNode<T> node = headNode;

        // 查找给定元素的前置结点
        while (node.next != null) {
            if (data.equals(node.next.element)) {
                preNode = node;
            }
            node = node.next;
        }

        // 如果之前存在该元素
        if (preNode != null) {
            // 删除原数据
            SNode<T> temp = preNode.next;
            preNode.next = temp.next;
            length--;
        } else {
            // 如果之前不存在，且超过了链表容量，则删除尾结点
            if (length >= this.capacity) {
                SNode<T> h = headNode;
                if (h.next == null) {
                    return;
                }
                // 遍历查找尾节点
                while (h.next.next != null) {
                    h = h.next;
                }
                h.next = null;
                length--;
            }
        }
        // 插入到链表的头部
        SNode<T> temp = headNode.next;
        headNode.next = new SNode<>(data, temp);
        length++;
    }

    public static class SNode<T> {
        private T element;
        private SNode<T> next;

        public SNode(T element, SNode<T> next) {
            this.element = element;
            this.next = next;
        }

        public SNode() {
            this.next = null;
        }
    }
}
