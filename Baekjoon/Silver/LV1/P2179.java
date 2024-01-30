// **문제**
// 4 x 6 크기의 배열로 표현되는 다음과 같은 미로가 있다.
// 미로의 각 칸에 들어있는 숫자 중 1은 이동할 수 있는 칸, 0은 이동할 수 없는 칸을 나타낸다. 한 칸에서 다른 칸으로 이동할 때는 서로 인접한 칸으로만 이동할 수 있다. 이동한 칸을 셀 때는 시작 위치와 도착 위치를 포함한다. 즉, (1, 1)에서 (4, 6)으로 이동하려면 총 15칸을 지나가야 한다.
// N x M 크기의 미로가 주어질 때 (1, 1)에서 출발해 (N, M)의 위치로 이동하기 위해 지나야 하는 칸 수의 최솟값을 구하는 프로그램을 작성하시오.
// **입력**
// 1번째 줄에 두 정수 N, M(2 ≤ N, M ≤ 100), 그다음 N개의 줄에는 미로의 내용이 M개의 정수로 주어진다.
// **출력**
// 1번째 줄에 지나야 하는 칸 수의 최솟값을 출력한다. 항상 도착 위치로 이동할 수 있을 때만 입력으로 주어진다.
// **문제 분석**
// N, M의 최대 데이터의 크기가 100으로 매우 작기 때문에 시간 제한은 별도로 생각하지 않아도 되는 문제이다. 문제의 요구사항은 지나야 하는 칸 수의 최솟값을 찾는 것이다. 이는 완전 탐색을 진행하며 몇 번째 깊이에서 원하는 값을 찾을 수 있는지를 구하는 것과 동일하다. 따라서 BFS를 사용해 최초로 도달했을 때 깊이를 출력하면 문제를 해결할 수 있다. DFS보다 BFS가 적합한 이유는 BFS는 해당 깊이에서 갈 수 있는 노드 탐색을 마친 후 다음 깊이로 넘어가기 때문이다.

import java.io.*;
import java.util.*;

public class P2178 {
    // 하우상좌를 탐색하기 위한 배열 선언하기
    static int[] dx = { 0, 1, 0, -1};
    static int[] dy = { 1, 0, -1, 0};

    static boolean[][] visited;
    static int[][] A;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());    // 노드 개수
        M = Integer.parseInt(token.nextToken());    // 에지 개수

        A = new int[N][M];
        visited = new boolean[N][M];
        for(int i = 0; i < N; i++){
            token = new StringTokenizer(br.readLine());
            String line = token.nextToken();
            for (int j = 0; j < M; j++){
                A[i][j] = Integer.parseInt(line.substring(j,j+1));
            }
        }

        BFS(0,0);
        System.out.println(A[N-1][M-1]);
    }

    public static void BFS(int i, int j){   // BFS 구현하기
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { i, j });
        visited[i][j] = true;
        while(!queue.isEmpty()){
            int[] now = queue.poll();
            for(int k = 0; k < 4; k++){
                int x = now[0] + dx[k];
                int y = now[1] + dy[k];

                // 좌표 유효성 검사하기
                if (x >= 0 && y >= 0 && x < N && y < M){
                    // 갈 수 있는 칸 && 방문 검사하기
                    if(A[x][y] != 0 && !visited[x][y]) {
                        visited[x][y] = true;
                        A[x][y] = A[now[0]][now[1]] + 1;    // 깊이 업데이트 하기
                        queue.add(new int[] {x, y});
                    }
                }
            }
        }
    }
}
