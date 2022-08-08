import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/* 퇴사 14501 60
 * 
 *  DP, 무시하고 건너가는 칸은 기존 값을 저장해야됨..
 */
public class Baekjoon14501 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
        
		int[] dp = new int[N+1];
		int[][] arr = new int[N+1][2];
        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	int T = Integer.parseInt(st.nextToken());
        	int P = Integer.parseInt(st.nextToken());
        	
			arr[i][0] =  T;
			arr[i][1] =  P;
		}
        
        for (int i = 0; i < N; i++) {
        	if(i+arr[i][0]<=N) {
        		dp[i+arr[i][0]] = Math.max(dp[i+arr[i][0]], dp[i]+arr[i][1]); 
        	}
        	dp[i+1] = Math.max(dp[i+1], dp[i]); //선택안해도 기존값을 저장해야됨.
		}
        
		System.out.println(dp[N]);
	}
}
