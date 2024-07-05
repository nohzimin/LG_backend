package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 원숭이는 어떤 좌표에서 말처럼 뛰거나 사방으로 움직이거나 하는 2가지 선택이 가능하다. 단 말처럼 뛰는 경우는 K만큼만 가능하다
// bfs() 구현 시 어떤 한 좌표에서 갈 수 있는 모든 선택 ( 말처럼 뛰거나 또는 사방으로 탐색하거나 ) 만큼 queue 에 담아야 한다.
// 말처럼 뛰는 경우는 K 를 이전보다 하나 줄여가고, 사방으로 이동하는 경우는 K 를 그대로 유지한다. visit 도 동일하게 표현
// 갈 수 있을 때까지 계속 bfs() 탐색을 통해서 이동한다. 이동하는 과정에 우하 지점에 도달하면 성공, queue 가 비우고, 도달 못하면 실패
// 가중치가 없는 최단 경로 => bfs

public class 말이되고픈원숭이_1600 {
    static int K, W, H;
    static int[][] map;
    // visit 관리 항목 y, x, 말이동 가능(남은) 횟수
    // visit 로 이전 방문했던 좌표를 새로 가지 않는 이유는 방문하면 이전 방문과 동일한 작업이 반복되기 때문
    // 이 문제는 같은 y, x 이라도 말이동 횟수 남은 것에 따라 동일한 방문이 반복되지 않을 수 있다.
    // visit[3][2][3] <= (3,2) 좌표에서 K 가 3일 때 갈 수 있는 모든 경우의 수는 같다
    static boolean[][][] visit;

    // 상,하,좌,우
    static int[] dy = { -1, 1, 0, 0 };
    static int[] dx = {  0, 0,-1, 1 };

    static int[] hdy = { -2, -2, -1, -1,  2,  2,  1,  1 };
    static int[] hdx = { -1,  1, -2,  2, -1,  1, -2,  2 };

    static Queue<Node> queue = new ArrayDeque<Node>();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][W];
        visit = new boolean[H][W][K+1]; // 말로 뛸 수가 K 개 남은 것부터 처리하려고 <= visit[0][0][K] = true 형태로 표현

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        bfs();
    }

    static void bfs() {

        // 시작좌표 처리
        visit[0][0][K] = true;
        queue.offer( new Node(0, 0, K, 0) );

        while( !queue.isEmpty() ) {
            Node node = queue.poll();

            // 목표 도달
            if( node.y == H - 1 && node.x == W - 1 ) {
                System.out.println(node.d);
                return;
            }

            // 탐색 #1 - 상하좌우
            for (int i = 0; i < 4; i++) {
                int ny = node.y + dy[i];
                int nx = node.x + dx[i];
                if( ny < 0 || nx < 0 || ny >= H || nx >= W || map[ny][nx] == 1
                        || visit[ny][nx][node.k] ) continue;
                visit[ny][nx][node.k] = true;
                queue.offer(new Node(ny, nx, node.k, node.d + 1 )); // k 그대로, d 는 증가
            }

            // 탐색 #2 - 말
            if( node.k == 0 ) continue;

            // visit 체크는 현재 node.k 보다 1작은 것을 체크한다.
            // 새로 방문할 node 로 말 이동한다는 건 현재 node의 좌표에서 k 를 하나 줄이고 k-1 인 상태로 가는 것이므로, 이전에 같은 visit가 있었는지 체크하는 것도 동일하게 k-1 로 확인
            // 방문하지 않았을 경우, k-1 로 새로운 Node 를 생성
            for (int i = 0; i < 8; i++) {
                int ny = node.y + hdy[i];
                int nx = node.x + hdx[i];
                if( ny < 0 || nx < 0 || ny >= H || nx >= W
                        || map[ny][nx] == 1
                        || visit[ny][nx][node.k - 1]
                ) continue;

                visit[ny][nx][node.k - 1] = true;
                queue.offer(new Node(ny, nx, node.k - 1, node.d + 1 )); // k 감소, d 는 증가
            }
        }

        System.out.println(-1);
    }

    static class Node{
        int y, x, k, d;
        Node( int y, int x, int k, int d ) {
            this.y = y; this.x = x; this.k = k; this.d = d;
        }
    }
}