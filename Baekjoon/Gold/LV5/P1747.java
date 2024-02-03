// **문제**
// 어떤 수와 그 수 의 숫자 순서를 뒤집은 수가 일치하는 수를 ‘팰린드롬’이라 부른다. 예를 들어 79191과 324423 등이 팰린드롬 수다. 어떤 수 N (1 ≤ N ≤ 1,000,000)이 주어졌을 때 N보다 크거나 같고 소수이면서 팰린드롬인 수 중 가장 작은 수를 구하는 프로그램을 작성하시오.
// **입력**
// 1번째 줄에 N이 주어진다.
// **출력**
// 1번째 줄에 조건을 만족하는 수를 출력한다.

import java.io.*;
import java.util.*;

public class P1747 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(token.nextToken());

        int[] A = new int[10000001];     // N의 범위 까지 소수 구하기
        for(int i = 2; i < A.length; i++){
            A[i] = i;
        }

        for(int i = 2; i < Math.sqrt(A.length); i++){   // 제곱근 까지만 수행
            if(A[i] == 0) continue;
            for(int j = i + i; j < A.length; j = j + i){   // 배수 지우기
                A[j] = 0;
            }
        }

        for(int i = N; i < A.length; i++){  // N부터 1씩 증가시키면서 소수와 펠린드롬 수가 맞는지 확인하기
            if(A[i] == 0) continue;
            if(isPalindrome(A[i])){
                System.out.println(A[i]);
                break;
            }
        }
    }

    private static boolean isPalindrome(int target){    // 펠린드롬 수 판별 함수
        char[] temp = String.valueOf(target).toCharArray();
        int s = 0;
        int e = temp.length - 1;
        while(s < e){
            if(temp[s] != temp[e])
                return false;
            s++;
            e--;
        }
        return true;
    }
}
