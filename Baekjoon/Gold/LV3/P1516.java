/*
숌 회사에서 이번에 새로운 전략 시뮬레이션 게임 세준크래프트를 개발하기로 했다. 핵심적인 부분은 개발이 끝난 상태고, 종족별 균형과 전체 게임 시간 등을 조절하는 부분만 남아 있었다. 게임 플레이에 들어가는 시간은 상황에 따라 다를 수 있기 때문에 모든 건물을 짓는 데 걸리는 최소의 시간을 이용해 근사하기로 했다.
물론, 어떤 건물을 짓기 위해서는 다른 건물을 먼저 지어야 할 수도 있으므로 문제가 단순하지는 않다. 예를 들면 스타크래프트에서 벙커를 짓기 위해서는 배럭을 먼저 지어야 하므로 배럭을 먼저 지은 후 벙커를 지어야 한다. 여러 개의 건물을 동시에 지을 수 있다. 편의상 자원은 무한히 많고, 건물을 짓는 명령을 내리기까지는 시간이 걸리지 않는다고 가정해보자. N개의 건물을 지을 때 각 건물을 짓기 위해 필요한 최소 시간을 출력하시오.

**입력**
1번째 줄에 건물의 종류 수 N(1 ≤ N ≤ 500), 그다음 N개의 줄에는 각 건물을 짓는 데 걸리는 시간과 그 건물을 짓기 위해 먼저 지어야 하는 건물들의 번호가 주어진다. 건물의 번호는 1부터 N까지로 하고, 각 줄을 -1로 끝난다고 가정해보자. 각 건물을 짓는 데 걸리는 시간은 100,000보다 작거나 같은 자연수다.

**출력**
N개의 각 건물이 완성되기까지 걸리는 최소 시간을 출력한다.

**문제분석**
이 문제를 풀기 위해서는 어떤 건물을 짓기 위해 먼저 지어야 하는 건물이 있을 수 있다라는 문장에 주목해야 한다. 각 건물을 노드라고 생각하면 그래프 형태에서 노드 순서를 정렬하는 알고리즘인 위상 정렬을 사용하는 문제라는 것을 눈치챌 수 있다. 건물의 수가 최대 500, 시간 복잡도가 2초 이므로 시간 제한 부담은 거의 없다.
 */

import java.io.*;
import java.util.*;

public class P1516 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(token.nextToken());

        ArrayList<ArrayList<Integer>> A = new ArrayList<>();
        int[] indegree = new int[n+1];  // 진입 차수 배열
        int[] time = new int[n+1];      // 자기 자신을 짓는 데 걸리는 시간

        for (int i = 0; i <= n; i++) {
            A.add(new ArrayList<>());
        }

        for (int i = 1; i <= n; i++) {
            token = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(token.nextToken());  // 건물을 짓는데 걸리는 시간
            while (true) {  // 인접리스트 초기화 하기
                int num = Integer.parseInt(token.nextToken());
                if (num == -1) break;
                A.get(num).add(i);
                indegree[i]++;    // 진입 차수 배열 초기화 하기
            }
        }

        // 위상 정렬
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        int[] result = new int[n + 1];
        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int next : A.get(now)) {
                indegree[next]--;
                // 시간 업데이트하기
                result[next] = Math.max(result[next], result[now] + time[now]);
                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            System.out.println(time[i] + result[i]);
        }
    }
}
