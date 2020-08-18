package dynamic;

/**
 * 矩阵最短路径问题
 *
 * 在4*4的矩阵中，起点是chessboard[0][0]，终点是chessboard[3][3]
 * 求从起点到终点所经过的路径的最短长度
 *
 * @author xulei
 * @date 2020/8/18 2:53 下午
 */
public class Chessboard {

    /** 4*4的矩阵 */
    private static final int[][] chessboard = new int[4][4];
    /** 状态数组，对应矩阵，数组值为从起始点到该节点的最短路径 */
    private static final int[][] states = new int[4][4];

    static {
        chessboard[0] = new int[]{1, 3, 5, 9};
        chessboard[1] = new int[]{2, 1, 3, 4};
        chessboard[2] = new int[]{5, 2, 6, 7};
        chessboard[3] = new int[]{6, 8, 4, 3};
    }

    /**
     * 求矩阵的最短路径(状态转移表法实现)
     */
    public static int minDist() {
        // 初始化第一行
        int sum = 0;
        for (int i = 0; i < chessboard[0].length; i++) {
            sum = sum + chessboard[0][i];
            states[0][i] = sum;
        }
        // 初始化第一列
        sum = 0;
        for (int i = 0; i < chessboard.length; i++) {
            sum = sum + chessboard[i][0];
            states[i][0] = sum;
        }
        // 循环填充状态
        for (int i = 1; i < chessboard[0].length; i++) {
            for (int j = 1; j < chessboard.length; j++) {
                // 取上一步路径短的节点来累加
                states[i][j] = chessboard[i][j] + Math.min(states[i - 1][j], states[i][j - 1]);
            }
        }
        return states[states.length - 1][states[0].length - 1];
    }

    public static void main(String[] args) {
        System.out.println(Chessboard.minDist());
    }
}
