import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            int[] intArr = new int[n];
            for(int i = 0; i < n; i++) {
                intArr[i] = scanner.nextInt();
            }
            Arrays.sort(intArr);
            for(int i = 0; i < intArr.length; i++) {
                if (i == 0 || intArr[i] != intArr[i-1]) {
                    System.out.println(intArr[i]);
                }
            }
        }
    }
}
