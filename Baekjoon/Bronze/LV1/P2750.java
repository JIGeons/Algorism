// 문제
// N개의 수가 주어졌을 때 이를 오름차순 정렬하는 프로그램을 작성하시오.
// 입력
// 1번째 줄에 수의 개수 N(1 ≤ N ≤ 1,000), 2번째 줄부터 N개의 줄에 숫자가 주어진다. 이 수는 절댓값이 1,000보다 작거나 같은 정수다. 수는 중복되지 않는다.
// 출력
// 1번째 줄부터 N개의 줄에 오름차순 정렬한 결과를 1줄에 1개씩 출력한다.
// 문제 분석
// 자바에서는 sort() 함수를 이용해 쉽게 정렬할 수 있지만, 이번에는 정렬을 직접 구현해 문제를 해결해 보자.
// N의 최대 범위가 1,000으로 매우 작기 때문에 O(n^2) 시간 복잡도 알고리즘으로 풀 수 있다. 버블 정렬의 시간 복잡도가 O(n^2)이므로 버블 정렬 알고리즘을 이용해 정렬해도 시간 복잡도 안에서 문제를 해결할 수 있습니다.

import java.io.*;
import java.util.*;

public class P2750 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(token.nextToken());
        int[] sort = new int[N];

        for(int i = 0; i < N; i++){
            token = new StringTokenizer(br.readLine());
            sort[i] = Integer.parseInt(token.nextToken());
        }

        for(int i = N-1; i > 0; i--){
            for(int j = 0; j < i; j++){
                if(sort[j] > sort[j+1]){
                    int tmp = sort[j];
                    sort[j] = sort[j+1];
                    sort[j+1] = tmp;
                }
            }
        }

        for(int i = 0; i < N; i++){
            System.out.println(sort[i]);
        }
    }
}
