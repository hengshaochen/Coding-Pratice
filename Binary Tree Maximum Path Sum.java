// 簡化版
Step1: 切左右 左右告訴我當前max Step2: cur = 把我的val + 左右的max Step3: 回傳cur
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
    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // Step1
        int left = maxPathSum(root.left);
        int right = maxPathSum(root.right);
        
        // Step2:
        int cur = Math.max(left + root.val, right + root.val);
        
        // Step3:
        return cur;
    }
}

// Leetcode 124 從任何出發或結束都可以
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
    int ans = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        // 這題主要要注意兩點：和Longest Univalue Path很像, 因為最後答案要的跟當次Step3回傳的不同, 因此需要helper
        // 例如[2,1,3], 在2的時候, 答案是6, 但2如果有父親, 對他的父親要回傳5而不是6. 因為回傳上去不能走彎曲的, 只能走一條子問題最長的path
        if (root == null) {
            return 0;
        }
        helper(root);
        return ans;
    }
    int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // Step1
        int l = helper(root.left);
        int r = helper(root.right);
        
        // Step2:
        // a: 判斷左右有沒有為負數, 若為負數, 則寧願不要加, 因為負數再怎麼加都會比不加更小 --> 因此把它設置為0, 讓之後等於沒加
        if (l < 0) {
            l = 0;
        }
        if (r < 0) {
            r = 0;
        }
        
        // b: 左子樹max + 本身 + 右子樹max
        int cur = l + root.val + r;
        if (ans < cur) {
            ans = cur;
        }
        
        // Step3:
        return Math.max(l + root.val, r + root.val);
    }
}