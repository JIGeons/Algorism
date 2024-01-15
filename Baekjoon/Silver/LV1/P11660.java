// 문제
// N x N개의 수가 N x N 크기의 표에 채워져 있다. 표 안의 수 중 (X1, Y1)에서 (X2, Y2)까지의 합을 구하려 한다. X는 행, Y는 열을 의미한다. 예를 들어 N = 4이고 표가 다음과 같이 채워져 있을 때를 살펴보자. (2, 2)에서 (3, 4)까지의 합을 구하면 3 + 4 + 5 + 4 + 5 + 6  = 27이고, (4, 4)에서 (4, 4)까지의 합을 구하면 7이다. 표에 채워져 있는 수와 합을 구하는 연산이 주어졌을 때 이를 처리하는 프로그램을 작성하시오.
// 입력
// 1번째 줄에 표의 크기 N과 합을 구해야 하는 횟수 M이 주어진다.(1≤ N ≤ 1024, 1 ≤ M ≤ 100,000). 2번째 줄부터 N개의 줄에는 표에 채워져 있는 수가 1행부터 차례대로 주어진다. 다음 M개의 줄에는 4개의 정수 X1, Y1, X2, Y2가 주어지며, (X1, Y1)에서 (X2, Y2)의 합을 구해 출력해야 한다. 표에 채워져 있는 수는 1,000보다 작거나 같은 자연수다(X1 ≤ X2, Y1 ≤ Y2).
// 출력
// 총 M줄에 걸쳐 (X1, Y1)에서 (X2, Y2)까지 합을 구해 출력한다.
// 문제 분석
// 먼저 질의의 개수가 100,000이므로 이 문제 역시 질의마다 합을 구하면 안되고, 구간 합 배열을 이용해야 한다는 것을 알 수 있다. 구간 합 배열이 1차원에서 2차원으로 확장된 것으로 생각하여 구간 합 배열을 어떻게 구성할지 고민하는 것이 이 문제의 핵심이다. 1차원 구간 합 배열은 다음과 같이 정의할 수 있다.
// 2차원 구간 합 배열 D[X][Y] 정의
// D[X][Y] = 원본 배열의 (0, 0)부터 (X, Y)까지의 사각형 영역 안에 있는 수의 합
// D[i][j]의 값을 채우는 구간 합의 공식
// D[i][j] = D[i][j-1] + D[i-1][j] - D[i-1][j-1] + A[i][j]
// 질의 X1, Y1, X2, Y2에 대한 답을 구간 합으로 구하는 방법
// D[X2][Y2] - D[X1-1][Y2] - D[X2][Y1-1] + D[X1-1][Y1-1]

import java.io.*;
import java.util.*;

public class P11660 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(token.nextToken());
        int m = Integer.parseInt(token.nextToken());

        long N[][] = new long[n+1][n+1];
        for (int i = 1; i <= n ; i++){
            token = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++){
                N[i][j] = N[i][j-1] + N[i-1][j] - N[i-1][j-1] + Integer.parseInt(token.nextToken());
            }
        }

        for (int i = 0; i < m; i++) {
            token = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(token.nextToken());
            int y1 = Integer.parseInt(token.nextToken());
            int x2 = Integer.parseInt(token.nextToken());
            int y2 = Integer.parseInt(token.nextToken());

            long answer = N[x2][y2] - N[x2][y1-1] - N[x1-1][y2] + N[x1-1][y1-1];
            System.out.println(answer);
        }
    }
}
