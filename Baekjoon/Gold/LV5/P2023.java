// 문제
// 수빈이가 세상에서 가장 좋아하는 것은 소수이고, 취미는 소수를 이용해 노는 것이다. 요즘 수빈이가 가장 관심 있어 하는 소수는 7331이다. 7331은 신기하게도 733도 소수, 73도 소수, 7도 소수다. 즉, 왼쪽부터 1자리, 2자리, 3자리, 4자릿수 모두 소수다. 수분이는 이런 숫자를 신기한 소수라고 이름을 붙였다. 수빈이는 N의 자리의 숫자 중 어떤 수들이 신기한 소수인지 궁금해졌다. 숫자 N이 주어졌을 때 N의 자리 숫자 중 신기한 소수를 모두 찾아보자.
// **입력**
// 1번째 줄에 N(1 ≤ N ≤ 8)이 주어진다.
// **출력**
// N의 자리 숫자 중 신기한 소수를 오름차순 정렬해 1줄에 1개씩 출력한다.
// **문제 분석**
// 앞에서 언급했듯이 DFS는 재귀 함수의 형태를 띄고 있다. 여기서는 재귀 함수의 원리는 설명과 함께 DFS를 풀어보자. 재귀 함수를 잘 이해하면 문제 조건에 맞도록 코드를 수정하기가 쉬울 것이다. 재귀 함수를 이용한 DFS 문제를 많이 풀어보자. 이 문제는 재귀 함수에 자릿수 개념을 붙여 구현한다.

import java.io.*;
import java.util.*;

public class P2023 {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        DFS(2, 1);  // 일의 자리 소수는 2, 3, 5, 7이므로 4개의 수에서만 시작
        DFS(3, 1);
        DFS(5, 1);
        DFS(7, 1);
    }

    public static void DFS(int number, int jarisu){
        if(jarisu == N){
            if(isPrime(number)){
                System.out.println(number);
            }
            return;
        }
        for(int i = 1; i < 10; i++){
            if(i % 2 == 0){     // 짝수라면 더 이상 탐색할 필요가 없음.
                continue;
            }
            if (isPrime(number * 10 + i)){  // 소수라면 재귀 함수로 자릿수를 늘림
                DFS(number * 10 + i, jarisu + 1);
            }
        }
    }
    static boolean isPrime(int num){
        for (int i = 2; i <= num / 2; i++){ // 2 * 7이나 7 * 2이나 같으므로 수의 절반 까지만 나머지 검색을 해본다.
            if ( num % i == 0 ){     //  나누어 떨어지는 경우는 소수가 아니므로 false를 return 한다.
                return false;
            }
        }
        return true;
    }
}
