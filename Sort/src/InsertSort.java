/**
 * 插入排序
 *
 * @author xulei
 * @date 2020/7/24 6:15 下午
 */
public class InsertSort {

    public static int[] sort(int[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }
        for (int i = 1; i < array.length; i++) {
            int value = array[i];
            int j = i - 1;
            // 从尾部开始插入
            for (; j >= 0; j--) {
                if (array[j] > value) {
                    // 移动数据
                    array[j + 1] = array[j];
                } else {
                    // 因为前面已经是排好序的了，直接返回
                    break;
                }
            }
            // 这里的j经过for循环以后减去了1，所以这里要加1
            array[j + 1] = value;
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
