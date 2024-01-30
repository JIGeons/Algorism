// **문제**
// 그래프를 DFS로 탐색한 결과와 BFS로 탐색한 결과를 출력하는 프로그램을 작성하시오. 단, 방문할 수 있는 노드가 여러 개일 경우에는 노드 번호가 작은 것을 먼저 방문하고 더 이상 방문할 수 있는 노드가 없을 때 종료한다. 노드 번호는 1에서 N까지다.
// **입력**
// 1번째 줄에 노드의 개수 N(1 ≤ N ≤ 1,000), 에지의 개수 M(1 ≤ M ≤ 10,000), 탐색을 시작할 노드의 번호 V가 주어진다. 그다음 M개의 줄에는 에지가 연결하는 두 노드의 번호가 주어진다. 어떤 두 노드 사이에 여러 개의 에지가 있을 수 있다. 입력으로 주어지는 에지는 양방향이다.
// **출력**
// 1번째 줄에 DFS를 수행한 결과, 그다음 줄에 BFS를 수행한 결과를 출력한다. V부터 방문된 점을 순서대로 출력하면 된다.
// **문제 분석**
// DFS와 BFS를 구현할 수 있는지 물어보는 기본 문제이다. 앞에서 제대로 공부했다면 충분히 풀 수 있다.

import java.io.*;
import java.util.*;

public class P1260 {
    static ArrayList<Integer> A[];
    static boolean visited[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(token.nextToken());    // 노드 개수
        int M = Integer.parseInt(token.nextToken());    // 에지 개수
        int V = Integer.parseInt(token.nextToken());    // 시작점

        A = new ArrayList[N+1];
        for(int i = 1; i <= N; i++){
            A[i] = new ArrayList<Integer>();    // 리스트 초기화
        }

        for(int i = 0; i < M; i++){
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());
            A[s].add(e);
            A[e].add(s);
        }

        // 번호가 작은 것을 먼저 방문하기 위해 정렬하기
        for(int i = 1; i <= N; i++){
            Collections.sort(A[i]);
        }

        visited = new boolean[N+1];     // 방문 배열 초기화 하기
        DFS(V);
        System.out.println();

        visited = new boolean[N+1];     // 방문 배열 초기화 하기
        BFS(V);
        System.out.println();
    }
    public static void DFS(int Node){   // DFS 구현하기
        System.out.print(Node + " ");
        visited[Node] = true;

        for(int i: A[Node]){
            if(!visited[i]){
                DFS(i);
            }
        }
        return ;
    }

    public static void BFS(int Node){   // BFS 구현하기
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(Node);
        visited[Node] = true;

        while(!queue.isEmpty()) {
            int now_Node = queue.poll();
            System.out.print(now_Node + " ");
            for(int i : A[now_Node]){
                if(!visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
    }
}
