import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        Set<String> hash_set = new HashSet<String>();
        for (char c: str.toCharArray()) 
            if ((int)c <= 127 && (int)c >= 0) hash_set.add(String.valueOf(c));
        System.out.println(hash_set.size());
    }
}