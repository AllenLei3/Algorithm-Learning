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
    public BinaryTree.TreeNode findNode(BinaryTree.TreeNode root, int data) {
        while (root != null) {
            if (root.value > data) {
                root = root.left;
            } else if (root.value < data) {
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
    public void insertNode(BinaryTree.TreeNode root, int data) {
        while (root != null) {
            if (root.value < data) {
                if (root.right == null) {
                    root.right = new BinaryTree.TreeNode(data);
                    return;
                }
                root = root.right;
            } else {
                if (root.left == null) {
                    root.left = new BinaryTree.TreeNode(data);
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
    public BinaryTree.TreeNode deleteNode(BinaryTree.TreeNode root, int data) {
        // 先获取要删除节点的父节点
        BinaryTree.TreeNode parent = null;
        while (root != null && root.value != data) {
            parent = root;
            if (root.value > data) {
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
            BinaryTree.TreeNode min = root.right;
            BinaryTree.TreeNode minParent = root;
            while (min.left != null) {
                minParent = min;
                min = min.left;
            }
            // 交换元素值
            root.value = min.value;
            // 这里改变引用是为了通过下面的代码来删除min节点
            root = min;
            parent = minParent;
        }

        // 要删除的节点是叶子节点或只有一个子节点
        BinaryTree.TreeNode child = null;
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

    public static void main(String[] args) {
        BinaryTree.TreeNode root = new BinaryTree.TreeNode(1);

        BinarySearchTree tree = new BinarySearchTree();
        tree.insertNode(root, 55);
        tree.insertNode(root, 67);
        tree.insertNode(root, 84);
        tree.insertNode(root, 15);
        tree.insertNode(root, 39);
        tree.insertNode(root, 42);
        tree.insertNode(root, 37);

        // 中序遍历即为顺序输出
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.inOrder(root);

        System.out.println("-----------");
        binaryTree.inOrder(tree.findNode(root, 15));

        System.out.println("-----------");
        binaryTree.inOrder(tree.deleteNode(root, 37));
    }
}
