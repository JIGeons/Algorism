/*
초기에 {0}, {1}, {2}, … {n} 이 각각 n + 1개의 집합을 이루고 있다. 여기에 합집합 연산과 두 원소가 같은 집합에 포함돼 있는지를 확인하는 연산을 수행하려고 한다. 집합을 표현하는 프로그램을 작성하시오.

**입력**
1번재 줄에 n(1 ≤ n ≤ 1,000,000), m(1 ≤ m ≤ 100,000)이 주어진다. m은 입력으로 주어지는 연산의 개수다. 다음 m개의 줄에는 각각의 연산이 주어진다. 합집합은 0 a b의 형태로 입력이 주어진다. 이는 a가 포함돼 있는 집합과 b가 포함돼 있는 집합을 합친다는 의미다. 두 원소가 같은 집합에 포함돼 있는지를 확인하는 연산은 1 a b의 형태로 입력이 주어진다. 이는 a와 b가 같은 집합에 포함돼 있는지를 확인하는 연산이다. a와 b는 n이하의 자연수 또는 0이고, 같을 수도 있다.

**출력**
1로 시작하는 입력에 1줄에 1개씩 YES 또는 NO로 결과를 출력한다.

**문제분석**
최대 원소의 개수 1,000,000과 질의 개수 100,000이 큰 편이므로 경로 압축이 필요한 전형적인 유니온 파인드 문제입니다. 앞에서 설명했던 핵심 이론을 실제 코드로 구현하면서 유니온 파인드에 관해 좀 더 정확하게 이해하자.
 */

import java.io.*;
import java.util.*;

public class P1717 {
    static int number[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(token.nextToken());
        int m = Integer.parseInt(token.nextToken());

        number = new int[n+1];

        for (int i = 1; i < n+1; i++) {
            number[i] = i;
        }

        for (int i = 0; i < m; i++) {
            token = new StringTokenizer(br.readLine());
            int question = Integer.parseInt(token.nextToken());
            int a = Integer.parseInt(token.nextToken());
            int b = Integer.parseInt(token.nextToken());
            if (question == 0) {    // 집합 합치기
                union(a, b);
            }
            else {
                if(find(a) == find(b)) {
                    bw.write("YES\n");
                } else {
                    bw.write("NO\n");
                }
            }
        }
        bw.flush();
        bw.close();
    }

    public static void union(int a, int b) {    // 대표 노드끼리 연결하기
        a = find(a);
        b = find(b);
        if (a != b) {
            number[b] = a;
        }
    }

    public static int find(int a) {
        if (a == number[a]) return a;
        else return number[a] = find(number[a]);
    }
}
