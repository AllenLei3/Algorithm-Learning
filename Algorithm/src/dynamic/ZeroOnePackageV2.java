package dynamic;

/**
 * 动态规划求解背包问题，优化二维数组的空间消耗
 *
 * @author xulei
 * @date 2020/8/17 5:13 下午
 */
public class ZeroOnePackageV2 {

    /** 每个物品的重量 */
    private final int[] weight;

    /** 背包可承载的总容量 */
    private final int w;

    /** 状态数组，这里采用一维数组，相当于合并了二维数组，因为我们只需要取最大值 */
    private final boolean[] states;

    public ZeroOnePackageV2(int packageCapacity, int[] itemWeight) {
        this.weight = itemWeight;
        this.w = packageCapacity;
        this.states = new boolean[this.w + 1];
    }

    /**
     * 装包
     */
    public int putItem2Package() {
        states[0] = true;
        if (weight[0] <= w) {
            states[weight[0]] = true;
        }
        // 动态规划
        for (int i = 1; i < weight.length; i++) {
            // j从大到小处理，避免for循环重复计算
            for (int j = w - weight[i]; j >= 0; j--) {
                if (states[j]) {
                    states[j + weight[i]] = true;
                }
            }
        }
        // 逆序遍历获取最靠右的，即为最大值
        for (int i = w; i >= 0; i--) {
            if (states[i]) {
                return i;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        ZeroOnePackageV2 p = new ZeroOnePackageV2(100, new int[]{20, 20, 20, 20, 21});
        System.out.println(p.putItem2Package());
    }
}
