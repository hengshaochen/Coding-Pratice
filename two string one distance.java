// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        // abc
        // abcd
        String s = "abc";
        String t = "abcd";
        int[] map = new int[256];
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i)] += 1;
        }
        for (int i = 0; i < t.length(); i++) {
            map[t.charAt(i)] -= 1;
        }
        for (int i = 0; i < 256; i++) {
            if (map[i] != 0) {
                System.out.println((char)i);
            }
        }
    }
}