// **문제**
// 모든 자리가 1로만 이뤄진 두 자연수 A와 B가 주어져 있다. 이때 A와 B의 최대 공약수를 구하는 프로그램을 작성하시오. 예를 들어 A가 111이고, B가 1111일 때 A와 B의 최대 공약수는 1이다. A가 111이고, B가 111111일 경우에는 최대 공약수가 111이다.
// **입력**
// 1번째 줄에 두 자연수 A와 B를 이루는 1의 개수가 주어진다. 입력되는 수는 263보다 작은 작은 자연수다.
// **출력**
// 1번째 줄에 A와 B의 최대 공약수를 출력한다. 정답은 1,000만 자리를 넘지 않는다.
// **문제 분석**
// - 수의 길이를 나타내는 두 수의 최대 공약수는 A와 B의 최대 공약수의 길이를 나타낸다.
// - 즉, 3, 6의 최대 공약수 3은 A(111)와 B(111111)의 최대 공약수(111)의 길이로 나타난다.

import java.io.*;
import java.util.*;

public class P1850 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine());

        long A = Long.parseLong(token.nextToken());
        long B = Long.parseLong(token.nextToken());
        long result = gcd(A, B);

        for(long i = 0; i < result; i++){
            bw.write("1");
        }
        bw.flush();
        bw.close();
    }
    public static long gcd(long a, long b){
        if(b == 0)
            return a;
        else
            return gcd(b, a % b);
    }
}
