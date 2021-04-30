import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String str = scanner.nextLine();
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            int size = str.length();
            int addZero = 8 - size%8;
            while((addZero > 0) && (addZero < 8)) {
                sb.append("0");
                addZero--;
            }
            String str1 = sb.toString();
            while(str1.length() > 0) {
                System.out.println(str1.substring(0,8));
                str1 = str1.substring(8);
            }
        }
    }
}
