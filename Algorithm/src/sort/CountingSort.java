package sort;

/**
 * 计数排序，假设数组中存储的都是非负整数
 *
 * @author xulei
 * @date 2020/7/28 9:44 下午
 */
public class CountingSort {

    public static int[] sort(int[] array) {
        if (array.length <= 1) {
            return array;
        }
        // 查找最大值，做为桶的数量
        int max = array[0];
        for (int i = 1; i < array.length; ++i) {
            if (max < array[i]) {
                max = array[i];
            }
        }
        // 申请一个计数数组，存储每个桶的元素数量
        int[] bucketCount = new int[max + 1];
        // 计算每个元素的个数，放入c中
        for (int i = 0; i < array.length; ++i) {
            bucketCount[array[i]]++;
        }
        // 依次累加
        for (int i = 1; i < max + 1; ++i) {
            bucketCount[i] = bucketCount[i-1] + bucketCount[i];
        }
        int[] result = new int[array.length];
        for (int i = array.length - 1; i >= 0; --i) {
            // a[i]为分数，c[a[i]]为小于等于这个分数的数量
            int index = bucketCount[array[i]]-1;
            result[index] = array[i];
            // 小于等于这个分数的数量减1
            bucketCount[array[i]]--;
        }
        // 将结果拷贝回a数组
        for (int i = 0; i < array.length; ++i) {
            array[i] = result[i];
        }
        return array;
    }

    public static void main(String[] args) {
        int[] array = sort(new int[]{3,6,5,8,4,7,2,1});
        for (int i : array) {
            System.out.println(i);
        }
    }
}
