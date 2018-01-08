public class Solution {
    /*
     * @param n: An integer
     * @param edges: a list of undirected edges
     * @return: true if it's a valid tree, or false
     */
    public boolean validTree(int n, int[][] edges) {
        if (edges.length + 1 != n) {
            return false;
        }
        // Build Graph (adjacency list)  Map<Node, Neighbor>
        HashMap<Integer, HashSet<Integer>> graph = buildGraph(n, edges);
        
        HashSet<Integer> node = new HashSet<>();
        
        // BFS
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        node.add(0);
        int counter = 1;
        while (!q.isEmpty()) {
            int cur = q.remove();
            // traverse hashset
            for (int neighbor : graph.get(cur)) {
                counter++;
                if (!node.contains(neighbor)) {
                    node.add(neighbor);
                    q.add(neighbor);
                }
            }
        }
        System.out.println(counter);
        if (node.size() == n) {
            return true;
        }
        return false;
    }
    
    HashMap<Integer, HashSet<Integer>> buildGraph(int n, int[][] edges) {
        HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            graph.put(i, new HashSet<Integer>());
        }
        
        for (int i = 0; i < edges.length; i++) {
            graph.get(edges[i][0]).add(edges[i][1]);
            graph.get(edges[i][1]).add(edges[i][0]);
        }
        return graph;
    }
}

// DFS Iteration:
public class Solution {
    /*
     * @param n: An integer
     * @param edges: a list of undirected edges
     * @return: true if it's a valid tree, or false
     */
    public boolean validTree(int n, int[][] edges) {
        if (edges.length + 1 != n) {
            return false;
        }
        // Build Graph (adjacency list)  Map<Node, Neighbor>
        HashMap<Integer, HashSet<Integer>> graph = buildGraph(n, edges);
        
        HashSet<Integer> node = new HashSet<>();
        
        // DFS
        Stack<Integer> s = new Stack<>();
        s.push(0);
        node.add(0);
        int counter = 1;
        while (!s.isEmpty()) {
            int cur = s.pop();
            System.out.println(cur);
            // traverse hashset
            for (int neighbor : graph.get(cur)) {
                if (!node.contains(neighbor)) {
                    node.add(neighbor);
                    s.push(neighbor);
                }
            }
        }
        if (node.size() == n) {
            return true;
        }
        return false;
    }
    
    HashMap<Integer, HashSet<Integer>> buildGraph(int n, int[][] edges) {
        HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            graph.put(i, new HashSet<Integer>());
        }
        
        for (int i = 0; i < edges.length; i++) {
            graph.get(edges[i][0]).add(edges[i][1]);
            graph.get(edges[i][1]).add(edges[i][0]);
        }
        return graph;
    }
}

// DFS Recursive:
public class Solution {
    /*
     * @param n: An integer
     * @param edges: a list of undirected edges
     * @return: true if it's a valid tree, or false
     */
    public boolean validTree(int n, int[][] edges) {
        if (edges.length + 1 != n) {
            return false;
        }
        // Build Graph (adjacency list)  Map<Node, Neighbor>
        HashMap<Integer, HashSet<Integer>> graph = buildGraph(n, edges);
        
        HashSet<Integer> node = new HashSet<>();
        
        // DFS
        DFS(graph, node, 0);
        
        if (node.size() == n) {
            return true;
        }
        return false;
    }
    
    void DFS(HashMap<Integer, HashSet<Integer>> graph, HashSet<Integer> node, int current_node) {
        // base case
        if (node.contains(current_node)) {
            return;
        }
        
        // 把當前node加入已走訪的set
        node.add(current_node);
        
        // 用DFS方式走訪current_node的鄰居, 會一直走訪到沒有鄰居在backtrack
        for (int neighbor : graph.get(current_node)) {
            DFS(graph, node, neighbor);
        }
    }
    
    HashMap<Integer, HashSet<Integer>> buildGraph(int n, int[][] edges) {
        HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            graph.put(i, new HashSet<Integer>());
        }
        
        for (int i = 0; i < edges.length; i++) {
            graph.get(edges[i][0]).add(edges[i][1]);
            graph.get(edges[i][1]).add(edges[i][0]);
        }
        return graph;
    }
}

// 法3: Union-Find
public class Solution {
    class UF {
        int[] f;
        UF(int size) {
            f = new int[size];
            for (int i = 0; i < size; i++) {
                f[i] = -1;
            }
        }
        
        int find(int x) {
            if (f[x] < 0) {
                return x;
            }
            
            // path compression
            f[x] = find(f[x]);
            
            return f[x];
        }
        
        void union(int x, int y) {
            int root_of_x = find(x);
            int root_of_y = find(y);
            if (f[root_of_x] < f[root_of_y]) {
                // x merge y
                f[root_of_x] = f[root_of_x] + f[root_of_y];
                f[root_of_y] = root_of_x;
            } else {
                f[root_of_y] = f[root_of_x] + f[root_of_y];
                f[root_of_x] = root_of_y;
            }
        }
    }
     
    public boolean validTree(int n, int[][] edges) {
        // 先檢查是否n個點n - 1個邊, 因為uf只是在找有沒有cycle
        if (n - 1 != edges.length) {
            return false;
        }
        
        UF uf = new UF(edges.length * 2);
        for (int i = 0; i < edges.length; i++) {
            if (uf.find(edges[i][0]) != uf.find(edges[i][1])) {
                uf.union(edges[i][0], edges[i][1]);
            } else {
                // 代表有cycle
                return false;
            }
        }
        return true;
    }
}
        