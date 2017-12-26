// "static void main" must be defined in a public class.
public class Solution {
    class Pair {
        int x;
        int y;
        Pair (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) {
        new Solution();
    }
    public Solution() {
        // 1. 建圖
        int[][] g = new int[][]{{0,1,1,0,0},
                            {1,0,0,0,0},
                            {1,0,0,1,1},
                            {0,0,1,0,0},
                            {0,0,1,0,0}};
        
        //Pair start = new Pair(0, 1);
        int start = 1;
        int end   = 4;
        
        // 判定是否訪問過 防止bfs重複訪問元素
        HashSet<Integer> set = new HashSet<>();
        
        Queue<Integer> q = new LinkedList<>();
        set.add(start);
        
        for (int i = 0; i < g.length; i++) {
            if (g[start][i] != 0) {
                set.add(i);
                q.add(i);
            }
        }
        
        int ans = 0;
        while (!q.isEmpty()) {
            ans++;
            int qsize = q.size();
            for (int i = 0; i < qsize; i++) {
                int current = q.remove();
                
                for (int j = 0; j < g.length; j++) {
                    if (g[current][j] != 0 && !set.contains(j)) {
                        set.add(j);
                        q.add(j);
                    }
                }
            }
        }
        
        System.out.println(ans);
    }
}
