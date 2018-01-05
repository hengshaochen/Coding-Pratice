class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
             return 0;
        }
        
        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    ans++;
                    DFS(grid, i, j);
                }
            }
        }
        return ans;
    }
    
    public void DFS(char[][] grid, int x, int y) {
        // 走過的點變成海洋
        grid[x][y] = '0';
        
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        for (int i = 0; i < 4; i++) {
            int next_x = x + dx[i];
            int next_y = y + dy[i];
            if (outBoundary(grid, next_x, next_y) || grid[next_x][next_y] != '1') {
                continue;
            }
            DFS(grid, next_x, next_y);
        }
    }
    public boolean outBoundary(char[][] grid, int x, int y) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) {
            return true;
        }
        return false;
    }
}