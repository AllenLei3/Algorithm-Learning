package dynamic;

/**
 * 硬币找零问题
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
public class CoinQuestion {

    /** 硬币类型 */
    private static int[] coinType = {1, 3, 5};

    /**
     * 求最少需要的硬币数量(状态转移表法)
     *
     * @param price 价格
     */
    public static int minCount(int price) {
        if (price == 1 || price == 3 || price == 5) {
            return 1;
        }
        // 一维表示硬币个数，二维表示金额
        boolean[][] states = new boolean[price][price + 1];
        // 初始化第一行
        if (price >= 1) {
            states[0][1] = true;
        }
        if (price >= 3) {
            states[0][3] = true;
        }
        if (price >= 5) {
            states[0][5] = true;
        }
        // 循环填充
        for (int i = 1; i < price; i++) {
            for (int j = 1; j <= price; j++) {
                // 根据上一次选择的情况来累加金额
                if (states[i - 1][j]) {
                    if (j + 1 <= price) {
                        states[i][j + 1] = true;
                    }
                    if (j + 3 <= price) {
                        states[i][j + 3] = true;
                    }
                    if (j + 5 <= price) {
                        states[i][j + 5] = true;
                    }
                    // 正好满足金额
                    if (states[i][price]) {
                        return i + 1;
                    }
                }
            }
        }
        return price;
    }

    public static void main(String[] args) {
        System.out.println(CoinQuestion.minCount(5));
    }
}
