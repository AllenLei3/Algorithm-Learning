/**
 * 计数排序，假设数组中存储的都是非负整数
 *
 * @author xulei
 * @date 2020/7/28 9:44 下午
 */
public class CountingSort {

    public static int[] sort(int[] a) {
        if (a.length <= 1) {
            return a;
        }
        // 查找最大值，做为桶的数量
        int max = a[0];
        for (int i = 1; i < a.length; ++i) {
            if (max < a[i]) {
                max = a[i];
            }
        }
        // 申请一个计数数组c，下标大小[0,max]
        int[] c = new int[max + 1];
        // 计算每个元素的个数，放入c中
        for (int i = 0; i < a.length; ++i) {
            c[a[i]]++;
        }
        // 依次累加
        for (int i = 1; i < max + 1; ++i) {
            c[i] = c[i-1] + c[i];
        }
        int[] r = new int[a.length];
        for (int i = a.length - 1; i >= 0; --i) {
            // a[i]为分数，c[a[i]]为小于等于这个分数的数量
            int index = c[a[i]]-1;
            r[index] = a[i];
            // 小于等于这个分数的数量减1
            c[a[i]]--;
        }
        // 将结果拷贝回a数组
        for (int i = 0; i < a.length; ++i) {
            a[i] = r[i];
        }
        return a;
    }

    public static void main(String[] args) {
        int[] array = sort(new int[]{3,6,5,8,4,7,2,1});
        for (int i : array) {
            System.out.println(i);
        }
    }
}
