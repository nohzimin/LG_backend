package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 프림 + 인접 행렬
// 메모리 초과 <= 2차원 배열이 너무 커진다, 10만x10만
public class 도시분할계획_1647_Prim2 {

    static int N, M ,result;
    static List<List<Vertex>> adjList = new ArrayList<>();
    static boolean[] visit;
    static PriorityQueue<Vertex> pqueue = new PriorityQueue<>( (v1, v2) -> v1.c - v2.c );

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i = 0; i <= N; i++){
            adjList.add(new ArrayList<>());
        }
        visit = new boolean[N + 1];


        // 간선 => 인접 행렬
        for (int i = 0; i< M; i++){
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adjList.get(v1).add(new Vertex(v2, c));
            adjList.get(v2).add(new Vertex(v1, c));

        }

        visit[1] = true;
       pqueue.addAll(adjList.get(1)); // offerAll() 없음. -> addall() 1번에서 갈 수 있는 모든 정점 다 추가.

        int maxCost = 0;
        int cnt = 1; // 선택 된 정점의 수

        while( !pqueue.isEmpty() ){
            Vertex vertex = pqueue.poll();
            if ( visit[vertex.v] ) continue;
            visit[vertex.v] = true;

            result += vertex.c;
//            maxCost = vertex.c; // 오류 (pqueue안에서 가장 작은 것이지 전체에서 가장 작은게 아님)
            maxCost = Math.max(maxCost, vertex.c);
            cnt++;
            if ( cnt == N ) break;

            for(Vertex v : adjList.get(vertex.v)){ // 꺼낸 녀석의 정점부터 갈 수 있는 모든 녀석
                if ( visit[v.v]) continue;
                pqueue.offer(new Vertex(v.v, v.c));
            }
        }

        System.out.println(result - maxCost);

    } // main

    static class Vertex {
        int v, c;
        Vertex(int v , int c){
            this.v = v;
            this.c = c;
        }
    }


}
