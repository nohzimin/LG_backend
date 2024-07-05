package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 조합 (Comb)
// 집, 치킨집 별도의 자료구조 (ArrayList)
public class 치킨배달_15686_NP_MEMOI {

    static int N, M, houseSize, srcSize, min;
    static List<int[]> house, src;
    static int[] index; // np
    static int[][] memoi; // 미리 계산된 집- 치킨집 거리

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        house = new ArrayList<>(); // 집
        src = new ArrayList<>(); // 치킨 집


        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int a = Integer.parseInt(st.nextToken());
                if (a == 1) house.add(new int[]{i, j});
                else if (a == 2) src.add(new int[]{i, j});

            }
        }


        // 풀이
        min = Integer.MAX_VALUE;
        houseSize = house.size();
        srcSize = src.size();

        memoi = new int[houseSize][srcSize]; // 집 x 치킨 집
        for( int i = 0 ; i < houseSize ; i++){ // 각각의 집에 대해서
           int[] h = house.get(i);

            for( int j = 0 ; j < srcSize ; j++){ // index 배열
                int[] c = src.get(j);
                memoi[i][j] = Math.abs(h[0] - c[0]) + Math.abs(h[1] - c[1]);

            }

        }


        // NP
        index = new int[srcSize]; // 치킨 집의 수만큼 배열을 생성, 전부 00000..00
        // M개 만큼 뽑고 싶다. (조합)
        for( int i = srcSize - M; i < srcSize; i++ ) { // 000011111 (M:5)
            index[i] = 1;
        }
        // index 배열은 최소를 의미 asc 로 정렬된 상태

        while(true){

            // 조합 1개 완성 상태
            int sum = 0;
            for( int i = 0 ; i < houseSize ; i++){ // 각각의 집에 대해서
                int minDist = Integer.MAX_VALUE;

                for( int j = 0 ; j < srcSize ; j++){ // index 배열
                    if( index[j] == 1) { // 1이 되면 뽑힌거.
                        minDist = Math.min(minDist, memoi[i][j]  );
                    }
                }
                sum += minDist;
            }
            min = Math.min(min, sum);

            if( ! np() ) break;

        }


        System.out.println(min);


    } //main

    static boolean np() {
        // 3
        // 맨 뒤에서 출발
        int i, j, k;
        i = j = k = srcSize - 1;


        // 뒤보다 앞이 크거나 같으면( 내림차순으로 정렬되어 있으면 ) 계속 가다가 그렇지 않으면 멈춘다.
        // 5 <-- 4 <-- 2 까지는 계속
        // 1 X 5 <-- 4 <-- 2
        while( i > 0 && index[i-1] >= index[i] ) --i;

        // 맨 앞까지 왔으면 종료
        if( i == 0 ) return false;

        // 현재 src[i-1] 이 src[i] 보다 작은 상태
        // src[i] 이후 항목 (src[i] 보다 작은) 과 src[i-1]과 비교 필요

        // 맨 뒤에서 출발
        // i 이전 항목 중 src[i-1] 보다 작은 것은 무시하고, 큰 것이 있으면 멈춤
        //  만약 큰 것이 있으면 그것과  없으면 src[i] 와 교환
        while( index[i-1] >= index[j]) --j;
        swap( index, i-1, j );

        // i 부터 맨 뒤까지 reverse
        while( i < k ) {
            swap(index, i++, k--);
        }
        return true;

    }
    // 일반화된 메소드(src 가 아닌 array)
    static void swap(int array[], int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }







}
