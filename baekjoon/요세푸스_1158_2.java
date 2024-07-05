package baekjoon;

// 자료구조 1. 배열
// 자료구조 2. ArrayList, Queue
// Queue : 살아있는 사람만  관리 + Queue 에서 꺼내서 K번 째면 죽이고 그렇지 않으면 다시 넣음

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


public class 요세푸스_1158_2 {

    static int N, K;
    static Queue<Integer> queue = new ArrayDeque<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 번호를 채우고, 죽으면 0으로 변경
        for (int i = 1; i <= N; i++) {
            queue.offer(i);
        }


        // 풀이
        int aliveCnt = 1; // 살아 있는 번호에서만 증가 <= K 번째를 판단

        sb.append("<");
        while(!queue.isEmpty()){
            int num = queue.poll();

            if((aliveCnt % K) == 0 ){
                sb.append(num).append(", ");
            }else{
                queue.offer(num);
            }
            aliveCnt++;

        }
        sb.setLength(sb.length() - 2); // 뒤에 칸을 두개 조정
        sb.append(">");
        System.out.println(sb);

    }
}
