// **문제**
// 어떤 수가 소수의 N 제곱(N ≥ 2)일 때 이 수를 ‘거의 소수’라고 한다. A와 B가 주어질 때 A보다 크거나 같고, B보다 작거나 같은 거의 소수가 몇 개인지 출력하는 프로그램을 작성하시오.
// **입력**
// 1번째 줄에 왼쪽 범위 A와 오른쪽 범위 B가 공백 한 칸을 사아에 두고 주어진다. A의 범위는 10^14보다 작거나 같은 자연수, B는 A보다 크거나 같고 10^14보다 작거나 같은 자연수다.
// **출력**
// 1번째 줄에 거의 소수가 총 몇 개 있는지 출력한다.
// **문제 분석**
// 최대 범위에 해당하는 모든 소수를 구해 놓고, 이 소수들이 입력된 A와 B사이에 존재하는지 판단해 문제를 해결할 수 있다. 입력에서 주어진 범위의 최댓값 10^14의 제곱근인 10^7까지 소수를 탐색해야 한다. 에라토스테네스의 체를 이용해 빠르게 소수를 먼저 구한다. 그 이후에는 주어진 소수들이 A ~ B 범위 안에 존재하는지 판별해 유효한 소수의 개수를 세면 이 문제를 해결할 수 있다.

import java.io.*;
import java.util.*;

public class P1456 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        long A = Long.parseLong(token.nextToken());
        long B = Long.parseLong(token.nextToken());

        long[] num = new long[10000001];     // 10^14의 제곱근이 10^7까지만 만들기
        for(int i = 2; i < num.length; i++){
            num[i] = i;
        }

        for(int i = 2; i <= Math.sqrt(num.length); i++){    // 제곱근까지만 수행하기
            if(num[i] == 0) continue;
            for(int j = i+i; j < num.length; j += i){   // 배수 지우기
                num[j] = 0;
            }
        }
        int count = 0;
        for(int i = 2; i <= 10000000; i++){
            if(num[i] != 0){
                long temp = num[i];
                // 현재 소수읭 제곱근이 Max보다 작을 때를 기준으로 하지만
                // 곱셈이 long의 범위를 넘어갈 수 있어 이항 정리로 처리하기
                while((double)num[i] <= (double) B / (double) temp) {
                    if((double)num[i] >= (double) A / (double) temp){
                        count++;
                    }
                    temp = temp * num[i];
                }
            }
        }

        System.out.println(count);
    }
}
