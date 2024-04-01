/*
봄 캠프를 마친 김 조교는 여러 도시를 돌며 여행을 다닐 계획이다. 그런데 김 조교는 ‘느림의 미학’을 중요시하는 사람이라 항상 최단 경로로만 이동하는 것은 별로 좋아하지 않는다. 하지만 너무 시간이 오래 걸리는 경로도 그리 매력적인 것만은 아니어서 적당한 타협안인 ‘K번째 최단 경로’를 구하길 원한다. 그를 돕기 위한 프로그램을 작성해 보자.

**입력**
1번째 줄에 n, m, k가 주어진다. (1 ≤ n ≤ 1000, 0 ≤ m ≤ 2000000, 1 ≤ k ≤ 100) n과 m은 각각 김조교가 여행을 고려하고 있는 도시들의 개수와 도시 간에 존재하는 도로의 수다. 이어지는 m개의 줄에는 각각 도로의 정보를 제공하는 3개의 정수 a, b, c가 포함돼 있다. 이것은 a번 도시에서 b번 도시로 갈 때는 c의 시간이 걸린다는 의미다(1 ≤ a, b ≤ n. 1 ≤ c ≤ 1000). 도시의 번호는 1번부터 n번까지 연속해있고, 1번은 시작 도시다.

**출력**
n개의 줄을 출력한다. i번째 줄에 1번 도시에서 i번 도시로 가는 K번째 최단 경로의 소요 시간을 출력한다. 경로의 소요 시간은 경로 위에 있는 도로들을 따라 이동하는 데 필요한 시간들의 합이다. i번 도시에서 i번 도시로 가는 최단 경로는 0이지만, 일반적인 K번째 최단 경로는 0이 아닐 수 있다는 것에 유의한다. 또 K번째 최단 경로가 존재하지 않으면 -1을 출력한다. 최단 경로에 같은 노드가 여러 번 포함될 수 있다.

**문제분석**
시작점과 도착점이 주어지고 이 목적지까지 가는 K번째 최단 경로를 구하는 문제이다. 도시(노드)의 개수는 1,000개, 도로(에지)의 수는 2,000,000이면서 시간 제약이 2초이므로 다익스트라 알고리즘으로 접근해보자. 이 문제에서 가장 고민되는 부분은 최단 경로가 아니라 K번째 최단 경로라는 것이다.
 */

import java.io.*;
import java.util.*;

public class P1854 {
    static final int INF = 100000000;
    public static void main(String[] args) throws IOException{
        int N, M, K;
        int[][] W = new int[1001][1001];    // 그래프 정보 저장 인접 행렬
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        K = Integer.parseInt(token.nextToken());
        PriorityQueue<Integer>[] distQueue = new PriorityQueue[N + 1];
        // 오름차순 정렬 기준 설정
        Comparator<Integer> cp = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 < o2 ? 1: -1;
            }
        };

        // 최단 거리 큐 배열 초기화
        for (int i = 0; i <= N; i++) {
            distQueue[i] = new PriorityQueue<Integer>(K, cp);
        }
        // 인접 행렬에 그래프 데이터 저장하기
        for(int i = 0; i < M; i++) {
            token = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(token.nextToken());
            int b = Integer.parseInt(token.nextToken());
            int c = Integer.parseInt(token.nextToken());
            W[a][b] = c;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));
        distQueue[1].add(0);
        while (!pq.isEmpty()) {
            Node u = pq.poll();
            for (int adjNode = 1; adjNode <= N; adjNode++) {
                // 연결된 모든 노드로 검색하기(시간 복잡도 측면에서 인접 행렬이 불리한 이유)
                if (W[u.node][adjNode] != 0) {
                    // 저장된 경로가 K개가 안 될 때는 그냥 추가하기
                    if (distQueue[adjNode].size() < K) {
                        distQueue[adjNode].add(u.cost + W[u.node][adjNode]);
                        pq.add(new Node(adjNode, u.cost + W[u.node][adjNode]));
                    }
                    // 저장된 경로가 K개이고, 현재 가장 큰 값보다 작을 때만 추가하기
                    else if (distQueue[adjNode].peek() > u.cost + W[u.node][adjNode]) {
                        distQueue[adjNode].poll();  // 기존 큐에서 Max값 먼저 삭제해야 함
                        distQueue[adjNode].add(u.cost + W[u.node][adjNode]);
                        pq.add(new Node(adjNode, u.cost + W[u.node][adjNode]));
                    }
                }
            }
        }
        // K번째 경로 출력하기
        for (int i = 1; i <= N; i++) {
            if (distQueue[i].size() == K) {
                bw.write(distQueue[i].peek() + "\n");
            } else {
                bw.write(-1 + "\n");
            }
        }
        bw.flush();
        bw.close();
    }
}
class Node implements Comparable<Node>{
    int node, cost;
    public Node (int node, int cost) {
        this.node = node;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        return this.cost > o.cost ? 1 : -1;
    }
}
