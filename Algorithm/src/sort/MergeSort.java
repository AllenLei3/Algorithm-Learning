package sort;

/**
 * 归并排序
 *
 * @author xulei
 * @date 2020/7/25 3:56 下午
 */
public class MergeSort {

    public static int[] sort(int[] array) {
        mergeSortInternally(array, 0, array.length - 1);
        return array;
    }

    /**
     * 分解
     */
    private static void mergeSortInternally(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }
        // 取中间位置,防止（start + end）的和超过int类型最大值
        int middle = start + (end - start) / 2;
        mergeSortInternally(array, start, middle);
        mergeSortInternally(array, middle + 1, end);
        merge(array, start, middle, end);
    }

    /**
     * 合并
     */
    private static void merge(int[] array, int start, int middle, int end) {
        int i = start;
        int j = middle + 1;
        int k = 0;
        int[] temp = new int[end - start + 1];
        while (i <= middle && j <= end) {
            if (array[i] <= array[j]) {
                temp[k++] = array[i++];
            } else {
                temp[k++] = array[j++];
            }
        }
        // 判断哪个子数组中有剩余的数据
        int s = i;
        int e = middle;
        if (j <= end) {
            s = j;
            e = end;
        }
        // 将剩余的数据拷贝到临时数组temp
        while (s <= e) {
            temp[k++] = array[s++];
        }
        // 将temp数组中的数据拷贝回array
        for (i = 0; i <= end - start; i++) {
            array[start + i] = temp[i];
        }
    }

    public static void main(String[] args) {
        int[] array = sort(new int[]{3,6,5,8,4,7,2,1});
        for (int i : array) {
            System.out.println(i);
        }
    }
}
