// **문제**
// 자연수 n이 주어졌을 때 GCD(n, k) = 1(1 ≤ k ≤ n)을 만족하는 자연수의 개수를 구하는 프로그램을 작성하시오.
// **입력**
// 1번째 줄에 자연수 n(1 ≤ n ≤ 10^12)이 주어진다.
// **출력**
// GCD(n, k) = 1(1 ≤ k ≤ n)을 만족하는 자연수의 개수를 출력한다.
// **문제 분석**
// 문제에서 요구하는 GCD(n, k) = 1을 만족하는 자연수의 개수가 바로 오일러 피 함수의 정의이다. 즉, 오일러 피 함수를 잘 구현할 수 있는지를 묻는 문제이다.

import java.io.*;
import java.util.*;

public class P11689 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        long n = Long.parseLong(token.nextToken());
        long result = n;
        for(long p = 2; p <= Math.sqrt(n); p++){    // 제곱근까지만 진행하기
            if(n % p == 0){                         // p가 소인수인지 확인하기
                result = result - result / p;       // 결괏값 업데이트하기
                while(n % p == 0){                  // 2^7 * 11라면 2^7을 없애고 11만 남김.
                    n /= p;
                }
            }
        }
        if (n > 1)                                  // 아직 소인수 구성이 남아 있을 때
            // 반복문에서 제곱근까지만 탐색했으므로 1개의 소인수가 노락되는 케이스
            result = result - result / n;
        System.out.println(result);
    }
}
