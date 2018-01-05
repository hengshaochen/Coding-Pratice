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
    int ans = 0;
    public List<Integer> inorderTraversal(TreeNode root) {
        pre(root);
        System.out.println(ans);
        return new ArrayList<>();
    }
    public void pre(TreeNode root) {
        if (root == null) {
            return ;
        }
        ans++;
        pre(root.left);
        pre(root.right);
    }
}