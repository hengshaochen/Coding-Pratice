// 正解
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
    class result {
        int up = 0;
        int down = 0;
        result (int up, int down) {
            this.up = up;
            this.down = down;
        }
    }
    int ans = 0;
    public int longestConsecutive2(TreeNode root) {
        // down為從下往上遞增, up為從上往下遞增
        // 定義up為從下往上 小到大 / down為從上往下 大到小的連續序列長度
        // 1. 分派任務給兒子, 左右兒子要給我up, down, max_len
        // 2. (a) 透過我跟左右兒子的數值關係來更新up, down. 把當前root.val和兒子比較, 若連續,則針對up或down增加 若不連續, 則把up down歸0
        //    (b) 更新max_len --> up 和 down 組合再一起 + 1 <-- root本身
        // 左右子樹都會回傳up / down, 都選Max的
        // 3. 回傳max_len, up, down給父親
        dfs(root);
        return ans;
    }
    result dfs(TreeNode root) {
        if (root == null) {
            return new result(0, 0);
        }
        int up = 0;
        int down = 0;
        
        // Step1
        result left = dfs(root.left);
        result right = dfs(root.right);
        
        // Step2
        // a
        if (root.left != null && root.left.val + 1 == root.val) {
            down = Math.max(down, left.down + 1);
        }
        if (root.left != null && root.left.val - 1 == root.val) {
            up = Math.max(up, left.up + 1);
        }
        if (root.right != null && root.right.val + 1 == root.val) {
            down = Math.max(down, right.down + 1);
        }
        if (root.right != null && root.right.val - 1 == root.val) {
            up = Math.max(up, right.up + 1);
        }
        // b
        int cur_len = down + 1 + up;
        if (ans < cur_len) {
            ans = cur_len;
        }
        
        // Step3
        return new result(up, down);
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