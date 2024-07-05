package baekjoon;

import java.util.Scanner;

// 완탐 dfs
// 시간 초과
public class 설탕배달_2839 {

    static int N, min;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        min = 5000;

        dfs(0, 0); // 시작은 5kg 0개, 3kg 0개

        min = min == 5000 ? -1 : min;
        System.out.println(min);
    }

    static void dfs(int five, int three) { // 5kg 몇개, 3kg 몇개 사용했는지
        // 기저 조건
        int sum = five*5 + three*3;

        if(sum == N){ // 성공한 경우
            min = Math.min(min, five+three); // 최소값 갱신
            return;
        } else if (sum <N){ // 실패한 경우
            return;
        }

        dfs(five + 1, three);
        dfs(five, three + 1);

    }
}
