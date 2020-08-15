package tree;

/**
 * 堆。基于数组实现
 *
 * @author xulei
 * @date 2020/8/5 6:45 下午
 */
public class Heap {

    // 通过数组来存储堆，最节省内存
    private final int[] array;
    // 数组最大容量(假设没有扩缩容)
    private final int capacity;
    // 当前已存储的元素个数
    private int count;

    public Heap(int capacity) {
        this.array = new int[capacity];
        this.capacity = capacity;
        this.count = 0;
    }

    /**
     * 插入元素，从下往上的堆化方法
     * 因为堆从array[1]开始，所以可以直接用count表示堆尾下标
     */
    public void insertNode(int data) {
        if (count >= capacity) {
            return;
        }
        // 堆元素数量加一，用来存放新元素
        count++;
        array[count] = data;
        int index = count;

        // 比较该节点与父节点的大小，这里大于0是因为array[0]不存储堆元素，(除以2可通过右移1位来实现)
        while (index / 2 > 0 && array[index] > array[index / 2]) {
            // 进行交换
            int temp = array[index];
            array[index] = array[index / 2];
            array[index / 2] = temp;
            index = index / 2;
        }
    }

    /**
     * 删除堆顶元素，从上往下的堆化方法
     */
    public void removeTopNode() {
        if (count == 0) {
            return;
        }
        // 交换堆顶和堆尾元素，count减1，相当于删除数组最后一个元素，这样不会出现数组空洞
        array[1] = array[count];
        count--;

        // 从堆顶节点开始往下比较与子节点的大小，堆顶下标从1开始
        int i = 1;
        while (true) {
            int maxIndex = i;
            // 先比较左子节点，如果左子节点大于父节点，则标记maxIndex为左子节点索引
            if (i * 2 <= count && array[i] < array[i * 2]) {
                maxIndex = i * 2;
            }
            // 再比较右子节点，判断左右子节点哪个大，然后更新maxIndex
            if (i * 2 + 1 <= count && array[maxIndex] < array[i * 2 + 1]) {
                maxIndex = i * 2 + 1;
            }
            // 只有一个节点
            if (maxIndex == i) {
                break;
            }
            // 将父节点与左右子节点中最大的节点进行交换
            int temp = array[i];
            array[i] = array[maxIndex];
            array[maxIndex] = temp;
            // 更新父节点
            i = maxIndex;
        }
    }

    public static void main(String[] args) {
        Heap heap = new Heap(10);
        heap.insertNode(34);
        heap.insertNode(26);
        heap.insertNode(13);
        heap.insertNode(47);
        heap.insertNode(85);
        heap.insertNode(96);
        heap.insertNode(57);

        // 查看堆顶元素
        System.out.println(heap.array[1]);
        heap.removeTopNode();
        System.out.println(heap.array[1]);
    }
}
