// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        HashMap<Integer, Character> map = new HashMap<>();
        String convert_table = "0123456789abcdef";
        for (int i = 0; i < 16; i++) {
            map.put(i, convert_table.charAt(i));
        }
        // binary to hex
        String num = "1101";
        
        // 思路：二轉十接著十進位再轉十六進位
        int val = 0;
        for (int i = 0; i < num.length(); i++) {
            double cur_pow = num.length() - i - 1;
            val = val + (num.charAt(i) -'0') * (int)Math.pow((double)2, cur_pow);
        }
        System.out.println("10進位：" + val);
        
        StringBuilder sb = new StringBuilder();
        while (val != 0) {
            sb.append(map.get(val % 16));
            val = val / 16;
        }
        sb.reverse();
        System.out.println("16進位:" + sb);
    }
}