import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int res = 0;
        while (num > 1) {
            if( num % 2 == 1) res++;
            num /= 2;
        }
        if (num == 1) res++;
        System.out.println(res);
    }
}