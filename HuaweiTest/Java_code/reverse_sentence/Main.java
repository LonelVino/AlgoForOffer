import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String string = sc.nextLine();
        List<String> items = Arrays.asList(string.split("\\s* \\s*"));
        for (int i = (items.size() - 1); i >= 0; i--) {
            System.out.print(items.get(i) + ' ');
        }
    }
}