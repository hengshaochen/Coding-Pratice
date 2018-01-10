// 1. MAXIMUM SUBARRAY
// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        
        // Preprocessing: Parse String to Integer array
        String input = "-2,2,-3,4,-1,2,1,-5,3";
        String[] input_split = input.split(",");
        int[] arr = new int[input_split.length];
        
        
        for (int i = 0; i < input_split.length; i++) {
            arr[i] = Integer.parseInt(input_split[i]);
        }
        
        // Maximum subarray sum
        int min = 0;
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            sum = sum + arr[i];
            max = Math.max(max, sum - min);
            min = Math.min(min, sum);
        }
        System.out.println(max);
        
    }
}

// 2.
// "static void main" must be defined in a public class.
import java.text.DecimalFormat;
public class Main {
    public static void main(String[] args) {
        String input = "6000~5~6~0";
        //String input = "30000~10~6~5000";
        String[] input_split = input.split("~");
        double[] arr = new double[input_split.length];
        
        // [0]: total loan, [2] annual interest rate,
        // [1]: year(payment period) [3] downpay
        for (int i = 0; i < input_split.length; i++) {
            arr[i] = Double.parseDouble(input_split[i]);
        }
        
        // formula: a = ( ([2] / 12) * ([0] - [3]) )
        // b = (1 - (1 + ([2] / 12))^-([1] * 12)   )
        // output[0] = a / b
        // output[1] = (output[0] * [1] * 12) - [0]
        double[] ans = new double[2];
    
        //System.out.prinlnt(((arr[2] / 12) * (arr[0] - arr[0])));
        
        ans[0] = ((arr[2] / 100 / 12) * (arr[0] - arr[3])) /
                 (1 - 1 / (Math.pow((1 + (arr[2] / 100 / 12)), arr[1] * 12)));
        
        ans[1] = (ans[0] * arr[1] * 12) - (arr[0] - arr[3]);
        
        DecimalFormat df = new DecimalFormat("##.00");
        //ans[0] = Double.parseDouble(df.format(ans[0]));
        
        
        //System.out.println( Math.round(ans[1]) );
        
        //System.out.println( ans[0] + "~$" +  Math.round(ans[1]) );
        System.out.println( df.format(ans[0]) + "~$" +  Math.round(ans[1]) );
    }
}

// 3.
// "static void main" must be defined in a public class.
import java.text.DecimalFormat;
public class Solution {
    public static void main(String[] args) {
        new Solution();
    }
    
    public Solution() {
        //String input = "PORT:AXN,0,10;BGT,20,30;CXZ,10,30|BENCH:AXN,50,10;BGT,30,30;DFG,30,20;XYZ,0,10";
        String input = "PORT:AXN,10,10;BGT,20,30;CXZ,10,30|BENCH:AXN,50,10;BGT,30,30;DFG,30,20";
        // preprocessing
        String[] sp = input.split("\\|");
        String[] buffer = sp[0].split(":");
        String port = buffer[1];
        
        buffer = sp[1].split(":");
        String bench = buffer[1];
        
        double[] port_total = new double[1];
        double[] bench_total = new double[1];
        TreeSet<String> stockSet = new TreeSet<>();
        HashMap<String, ArrayList<Double>> port_map = buildMap(port, stockSet, port_total);
        HashMap<String, ArrayList<Double>> bench_map = buildMap(bench, stockSet, bench_total);
                
        //System.out.println(port_total[0]);
        StringBuilder ans = new StringBuilder();
        for (String cur : stockSet) {
            ArrayList<Double> cur_port = port_map.get(cur);
            ArrayList<Double> cur_bench = bench_map.get(cur);
            
            double cur_port_val = cur_port != null ? cur_port.get(0) * cur_port.get(1) / port_total[0]  : 0;
            double cur_bench_val = cur_bench != null ? cur_bench.get(0) * cur_bench.get(1) / bench_total[0] : 0;
            
            DecimalFormat df = new DecimalFormat("##00.00");
            //double cur_ans = (((cur_port_val / port_total[0]) - (cur_bench_val / bench_total[0]) * 100));
            double cur_ans = (cur_port_val - cur_bench_val) * 100;
            if (cur_port == null && cur_bench_val == 0) {
                ans.append(cur + ":" + "-0.00"  + ",");
            } else {
                ans.append(cur + ":" + df.format(cur_ans)  + ",");
            }
        }
        ans.deleteCharAt(ans.length() - 1);
        System.out.println(ans.toString());
        
        //System.out.println(bench_map.get("CXZ"));
        /*
        System.out.println(port);
        System.out.println(bench);
        */
    }
    
    public HashMap<String, ArrayList<Double>> buildMap(String inputString, TreeSet<String> stockSet, double[] total) {
        HashMap<String, ArrayList<Double>> map = new HashMap<>();
        
        String[] stringList = inputString.split(";");
        for (int i = 0; i < stringList.length; i++) {
            String[] cur = stringList[i].split(",");
            ArrayList<Double> cur_value = new ArrayList<>();
            cur_value.add(Double.parseDouble(cur[1]));
            cur_value.add(Double.parseDouble(cur[2]));
            map.put(cur[0], cur_value);
            stockSet.add(cur[0]);
            total[0] = total[0] + (Double.parseDouble(cur[1]) * Double.parseDouble(cur[2]));
        }
        return map;
    }
}


// 4. LCA
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Result {
    TreeNode return_node;
    boolean p_exist;
    boolean q_exist;
    Result (boolean p, boolean q, TreeNode node) {
        return_node = node;
        p_exist = p;
        q_exist = q;
    }
}
class Solution {
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        // Root job? 如果p或q其中一個等於root, 回傳root本身 / 如果左右子樹都有東西, LCA就是root本身 / 
        // 注意：在root的情況下, 要p跟q都有遇到才能return, 不然就return null
        // 奇怪？root的定義跟其他子節點定義不太一樣怎麼辦？使用helper function, 把helper function回傳回來的在自己判斷一下即可
        Result rootResult = helper(root, p, q);
        if (rootResult.p_exist == true && rootResult.q_exist == true) {
            return rootResult.return_node;
        }
        return null;
    }

    Result helper(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return new Result(false, false, root);
        }

        Result left = helper(root.left, p, q);
        Result right = helper(root.right, p, q);

        boolean p_exist = left.p_exist || right.p_exist || root == p;
        boolean q_exist = left.q_exist || right.q_exist || root == q;

        if (root == p || root == q) {
            return new Result(p_exist, q_exist, root);
        }

        if (left.return_node != null && right.return_node != null) {
            return new Result(p_exist, q_exist, root);
        }

        if (left.return_node != null) {
            return new Result(p_exist, q_exist, left.return_node);
        }
        if (right.return_node != null) {
            return new Result(p_exist, q_exist, right.return_node);
        }
        return new Result(p_exist, q_exist, null);

    }
}