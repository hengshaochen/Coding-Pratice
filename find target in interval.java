// "static void main" must be defined in a public class.
public class Solution {
    class Interval {
        double start = 0;
        double end = 0;
        Interval(double s, double e) {
            this.start = s;
            this.end = e;
        }
    }
    public static void main(String[] args) {
        new Solution();
    }
    public Solution() {
        // [-1.1, 1.0], [-0.5, 3.5], [3.6, 4.0]
        // Step1: 先所有端點拿出來排序: -1.1 -0.5 1.0 3.5 3.6 4.0
        // Step2: 遇到起點就把該區間加入, 遇到終點就把該區間刪除
        // -1.1: [-1.1 1.0]
        // -0.5: [-1.1 1.0] [-0.5 3.5]
        //  1.0: [-0.5 3.5]
        //  3.5: []
        //  3.6: [3.6 4.0]
        //  4.0: []
        
        // Pre-processing
        double target = 0.3;
        List<Interval> input = new ArrayList<>();
        input.add(new Interval(-1.1, 1.0));
        input.add(new Interval(-0.5, 3.5));
        input.add(new Interval(3.6, 4.0));
        
        double[] arr = new double[input.size() * 2];
        int count = 0;
        for (int i = 0; i < input.size(); i++) {
            arr[count++] = input.get(i).start;
            arr[count++] = input.get(i).end;
        }
        Arrays.sort(arr);
        HashMap<Double, List<Interval>> map = new HashMap<>();
        map.put(arr[0], new ArrayList<>());
        map.get(arr[0]).add(input.get(0));
        for (int i = 1; i < arr.length; i++) {
            // 注意：這邊要用deep copy, 因為後續會remove會影響到前面
            map.put(arr[i], new ArrayList<>(map.get(arr[i - 1])));
            
            // 判斷是哪個區間的 起點 or 終點
            int interval_idx = 0;
            int startOrEnd = 0;
            for (int j = 0; j < input.size(); j++) {
                if (input.get(j).start == arr[i]) {
                    interval_idx = j;
                    startOrEnd = 0;
                } else if (input.get(j).end == arr[i]) {
                    interval_idx = j;
                    startOrEnd = 1;
                }
            }
            
            // 把該區間加入或刪除（如果是起點就加入 / 終點就刪除）
            if (startOrEnd == 0) {
                map.get(arr[i]).add(input.get(interval_idx));
            } else if (startOrEnd == 1){
                map.get(arr[i]).remove(input.get(interval_idx));
            }
        }
        
        // Step3: binary search Step1那些端點, 然後取左邊的
        // ex: target = 0.3 , 會找到-0.5 ~ 1.0, 取-0.5 --> [-1.1 1.0][-0.5 3.5]
        int start = 0;
        int end = arr.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (target <= arr[mid]) {
                end = mid;
            } else {
                start = mid;
            }
        }

        // 印答案
        if (arr[start] < target) {
            System.out.println(map.get(arr[start]));
        } else {
            System.out.println(map.get(arr[end]));
        }
    }
}

// 法2:
// "static void main" must be defined in a public class.
public class Solution {
    class Interval {
        double start = 0;
        double end = 0;
        Interval(double s, double e) {
            this.start = s;
            this.end = e;
        }
    }
    List<Interval> ans;
    public static void main(String[] args) {
        new Solution();
    }
    public Solution() {
        // 法2: Step1: 先依照左端點來排序
        
        // Step2: 用Binary Search, 找input的中點, 比左端點小 --> 往左, 比右端點大 --> 往右
        // 其他情況就切成子問題來解
        
        double target = 4.2;
        List<Interval> input = new ArrayList<>();
        input.add(new Interval(-1.1, 1.0));
        input.add(new Interval(-0.5, 3.5));
        input.add(new Interval(3.6, 4.0));
        
        ans = new ArrayList<>();
        bs(0, input.size() - 1, target, input);
        printAns();
    }
    
    void bs(int left, int right, double target, List<Interval> input) {
        if (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (target < input.get(mid).start) {
                // target比左端點小 --> 往左
                right = mid;
            } else if (target > input.get(mid).end) {
                // target比右端點大 --> 往右
                left = mid;
            } else {
                // 其他情況, 比左端點大 同時比 右端點小 --> 加入ans, 兩邊切子問題繼續做
                ans.add(input.get(mid));
                bs(left, mid - 1, target, input);
                bs(mid + 1, right, target, input);
                return;
            }
        }
        
        if (target >= input.get(left).start && target <= input.get(left).end) {
            ans.add(input.get(left));
        } else if (target >= input.get(right).start &&
                   target <= input.get(right).end) {
            ans.add(input.get(right));
        }
    }
    
    void printAns () {
        for (int i = 0; i < ans.size(); i++) {
            System.out.println(ans.get(i).start + " " + ans.get(i).end);
        }
    }
}