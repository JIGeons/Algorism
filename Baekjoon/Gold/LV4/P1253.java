// 문제
// 주어진 N개의 수에서 다른 두 수의 합으로 표현되는 수가 있다면 그 수를 ‘좋은 수’라고 한다. N개의 수 중 좋은 수가 총 몇 개인지 출력하시오.
// 입력
// 1번째 줄에 수의 개수 N(1 ≤ N ≤ 2,000), 2번째 줄에 N개의 수의 값(Ai)이 주어진다(|Ai| ≤ 1,000,000,000, Ai는 정수).
// 출력
// 좋은 수의 개수를 출력한다.
// 문제 분석
// 시간 복잡도부터 생각해 보자. N의 개수가 최대 2,000이라 가정해도 좋은 수 하나를 찾는 알고리즘의 시간 복잡도는 N^2보다 작아야 한다. 만약 시간 복잡도가 N^2인 알고리즘을 사용하면 최종 시간 복잡도는 N^3이 되어 제한 시간 안에 문제를 풀 수 없기 때문이다. 따라서 좋은 수 하나를 찾는 알고리즘의 시간 복잡도는 최소 O(nlogn)이어야 한다. 정렬, 투 포인터 알고리즘을 사용하면 된다. 단 정렬된 데이터에서 자기 자신을 좋은 수 만들기에 포함하면 안된다. 이점을 예외로 처리해야 한다는 것을 염두에 두고 문제에 접근해 보자.

import java.io.*;
import java.util.*;

public class P1253 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(token.nextToken());
        long N[] = new long[n];

        token = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            N[i] = Long.parseLong(token.nextToken());
        }

        // N배열 정렬
        Arrays.sort(N);

        int count = 0;
        for(int k=0; k < n; k++){
            int start = 0;
            int end = k-1;
            while(start < end) {
                if(N[start] + N[end] < N[k]){
                    start++;
                } else if(N[start] + N[end] > N[k]){
                    end--;
                } else {
                    count++;
                    break;
                }
            }
        }
        System.out.println(count);
    }
}
