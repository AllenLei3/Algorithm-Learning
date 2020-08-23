package dynamic;

/**
 * 最长公共子串（动态规划实现），编辑距离的一种实现方式，只可以新增、删除字符，不可以修改
 *
 * @author xulei
 * @date 2020/8/23 11:12 下午
 */
public class MaxPublicSubString {

    public static int match(char[] a, char[] b) {
        int[][] maxString = new int[a.length][b.length];

        // 初始化列，求b[0]和a在所有位置的最长公共子串
        for (int i = 0; i < a.length; i++) {
            // 相当于把a前面i-1个位置的元素删除，对应的最长公共子串就是1，因为b[0]只有一个元素
            if (a[i] == b[0]) {
                maxString[i][0] = 1;
            } else {
                if (i == 0) {
                    maxString[0][0] = 0;
                } else {
                    maxString[i][0] = maxString[i-1][0];
                }
            }
        }

        // 初始化行，求a[0]和b在所有位置的最长公共子串
        for (int i = 0; i < b.length; i++) {
            if (a[0] == b[i]) {
                maxString[0][i] = 1;
            } else {
                if (i == 0) {
                    maxString[0][0] = 0;
                } else {
                    maxString[0][i] = maxString[0][i-1];
                }
            }
        }

        for (int i = 1; i < a.length; i++) {
            for (int j = 1; j < b.length; j++) {
                if (a[i] == b[j]) {
                    maxString[i][j] = max(maxString[i-1][j], maxString[i][j-1], maxString[i-1][j-1] + 1);
                } else {
                    maxString[i][j] = max(maxString[i-1][j], maxString[i][j-1], maxString[i-1][j-1]);
                }
            }
        }
        return maxString[a.length - 1][b.length - 1];
    }

    private static int max(int x, int y, int z) {
        int minValue = Math.max(x, y);
        minValue = Math.max(minValue, z);
        return minValue;
    }

    public static void main(String[] args) {
        char[] a = new char[]{'h', 'e', 'l', 'l', 'o'};
        char[] b = new char[]{'h', 'p', 'l'};
        System.out.println(MaxPublicSubString.match(a, b));
    }
}
