class Solution {
    class Result {
        double ans;
        boolean find;
        Result(double ans, boolean find) {
            this.ans = ans;
            this.find = find;
        }
    }
    HashMap<String, HashMap<String, Double>> map = new HashMap<>();
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        double[] ans = new double[queries.length];
        
        // set ratio
        for (int i = 0; i < equations.length; i++) {
            setRatio(equations[i][0], equations[i][1], values[i]);
            setRatio(equations[i][1], equations[i][0], 1 / values[i]);
        }
        
        // get ratio
        for (int i = 0; i < queries.length; i++) {

            // init hashmap
            HashSet<String> visited = new HashSet<>();
            
            Result result = getRatio(queries[i][0], queries[i][1], visited);
            
            
            if (result.find == true) {
                ans[i] = result.ans;
            } else {
                ans[i] = -1.0;
            }
        }
        
        return ans;
    }
    
    Result getRatio(String x, String y, HashSet<String> visited) {
        // base case , 如果相等 --> 找到結果 | 如果走到底沒有y
        if (map.get(x) == null) {
            return new Result(1, false);
        }
        if (x.compareTo(y) == 0) {
            return new Result(1, true);
        }

        Result subratio = new Result(1, false);
        for (String cur : map.get(x).keySet()) {
            
            // 如果走訪過, 就設為visited
            if (visited.contains(cur)) {
                continue;
            }
            
            // Step1: 切子問題給兒子並設為走訪過
            visited.add(cur);
            subratio = getRatio(cur, y, visited);
            
            // Step2: 拿到兒子回傳的資訊要如何處理？
            if (subratio.find) {
                
                subratio.ans = subratio.ans * map.get(x).get(cur);
                
                // Step3: 回傳什麼給父親. Case1. 有找到pair, 繼續乘
                return subratio;
            }
        }
        // Step3: 回傳什麼給父親. Case2. 找不到, 回傳-1.0
        return subratio;
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
}