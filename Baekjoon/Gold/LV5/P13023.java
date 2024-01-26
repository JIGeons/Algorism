// **문제**
// BOJ 알고리즘 캠프에는 총 N명이 참가하고 있다. 사람들은 0번부터 N - 1번으로 번호가 매겨져 있고, 일부 사람들은 친구다. 오늘은 다음과 같은 친구 관계를 가진 사람 A, B, C, D, E가 존재하는지 구해보려고 한다.
//- A는 B와 친구다.
//- B는 C와 친구다.
//- C는 D와 친구다.
//- D는 E와 친구다.
// 위와 같은 친구 관계가 존재하는지 여부를 구하는 프로그램을 작성하시오.
// **입력**
// 1번째 줄에 사람의 수 N(5 ≤ N ≤ 2,000)과 친구 관계의 수 M(1 ≤ M ≤ 2,000), 2번째 줄부터 M개의 줄에 정수 a와 b가 주어진다. a와 b는 친구라는 뜻이다(0 ≤ a, b ≤ N - 1, a ≠ b). 같은 친구 관계가 2번 이상 주어지지는 않는다.
// **출력**
// 문제의 조건에 맞는 A, B, C, D, E가 존재할 때 1이 없으면 0을 출력한다.
// **문제 분석**
// N의 최대 범위가 2,000이므로 알고리즘의 시간 복잡도를 고려할 때 좀 자유롭다. 그리고 문제에서 요구하는 A, B, C, D, E의 관계는 재귀 함수의 형태와 비슷하다. 주어진 모든 노드에 DFS를 수행하고 재귀의 깊이가 5이상(5개의 노드가 재귀 형태로 연결)이면 1, 아니라면 0을 출력한다. DFS의 시간 복잡도는 O(V+E)이므로 최대 4,000, 모든 노드를 진행 했을 때 4,000 * 2,000, 즉, 8,000,000이므로 DFS를 사용해도 제한 시간 내에 문제를 풀 수 있다.

import java.io.*;
import java.util.*;

public class P13023 {
    static ArrayList<Integer> A[];
    static boolean friend[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(token.nextToken());
        int M = Integer.parseInt(token.nextToken());
        A = new ArrayList[N];
        friend = new boolean[N];

        for(int i = 0; i < N; i++){
            A[i] = new ArrayList<Integer>();    // 인접 리스트 초기화
        }

        for(int i = 0; i < M; i++){         // 인접 리스트 추가
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());
            A[s].add(e);
            A[e].add(s);
        }

        for(int i = 0; i < N; i++){
            if(DFS(i, 0)){      // A ~ E까지의 친구가 있는 경우라고 했으므로 하나라도 있으면 1을 출력후 break;
                System.out.println(1);
                break;
            } else if (i == N-1){   // 마지막까지 돌았는데도 DFS가 false인 경우는 A~E까지 연결된 친구가 없다는 의미이므로 0을 출력하고 break;
                System.out.println(0);
            }
        }
    }

    public static boolean DFS(int v, int fr){
        friend[v] = true;
        if(fr == 4){        // 4번째 까지 도달하면 A->B->C->D->E 까지 친구가 있으므로 true를 return
            return true;
        }
        for(int i : A[v]){
            if(!friend[i]){ // A->B 로 간 후 B->A로 다시 가는 것을 방지
                if(DFS(i, fr + 1))
                    return true;
            }
        }
        friend[v] = false;      // 마지막 노드 일 경우 해당 노드 방문을 false로 변경 후 E까지의 친구가 없으므로 false를 return
        return false;
    }
}
