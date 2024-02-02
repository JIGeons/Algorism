// **문제**
// 세준이는 양수와 +, 1 그리고 괄호를 이용해 어떤 수식을 만들었다. 그리고 괄호를 모두 지우고, 다시 괄호를 적절히 넣어 이 수식의 값을 최소로 만들려고 한다. 이렇게 수식의 괄호를 다시 적절하게 배치해 수식의 값을 최소로 만드는 프로그램을 작성하시오.
// **입력**
// 1번째 줄에 식이 주어진다. 식은 ‘0’~’9’, ‘+’그리고 ‘-’만으로 이뤄져 있고, 가장 처음과 마지막 문자는 숫자다. 그리고 연속해서 2개 이상의 연산자가 나타나지 않고, 5자리보다 많이 연속되는 숫자는 없다. 수는 0으로 시작할 수 있다. 입력으로 주어지는 식의 길이는 50보다 작거나 같다.
// **출력**
// 1번째 줄에 정답을 출력한다.

import java.io.*;
import java.util.*;

public class P1541 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        String example = token.nextToken();

        String[] str = example.split("-");
        int answer = 0;

        for(int i = 0; i < str.length; i++){
            int temp = mySum(str[i]);
            if(i == 0)
                answer += temp; // 가장 앞에 있는 값만 더함
            else
                answer -= temp; // 뒷부분은 더한 값들을 뺌
        }
        System.out.println(answer);
    }

    static int mySum(String a) {    // 나뉜 그룹의 더하기 연산 수행 함수
        int sum = 0;
        String[] temp = a.split("[+]");
        for (int i = 0; i < temp.length; i++){
            sum += Integer.parseInt(temp[i]);
        }
        return sum;
    }
}
