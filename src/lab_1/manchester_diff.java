package lab_1;

import java.util.Scanner;

public class manchester_diff {

    public static void main( String[ ] args ) {
        Scanner sc = new Scanner(System.in);
        String lent = sc.next();
        String[] digits = String.valueOf(lent).split("");
        int len = digits.length;
        int[] input  = new int[2*len];
        int temp,last;
        last = 1;
        for(int i=0;i<len;i++){
            temp=Integer.parseInt(digits[i]);
            if ((temp == 0 && last ==-1)||(temp==1 && last == 1)){
                input[2*i]=1;
                input[2*i+1]=-1;
                last = -1;
            }else {
                input[2*i]=-1;
                input[2*i+1]=1;
                last= 1;
            }
        }
        encoding en = new encoding(input,0.5);
    }
}
