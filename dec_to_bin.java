// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        int num = 15;
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            sb.append(num % 2);
            num = num / 2;
        }
        System.out.println(sb.reverse());
    }
}