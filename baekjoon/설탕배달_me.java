package baekjoon;

import java.util.Scanner;

public class 설탕배달_me {

    static int N, min;
    static int[] memoi = new int[5001];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

       if (N == 4 || N == 7){
           System.out.println(-1);
       }
       else if( N % 5 == 0){
           System.out.println(N/5);
       }
       else if( N % 5 == 1 || N % 5 == 3){
           System.out.println((N/5) + 1);
       }
       else if( N % 5 == 2 || N % 5 == 4){
           System.out.println((N/5) + 2);
       }
    }//main
}
