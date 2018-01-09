// "static void main" must be defined in a public class.
public class Solution {
    public static void main(String[] args) {
        new Solution();
    }
    public Solution() {
        int low = 1;
        int high = 650;
        
        Set<Integer> set = new HashSet<>();
        set.add(0);
        set.add(1);
        set.add(6);
        set.add(8);
        set.add(9);
        Set<Integer> ans = new TreeSet<>();
        for (int i = low; i < high; i++) {
            // 分離數字
            int ori = i;
            StringBuilder cur = new StringBuilder();
            while(ori > 0) {
                cur.append(ori % 10);
                ori /= 10;
            }
            cur.reverse();
            
            // check all digit is 0,1,6,8,9
            if (!allDigit_inSet(set, cur)) {
                continue;
            }
            
            // get upside down
            //System.out.println(cur);
            int[] mapping = {0, 1, -1, -1, -1, -1, 9, -1, 8, 6};
            StringBuilder cur_upside_down = new StringBuilder();
            for (int j = 0; j < cur.length(); j++) {
                //System.out.println( (int)cur.charAt(j) );
                cur_upside_down.append(mapping[cur.charAt(j) - 48]);
            }
            cur_upside_down.reverse();
            
            // compare cur and upside_down, if not equal then add to ans
            // if upside_down have leading zero don't all
            if (cur_upside_down.charAt(0) == '0') {
                continue;
            }
            // if upside_down greater than high
            if (Integer.parseInt( cur_upside_down.toString() ) > high) {
                continue;
            }
            
            for (int j = 0; j < cur.length(); j++) {
                if (cur.charAt(j) != cur_upside_down.charAt(j)) {
                    ans.add(Integer.parseInt(cur.toString()));
                }
            }
        }
        System.out.println(ans);
    }
    boolean allDigit_inSet(Set<Integer> set, StringBuilder cur) {
        for (int j = 0; j < cur.length(); j++) {
                if (!set.contains(cur.charAt(j) - 48)) {
                    return false;
                }
        }
        return true;
    }
}