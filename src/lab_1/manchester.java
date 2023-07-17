package lab_1;

import java.util.Scanner;

public class manchester {
    public static void main( String[ ] args ) {
        Scanner sc = new Scanner(System.in);
        String lent = sc.next();
        String[] digits = String.valueOf(lent).split("");
        int len = digits.length;
        int[] input  = new int[2*len];
        int temp;
        for(int i=0;i<len;i++){
            temp=Integer.parseInt(digits[i]);
            if (temp == 0){
                input[2*i]=1;
                input[2*i+1]=-1;
            }else {
                input[2*i]=-1;
                input[2*i+1]=1;
            }
        }
        encoding en = new encoding(input);

    }
}
