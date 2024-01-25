// 문제
// N개의 수가 주어졌을 때 이를 오름차순 정렬하는 프로그램을 작성하시오
// 입력
// 1번째 줄에 수의 개수 N(1 ≤ N ≤ 10,000,000), 2번째 줄부터 N개의 줄에 숫자가 주어진다. 이 수는 10,000보다 작거나 같은 자연수다.
// 출력
// 1번째 줄부터 N개의 줄에 오름차순 정렬한 결과를 1줄에 1개씩 출력한다.
// 문제 분석
// 이 문제는 N의 최대 개수가 10,000,000으로 매우 크기 때문에 O(nlogn)보다 더 빠른 알고리즘이 필요하다. 문제에서 주어지는 숫자의 크기가 10,000보다 작다는 것을 바탕으로 O(kn)의 시간 복잡도의 기수 정렬을 사용하면 된다는 것을 알 수 있다.

import java.io.*;
import java.util.*;

public class P10989 {
    public static int[] A;
    public static long result;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(token.nextToken());
        A = new int[N];
        for(int i = 0; i < N; i++){
            token = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(token.nextToken());
        }
        br.close();
        Radix_Sort(A, 5);
        for(int i = 0; i < N; i++){
            bw.write(A[i] + "\n");
        }
        bw.flush();
        bw.close();
    }

    public static void Radix_Sort(int[] A, int max_size){
        int[] output = new int[A.length];
        int jarisu = 1;
        int count = 0;
        while (count != max_size) {     // 최대 자릿수만큼 반복하기
            int[] bucket = new int[10];
            for(int i = 0; i < A.length; i++){  // 일의 자리부터 시작
                bucket[(A[i] / jarisu) % 10]++;
            }
            for(int i = 1; i < 10; i++) {   // 합 매열을 이용해 output에 들어갈 index 계산하기
                bucket[i] += bucket[i-1];
            }
            for(int i = A.length - 1; i >= 0; i--){ // 현재 자릿수를 기준으로 정렬하기.
                output[bucket[(A[i] / jarisu % 10)] - 1] = A[i];
                bucket[(A[i] / jarisu % 10)]--;
            }
            for(int i = 0; i < A.length; i++){
                //  다음 자릿수를 이용하기 위해 현재 자릿수 기준 정렬 데이터 저장하기
                A[i] = output[i];
            }
            jarisu = jarisu * 10;   // 자릿수 증가시키기
            count++;
        }
    }
}
