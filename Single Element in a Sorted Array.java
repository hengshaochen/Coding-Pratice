// 解法1: O(n)
class Solution {
    public int singleNonDuplicate(int[] nums) {
        for (int i = 0; i < nums.length - 1; i += 2) {
            if (nums[i] != nums[i + 1]) {
                return nums[i];
            }
        }
        // 若前面都沒找到一定是在最後一個烙單
        return nums[nums.length  - 1];
    }
}

// 解法2: O(logn)
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            // ***如果mid在偶數, i*在mid + 1
            int i_star = mid % 2 == 0 ? mid + 1 : mid - 1;
            
            // 相等往右找, 不相等往左找
            if (nums[mid] == nums[i_star]) {
                start = mid;
            } else {
                end = mid;
            }
        }
        // 烙單的數字一定在偶數位置上, 然後binary search結束後start, end必定差一, 一定有一個是烙單的
        if (start % 2 == 0) {
            return nums[start];
        }
        return nums[end];
    }
}