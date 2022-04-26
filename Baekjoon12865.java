import java.io.*;
import java.util.*;

/*  평범한 배낭 12865 120
 * 
 *  dp, knapsack
 *  
 *  무게별로 가장 가치는 값 저장 -> dp 
 */

class Baekjoon12865 {
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N;
	static int[][] arr; //초기 맵
	static int[][] dp;
	static int maxw = 0;
	static int ans = 0;
	
	static int res = 0; //결과

	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        
        //set
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        maxw = Integer.parseInt(st.nextToken());

        arr = new int[N+1][2];

        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(bf.readLine());
        	int w =	Integer.parseInt(st.nextToken());
        	int v =	Integer.parseInt(st.nextToken());
        	arr[i] = new int[] {w, v};
        }
        dp = new int[N+1][maxw+1]; 
        for(int i=1; i<=N; i++) {
        	for(int j=1; j<=maxw; j++) {
        		dp[i][j] = dp[i-1][j];
        		if(j-arr[i-1][0] < 0) { //무게 초과
        			continue;
        		}
        		dp[i][j] = Math.max(dp[i][j], arr[i-1][1]+dp[i-1][j-arr[i-1][0]]);//각각의 무게 마다 가치 더해줌. 최적의 해
        	}
        }
        System.out.println(dp[N][maxw]);
	}
}