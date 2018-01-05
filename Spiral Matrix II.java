class Solution {
    class Coord {
        int x, y;
        Coord (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public int[][] generateMatrix(int n) {
        int[][] ans = new int[n][n];
        if (n == 0) {
            return ans;
        }
        
        DFS(new Coord(0, 0), 0, ans, 0);
        return ans;
    }

    
    public void DFS(Coord cur, int direction, int[][] matrix, int count) {
        if (count == matrix.length * matrix[0].length) {
            return;
        }
        
        // 新增當前點 並標記為已走訪
        matrix[cur.x][cur.y] = ++count;
        
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        
        // 下個點
        Coord next = new Coord(cur.x + dx[direction], cur.y + dy[direction]);
        
        if (outBoundary(next, matrix) || matrix[next.x][next.y] != 0) {
            // 如果碰到走過的, 方向 + 1 或 如果撞牆, 方向 + 1 繼續用DFS走
            int new_direction = (direction + 1) % 4;
            Coord modify_next = new Coord(cur.x + dx[new_direction], cur.y + dy[new_direction]);
            DFS(modify_next, new_direction, matrix, count);
        } else {
            // 如果沒有撞牆或是遇到走過的, 同個方向走
            DFS(next, direction % 4, matrix, count);
        }
    }
    
    public boolean outBoundary(Coord cur, int[][] matrix) {
        if (cur.x < 0 || cur.x >= matrix.length || cur.y < 0 || cur.y >= matrix[0].length) {
            return true;
        }
        return false;
    }
}