// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        // input
        String[] city = {"CityA", "CityB", "CityC"};
        int[] freq = {50, 100, 150};
        
        // algo start
        int[] prefix = new int[freq.length];
        
        // 算prefix
        int sum = 0;
        for (int i = 0; i < prefix.length; i++) {
            sum = sum + freq[i];
            prefix[i] = sum;
        }
        /*
        // 算prefix寫法2
        prefix[0] = freq[0];
        for (int i = 1; i < prefix.length; i++) {
            prefix[i] = freq[i] + prefix[i - 1];
        }
        */
        
        // random一個介於1~300(prefix[i - 1]的數字   
        Random rand = new Random();
        int n = rand.nextInt(prefix[freq.length - 1]) + 1;
        
        // 在prefix中取ceil
        int indexc = 0;
        for (int i = 0; i < prefix.length; i++) {
            if (prefix[i] >= n) {
                indexc = i;
                break;
            }
        }
        
        // 輸出結果
        System.out.println(city[indexc]);
    }
}