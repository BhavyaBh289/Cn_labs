package lab_1;

import java.util.Scanner;

public class polarnrzl {
    public static void main( String[ ] args ) {Scanner sc = new Scanner(System.in);
        int len = sc.nextInt();
        String[] digits = String.valueOf(len).split("");
        len = digits.length;
        int[] inputt  = new int[len];
        int temp;
        for(int i=0;i<len;i++){
            temp=Integer.parseInt(digits[i]);
            if (temp ==0){
                inputt[i] = 1;
            } else if (temp == 1) {
                inputt[i]=-1;
            }
        }
        encoding en = new encoding(inputt);
    }
}
