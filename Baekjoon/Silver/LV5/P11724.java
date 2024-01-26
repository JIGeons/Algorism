// 문제
// 방향 없는 그래프가 주어졌을 때 연결 요수(connected Component)의 개수를 구하는 프로그램을 작성하시오.
// **입력**
// 1번째 줄에 노드의 개수 N(1 ≤ N ≤ 1,000)과 에지의 개수 M(0 ≤ M ≤ N x (N-1)/2), 2번째 줄부터 M개의 줄에 에지의 양끝 점 u와 v가 주어진다.(1 ≤ u, v ≤ N, u ≠ v). 같은 에지는 한 번만 주어진다.
// **출력**
// 1번째 줄에 연결 요소의 개수를 출력한다.
// **문제 분석**
// 노드의 최대 개수가 1,000이므로 시간 복잡도 N^2 이하의 알고리즘을 모두 사용할 수 있다. 연결 요소는 에지로 연결된 노드의 집합이며, 한 번의 DFS가 끝날 때까지 탐색한 모든 노드의 집합을 하나의 연결 요소로 판단할 수 있다.

import java.io.*;
import java.util.*;

public class P11724 {
    static ArrayList<Integer>[] A;
    static boolean visited[];
    public static long result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(token.nextToken());
        int M = Integer.parseInt(token.nextToken());

        A = new ArrayList[N + 1];
        visited = new boolean[N + 1];

        for (int i = 0; i <= N; i++){   // 인접 리스트 초기화
            A[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < M; i++){
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());
            A[s].add(e);    // 양방향 에지이므로 양쪽에 에지를 더하기
            A[e].add(s);
        }
        int count = 0;
        for (int i = 1; i <= N; i++){
            if (!visited[i]){   // 방문 하지 않은 노드가 없을 때까지 반복하기
                count++;
                DFS(i);
            }
        }
        System.out.println(count);
    }

    public static void DFS(int v){
        if(visited[v]){
            return;
        }
        visited[v] = true;
        for(int i : A[v]){
            if(visited[i] == false){    // 연결 노드 중 방문하지 않았던 노드만 탐색하기
                DFS(i);
            }
        }
    }
}
