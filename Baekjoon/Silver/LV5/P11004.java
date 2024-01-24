// 문제
// 수 N개(A1, A2, …, AN)가 주어진다. A를 오름차순 정렬했을 때 앞에서부터 K번째에 있는 수를 구하는 프로그램을 작성하시오.
// 입력
// 1번째 줄에 N(1 ≤ N ≤ 5,000,000)과 K(1 ≤ K ≤ N), 2번째 줄에 A1, A2, …, AN이 주어진다.(-109 ≤ Ai ≤ 109).
// 출력
// A를 정렬했을 때 앞에서부터 K 번째에 있는 수를 출력한다.
// 문제 분석
// N의 최대 범위가 5,000,000이므로 O(nlogn)의 시간 복잡도로 정렬을 수행하면 된다. 앞에서 배운 퀵 정렬을 구현해 주어진 수를 오름차순 정렬하고, K번째 수를 출력해 보자. 단, 이 문제는 시간 복잡도가 민감하므로 퀵 정렬 알고리즘에서 K번째 수를 좀 더 빨리 구하기 위한 아이디어를 먼저 고민해 보자. 퀵 정렬 알고리즘을 구현하려면 먼저 pivot을 지정해야 한다. 이때 어떤 값을 pivot으로 정하면 K번째 수를 더 빨리 구할 수 있을지 생각해보자.

import java.io.*;
import java.util.*;

public class P11004 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(token.nextToken());
        int K = Integer.parseInt(token.nextToken());

        int[] A = new int[N];

        token = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(token.nextToken());
        }
        QuickSort(A, 0, N - 1, K - 1);
        System.out.println(A[K-1]);
    }
    public static void QuickSort(int[] A, int S, int E, int k){
        if (S < E) {
            int pivot = partition(A, S, E);
            if (pivot == k){    // k번째 수가 pivot이면 더이상 구할 필요 없음
                return;
            } else if ( k < pivot ) {   // k가 pivot보다 작으면 왼쪽 그룹만 정렬 수행하기
                QuickSort(A, S, pivot - 1, k);
            } else {    // k가 pivot보다 크면 오른쪽 그룹만 정렬 수행하기
                QuickSort(A, pivot + 1, E, k);
            }
        }
    }

    public static int partition(int[] A, int S, int E){
        if(S + 1 == E){
            if(A[S] > A[E])
                swap(A, S, E);
            return E;
        }
        int M = (S + E) / 2;
        swap(A, S, M);  // 중앙값을 1번째 요소로 이동하기
        int pivot = A[S];
        int i = S + 1, j = E;
        while( i <= j ){
            while (pivot < A[j] && j > 0){  // pivot보다 작은 수가 나올 때 까지 j--
                j--;
            }
            while (pivot > A[i] && i < A.length-1) {  // pivot보다 큰 수가 나올때 까지 i++
                i++;
            }
            if (i <= j){
                swap (A, i++, j--);
            }
        }
        A[S] = A[j];
        A[j] = pivot;
        return j;
    }
    public static void swap(int[] A, int i, int j){
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
}
