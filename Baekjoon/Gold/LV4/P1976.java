/*
동혁이는 친구들과 함께 여행을 가려고 한다. 한국에는 도시가 N개 있고 임의의 두 도시 사이에 길이 있을 수도, 없을 수도 있다. 동혁이는 여행 계획이 주어졌을 때 이 계획대로 여행할 수 있는지를 알아보려한다. 물론 중간에 다른 도시를 경유해 여행할 수도 있다. 예를 들어 도시가 5개 있고, A-B, B-C, A-D, B-D, E-A의 길이 있고, 동혁이의 여행 계획이 E, C, B, C, D라면 E-A-B-C-B-C-B-D라는 여행 경로를 이용해 계획대로 여행할 수 있다. 도시의 개수와 도시 간의 연결 여부가 주어져 있고, 동혁이의 여행 계획에 속한 도시들이 순서대로 주어졌을 때 계획대로 여행이 가능한지 판별하는 프로그램을 작성하시오.

**입력**
1번째 줄에 도시의 수 N이 주어진다(N ≤ 200). 2번째 줄에 여행 계획에 속한 도시들의 수 M이 주어진다(M ≤ 1000). 다음 N개의 줄에는 N개의 정수가 주어진다. i번째 줄의 j번째 수는 i번 도시와 j번 도시의 연결 정보를 의미한다. 1이면 연결된 것이고, 0이면 연결되지 않은 것이다. A와 B가 연결됐으면 B와 A도 연결돼 있다. 마지막 줄에는 여행 계획이 주어진다. 도시의 번호는 1에서 N까지 차례대로 매겨져 있다.

**출력**
1번째 줄에 가능하면 YES, 불가능하면 NO를 출력한다.

**문제분석**
도시의 연결 유무를 유니온 파인드 연산을 이용해 해결할 수 있다는 아이디어를 떠올릴 수 있으면 쉽게 해결할 수 있는 문제이다. 일반적으로 유니온 파인드는 그래프 영역에서 많이 활용되지만, 위 문제와 같이 단독으로도 활용할 수 있다는 점도 참고하자. 이 문제에서는 도시 간 연결 데이터를 인접 행렬의 형태로 주었기 때문에 인접 행렬을 탐색하면서 연결될 때마다 union 연산을 수행하는 방식으로 문제에 접근하면 된다.
 */

import java.io.*;
import java.util.*;

public class P1976 {
    static int parent[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(token.nextToken());
        token = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(token.nextToken());

        int[][] number = new int[n+1][n+1];

        for (int i = 1; i < n + 1; i++) {   // 도시 연결 데이터 저장하기
            token = new StringTokenizer(br.readLine());
            for (int j = 1; j < n+1; j++) {
                number[i][j] = Integer.parseInt(token.nextToken());
            }
        }

        token = new StringTokenizer(br.readLine());
        int[] route = new int[m];
        for (int i = 0; i < m; i++) {   // 여행 도시 정보 저장하기
            route[i] = Integer.parseInt(token.nextToken());
        }

        parent = new int[n+1];
        for (int i = 1; i < n+1; i++) { // 대표 노드를 자기 자신으로 초기화
            parent[i] = i;
        }

        for (int i = 1; i < n+1; i++) { // 인접 행렬에서 도시가 연결돼 있으면 union 실행
            for (int j = 1; j < n+1; j++) {
                if(number[i][j] == 1) union(i,j);
            }
        }

        // 여행 계획 도시들이 1개의 대표 도시로 연결돼 있는지 확인하기
        int index = find(route[0]);
        for (int i = 1; i < route.length; i++) {
            if (index != find(route[i])) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");

    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) parent[b] = a;
    }

    public static int find(int a) {
        if (parent[a] == a) return a;
        else return parent[a] = find(parent[a]);
    }
}
