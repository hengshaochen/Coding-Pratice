/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        return dfs(root, sum, 0) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }
    int dfs(TreeNode root, int sum, int cur) {
        if (root == null) {
            return 0;
        }
        
        // 1. 分派工作給兒子
        int left = dfs(root.left, sum, cur + root.val);
        int right = dfs(root.right, sum, cur + root.val);
        
        // 2. 當前root的工作: 判斷當前累積的是否 == sum 和 3. 回傳的東西
        return (cur + root.val == sum ? 1 : 0) + left + right;
    }
}