import java.io.*;
import java.util.*;
/*  동전1 2293 90
 *  DP
 *  3 10
 *  1,2,5
 *  -> 1로 1~10만들 수 있는 경우의 수  = 무조건 1    1, 1, 1, 1, 1
 *  -> 1,2 로 4를 만들 수 있는 경우의 수 => 2를 포함 안하고 4를 만들 수 있는 경우 + 2(4-2)를 만들 수 있는 경우의 수 = 3(1+2)
 *  -> 1,2,5로 7을 만들 수 있는 경우의 수 => 5를 포함 안하고 7을 만들 수 있는 경우 + 5를 포함하고 2(7-5)를 만들 수 있는 수 = 6가지(4+2)
 */
class Baekjoon2293 {
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static BufferedWriter bw;
	static int N, K;
	
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));      
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        //set
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int num = 0;
        int[] dp = new int[K+1];
        dp[0] = 1;
        
        for(int i=0; i<N; i++) {
        	num = Integer.parseInt(br.readLine());
        	for(int j=num; j<=K; j++) {
        		//arr[i]를 사용 안하고 j만들 수 있는 경우의 수+ j-arr[i]로 만들 수 있는 경우의 수(즉, arr[i]가 포함된 경우의 수)
        		dp[j] = dp[j]+dp[j-num]; 
        	}
        }

        bw.write(dp[K]+"");
        bw.flush();
        bw.close();
        br.close();
	}
}