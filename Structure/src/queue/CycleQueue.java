package queue;

/**
 * 用数组实现的循环队列
 *
 * @author xulei
 * @date 2020/7/26 4:59 上午
 */
public class CycleQueue {

    private final String[] item;
    /** 数组大小 */
    private int n;
    /** 头指针 */
    private int head;
    /** 尾指针 */
    private int tail;

    public CycleQueue(int capacity) {
        item = new String[capacity];
        n = capacity;
    }

    /**
     * 循环队列会浪费一个数组元素空间
     */
    public boolean enqueue(String s) {
        // 队满
        if ((tail + 1) % n == head) {
            return false;
        }
        item[tail] = s;
        tail = (tail + 1) % n;
        return true;
    }

    public String dequeue() {
        if (head == tail) {
            return null;
        }
        String ret = item[head];
        head = (head + 1) % n;
        return ret;
    }

    public static void main(String[] args) {
        CycleQueue queue = new CycleQueue(5);
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        queue.enqueue("d");
        queue.dequeue();
        queue.dequeue();
        queue.enqueue("e");
        queue.enqueue("f");

        for (int i = 0; i < 4; i++) {
            System.out.println(queue.dequeue());
        }
    }
}
