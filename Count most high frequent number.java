// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        // input:  [1, 2, 2, 2, 4, 4, 5]
        int[] input = {1, 2, 2, 2, 4, 4, 5, 5, 5, 5, 90, 90, 90, 90, 90, 90};
        
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < input.length; i++) {
            if (!map.containsKey(input[i])) {
                map.put(input[i], 1);
            } else {
                map.put(input[i], map.get(input[i]) + 1);
            }
        }
        
        int max_count = 0;
        int ans = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (max_count < entry.getValue()) {
                max_count = entry.getValue();
                ans = entry.getKey();
            }
        }
        System.out.println(ans);
        /*
        // 底下是錯誤答案
        int[] map = new int[10];
        for (int i = 0; i < input.length; i++) {
            int cur_number = input[i];
            map[cur_number] = map[cur_number] + 1;
        }
        
        int max = -1;
        int max_val = -1;
        for (int i = 0; i < 10; i++) {
            if (max < map[i]) {
                max_val = i;
                max = map[i];
            }
        }
        System.out.println("ans:" + max_val);
        
        // check map (just for review)
        for (int i = 0; i < 10 ;i++) {
            System.out.println(map[i]);
        }
        */
    }
}