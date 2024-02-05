/**
 * **문제**
 * 1번부터 N번까지의 도시와 M개의 단방향 도로가 존재하고, 모든 도로의 거리는 1인 도시가 있다. 도시 X로부터 출발해 도달할 수 있는 모든 도시 중 최단 거리가 정확히 K인 모든 도시들의 번호를 출력하시오(출발 도시 X에서 출발 도시 X로 가는 최단 거리는 항상 0이다).
 * 예를 들어 N = 4, K = 1, X = 1일 때 다음과 같이 그래프가 구성돼 있다고 가정해보자.
 * 이때 1번 도시에서 출발해 도달할 수 있는 도시 중 최단 거리가 1인 도시는 2번과 3번 도시다. 4번 도시는 최단 거리가 2이므로 출력하지 않는다.
 *
 * **입력**
 * 1번째 줄에 도시의 개수(N), 도로의 개수(M) 거리 정보(K), 출발 도시의 번호(X)가 입력된다(2 ≤ N ≤ 300,000, 1 ≤ M ≤ 1,000,000, 1 ≤ K <300,000, 1 ≤ X ≤ N). 이후 M개의 줄에 걸쳐 2개의 자연수 A, B가 공백으로 구분돼 주어진다. A번 도시에서 B번 도시로 이동하는 단방향 도로가 존재한다는 뜻이다(1 ≤ A, B ≤ N). 단, A,와 B는 같을 수 없다.
 *
 * **출력**
 * X로부터 출발해 도달 가능한 도시 중 최단 거리가 K인 모든 도시의 번호를 1줄에 1개씩 오름차순으로 출력한다. 해당하는 도시가 1개도 존재하지 않으면 -1을 출력한다.
 *
 * **문제 분석**
 * 모든 도로의 거리가 1이므로 가중치가 없는 인접 리스트로 이 그래프를 표현할 수 있다. 도시의 개수가 300,000, 도로의 최대 크기가 1,000,000이므로 BFS 탐색을 수행하면 이 문제를 시간 복잡도 안에서 해결할 수 있다.
 */

import java.io.*;
import java.util.*;

public class P18352 {
    static int visited[];
    static ArrayList<Integer>[] A;
    static int N, M, K, X;
    static List<Integer> answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());    // 노드의 수
        M = Integer.parseInt(token.nextToken());    // 에지의 수
        K = Integer.parseInt(token.nextToken());    // 목표 거리
        X = Integer.parseInt(token.nextToken());    // 시작점

        A = new ArrayList[N + 1];
        answer = new ArrayList<>();

        for(int i = 1; i <= N; i++){
            A[i] = new ArrayList<Integer>();
        }
        for(int i = 0; i < M; i++){
            token = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(token.nextToken());
            int E = Integer.parseInt(token.nextToken());
            A[S].add(E);
        }
        visited = new int[N + 1];   // 방문 배열 초기화 하기
        for(int i = 0; i <= N; i++){
            visited[i] = -1;
        }
        BFS(X);
        for(int i = 0; i <= N; i++){
            if(visited[i] == K){
                answer.add(i);
            }
        }
        if(answer.isEmpty()){
            System.out.println("-1");
        } else {
            Collections.sort(answer);
            for(int temp : answer){
                System.out.println(temp);
            }
        }
    }
    // BFS 구현하기
    private static void BFS(int Node) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(Node);
        visited[Node]++;
        while (!queue.isEmpty()) {
            int now_Node = queue.poll();
            for(int i : A[now_Node]) {
                if(visited[i] == -1){
                    visited[i] = visited[now_Node] + 1;
                    queue.add(i);
                }
            }
        }
    }
}
