/*
에지의 가중치가 10 이하의 자연수인 방향 그래프가 있다. 이 그래프의 시작점에서 다른 모든 노드로의 최단 경로를 구하시오.

**입력**
1번째 줄에 노드의 개수 V와 에지의 개수 E가 주어진다(1 ≤ V ≤ 20,000, 1 ≤ E ≤ 300,000). 모든 노드에는 1부터 V까지 번호가 매겨져 있다. 2번째 줄에 출발 노드의 번호 K가 주어진다(1 ≤ K ≤ V). 3번째 줄에서 E개의 줄에 걸쳐 각 에지의 정보(u, v, w)가 순서대로 주어진다. 이는 u에서 v로 가는 가중치 w인 에지가 존재한다는 뜻으로, u와 v는 서로 다르다. 두 노드 사이에 에지가 2개 이상 존재할 수 있다는 것에 유의하자.

**출력**
1번째 줄부터 v개의 줄에 걸쳐, i번째 줄에 i번 노드까지 최단 경로값을 출력한다. 시작점은 0, 경로가 없을 때는 INF를 출력한다.

**문제분석**
시작점과 다른 노드와 관련된 최단 거리를 구하는 문제로, 다익스트라 알고리즘의 가장 기본적인 형태를 구현할 수 있는지를 묻고 있다. 앞에서 배운 핵심 이론을 이용해 다익스트라 알고리즘을 코드로 구현해보자.
 */

import java.io.*;
import java.util.*;

public class P1753 {
    public static int V, E, K;
    public static int distance[];
    public static boolean visited[];
    public static ArrayList<Edge> list[];
    public static PriorityQueue<Edge> q = new PriorityQueue<Edge>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        V = Integer.parseInt(token.nextToken());
        E = Integer.parseInt(token.nextToken());

        token = new StringTokenizer(br.readLine());
        K = Integer.parseInt(token.nextToken());

        distance = new int[V + 1];
        visited = new boolean[V + 1];
        list = new ArrayList[V + 1];
        for (int i = 0; i <= V; i++) {
            list[i] = new ArrayList<Edge>();
        }
        for (int i = 0; i <= V; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        // 가중치가 있는 인접리스트 초기화하기
        for (int i = 0; i < E; i++) {
            token = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(token.nextToken());
            int v = Integer.parseInt(token.nextToken());
            int w = Integer.parseInt(token.nextToken());
            list[u].add(new Edge(v, w));
        }
        q.add(new Edge(K, 0));  // K를 시작점으로 설정하기
        distance[K] = 0;
        while (!q.isEmpty()) {
            Edge current = q.poll();
            int c_v = current.vertex;
            if (visited[c_v]) continue; // 이미 방문한 적이 있는 노드는 다시 큐에 넣지 않음
            visited[c_v] = true;
            for (int i = 0; i <list[c_v].size(); i++) {
                Edge tmp = list[c_v].get(i);
                int next = tmp.vertex;
                int value = tmp.value;
                if (distance[next] > distance[c_v] + value) {   // 최소 거리로 업데이트하기
                    distance[next] = value + distance[c_v];
                    q.add(new Edge(next, distance[next]));
                }
            }
        }
        // 거리 배열 출력하기
        for (int i = 1; i <= V; i++) {
            if (visited[i]) System.out.println(distance[i]);
            else System.out.println("INF");
        }
    }
}
class Edge implements Comparable<Edge>{
    int vertex, value;
    Edge(int vertex, int value) {
        this.vertex = vertex;
        this.value = value;
    }
    public int compareTo(Edge e) {
        if (this.value > e.value) return 1;
        else return -1;
    }
}
