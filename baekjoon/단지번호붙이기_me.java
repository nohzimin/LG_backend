package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 집 있음 1 / 집 없음 0
// 연결된 묶음에 단지 번호를 붙임 (1, 2, 3..)
// 총 단지 개수 출력 + 각 단지 마다 속하는 집의 개수 출력
public class 단지번호붙이기_me {

    static int n, apartCnt, homeCnt ;
    static String[][] apart;

    // 상 하 좌 우
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void dfs(int y, int x){
        apart[y][x]= "1";
        homeCnt++;

        for(int i=0; i<4; i++){
            int ny = y+dy[i];
            int nx = x+dx[i];

            if( ny < 0 || nx < 0 || ny >= n || nx >= n || apart[ny][nx] != "0") continue;
            dfs(ny, nx);
        }


    }

    public static void bfs(int y, int x){
       // 해당 노드 방문 처리 + 순서 매김을 위해 -1로 처리
        apart[y][x]="1";

    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        apart = new String[n][n];

        // 아파트 단지 정보 받기
        for (int i = 0; i < n; i++) {
            apart[i] = br.readLine().split("");
        }

        apartCnt = 0;
        int [] homeCounts = new int[n*n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 현재 위치에서 DFS 수행
                if( apart[i][j] == "1") {
                    homeCnt = 0;
                    dfs(i, j);
                    homeCounts[apartCnt++] = homeCnt;
                }
            }
        }
        System.out.println(apartCnt); // 아파트 단지 개수 출력
        int[] result = new int[apartCnt];
        System.arraycopy(homeCounts, 0, result, 0, apartCnt);
        Arrays.sort(result);
        for (int i : result) {
            System.out.println(i);
        }






    }



}




/*
7
0110100
0110101
1110101
0000111
0100000
0111110
0111000

3
7
8
9
 */