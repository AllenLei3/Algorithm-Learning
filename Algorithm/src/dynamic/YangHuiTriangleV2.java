package dynamic;

/**
 * 杨辉三角V2
 *
 * @author xulei
 * @date 2020/8/18 8:49 下午
 */
public class YangHuiTriangleV2 {

    /** 杨辉三角 */
    private static final int[][] triangle = {{5},{7, 8},{2, 3, 4},{4, 9, 6, 1},{2, 7, 9, 4, 5}};

    /**
     * 状态转移方程法实现,从下往上倒推
     */
    public static int minDist() {
        // 用于存储每一层的状态
        int[] min = new int[triangle.length + 1];
        // 从最底层开始
        for (int i = triangle.length - 1; i >= 0; i--) {
            int[] row = triangle[i];
            for (int j = 0; j < row.length; j++) {
                // 取下一层中的最小值
                min[j] = row[j] + Math.min(min[j], min[j + 1]);
            }
        }
        return min[0];
    }

    public static void main(String[] args) {
        System.out.println(YangHuiTriangleV2.minDist());
    }
}
