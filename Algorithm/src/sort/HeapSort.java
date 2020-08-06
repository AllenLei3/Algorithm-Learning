package sort;

/**
 * 堆排序，堆元素是从数组下标1开始
 *
 * @author xulei
 * @date 2020/8/5 8:15 下午
 */
public class HeapSort {

    /**
     * 堆排序，这里为了方便展示堆化的过程，排序是从array[1]开始的
     */
    public static int[] sort(int[] array) {
        if (array.length <= 1) {
            return array;
        }
        buildHeap(array);
        int heapEndIndex = array.length - 1;
        while (heapEndIndex > 1) {
            // 交换堆顶和堆尾元素，堆尾下标减1，表示堆的大小减1
            int temp = array[1];
            array[1] = array[heapEndIndex];
            array[heapEndIndex] = temp;
            heapEndIndex--;
            // 每次都是从堆顶开始向下堆化，因为每次都是交换堆顶和堆尾元素
            heapify(array, 1, heapEndIndex + 1);
        }
        return array;
    }

    /**
     * 建堆，这里是从array[1]开始建堆的，实际排序是从array[0]开始的，这里是为了方便展示堆化的过程
     */
    private static void buildHeap(int[] array) {
        // 从非子节点处开始构建。i>0表示array[0]的元素不会构建到堆中
        for (int i = (array.length - 1) / 2; i > 0; i--) {
            heapify(array, i, array.length);
        }
    }

    /**
     * 从上到下的堆化
     *
     * @param array 堆数组
     * @param index 要进行堆化的下标索引
     * @param size 堆占用的数组大小
     */
    private static void heapify(int[] array, int index, int size) {
        while (true) {
            int maxIndex = index;
            // 下标从1开始的堆的左子节点为 2*i
            if (index * 2 < size && array[index * 2] > array[index]) {
                maxIndex = index * 2;
            }
            // 下标从1开始的堆的右子节点为 2*i+1
            if (index * 2 + 1 < size && array[index * 2 + 1] > array[maxIndex]) {
                maxIndex = index * 2 + 1;
            }
            if (maxIndex == index) {
                break;
            }
            int temp = array[index];
            array[index] = array[maxIndex];
            array[maxIndex] = temp;
            index = maxIndex;
        }
    }

    public static void main(String[] args) {
        int[] array = sort(new int[]{3, 6, 5, 8, 4, 7, 2, 1});
        for (int i : array) {
            System.out.println(i);
        }
    }
}
