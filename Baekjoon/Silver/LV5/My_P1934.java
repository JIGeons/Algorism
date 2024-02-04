import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(token.nextToken());
        int[] A = new int[N];
        int[] B = new int[N];

        for(int i = 0; i < N; i++){
            token = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(token.nextToken());
            B[i] = Integer.parseInt(token.nextToken());
        }

        int Max, Min;
        for(int i = 0; i < N; i++){
            if(A[i] > B[i]) {
                Max = A[i];
                Min = B[i];
            } else {
                Max = B[i];
                Min = A[i];
            }
            while(Max % Min != 0){
                int temp = Max;
                Max = Min;
                Min = temp % Min;
            }
            int result = A[i] * B[i] / Min;
            System.out.println(result);
        }
    }
}
