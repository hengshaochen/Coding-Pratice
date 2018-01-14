// "static void main" must be defined in a public class.
public class Solution {
    class Result {
        double ans;
        boolean find;
        Result(double ans, boolean find) {
            this.ans = ans;
            this.find = find;
        }
    }
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
        setRatio("B", "E", 0.2);
        
        //System.out.println( map.get("B").keySet() );
        Result result = getRatio("A", "H");
        if (result.find == true) {
            System.out.println(result.ans);
        } else {
            System.out.println("NOT FIND THE RELATIONSHIP!");
        }
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
    Result getRatio(String x, String y) {
        // base case , 如果相等 --> 找到結果 | 如果走到底沒有y
        if (x.compareTo(y) == 0) {
            return new Result(1, true);
        }
        if (map.get(x) == null) {
            return new Result(1, false);
        }
        
        
        Result subratio = new Result(1, false);
        for (String cur : map.get(x).keySet()) {
            //ans = ans * getRatio(cur, y) * map.get(x).get(cur);
            // Step1: 切子問題給兒子
            subratio = getRatio(cur, y);
            
            // Step2: 拿到兒子回傳的資訊要如何處理？
            if (subratio.find) {
                //subratio.find = true;
                //System.out.println(cur + " map.get(x).get(cur): " + subratio.ans * map.get(x).get(cur));
                subratio.ans = subratio.ans * map.get(x).get(cur);
                
                // Step3: 回傳什麼給父親. Case1. 有找到pair, 繼續乘
                return subratio;
            }
        }
        // Step3: 回傳什麼給父親. Case2. 找不到, 回傳1
        return subratio;
    }
}

/*
// "static void main" must be defined in a public class.
public class Solution {
    HashMap<String, HashMap<String, Double>> map = new HashMap<>();
    public static void main(String[] args) {
        
        
        
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
*/