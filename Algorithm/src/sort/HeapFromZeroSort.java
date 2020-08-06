package sort;

/**
 * 堆排序，堆元素是从数组下标0开始
 *
 * @author xulei
 * @date 2020/8/5 8:15 下午
 */
public class HeapFromZeroSort {

    public static int[] sort(int[] array) {
        if (array.length <= 1) {
            return array;
        }
        buildHeap(array);
        int heapEndIndex = array.length - 1;
        while (heapEndIndex > 0) {
            int temp = array[0];
            array[0] = array[heapEndIndex];
            array[heapEndIndex] = temp;
            heapEndIndex--;
            // 堆顶从0开始
            heapify(array, 0, heapEndIndex + 1);
        }
        return array;
    }

    /**
     * 从非子节点开始，从后向前建堆，直到array[0]
     */
    private static void buildHeap(int[] array) {
        for (int i = (array.length - 1) / 2; i >= 0; i--) {
            heapify(array, i, array.length);
        }
    }

    /**
     * 从上到下的堆化
     */
    private static void heapify(int[] array, int index, int size) {
        while (true) {
            int maxIndex = index;
            // 下标从0开始的堆的左子节点为 2*i+1
            if (index * 2 + 1 < size && array[index * 2 + 1] > array[index]) {
                maxIndex = index * 2 + 1;
            }
            // 下标从0开始的堆的右子节点为 2*i+1
            if (index * 2 + 2 < size && array[index * 2 + 2] > array[maxIndex]) {
                maxIndex = index * 2 + 2;
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
        int[] array = sort(new int[]{3, 6, 5, 8, 4, 9, 7, 2, 1});
        for (int i : array) {
            System.out.println(i);
        }
    }
}
