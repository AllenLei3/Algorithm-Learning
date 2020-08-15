package queue;

/**
 * 用链表实现的链式队列（非循环队列实现）
 *
 * @author xulei
 * @date 2020/7/26 4:41 上午
 */
public class LinkedQueue {

    private Node head;
    private Node tail;

    /**
     * 入队（尾插）
     */
    public void enqueue(String s) {
        Node node = new Node(s);
        if (tail == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = tail.next;
        }
    }

    /**
     * 出队（取头）
     */
    public String dequeue() {
        if (head == null) {
            return null;
        }
        String value = head.value;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        return value;
    }

    public static class Node {
        private String value;
        private Node next;

        public Node(String value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        LinkedQueue queue = new LinkedQueue();
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        queue.enqueue("d");
        queue.enqueue("e");

        for (int i = 0; i < 5; i++) {
            System.out.println(queue.dequeue());
        }
    }
}
