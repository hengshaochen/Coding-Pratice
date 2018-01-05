/*
Basic idea: each time we take a look at the last four digits of
            binary verion of the input, and maps that to a hex char
            shift the input to the right by 4 bits, do it again
            until input becomes 0.

*/

public class Solution {
    
    char[] map = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
    
    public String toHex(int num) {
        if(num == 0) return "0";
        String result = "";
        while(num != 0){
            System.out.println(map[(num & 15)]);
            result = map[(num & 15)] + result; 
            // >>>為無符號右移. >>為有符號右移。例如，如果要移走的值为负数，每一次右移都在左边补1，如果要移走的值为正数，每一次右移都在左边补0，这叫做                符号位扩展（保留符号位）（sign extension ），在进行右移 
            num = (num >>> 4);
        }
        return result;
    }
}