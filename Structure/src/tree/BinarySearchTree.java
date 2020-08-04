package tree;

/**
 * 二叉查找树
 *
 * @author xulei
 * @date 2020/8/4 12:30 上午
 */
public class BinarySearchTree {

    /**
     * 二叉查找树的查找操作
     */
    public TreeNode findNode(TreeNode root, int data) {
        while (root != null) {
            if (root.data > data) {
                root = root.left;
            } else if (root.data < data) {
                root = root.right;
            } else {
                return root;
            }
        }
        return null;
    }

    /**
     * 二叉查找树的插入操作
     */
    public void insertNode(TreeNode root, int data) {
        while (root != null) {
            if (root.data < data) {
                if (root.right == null) {
                    root.right = new TreeNode(data);
                    return;
                }
                root = root.right;
            } else {
                if (root.left == null) {
                    root.left = new TreeNode(data);
                    return;
                }
                root = root.left;
            }
        }
    }

    /**
     * 二叉查找树的删除操作
     *
     * @param root 指向要删除的节点，初始化为根节点
     * @param data data
     */
    public TreeNode deleteNode(TreeNode root, int data) {
        // 先获取要删除节点的父节点
        TreeNode parent = null;
        while (root != null && root.data != data) {
            parent = root;
            if (root.data > data) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        if (root == null) {
            return null;
        }

        // 如果要删除的节点有两个子节点
        if (root.left != null && root.right != null) {
            // 获取要删除节点的右子树中的最小值
            TreeNode min = root.right;
            TreeNode minParent = root;
            while (min.left != null) {
                minParent = min;
                min = min.left;
            }
            // 交换元素值
            root.data = min.data;
            // 这里改变引用是为了通过下面的代码来删除min节点
            root = min;
            parent = minParent;
        }

        // 要删除的节点是叶子节点或只有一个子节点
        TreeNode child = null;
        if (root.left != null) {
            child = root.left;
        } else if (root.right != null) {
            child = root.right;
        }

        // 要删除的是根节点
        if (parent == null) {
            return root;
        }
        // 将子节点代替要删除的节点,child为空或是要删除节点的子节点
        else if (parent.left == root) {
            parent.left = child;
        } else {
            parent.right = child;
        }
        return parent;
    }

    public static class TreeNode {
        private int data;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int data) {
            this.data = data;
        }
    }
}
