package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 다익스트라
// 그래프 자료구조 : 인접 리스트
public class 파티_1238 {

    static int N, M, X, max;
    static final int INF = Integer.MAX_VALUE;
    static List<List<Node>> goList = new ArrayList<>(); // X로 가는
    static List<List<Node>> backList = new ArrayList<>();// X로에서 집으로 되돌아오는
    static PriorityQueue<Node> pqueue = new PriorityQueue<>((n1, n2) -> n1.t - n2.t );
    static int[] goCost;
    static int[] backCost;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        for( int i = 0; i <= N; i++){
            goList.add(new ArrayList<>());
            backList.add(new ArrayList<>());
        }
        goCost = new int[N + 1];
        backCost = new int[N + 1];
        visit = new boolean[N + 1];

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            goList.get(a).add(new Node(b, t));
            // (a -> b) 길 중에 분명히 (집 -> X)로 가는 최단 경로가 존재할 것이고 역으로 뒤집으면 그 길이 (X -> 집) 으로 가는 다익스트라로 처리 가능
            backList.get(b).add(new Node(a, t));
        }

        // 풀이
        max = Integer.MIN_VALUE;
        dijkstra(goList, goCost);
        dijkstra(backList, backCost);

        for(int i = 1; i <= N; i++){ // 두개를 합쳤을 때 가장 큰 녀석
            max = Math.max(max, goCost[i] + backCost[i]);
        }
        System.out.println(max);

    } // main

    static void dijkstra(List<List<Node>> list, int[] cost){
        // cost, visit 초기화
        Arrays.fill(cost, INF);
        Arrays.fill(visit, false);

        // 시작점 X
        cost[X] = 0;
        pqueue.offer(new Node(X, 0));

        while(!pqueue.isEmpty()){
            Node node  = pqueue.poll();
            if( visit[node.v] ) continue;
            visit[node.v] = true;

            for(Node n : list.get(node.v)){
                if(visit[n.v]) continue;
                if(cost[n.v] > cost[node.v] + n.t){
                    cost[n.v] = cost[node.v] + n.t;
                    pqueue.offer(new Node(n.v, cost[n.v]));
                }
            }
        }
    }

    static class Node{
        int v; // 정점 마을(집)
        int t; // 시간
        Node(int v, int t){
            this.v = v;
            this.t = t;
        }
    } // Node
}
