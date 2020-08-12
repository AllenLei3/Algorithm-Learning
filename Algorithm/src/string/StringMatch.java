package string;

/**
 * 字符串匹配算法
 *
 * @author xulei
 * @date 2020/8/10 7:42 下午
 */
public class StringMatch {

    /**
     * 暴力匹配算法(BF算法)
     *
     * @param main 主串
     * @param pattern 模式串
     */
    public static int bf(String main, String pattern) {
        int n = main.length();
        int m = pattern.length();
        int k;
        // 主串
        char[] mainArray = main.toCharArray();
        // 模式串
        char[] patternArray = pattern.toCharArray();
        // 遍历子串
        for (int i = 0; i <= n - m; i++) {
            k = 0;
            // 遍历模式串中的每个字符看是否匹配子串
            for (int j = 0; j < m; j++) {
                if (mainArray[i + j] == patternArray[j]) {
                    k++;
                } else {
                    break;
                }
            }
            if (k == m) {
                return i;
            }
        }
        return -1;
    }

    /**
     * RK算法，假设字符串中的每个字符都是a～z
     * 每个字符对应整数0～25，采用二十六进制计算哈希值
     *
     * @param main 主串
     * @param pattern 模式串
     */
    public static int rk(String main, String pattern) {
        int n = main.length();
        int m = pattern.length();
        // 保存子串的哈希值
        int[] hash = new int[n - m + 1];
        int[] table = new int[26];
        // 主串
        char[] mainArray = main.toCharArray();
        // 模式串
        char[] patternArray = pattern.toCharArray();

        // 用哈希表保存计算好的平方值，空间换时间策略
        int number = 1;
        for (int i = 0; i < table.length; i++) {
            table[i] = number;
            number *= 26;
        }
        // 循环计算每个子串的哈希值
        for (int i = 0; i <= n - m; i++) {
            int hashcode = 0;
            for (int j = 0; j < m; j++) {
                hashcode += (mainArray[i + j] - 'a') * table[m - 1 - j];
            }
            hash[i] = hashcode;
        }
        // 计算模式串的哈希值
        int patternHashcode = 0;
        for (int i = 0; i < m; i++) {
            patternHashcode += (patternArray[i] - 'a') * table[m- 1 - i];
        }
        // 遍历判断哈希值是否相等
        for (int i = 0; i < n - m + 1; i++) {
            if (hash[i] == patternHashcode) {
                return i;
            }
        }
        return -1;
    }
}
