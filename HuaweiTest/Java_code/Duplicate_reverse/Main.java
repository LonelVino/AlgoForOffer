import java.util.*;

public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        char[] chars = (num+"").toCharArray();
        String res = "";
        for (int i = chars.length-1; i >= 0; i--) {
            if(!res.contains(chars[i]+""))
            res += chars[i];
        }
        System.out.println(res);
    }
}