package string;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * AC自动机——多模式串匹配
 *
 * @author xulei
 * @date 2020/8/16 12:14 上午
 */
public class AcAutoMata {

    private final AcNode root = new AcNode("/");

    /**
     * 添加模式串，即构建Trie树的过程
     */
    private void insert(String pattern) {
        AcNode node = this.root;
        for (int i = 0; i < pattern.length(); i++) {
            String c = pattern.charAt(i) + "";
            if (node.child.get(c) == null) {
                node.child.put(c, new AcNode(c));
            }
            node = node.child.get(c);
        }
        node.isEndingChar = true;
        node.length = pattern.length();
    }

    /**
     * 构建失败指针
     */
    private void buildFailurePointer() {
        AcNode root = this.root;
        LinkedList<AcNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            AcNode start = queue.pop();
            // 遍历子节点，设置失败指针
            for (AcNode childNode: start.child.values()){
                if (childNode == null) {
                    continue;
                }
                // 根节点的失败指针指向自己
                if (start == root) {
                    childNode.fail = root;
                } else {
                    AcNode failNode = start.fail;
                    while (failNode != null) {
                        AcNode qc = failNode.child.get(childNode.data);
                        if (qc != null) {
                            childNode.fail = qc;
                            break;
                        }
                        failNode = failNode.fail;
                    }
                    if (failNode == null) {
                        childNode.fail = root;
                    }
                }
                queue.add(childNode);
            }
        }
    }

    private Boolean match(String main) {
        AcNode root = this.root;
        AcNode p = root;

        // 循环主串字符
        for (int i = 0; i < main.length(); i++) {
            String c = main.charAt(i) + "";
            while (p.child.get(c) == null && p != root){
                p = p.fail;
            }
            p = p.child.get(c);
            // 如果没有匹配的，从root开始重新匹配
            if (p == null) {
                p = root;
            }
            AcNode tmp = p;
            while (tmp != root) {
                if (tmp.isEndingChar) {
                    System.out.println("Start from " + (i - p.length + 1));
                    return true;
                }
                tmp = tmp.fail;
            }
        }
        return false;
    }

    /**
     * 多模式串匹配
     */
    public static boolean match(String main, String[] patterns) {
        AcAutoMata automata = new AcAutoMata();
        // 插入多个模式串
        for (String pattern: patterns) {
            automata.insert(pattern);
        }
        automata.buildFailurePointer();
        return automata.match(main);
    }

    private static class AcNode {

        private final String data;
        /** 这里采用哈希表，key为节点元素，value为对应节点元素指向的指针 */
        private final Map<String, AcNode> child = new HashMap<>();
        private boolean isEndingChar = false;
        private int length = 0;
        /** 失败指针，当模式串匹配失败时跳转的指针 */
        private AcNode fail;

        public AcNode(String data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        String[] patterns = {"at", "art", "oars", "soar"};
        String text = "soarsoars";
        System.out.println(match(text, patterns));

        String[] patterns2 = {"Fxtec Pro1", "谷歌Pixel"};
        String text2 = "一家总部位于伦敦的公司Fxtex在MWC上就推出了一款名为Fxtec Pro1的手机，该机最大的亮点就是采用了侧滑式全键盘设计。DxOMark年度总榜发布 华为P20 Pro/谷歌Pixel 3争冠";
        System.out.println(match(text2, patterns2));
    }
}
