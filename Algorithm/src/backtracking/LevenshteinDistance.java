package backtracking;

/**
 * 莱文斯坦距离（回溯算法实现）
 *
 * @author xulei
 * @date 2020/8/19 5:06 下午
 */
public class LevenshteinDistance {

    private final char[] a;
    private final char[] b;
    /** 最短距离 */
    private int minDist = Short.MAX_VALUE;

    public LevenshteinDistance(String a, String b) {
        this.a = a.toCharArray();
        this.b = b.toCharArray();
    }

    /**
     * 计算编辑距离
     *
     * @param i a串下标索引
     * @param j b串下标索引
     * @param dist 距离
     */
    private void match(int i, int j, int dist) {
        // 其中一个字符串匹配完了
        if (i == a.length || j == b.length) {
            // 相当于把多余字符都删掉
            if (i < a.length) {
                dist = dist + a.length - i;
            }
            if (j < b.length) {
                dist = dist + b.length - j;
            }
            // 更新最小值
            if (dist < minDist) {
                minDist = dist;
            }
            return;
        }
        // 两个字符匹配
        if (a[i] == b[j]) {
            match(i + 1, j + 1, dist);
        } else {
            // 相当于删除a[i]（a取下一个，b不变）或在b[j]前加一个字符（b前面加了一个，所以下一次匹配还是b，而a要取下一个）
            match(i + 1, j, dist + 1);
            // 相当于删除b[j]（b取下一个，a不变）或在a[i]前加一个字符（a前面加了一个，所以下一个匹配还是a，而b要取下一个）
            match(i, j + 1, dist + 1);
            // a[i]和b[j]替换
            match(i + 1, j + 1, dist + 1);
        }
    }

    public static void main(String[] args) {
        LevenshteinDistance ld = new LevenshteinDistance("hello", "hpl");
        ld.match(0, 0, 0);
        System.out.println(ld.minDist);
    }
}
