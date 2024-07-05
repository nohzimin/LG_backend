package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

public class 쿼드트리_me {
    static int N;
    static char[][] map;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new char[N][];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }
        divide(0, 0, N);
        System.out.println(sb);
    }
    static void divide(int y, int x, int n) {
        // y,x 좌표를 왼쪽 위 시작점으로 하고 가로, 세로 n 길이만큼 문자가 모두 같은 지 확인
        if( check(y, x, n) ) { // 모든 영역이 같은 문자

            sb.append(map[y][x]);

        }else { // 모든 영여기 같은 문자 X

            sb.append("(");

            int half = n / 2;

            divide(y, x, half); // 위 왼쪽
            divide(y, x + half, half); // 위 오른쪽
            divide(y + half, x, half); // 아래 왼쪽
            divide(y + half, x + half, half); // 아래 오른쪽

            sb.append(")");
        }
    }

    static boolean check(int y, int x, int n) {
        char ch = map[y][x];

        for (int i = y; i < y + n; i++) {
            for (int j = x; j < x + n; j++) {
                if( ch != map[i][j] ) return false;
            }
        }

        return true;
    }
}
