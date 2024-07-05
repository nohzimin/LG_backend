package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class DFSBFS {


    static int N, M, V;
    static List<List<Integer>> adjList = new ArrayList<>();
    public static int[][] matrix = new int[5][5];
    static boolean[] visit; // 중복 방문 방지
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        // 자료 구조 생성

        for ( int i = 0 ; i < N+1 ; i++ ) { // 0 dummy List
            adjList.add(new ArrayList<Integer>()); // 0, 1, 2, 3, 4, ArrayList 가 만들어진다.
        }
//        System.out.println(adjList);


        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }




//        System.out.println(N);
//        System.out.println(M);
//        System.out.println(V);
//        System.out.println(adjList);

        visit = new boolean[N+1];


        dfs(V);
        System.out.println();
        for (int i = 0; i < N+1; i++) {
            visit[i] = false;
        }
        bfs(V);


    } // 메인


    // 재귀호출
    static void dfs(int v){ // V: 시작정점
        // 방문완료
        visit[v] = true;

        // 필요한 처리 수행
        System.out.print( v+" ");


        // 계속 방문을 이어 간다
        List<Integer> dfslist = adjList.get(v);
        dfslist.sort((a1, a2) -> a1 - a2); // adjlist sort. 순서대로..

        for(Integer i : dfslist){
            if(visit[i]) continue;
            dfs(i);
        }

    } // dfs


    static void bfs(int v){ // V: 시작정점

        // 방문완료
        Queue<Integer> bfsQueue = new ArrayDeque<>();
        bfsQueue.offer(v);
        visit[v] = true;

        while ( !bfsQueue.isEmpty() ){
            // 정점 꺼내기
            int result = bfsQueue.poll();

            // 필요한 처리 수행
            System.out.print(result +" ");

            // 계속 방문을 이어 간다
            List<Integer> bfslist = adjList.get(result);
            bfslist.sort((a1, a2) -> a1 - a2);  // adjlist sort. 순서대로..

            for( Integer i : bfslist){
                if (visit[i]) continue;
                bfsQueue.offer(i);
                visit[i] = true;
            }

        }


    } // bfs



}
