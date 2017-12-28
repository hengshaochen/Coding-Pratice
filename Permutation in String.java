// TLE
class Solution {
    List<String> string_set;
    public boolean checkInclusion(String s1, String s2) {
        // 先用DFS窮舉所有s1的排列存到string_set中
        char[] s1_char = s1.toCharArray();
        Arrays.sort(s1_char);
        boolean[] visited = new boolean[s1.length()];
        
        string_set = new ArrayList<>();
        dfs(s1_char, 0, "", visited);
        
        // 從string_set比較是否有s2的substring
        for (int i = 0; i < string_set.size(); i++) {
            if (s2.contains(string_set.get(i))) {
                System.out.println(string_set.get(i));
                return true;
            }
        }
        return false;
    }
    
    void dfs(char[] s1_char, int start, String cur, boolean[] visited) {
        if (cur.length() == s1_char.length) {
            string_set.add(cur);
            return;
        }
        for (int i = 0; i < s1_char.length; i++) {
            if (visited[i]) {
                continue;
            }
            if (i >= 1 && visited[i - 1] == false && s1_char[i - 1] == s1_char[i]) {
                continue;
            }
            visited[i] = true;
            dfs(s1_char, i, cur + s1_char[i], visited);
            visited[i] = false;
        }
    }
    
}


// Sliding Window!*****O(n)
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        // corner case: s1長度比s2長
        if (s1.length() > s2.length()) {
            return false;
        }
        
        int[] map = new int[256];
        
        for (int i = 0; i < s1.length(); i++) {
            map[s1.charAt(i) - '0'] += 1;
            map[s2.charAt(i) - '0'] -= 1;
        }
        if (allEqual(map)) {
            return true;
        }
        
        for (int i = 0; i < s2.length() - s1.length(); i++) {
            // 去掉i位置的
            map[s2.charAt(i) - '0'] += 1;
            map[s2.charAt(i + s1.length()) - '0'] -= 1;
            if (allEqual(map)) {
                return true;
            }
        }
        return false;
    }
    
    boolean allEqual(int[] map) {
        for (int i = 0; i < 256; i++) {
            if (map[i] != 0) {
                return false;
            }
        }
        return true;
    }
}