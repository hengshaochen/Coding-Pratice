// Botton-Up
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
    class Result {
        int child_node;
        int path;
        Result(int child_node, int path) {
            this.child_node = child_node;
            this.path = path;
        }
    }
    
    int ans = 0;
    public int longestConsecutive(TreeNode root) {
        if (root == null) {
            return ans;
        }
        
        helper(root);
        return ans;
    }
    
    Result helper(TreeNode root) {
        // base case
        if (root == null) {
            return new Result(-1, 0);
        }
        if (root.left == null && root.right == null) {
            return new Result(root.val, 1);
        }
        
        // 1. 要怎麼分派任務給兒子 並要從兒子身上拿到什麼
        // 拿到兒子的node以及兒子現在累積的path長度
        Result left = helper(root.left);
        Result right = helper(root.right);
        
        // 2. 拿到兒子給你的東西後要做什麼
        // 算longest path: 判斷當前層是否能跟兒子層連接起來
        int left_path = root.val == left.child_node - 1 ? left.path + 1 : 1;
        int right_path = root.val == right.child_node - 1 ? right.path + 1 : 1;
        
        int current_longest_path = Math.max(left_path, right_path);
        ans = Math.max(ans, current_longest_path);
        
        // 3. 回傳什麼回去
        // 回傳目前累積的path長度
        return new Result(root.val, current_longest_path);
    }
}

// 2.Top-down
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
    int max = 1;
    public int longestConsecutive(TreeNode root) {
        if (root == null) {
            return 0;
        }
        helper(root, 1, root.val + 1);
        return max;
    }
    void helper(TreeNode root, int path, int parent_val) {
        if (root == null) {
            return;
        }
        if (parent_val == root.val) {
            path += 1;
        } else {
            path = 1;
        }
        max = Math.max(max, path);
        
        helper(root.left, path, root.val + 1);
        helper(root.right, path, root.val + 1);
    }
    
}