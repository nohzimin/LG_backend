package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 쿠르스칼 + 간선 리스트(ArrayList)
public class 도시분할계획_1647_Kruskal {

    static int N, M ,result;
    static int[] parent;
    static List<Edge> edges = new ArrayList<>();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];

        // 간선 리스트 구성
        for (int i = 0; i< M; i++){
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges.add(new Edge(v1,v2,c));
        }

        // 풀이
        makeSet();
        Collections.sort(edges, (e1, e2) -> e1.c - e2.c); // Comparator Interface

        int maxCost = 0;
        int cnt = 0; // 선택 된 간선의 수
        for( int i = 0; i < M; i++ ){
            int v1 = edges.get(i).v1;
            int v2 = edges.get(i).v2;
            int c = edges.get(i).c;

            if ( union (v1, v2) ){ // 사이클이 생기지 않는 유효한 간선 선택
                result += c;
                maxCost = c; // 현재까지의 가장 큰 비용

                cnt++;
                if( cnt == N-1 ) break;
            }
        }
        System.out.println(result - maxCost);

    } // main

    static void makeSet(){
        for(int i=0;i<N;i++){
            parent[i]=i;
        }
    }
    static int findSet(int x){
        if (parent[x] == x) return x;
        else return parent[x] = findSet(parent[x]); // Path compressoion
    }
    static boolean union(int x,int y){
        int px = findSet(x);
        int py = findSet(y);

        if(px == py) return false; // 사이클이 발생한다
        if(py> px) parent[py] = px;
        else parent[px] = py;
        return true;
    }
    static class Edge{
        int v1, v2, c;
        Edge(int v1, int v2, int c){
            this.v1 = v1;
            this.v2 = v2;
            this.c = c;
        }
    }


}
