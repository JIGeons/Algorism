// **문제**
// 세준이는 크기가 N x N인 배열 A를 만들었다. 배열에 들어 있는 수는 A[i][j] = i x j이다. 이 수를 1차원 배열 B에 넣으면 B의 크기는 N x N이 된다. B를 오름차순 정렬했을 때 B[k]를 구하라(배열 A와 B인덱스는 1부터 시작한다).
// **입력**
// 1번째 줄에 배열의 크기 N이 주어진다. N은 10^5보다 작거나 같은 자연수다. 2번째 줄에 k가 주어진다. k는 min(10^9, N^2)보다 작거나 같은 자연수다.
// **출력**
// B[k]를 출력한다.
// **문제 분석**
// k의 범위가 1~min(10^9, N^2)이므로 시간 복잡도가 N^2인 알고리즘은 사용할 수 없다. 여기서는 이진 탐색을 사용한다. 이진 탐색으로 중앙값보다 작은 수의 개수를 세면서 범위를 절반씩 줄이는 방법으로 B[k]값을 구한다. 다시 말해 작은 수의 개수가 k - 1개인 중앙값이 정답이다. 작은 수의 개수를 세는 아이디어가 이 문제의 열쇠이다.
// **이진 탐색 조건**
// - 중앙값 크기보다 작은 수가 K보다 작으면 시작 인덱스 = 중앙값 + 1
// - 중앙값 크기보다 작은 수가 K보다 크거나 같으면 종료 인덱스 = 중앙값 - 1, 정답 변수 = 중앙값

import java.io.*;
import java.util.*;

public class P1300 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer token = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(token.nextToken());

        token = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(token.nextToken());

        long start = 1;
        long end = K;
        long ans = 0;

        // 이진 탐색 수행하기
        while(start <= end){
            long middle = (start + end) / 2;
            long cnt = 0;
            // 중앙값보다 작은 수는 몇 개인지 계산하기
            for(int i = 1; i <= N; i++){
                cnt += Math.min(middle / i, N); // 작은 수를 카운트하는 핵심 로직
            }
            if (cnt < K) {
                start = middle + 1;
            } else {
                ans = middle;   // 현재 단계의 중앙값을 정답 변수에 저장하기
                end = middle - 1;
            }
        }
        System.out.println(ans);
    }
}
