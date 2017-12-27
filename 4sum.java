class Solution {
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            threeSum(i, nums, target, nums[i]);
        }
        return ans;
    }
    
    void threeSum(int start, int[] nums, int target, int init_val) {
        for (int i = start + 1; i < nums.length - 2; i++) {
            if (i > start + 1 && nums[i] == nums[i - 1]) {
                continue;
            }
            int sum = init_val + nums[i];
            
            // two sum
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                if (nums[left] + nums[right] + sum == target) {
                    List<Integer> cur = new ArrayList<>();
                    cur.add(nums[start]);
                    cur.add(nums[i]);
                    cur.add(nums[left]);
                    cur.add(nums[right]);
                    ans.add(cur);
                    left++;
                    right--;
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                } else if (nums[left] + nums[right] + sum > target) {
                    right--;
                } else {
                    left++;
                }
            }
        }
    }
}