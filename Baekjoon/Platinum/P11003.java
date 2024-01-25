// 문제
// N개의 수 A1, A2, …, AN과 L이 주어진다. Ai-L+1 ~ Ai중 최솟값을 Di라고 할 때 D에 저장된 수를 출력하는 프로그램을 작성하시오. 이때 i ≤ 0인 Ai는 무시하고 D를 구해야 한다.
// 입력
// 1번째 줄에 N과 L(1 ≤ L ≤ N ≤ 5,000,000), 2번째 줄에 N개의 수 Ai가 주어진다(-109 ≤ A ≤ 109).
// 출력
// 1번째 줄에 Di를 공백으로 구분해 순서대로 출력한다.
// 문제 분석
// 일정 범위 안에서 최솟값을 구하는 문제이므로 슬라이딩 윈도우와 정렬을 사용하면 될 것 같다. 윈도우의 크기는 문제에서 최솟값을 구하는 범위가 i-L+1 부터 i까지이므로 L로 생각하면 된다. 최솟값을 찾기 위한 정렬은 어떨까? 일반적으로 정렬은 nlogn(n)의 시간 복잡도를 가지고 N과 L의 최대 범위가 5,000,000인 이 문제에서는 정렬을 사용할 수 없다. 다시 말해 O(n)의 시간 복잡도로 해결해야 한다. 하지만 슬라이딩 윈도우를 덱으로 구현하여 정렬 효과를 볼 수 있다.

import java.io.*;
import java.util.*;

public class P11003 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 출력을 버퍼에 넣고 한 번에 출력하기 위해 BufferedWriter 사용
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(token.nextToken());
        int L = Integer.parseInt(token.nextToken());
        token = new StringTokenizer(br.readLine());
        Deque<Node> mydeque = new LinkedList<>();
        for(int i = 0; i < N; i++){
            int now = Integer.parseInt(token.nextToken());
            // 새로운 값이 들어올 때마다 정렬 대신 현재 수보다 큰 값을 덱에서 제거해 시간 복잡도를 줄임

            while (!mydeque.isEmpty() && mydeque.getLast().value > now){
                mydeque.removeLast();
            }
            mydeque.addLast(new Node(now, i));
            // 범위에서 벗어난 값은 덱에서 제거
            if (mydeque.getFirst().index <= i - L){
                mydeque.removeFirst();
            }
            bw.write(mydeque.getFirst().value + " ");
        }
        bw.flush();
        bw.close();
    }

    static class Node {
        public int value;
        public int index;

        Node(int value, int index){
            this.value = value;
            this.index = index;
        }
    }
}
