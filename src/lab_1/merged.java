package lab_1;

import java.util.Scanner;

public class merged {
    public static void main( String[ ] args ) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter encoding method \n1 for unipolar ,\n2 for polar non return to zero level ,\n3 for polar non return to zero inverted,\n4 for polar return to zero ,\n5 for manchester,\n6 for differential manchester");
        int type = sc.nextInt();
        System.out.println("Enter the code");
        String lent = sc.next();
        String[] digits = String.valueOf(lent).split("");
        int len = digits.length;
        int[] inputt  = new int[len];
        int[] input  = new int[2*len];
        int temp,last;
        switch (type){
            case 1:
                for(int i=0;i<len;i++){
                    inputt[i]=Integer.parseInt(digits[i]);
                }
                encoding en = new encoding(inputt);
                break;
            case 2:
//                int[] inputt  = new int[len];
//                int temp;
                for(int i=0;i<len;i++){
                    temp=Integer.parseInt(digits[i]);
                    if (temp ==0){
                        inputt[i] = 1;
                    } else if (temp == 1) {
                        inputt[i]=-1;
                    }
                }
                encoding enc = new encoding(inputt);
                break;
            case 3:
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
                encoding ena = new encoding(inputt);
                break;
            case 4:
                for(int i=0;i<len;i++){
                    temp=Integer.parseInt(digits[i]);
                    if (temp ==0){
                        input[2*i] = -1;
                    } else if (temp == 1) {
                        input[2*i] = 1;
                    }
                    input[2*i+1]= 0;
                }
                encoding enb = new encoding(input);
                break;
            case 5:
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
                encoding end = new encoding(input);
                break;
            case 6:
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
                encoding ene = new encoding(input);
                break;
        }
    }
}
