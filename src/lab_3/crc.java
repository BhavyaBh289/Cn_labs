package lab_3;

import java.util.Scanner;

public class crc {
    static int divisor=0b11001;       //11001 16+8+1
    public static void main(String []args){
//        Scanner sc = new Scanner(System.in);
//        String[] splitArrayy = sc.nextLine().split("");
//        int t = 0;
//        for (int i = 0; i < splitArrayy.length; i++) {
//            t*=2;
//            t += Integer.parseInt(splitArrayy[i]);
//        }
        int t = 0b110011;
//        printn(xorDivision(t),4);
        int cyclic = addremainder(t);
        System.out.println(xorDivision(t)+" "+xorDivision(cyclic));
//        printn(cyclic,6+5);
//        check(t);
//        check(cyclic);
//        printn(t,splitArrayy.length);
//        printn(t%divisor,4);
    }
    public static void check(int num){
        if (xorDivision(num)==0){
            System.out.println("No Error");
        }else {
            System.out.println("Error");
        }
    }
    public static int addremainder(int num){
//        int temp = xorDivision(num);
        num*=32;
//        printn(num,9);
//        System.out.println(xorDivision(num));
//        System.out.println(xorDivision(num));
        num= num^xorDivision(num);
//        printn(num,9);
//        System.out.println();
        return num;
    }
    public static int xorDivision(int dividend) {
        int remainder=dividend;
        while (remainder>=divisor){
//            System.out.println(remainder);
            int temp =divisor*2;
            while (temp<remainder){
                temp*=2;
            }
            temp=temp/2;
            remainder=temp ^ remainder;
        }
        return remainder;
    }
    public static void printn(int num,int n){
        int []res= new int[n];
        for(int i = n-1;i>=0;i--){
            res[i]=num%2;
            num/=2;
        }
        for (int i :res){
            System.out.print(i);
        }
    }
}
