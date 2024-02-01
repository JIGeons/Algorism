// **문제**
// 준규가 소유하고 있는 동전은 총 N종류이고, 각 동전의 개수가 많다. 동전을 적절히 사용해 그 가격의 합을 K로 만들려고 한다. 이때 필요한 동전 개수의 최솟값을 구하는 프로그램을 작성하시오.
// **입력**
// 1번째 줄과 N과 K(1 ≤ N ≤ 10, 1 ≤ K ≤ 100,000,000), 2번째 줄부터 N개의 줄에 동전의 가격 A가 오름차순으로 주어진다(1 ≤ Ai ≤ 1,000,000, A1 = 1, i ≥ 2일 때 Ai는 Ai-1의 배수).
// **출력**
// 1번째 줄에 K원을 만드는 데 필요한 동전 개수의 최솟값을 출력한다.
// **문제 분석**
// 전형적인 그리디 알고리즘 문제이다. 이 문제는 그리디 알고리즘으로 풀 수 있도록 뒤의 동전 가격 Ai가 앞에 나오는 동전 가격 Ai-1의 배수가 된다는 조건을 부여했다. 즉, 동전을 최소로 사용하여 K를 반들기 위해서는 가장 가격이 큰 동전부터 차례대로 사용하면 된다.

import java.io.*;
import java.util.*;

public class P11047 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(token.nextToken());
        int K = Integer.parseInt(token.nextToken());

        int[] A = new int[N];
        for(int i = 0; i < N; i++){
            token = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(token.nextToken());
        }
        int ans = 0;
        for(int i = N-1; i >= 0; i--){
            if(K >= A[i]){
                ans += K / A[i];
                K = K % A[i];
            }
            if(K == 0) break;
        }
        System.out.println(ans);
    }
}
