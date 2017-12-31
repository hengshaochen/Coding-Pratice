// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        //int[] nums = { 1, 2, 3, 4, 0, 19, 1, 1, 2, 2, 3, 3, 2 };
        int[] nums = {7,7,7};
        int max = 1;
        int count = 1;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1] || nums[i] == nums[i + 1] - 1) {
                count++;
            } else {
                count = 1;
            }
            
            if (max < count) {
                max = count;
            }
        }
        
        System.out.println(max);
    }
}