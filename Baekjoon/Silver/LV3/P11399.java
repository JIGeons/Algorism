// 입력
// 1번째 줄에 사람의 수 N(1 ≤ N ≤ 1,000), 2번째 줄에 각 사람이 돈을 인출하는 데 걸리는 시간 Pi(1 ≤ Pi ≤ 1,000)가 주어진다.
// 출력
// 1번째 줄에 각 사람이 돈을 인출하는 데 필요한 시간의 합의 최솟값을 출력한다.
// 문제 분석
// ATM에서 모든 사람이 가장 빠른 시간에 인출하는 방법을 그리디 방식으로 해결해 보자. ATM 앞에 있는 사람 중 인출 시간이 가장 적게 걸리는 사람이 먼저 인출할 수 있도록 순서를 정하는 것이 곧 그리디 방식이다. 그리고 이를 위해서는 인출 시간을 기준으로 값을 정렬해야 한다. N의 최댓값이 1,000이고, 시간 제한이 1초이므로 시간 복잡도가 O(n^2)이하인 정렬 알고리즘 중 아무거나 사용해도 된다. 여기서는 삽입 정렬을 이용하자. 정렬을 마친 후에는 각 사람이 돈을 인출하는 데 필요한 시간을 더하면 된다.

import java.io.*;
import java.util.*;

public class P11399 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(token.nextToken());
        int[] A = new int[N];
        int[] sum = new int[N];

        token = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(token.nextToken());
        }
        Arrays.sort(A);
        sum[0] = A[0];
        int result = A[0];
        for(int i = 1; i < N; i++){
            sum[i] = sum[i-1] + A[i];
            result += sum[i];
        }
        System.out.println(result);
    }

    // 누적합 방식
    public void Cumulative_Sum(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(token.nextToken());
        int[] A = new int[N];
        int[] sum = new int[N];

        token = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(token.nextToken());
        }
        Arrays.sort(A);
        sum[0] = A[0];
        int result = A[0];
        for(int i = 1; i < N; i++){
            sum[i] = sum[i-1] + A[i];
            result += sum[i];
        }
        System.out.println(result);
    }
    
    // 삽입 정렬
    public void insert_sort(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(token.nextToken());
        int[] A = new int[N];
        int[] sum = new int[N];

        token = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(token.nextToken());
        }
        for(int i = 1; i < N; i++){
            int insert_point = 1;
            int insert_value = A[i];
            for(int j = i-1; j >= 0; j--){
                if(A[j] < A[i]){
                    insert_point = j + 1;
                    break;
                }
                if (j == 0){
                    insert_point = 0;
                }
            }

            for(int j = i; j > insert_point; j--){
                A[j] = A[j-1];
            }
            A[insert_point] = insert_value;
        }
        sum[0] = A[0];
        for(int i = 1; i < N; i++){
            sum[i] = sum[i-1] + A[i];
        }
        int result = 0;
        for(int i = 0; i < N; i++){
            result += sum[i];
        }
        System.out.println(result);
    }
}
