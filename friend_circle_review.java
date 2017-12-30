class Solution {
    class UF {
        // 注意：有f[x]的就代表是x的父親是誰 或是(若為root就是x的這群有多少人).
        // 而x就是這個元素是什麼, 並不是取他父親
        int[] f;
        UF(int length) {
            f = new int[length];
            for (int i = 0; i < length; i++) {
                f[i] = -1;
            }
        }
        
        // find: 回傳該團的root數字, 不是他有多少個兒子
        int find(int x) {
            // base case
            if (f[x] < 0) {
                return x;
            }
            f[x] = find(f[x]);
            
            // 修改過後的f[x], 也就是變成壓縮後的f[x]了
            return f[x];
        }
        
        // union: 把兩個不同群的合併, 大吃小
        void union(int x, int y) {
            // 找x,y的root, 就是要比誰比較多元素來大吃小
            int root_of_x = find(x);
            int root_of_y = find(y);
            
            if (f[root_of_x] < f[root_of_y]) {
                // x元素多, x吃掉y
                f[root_of_x] = f[root_of_x] + f[root_of_y];
                f[root_of_y] = root_of_x;
            } else {
                f[root_of_y] = f[root_of_x] + f[root_of_y];
                f[root_of_x] = root_of_y;
            }
        }
    }
    
    public int findCircleNum(int[][] M) {
        UF uf = new UF(M.length);
        for (int i = 0; i < M.length; i++) {
            for (int j = i + 1; j < M.length; j++) {
                if (M[i][j] == 1 && uf.find(i) != uf.find(j)) {
                    uf.union(i, j);
                }
            }
        }
        
        int ans = 0;
        for (int i = 0; i < M.length; i++) {
            if (uf.f[i] < 0) {
                ans++;
            }
        }
        return ans;
    }
}


// BFS解法, 和求無方向圖的Connect Componment數量一樣
class Solution {
    public int findCircleNum(int[][] M) {
        // BFS呼叫幾次就代表有幾個friend circle
        int ans = 0;
        
        // 判斷是否走訪過
        boolean[] visited = new boolean[M.length];
        
        for (int i = 0; i < M.length; i++) {
            if (visited[i] == false) {
                ans++;
                bfs(M, i, visited);
            }
        }
        return ans;
    }
    void bfs(int[][] M, int start, boolean[] visited) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        
        while (!q.isEmpty()) {
            int qsize = q.size();
            for (int i = 0; i < qsize; i++) {
                int cur = q.remove();
                visited[cur] = true;
                for (int j = 0; j < M.length; j++) {
                    // 對角線不要看: j != cur
                    // 兩點之間有連線: M[cur][j] == 1
                    // 對方還沒被走訪過: visited[j] == false
                    if (j != cur && M[cur][j] == 1 && visited[j] == false) {
                        visited[cur] = true;
                        q.add(j);
                    }
                }
            }
        }
    }
}