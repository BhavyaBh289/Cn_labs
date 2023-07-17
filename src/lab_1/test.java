package lab_1;

import java.util.Scanner;

public class test {
    public static void main( String[ ] args ) {
        Scanner sc = new Scanner(System.in);
        int len = sc.nextInt();
        int[] inputt  = new int[len];
        int i = 0;
        while (i<len) {
            inputt[i]=sc.nextInt();
            i++;
        }
        encoding en = new encoding(inputt,1);

    }
}
