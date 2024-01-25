// 문제
// 버블 소트는 서로 인접해 있는 두 수를 바꾸면서 정렬하는 방법이다. 예를 들어 수열이 3, 2, 1이었다고 가정해 보자. 이때는 인접해 있는 3, 2가 바뀌어야 하므로 2, 3, 1이 된다. 그 다음은 3, 1이 바뀌어야 하므로 2, 1, 3이 된다. 그 다음에는 2, 1이 바뀌어야 하므로 1, 2, 3이 된다. 그러면 더 이상 바꿀 수 없으므로 정렬이 완료된다.
// N개의 수로 이뤄진 수열 A[1], A[2], …, A[N]이 있다. 이 수열로 버블 소트를 수행할 때 swap이 총 몇번 발생하는지 알아내는 프로그램을 작성하시오.
// 입력
// 1번째 줄에 N(1 ≤ N ≤ 500,000), 2번째 줄에 N개의 정수로 A[1], A[2], …, A[N]이 주어진다. 각각의 A[i]는 0 ≤ |A[i]| ≤ 1,000,000,000의 범위에 들어 있다.
// 출력
// 1번째 줄에 swap 횟수를 출력한다.
// 문제 분석
// N의 최대 범위가 1,000,000이므로 O(nlogn)의 시간 복잡도로 정렬을 수행하면 된다. 앞에서 배운 병합 정렬로 정렬을 수행한 후 결과를 출력해보자.
//제목은 버블 소트이지만, N의 최대 범위가 5,000,000이므로 곧이곧대로 버블 소트를 사용하면 제한 시간을 초과한다. 즉, 이 문제는 버블 소트가 아닌 O(nlogn)의 시간 복잡도를 가진 병합 정렬을 사용해야 한다. 병합 정렬을 이해한 상태라면 두 그룹을 병합하는 과정에 버블 정렬의 swap이 포함되어 있다는 것을 떠올릴 수 있다.

import java.io.*;
import java.util.*;

public class P1517 {
    public static int[] A, tmp;
    public static long result;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(token.nextToken());
        A = new int[N];
        tmp = new int[N];

        token = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(token.nextToken());
        }

        MergeSort(0, N-1);  // 병합 정렬 수행하기
        System.out.println(result);
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
                result = result + index2 - k;   // 뒤쪽 데이터 값 이 작은 경우 result 업데이트
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
