package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 부분집합
public class 도영이가만든음식_2961_DFS {

    static int N, min;
    static int[][] src; // 재료(신,쓴)

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 재료 수
        src = new int[N][2];


        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            src[i][0] = Integer.parseInt(st.nextToken()); // 신 맛
            src[i][1] = Integer.parseInt(st.nextToken()); // 쓴 맛
        }

        min = Integer.MAX_VALUE;

        dfs(0, 1, 0); // 첫 번째 재료부터 시작
        System.out.println(min);
    } // main


    // dfs로 푸는 것은 호출 된 시점에 문제 풀이의 일부가 계속 계산되어서 다음 단계에 넘어가야 함.
    static void dfs(int srcIdx, int SourSum, int BitterSum){ // 현 단계 이전까지의 신맛, 쓴맛의 합
        // 기저조건
        if( srcIdx == N ) return;

        // 매 단계마다 신맛 쓴맛 계산
        int curSour = src[srcIdx][0] * SourSum;
        int curBitter = src[srcIdx][1] + BitterSum;

        min = Math.min(min, Math.abs(curSour - curBitter));

        // 현재 재료 선택 후 진행
        dfs(srcIdx + 1, curSour, curBitter);
        // 현재 재료 비선택 후 진행
        dfs(srcIdx + 1, SourSum, BitterSum);

    } // dfs


}
















