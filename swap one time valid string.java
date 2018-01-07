// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        // 给两个单词，问只swap一次后是不是一样。follow up 跟利特口 刘齐刘 有点像。
        String a = "apple";
        // String b = "aeplp"; // swap一次可以變apple
        String b = "papel";
        
        // 算法：只能swap一次字元, 因此我們把a不變, 同時掃a和b, 紀錄第一個不同的位置, 掃到第二個不同時, swap並比較
        // 算法：掃到第二個以上不同就return false
        int count = 0;
        boolean valid = true;
        for (int i = 0; i < a.length(); i++) {
            if (count == 2 && a.charAt(i) != b.charAt(i)) {
                valid = false;
                break;
            }
            if (a.charAt(i) != b.charAt(i)) {
                count++;
            }
        }
        if (valid) {
            System.out.println("Valid");
        } else {
            System.out.println("NOT Valid");
        }
    }
}