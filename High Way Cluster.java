// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        //int[] arr = {5, 4, 7, 8, 2, 1};
        int[] arr = {2, 4, 1, 3};
        int start = 0;
        List<List<Integer>> group = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1 || arr[i] > arr[i + 1]) {
                group.add(new ArrayList<>());
                for (int j = start; j <= i; j++) {
                    group.get(group.size() - 1).add(arr[j]);
                }
                start = i + 1;
            }
        }
        
        for (int i = 0; i < group.size(); i++) {
            System.out.println(group.get(i));
        }
    }
}