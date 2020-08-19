package dynamic;

/**
 * 矩阵最短路径问题（递归+备忘录的方式实现）
 *
 * @author xulei
 * @date 2020/8/18 2:53 下午
 */
public class MatrixMinDistV2 {

    /** 4*4的矩阵 */
    private static final int[][] matrix = new int[4][4];
    /** 备忘录 */
    private static final int[][] mem = new int[4][4];

    static {
        matrix[0] = new int[]{1, 3, 5, 9};
        matrix[1] = new int[]{2, 1, 3, 4};
        matrix[2] = new int[]{5, 2, 6, 7};
        matrix[3] = new int[]{6, 8, 4, 3};
    }

    /**
     * 求矩阵的最短路径(递归+备忘录的方式实现)
     *
     * @param i 终点所在行
     * @param j 终点所在列
     */
    public static int minDist(int i, int j) {
        if (i == 0 && j == 0) {
            return matrix[0][0];
        }
        // 如果备忘录里有数据，直接返回备忘录数据
        if (mem[i][j] > 0) {
            return mem[i][j];
        }
        // 从后一个节点倒推前一个节点，即状态转移方程
        int minLeft = Integer.MAX_VALUE;
        if (j - 1 >= 0) {
            minLeft = minDist(i, j - 1);
        }
        int minUp = Integer.MAX_VALUE;
        if (i - 1 >= 0) {
            minUp = minDist(i - 1, j);
        }
        int currentDist = matrix[i][j] + Math.min(minLeft, minUp);
        mem[i][j] = currentDist;
        return currentDist;
    }

    public static void main(String[] args) {
        System.out.println(MatrixMinDistV2.minDist(3, 3));
    }
}
