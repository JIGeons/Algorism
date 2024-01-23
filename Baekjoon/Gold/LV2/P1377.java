// 문제
// n은 배열의 크기, a는 수가 들어 있는 배열이다. 수는 배열의 1번 방부터 채운다. 위와 같은 코드를 실행시켰을 때 어떤 값이 출력되는지를 구하는 프로그램을 작성하시오.
// 입력
// 1번째 줄에 N이 주어진다. N은 500,000보다 작거나 같은 자연수다. 2번째 줄부터 N개의 줄에 A[1]부터 A[N]까지 1개씩 주어진다. A에 들어 있는 수는 1,000,000보다 작거나 같은 자연수 또는 0이다.
// 버블 정렬의 swap이 한 번도 일어나지 않은 루프가 언제인지 알아내는 문제이다. 핵심 이론에서 언급했듯이 ‘버블 정렬의 이중 for 문에서 안쪽 for문 전체를 돌 때 swap이 일어나지 않았다’는 것은 이미 모든 데이터가 정렬됐다는 것을 의미한다. 이때는 프로세스를 바로 종료해 시간 복잡도를 줄일 수 있다. 하지만 이 문제는 N의 최대 범위가 500,000이므로 버블 정렬로 문제를 풀면 시간을 초과할 수 있다. 안쪽 for문이 몇 번 수행됐는지 구하는 다른 아이디어가 필요하다.
// 안쪽 for문이 몇 번 수행됐는지 구하는 다른 아이디어
// 안쪽 루프는 1에서 n-j까지, 즉 왼쪽에서 오른쪽으로 이동하면서 swap을 수행한다. 이는 특정 데이터가 안쪽 루프에서 swap의 왼쪽으로 이동할 수 있는 최대 거리가 1이라는 뜻이다. 즉, 데이터의 정렬 전 index와 정렬 후 index를 비교해 왼쪽으로 가장 많이 이동한 값을 찾으면 이 문제를 해결할 수 있다.

import java.io.*;
import java.util.*;

public class P1377 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(token.nextToken());
        mData[] A = new mData[N];

        for(int i = 0; i < N; i++){
            token = new StringTokenizer(br.readLine());
            A[i] = new mData(Integer.parseInt(token.nextToken()), i);
        }

        Arrays.sort(A); //  A 배열 정렬(O(nlogn) 시간 복잡도)
        System.out.println(A);
        int Max = 0;
        for(int i = 0; i < N; i++){     // (정렬 전 index) - (정렬 후 index) 계산의 최댓값 저장하기
            if(Max < A[i].index - i)
                Max = A[i].index - i;
        }
        System.out.println(Max + 1);
    }

    static class mData implements Comparable<mData>{
        int value;
        int index;

        public mData(int value, int index) {
            super();
            this.value = value;
            this.index = index;
        }

        @Override
        public int compareTo(mData o){      // value 기준 오름차순 정렬하기
            return this.value - o.value;
        }
    }
}
