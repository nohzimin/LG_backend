package baekjoon;

// 자료구조 1. 배열
// 자료구조 2. ArrayList, Queue
// 배열 : 배열 전체를 순회 흐름 + 그 안에 살아 있는 사람만 순회 흐름

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class 요세푸스_1158 {

    static int N, K;
    static int[] input;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        input = new int[N+1 ]; // 출력번호 1 시작 : 0 dummy;

        // 번호를 채우고, 죽으면 0으로 변경
        for (int i = 1; i <= N; i++) {
            input[i] = i;
        }


        // 풀이
        int deathCnt = 0; // 죽은 사람의 수, 모두 죽으면 종료 (deathCnt == N)
        int idx = 1;      // 죽은 사람을 표함하는 배열 순회의 index
        int aliveCnt = 1; // 살아 있는 번호에서만 증가



        sb.append("<");
        while(true){
            // 종료 조건
            if (deathCnt == N) break; // 모두 죽으면 종료

            // 살아 있는 사람의 K번째 선택해 죽인다.
            if (input[idx] != 0) {
                if((aliveCnt % K )== 0 ){ // 살아 있는 사람 중에 K 번 째
                    sb.append(input[idx]).append(", ");
                    input[idx] = 0; // 죽음으러 표시
                    deathCnt++;
                }
                aliveCnt++;
            }


            idx++; // 항상 증가
            if (idx > N) idx = 1; // 순회
        }
        sb.setLength(sb.length() - 2); // 뒤에 칸을 두개 조정
        sb.append(">");
        System.out.println(sb);

    }
}
