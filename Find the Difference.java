class Solution {
    public char findTheDifference(String s, String t) {
        int[] map = new int[256];
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i)] += 1;
        }
        for (int i = 0; i < t.length(); i++) {
            map[t.charAt(i)] -= 1;
        }
        for (int i = 0; i < 256; i++) {
            if (map[i] != 0) {
                return (char) i;
            }
        }
        return 0;
    }
}

// 26的版本, 概念就是把a的ASCII本來是97, 變成存在map[0], b是98, 存在map[1], 因此最後答案a要再加回來, 轉成char才是答案
class Solution {
    public char findTheDifference(String s, String t) {
        int[] map = new int[26];
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i) - 'a'] += 1;
        }
        for (int i = 0; i < t.length(); i++) {
            map[t.charAt(i) - 'a'] -= 1;
        }
        for (int i = 0; i < 26; i++) {
            if (map[i] != 0) {
                return (char) (i + 'a');
            }
        }
        return 0;
    }
}