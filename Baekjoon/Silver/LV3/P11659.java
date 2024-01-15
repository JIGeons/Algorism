// 문제
// 수 N개가 주어졌을 때 i번째 수에서 j 번째 수까지의 합을 구하는 프로그램을 작성하시오.
// 입력
// 1번째 줄에 수의 개수 N(1 ≤ N ≤ 100,000), 합을 구해야 하는 횟수 M(1 ≤ M ≤ 100,000), 2번째 줄에 N개의 수가 주어진다. 각 수는 1,000보다 작거나 같은 자연수다. 3번째 줄부터는 M개의 줄에 합을 구해야 하는 구간 i와 j가 주어진다.
// 출력
// 총 M개의 줄에 입력으로 주어진 i 번째 수에서 j 번째 수까지의 합을 출력한다.
// 문제 분석
// 문제에서 수의 개수와, 합을 구해야 하는 횟수는 최대 100,000이다. 게다가 구간마다 합을 매번 계산하면 0.5초 안에 모든 구간 합 계산을 끝낼 수 없다. 이럴 때 바로 구간 합을 이용해야 한다. 구간 합 개념을 적용하는 가장 기본적인 문제이므로 핵심 이론에서 배웠던 내용을 그대로 적용해 코드를 작성하면 된다.
import java.io.*;
import java.util.*;

public class P11659 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        int suNo = Integer.parseInt(stringTokenizer.nextToken());
        int quizNo = Integer.parseInt(stringTokenizer.nextToken());

        long[] S = new long[suNo + 1];
        stringTokenizer = new StringTokenizer(br.readLine());

        for(int i = 1; i<= suNo; i++){
            S[i] = S[i-1] + Integer.parseInt(stringTokenizer.nextToken());
        }

        for(int q = 0; q < quizNo ; q++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(stringTokenizer.nextToken());
            int end = Integer.parseInt(stringTokenizer.nextToken());
            
            System.out.println(S[end] - S[start - 1]);
        }
    }
}
