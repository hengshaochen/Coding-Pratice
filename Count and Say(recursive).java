class Solution {
    public String countAndSay(int n) {
        // base case
        if (n == 1) {
            return "1";
        }
        
        // Step1
        String pre = countAndSay(n - 1);
        
        // Step2 
        StringBuilder cur = new StringBuilder();
        int count = 1;
        for (int i = 0; i < pre.length(); i++) {
            if ( i < pre.length() - 1 && pre.charAt(i) == pre.charAt(i + 1)) {
                count++;
            } else {
                // 若不相等 或是到尾巴 --> append
                cur.append(count);
                cur.append(pre.charAt(i));
                count = 1;
            }
        }
        
        // Step3
        return cur.toString();
    }
}