package stack;

/**
 * 顺序栈（数组实现）
 *
 * @author xulei
 * @date 2020/7/24 2:08 上午
 */
public class ArrayStack {

    private final String[] item;
    /** 栈顶指针 */
    private int count;

    public ArrayStack(int capacity) {
        item = new String[capacity];
        count = 0;
    }

    public boolean push(String s) {
        // 栈满
        if (count == item.length) {
            return false;
        }
        item[count] = s;
        ++count;
        return true;
    }

    public String pop() {
        // 栈空
        if (count == 0) {
            return null;
        }
        String value = item[count - 1];
        --count;
        return value;
    }

    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(10);
        stack.push("1");
        stack.push("2");
        stack.push("3");
        stack.push("4");
        stack.push("5");

        for (int i = 0; i < 5; i++) {
            System.out.println(stack.pop());
        }
    }
}
