// 문제
// N개의 숫자가 공백 없이 써 있다. 이 숫자를 모두 합해 출력하는 프로그램을 작성하시오.
// 입력
// 1번째 줄에 숫자의 개수 N(1 <= N <= 100), 2번째 줄에는 숫자 N개가 공백 없이 주어진다.
// 문제 분석
// 최대 100개의 숫자가 공백없이 나열이 되므로 int나 long형 변수에 담을 수 없으므로 char[]로 받아서 덧셈을 한다.
import java.io.*;
import java.util.*;
public class P11720 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        // 입력값을 String형 변수 sNum에 저장한 후 char[]형 변수로 변환하기
        String sNum = br.readLine();
        char[] cNum = sNum.toCharArray();   // String을 Char형 배열로 변환하는 함수 .toCharArray()
        int sum = 0;
        for (int i = 0; i < cNum.length; i++) {
            sum += cNum[i] - '0';   // cNum[i]를 정수형으로 변환하면서 sum에 더하여 누적하기
        }
        System.out.print(sum);
    }
}
