class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        
        // 特殊初始化, 要讓s = aaa和p = c*aab 這種c*可以先消掉, 然後比s = aaa 和 p = aab, 這時dp[0][2] 要為true
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*' && dp[0][i - 1] == true) {
                dp[0][i + 1] = true;
            }
        }
        
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                // Case1
                if (p.charAt(j) == s.charAt(i)) {
                    dp[i + 1][j + 1] = dp[i][j];
                }
                // Case2
                if (p.charAt(j) == '.') {
                    dp[i + 1][j + 1] = dp[i][j];
                }
                // Case3 又拆成兩種子情況
                if (p.charAt(j) == '*') {
                    // 子情況1: s = baab, p = bc*aab, 可以把c*刪除, 變成s = baab 和 p = baab比較
                    if (p.charAt(j - 1) != s.charAt(i) && p.charAt(j - 1) != '.') {
                        dp[i + 1][j + 1] = dp[i + 1][j - 1];
                    }
                    // 子情況2: 分成三種可能, s = aa, p = a* , 可以把a*的*當成1個, 多個, 或是抵銷來用
                    else {
                        dp[i + 1][j + 1] = dp[i + 1][j] || dp[i][ j + 1] || dp[i + 1][j - 1];
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }
}