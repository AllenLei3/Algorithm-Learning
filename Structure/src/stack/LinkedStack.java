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
    public void push(String value) {
        Node node = new Node(value);
        if (head == null) {
            head = node;
        } else {
            node.next = head;
            head = node;
        }
    }

    public String pop() {
        if (head == null) {
            return ""; // 无数据
        }
        String value = head.value;
        head = head.next;
        return value;
    }

    public static class Node{
        private final String value;
        private Node next;

        public Node(String value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        LinkedStack stack = new LinkedStack();
        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.push("d");
        stack.push("e");

        for (int i = 0; i < 5; i++) {
            System.out.println(stack.pop());
        }
    }
}
