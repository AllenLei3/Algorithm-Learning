package sort;

/**
 * 快速排序
 *
 * @author xulei
 * @date 2020/7/26 10:59 下午
 */
public class QuickSort {

    public static int[] sort(int[] array) {
        quickSortInternally(array, 0, array.length - 1);
        return array;
    }

    private static void quickSortInternally(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }
        // 获取分区点
        int pivot = partition(array, start, end);
        quickSortInternally(array, start, pivot - 1);
        quickSortInternally(array, pivot + 1, end);
    }

    /**
     * 分区
     */
    private static int partition(int[] array, int start, int end) {
        // 取最后一个元素作为分区点，这样便于区分已处理区间和未处理区间
        int pivot = array[end];
        // i表示已处理区间
        int i = start;
        // j表示未处理区间，循环取未处理区间中的元素与分区点比较，看是否要放入已处理区间
        for (int j = start; j < end; j++) {
            if (array[j] < pivot) {
                // 将未处理区间元素与已处理区间最后一个元素交换
                if (i != j) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
                // 已处理区间里的元素数量加一
                i++;
            }
        }
        // 交换分区点位置
        int temp = array[i];
        array[i] = array[end];
        array[end] = temp;
        return i;
    }

    public static void main(String[] args) {
        int[] array = sort(new int[]{3,6,5,8,4,7,2,1});
        for (int i : array) {
            System.out.println(i);
        }
    }
}
