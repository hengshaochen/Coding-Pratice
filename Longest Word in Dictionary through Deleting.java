class Solution {
    public String findLongestWord(String s, List<String> d) {
        // 掃字典中每個字, 針對每個字確認 1.是否合法 2.若合法則跟當前最長的做比較（用一個String存當前最長合法的）
        
        String ans = "";
        for (int i = 0; i < d.size(); i++) {
            String cur = d.get(i);
            
            // 1.檢查合法: 2ptr, sPtr指向s, dPtr指向d, 若dPtr移動到d.length就代表合法, 若sPtr已經移到尾, dPtr還沒移到尾則不合法
            int sPtr = 0;
            int dPtr = 0;
            boolean valid = false;
            while (sPtr < s.length()) {
                if (s.charAt(sPtr) == cur.charAt(dPtr)) {
                    dPtr++;
                }
                sPtr++;
                if (dPtr == cur.length()) {
                    valid = true;
                    break;
                }
            }
            
            // 2. 和目前的最佳值做比較, 先比長度, 若長度相同選字母序小的
            if (valid == true) {
                if (cur.length() > ans.length()) {
                    ans = cur;
                } else if (cur.length() == ans.length()) {
                    // 長度相同, 選字母序小的
                    // 如果 < 0 代表cur字母序會比ans小, 因此更新ans. 例如 cur = 'a' ans = 'b' , 把ans更新為a
                    if (cur.compareTo(ans) < 0) {
                        ans = cur;
                    }
                }
            }
        }
        return ans;
    }
}