// "static void main" must be defined in a public class.
public class Solution {
    public static void main(String[] args) {
        new Solution();
    }
    public Solution() {
        
        // yes
        int G[][] = {{0, 1, 0, 1},
            {1, 0, 1, 0},
            {0, 1, 0, 1},
            {1, 0, 1, 0}};
        /*
        
        // no
        int G[][] = {
            {0, 1, 1},
            {1, 0, 1},
            {1, 1, 0}
        };
        */
        if (isBipartite(G))
           System.out.println("Yes");
        else
           System.out.println("No");
    }
    
    boolean isBipartite(int[][] G) {
        int[] color = new int[G.length];
        
        // 先初始為沒塗色
        for (int i = 0; i < color.length; i++) {
            color[i] = -1;
        }
        // 起點初始化顏色為1
        color[0] = 1;
        
        // 做BFS
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        
        while (!q.isEmpty()) {
            int qsize = q.size();
            
            for (int j = 0; j < qsize; j++) {
                int cur = q.remove();
                
                // 如果兩點之間有相鄰 且 相鄰點沒有塗色 --> 給他塗上另一個顏色, 並將相鄰點加入queue.
                for (int i = 0; i < G[0].length; i++) {
                    if (G[cur][i] == 1 && color[i] == -1) {
                        color[i] = 1 - color[cur];  // 塗上另一個顏色
                        q.add(i);
                    }
                    // 相鄰, 但相鄰點顏色相同
                    else if (G[cur][i] == 1 && color[i] == color[cur]) {
                        return false;
                    }
                    // else 如果相鄰點已經塗色, 什麼事都不用作
                }
            }
        }
        return true;
    }
}