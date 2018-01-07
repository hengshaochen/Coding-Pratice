class Solution {
    int[][] dp;
    public int minDistance(String word1, String word2) {
        dp = new int[word1.length() + 1][word2.length() + 1];
        // 初始化2d array為-1, 代表還沒求解過
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        
        return helper(word1, word2, word1.length(), word2.length());
    }
    public int helper(String word1, String word2, int len1, int len2) {
        // base case
        if (len1 == 0) {
            return len2;
        }
        if (len2 == 0) {
            return len1;
        }
        if (dp[len1][len2] != -1) {
            return dp[len1][len2];
        }
        
        int ans = 0;
        if (word1.charAt(len1 - 1) == word2.charAt(len2 - 1)) {
            ans = helper(word1, word2, len1 - 1, len2 - 1);
        } else {
            ans = 1 + Math.min( helper(word1, word2, len1 - 1, len2 - 1), 
                            Math.min(helper(word1, word2, len1 - 1, len2), helper(word1, word2, len1, len2 - 1)));
        }
        dp[len1][len2] = ans;
        return dp[len1][len2];
    }
    
}