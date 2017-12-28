/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class BSTIterator {
    Stack<TreeNode> s = new Stack<>();
    public BSTIterator(TreeNode root) {
        TreeNode cur = root;
        while (cur != null) {
            s.push(cur);
            cur = cur.left;
        }
        
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        if (!s.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode ans = s.pop();
        TreeNode cur = ans.right; 
        while (cur != null) {
            s.push(cur);
            cur = cur.left;
        }
        return ans.val;
    }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */