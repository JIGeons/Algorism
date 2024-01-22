// 문제
// N장의 카드가 있다. 각각의 카드는 차례로 1에서 N까지의 번호가 붙어 있으며, 1번 카드가 가장 위, N번 카드가 가장 아래인 상태로 놓여 있다. 이제 다음과 같은 동작을 카드가 1장 남을 때까지 반복한다.
// 먼저 가장 위에 있는 카드를 바닥에 버린다. 그 다음 가장 위에 있는 카드를 가장 아래에 있는 카드를 밑으로 옮긴다. 예를 들어 N = 4일 때를 생각해 보자. 카드는 가장 위에서부터 1, 2, 3, 4의 순서대로 놓여 있다. 1을 버리면 2, 3, 4가 남는다. 여기서 2를 가장 아래로 옮기면 순서가 3, 4, 2가 된다. 3을 버리면 4, 2가 남고, 4를 밑으로 옮기면 순서가 2, 4가 된다. 마지막으로 2를 버리면 카드 4가 남는다. N이 주어졌을 때 가장 마지막에 남는 카드를 구하는 프로그램을 작성하시오.
// 입력
// 1번째 줄에 정수 N(1 ≤ N ≤ 500,000)이 주어진다.
// 출력
// 1번째 줄에 남는 카드의 번호를 출력한다.
// 문제 분석
// 큐를 잘 이해하고 있는지를 묻는 문제이다. 가장 위의 카드를 가장 아래에 있는 카드 밑으로 옮기는 동작은 큐의 선입선출 성질을 이용하면 쉽게 구현할 수 있다. 카드의 개수의 최대가 500,000이므로 시간 복잡도의 제약도 크지 않습니다. 큐로 이 문제를 해결해 보자.
// 문제 푸는 순서
// ① poll을 수행하며 맨 앞의 카드를 버린다.
// ② 과정 1에 이어 바로 add를 수행해 맨 앞에 있는 카드를 가장 아래로 옮긴다.
// ③ 큐의 크기가 1이 될 때까지 과정 1~2를 반복한 후 큐에 남은 원소를 출력한다.

import java.io.*;
import java.util.*;

public class P2164 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(token.nextToken());
        Queue<Integer> myQueue = new LinkedList<>();

        for(int i = 1; i <= N; i++){    // 카드를 큐에 저장하기
            myQueue.add(i);
        }

        while(myQueue.size() > 1){      // 카드가 1장 남을 때까지
            myQueue.poll();             // 맨 위의 카드를 버림
            myQueue.add(myQueue.poll());// 맨 위의 카드를 맨 밑으로 이동
        }

        System.out.println(myQueue.poll()); // 마지막으로 남은 카드 출력
    }
}
