package structure;

/**
 * 单链表的实现
 *
 * @author xulei
 * @date 2020/7/26 3:30 上午
 */
public class SingleLinkedList {

    private Node head = null;

    //------------------ 插入 ----------------------

    public void insertHead(int value) {
        Node node = new Node(value, null);
        if (head == null) {
            head = node;
        } else {
            node.next = head;
            head = node;
        }
    }

    public void insertTail(int value) {
        Node node = new Node(value, null);
        if (head == null) {
            head = node;
        } else {
            Node q = head;
            while (q.next != null) {
                q = q.next;
            }
            q.next = node;
        }
    }

    public void insertAfter(Node node, int value) {
        Node newNode = new Node(value, null);
        if (node == null) {
            return;
        }
        newNode.next = node.next;
        node.next = newNode;
    }

    //------------------ 删除 ----------------------

    public void deleteByNode(Node node) {
        if (node == null || head == null) {
            return;
        }
        if (node == head) {
            head = head.next;
            return;
        }
        Node q = head;
        while (q != null && q.next != node) {
            q = q.next;
        }
        if (q == null) {
            return;
        }
        q.next = q.next.next;
    }

    public static class Node {

        private int value;
        private Node next;

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

}
