package dynamic;

/**
 * 动态规划求解背包问题
 *
 * 0-1背包问题，有n个物品，背包总重量为wKG，如何选择物品让背包可承载的总重量最大？
 *
 * @author xulei
 * @date 2020/8/17 5:13 下午
 */
public class ZeroOnePackage {

    /** 每个物品的重量 */
    private final int[] weight;

    /** 背包可承载的总容量 */
    private final int w;

    /** 状态数组,一维为物品，二维为重量 */
    private final boolean[][] states;

    public ZeroOnePackage(int packageCapacity, int[] itemWeight) {
        this.weight = itemWeight;
        this.w = packageCapacity;
        this.states = new boolean[itemWeight.length][packageCapacity + 1];
    }

    /**
     * 装包
     */
    public void putItem2Package() {
        // 第一行数据特殊处理，表示将第一个物品的选择或不选择两种方式的状态都设为true
        states[0][0] = true;
        if (weight[0] <= w) {
            states[0][weight[0]] = true;
        }
        // 循环剩余物品,计算状态
        for (int i = 1; i < weight.length; i++) {
            // 不把第i个物品放入背包
            for (int j = 0; j <= w; j++) {
                // 因为不放入，所以重量不会增加，保持和上一次放入时的重量一样
                if (states[i - 1][j]) {
                    states[i][j] = true;
                }
            }
            // 把第i个物品放入背包，且保证放入后不超过最大容量
            for (int j = 0; j <= w - weight[i]; j++) {
                // 放入后，重量增加
                if (states[i - 1][j]) {
                    states[i][j + weight[i]] = true;
                }
            }
        }

        // 动态规划求解完成，取在状态范围内的最大值，逆序遍历获取最靠右的
        int value = 0;
        for (int i = w; i >= 0; i--) {
            if (states[weight.length - 1][i]) {
                value = i;
                break;
            }
        }
        System.out.println("最多可以放入背包中的容量为：" + value);

        // 打印选择装入背包中的物品，从后往前推
        System.out.print("放入背包中的商品有：");
        for (int i = weight.length - 1; i >= 1; i--) {
            if (value - weight[i] >= 0 && states[i - 1][value - weight[i]]) {
                System.out.print(weight[i] + " ");
            }
            value = value - weight[i];
        }
        // 判断第一个物品是否装入背包中
        if (value != 0) {
            System.out.print(weight[0] + " ");
        }
    }

    public static void main(String[] args) {
        ZeroOnePackage p = new ZeroOnePackage(100, new int[]{21, 22, 23, 24, 25});
        p.putItem2Package();
    }
}
