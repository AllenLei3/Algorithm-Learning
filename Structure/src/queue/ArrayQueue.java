package queue;

/**
 * 用数组实现的顺序队列（非循环队列实现）
 *
 * @author xulei
 * @date 2020/7/24 1:26 上午
 */
public class ArrayQueue {

    private final String[] item;
    /** 头指针 */
    private int head;
    /** 尾指针 */
    private int tail;

    public ArrayQueue(int capacity) {
        item = new String[capacity];
    }

    /**
     * 入队到队尾
     */
    public boolean enqueue(String s) {
        if (tail == item.length) {
            return false;
        }
        item[tail] = s;
        ++tail;
        return true;
    }

    /**
     * 从队头出队
     */
    public String dequeue() {
        // 如果队头和队尾重合，则队列中没有元素
        if (head == tail) {
            return null;
        }
        String value = item[head];
        ++head;
        return value;
    }

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(10);
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");
        queue.enqueue("4");
        queue.enqueue("5");

        for (int i = 0; i < 5; i++) {
            System.out.println(queue.dequeue());
        }
    }
}
