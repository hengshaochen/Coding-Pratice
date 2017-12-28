
        Stack<TreeNode> left = new Stack<>();
        Stack<TreeNode> right = new Stack<>();
        int[] ans = new int[2];
        
        
        // 塞滿左stack, 右stack
        TreeNode cur = root;
        while (cur != null) {
            left.push(cur);
            cur = cur.left;
        }
        cur = root;
        while (cur != null) {
            right.push(cur);
            cur = cur.right;
        }
        
        // 開始做sorted版本的two sum
        while (!left.isEmpty() && !right.isEmpty() && left.peek() != right.peek()) {
            int sum = left.peek().val + right.peek().val;
            
            if (sum == n) {
                ans[0] = left.peek().val;
                ans[1] = right.peek().val;
                return ans;
            } else if (sum < n) {
                // sum要放大，注意這邊要先往右子樹走, 因為右子樹有較大的值
                TreeNode left_cur = left.pop().right;
                while (left_cur != null) {
                    left.push(left_cur);
                    left_cur = left_cur.left;
                }
            } else {
                // sum要縮小，要先往左子樹走，佐子樹有較小的值
                TreeNode right_cur = right.pop().left;
                while (right_cur != null) {
                    right.push(right_cur);
                    right_cur = right_cur.right;
                }
            }
        }
        return null;