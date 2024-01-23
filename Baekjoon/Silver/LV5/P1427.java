// 문제
// 배열을 정렬하는 것은 쉽다. 수가 주어지면 그 수의 각 자릿수를 내림차순으로 정렬하시오.
// 입력
// 1번째 줄에 정렬할 수 N이 주어진다. N은 1,000,000,000보다 작거나 같은 자연수다.
// 출력
// 1번째 줄에 자릿수를 내림차순 정렬한 수를 출력한다.
// 문제 분석
// 자연수를 받아 자릿수별로 정렬하는 문제이므로 먼저 숫자를 각 자릿수별로 나누는 작업이 필요하다. 나머지 연산으로 분리할 수도 있지만 여기서는 입력값을 String으로 받은 후 substring() 함수를 이용해 자릿수 단위로 분리하고, 이를 다시 int형으로 변경해 배열에 저장한다. 그 다음에는 단순하게 배열을 정렬하면 된다. 자바의 내장 함수를 사용해도 되지만, N의 길이가 크지 않으므로 앞에서 배운 선택 정렬을 사용해 내림차순 정렬을 수행해 보자.

import java.io.*;
import java.util.*;

public class P1427 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        String number = token.nextToken();
        int[] num = new int[number.length()];
        for(int i = 0; i < number.length(); i++){
            num[i] = Integer.parseInt(number.substring(i,i+1));
        }
        for (int i = 0; i < number.length(); i++){
            int Max = i;
            for(int j = i + 1; j < number.length(); j++){
                if( num[j] > num[Max] ){
                    Max = j;
                }
                if(num[i] < num[Max]){
                    int tmp = num[i];
                    num[i] = num[Max];
                    num[Max] = tmp;
                }
            }
        }
        for(int i=0; i<number.length(); i++){
            System.out.print(num[i]);
        }
    }
}
