package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


//dfs

public class 빵집_3109_Fail {

    static int R, C, ans;
    static char[][] map;

    // y만 x는 항상 +1
    static int[] dy = { -1, 0, 1}; // 우선 순위 상우, 우, 하우
    // visit ? 사용 X
    // 이전에 어떤 좌표를 지났으면 다음 시도(다음 파이프 연결) 할 때 그 좌표는 이전에 성공하던 실패하던 갈 수가 없다.
    // 좌표를 따라가서 성공 <= 이미 그 자리에 파이프가 놓여져 있다.
    // 좌표를 따라가서 실패 <= 그 자리에 갈 필요가 어벗다.
    // 방문 하면 'x' 도시
    public static void main(String[] args)  throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][];

        for(int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        // 풀이
        // 맨 위부터 맨 아래행으로 내려오면 연결 시도
        for( int i = 0; i < R; i++) { // 행
            if( dfs(i, 0)) ans++; // 열은 항상 가장 왼쪽부터

        }
        System.out.println(ans);


    } // main

    static void dfs(int y, int x) {
        int nx = x + 1; // 다음 움직임은 x는 무조건 1 증가 (왼쪽)

        if (nx == C - 1) ans++;

        // for문 안에서 3 방향 중 우선순위로 탐색하되 먼저 성공하면 나머지 탐색은 진행X
        for( int d = 0; d < 3; d++){
            int ny = y + dy[d];
            if( ny < 0 || ny >=R || map[ny][nx] == 'x' ) continue;

            // 갈 수 있는 곳이면
            map[ny][nx] = 'x'; // 일종의 visit 체크임
            dfs(ny, nx) ;


        }
        return false;
    }



}
