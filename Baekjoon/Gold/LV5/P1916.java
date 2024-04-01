/*
N개의 도시가 있다. 그리고 한 도시에서 출발해 다른 도시에 도착하는 M개의 버스가 있다. A번째 도시에서 B번째 도시까지 가는 데 드는 버스 비용을 최소화하려고 한다. A번째 도시에서 B번째 도시까지 가는데 드는 최소 비용을 출력하라. 도시의 번호는 1부터 N까지다.

**입력**
1번째 줄에 도시의 개수 N(1 ≤ N ≤ 1,000), 2번째 줄에 버스의 개수 M(1 ≤ M ≤ 100,000)이 주어진다. 그리고 3번째 줄에서 M + 2줄까지 다음과 같은 버스의 정보가 주어진다. 가장 처음에는 그 버스의 출발 도시의 번호가 주어진다. 그다음에는 도착도시의 도시 번호가 주어지고, 그 버스 비용이 주어진다. 버스 비용은 0보다 크거나 같고, 100,000보다 작은 정수다. 그리고 M + 3째 줄에는 우리가 구하고자 하는 구간 출발점의 도시 번호와 도착점의 도시 번호가 주어진다. 출발점에서 도착점을 갈 수 있을 때만 입력으로 주어진다.

**출력**
1번째 줄의 출발 도시에서 도착 도시까지 가는 데 드는 최소 비용을 출력한다.

**문제분석**
시작점과 도착점이 주어지고, 이 목적지까지 가는 최소 비용(최단 거리)를 구하는 문제이다. 또한 버스 비용의 범위가 음수가 아니기 때문에 이 문제는 다익스트라 알고리즘을 이용해 해결할 수 있다. 도시의 개수가 최대 1,000개이므로 인접 행렬 방식으로도 그래프를 표현할 수 있지만, 시간 복잡도나 공간 효율성 측면을 고려해 인접 리스트의 자료구조를 선택했다.
 */

import java.io.*;
import java.util.*;

public class P1916 {
    public static int N, M;
    public static ArrayList<Node> list[];   // 인접 리스트 그래프 표현
    public static int[] dist;               // 최단 거리 배열
    public static boolean[] visit;          // 사용 노드인지 확인하는 배열
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        token = new StringTokenizer(br.readLine());
        M = Integer.parseInt(token.nextToken());

        list = new ArrayList[N + 1];
        dist = new int[N + 1];
        visit = new boolean[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);// 거리 배열을 충분히 큰 수로 초기화하기
        for (int i = 0; i <= N; i++) {
            list[i] = new ArrayList<Node>();
        }
        for(int i = 0; i < M; i++) {    // 주어진 그래프의 예지를 인접 리스트 자료구조에 넣기
            token = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(token.nextToken());
            int end = Integer.parseInt(token.nextToken());
            int weight = Integer.parseInt(token.nextToken());
            list[start].add(new Node(end, weight));
        }
        token = new StringTokenizer(br.readLine());
        int startIndex = Integer.parseInt(token.nextToken());
        int endIndex = Integer.parseInt(token.nextToken());
        bw.write(dijkstra(startIndex, endIndex) + "\n");    // 다익스트라 알고리즘 수행하기
        bw.flush();
        bw.close();
    }
    // 다익스트라 알고리즘
    public static int dijkstra(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));   // 시작할때는 비용이 없으므로 0
        dist[start] = 0;    // 거리 비용도 없으므로 0
        while (!pq.isEmpty()) {
            Node newNode = pq.poll();
            int now = newNode.targetNode;
            if (!visit[now]) {
                visit[now] = true;
                for (Node n : list[now]) {  // 선택 노드 + 비용 < 타깃 노드일 때 값을 업데이트
                    if (!visit[n.targetNode] && dist[n.targetNode] > dist[now] + n.cost) {
                        dist[n.targetNode] = dist[now] + n.cost;
                        pq.add(new Node(n.targetNode, dist[n.targetNode]));
                    }
                }
            }
        }
        return dist[end];
    }
}
class Node implements Comparable<Node>{
    int targetNode, cost;
    public Node (int targetNode, int cost) {
        this.targetNode = targetNode;
        this.cost = cost;
    }
    public int compareTo(Node n) {
        if (this.cost > n.cost) return 1;
        else return -1;
    }
}
