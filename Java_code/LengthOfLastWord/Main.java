import java.util.*;
public class Main {
    public static int lengthOfLast(String str) {
        String[] s = str.split(" ");
        return s[s.length-1].length();
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while(scan.hasNext()) {
            String str = scan.nextLine();
            System.out.println(lengthOfLast(str));
        }
    }
}