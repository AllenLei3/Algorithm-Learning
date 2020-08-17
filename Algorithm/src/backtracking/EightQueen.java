package backtracking;

/**
 * 回溯算法典型应用
 *
 * 八皇后问题,棋盘为8*8,每个棋子所在的行、列、对角线都不能有另一个棋子
 *
 * @author xulei
 * @date 2020/8/16 10:59 下午
 */
public class EightQueen {

    /** 数组下标为行，值为棋子在该行所处的列 */
    private static final int[] queen = new int[8];

    /**
     * 放棋子(皇后)，获取符合条件的每种排列组合
     *
     * @param row 行数，从0开始
     */
    public void putQueen(int row) {
        // 棋子放置完毕，打印棋盘
        if (row == queen.length) {
            printEightQueen();
            return;
        }
        // 循环每列，即会获取第一个棋子在第一行中的每一列的所有排列组合
        for (int column = 0; column < queen.length; column++) {
            // 判断本次放法是否满足要求
            if (putSuccess(row, column)) {
                queen[row] = column;
                // 递归放置棋子
                putQueen(row + 1);
            }
        }
    }

    /**
     * 判断row行column列放置是否合适，row、column都是从0开始
     */
    private boolean putSuccess(int row, int column) {
        int leftUp = column - 1;
        int rightUp = column + 1;

        // 遍历row上面的已经放置了棋子的所有行，i表示row的上一行，因为row从0开始，所以i>=0
        for (int i = row - 1; i >= 0; i--) {
            // 判断在该行以上的其他行中，有没有棋子放在了同一列上的
            if (queen[i] == column) {
                return false;
            }
            // 判断该位置的左上位置是否已经有棋子了，即左对角线
            if (leftUp >= 0) {
                if (queen[i] == leftUp) {
                    return false;
                }
            }
            // 判断该位置的右上位置是否已经有棋子了，即右对角线
            if (rightUp < 8) {
                if (queen[i] == rightUp) {
                    return false;
                }
            }
            leftUp--;
            rightUp++;
        }
        return true;
    }

    /**
     * 打印二维矩阵的棋盘
     */
    private void printEightQueen() {
        for (int row = 0; row < queen.length; row++) {
            for (int column = 0; column < queen.length; column ++) {
                if (queen[row] == column) {
                    System.out.print("Q ");
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        EightQueen queen = new EightQueen();
        queen.putQueen(0);
    }
}
