// "static void main" must be defined in a public class.
public class Solution {
    public static void main(String[] args) {
        new Solution();
    }
    boolean isCircle = false;
   // List<String[]> permu = new ArrayList<>();
    public Solution() {
        
        String[] arr =  {"aa", "ac", "ca", "aa"};
        Arrays.sort(arr);
        
        List<String> cur = new ArrayList<>();
        boolean[] visited = new boolean[arr.length];
        
        dfs(arr, cur, 0, visited);
        
        if (isCircle)
           System.out.println("Yes");
        else
           System.out.println("No");
    }
    
    void dfs(String[] arr, List<String> cur, int cur_len, boolean[] visited) {
        // base case
        // System.out.println(cur);
        if (isCircle == true) {
            return;
        }
        if (cur_len == arr.length) {
            if (satisfy(cur)) {
                isCircle = true;
            }
            return;
        }
        
        for (int i = 0; i < arr.length; i++) {
            if (visited[i]) {
                continue;
            }
            
            if (i > 0 && arr[i].equals(arr[i - 1]) && visited[i - 1] == false) {
                continue;
            }
            
            // 新增
            cur.add(arr[i]);
            visited[i] = true;
            
            dfs(arr, cur, cur_len + 1, visited);
            
            // 回朔
            cur.remove(cur.size() - 1);
            visited[i] = false;
        }
    }
    
    boolean satisfy(List<String> cur) {
        for (int i = 0; i < cur.size() - 1; i++) {
            if (cur.get(i).charAt(cur.get(i).length() - 1) != cur.get(i + 1).charAt(0)) {
                return false;
            }
        }
        // 最後一個的最後一個元素, 跟第一個的第一個元素比較
        if (cur.get(cur.size() - 1).charAt(cur.get(cur.size() - 1).length() - 1) != cur.get(0).charAt(0)) {
            return false;
        }
        return true;
    }
}