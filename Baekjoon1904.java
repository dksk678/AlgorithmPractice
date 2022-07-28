import java.io.*;
import java.util.*;
/*   01타일 1904 10
 *   DP.
 */
public class Baekjoon1904 {
	static int N;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		
		int[] dp = new int[N+1];
		dp[0] = 1;
		dp[1] = 2;
		
		for (int i=2; i < N; i++) {
			dp[i] =(dp[i-1]%15746+dp[i-2]%15746)%15746;
		}
		
		System.out.println(dp[N-1]);
	}

	static void print(boolean[][] arr) {
		for(boolean[] i:arr) {
			for(boolean j:i) {
				System.out.print(j +" ");
			}
			System.out.println("");
		}
		System.out.println("-----------");
	}
}
