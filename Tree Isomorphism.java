// "static void main" must be defined in a public class.
public class Solution {
    class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) {
            this.val = val;
        }
    }
    public static void main(String[] args) {
        new Solution();
    }
    public Solution() {
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        root1.left.right = new TreeNode(5);
        root1.right.left = new TreeNode(6);
        root1.left.right.left = new TreeNode(7);
        root1.left.right.right = new TreeNode(8);
        
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(3);
        root2.right = new TreeNode(2);
        root2.left.right = new TreeNode(6);
        root2.right.left = new TreeNode(4);
        root2.right.right = new TreeNode(5);
        root2.right.right.left = new TreeNode(8);
        root2.right.right.right = new TreeNode(7);
        
        boolean ans = isIsomorphism(root1, root2);
        if (ans == true) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
    public boolean isIsomorphism(TreeNode root1, TreeNode root2) {
        
        // base case
        if (root1 == null && root2 == null) {
            return true;
        }
        
        // 其中一個是null, 一個不是null --> not isomorphism
        if (root1 == null ||root2 == null) {
            return false;
        }
        
        if (root1.val != root2.val) {
            return false;
        }
        
        // Step1
        boolean case1 = isIsomorphism(root1.left, root2.left) && isIsomorphism(root1.right, root2.right);
        boolean case2 = isIsomorphism(root1.left, root2.right) && isIsomorphism(root1.right, root2.left);
        
        // Step2 + Step3
        return case1 || case2;
    }
}