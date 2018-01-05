class Solution {
    class Coord {
        int x, y;
        Coord (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new ArrayList<>();
        }
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        // direction 0 1 2 3 右 下 左 上
        List<Integer> ans = new ArrayList<>();
        DFS(new Coord(0, 0), 0, matrix, visited, 0, ans);
        return ans;
    }
    
    public void DFS(Coord cur, int direction, int[][] matrix, boolean[][] visited, int count, List<Integer> ans) {
        if (count == matrix.length * matrix[0].length) {
            return;
        }
        
        // 新增當前點 並標記為已走訪
        ans.add(matrix[cur.x][cur.y]);
        visited[cur.x][cur.y] = true;
        count++;
        
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        
        // 下個點
        Coord next = new Coord(cur.x + dx[direction], cur.y + dy[direction]);
        
        if (outBoundary(next, matrix) || visited[next.x][next.y]) {
            // 如果碰到走過的, 方向 + 1 或 如果撞牆, 方向 + 1 繼續用DFS走
            int new_direction = (direction + 1) % 4;
            Coord modify_next = new Coord(cur.x + dx[new_direction], cur.y + dy[new_direction]);
            DFS(modify_next, new_direction, matrix, visited, count++, ans);
        } else {
            // 如果沒有撞牆或是遇到走過的, 同個方向走
            DFS(next, direction % 4, matrix, visited, count++, ans);
        }
    }
    
    public boolean outBoundary(Coord cur, int[][] matrix) {
        if (cur.x < 0 || cur.x >= matrix.length || cur.y < 0 || cur.y >= matrix[0].length) {
            return true;
        }
        return false;
    }
}