package array;

/**
 * @author xulei
 * @date 2020/7/26 2:32 上午
 */
public class Array {

    // 用数组存储元素
    private final int[] array;
    // 数组中实际元素的个数
    private int count;

    public Array(int capacity) {
        array = new int[capacity];
        count = 0;
    }

    public void add(int value) {
        if (count == array.length) {
            throw new RuntimeException("数组已满");
        }
        array[count++] = value;
    }

    public void add(int index, int value) {
        if (count == array.length) {
            throw new RuntimeException("数组已满");
        }
        if (index < 0 || index >= array.length) {
            throw new RuntimeException("索引无效");
        }
        if (index > count) {
            throw new RuntimeException("索引越界");
        }
        System.arraycopy(array, index, array, index + 1, count - index);
        array[index] = value;
        count++;
    }

    public void remove(int index) {
        if (index < 0 || index >= array.length) {
            throw new RuntimeException("索引无效");
        }
        if (index > count) {
            throw new RuntimeException("索引越界");
        }
        for (int i = index + 1; i < count; i++) {
            array[i - 1] = array[i];
        }
        count--;
    }

    public void printAll() {
        for (int i = 0; i < count; ++i) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Array array = new Array(10);
        array.add(1);
        array.add(0, 2);
        array.printAll();
    }

}
