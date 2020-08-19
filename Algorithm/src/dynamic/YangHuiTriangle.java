package dynamic;

/**
 * 杨辉三角
 *
 * 假设你站在第一层，往下移动，我们把移动到最底层所经过的所有数字之和，定义为路径的长度
 * 求出从最高层移动到最底层的最短路径长度。
 *
 * @author xulei
 * @date 2020/8/18 8:49 下午
 */
public class YangHuiTriangle {

    /** 杨辉三角 */
    private static final int[][] triangle = {{5},{7, 8},{2, 3, 4},{4, 9, 6, 1},{2, 7, 9, 4, 5}};
    /** 状态数组 */
    private static final int[][] states = new int[triangle.length][triangle.length];

    /**
     * 状态转移表法实现
     */
    public static int minDist() {
        states[0][0] = triangle[0][0];
        for (int i = 1; i < triangle.length; i++) {
            for (int j = 0; j < triangle[i].length; j++) {
                // 初始化最左路径
                if (j == 0) {
                    states[i][j] = triangle[i][j] + states[i - 1][j];
                }
                // 初始化最右路径
                else if (j == triangle[i].length - 1) {
                    states[i][j] = triangle[i][j] + states[i - 1][j - 1];
                }
                // 取上一层路径中距离最小的节点
                else {
                    int left = states[i - 1][j - 1];
                    int right = states[i - 1][j];
                    states[i][j] = triangle[i][j] + Math.min(left, right);
                }
            }
        }
        // 求最小值
        int minDist = Integer.MAX_VALUE;
        for (int i = 0; i < triangle.length; i++) {
            int dist = states[triangle.length - 1][i];
            if (dist < minDist) {
                minDist = dist;
            }
        }
        return minDist;
    }

    public static void main(String[] args) {
        System.out.println(YangHuiTriangle.minDist());
    }
}
