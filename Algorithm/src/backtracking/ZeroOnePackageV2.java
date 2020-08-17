package backtracking;

/**
 * 0-1背包问题优化，引入备忘录，避免重复计算
 *
 * @author xulei
 * @date 2020/8/17 3:07 下午
 */
public class ZeroOnePackageV2 {

    /** 每个物品的重量 */
    private final int[] itemWeight;

    /** 背包可承载的总容量 */
    private final int packageCapacity;

    /** 选择装入到背包中的物品的总容量 */
    private int maxSelectCapacity = 0;

    /** 备忘录,一维为物品，二维为重量 */
    private final boolean[][] mem;

    public ZeroOnePackageV2(int packageCapacity, int[] itemWeight) {
        this.itemWeight = itemWeight;
        this.packageCapacity = packageCapacity;
        this.mem = new boolean[itemWeight.length][packageCapacity + 1];
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
        if (currentWeight == packageCapacity || index == itemWeight.length) {
            if (currentWeight > maxSelectCapacity) {
                this.maxSelectCapacity = currentWeight;
            }
            return;
        }
        // 通过备忘录记录重复状态,相同节点只会重复执行一次
        if (mem[index][currentWeight]) {
            return;
        }
        mem[index][currentWeight] = true;
        // 不选择当前物品
        put(index + 1, currentWeight);
        if (currentWeight + itemWeight[index] <= packageCapacity) {
            // 选择当前物品
            put(index + 1, currentWeight + itemWeight[index]);
        }
    }

    public static void main(String[] args) {
        ZeroOnePackageV2 p = new ZeroOnePackageV2(100, new int[]{3, 9, 12, 6, 2, 29, 10, 7, 5, 4});
        p.putItem2Package();
        System.out.println(p.maxSelectCapacity);
    }
}
