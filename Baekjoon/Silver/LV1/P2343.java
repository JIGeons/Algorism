// **문제**
// 강토는 자신의 기타 강의 동영상을 블루레이로 만들어 판매하려고 한다. 블루레이에는 총 N개의 강의가 들어가는데, 블루레이를 녹화할 때, 강의의 순서가 바뀌면 안 된다. 순서가 뒤바뀌는 경우에는 강의의 흐름이 끊겨, 학생들이 대혼란에 빠질 수 있기 때문이다. 즉, i번 강의와 j번 강의를 같은 블루레이에 녹화하려면 i와 j 사이의 모든 강의도 같은 블루레이에 녹화해야 한다.
// 강토는 이 블루레이가 얼마나 팔릴지 아직 알 수 없기 때문에, 블루레이의 개수를 가급적 줄이려고 한다. 오랜 고민 끝에 강토는 M개의 블루레이에 모든 기타 강의 동영상을 녹화하기로 했다. 이때, 블루레이의 크기(녹화 가능한 길이)를 최소로 하려고 한다. 단, M개의 블루레이는 모두 같은 크기이어야 한다.
// 강토의 각 강의의 길이가 분 단위(자연수)로 주어진다. 이때, 가능한 블루레이의 크기 중 최소를 구하는 프로그램을 작성하시오.
// **입력**
// 1번째 줄에 레슨의 수 N(1 ≤ N ≤ 100,000)과 M(1 ≤ M ≤ N), 2번째 줄에 강토의 기타 레슨의 길이가 레슨 순서대로 분 단위로(자연수)로 주어진다. 각 레슨의 길이는 10,000분을 넘지 않는다.
// **출력**
// 1번째 줄에 블루레이 크기 중 최솟값을 출력한다.
// **문제 분석**
// ’블루레이의 크기가 모두 같고 녹화 순서가 바뀌지 않아야 함’이라는 문제 조건이 이진 탐색 알고리즘을 선택하게 하는 실마리이다. 블루레이에 첫 레슨부터 마지막 레슨까지 차례대로 저장하다 보면 지정한 블루레이 크기로 모든 레슨을 저장할 수 있는지 판단할 수 있기 때문이다. 모두 저장할 수 있다면 블루레이 크기를 줄이고 저장할 수 없다면 블루레이 크기를 늘리는 방식으로 블루레이 크기의 최솟값을 알 수 있다.

import java.io.*;
import java.util.*;

public class P2343 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(token.nextToken());
        int M = Integer.parseInt(token.nextToken());
        int[] A = new int[N];

        token = new StringTokenizer(br.readLine());
        int start = 0;
        int end = 0;
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(token.nextToken());
            if(start < A[i]) start = A[i];  // A 배열 중 가장 큰 수가 start가 된다.
            end += A[i];    // A 배열의 총 합이 end가 된다.
        }

        while (start <= end){
            int middle = (start + end) / 2;
            int sum = 0;
            int count = 0;
            for(int i = 0; i < N; i++) {     // middle값으로 모든 레슨을 저장할 수 있는지 확인
                if (sum + A[i] > middle) {
                    count++;
                    sum = 0;
                }
                sum += A[i];
            }
            if (sum != 0)   // 탐색 후 sum이 0이 아니면 블루레이가 1개 더 필요하므로 더함
                count++;

            if (count > M){
                start = middle + 1;
            } else {
                end = middle - 1;
            }
        }
        System.out.println(start);
    }
}
