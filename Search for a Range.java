class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] ans = new int[2];
        if (nums.length == 0) {
            ans[0] = -1;
            ans[1] = -1;
            return ans;
        }
        
        // 找左邊
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                end = mid;
            } else if (nums[mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        
        if (nums[start] == target) {
            ans[0] = start;
        } else if (nums[end] == target){
            ans[0] = end;
        } else {
            ans[0] = -1;
        }
        
        // 找右邊
        start = 0;
        end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                start = mid;
            } else if (nums[mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        
        if (nums[end] == target) {
            ans[1] = end;
        } else if (nums[start] == target){
            ans[1] = start;
        } else {
            ans[1] = -1;
        }
        
        return ans;
    }
}

// recursive
class Solution {
    int[] ans = new int[2];
    public int[] searchRange(int[] nums, int target) {
        ans[0] = nums.length;
        ans[1] = -1;
        bs(nums, target, 0, nums.length - 1);
        
        if (ans[0] > ans[1]) {
            ans[0] = -1;
        }
        return ans;
    }
    void bs(int[] nums, int target, int start, int end) {
        if (start > end) {
            return;
        }
        int mid = start + (end - start) / 2;
        
        if (nums[mid] == target) {
                System.out.println(ans[0]);
            // 往左找
            if (mid < ans[0]) {
                ans[0] = mid;
                bs(nums, target, start, mid - 1);
            }
            // 往右找
            if (mid > ans[1]) {
                ans[1] = mid;
                bs(nums, target, mid + 1, end);
            }
            
        } else if (nums[mid] > target) {
            bs(nums, target, start, mid - 1);
        } else {
            bs(nums, target, mid + 1, end);
        }
        
    }
}