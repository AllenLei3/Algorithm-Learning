package backtracking;

/**
 * 0-1背包问题升级版，除了重量外，为每个物品增加价值属性
 * 求在满足背包最大重量限制的前提下，背包中可装入物品的总价值最大
 *
 * @author xulei
 * @date 2020/8/17 3:07 下午
 */
public class ZeroOnePackageV3 {

    /** 每个物品的重量 */
    private final int[] itemWeight;

    /** 每个物品的价值 */
    private final int[] itemValue;

    /** 背包可承载的总容量 */
    private final int packageCapacity;

    /** 选择装入到背包中的物品的总价值 */
    private int maxSelectValue = 0;

    public ZeroOnePackageV3(int packageCapacity, int[] itemWeight, int[] itemValue) {
        this.itemWeight = itemWeight;
        this.itemValue = itemValue;
        this.packageCapacity = packageCapacity;
    }

    /**
     * 装包
     *
     * @param i 当前要装载物品的索引
     * @param currentWeight 当前背包已装载的重量
     * @param currentValue 当前背包已装载的物品的总价值
     */
    public void put(int i, int currentWeight, int currentValue) {
        // 如果装满了或者装完了
        if (currentWeight == packageCapacity || i == itemWeight.length) {
            if (currentValue > maxSelectValue) {
                this.maxSelectValue = currentValue;
            }
            return;
        }
        // 不选择当前物品
        put(i + 1, currentWeight, currentValue);
        if (currentWeight + itemWeight[i] <= packageCapacity) {
            // 选择当前物品
            put(i + 1, currentWeight + itemWeight[i], currentValue + itemValue[i]);
        }
    }

    public static void main(String[] args) {
        int[] weight = new int[]{3, 9, 12, 6, 2, 29, 10, 7, 5, 4};
        int[] value = new int[]{3, 9, 12, 6, 2, 29, 10, 7, 5, 4};
        ZeroOnePackageV3 p = new ZeroOnePackageV3(100, weight, value);
        p.put(0, 0, 0);
        System.out.println(p.maxSelectValue);
    }
}
