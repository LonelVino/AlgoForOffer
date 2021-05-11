import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int N = sc.nextInt();
            for (int i = 1; i <= N; i ++) {
                for (int j = 1; j < (N - (i - 1)); j++) {
                    System.out.print(((j + i -1) * (j + i -1) + (j + i -1)) / 2 - (i - 1) + " ");
                }
                System.out.println((N*N+N)/2 - (i-1));
            }
        }
    }
}
