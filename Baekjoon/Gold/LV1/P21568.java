// **문제**
// A, B, C가 주어졌을 때 Ax + By = C를 만족하면서 다음 조건을 만족하는 (x, y) 쌍을 찾으시오.
// - x, y 는 정수
// - -1,000,000,000 ≤ x, y ≤ 1000,000,000
// **입력**
// 1번째 줄에 정수 A, B, C가 주어진다.
// **출력**
// Ax + By = C를 만족하는 x, y를 공백으로 구분해 출력한다. 문제의 조건을 만족하는 (x, y)가 존재하지 않을 때는 -1을 출력한다.
// - 1,000,000 ≤ A, B, C ≤ 1,000,000
// **문제 분석**
// 앞에서 배운 ‘확장 유클리드 호제법’을 그대로 구현하면 되는 문제이다. 핵심 이론을 다시 한번 정확하게 학습하고, 학습 내용을 실전 문제에 적용해 보자.

import java.io.*;
import java.util.*;

public class P21568 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(token.nextToken());
        int B = Integer.parseInt(token.nextToken());
        int C = Integer.parseInt(token.nextToken());
        long gcd = gcd(A, B);
        if( C % gcd != 0){
            System.out.println(-1);
        } else {
            int mok = (int) (C / gcd);
            long[] ret = Excute(A, B);
            System.out.println(ret[0] * mok + " " + ret[1] * mok);
        }
    }
    private static long[] Excute(long a, long b){
        long[] ret = new long[2];
        if(b == 0){
            // x = 1, y = 0으로 설정하고 리턴하기
            ret[0] = 1;
            ret[1] = 0;
            return ret;
        }
        long q = a / b;
        long[] v = Excute(b, a % b);    // 재귀 현태로 유클리드 호제법 수행하기
        ret[0] = v[1];  // 역순으로 올라오면서 x, y 값을 계산하는 로직
        ret[1] = v[0] - v[1] * q;
        return ret;
    }
    private static long gcd(long a, long b){
        while(b != 0){
            long temp = a % b;
            a = b;
            b = temp;
        }
        return Math.abs(a);
    }
}
