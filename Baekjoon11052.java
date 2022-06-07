import java.io.*;
import java.util.*;
/*  카드 구매하기 11052 50
 *  DP 
 *  dp[4] = dp[1]+dp[3] or dp[2]+dp[2] or dp[4]
 *  
 *  -> dp[i] = dp[j]+dp[i-j] or dp[i] 
 */
class Baekjoon11052 {
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static BufferedWriter bw;
	static long x, y;
	static int[] parents;
	static int[] rank;
	
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));      
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        //set
        
        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
        	dp[i] = Integer.parseInt(st.nextToken());
        }
        for(int i=2; i<=N; i++) {
        	for(int j=1; j<i/2+1; j++) {
        		dp[i] = Math.max(dp[j]+dp[i-j], dp[i]);
        	}
        }

        bw.write(dp[N]+"");
        bw.flush();
        bw.close();
        br.close();
	}
}