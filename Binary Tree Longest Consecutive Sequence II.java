// 正解
class ResultType {
    public int max_length, max_down, max_up;
    ResultType(int len, int down, int up) {
        max_length = len;
        max_down = down;
        max_up = up;    
    }
}

public class Solution {
    /**
     * @param root the root of binary tree
     * @return the length of the longest consecutive sequence path
     */
    public int longestConsecutive2(TreeNode root) {
        // Write your code here
        return helper(root).max_length;
    }
    
    ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(0, 0, 0);
        }

        ResultType left = helper(root.left);
        ResultType right = helper(root.right);

        int down = 0, up = 0;
        if (root.left != null && root.left.val + 1 == root.val)
            down = Math.max(down, left.max_down + 1);

        if (root.left != null && root.left.val - 1 == root.val)
            up = Math.max(up, left.max_up + 1);

        if (root.right != null && root.right.val + 1 == root.val)
            down = Math.max(down, right.max_down + 1);

        if (root.right != null && root.right.val - 1 == root.val)
            up = Math.max(up, right.max_up + 1);

        int len = down + 1 + up;
        len = Math.max(len, Math.max(left.max_length, right.max_length));

        return new ResultType(len, down, up);
    }
}



// 錯誤 有bug {2,#,3,2,#,1} 會出錯, 因為沒有分開判斷up, down
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */


public class Solution {
    /*
     * @param root: the root of binary tree
     * @return: the length of the longest consecutive sequence path
     */
     int max = 0;
    public int longestConsecutive2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        helper(root);
        return max;
    }
    
    
    int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // leaf node
        if (root.left == null && root.right == null) {
            return 1;
        }
        int left = helper(root.left);
        int right = helper(root.right);
        
        int left_plus_root = 1;
        int right_plus_root = 1;
        
        if (root.left != null && Math.abs(root.left.val - root.val) == 1) {
            left_plus_root = left + 1;
        }
        if (root.right != null && Math.abs(root.right.val - root.val) == 1) {
            right_plus_root = right + 1;
        }
        
        // 如果可以加上root接起來
        if (root.left != null && root.right != null) {
            if (Math.abs(root.left.val - root.val) == 1 && Math.abs(root.right.val - root.val) == 1 &&
                Math.abs(root.left.val - root.right.val) == 2) {
                max = Math.max(max, left_plus_root + right_plus_root - 1);        
            }
        } else {
            max = Math.max(max, left_plus_root);
            max = Math.max(max, right_plus_root);
        }        
        
        return Math.max(left_plus_root, right_plus_root);
    }
    
}