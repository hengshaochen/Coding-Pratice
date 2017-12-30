// "static void main" must be defined in a public class.
public class Solution {
    public static void main(String[] args) {
        new Solution();
    }
    boolean isCircle = false;
   // List<String[]> permu = new ArrayList<>();
    public Solution() {
        
        String[] arr =  {"aaa", "abb", "baa"};
        List<String> cur = new ArrayList<>();
        HashSet<String> set = new HashSet<>();
        dfs(arr, cur, 0, set);
        
        if (isCircle)
           System.out.println("Yes");
        else
           System.out.println("No");
    }
    
    void dfs(String[] arr, List<String> cur, int cur_len, HashSet<String> set) {
        // base case
        if (isCircle == true) {
            return;
        }
        if (cur_len == arr.length) {
            if (satisfy(cur)) {
                //permu.add(cur);
                System.out.println(cur);
                isCircle = true;
            }
            return;
        }
        
        for (int i = 0; i < arr.length; i++) {
            if (set.contains(arr[i])) {
                continue;
            }
            
            // 新增
            cur.add(arr[i]);
            set.add(arr[i]);
            
            dfs(arr, cur, cur_len + 1, set);
            
            // 回朔
            cur.remove(cur.size() - 1);
            set.remove(arr[i]);
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