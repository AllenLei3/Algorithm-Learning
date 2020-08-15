package string;

/**
 * 通过Trie树进行字符串匹配
 *
 * @author xulei
 * @date 2020/8/14 5:44 下午
 */
public class TrieMatch {

    /** 根节点存储一个无意义字符 */
    private final TrieNode root = new TrieNode('/');

    /**
     * 往Trie树中插入一个字符串
     */
    public void insert(char[] text) {
        TrieNode start = root;
        for (char c : text) {
            // 因为我们按顺序存储的a～z，所以直接用ASCII码相减就可以快速得到指定的子节点的元素下标
            int index = c - 'a';
            if (start.child[index] == null) {
                TrieNode n = new TrieNode(c);
                start.child[index] = n;
            }
            start = start.child[index];
        }
        start.isEndingChar = true;
    }

    /**
     * 从Trie树中匹配一个字符串
     */
    public boolean findMatchText(char[] text) {
        TrieNode start = root;
        for (char c : text) {
            int index = c - 'a';
            if (start.child[index] == null) {
                return false;
            }
            start = start.child[index];
        }
        // 判断是否是结束节点，还是只是部分匹配
        return start.isEndingChar;
    }

    private static class TrieNode {

        /** 假设每个节点内存储的数据只有a～z */
        private final char data;

        /** a元素的下标为0,b元素的下标为1,以此类推,z的下标为25 */
        private final TrieNode[] child = new TrieNode[26];

        /** 是否是结束节点,结束节点不一定是根节点,比如字符abc和ab中的节点b，它是结束节点但不是根节点 */
        private boolean isEndingChar;

        public TrieNode(char data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        TrieMatch trie = new TrieMatch();
        trie.insert(new char[]{'h', 'e', 'l', 'l', 'o'});
        trie.insert(new char[]{'h', 'e'});
        trie.insert(new char[]{'a', 'l', 'l', 'e', 'n'});
        trie.insert(new char[]{'i', 'v', 'e', 'r', 's', 'o', 'n'});

        System.out.println(trie.findMatchText(new char[]{'a', 'l', 'l', 'e', 'n'}));
        System.out.println(trie.findMatchText(new char[]{'a', 'l', 'e', 'n'}));
    }
}
