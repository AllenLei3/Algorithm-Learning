package dynamic;

/**
 * 动态规划求解背包问题的升级版，引入物品价值的概念
 *
 * 求在满足背包最大重量限制的前提下，背包中可装入物品的总价值最大
 *
 * @author xulei
 * @date 2020/8/17 5:13 下午
 */
public class ZeroOnePackageV3 {

    /** 每个物品的重量 */
    private final int[] weight;

    /** 每个物品的价值 */
    private final int[] value;

    /** 背包可承载的总容量 */
    private final int w;

    /** 状态数组，一维是物品，二维是重量，数组值是相同重量下的最高价 */
    private final int[][] states;

    public ZeroOnePackageV3(int w, int[] weight, int[] value) {
        this.weight = weight;
        this.value = value;
        this.w = w;
        this.states = new int[weight.length][this.w + 1];
        // 初始化states
        for (int i = 0; i < weight.length; i++) {
            for (int j = 0; j < this.w + 1; j++) {
                states[i][j] = -1;
            }
        }
    }

    /**
     * 装包
     */
    public int putItem2Package() {
        states[0][0] = 0;
        if (weight[0] <= w) {
            states[0][weight[0]] = value[0];
        }
        // 动态规划，状态转移
        for (int i = 1; i < weight.length; i++) {
            // 不把第i个物品放入背包，此时价值不变
            for (int j = 0; j <= w; j++) {
                if (states[i - 1][j] > 0) {
                    states[i][j] = states[i - 1][j];
                }
            }
            // 把第i个物品放入背包，且保证放入后不超过最大容量
            for (int j = 0; j <= w - weight[i]; j++) {
                if (states[i - 1][j] >= 0) {
                    // 获取同重量下价值最高的
                    int v = states[i - 1][j] + value[i];
                    if (v > states[i][j + weight[i]]) {
                        states[i][j + weight[i]] = v;
                    }
                }
            }
        }

        // 找出最大值
        int maxValue = 0;
        for (int i = 0; i <= w; i++) {
            if (states[weight.length - 1][i] > maxValue) {
                maxValue = states[weight.length - 1][i];
            }
        }
        return maxValue;
    }

    public static void main(String[] args) {
        int[] weight = new int[]{20, 20, 20, 20, 21};
        int[] value = new int[]{20, 20, 20, 20, 21};
        ZeroOnePackageV3 p = new ZeroOnePackageV3(100, weight, value);
        System.out.println(p.putItem2Package());
    }
}
