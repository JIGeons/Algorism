/*
각각 부피가 A, B, C(1 ≤ A, B, C ≤ 200) 리터인 3개의 물통이 있다. 처음에는 앞의 두 물통은 비어있고, 3번째 물통은 가득(C 리터)차 있다. 이제 어떤 물통에 들어 있는 물을 다른 물통으로 쏟아부을 수 있는데, 이때는 한 물통이 비거나, 다른 한 물통이 가득 찰 때까지 물을 부을 수 있다. 이 과정에서 손실되는 물은 없다고 가정한다. 이와 같은 과정을 거치다 보면 3번째 물통(용량이 C인)에 담겨 있는 물의 양이 변할 수도 있다. 1번째 물통(용량이 A인)이 비어 있을 때 3번째 물통(용량이 C인)에 담겨 있을 수 있는 물의 양을 모두 구하는 프로그램을 작성하시오.

**입력**
1번째 줄에 세 정수 A, B, C가 주어진다.

**출력**
1번째 줄에 공백으로 구분해 답을 출력한다. 각 용량은 오름차순 정렬한다.

**문제분석**
지금까지 접해 봤던 그래프 데이터를 저장하고 저장한 자료구조를 이용하는 방식과 달리, 그래프 원리를 적용해 그래프를 역으로 그리는 방식으로 접근하는 문제이다. A, B, C의 특정 무게 상태를 1개의 노드로 가정하고, 조건에 따라 이 상태에서 변경할 수 있는 이후 무게 상태가 에지로 이어진 인접한 노드라고 생각하고, 문제에 접근해 보자.
 */

import java.io.*;
import java.util.*;

public class P2251 {
    // 6가지의 이동케이스를 표현하기 위한 배열 0(A->B), 1(A->C), 2(B->A), 3(B->C).....
    static int[] Sender = {0 ,0, 1, 1, 2, 2};
    static int[] Receiver = { 1, 2, 0, 2, 0, 1 };

    // A, B의 무게만 있으면 C의 무게가 고정되므로 2개만 체크
    static boolean visited[][];
    static boolean answer[];
    static int now[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine());

        now = new int[3];   // A, B, C 물의 양을 저장하는 배열
        for (int i = 0; i < 3; i++) {
            now[i] = Integer.parseInt(token.nextToken());
        }

        visited = new boolean[201][201]; // 최대 물의 양이 200이므로 201만큼 배열 생성
        answer = new boolean[201];  // 이하 동문

        BFS();

        for (int i = 0; i < answer.length; i++) {
            if (answer[i])
                bw.write(i + " ");
        }
        bw.flush();
        bw.close();
    }

    public static void BFS() {
        Queue<AB> queue = new LinkedList<>();
        queue.add(new AB(0, 0));    // 처음엔 C만 담겨있기 때문에 0, 0
        visited[0][0] = true;   // 1번째 부터 시작할거기 때문에 0번째는 true
        answer[now[2]] = true;  // 처음엔 C가 가득 차있기 때문에 true
        while (!queue.isEmpty()) {
            AB p = queue.poll();
            int A = p.A;
            int B = p.B;
            int C = now[2] - A - B; // C는 전체 물의 양에서 A와 B를 뺀 것
            for (int k = 0; k < 6; k++) {   // A->B, A->C, B->A, B->C, C->A. C->B (6가지의 이동 방법)
                int[] next = { A, B, C };
                next[Receiver[k]] += next[Sender[k]];   // EX) k가 0일때 A에서 B로 넘기는 과정
                next[Sender[k]] = 0;
                if (next[Receiver[k]] > now[Receiver[k]]) { // 물이 넘칠 때(now는 물의 양을 저장해둔 배열)
                    // 초과하는 만큼 다시 이전 물통에 넣어 줌
                    next[Sender[k]] = next[Receiver[k]] - now[Receiver[k]];
                    next[Receiver[k]] = now[Receiver[k]];   // 대상 물통은 최대로 채워 줌
                }
                if (!visited[next[0]][next[1]]) {   // A와 B의 물의 양을 이용해 방문 배열 체크
                    visited[next[0]][next[1]] = true;
                    queue.add(new AB(next[0], next[1]));
                    if(next[0] == 0) {  // A의 물의 양이 0일 때 C의 물의 무게를 정답 변수에 저장
                        answer[next[2]] = true;
                    }
                }
            }
        }
    }
}
// AB 클래스 선언 -> A와 B의 값만 지니고 있으면 C는 유추할 수 있으므로 두 변수만 사용하기
class AB {
    int A;
    int B;
    public AB(int A, int B) {
        this.A = A;
        this.B = B;
    }
}
