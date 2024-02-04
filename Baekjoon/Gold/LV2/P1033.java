// **문제**
// 모든 자리가 1로만 이뤄진 두 자연수 A와 B가 주어져 있다. 이때 A와 B의 최대 공약수를 구하는 프로그램을 작성하시오. 예를 들어 A가 111이고, B가 1111일 때 A와 B의 최대 공약수는 1이다. A가 111이고, B가 111111일 경우에는 최대 공약수가 111이다.
// **입력**
// 1번째 줄에 두 자연수 A와 B를 이루는 1의 개수가 주어진다. 입력되는 수는 263보다 작은 작은 자연수다.
// **출력**
// 1번째 줄에 A와 B의 최대 공약수를 출력한다. 정답은 1,000만 자리를 넘지 않는다.
// **문제 분석**
// - 수의 길이를 나타내는 두 수의 최대 공약수는 A와 B의 최대 공약수의 길이를 나타낸다.
// - 즉, 3, 6의 최대 공약수 3은 A(111)와 B(111111)의 최대 공약수(111)의 길이로 나타난다.

import java.io.*;
import java.util.*;

public class P1850 {
    static ArrayList<cNode>[] A;
    static long lcm;
    static boolean visited[];
    static long D[];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(token.nextToken());

        A = new ArrayList[N];   // 인접 리스트
        visited = new boolean[N];   // 각 노드 탐색 여부 저장 배열
        D = new long[N];    // 각 노드값 저장 배열
        lcm = 1;    // 최소 공배수

        for(int i = 0; i < N; i++){
            A[i] = new ArrayList<cNode>();
        }
        for(int i = 0; i < N-1; i++){
            token = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(token.nextToken());
            int b = Integer.parseInt(token.nextToken());
            int p = Integer.parseInt(token.nextToken());
            int q = Integer.parseInt(token.nextToken());
            A[a].add(new cNode(b, p, q));
            A[b].add(new cNode(a, q, p));
            lcm *= (p * q / gcd(p, q)); // 최소 공배수는 두 수의 곲을 최대 공약수로 나눈 것
        }
        D[0] = lcm;
        DFS(0);
        long mgcd = D[0];
        for(int i = 1; i < N; i++){
            mgcd = gcd(mgcd, D[i]);
        }
        for(int i = 0; i < N; i++){
            long result = D[i] / mgcd;
            bw.write(result + " ");
        }
        bw.flush();
        bw.close();
    }

    public static long gcd(long a, long b){ // 최대 공약수 함수 구현하기
        if(b == 0)
            return a;
        else
            return gcd(b, a % b);
    }
    public static void DFS(int Node) {  // DFS 구현하기
        visited[Node] = true;
        for (cNode i : A[Node]){
            int next = i.getB();
            if(!visited[next]){
                D[next] = D[Node] * i.getQ() / i.getP();     //  주어진 비율로 다음 노드값 갱신하기
                DFS(next);
            }
        }
    }
}
class cNode {
    int b;
    int p;
    int q;
    public cNode(int b, int p, int q){
        super();
        this.b = b;
        this.p = p;
        this.q = q;
    }
    public int getB(){
        return b;
    }
    public int getP(){
        return p;
    }
    public int getQ(){
        return q;
    }
}
