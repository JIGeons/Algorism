// **문제**
// 1개의 회의실에서 N개의 회의를 진행하기 위해 회의실 사용표를 만들려고 한다. 각 회의마다 시작 시간과 끝나는 시간이 주어질 때 회의 시간이 겹치지 않으면서 회의를 가장 많이 진행하려면 최대 몇 번까지 할 수 있는지 알아보자. 단, 회의를 시작하면 중간에 중단할 수 없고, 한 회의를 끝내는 것과 동시에 다음 회의를 시작할 수 있다. 회의의 시작과 끝나는 시간이 같을 수도 있는데, 이때는 시작하자마자 끝나는 것으로 생각하면 된다.
// **입력**
// 1번째 줄에 회의의 수 N(1 ≤ N ≤ 100,000), 2번째 줄부터 N + 1줄까지는 각 회의의 시작 시간과 끝나는 시간이 공백을 사이에 두고 주어진다. 시작 시간과 끝나는 시간을 -1보다 작거나 같은 자연수 또는 0이다.
// **출력**
// 1번째 줄에 진행할 수 있는 회의의 최대 개수를 출력한다.
// **문제 분석**
// 문제에서는 1개의 회의실에 회의가 겹치지 않게 최대한 많은 회의를 배정해야 한다. 이때는 그리디 알고리즘을 적용해야 하는데, 현재 회의의 종료 시간이 빠를수록 다음 회의와 겹치지 않게 시작하는 데 유리하다. 그렇게 때문에 종료 시간이 빠른 순서대로 정렬해 겹치지 않는 회의실을 적절하게 선택하면 이 문제를 해결할 수 있다.

import java.io.*;
import java.util.*;

public class P1931 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(token.nextToken());

        List<meeting> list = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());
            list.add(new meeting(s, e));
        }
        Collections.sort(list);

        int count = 0;
        int end = -1;
        for(meeting m : list){
            if(m.start >= end){ // 겹치지 않는 다음 회의가 나온 경우
                end = m.end;    // 종료 시간 업데이트 하기
                count++;
            }
        }
        System.out.println(count);
    }
}
class meeting implements Comparable{
    int start;
    int end;
    public meeting(int start, int end){
        this.start = start;
        this.end = end;
    }
    public int compareTo(Object obj){
        meeting m1 = (meeting) obj;
        int result = this.end - m1.end;

        if(result == 0){  // 종료 시간이 같을 경우 시작 시간이 빠른 회의를 우선으로 한다.
            result = this.start - m1.start;
        }

        return result;
    }
}
