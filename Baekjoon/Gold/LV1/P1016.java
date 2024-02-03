// **문제**
// 어떤 수 X가 1보다 큰 제곱수로 나누어떨어지지 않을 때 이 수를 ‘제곱이 아닌 수’ 라고 가정해 보자. 여기서 제곱수는 정수의 제곱이다. min과 max의 값이 주어질 때 min보다 크고, max보다 작은 값 중 ‘제곱이 아닌 수’가 몇 개 있는지 출력하시오.
// **입력**
// 1번째 줄에 두 정수 min과 max가 주어진다.
// **출력**
// 1번째 줄에[mix, max]구간에 제곱이 아닌 수가 몇 개인지 출력한다.
// (1 ≤ min ≤ 1,000,000,000,000, min ≤ max ≤ min + 1,000,000)
// **문제 분석**
// 언뜻 보면 min의 최댓값이 1,000,000,000,000으로 매우 큰 것 같지만 실제로는 min과 max사이의 수들 안에서 구하는 것이므로 ,1,000,000의 데이터만 확인하면 된다. 제곱수 판별을 일반적인 반복문으로 구하면 시간 초과가 발생하므로 에라토스테네스의 체 알고리즘 방식을 제곱수 판별 로직에 적용해 문제를 해결해 보자.

import java.io.*;
import java.util.*;

public class P1016 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        long Min = Long.parseLong(token.nextToken());
        long Max = Long.parseLong(token.nextToken());

        // 최댓값과 최솟값의 차이만큼 배열 선언하기
        boolean[] Check = new boolean[(int) (Max - Min + 1)];

        // 2의 제곱수인 4부터 Max보다 작거나 같은 값까지 반복하기
        for(long i = 2; i * i <= Max; i++){
            long pow = i * i;   // 제곱수
            long start_index = Min / pow;
            if(Min % pow != 0) {
                start_index++;  // 나머지가 있으면 1을 더해야 Min보다 큰 제곱수로 시작 됨.
            }
            for(long j = start_index; pow * j <= Max; j++){ // 제곱수를 true로 변경하기
                Check[(int) ((j * pow) - Min)] = true;
            }
        }
        int count = 0;
        for (int i = 0; i <= Max - Min; i++){
            if(!Check[i]){
                count++;
            }
        }
        System.out.println(count);
    }
}
