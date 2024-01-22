// 입력
// 1번째 줄에 민호가 임의로 만든 DNA 문자열의 길이 |S|와 비밀번호로 사용할 부분 문자열의 길이 |P|가 주어진다(1 ≤ |P| ≤ |S| ≤ 1,000,000). 2번째 줄에 민호가 임의로 만든 DNA 문자열이 주어진다. 3번째 줄에 부분 문자열에 포함돼야 할 {’A’, ‘C’, ‘G’, ‘T’}의 최소 개수가 공백 문자를 사이에 두고 각각 주어진다. 각각의 수는 |S|보다 작거나 같은 음이 아닌 정수로 총합은 |S|보다 작거나 같다는 것이 보장된다.
// 출력
// 좋은 수의 개수를 출력한다.
// 문제 분석
// P와 S의 길이가 1,000,000으로 매우 크기 때문에 O(n)의 시간 복잡도 알고리즘으로 문제를 해결해야 한다. 이때 부분 문자열의 길이가 P이므로 슬라이딩 윈도우의 개념을 이용하면 문제를 쉽게 해결할 수 있다.

import java.io.*;
import java.util.*;

public class P12981 {
    static int checkArr[];  // 비밀번호 체크 배열
    static int myArr[];     // 현재 상태 배열
    static int checkSecret; // 몇 개의 문자와 관련된 개수를 충족했는지 판단하는 변수
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        int S = Integer.parseInt(token.nextToken());
        int P = Integer.parseInt(token.nextToken());

        token = new StringTokenizer(br.readLine());
        char[] DNA = new char[S];
        checkArr = new int[4];
        myArr = new int[4];
        checkSecret = 0;

        DNA = token.nextToken().toCharArray();

        token = new StringTokenizer(br.readLine());

        for(int i = 0; i < 4; i++){
            checkArr[i] = Integer.parseInt(token.nextToken());
            if(checkArr[i] == 0){
                checkSecret++;
            }
        }

        int count = 0;

        for(int i = 0; i < P; i++){
            Add(DNA[i]);
        }

        if(checkSecret == 4){
            count++;
        }

        for(int i = P; i < S; i++){
            Remove(DNA[i-P]);
            Add(DNA[i]);
            if(checkSecret == 4){
                count++;
            }
        }

        System.out.println(count);
    }

    public static void Add(char C){
        switch (C) {
            case 'A':
                myArr[0]++;
                if(myArr[0] == checkArr[0]) checkSecret++;
                break;
            case 'C':
                myArr[1]++;
                if(myArr[1] == checkArr[1]) checkSecret++;
                break;
            case 'G':
                myArr[2]++;
                if(myArr[2] == checkArr[2]) checkSecret++;
                break;
            case 'T':
                myArr[3]++;
                if(myArr[3] == checkArr[3]) checkSecret++;
                break;
        }
    }

    public static void Remove(char C){
        switch (C) {
            case 'A':
                if(myArr[0] == checkArr[0]) checkSecret--;
                myArr[0]--;
                break;
            case 'C':
                if(myArr[1] == checkArr[1]) checkSecret--;
                myArr[1]--;
                break;
            case 'G':
                if(myArr[2] == checkArr[2]) checkSecret--;
                myArr[2]--;
                break;
            case 'T':
                if(myArr[3] == checkArr[3]) checkSecret--;
                myArr[3]--;
                break;
        }
    }
}
