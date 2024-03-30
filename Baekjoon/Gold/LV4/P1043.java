/*
지민이는 파티에 갈 때마다 자기가 가장 좋아하는 이야기를 한다. 이야기는 과장할수록 더 재미있어지므로 되도록이면 과장해 이야기하려 한다. 문제는 몇몇 사람들이 그 이야기의 진실을 안다는 것이다. 지민이는 이야기를 과장한게 들켜서 거짓말쟁이가 되는 건 싫어한다. 그래서 이 사람들이 파티에 왔을 때는 진실을 이야기할 수 밖에 없다.
사람의 수 N이 주어지고, 이야기의 진실을 아는 사람이 주어진다. 그리고 각 파티에 오는 사람들의 번호가 주어진다. 지민이는 모든 파티에 참가해야 한다. 이때 지민이가 거짓말쟁이로 알려지지 않으면서 과장된 이야기를 할 수 있는 파티 개수의 최댓값을 구하는 프로그램을 작성하시오.

**입력**
1번째 줄에 사람의 수 N과 파티의 수 M이 주어진다. 2번째 줄에 이야기의 진실을 아는 사람의 수와 번호가 주어진다. 진실을 아는 사람의 수가 먼저 주어지고, 그 개수만큼 사람들의 번호가 주어진다. 사람들의 번호는 1부터 N까지의 수로 주어진다. 3번째 줄에서 M개의 줄에는 각 파티마다 오는 사람의 수와 번호가 같은 방식으로 주어진다. N, M은 50 이하의 자연수, 진실을 아는 사람의 수와 각 파티마다 오는 사람의 수는 모두 0이상 50이하의 정수이다.

**출력**
1번째 줄에 문제의 정답을 출력한다.

**문제분석**
이 문제의 핵심은 파티에 참석한 사람들을 1개의 집합으로 생각하고, 각각의 파티마다 union 연산을 이용해 사람들을 연결하는 것이다. 이 작업을 하면 1개의 파티에 있는 모든 사람들은 같은 대표 노드를 바라보게 된다. 이후 각 파티의 대표 노드와 진실을 알고 있는 사람들의 각 대표 노드가 동일한지 find 연산을 이용해 확인함으로써 과장된 이야기를 할 수 있는지 판단할 수 있다.
 */

import java.io.*;
import java.util.*;

public class P1043 {
    static int people[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(token.nextToken());    // 사람의 수
        int M = Integer.parseInt(token.nextToken());    // 파티의 수

        people = new int[N+1];
        for (int i=0; i < N+1 ; i++) {
            people[i] = i;
        }

        token = new StringTokenizer(br.readLine());
        int know = Integer.parseInt(token.nextToken()); // 진실을 아는 사람의 수
        int[] knowP = new int[know];
        if (know > 0) { // 진실을 아는 사람의 번호를 저장
            for (int i = 0; i < know; i++) {
                knowP[i] = Integer.parseInt(token.nextToken());
            }
        }

        ArrayList<Integer>[] party = new ArrayList[M];
        // 파티의 정보를 받는다.
        for (int i = 0; i < M; i++) {
            party[i] = new ArrayList<Integer>();
            token = new StringTokenizer(br.readLine());
            int number = Integer.parseInt(token.nextToken());   // 파티에 오는 사람의 수
            for (int j = 0; j < number; j++) {
                party[i].add(Integer.parseInt(token.nextToken()));
            }
        }

        // 각 파티에 참여한 사람들을 1개의 그룹으로 만들기
        for (int i = 0; i < M; i++) {
            int firstPeople = party[i].get(0);
            for (int j = 1; j < party[i].size(); j++) {
                union(firstPeople, party[i].get(j));
            }
        }

        int result = 0;

        // 각 파티의 대표 노드와 진실을 아는 사람들의 대표 노드가 같다면 과장할 수 없음.
        for (int i = 0; i < M; i++) {
            boolean isPossible = true;
            int cur = party[i].get(0);
            for (int j = 0; j < knowP.length; j++) {
                if (find(cur) == find(knowP[j])) {
                    isPossible = false;
                    break;
                }
            }
            if (isPossible) result++;   // 모두 다르다면 결과값 1 증가
        }
        System.out.println(result);
    }

    // union 연산
    public static void union(int a, int b) {
        int A = find(a);
        int B = find(b);
        if (A != B) {
            people[B] = A;
        }
    }

    // find 연산
    public static int find(int a) {
        if(people[a] == a) return a;
        else return people[a] = find(people[a]);    // 재귀 함수의 형태로 구현
    }
}
