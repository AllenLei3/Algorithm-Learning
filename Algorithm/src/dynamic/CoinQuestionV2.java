package dynamic;

/**
 * 硬币找零问题V2
 *
 * <br>
 * 假设我们有几种不同币值的硬币 v1，v2，……，vn（单位是元）。
 * 如果我们要支付 w 元，求最少需要多少个硬币。
 * </br>
 *
 * 比如，我们有 3 种不同的硬币，1 元、3 元、5 元，我们要支付 9 元，最少需要 3 个硬币（3 个 3 元的硬币）。
 *
 * @author xulei
 * @date 2020/8/19 2:19 下午
 */
public class CoinQuestionV2 {

    /** 硬币类型 */
    private static final int[] coinType = {1, 3, 5};

    /**
     * 求最少需要的硬币数量(状态转移方程法)，从后往前倒推
     *
     * @param price 价格
     */
    public static int minCount(int price) {
        if (price < 1) {
            return 0;
        }
        // count[i]代表当钱包的总价值为i时，所需要的最少硬币数
        int[] count = new int[price];
        return min(price, count);
    }

    private static int min(int price, int[] count) {
        // 如果超过了金额，则排除此选法
        if (price < 0) {
            return -1;
        }
        if (price == 0) {
            return 0;
        }
        // 满足条件则退出
        if (count[price - 1] != 0) {
            return count[price - 1];
        }
        // 求最优子结构
        int minValue = Integer.MAX_VALUE;
        for (int value : coinType) {
            int v = min(price - value, count);
            if (v >= 0 && v < minValue) {
                minValue = v + 1;
            }
        }
        count[price - 1] = minValue == Integer.MAX_VALUE ? -1 : minValue;
        return count[price - 1];
    }

    public static void main(String[] args) {
        System.out.println(CoinQuestionV2.minCount(9));
    }
}
