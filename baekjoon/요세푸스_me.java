package baekjoon;

import java.util.Arrays;
import java.util.Scanner;

public class 요세푸스_me {

    static int n, k;
    static int[] input;
    static int[] result;

    public static void main(String[] args) {
        // 입력 받기
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        k = scanner.nextInt();

        // 원 배열 만들기
        input = new int[n];
        for (int i = 0; i < n; i++) {
            input[i] = i + 1;
        }

        // 1 2 3 4 5 6 7 input
        // 3 6 2 7 5 1 4 result

        result = new int[n];

//        System.out.println(Arrays.toString(input));


        int index = 0; // 현재 위치
        int delPerson = 0; // 제거된 사람 수


       // 제거 된 인원이 원래 인원보다 많을 경우 종료
        while(delPerson < n){
            int moveToK = 0; //

            // k 찾기 위해 k-1까지 이동( 3을 얻기 위해 자리 2번을 가는 것)
            while(moveToK < k){

                // k번째가 아닌 제거하면 안되는 경우 = 이동
                if(input[index] != -1){
                    moveToK++;
                }

                // 순환
                if( moveToK < k ){
                    index = (index+1) % n;
                }


            }

            // result에 넣고 input=-1(제거)
            result[delPerson] = input[index];
            input[index] = -1;  // 제거하면 -1 값 넣기
            delPerson++;

//            System.out.println("제거된 사람 :" + result[delPerson -1]);
        } // 큰 while

        System.out.println(Arrays.toString(result));

    } // main


}
