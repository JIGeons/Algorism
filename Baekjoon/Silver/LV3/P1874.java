// 문제
// 임의의 수열을 스택에 넣었다가 출력하는 방식으로 오름차순 수열을 출력할 수 있는지 확인하고, 출력할 수 있다면 push와 pop 연산을 어떤 순서로 수행해야 하는지 알아내는 프로그램을 작성해보자
// 입력
// 1번째 줄에 수열의 개수n(1 ≤ n ≤ 100,000)이 주어진다. 2번째 줄에서 n개의 줄에는 수열을 이루는 1이상 n이하의 정수가 1개씩 순서대로 주어진다. 이때 같은 정수가 두 번 이상 나오지는 않는다.
// 출력
// 오름차순 수열을 만들기 위한 연산 순서를 출력한다. push 연산은 +, pop 연산은 -로 출력하고, 불가능 할 때는 NO를 출력한다.
// 문제 분석
// 스택의 원리를 정확하게 알고 있는지를 묻는 문제이다. 이 문제는 스택의 pop, push 연산과 후입선출 개념을 이해하고 있다면 쉽게 풀 수 있다. 스택에 넣는 값은 오름차순 정렬이어야 한다는 것에 유념하며 손으로 문제를 풀어 보자.
// 스택 연산 수행 방법
//1. 현재 수열 값 ≥ 자연수
//    현재 수열 값이 자연수보다 크거나 같을 때까지 자연수를 1씩
//    증가시키며 자연수를 스택에 push한다. 그리고 push가 끝나면
//    수열을 출력하기 위해 마지막 1회만 pop한다.
//    예를 들어 현재 수열 값이 4면 스택에는 1, 2, 3, 4를 push하고
//    마지막에 1회만 pop하면 4를 출력한 뒤 조건문을 빠져나온다.
//    자연수는 5가 된다.
//2. 현재 수열 값 < 자연수
//    현재 수열 값보다 자연수가 크다면 pop으로 스택에 있는 값을
//    꺼낸다. 꺼낸 값이 현재 수열 값이거나 아닐 수 있다. 만약 아니라
//    면 후입선출 원리에 따라 수열을 표현할 수 없으므로 NO를 출력
//    한 후 문제를 종료하고, 현재 수열 값이라면 그대로 조건문을 빠
//    져나온다.
//   앞의 예를 이어 설명하면 자연수 5, 현재 수열 값은 3이므로 스택
//   에서 3을 꺼낸다. 현재 수열 값과 스택에서 꺼낸 값은 같으므로 계
//   속해서 스택 연산을 수행할 수 있다. 스택에는 1, 2가 남아 있으며,
//   자연수는 5다.

import java.io.*;
import java.util.*;

public class P1874 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        Stack<Integer> stack = new Stack<>();
        StringBuffer bf = new StringBuffer();

        int N = Integer.parseInt(token.nextToken());
        int num = 1;

        boolean result = true;
        for(int i = 1; i <= N; i++) {
            token = new StringTokenizer(br.readLine());
            int su = Integer.parseInt(token.nextToken());
            if (su >= num) {
                while (su >= num) {
                    stack.push(num++);
                    bf.append("+\n");
                }
                stack.pop();
                bf.append("-\n");
            } else {
                int n = stack.pop();
                if (n > su) {
                    System.out.println("NO");
                    result = false;
                    break;
                } else {
                    bf.append("-\n");
                }
            }
        }
        if(result) System.out.println(bf.toString());
    }
}
