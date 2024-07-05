package baekjoon;

import java.util.Arrays;
import java.util.Scanner;

public class 요세푸스_me2 {

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

        // 결과 배열 초기화
        result = new int[n];

        System.out.println(Arrays.toString(input));

        int index = 0; // 현재 위치
        int count = 0; // 제거된 사람 수

        while (count < n) {
            int step = 0;

            // k번째 사람을 찾기 위해 k-1번 이동
            while (step < k) {
                if (input[index] != -1) { // 이미 제거되지 않은 경우에만 이동
                    step++;
                }
                if (step < k) {
                    index = (index + 1) % n; // 원형 순환
                }
            }

            // k번째 사람 제거
            result[count] = input[index];
            input[index] = -1; // 제거 표시
            count++;

            System.out.println("제거된 사람: " + result[count - 1]);
        }

        System.out.println(Arrays.toString(result));
    }
}
