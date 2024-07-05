package baekjoon;

// dfs + no visit

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 단지번호붙이기_2667_DFS2 {

    static int N, cnt; // 단지 별 개수
    static char[][] map;

    // 상하좌우
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static List<Integer> list = new ArrayList<>(); // 각 단지 별 수 추가, 나중에 정렬

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N =Integer.parseInt(br.readLine());

        map = new char[N][];

        for ( int i = 0 ; i < N ; i++) {
            map[i] = br.readLine().toCharArray();
        }

        // 풀이
        // 2차원 배열을 순회 하면서 새로운 영역이 나오면 dfs 수행
        for(int i = 0 ; i < N ; i++) {
            for(int j = 0 ; j < N ; j++) {
                if(map[i][j] != '1') continue; // 방문하지 않았거나, 0인 부분
                cnt = 0; // 최초 호출 시, 새로운 영역이 나왔을 때 초기화를 여기서 함
                //
                dfs(i, j);
                list.add(cnt);
            }
        }
        // 정렬
        Collections.sort(list);
        System.out.println(list.size()); // 리스트에 몇개 들어가 있는지
        for(int i = 0 ; i < list.size() ; i++) {
            System.out.println(list.get(i));
        }



    } // main

    static void dfs(int y, int x) {
        cnt++; // 호출 될 때 마다 증가
        map[y][x] = '0';


        // 기저 조건의 역할
        for (int d = 0 ; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];

            if( ny < 0 || nx < 0 || ny >= N || nx >= N ) continue;
            if(map[ny][nx] != '1') continue;

            // 재귀 호출
            dfs(ny, nx);
        }
    }


}
