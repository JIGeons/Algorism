/*
월드 나라는 모든 도로가 일방통행이고, 사이클이 없다. 그런데 어떤 무수히 많은 사람이 월드 나라의 지도를 그리기 위해 어떤 시작 도시에서 도착 도시까지 출발해 갈 수 있는 모든 경로를 탐색한다고 한다. 이 지도를 그리는 사람들은 사이가 너무 좋아서 지도를 그리는 일을 모두 마치고 도착 도시에서 만나기로 했다. 어떤 사람은 도착 시간에 만나기 위해 1분도 쉬지 않고 달려야 하는 사람들이 지나는 도로의 수를 계산하는 프로그램을 작성하시오(출발 도시는 들어오는 도로가 0개, 도착 도시는 나가는 도로가 0개다).

**입력**
1번째 줄에 도시의 개수 n(1 ≤ n ≤ 10,000), 2번째 줄에 도로의 개수 m(1 ≤ m ≤ 100,000)이 주어진다. 그리고 3번째 줄에서 m + 2줄까지 다음과 같은 도로의 정보가 주어진다. 처음에는 도로의 출발 도시의 번호가 주어지고, 그다음에는 도착 도시의 번호 그리고 마지막에는 이 도로를 지나는 데 걸리는 시간이 주어진다. 도로를 지나가는 시간은 10,000보다 작거나 같은 자연수다. 그리고 m + 3째 줄에는 지도를 그리는 사람들이 출발하는 출발 도시와 도착 도시가 주어진다. 모든 도시는 출발 도시에서 도달할 수 있고, 모든 도시에서 도착 도시에 도달할 수 있다.

**출력**
1번째 줄에 이들이 만나는 시간, 2번째 줄에 1분도 쉬지 않고 달려야 하는 도로의 수가 몇 개인지 출력하라.

**문제분석**
출발 도시와 도착 도시가 주어지기 때문에 일반적인 위상 정렬이 아닌 시작점을 출발 도시로 지정하고 위상 정렬을 수행하면 출발 도시에서 도착 도시까지 거치는 모든 도시와 관련된 임계 경로값을 구할 수 있다. 단, 이 문제의 핵심은 1분도 쉬지 않고 달려야 하는 도로의 수를 구하는 것인데, 이를 해결하려면 에지 뒤집기라는 아이디어가 필요하다. 에지 뒤집기 아이디어는 그래프 문제에서 종종 나오는 개념이므로 이 문제를 이용해 학습해보자.
 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(token.nextToken());
        token = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(token.nextToken());

        ArrayList<ArrayList<Road>> A = new ArrayList<>();
        ArrayList<ArrayList<Road>> reverseA = new ArrayList<>();

        for(int i = 0; i <= n; i++) {
            A.add(new ArrayList<>());
            reverseA.add(new ArrayList<>());
        }

        int[] indegree = new int[n+1];  // 진입차수 배열

        for(int i = 0; i < m; i++) {
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());
            int t = Integer.parseInt(token.nextToken());
            A.get(s).add(new Road(e, t));
            reverseA.get(e).add(new Road(s, t));    // 역방향 에지 정보 저장하기
            indegree[e]++;                          // 진입 차수 배열 초기화하기
        }

        token = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(token.nextToken());
        int end = Integer.parseInt(token.nextToken());

        // 위상 정렬
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        int[] result = new int[n + 1];
        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (Road next : A.get(now)) {
                indegree[next.city]--;
                result[next.city] = Math.max(result[next.city], result[now] + next.time);
                if (indegree[next.city] == 0) {
                    queue.offer(next.city);
                }
            }
        }


        // 위상 정렬 reverse
        int resultCount = 0;
        boolean visited[] = new boolean[n + 1];
        queue = new LinkedList<>();
        queue.offer(end);
        visited[end] = true;
        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (Road next : reverseA.get(now)) {
                // 1분도 쉬지 않는 도로 체크하기
                if (result[next.city] + next.time == result[now]) {
                    resultCount++;
                    // 중복 카운트 방지를 위해 이미 방문한 적이 있는 노드 제외하기
                    if (visited[next.city] == false) {
                        visited[next.city] = true;
                        queue.offer(next.city);
                    }
                }
            }
        }
        System.out.println(result[end]);
        System.out.println(resultCount);
    }
}
class Road {
    int city;
    int time;
    public Road(int city, int time) {
        this.city = city;
        this.time = time;
    }
}
