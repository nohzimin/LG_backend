package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 부분집합
// bitmasking
public class 도영이가만든음식_2961_BitMask {

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

        subset(0, 0); // 첫 번째 재료부터 시작, 아무것도 선택되지 않은 상태의 bitmasking (...00000)

        System.out.println(min);

    } // main

    static void subset(int srcIdx, int mask){
        // 기저조건
        if(srcIdx == N){
            // 부분 집합의 한 경우가 만들어진 상태
            // 문제의 경우를 따지면 된다.
            // select true인 재료를 사용
            int sour = 1;
            int bitter = 0;
            int cnt = 0;

            for(int i = 0; i < N; i++){
                if( ( mask & ( 1 << i ) ) != 0 ) { // mask의 bit 표현 중 i번째 녀석이 1인지 아닌지 확인
                    sour *= src[i][0]; // 신 맛
                    bitter += src[i][1]; // 쓴 맛
                    cnt++;
                }
            }
            if( cnt > 0) {
                min = Math.min(min, Math.abs(sour - bitter)); // 절대값
            }

            return;
        }

        subset(srcIdx + 1, mask | 1 << srcIdx); // 현재 재료 선택

        subset(srcIdx + 1, mask); // 현재 재료 (srcIdx) 선택 안함 = 비선택

    } // subset


}
















