/*
N명의 학생들을 키 순서대로 줄을 세우려고 한다. 각 학생의 키를 직접 재서 정렬하면 간단하겠지만, 마땅한 방법이 없어 두 학생의 키를 비교하는 방법을 사용하기로 했다. 그나마도 모든 학생을 비교해 본 것이 아니라 일부 학생들의 키만을 비교해 봤다. 일부 학생들의 키를 비교한 결과가 주어졌을 때 줄을 세우는 프로그램을 작성하시오.

**입력**
1번째 줄에 N(1 ≤ N ≤ 32,000), M(1 ≤ M ≤ 100,000)이 주어진다. M은 키를 비교한 횟수다. 그다음 M개의 줄에는 키를 비교한 두 학생의 번호 A, B가 주어진다. 이는 학생 A가 학생 B의 앞에 서야 한다는 의미다. 학생들의 번호는 1번부터 N번이다.

**출력**
1번째 줄부터 앞에서부터 줄을 세운 결과를 출력한다. 답이 여러 가지일 경우에는 아무거나 출력한다.

**문제분석**
학생들을 노드로 생각하고, 키 순서 비교 데이터로 에지를 만든다고 생각했을 때 노드의 순서를 도출하는 가장 기본적인 문제이다. 특히 답이 여러 개일 때 아무것이나 출력해도 된다는 전제는 위상 정렬의 결괏값이 항상 유일하지 않다는 알고리즘의 전제와 동일하는 것을 알 수 있다.
 */

import java.io.*;
import java.util.*;

public class P2252 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(token.nextToken());
        int m = Integer.parseInt(token.nextToken());

        ArrayList<ArrayList<Integer>> A = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            A.add(new ArrayList<>());
        }

        int[] indegree = new int[n+1];  // 진입 차수 배열

        for (int i = 0; i < m; i++) {
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());
            A.get(s).add(e);
            indegree[e]++;  // 진입 차수 배열 데이터 저장하기
        }

        Queue<Integer> queue = new LinkedList<>();  // 위상 정렬 수행하기
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        while ( !queue.isEmpty() ) {
            int now = queue.poll();
            System.out.print(now + " ");
            for (int next : A.get(now)) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }
    }
}
