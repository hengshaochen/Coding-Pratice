// 正確版
/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */


public class Solution {
    /*
     * @param root: The root of the binary search tree.
     * @param A: A TreeNode in a Binary.
     * @param B: A TreeNode in a Binary.
     * @return: Return the least common ancestor(LCA) of the two nodes.
     */
     boolean isCousin = false;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode A, TreeNode B) {
        helper(root, A, B, 0);
        System.out.println(isCousin);
        return root;
    }
    // helper定義：回傳root中的target的深度
    // 如果深度相等, 不同parents就是cousins
    int helper(TreeNode root, TreeNode x, TreeNode y, int depth) {
        // 1. base case
        if (root == null) {
            return -1;
        }
        // 找到target, 回傳target的深度
        if (root == x || root == y) {
            return depth;
        }
        
        // Step1: root要怎麼分派工作給兒子, 並期望從兒子深上拿回什麼
        int left_depth = helper(root.left, x, y, depth + 1);
        int right_depth = helper(root.right, x, y, depth + 1);
        
        
        // Step2: 拿到兒子給他的資訊後要做什麼：判斷是否為cousin
        // 左右在同一層 且 不是同個父親(depth代表父親的層數)
        // left_depth - 1 > depth 代表left_depth的點和父親隔的層數>1
        if (left_depth == right_depth && left_depth - 1 > depth) {
            isCousin = true;
        }
    
        // Step3: 要回傳什麼給父親：target的深度
        // 如果找到target, 回傳target的深度
        if (left_depth > right_depth) {
            return left_depth;
        }
        return right_depth;
    }

}

///////////////////////////////////////////
// 以下版本有BUG:

/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */


public class Solution {
    /*
     * @param root: The root of the binary search tree.
     * @param A: A TreeNode in a Binary.
     * @param B: A TreeNode in a Binary.
     * @return: Return the least common ancestor(LCA) of the two nodes.
     */
     boolean isCousin = false;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode A, TreeNode B) {
	    helper(root, A, B, 0);
	    System.out.println(isCousin);
	    return root;
    }
    // helper定義：回傳root中的target的深度
    // 如果深度相等, 不同parents就是cousins
    int helper(TreeNode root, TreeNode x, TreeNode y, int depth) {
    	// 1. base case
    	// leaf
    	if (root.left == null && root.right == null) {
    		return depth;
    	}
    	// 
    	if (root == x || root == y) {
    		return depth;
        }
    	
    	int left_depth = helper(root.left, x, y, depth + 1);
    	int right_depth = helper(root.right, x, y, depth + 1);

        
        // 深度相等, 然後左右子樹都為target --> 不是cousin
    	if (left_depth == right_depth && ((root.left == x && root.right == y) || (root.left == y && root.right == x))) {
    		isCousin = false;
        }
        // 深度不同,
        
        
        // 如果找到target, 回傳target的值
        if (left_depth >= 0) {
        	return left_depth;
        }
        if (right_depth >= 0) {
        	return right_depth;
        }
        return -1;
    }

}