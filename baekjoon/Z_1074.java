package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Z_1074 {

    // 반복문

    static int N, r, c, ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        N = (int) Math.pow(2, N); // N = 2^N으로 보정


        // 원점부터 (r,c) 까지의 거리
        int y = 0;
        int x = 0;

        // 반복문으로 분할 정복
        // (r, c) 위치까지 찾아질 동안 계속 분할 처리
        while(true){
            if(N == 1) break;

            N /= 2; // N=3이라면 8 -> 4

            if( r < y + N && c < x + N){ // 1사분면
                ; // 분할 영역
            }else if( r < y + N && c >= x + N){ // 2사분면
                // 앞 사분면을 누적으로 다 더해
                ans += N * N * 1;
                // 원점을 옮김
                x += N;
            }else if( r >= y + N && c < x + N){
                ans += N * N * 2;
                y += N;
            }else{
                ans += N * N * 3;
                y += N;
                x += N;
            }
        }

        System.out.println(ans);
    }


}
