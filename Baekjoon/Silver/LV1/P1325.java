/*
해커 김지민은 잘 알려진 어느 회사를 해킹하려고 한다. 이 회사에는 신뢰하는 관계와 신뢰하지 않는 관계로 이루어진 N개의 컴퓨터가 있다. A가 B를 신뢰할 경우 B를 해킹하면 A도 해킹할 수 있다. 이 회사의 컴퓨터의 신뢰하는 관계가 주어졌을 때 한 번에 가장 많은 컴퓨터를 해킹할 수 있는 컴퓨터의 번호를 출력하는 프로그램을 작성하시오.

**입력**
1번째 줄에 N과 M이 들어온다. N은 10,000보다 작거나 같은 자연수, M은 100,000보다 작거나 같은 자연수다. 2번째 줄부터 M개의 줄에 신뢰하는 관계가 ‘A B’와 같은 형식으로 들어오며, ‘A와 B를 신뢰한다’를 의미한다. 컴퓨터는 1번부터 N번까지 번호가 1개씩 매겨져 있다.

**출력**
1번째 줄에 김지민이 한 번에 가장 많은 컴퓨터를 해킹할 수 있는 컴퓨터의 번호를 오름차순 출력한다.

**문제 분석**
N과M의 크기가 작은 편이므로 시간 복잡도와 관련된 제약은 크지 않은 편이다. 이 문제에서 잘 확인해야 할 부분은 신뢰 관계가 A, B라고 했을 때, A가 B를 신뢰한다는 것이다. 또한 가장 많은 컴퓨터를 해킹할 수 있는 컴퓨터는 신뢰를 가장 많이 받는 컴퓨터이다. 그래프의 노드와 에지를 기준으로 이해하면 A라는 노드에서 탐색 알고리즘으로 방문하는 노드가 B, C 라고 하면 B, C는 A에게 신뢰받는 노드가 된다.
 */
import java.io.*;
import java.util.*;

public class P1325 {
    static int N, M;
    static boolean visited[];
    static int answer[];
    static ArrayList<Integer> A[];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        A = new ArrayList[N + 1];
        answer = new int[N + 1];
        for (int i = 1; i <= N ; i++) {
            A[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            token = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(token.nextToken());
            int E = Integer.parseInt(token.nextToken());
            A[S].add(E);
        }
        for (int i = 1; i <= N; i++) {  // 모든 노드로 BFS 실행하기
            visited = new boolean[N + 1];
            DFS(i);
        }
        int maxVal = 0;
        for (int i = 1; i <= N; i++) {
            maxVal = Math.max(maxVal, answer[i]);
        }
        for (int i = 1; i <= N; i++) {
            if (answer[i] == maxVal) {  // answer 배열에서 maxVal와 같은 값을 지닌 index가 정답
                bw.write(i + " ");
            }
        }
        bw.flush();
        bw.close();
    }

    public static void BFS(int index) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(index);
        visited[index] = true;
        while(!queue.isEmpty()) {
            int now_Node = queue.poll();
            for (int i : A[now_Node]) {
                if (visited[i] == false) {
                    visited[i] = true;
                    answer[i]++;    // 신규 노드 인덱스의 정답 배열 값을 증가시킴
                    queue.add(i);
                }
            }
        }
    }

    public static void DFS(int index) {
        visited[index] = true;
        for (int i : A[index]) {
            if (!visited[i]) {
                answer[i]++;    // i가 해킹할 수 있는 컴퓨터 숫자 증가
                DFS(i);
            }
        }
    }
}
