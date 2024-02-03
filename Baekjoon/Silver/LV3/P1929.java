// **문제**
// M 이상 N 이하의 소수를 모두 출력하는 프로그램을 작성하시오.
// **입력**
// 1번째 줄에 자연수 M과 N이 빈칸을 사이에 두고 주어진다.(1 ≤ M ≤ N ≤ 1,000,000). M 이상 N 이하의 소수가 1개 이상 있는 입력만 주어진다.
// **출력**
// 1줄에 1개씩, 증가하는 순서대로 소수를 출력한다.

import java.io.*;
import java.util.*;

public class P1929 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(token.nextToken());
        int N = Integer.parseInt(token.nextToken());

        int[] A = new int[N+1];

        for(int i = 2; i < N+1; i++){
            A[i] = i;
        }

        for(int i = 2; i <= Math.sqrt(N); i++){     // 제곱근까지만 수행하기
            if(A[i] == 0) continue;
            for(int j = i+i; j < N + 1; j += i){    // 배수 지우기
                A[j] = 0;
            }
        }

        for(int i = M; i < N+1; i++){
            if(A[i] != 0) System.out.println(A[i]);
        }
    }
}
