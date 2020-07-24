/**
 * 冒泡排序
 * @author xulei
 * @date 2020/7/24 4:52 下午
 */
public class maopaoSort {

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


    public static void main(String[] args) {
        int[] array = sort(new int[]{3,6,8,4,2,1});
        for (int i : array) {
            System.out.println(i);
        }
    }
}
