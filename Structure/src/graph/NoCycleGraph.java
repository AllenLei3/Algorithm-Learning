package graph;

import java.util.LinkedList;

/**
 * 拓扑排序（有向无环图）
 *
 * @author xulei
 * @date 2020/8/24 8:59 下午
 */
public class NoCycleGraph {

    private final int v;
    private final LinkedList<Integer>[] adj;

    public NoCycleGraph(int capacity) {
        this.v = capacity;
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    /**
     * 添加边，t依赖s，添加一条由s指向t的边
     */
    public void addEdge(int s, int t) {
        adj[s].add(t);
    }

    /**
     * Kahn算法，先加载入度为0的顶点，在加载依赖该顶点的顶点
     */
    public void sort() {
        // 计算每个顶点的入度，即顶点所依赖的其他顶点
        int[] inDegree = new int[v];
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < adj[i].size(); j++) {
                int w = adj[i].get(j);
                inDegree[w]++;
            }
        }
        // 先取入度为0的进行计算，即不需要依赖其他顶点的顶点
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        // 从入度为0的顶点中加载依赖该顶点的其他顶点
        while (!queue.isEmpty()) {
            int i = queue.remove();
            System.out.print("->" + i);
            for (int j = 0; j < adj[i].size(); j++) {
                int k = adj[i].get(j);
                inDegree[k]--;
                // 如果出度以后为0，表示该顶点所依赖的顶点都加载完了，此时加载该顶点
                if (inDegree[k] == 0) {
                    queue.add(k);
                }
            }
        }
    }

    /**
     * DFS深度优先搜索，遍历所有顶点，如果该顶点依赖其他顶点，则递归加载其他顶点
     */
    public void sortByDFS() {
        LinkedList<Integer>[] inverseAdj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            inverseAdj[i] = new LinkedList<>();
        }
        // 根据邻接表构建逆邻接表,逆邻接表表示顶点所依赖了哪些顶点
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < adj[i].size(); j++) {
                int w = adj[i].get(j);
                inverseAdj[w].add(i);
            }
        }
        boolean[] visited = new boolean[v];
        for (int i = 0; i < v; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(i, inverseAdj, visited);
            }
        }
    }

    private static void dfs(int i, LinkedList<Integer>[] adj, boolean[] visited) {
        for (int j = 0; j < adj[i].size(); j++) {
            int w = adj[i].get(j);
            if (!visited[w]) {
                visited[w] = true;
                dfs(w, adj, visited);
            }
        }
        // 逆邻接表为空，表示该顶点不依赖其他顶点，直接输出
        System.out.print("->" + i);
    }

    public static void main(String[] args) {
        NoCycleGraph graph = new NoCycleGraph(8);
        graph.addEdge(0,1);
        graph.addEdge(0,3);
        graph.addEdge(1,2);
        graph.addEdge(1,4);
        graph.addEdge(2,5);
        graph.addEdge(4,5);
        graph.addEdge(4,6);
        graph.addEdge(5,7);
        graph.addEdge(6,7);

        graph.sort();
        System.out.println();
        graph.sortByDFS();
    }
}
