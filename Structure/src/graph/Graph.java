package graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 图，通过邻接表来实现(数组+链表)
 *
 * @author xulei
 * @date 2020/8/9 3:56 下午
 */
public class Graph {

    /** 邻接表，数组下标代表顶点元素，链表里保存边的信息 */
    private final LinkedList<Integer>[] listArray;

    public Graph(int capacity) {
        listArray = new LinkedList[capacity];
        for (int i = 0; i < listArray.length; i++) {
            listArray[i] = new LinkedList<>();
        }
    }

    /**
     * 添加边，无向图一次保存两条边，有向图一次保存一条边
     *
     * @param s 顶点s
     * @param t 顶点t
     */
    public void addEdge(int s, int t) {
        listArray[s].add(t);
        listArray[t].add(s);
    }

    /**
     * 广度优先搜索,获取s到t的最短路径
     *
     * @param s 起始顶点
     * @param t 终止顶点
     */
    public void bfs(int s, int t) {
        if (s == t) {
            return;
        }
        // 用来记录已经被访问的顶点，避免顶点被重复访问
        boolean[] visited = new boolean[listArray.length];
        visited[s] = true;
        // 用来存储自身已经被访问了的，但其相连的顶点还没有被访问到的顶点
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        // 用来记录搜索路径，初始值都设为-1，表示没有元素。此处数组下标表示顶点，数组元素表示该下标顶点对应的前驱顶点
        int[] prev = new int[listArray.length];
        Arrays.fill(prev, -1);

        // 外层while循环用来遍历层
        while (queue.size() != 0) {
            int node = queue.poll();
            // 遍历该顶点所连接的其他顶点
            for (int i = 0; i < listArray[node].size(); i++) {
                int backNode = listArray[node].get(i);
                // 如果该顶点之前没有访问过
                if (!visited[backNode]) {
                    // 以该顶点为数组下标保存该顶点的前驱顶点，相当于prev[]数组的每个元素保存的都是该元素的前驱顶点
                    prev[backNode] = node;
                    if (backNode == t) {
                        // 递归输出路径，逆序输出，比如终点是6，则prev[6]指向的是6的前驱顶点，依次递推直到起点
                        print(prev, s, t);
                        return;
                    }
                    visited[backNode] = true;
                    // 已经访问过的顶点加到队列中
                    queue.add(backNode);
                }
            }
        }
    }

    private void print(int[] prev, int s, int t) {
        if (prev[t] != -1 && t != s) {
            print(prev, s, prev[t]);
        }
        System.out.println(t + " ");
    }

    public static void main(String[] args) {
        Graph graph = new Graph(8);
        graph.addEdge(0,1);
        graph.addEdge(0,3);
        graph.addEdge(1,2);
        graph.addEdge(1,4);
        graph.addEdge(2,5);
        graph.addEdge(4,5);
        graph.addEdge(4,6);
        graph.addEdge(5,7);
        graph.addEdge(6,7);

        // 广度优先
        graph.bfs(0,6);
    }
}
