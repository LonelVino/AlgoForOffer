import java.util.Scanner;
public class Main{
    public static int nums(String str,char c){
        int i = 0;
        char[] chars = str.toCharArray();
        for(char ch:chars){
            if(c == ch ){
                i++;
            }
        }
        return i;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine().toLowerCase();
        char c = sc.nextLine().toLowerCase().charAt(0);
        System.out.println(nums(str,c));
    }

}