import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        Hashtable<Integer, Integer> my_dict = new Hashtable<Integer, Integer>();
        for (int i=0; i < num; i++) {
            int key = sc.nextInt();
            int val = sc.nextInt();
            if (my_dict.containsKey(key)) {
                my_dict.put(key, my_dict.get(key) + val);
            } else {
                my_dict.put(key, val);
            }
        }
        Enumeration<Integer> keys = my_dict.keys();
        List<Integer> keys_list = Collections.list(keys);
        Collections.sort(keys_list);
        
        for (int k: keys_list) {
            System.out.println(k + " " + my_dict.get(k));
        }
    }
}