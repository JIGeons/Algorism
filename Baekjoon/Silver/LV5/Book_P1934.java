// **문제**
// 두 자연수 A와 B가 있을 때 A의 배수이면서 B의 배수인 자연수를 A와 B의 공배수라고 한다. 이런 공배수 중 가장 작은 수를 최소 공배수라고 한다. 예를 들어 6과 15의 공배수는 30, 60, 90 등이 있으며, 최소 공배수는 30이다. 두 자연수 A와 B가 주어졌을 때 A와 B의 최소 공배수를 구하는 프로그램을 작성하시오.
// **입력**
// 1번째 줄에 테스트 케이스의 개수 T(1 ≤ T ≤ 1,000), 2번째 줄부터 T개의 줄에 걸쳐 A와 B가 주어진다(1 ≤ A, B ≤ 45,000).
// **출력**
// 1번째 줄부터 T개의 줄에 A와 B의 최소 공배수를 입력받은 순서대로 1줄에 1개씩 출력한다.
// **문제 분석**
// 최소 공배수는 A와 B가 주어졌을 때 ‘A * B / 최대 공약수’를 계산해 구할 수 있다. 결국 이 문제는 유클리드 호제법을 이용해 최대 공약수를 궁한 후 두 수의 곱에서 최대 공약수를 나누는 것으로 해결할 수 있다.

import java.io.*;
import java.util.*;

public class P1934 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(token.nextToken());

        for(int i = 0; i < N; i++){
            token = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(token.nextToken());
            int b = Integer.parseInt(token.nextToken());
            int result = a * b / gcd(a, b);
            System.out.println(result);
        }
    }
    public static int gcd(int a, int b){
        if(b == 0)
            return a;
        else return gcd(b, a % b);  // 재귀 함수 형태로 구현
    }
}
