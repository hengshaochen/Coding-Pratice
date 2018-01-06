/*
List of sets
set1: 1,2,3
set2: 4,5
set3: 6,7
...
...

Print out all permutations that each permutation contains exactly one element from each set. 

[146][147][246]...
DFS
[1
      1         2          3
   4    5      4   5     4   5
  6  7 6 7    6 7 6 7   6 7 6 7
  146, 147, 156 157 , ...
  O(number of all permutations) = O(length of set1 * length of set2 * length of set 3) = O(12) 
  
*/
// "static void main" must be defined in a public class.
public class Solution {
    public static void main(String[] args) {
        new Solution();
    }
    public Solution() {
        List<List<Integer>> input = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        cur.add(1);
        cur.add(2);
        cur.add(3);
        input.add(new ArrayList<>(cur));
        cur.clear();
        cur.add(4);
        cur.add(5);
        input.add(new ArrayList<>(cur));
        cur.clear();
        cur.add(6);
        cur.add(7);
        input.add(new ArrayList<>(cur));
        DFS(input, 0, new ArrayList<>());
    }
    
  void DFS(List<List<Integer>> input, int current_layer, List<Integer> cur) {
      // base case
      if (cur.size() == input.size()) {
          System.out.println(cur);
          return;
      }
      for (int i = 0; i < input.get(current_layer).size(); i++) {
          cur.add(input.get(current_layer).get(i));
          DFS(input, current_layer + 1, cur);
          cur.remove(cur.size() - 1);
      }
  }
}