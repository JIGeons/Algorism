// 문제
// N개의 수 A1, A2, …, AN이 주어졌을 때 연속된 부분의 합이 M으로 나누어 떨어지는 구간의 개수를 구하는 프로그램을 작성하시오. 즉, Ai + … + Aj(i ≤ j)의 합이 M으로 나누어떨어지는 (i, j) 쌍의 개수를 구하시오.
// 입력
// 1번째 줄에 N과 M(1 ≤ N ≤ 106, 2 ≤ M ≤ 103), 2번째 줄에 N개의 수 A1, A2, …, AN이 주어진다(0 ≤ Ai ≤ 109).
// 출력
// 1번째 줄에 연속된 부분의 합이 M으로 나누어 떨어지는 구간의 개수를 출력한다.
// 문제 분석
// N의 최댓값이 106이라 연산량이 작게 느껴질 수 있다. 하지만 잠시 생각해 보면 106개의 수에 대하여 모든 구간 합을 구해야 하므로 1초 안에 연산하기는 어렵다. 여기서도 구간 합 배열을 이용해야한다. 이 문제의 핵심 아이디어는 다음과 같다
// 나머지 합 문제 풀이의 핵심 아이디어
//- (A + B) % C은 ((A % C) + (B % C)) % C와 같다. 다시 말해 특정 구간 수들의 나머지 연산을 더해 나머지 연산을 한 값과 이 구간 합의 나머지 연산을 한 값은 동일하다.
//- 구간 합 배열을 이용한 식 S[i] - S[j]는 원본 배열의 j + 1 부터 i까지의 구간 합이다.
//- S[i] % M의 값과 S[j] % M의 값이 같다면 (S[i] - S[j]) % M은 0이다. 즉, 구간 합 배열의 원소를 M으로 나눈 나머지로 업데이트하고 S[i]와 S[j]가 같은 (i, j)쌍을 찾으면 원본 배열에서 j + 1 부터 i까지의 구간 합이 M으로 나누어떨어진다는 것을 알 수 있다.

import java.io.*;
import java.util.*;

public class P10986 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(token.nextToken());
        int M = Integer.parseInt(token.nextToken());

        long S[] = new long[N+1];   // 구간 합 배열
        long C[] = new long[M];  // 나머지 배열

        token = new StringTokenizer(br.readLine());
        for (int i=1; i <= N; i++){
            S[i] = S[i-1] + Integer.parseInt(token.nextToken());    // 구간 합 배열 구하기
            C[(int)(S[i] % M)]++;  // 구간 합 배열의 나머지 갯수
        }

        long answer = C[0];
        for (int i=0; i < M; i++) {
            if (C[i] > 1)   // 갯수가 2 이상일 때
                answer += C[i] * (C[i] - 1) / 2;    // n개 중 2개를 뽑는 경우의 수 계산 공식 n * n-1 / 2
        }

        System.out.println(answer);
    }
}
