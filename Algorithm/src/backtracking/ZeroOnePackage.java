package backtracking;

/**
 * 回溯算法典型应用
 *
 * 0-1背包问题，有n个物品，背包总重量为wKG，如何选择物品让背包可承载的总重量最大？
 *
 * @author xulei
 * @date 2020/8/17 2:07 下午
 */
public class ZeroOnePackage {

    /** 每个物品的重量 */
    private final int[] itemWeight;

    /** 背包可承载的总容量 */
    private final int packageCapacity;

    /** 选择装入到背包中的物品的总容量 */
    private int maxSelectCapacity = 0;

    public ZeroOnePackage(int packageCapacity, int[] itemWeight) {
        this.itemWeight = itemWeight;
        this.packageCapacity = packageCapacity;
    }

    /**
     * 把物品放入背包中
     */
    public void putItem2Package() {
        put(0, 0);
    }

    /**
     * 装包
     *
     * @param index 当前要装载物品的索引
     * @param currentWeight 当前背包已装载的重量
     */
    private void put(int index, int currentWeight) {
        // 如果背包容量装满了或者物品装完了
        if (currentWeight == packageCapacity || index == itemWeight.length) {
            // 更新当前背包装载的最大重量
            if (currentWeight > maxSelectCapacity) {
                this.maxSelectCapacity = currentWeight;
            }
            return;
        }
        // 表示不选择当前物品，直接考虑下一个，这里不能用if else来表示装不装当前物品
        put(index + 1, currentWeight);
        // 如果已经超过了背包容量，就不再装了
        if (currentWeight + itemWeight[index] <= packageCapacity) {
            // 表示选择当前物品，更新currentWeight
            put(index + 1, currentWeight + itemWeight[index]);
        }
    }

    public static void main(String[] args) {
        ZeroOnePackage p = new ZeroOnePackage(100, new int[]{3, 9, 12, 6, 2, 29, 10, 7, 5, 4});
        p.putItem2Package();
        System.out.println(p.maxSelectCapacity);
    }
}
