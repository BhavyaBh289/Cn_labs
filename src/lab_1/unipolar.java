package lab_1;

import java.util.Scanner;

public class unipolar {
    public static void main( String[ ] args ) {
        Scanner sc = new Scanner(System.in);
        String lent = sc.next();
        String[] digits = String.valueOf(lent).split("");
        int len = digits.length;
        int[] input  = new int[len];
        for(int i=0;i<len;i++){
            input[i]=Integer.parseInt(digits[i]);
        }
        encoding en = new encoding(input,1);
    }
}