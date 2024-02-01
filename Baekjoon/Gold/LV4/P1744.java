// **문제**
// 길이가 N인 수열이 주어질 때 수열의 합을 구하려고 한다. 그런데 수열의 삽을 구하기 전에 먼저 수열 안에 있는 임의의 두 수를 묶으려 한다. 위치에 상관없이 두 수를 묶을 수 있다. 단, 같은 위치에 있는 수(자기 자신)를 묶을 수는 없다. 묶인 두 수는 수열의 합을 구할 때 서로 곱한 후 계산한다. 수열의 모든 수는 각각 한 번씩만 묶을 수 있다. 예를 들어 어떤 수열이 {0, 1, 2, 4, 3, 5}일 때 그냥 이 수열의 합을 구하면 0 + 1 + 2 + 4 + 3 + 5 = 15이다. 하지만 2와 3을 묶고, 4와 5를 묶으면 0 + 1 + (2 * 3) + (4 * 5) = 27이 돼 최댓값이 나온다. 주어진 수열의 각 수를 적절히 묶어 그 합을 최대로 만드는 프로그램을 작성하시오.
// **입력**
// 1번째 줄에 수열의 크기 N이 주어진다. N은 10,000보다 작은 자연수다. 2번째 줄부터 N개의 줄에 수열의 각 수가 주어진다. 수열의 수는 -10,000보다 크거나 같고, 10,000보다 작거나 같은 정수다.
// **출력**
// 합이 최대가 나오게 수를 묶었을 때 그 합을 출력한다. 정답은 항상 231보다 작다.
// **문제 분석**
// N의 최대 범위가 10,000이므로 시간 복잡도와 관련된 제약은 적은 문제이다. 문제의 내용에 집중해 아이디어를 생각해 보자. 가능한 한 큰 수들끼리 묶어야 결과값이 커진다는 것을 알 수 있따. 주어진 수열이 1, 2, 3, 4라면 1*4 + 2*3보다 1*2 + 3*4의 결과값이 더 크다. 또한 음수끼리 곱하면 양수로 변하는 성질을 추가로 고려해 문제를 풀어보자.

import java.io.*;
import java.util.*;

public class P1744 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(token.nextToken());

        PriorityQueue<Integer> plus = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minus = new PriorityQueue<>();

        int one = 0;
        for(int i = 0; i < N; i++){
            token = new StringTokenizer(br.readLine());
            int data = Integer.parseInt(token.nextToken());
            if(data > 1){
                plus.add(data);
            } else if (data == 1){  // 1은 어떤 수랑 곱해도 의미가 없으므로 더하기만 한다.
                one++;
            } else {
                minus.add(data);
            }
        }

        int sum = 0;

        // 양수 처리하기
        while(plus.size() > 1){
            int p_data1 = plus.remove();
            int p_data2 = plus.remove();
            sum += p_data1 * p_data2;
        }
        if(!plus.isEmpty()){
            int p_data = plus.remove();
            sum += p_data;
        }

        // 음수 처리하기
        while(minus.size() > 1){
            int m_data1 = minus.remove();
            int m_data2 = minus.remove();
            sum += m_data1 * m_data2;
        }
        if(!minus.isEmpty()){
            int m_data = minus.remove();
            sum += m_data;
        }
        sum += one;
        System.out.println(sum);
    }
}
