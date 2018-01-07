// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        String a = "abc";
        String b = "adc";
        char ans = ' ';
        
        // 法1: 用HashSet, Space: O(n)
        /*
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < a.length(); i++) {
            set.add(a.charAt(i));
        }
        
        for (int i = 0; i < b.length(); i++) {
            if (!set.contains(b.charAt(i))) {
                ans = b.charAt(i);
            }
        }
        */
        // 法2: 相同數字XOR為0, 不同不為0. 把char --> ascii
        for (int i = 0; i < a.length(); i++) {
            if ( ((int)a.charAt(i) ^ (int)b.charAt(i)) != 0 ) {
                ans = b.charAt(i);
            }
            
        }
        System.out.println(ans);
    }
}

// 題目2: 如果是剛好多一個Char
// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        String a = "adb";
        String b = "acdb";
        char ans = ' ';
        
        // 法1: 用HashSet, Space: O(n)
        /*
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < a.length(); i++) {
            set.add(a.charAt(i));
        }
        
        for (int i = 0; i < b.length(); i++) {
            if (!set.contains(b.charAt(i))) {
                ans = b.charAt(i);
            }
        }
        */
        // 法3. 直接數出現次數, 先數a遇到就+1, 再數b遇到-1. 最後掃一次map, 不等於0就是不同
        int[] map = new int[256];
        for (int i = 0; i < a.length(); i++) {
            map[a.charAt(i)] += 1;
        }
        for (int i = 0; i < b.length(); i++) {
            map[b.charAt(i)] -= 1;
        }
        for (int i = 0; i < 256; i++) {
            if (map[i] != 0) {
                ans = (char)i;
            }
        }
        System.out.println(ans);
    }
}