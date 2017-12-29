// 法1, 用string, 每次string +都是O(N), 但程式碼較簡單
// "static void main" must be defined in a public class.
public class Solution {
    List<String> ans = new ArrayList<>();
    public static void main(String[] args) {
        new Solution();
    }
    public Solution() {
        String str = "abc";
            
        dfs("", 0, str);
        System.out.println(ans);
    }

    void dfs(String cur, int start, String str) {
        // exit 
        if (start >= str.length() - 1) {
            ans.add(cur + str.charAt(start));
            return;
        }
        dfs(cur + str.charAt(start) + "_", start + 1, str);
        dfs(cur + str.charAt(start), start + 1, str);
    }

}

// 法2, 用stringbuilder, 效率較好, 但程式碼複雜一些
// "static void main" must be defined in a public class.
public class Solution {
    List<String> ans = new ArrayList<>();
    public static void main(String[] args) {
        new Solution();
    }
    public Solution() {
        String str = "abc";
        StringBuilder sb = new StringBuilder("");
            
	    dfs(sb, 0, str);
	    System.out.println(ans);
    }

    void dfs(StringBuilder cur, int start, String str) {
        // exit 
        if (start >= str.length() - 1) {
            cur.append(str.charAt(start));
            ans.add(new String(cur));
            cur.deleteCharAt(cur.length() - 1);
            return;
        }
        cur.append(str.charAt(start));
        cur.append("_");
        dfs(cur, start + 1, str);
        cur.deleteCharAt(cur.length() - 1);
        dfs(cur, start + 1, str);
        cur.deleteCharAt(cur.length() - 1);


    }

}