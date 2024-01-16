// 문제
// 주몽은 철기군을 양성하기 위한 프로젝트에 나섰다. 그래서 야철대장에게 철기군이 입을 갑옷을 만들라고 명령했다. 야철대장은 주몽의 명령에 따르기 위해 연구에 착수하던 중 갑옷을 만드는 재료들은 각각 고유한 번호가 있고, 갑옷은 2개의 재료로 만드는 데 2가지 재료의 고유한 번호를 합쳐 M(1 ≤ M ≤ 10,000,000)이 되면 갑옷이 만들어진다는 사실을 발견했다. 야철대장은 자신이 만들고 있는 재료로 갑옷을 몇 개나 만들 수 있는지 궁금해졌다. 야철대장의 궁금중을 풀어주기 위해 N(1≤ N ≤ 15,000)개의 재료와 M이 주어졌을 때 몇 개의 갑옷을 만들 수 있는지를 구하는 프로그램을 작성하시오.
// 입력
// 1번째 줄에 재료의 개수 N(1 ≤ N ≤ 15,000), 2번째 줄에 갑옷을 만드는 데 필요한 수 M(1 ≤ M ≤ 10,000,000)이 주어진다. 3번째 줄에는 N개의 재료들이 가진 고유한 번호들이 공백을 사이에 두고 주어진다. 고유한 번호는 100,000보다 작거나 같은 자연수다.
// 출력
// 1번째 줄에 갑옷을 만들 수 있는 개수를 출력한다.
// 문제 분석
// 우선 시간 복잡도를 고려해 보자. 두 재료의 번호의 합, 즉, 크기를 비교하므로 값을 정렬하면 문제를 좀 더 쉽게 풀 수 있다. N의 최대 범위가 15,000이므로 O(nlogn) 시간 복잡도 알고리즘을 사용해도 문제가 없다. 일반적으로 정렬 알고리즘의 시간 복잡도는 O(nlogn)이다. 즉, 정렬을 사용해도 괜찮다. 입력받은 N개의 재룟값을 정렬한 다음 양쪽 끝의 위치를 투 포인터로 지정해 문제를 풀어보자.
// 투 포인터 이동 원칙
//- A[i] + A[j] > M : j—;    // 번호의 합이 M보다 크므로 큰 번호 index를 내린다.
//- A[i] + A[j] < M : i++;    // 번호의 합이 M보다 작으므로 작은 번호 index를 올린다.
//- A[i] + A[j] == M : i++; j—; count++;    //양쪽 포인터를 모두 이동시키고 count를 증가시킨다.

import java.io.*;
import java.util.*;

public class P1940 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(token.nextToken());    // 재료의 갯수

        token = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(token.nextToken());    // 고유 번호

        int N[] = new int[n];
        token = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            N[i] = Integer.parseInt(token.nextToken());
        }

        // 오름차순 정렬
        Arrays.sort(N);

        int count = 0;
        int start = 0;
        int end = n-1;
        // 투 포인터 원칙
        while(start < end) {
            if(N[start] + N[end] > M) {
                end--;
            } else if(N[start] + N[end] < M) {
                start++;
            } else {
                start++;
                end--;
                count++;
            }
        }

        System.out.println(count);
    }
}
