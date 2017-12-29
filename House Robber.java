class Solution {
    public int rob(int[] num) {
        int[][] dp = new int[num.length + 1][2];
        // dp[i][1] means we rob the current house and dp[i][0] means we don’t,
        for (int i = 1; i <= num.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = num[i - 1] + dp[i - 1][0];
        }
        return Math.max(dp[num.length][0], dp[num.length][1]);
    }
}