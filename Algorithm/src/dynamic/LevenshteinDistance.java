package dynamic;

/**
 * 莱文斯坦距离（动态规划实现）
 *
 * @author xulei
 * @date 2020/8/23 10:10 下午
 */
public class LevenshteinDistance {

    /**
     * 字符串编辑距离
     *
     * @param a 字符串a
     * @param b 字符串b
     * @return 编辑距离
     */
    public static int match(char[] a, char[] b) {
        int[][] minDist = new int[a.length][b.length];

        // 初始化第0行，a[0..0]与b[0..i]的编辑距离，相当于求解a[0]和b在所有位置的最小编辑距离
        for (int i = 0; i < b.length; i++) {
            // 如果相等直接赋值为i，因为直接把b前面i-1个位置的元素删除就行，对应的编辑距离就是i
            if (a[0] == b[i]) {
                minDist[0][i] = i;
            } else {
                if (i == 0) {
                    minDist[0][0] = 1;
                } else {
                    minDist[0][i] = minDist[0][i - 1] + 1;
                }
            }
        }

        // 初始化第0列，a[0..i]与b[0..0]的编辑距离
        for (int i = 0; i < a.length; i++) {
            if (a[i] == b[0]) {
                minDist[i][0] = i;
            } else {
                if (i == 0) {
                    minDist[0][0] = 1;
                } else {
                    minDist[i][0] = minDist[i - 1][0] + 1;
                }
            }
        }

        // 根据状态转移方程循环填充
        for (int i = 1; i < a.length; i++) {
            for (int j = 1; j < b.length; j++) {
                if (a[i] == b[j]) {
                    minDist[i][j] = min(minDist[i-1][j] + 1, minDist[i][j-1] + 1, minDist[i-1][j-1]);
                } else {
                    minDist[i][j] = min(minDist[i-1][j] + 1, minDist[i][j-1] + 1, minDist[i-1][j-1] + 1);
                }
            }
        }
        return minDist[a.length - 1][b.length - 1];
    }

    private static int min(int x, int y, int z) {
        int minValue = Math.min(x, y);
        minValue = Math.min(minValue, z);
        return minValue;
    }

    public static void main(String[] args) {
        char[] a = new char[]{'h', 'e', 'l', 'l', 'o'};
        char[] b = new char[]{'h', 'p', 'l'};
        System.out.println(LevenshteinDistance.match(a, b));
    }
}
