// good solution
class Solution {
    public int myAtoi(String str) {
        if (str == null || str.length() == 0)
            return 0;
        
        // 去除leading空格
        str = str.trim();
        
        // 預設sign為正, 因為如果不帶正負號就直接是正, 判斷正負號
        int sign = 1;
        char firstChar = str.charAt(0);
        int start = 0, len = str.length();
        long sum = 0;
        
        // 判斷正負號： +35, -35 也有可能是35不帶正負號
        if (firstChar == '+') {
            sign = 1;
            start++;
        } else if (firstChar == '-') {
            sign = -1;
            start++;
        }
        
        for (int i = start; i < len; i++) {
            // 若遇到特殊符號則直接return目前算到的值, 例如35a66 --> ans: 35
            if (!Character.isDigit(str.charAt(i))) {
                return (int) sum * sign;
            }
            
            // 正常計算
            sum = sum * 10 + str.charAt(i) - '0';
            
            // 處理overflow
            if (sign == 1 && sum > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (sign == -1 && (-1) * sum < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
        }

        return (int) sum * sign;
    }
}



// my solution accepted but not good
class Solution {
    public int myAtoi(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        
        // 去除leading空格 例如"   34 5556" --> "34 5556" ---> ans: 34
        str = str.trim();
        
        // 判斷負號
        int start = 0;
        if (str.charAt(0) == '-') {
            start = 1;
        }
        
        long number = 0;
        for (int i = start; i < str.length(); i++) {
            // 如果第一個char是 + , 例如+35 --> 35
            if (i == 0 && str.charAt(0) == '+') {
                continue;
            }
            
            // 如果有出現非數字的後面都不用看就都是0 --> 133a567 --> 133
            if (!Character.isDigit(str.charAt(i))) {
                // 負號
                if (start == 1) {
                    return (int) ((-1) * number);
                }
                return (int)number;
            }
            
            number *= 10;
            number = number + (str.charAt(i) - '0');
            
            // overflow: 照理說pre要比number小, 但若pre > number代表overflow了
            if (start == 0 && number > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (start == 1 && number * (-1) < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
        }
        
        // 負號
        if (start == 1) {
            return (int) ((-1) * number);
        }
        return (int)number;
    }
}