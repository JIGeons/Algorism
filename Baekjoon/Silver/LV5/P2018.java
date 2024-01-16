// 문제
// 어떠한 자연수 N은 몇 개의 연속된 자연수의 합으로 나타낼 수 있다. 당신은 어떤 자연수(1 ≤ N ≤ 10,000,000)을 몇 개의 연속된 자연수의 합으로 나타내는 가짓수를 알고 싶다. 이때 사용하는 자연수는 N이어야 한다. 예를 들어 15를 나타내는 방법은 15, 7 + 8, 4 + 5 + 6, 1 + 2 + 3 + 4 + 5이다. 반면, 10을 나타내는 방법은 10, 1 + 2 + 3 + 4이다. N을 입력받아 연속된 자연수의 합으로 나타내는 가짓수를 출력하는 프로그램을 작성하시오.
// 입력
// 1번째 줄에 정수 N이 주어진다
// 출력
// 입력된 자연수 N을 연속된 자연수의 합으로 나타내는 가짓수를 출력한다.
// 문제 분석
// 이 문제는 시간 복잡도 분석으로 사용할 알고리즘의 범위부터 줄여야 한다. 우선 문제에 주어진 시간 제한은 2초이다. 그런데 N의 최댓값은 10,000,000으로 매우 크게 잡혀 있다. 이런 상황에서는 O(nlogn)의 시간 복잡도 알고리즘을 사용하면 제한 시간을 초과하므로 O(n)의 시간 복잡도 알고리즘을 사용해야 한다. 이런 경우 자주 사용하는 방법이 투 포인터이다. 연속된 자연수의 합을 구하는 것이 문제이므로 시작 인덱스와 종료 인덱스를 지정하여 연속된 수를 표현한다.
// 투 포인터 이동 원칙
//- sum > N : sum = sum - start_index; start_index++;
//- sum < N : end_index++; sum = sum + end_index++;
//- sum == N : end_index++; sum = sum + end_index++; count;

import java.io.*;
import java.util.*;

public class P2018 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        long N = Integer.parseInt(token.nextToken());
        long count = 1;
        long sum = 1;
        long start = 1;
        long end = 1;

        while (end != N){
            if ( sum > N ) {
                sum -= start;
                start++;
            } else if (sum < N) {
                end++;
                sum += end;
            } else {
                count++;
                end++;
                sum += end;
            }
        }

        System.out.println(count);
    }
}
