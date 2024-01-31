// **문제**
// N개의 정수 A[1], A[2], …, A[N]이 주어져 있을 때 이 안에 X라는 정수가 존재하는지 알아내는 프로그램을 작성하시오.
// **입력**
// 1번째 줄에 자연수 N(1 ≤ N ≤ 100,000), 그다음 줄에 N개의 정수 A[1], A[2], …, A[N]이 주어진다. 그다음 줄에 M(1 ≤ N ≤ 100,000), 그다음 줄에 M개의 수들이 주어지는데, 이 수들이 A 안에 존재하는지 알아내면 된다. 모든 정수의 범위는 -231 보다 크거나 같고, 231보다는 작다.
// **출력**
// M개의 줄에 답을 출력한다. 존재하면 1, 존재하지 않으면 0을 출력한다.
// **문제 분석**
// N의 최대 범위가 100,000이므로 단순 반복문으로는 이 문제를 풀 수 없다. 이진 탐색을 적용하면 O(nlogn) 시간 복잡도로 해결할 수 있으므로 이진 탐색을 적용하자. 앞에서 언급했듯이 이진 탐색은 정렬을 가정하므로 정렬 함수도 사용한다.
// → 자바의 기본 정렬을 O(nlogn)의 시간 복잡도를 가지므로 정렬을 수행해도 제한 시간을 초과하지 않는다.

import java.io.*;
import java.util.*;

public class P1920 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(token.nextToken());
        int[] A = new int[N];

        token = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(token.nextToken());
        }
        Arrays.sort(A);     // A를 오름차순으로 정렬

        token = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(token.nextToken());

        token = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++){
            int find = Integer.parseInt(token.nextToken());
            int start = 0;
            int end = N - 1;
            while (start <= end) {
                // A를 오름차순 정렬을 해놨으므로 A배열의 0번째보다 작거나 마지막 배열의 수보다 크면 A 배열안에 없으므로 바로 0 출력
                if (A[A.length - 1] < find || A[0] > find){
                    System.out.println(0);
                    break;
                }

                int middle = (start + end) / 2;
                if(A[middle] == find){
                    System.out.println(1);
                    break;
                } else if(A[middle] > find){
                    end = middle - 1;
                } else if(A[middle] < find){
                    start = middle + 1;
                }
            }
            if(start > end) {
                System.out.println(0);
            }
        }
    }
}
