// 문제
// N개의 수가 주어졌을 때 이를 오름차순 정렬하는 프로그램을 작성하시오.
// 입력
// 1번째 줄에 수의 개수 N(1 ≤ N ≤ 1,000,000), 2번째 줄부터 N개의 줄에 숫자가 주어진다. 이 수는 절댓값이 1,000,000보다 작거나 같은 정수다. 수는 중복되지 않는다.
// 출력
// 1번째 줄부터 N개의 줄에 오름차순 정렬한 결과를 1줄에 1개씩 출력한다.
// 문제 분석
// N의 최대 범위가 1,000,000이므로 O(nlogn)의 시간 복잡도로 정렬을 수행하면 된다. 앞에서 배운 병합 정렬로 정렬을 수행한 후 결과를 출력해보자.

import java.io.*;
import java.util.*;

public class P2751 {
    public static int[] A, tmp;
    public static long result;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(token.nextToken());
        A = new int[N];
        tmp = new int[N];

        for(int i = 0; i < N; i++){
            token = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(token.nextToken());
        }

        MergeSort(0, N-1);  // 병합 정렬 수행하기

        for(int i=0; i < N; i++){
            bw.write(A[i] + "\n");
        }

        bw.flush();
        bw.close();
    }
    public static void MergeSort(int S, int E){
        if(E - S < 1){
            return;
        }
        int M = (E + S) / 2;
        MergeSort(S, M);
        MergeSort(M+1, E);
        for (int i = S; i <= E; i++){
            tmp[i] = A[i];
        }
        int k = S;
        int index1 = S;
        int index2 = M + 1;
        while (index1 <=M && index2 <= E){  // 두 그룹을 병합하는 로직
            // 양쪽 그룹의 index가 가리키는 값을 비교해 더 작은 수를 선택해 배열에 저장하고, 선택된 데이터의 index 값을 오른쪽으로 한 칸 이동하기.
            if (tmp[index1] > tmp[index2]){
                A[k] = tmp[index2];
                k++;
                index2++;
            } else {
                A[k] = tmp[index1];
                k++;
                index1++;
            }
        }
        while (index1 <= M){    // 한쪽 그룹이 모두 선택된 후 남아 있는 값 정리하기.
            A[k] = tmp[index1];
            k++;
            index1++;
        }
        while (index2 <= E){
            A[k] = tmp[index2];
            k++;
            index2++;
        }
    }
}
