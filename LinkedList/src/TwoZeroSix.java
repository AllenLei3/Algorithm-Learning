/**
 * LeetCode 206 反转链表
 *
 * @author xulei
 * @date 2020/7/21 12:22 上午
 */
public class TwoZeroSix {

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
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }
}
