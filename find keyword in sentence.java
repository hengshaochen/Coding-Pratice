// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        String s = "I am Heng-Shao and pass the Google DogHome interview";
        HashMap<String, String> map = new HashMap<>();
        map.put("Heng-Shao", "Heng-Shao");
        map.put("Google", "Google");
        // alias
        map.put("DogHome", "Google");
        
        String[] sp = s.split(" ");
        for (int i = 0; i < sp.length; i++) {
            if (map.containsKey(sp[i])) {
                System.out.println(map.get(sp[i]));
            }
        }
    }
}