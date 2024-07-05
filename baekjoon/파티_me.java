package baekjoon;

// N명의 학생,  X번 마을, M개의 단뱡향 도로, i번째 길을 지나는데 T(i) 시간을 소비함 (1부터100까지)
// 갔다 다시 돌아와야하며 가는 길과 돌아오는 길은 다를 수 있음
// 오고가는데 가장 많은 시간을 소비하는 학생은?

import ch09.전보;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 파티_me {

    static class Node{
        int n;
        int m;
        Node(int n, int m){
            this.n= n;
            this.m= m;
        }
    }

    static int N, M, X, end, start;
    static List<List<Node>> adjList = new ArrayList<>();
    static List<List<Node>> back_adjList = new ArrayList<>();
    static int[] time, shortTerm, back_shortTerm; // 시작점에서부터의 최소 비용
    static boolean[] visit;
    static final int INF = Integer.MAX_VALUE; // cost 배열 초기화 때 사용
    static PriorityQueue<Node> pqueue = new PriorityQueue<>((n1, n2) -> n1.m - n2.m);

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

//        start  = 0;
//        end = N-1;

        for(int i = 0 ; i <= N ; i++){
            adjList.add(new ArrayList<>());
            back_adjList.add(new ArrayList<>());
        }
        time = new int[N+1];
        visit = new boolean[N+1];

        // 입력 M개 -> 인접리스트 구성
        for(int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(br.readLine());

            int n1 = Integer.parseInt(st.nextToken()); // 출발 마을
            int n2 = Integer.parseInt(st.nextToken()); // 도착 마을
            int m = Integer.parseInt(st.nextToken()); // 다리 개수

            adjList.get(n1).add(new Node(n2, m));
            back_adjList.get(n2).add(new Node(n1, m));
        }


        // 다익스트라

//        shortTerm = dijkstra(adjList);
//        back_shortTerm = dijkstra(back_adjList);


        // 왕복 시간은? (max)
        int maxTime = 0;
        for(int i = 1; i <= N; i++) {
            if( time[i] == INF) continue;
            maxTime = Math.max(maxTime, shortTerm[i] + back_shortTerm[i]);
        }
        // 받은 도시만 계산해야함 => 시작도시 빼야함 (-1)
        System.out.println( maxTime );



    }//main

    public static int[] dijkstra(ArrayList<List<Node>> a) {
        Arrays.fill(time, INF);
        pqueue.offer(new Node(X, 0));
        time[X] = 0;

        while(!pqueue.isEmpty()){
            Node node = pqueue.poll();

            if( visit[node.n] ) continue; // 이미 방문한 곳은 skip
            visit[node.n] = true; // 방문 했으니 true 설정

            for(Node n : adjList.get(node.n)) {
                if( visit[n.n] ) continue;
                if( time[n.n] > time[node.n] + n.m){
                    time[n.n] = time[node.n] + n.m;
                    pqueue.offer(new Node(n.n, time[n.n]));
                }
            }
        }
    return time;
    } // dijkstra

}

/*
4 8 2
1 2 4
1 3 2
1 4 7
2 1 1
2 3 5
3 1 2
3 4 4
4 2 3

=> 10
 */