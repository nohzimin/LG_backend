package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 도영이가만든음식_me {

    // 신맛과 쓴맛의 차이가 가장 작은 요리의 차이 출력
    // DFS

    static int N, result;
    static int[] S, B;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] S = new int[N];
        int[] B = new int[N];

        for (int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            S[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 1, 0, 0);
        System.out.println(result);

    } // main

    // 완탐
    static void dfs(int level, int s, int b, int selectCnt){ // 트리 깊이, 신맛, 쓴맛, 선택한 음식의 개수

        if( level == N ){
            if( selectCnt != 0){
                result = Math.min(result, Math.abs(s - b)); // 차이니까 b-s, s-b의 절대값이 제일 작은 애로 선택
            }
            return;
        }
        dfs(level+1, s, b, selectCnt ); // 비선택
        dfs(level+1, s * S[level], b + B[level], selectCnt + 1); // 선택


    } // dfs
}
