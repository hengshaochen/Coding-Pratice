// "static void main" must be defined in a public class.
public class Solution {
    HashMap<String, HashMap<String, Double>> map = new HashMap<>();
    public static void main(String[] args) {
        
        /*
        A B 0.5
        B C 0.6
        C D 0.7
        B D 0.2
        */
        
        
        new Solution();
    }
    public Solution() {
        setRatio("A", "B", 0.5);
        setRatio("B", "C", 0.6);
        setRatio("C", "D", 0.7);
        // setRatio("B", "E", 0.2);
        
        //System.out.println( map.get("B").keySet() );
        
        System.out.println(getRatio("A", "D"));
    }
    void setRatio(String x, String y, double ratio) {
        if (!map.containsKey(x)) {
            HashMap<String, Double> cur = new HashMap<>();
            cur.put(y, ratio);
            map.put(x, cur);
        } else {
            HashMap<String, Double> cur = map.get(x);
            cur.put(y, ratio);
        }
    }
    double getRatio(String x, String y) {
        // base case , 如果相等 --> 找到結果 | 如果走到底沒有y
        if (x.compareTo(y) == 0 || map.get(x) == null) {
            return 1;
        }
        
        double ans = 1;
        for (String cur : map.get(x).keySet()) {
            System.out.println(x + " " + y);
            ans = ans * getRatio(cur, y) * map.get(x).get(cur);
        }
        return ans;
    }
}