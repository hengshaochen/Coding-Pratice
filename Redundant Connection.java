class Solution {
    class UF {
        int[] f;
        UF(int x) {
            f = new int[x];
            for (int i = 0; i < x; i++) {
                f[i] = -1;
            }
        }
        
        void union(int x, int y) {
            int root_of_x = find(x);
            int root_of_y = find(y);
            
            if (f[root_of_x] < f[root_of_y]) {
                f[root_of_x] = f[root_of_y] + f[root_of_x];
                f[root_of_y] = root_of_x;
            } else {
                f[root_of_y] = f[root_of_y] + f[root_of_x];
                f[root_of_x] = root_of_y;
            }
        }
        
        int find(int x) {
            if (f[x] < 0) {
                return x;
            }
            f[x] = find(f[x]);
            return f[x];
        }
    }
    public int[] findRedundantConnection(int[][] edges) {
        UF uf = new UF(edges.length * 2);
        
        for (int i = 0; i < edges.length; i++) {
            if (uf.find(edges[i][0]) != uf.find(edges[i][1])) {
                uf.union(edges[i][0], edges[i][1]);
            } else {
                return new int[]{edges[i][0], edges[i][1]};
            }
        }
        return new int[2];
    }
}