// **문제**
// 트리의 지름은 트리를 구성하는 노드 중 두 노드 사이의 거리가 가장 긴 것을 말한다. 트리의 지름을 구하시오.
// **입력**
// 1번째 줄에서는 트리의 노드 개수 V(2 ≤ V ≤ 100,000), 2번째 줄부터 V개의 줄에 걸쳐 에지의 정보가 주어진다. 먼저 노드 번호가 주어지고 그다음으로 연결된 에지의 정보를 의미하는 정수가 2개씩(연결된 노드 번호, 거리) 주어진다. 거리는 10,000 이하의 자연수다.
// 예를 들어 2번째 줄에 3 1 2 4 3 -1이 주어질 때 노드 1과 거리가 2인 에지로 연결돼 있고, 노드 4는 거리가 3인 에지로 연결돼 있다는 뜻이다. -1은 더이상 노드가 없으므로 종료한다는 의미다.
// **출력**
// 트리의 지름을 출력한다.
// **문제 분석**
// 가장 긴 경로를 찾는 방법과 관련된 아이디어가 필요한 문제이다. 아이디어는 다음과 같다.
// **가장 긴 경로 찾기 아이디어**
// - 아이디어 1: 임의의 노드에서 가장 긴 경로로 연결돼 있는 노드는 트리의 지름에 해당하는 두 노드 중 하나다.
import java.io.*;
import java.util.*;

public class P1167 {
    static boolean[] visited;
    static int[] distance;  // 거리
    static ArrayList< Edge>[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(token.nextToken());    // 노드 개수

        A = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++){
            A[i] = new ArrayList< Edge>();
        }

        for(int i = 0; i < N; i++){     // A 인접 리스트에 그래프 데이터 저장하기
            token = new StringTokenizer(br.readLine());
            int node = Integer.parseInt(token.nextToken());
            while(true){
                int edge = Integer.parseInt(token.nextToken());
                if(edge == -1) break;
                int value = Integer.parseInt(token.nextToken());
                A[node].add(new Edge(edge, value));
            }
        }

        distance = new int[N + 1];
        visited = new boolean[N + 1];
        BFS(1);

        int Max = 1;
        for (int i = 2; i <= N; i++){   // distance 배열에서 가장 큰 값으로 다시 시작점 설정
            if(distance[Max] < distance[i]){
                Max = i;
            }
        }
        distance = new int[N + 1];
        visited = new boolean[N + 1];
        BFS(Max);   // 새로운 시작점으로 실행
        Arrays.sort(distance);
        System.out.println(distance[N]);
    }

    public static void BFS(int index){   // BFS 구현하기
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(index);
        visited[index] = true;
        while(!queue.isEmpty()){
            int now_node = queue.poll();
            for(Edge i : A[now_node]){
                int e = i.e;
                int v = i.value;
                if (!visited[e]) {
                    visited[e] = true;
                    queue.add(e);
                    distance[e] = distance[now_node] + v;   // 거리 배열 업데이트하기
                }
            }
        }
    }
}

class Edge {
    int e;
    int value;
    public Edge(int e, int value){
        this.e = e;
        this.value = value;
    }
}
