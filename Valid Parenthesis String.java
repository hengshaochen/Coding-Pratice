class Solution {
    int[][] dp;
    public boolean checkValidString(String s) {
        int len = s.length();
        dp = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                dp[i][j] = -1;
            }
        }
        
        if (isValid(s, 0, len - 1) == 1) {
            return true;
        }
        return false;
    }
    // isValid == 1 代表有解, isValid == 0 代表無解
    // dp[i][j] == 1 代表有解, dp[i][j] == 0 代表無解, dp[i][j] == -1 代表還沒計算到這個子問題
    int isValid(String s, int i, int j) {
        // base case
        if (i > j) {
            return 1;
        }
        // 已經計算過了
        if (dp[i][j] >= 0) {
            return dp[i][j];
        }
        // 字串長度為1時, *號才有解, 只有星號可以用empty來匹配
        if (i == j) {
            if (s.charAt(i) == '*') {
                dp[i][j] = 1;
                return dp[i][j];
            }
        }
        
        // Case1
        if ( (s.charAt(i) == '(' || s.charAt(i) == '*') &&
             (s.charAt(j) == ')' || s.charAt(j) == '*') &&
             isValid(s, i + 1, j - 1) == 1)  // 遞迴往裡面( () )求解
        {
            dp[i][j] = 1;
            return dp[i][j];
        }
        
        // Case2: 拆分
        for (int p = i; p < j; ++p) {
            if (isValid(s, i, p) == 1 && isValid(s, p + 1, j ) == 1) {
                dp[i][j] = 1;
                return dp[i][j];
            }
        }
        
        // 無解的情況
        dp[i][j] = 0;
        return dp[i][j];
    }
}