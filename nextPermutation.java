// "static void main" must be defined in a public class.
public class Solution {
    public static void main(String[] args) {
        new Solution();
    }
    
    int total_number = 1;
    
    // 讓int可以在function中產生變化
    int[] count = new int[1];
    
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
        //DFS(input, 0, new ArrayList<>());
        
        count[0] = 0;
        // 計算全部set加起來有幾個元素
        for (int i = 0; i< input.size(); i++) {
            total_number *= input.get(i).size();
        }
        nextPermutation(input);
        nextPermutation(input);
        nextPermutation(input);
        nextPermutation(input);
    }
    
    void nextPermutation(List<List<Integer>> input) {
        int counter = count[0];
        System.out.println(counter);
        // 防止counter超過total_number
        counter %= total_number;
        
        // 進制轉換（根據每個set的size來看進位）
        List<Integer> index = new ArrayList<>();
        int current_dig = input.size() - 1;
        for (int i = current_dig; i >= 0; i--) {
            index.add(counter % input.get(i).size());
            counter /= input.get(i).size();
        }
        Collections.reverse(index);
        
        // 印出答案
        for (int i = 0; i < index.size(); i++) {
            System.out.print(input.get(i).get(index.get(i)) + " ");
        }
        System.out.println("");
        count[0] += 1;
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