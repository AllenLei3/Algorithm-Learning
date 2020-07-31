package list;

/**
 * LC 206 反转链表
 *
 * @author xulei
 * @date 2020/7/21 12:22 上午
 */
public class ReverseLinkedList {

    public Node reverseList(Node head) {
        Node pre = null;
        Node cur = head;
        while (cur != null) {
            Node temp = cur.next;
            // 将next指针指向前一个元素
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    private static class Node {
        int value;
        Node next = null;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Node list1 = new Node(1);
        Node list2 = new Node(2);
        Node list3 = new Node(3);
        list1.next = list2;
        list2.next = list3;

        ReverseLinkedList revers = new ReverseLinkedList();
        Node node = revers.reverseList(list1);
        while (node != null) {
            System.out.println(node.value);
            node = node.next;
        }
    }
}
