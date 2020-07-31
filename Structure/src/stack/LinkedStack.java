package stack;

/**
 * 链式栈（链表实现）
 *
 * @author xulei
 * @date 2020/7/26 4:24 上午
 */
public class LinkedStack {

    private Node head;

    /**
     * 入栈（头插）
     */
    public void push(int value) {
        Node node = new Node(value);
        if (head == null) {
            head = node;
        } else {
            node.next = head;
            head = node;
        }
    }

    public int pop() {
        if (head == null) {
            return -1; // -1 表示无数据
        }
        int value = head.value;
        head = head.next;
        return value;
    }

    public static class Node{
        private final int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }
}
