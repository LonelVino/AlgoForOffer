import java.util.*;

/**
 * Main
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.valueOf(sc.nextLine());
        int i = 2;
        while (i * i <= n) {
            while(n % i == 0) {
                System.out.print(i + " ");
                n /= i;
            }
        }
        i++;
        if (n - 1 != 0) {
            System.out.print(i + " ");
        }
    }
}