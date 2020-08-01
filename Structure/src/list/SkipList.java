package list;

/**
 * 跳表
 * 假设存储的是正整数，并且存储的元素是不重复的。
 *
 * @author xulei
 * @date 2020/7/31 5:47 下午
 */
public class SkipList {

    /** 最大层数 */
    private static final int MAX_LEVEL = 16;
    /** 当前层数 */
    private int levelCount = 1;
    /** 头节点 */
    private final Node head = new Node();

    /**
     * 查找
     */
    public Node find(int value) {
        Node p = head;
        // 从最顶层开始，遍历每层查找
        for (int i = levelCount - 1; i >= 0; --i) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
        }
        if (p.forwards[0] != null && p.forwards[0].data == value) {
            return p.forwards[0];
        } else {
            return null;
        }
    }

    /**
     * 插入
     */
    public void insert(int value) {
        // 随机生成层数
        int level = randomLevel();
        Node newNode = new Node();
        newNode.data = value;
        newNode.maxLevel = level;

        // update[]用来保存新节点的所有层数上，每个索引节点的前一个节点的信息
        Node[] update = new Node[level];
        for (int i = 0; i < level; ++i) {
            // 先循环将新节点的每层索引节点指向头节点
            update[i] = head;
        }
        Node p = head;
        // 从level层开始，从头节点开始遍历向后查找，索引节点要插入的位置
        for (int i = level - 1; i >= 0; --i) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
            update[i] = p;
        }

        // 修改指针，插入索引层节点
        for (int i = 0; i < level; ++i) {
            newNode.forwards[i] = update[i].forwards[i];
            update[i].forwards[i] = newNode;
        }
        // 更新当前层高
        if (levelCount < level) levelCount = level;
    }

    /**
     * 删除
     */
    public void delete(int value) {
        Node[] update = new Node[levelCount];
        Node p = head;

        // 从最高层开始，遍历每层查找要删除的元素
        for (int i = levelCount - 1; i >= 0; --i) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
            // 这里即使不是给定元素也会放到update数组中
            update[i] = p;
        }

        // 遍历update数组进行删除，update数组的每个元素不一定是给定元素，因此删除前需要进行判断
        if (p.forwards[0] != null && p.forwards[0].data == value) {
            for (int i = levelCount - 1; i >= 0; --i) {
                if (update[i].forwards[i] != null && update[i].forwards[i].data == value) {
                    update[i].forwards[i] = update[i].forwards[i].forwards[i];
                }
            }
        }
        while (levelCount > 1 && head.forwards[levelCount] == null){
            levelCount--;
        }
    }

    /**
     * 该 randomLevel 方法会随机生成 1~MAX_LEVEL 之间的数，且 ：
     * 50%的概率返回 1
     * 25%的概率返回 2
     * 12.5%的概率返回 3 ...
     */
    private static int randomLevel() {
        int level = 1;
        while (Math.random() < 0.5f && level < MAX_LEVEL)
            level += 1;
        return level;
    }

    public static class Node {
        private int data = -1;
        // forwards数组用于存储该节点所有层的下一个节点的信息
        private final Node[] forwards = new Node[MAX_LEVEL];
        private int maxLevel = 0;

        @Override
        public String toString() {
            return "{ data: " + data + "; levels: " + maxLevel + " }";
        }
    }
}
