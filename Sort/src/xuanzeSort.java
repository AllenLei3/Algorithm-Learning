/**
 * @author xulei
 * @date 2020/7/24 7:12 下午
 */
public class xuanzeSort {

    public static int[] sort(int[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }
        for (int i = 0; i < array.length; i++) {
            // 先让最小值等于未排序区间中的第一个元素
            int min = i;
            // 循环获取未排序区间中的最小值
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[min]) {
                    min = j;
                }
            }
            // 将未排序区间的第一个值和最小值交换
            if (min != i) {
                int temp = array[min];
                array[min] = array[i];
                array[i] = temp;
            }
        }
        return array;
    }

    public static void main(String[] args) {
        int[] array = sort(new int[]{3,6,8,4,2,1});
        for (int i : array) {
            System.out.println(i);
        }
    }
}
