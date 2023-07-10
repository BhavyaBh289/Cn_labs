package lab_1;

import java.util.Scanner;

public class unipolar {
    public static void main( String[ ] args ) {
        Scanner sc = new Scanner(System.in);
        int len = sc.nextInt();
        String[] digits = String.valueOf(len).split("");
        len = digits.length;
        int[] input  = new int[len];
        for(int i=0;i<len;i++){
            input[i]=Integer.parseInt(digits[i]);
        }
        encoding en = new encoding(input);
    }
}