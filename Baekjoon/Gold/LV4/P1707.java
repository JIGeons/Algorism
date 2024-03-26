/*
각 집합에 속한 노드끼리 서로 인접하지 않는 두 집합으로 그래프의 노드를 나눌 수 있을 때 이 그래프를 ‘이분 그래프(bipartie graph)’라고 한다. 그래프가 입력으로 주어졌을 때 이 그래프가 이분 그래프인지 여부를 판별하는 프로그램을 작성하시오.

**입력**
입력은 여러 개의 사례로 구성돼 있는데, 1번째 줄에 테스트 케이스의 개수 K(2 ≤ K ≤ 5)가 주어진다. 각 사례의 1번째 줄에 그래프의 노드의 개수 V(1 ≤ V ≤ 20,000)와 에지의 개수 E(1 ≤ E ≤ 200,000)가 빈칸을 사이에 두고 순서대로 주어진다. 각 노드에는 1부터 V까지 차례로 번호가 붙어 있다. 이어서 2번째 줄부터 E개의 줄에 걸쳐 에지와 관련된 정보가 주어지는데, 각 줄에 인접한 노드의 번호가 공백 문자를 사이에 두고 주어진다.

**출력**
K개의 줄에 걸쳐 입력으로 주어진 그래프가 이분 그래프이면 YES, 아니면 NO를 순서대로 출력한다.

**문제 분석**
노드의 집합을 2개로 나누는데, 인접한 노드끼리 같은 집합이 되지 않도록 적절하게 임의로 분할할 수 있다고 한다. 잘 생각해 보면 트리의 경우에는 항상 이분 그래프가 된다는 것을 알 수 있다. 사이클이 발생하지 않으면 탐색을 하면서 다음 노드를 이번 노드와 다른 집합으로 지정하면 되기 때문이다. 단, 사이클이 발생했을 때는 이런 이분 그래프가 불가능할 때가 있다. 그러므로 탐색한 노드에 다시 접근하게 된다면 이분 그래프가 불가능 하다.
 */

import java.io.*;
import java.util.*;

public class P1707 {
    static boolean visited[];
    static int check[];
    static boolean result;
    static ArrayList<Integer> A[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(token.nextToken());
        for (int i = 0; i < K; i++) {
            token = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(token.nextToken());
            int E = Integer.parseInt(token.nextToken());

            A = new ArrayList[V + 1];
            check = new int[V + 1];

            for (int j = 1; j <= V; j++) {
                A[j] = new ArrayList<>();
            }
            for (int j = 1; j <= E; j++) {  // 인접 리스트로 그래프 저장하기
                token = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(token.nextToken());
                int e = Integer.parseInt(token.nextToken());
                A[s].add(e);
                A[e].add(s);
            }

            result = true;
            visited = new boolean[V + 1];

            // 주어진 그래프가 1개로 연결돼 있다는 보장이 없으므로 모든 노드에서 수행하기
            for (int j = 1; j <= V; j++) {
                DFS(j);
            }
            if(result) {
                bw.write("YES\n");
            } else {
                bw.write("NO\n");
            };
        }
        bw.flush();
        bw.close();
    }

    public static void DFS(int index) {
        visited[index] = true;
        for (int i : A[index]) {
            if (!visited[i]) {
                // 인접한 노드는 같은 집합이 아니므로 이전 노드와 다른 집합으로 처리하기
                check[i] = (check[index] + 1) % 2;
                DFS(i);
            } else if (check[index] == check[i]){
                result = false;
            }
        }
    }
}
