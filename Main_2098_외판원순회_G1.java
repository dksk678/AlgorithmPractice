import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * bit masking + dp
 * 
 * bit연산으로 모든 도시 탐색값 중 최저 값저장
 * 
 * 
 */
public class Main_2098_외판원순회_G1 {
	static int[][] map;
	static int[][] dp;
	static int N;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		dp = new int[1<<N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < 1<<N; i++) {
			Arrays.fill(dp[i], -1);
		}
		
		int ans = DFS(1, 0);
		
		System.out.println(ans);
	}
	private static int DFS(int v, int idx) {
		if(v == (1<<N)-1) {
			if(map[idx][0] == 0) return 12345789;
			
			return map[idx][0];
		}
		
		if(dp[v][idx] != -1) return dp[v][idx];
		
		dp[v][idx] = 123456789;
		
		for (int i = 0; i < N; i++) {
			if((v & (1<<i))!=0 || map[idx][i]==0) continue;
			
			int result = DFS(v | (1<<i), i) + map[idx][i];
			dp[v][idx] = Math.min(result, dp[v][idx]);
		}
		
		return dp[v][idx];
	}	

}
