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

        String str = token.nextToken();

        int sum = 0;
        int plus_sum = 0;
        int s = 0;  // 숫자가 시작되는 index를 가리키는 변수
        boolean first_number = true;    // '-'가 나오기 전 첫번째 숫자인지 판별하는 변수

        for(int i = 0; i < str.length(); i++) {
            if(str.substring(i, i+1).equals("-")){
                plus_sum += Integer.parseInt(str.substring(s, i));
                if(first_number){   // 첫번째 숫자는 음수가 아니므로 +한다.
                    sum += plus_sum;
                    first_number = false;   // '-'가 나와기 때문에 false로 변경
                } else {
                    sum -= plus_sum;
                }
                plus_sum = 0;
                s = i+1;
            } else if(str.substring(i, i+1).equals("+")){
                plus_sum += Integer.parseInt(str.substring(s, i));
                s = i+1;
            }
        }
        if (first_number){  // first_number가 true인 것은 '-'가 한 번도 나오지 않은 것이므로 '+'한다.
            plus_sum += Integer.parseInt(str.substring(s, str.length()));
            sum += plus_sum;
        } else if(plus_sum != 0){   // plus_num이 0이 아니면 이전 부호가 + 인 것이므로 plus_sum에 마지막 수를 더해주고 sum - plus_sum을 한다.
            plus_sum += Integer.parseInt(str.substring(s, str.length()));
            sum -= plus_sum;
        } else {
            sum -= Integer.parseInt(str.substring(s, str.length()));
        }
        System.out.println(sum);
    }
}
