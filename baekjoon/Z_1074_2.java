package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Z_1074_2 {

    // 재귀 함수

    static int N, r, c, ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        N = (int) Math.pow(2, N); // N = 2^N으로 보정

        z(0,0);

        System.out.println(ans);
    }


    static void z(int y, int x){
        if( N == 1 ) return;
        N /= 2; // N=3이라면 8 -> 4

        if( r < y + N && c < x + N){ // 1사분면
            z(y, x);
        }else if( r < y + N && c >= x + N){ // 2사분면
            ans += N * N * 1;
            z(y, x + N ); // 왜 여기만 x+=N임? x + N이 아니라?
        }else if( r >= y + N && c < x + N){
            ans += N * N * 2;
            z(y + N, x); // 왜 여기만 y+=N임? y + N이 아니라?
        }else{
            ans += N * N * 3;
            z(y + N, x + N);
        }


    } // z

}
