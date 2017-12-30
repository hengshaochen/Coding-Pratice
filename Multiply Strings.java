class Solution {
    public String multiply(String num1, String num2) {
        // ex: 120 * 93 = 11160 / num1:120, num2: 93
        int[] product = new int[num1.length() + num2.length()];
        
        for (int i = num2.length() - 1; i >= 0; i--) {
            for (int j = num1.length() - 1; j >= 0; j--) {
                int cur = product[i + j + 1] + ((num2.charAt(i) - '0') * (num1.charAt(j) - '0'));
                
                // 原本位數有的數字 % 10 = 當位的數字
                product[i + j + 1] = cur % 10;
                
                // 這個是carry, 原本已經位於carry的數字 再加上 新過來的carry / 10 = 新的carry
                product[i + j] = product[i + j ] + (cur / 10);
            }
        }
        
        // 輸出答案
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < product.length; i++) {
            // 去除leading zero, 可能是3*2 = 06 或是9133*0 = 0000這種情況
            if (sb.length() == 0 && product[i] == 0) {
                continue;
            }
            sb.append(product[i]);
        }
        if (sb.length() == 0) {
            return "0";
        }
        return sb.toString();
    }
}