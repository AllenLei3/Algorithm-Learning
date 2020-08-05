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
     */
    public void insertNode(int data) {
        if (count == capacity) {
            return;
        }
        // 将新元素放到数组尾部
        array[count++] = data;
        int index = count;

        // 比较该节点与父节点的大小，进行交换
        while (index / 2 > 0 && array[index] > array[index / 2]) {
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
        // 交换堆顶和堆尾元素
        array[1] = array[count];
        count--;

        // 从上往下比较父节点与子节点的大小，进行交换
        int i = 1;
        while (true) {
            int maxIndex = i;
            // 先比较左子节点，如果左子节点大于父节点，则标记maxIndex为左子节点索引
            if (i * 2 <= count && array[i] < array[i * 2]) {
                maxIndex = i * 2;
            }
            // 再比较右子节点，判断左右子节点哪个大
            if (i * 2 + 1 <= count && array[maxIndex] < array[i * 2 + 1]) {
                maxIndex = i * 2 + 1;
            }
            // 只有一个节点
            if (maxIndex == i) {
                break;
            }
            int temp = array[i];
            array[i] = array[maxIndex];
            array[maxIndex] = temp;
            i = maxIndex;
        }
    }
}
