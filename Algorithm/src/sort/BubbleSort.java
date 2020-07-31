package sort;

/**
 * 冒泡排序
 *
 * @author xulei
 * @date 2020/7/24 4:52 下午
 */
public class BubbleSort {

    public static int[] sort(int[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }
        for (int i = 0; i < array.length; i++) {
            // 排序提前结束的标志
            boolean flag = false;
            for (int j = 0; j < array.length - i -1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
        return array;
    }

    /**
     * 冒泡排序优化版
     */
    public static int[] sortTwo(int[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }
        int lastExchange = 0;
        int sortBorder = array.length - 1;
        for (int i = 0; i < array.length; i++) {
            // 排序提前结束的标志
            boolean flag = false;
            for (int j = 0; j < sortBorder; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    flag = true;
                    // 更新最后一次交换的位置
                    lastExchange = j;
                }
            }
            sortBorder = lastExchange;
            if (!flag) {
                break;
            }
        }
        return array;
    }


    public static void main(String[] args) {
        int[] array = sortTwo(new int[]{3,6,8,4,2,1});
        for (int i : array) {
            System.out.println(i);
        }
    }
}
