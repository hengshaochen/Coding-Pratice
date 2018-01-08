class Solution {
    int max = 0;
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, (a,b) -> a[0] - b[0]);
        /*
        for (int i = 0; i < pairs.length; i++) {
            System.out.println(pairs[i][0] + " " + pairs[i][1]);
        }
        */
        
        dfs(0, Integer.MIN_VALUE, pairs, 0);
        return max;    
    }
    void dfs(int start, int pre_end_value, int[][] pairs, int cur_max) {
        // base case
        //   System.out.println("pre:" + pre_end_value + "start:" + start +  "    cur_max:" + cur_max);

        
        // Step2
        if (pre_end_value < pairs[start][0]) {
            cur_max += 1;
        }
        if (max < cur_max) {
            max = cur_max;
        }
        
        
        for (int i = start; i < pairs.length - 1; i++) {
            dfs(i + 1, pairs[start][1], pairs, cur_max);
        }
    }
}
/*
           [1,2]              [2,3]          [3,4]  i = 0 - 2
         [2,3] [3,4]  i =1    [3,4] i = 2      i = 3 
               [2,3]
*/


// Copy Ans
class Solution {
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, (p1, p2)->p1[0]-p2[0]);
        int len = 0;
        int pre = Integer.MIN_VALUE;
        for(int[] pair : pairs){
            if(pair[0] > pre){  // not overlap
                len++;
                pre = pair[1];
             }else if(pair[1] < pre){ // overlap but with smaller second element
                pre = pair[1];
            }
        }
        return len;
    }
}