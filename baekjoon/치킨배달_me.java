package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 최단 거리 구하기 조합 (Comb)
// 살아남은 치킨집이 M개 일때 도시의 치킨 거리의 최솟값 출력
public class 치킨배달_me {

    static int N, M, result; // 도시 크기 N x N, 살아남을 치킨집 M 개
    static int[][] city;
    static ArrayList<Point> home;
    static ArrayList<Point> chicken;
    static boolean[] open; // 살아남은 치킨 집 = true


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        city = new int[N][N];
        home = new ArrayList<>();
        chicken = new ArrayList<>();

        // 미리 집과 치킨집에 해당하는 좌표를 ArrayList에 넣어둠..
        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j++){
                city[i][j] = Integer.parseInt(st.nextToken());


            }
        }





    } // main

    static class Point {
        int y;
        int x;
        Point(int y, int x){
            this.y = y;
            this.x = x;
        }
    } // Collection Point
}
