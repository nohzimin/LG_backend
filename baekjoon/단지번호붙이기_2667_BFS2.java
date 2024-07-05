package baekjoon;

// bfs + no visit

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 단지번호붙이기_2667_BFS2 {

    static int N, cnt; // 단지 별 개수
    static char[][] map;

    // 상하좌우
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static List<Integer> list = new ArrayList<>(); // 각 단지 별 수 추가, 나중에 정렬
    static Queue<Node> queue = new ArrayDeque<>();

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
                bfs(i, j);
                list.add(cnt);
            }
        }

        Collections.sort(list); // 정렬
        System.out.println(list.size()); // 리스트에 몇개 들어가 있는지
        for(int i = 0 ; i < list.size() ; i++) {
            System.out.println(list.get(i));
        }



    } // main

    static void bfs(int y, int x) {
        cnt = 1; // 처음 호출되면 새로운 단지가 만들어지므로 1로 초기화
        map[y][x] = '0';
        queue.offer(new Node(y,x));

        while(!queue.isEmpty()) {
            Node node = queue.poll();
            for (int d = 0 ; d < 4; d++) {
                int ny = node.y + dy[d];
                int nx = node.x + dx[d];

                if( ny < 0 || nx < 0 || ny >= N || nx >= N ) continue;
                if(map[ny][nx] != '1') continue;

                cnt++;
               map[ny][nx] = '0';
                queue.offer(new Node(ny,nx));
            }
        }
    } // bfs

    static class Node{
        int y, x;
        Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    } // Node


}
