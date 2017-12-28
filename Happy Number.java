class Solution {
    public boolean isHappy(int n) {
        // Happy number: 分解一直下去會有1, 沒有1就不是
        // 思路：用set來存分解的結果, 若有重複出現的數字代表進入loop, 不用在繼續算下去. 最後判斷是否有1
        HashSet<Integer> set = new HashSet<>();
        while (!set.contains(n)) {
            set.add(n);
            int sum = 0;
            while (n > 0) {
                sum = sum + (n % 10) * (n % 10);
                n = n / 10;
            }
            n = sum;
        }
        if (set.contains(1)) {
            return true;
        } else {
            return false;
        }
    }
}