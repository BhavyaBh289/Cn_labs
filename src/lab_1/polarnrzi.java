package lab_1;

import java.util.Scanner;

public class polarnrzi {
    public static void main( String[ ] args ) {
        Scanner sc = new Scanner(System.in);
        int len = sc.nextInt();
        String[] digits = String.valueOf(len).split("");
        len = digits.length;
        int[] input  = new int[len];
        int[]inputt = new int[len];
        int temp,last;
        last = 1;
        for(int i=0;i<len;i++){
            temp =Integer.parseInt(digits[i]);
            if (temp ==0){
                inputt[i] = last;
            } else if (temp == 1) {
                if(last==1){
                    inputt[i]=-1;
                    last= -1;
                } else if (last ==-1) {
                    inputt[i]=1;
                    last = 1;
                }
            }
        }
        encoding en = new encoding(inputt);
    }
}
