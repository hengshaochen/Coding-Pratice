// "static void main" must be defined in a public class.
public class Solution {
    class Coord {
        int x, y;
        Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
        int[][] grid = {{0, 1, 0, 0, 1},
                        {0, 1, 0, 1, 0},
                        {0, 0, 0, 0, 0}};
    
    public static void main(String[] args) {
        new Solution();
    }
    public Solution() {
        
        int grid_size = 10;
        
        HashSet<String> set = new HashSet<>();
        System.out.println("1,4");
        set.add("1,4");
        DFS(new Coord(1, 4), set, grid_size);
    }
    
    public void DFS(Coord cur, HashSet<String> set, int grid_size) {
        if (set.size() == grid_size) {
            return;
        }
        
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        
        for (int i = 0; i < 4; i++) {
            Coord next = new Coord(cur.x + dx[i], cur.y + dy[i]);
            if (outBoundary(next) || set.contains(next.x + "," + next.y)
                || grid[next.x][next.y] == 1) {
                continue;
            }
            System.out.println(next.x + " " + next.y);
            set.add(next.x + "," + next.y);
            DFS(next, set, grid_size);
        }
    }
    
    public boolean outBoundary(Coord cur) {
        if (cur.x < 0 || cur.x >= grid.length || cur.y < 0 || cur.y >= grid[0].length) {
            return true;
        }
        return false;
    }
}

// Stack版本
// "static void main" must be defined in a public class.
public class Solution {
    class Coord {
        int x, y;
        Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
        int[][] grid = {{0, 1, 0, 0, 1},
                        {0, 1, 0, 1, 0},
                        {0, 0, 0, 0, 0}};
    
    public static void main(String[] args) {
        new Solution();
    }
    public Solution() {
        
        int grid_size = 10;
        
        HashSet<String> set = new HashSet<>();
        System.out.println("1,4");
        set.add("1,4");
        DFS(new Coord(1, 4), set, grid_size);
    }
    
    public void DFS(Coord cur, HashSet<String> set, int grid_size) {
        if (set.size() == grid_size) {
            return;
        }
        
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        
        Stack<Coord> stack = new Stack<>();
        stack.push(cur);
        
        while (!stack.isEmpty()) {
            cur = stack.pop();
            for (int i = 0; i < 4; i++) {
                Coord next = new Coord(cur.x + dx[i], cur.y + dy[i]);
                if (outBoundary(next) || set.contains(next.x + "," + next.y)
                    || grid[next.x][next.y] == 1) {
                    continue;
                }
                System.out.println(next.x + "," + next.y);
                set.add(next.x + "," + next.y);
                stack.push(next);
            }
        }
    }
    
    public boolean outBoundary(Coord cur) {
        if (cur.x < 0 || cur.x >= grid.length || cur.y < 0 || cur.y >= grid[0].length) {
            return true;
        }
        return false;
    }
}